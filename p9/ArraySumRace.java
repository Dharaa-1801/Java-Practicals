import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicLong;


// Compares an unsafe shared sum with two thread-safe implementations.
// Timing uses System.nanoTime() around only the worker execution. The array is
// created before timing so setup cost does not affect the comparison.
public class ArraySumRace {
    private static final int ARRAY_SIZE = 1_000_000;
    private static final int THREAD_COUNT = 4;

    private static final class UnsafeSum {
        private long total;
        private final CyclicBarrier addBarrier = new CyclicBarrier(THREAD_COUNT);

        void add(long value) throws InterruptedException, BrokenBarrierException {
            long currentTotal = total;
            // Force all workers to read the old total before writing.
            addBarrier.await();
            total = currentTotal + value;
        }

        long getTotal() {
            return total;
        }
    }

    private static final class SynchronizedSum {
        private long total;

        synchronized void add(long value) {
            total += value;
        }

        long getTotal() {
            return total;
        }
    }

    private static final class AtomicSum {
        private final AtomicLong total = new AtomicLong();

        void add(long value) {
            total.addAndGet(value);
        }

        long getTotal() {
            return total.get();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long[] numbers = createNumbers();
        long expected = expectedSum(numbers);

        UnsafeSum unsafeSum = new UnsafeSum();
        long unsafeTime = runWorkers(numbers, value -> unsafeSum.add(value));
        printResult("Without synchronization", unsafeSum.getTotal(), expected, unsafeTime);

        SynchronizedSum synchronizedSum = new SynchronizedSum();
        long synchronizedTime = runWorkers(numbers, value -> synchronizedSum.add(value));
        printResult("With synchronized add()", synchronizedSum.getTotal(), expected,
                synchronizedTime);

        AtomicSum atomicSum = new AtomicSum();
        long atomicTime = runWorkers(numbers, value -> atomicSum.add(value));
        printResult("With AtomicLong", atomicSum.getTotal(), expected, atomicTime);

        System.out.println("Timing is measured with System.nanoTime() and is reported in ms.");
    }

    private static long[] createNumbers() {
        long[] numbers = new long[ARRAY_SIZE];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (i % 100) + 1;
        }
        return numbers;
    }

    private static long expectedSum(long[] numbers) {
        long total = 0;
        for (long number : numbers) {
            total += number;
        }
        return total;
    }

    @FunctionalInterface
    private interface SumAction {
        void add(long value) throws Exception;
    }

    private static long runWorkers(long[] numbers, SumAction action) throws InterruptedException {
        Thread[] workers = new Thread[THREAD_COUNT];
        Throwable[] failures = new Throwable[THREAD_COUNT];
        int chunkSize = numbers.length / THREAD_COUNT;
        long startTime = System.nanoTime();

        for (int i = 0; i < THREAD_COUNT; i++) {
            final int workerNumber = i;
            final int start = i * chunkSize;
            final int end = (i == THREAD_COUNT - 1) ? numbers.length : start + chunkSize;
            workers[i] = new Thread(() -> {
                try {
                    for (int index = start; index < end; index++) {
                        action.add(numbers[index]);
                    }
                } catch (Throwable failure) {
                    failures[workerNumber] = failure;
                }
            }, "sum-worker-" + i);
            workers[i].start();
        }

        for (Thread worker : workers) {
            worker.join();
        }
        long elapsed = System.nanoTime() - startTime;
        for (Throwable failure : failures) {
            if (failure != null) {
                throw new IllegalStateException("A worker failed", failure);
            }
        }
        return elapsed;
    }

    private static void printResult(String label, long actual, long expected, long elapsedNanos) {
        System.out.printf("%s: %d (expected %d), time %.3f ms%n",
                label, actual, expected, elapsedNanos / 1_000_000.0);
    }
}
