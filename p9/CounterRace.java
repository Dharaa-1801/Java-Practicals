import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Demonstrates a lost-update race and its synchronized solution.
 */
public class CounterRace {
    private static final int THREAD_COUNT = 4;
    private static final int INCREMENTS_PER_THREAD = 1_000;

    private static final class UnsafeCounter {
        private int value;
        private final CyclicBarrier readBarrier;

        UnsafeCounter() {
            readBarrier = new CyclicBarrier(THREAD_COUNT);
        }

        void increment() throws InterruptedException, BrokenBarrierException {
            int currentValue = value;
            // All workers read before any worker writes, making the race visible.
            readBarrier.await();
            value = currentValue + 1;
        }

        int getValue() {
            return value;
        }
    }

    private static final class SafeCounter {
        private int value;

        synchronized void increment() {
            value++;
        }

        int getValue() {
            return value;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int expected = THREAD_COUNT * INCREMENTS_PER_THREAD;

        UnsafeCounter unsafeCounter = new UnsafeCounter();
        runWorkers((workerNumber) -> {
            for (int i = 0; i < INCREMENTS_PER_THREAD; i++) {
                unsafeCounter.increment();
            }
        });
        System.out.println("Without synchronization: " + unsafeCounter.getValue()
                + " (expected " + expected + ")");

        SafeCounter safeCounter = new SafeCounter();
        runWorkers((workerNumber) -> {
            for (int i = 0; i < INCREMENTS_PER_THREAD; i++) {
                safeCounter.increment();
            }
        });
        System.out.println("With synchronized increment(): " + safeCounter.getValue()
                + " (expected " + expected + ")");
    }

    @FunctionalInterface
    private interface WorkerAction {
        void run(int workerNumber) throws Exception;
    }

    private static void runWorkers(WorkerAction action) throws InterruptedException {
        Thread[] workers = new Thread[THREAD_COUNT];
        Throwable[] failures = new Throwable[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; i++) {
            final int workerNumber = i;
            workers[i] = new Thread(() -> {
                try {
                    action.run(workerNumber);
                } catch (Throwable failure) {
                    failures[workerNumber] = failure;
                }
            }, "counter-worker-" + i);
            workers[i].start();
        }

        for (Thread worker : workers) {
            worker.join();
        }
        for (Throwable failure : failures) {
            if (failure != null) {
                throw new IllegalStateException("A worker failed", failure);
            }
        }
    }
}
