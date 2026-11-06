import java.util.concurrent.CountDownLatch;

// Shows a deadlock caused by inconsistent lock ordering, then fixes it.
 
public class DeadlockDemo {
    private static final Object ACCOUNT_A = new Object();
    private static final Object ACCOUNT_B = new Object();
    private static final Object SAFE_ACCOUNT_A = new Object();
    private static final Object SAFE_ACCOUNT_B = new Object();

    public static void main(String[] args) throws InterruptedException {
        demonstrateDeadlock();
        demonstrateConsistentLockOrder();
    }

    private static void demonstrateDeadlock() throws InterruptedException {
        CountDownLatch firstLocksAcquired = new CountDownLatch(2);
        Thread firstThread = new Thread(() -> {
            synchronized (ACCOUNT_A) {
                firstLocksAcquired.countDown();
                awaitOtherThread(firstLocksAcquired);
                synchronized (ACCOUNT_B) {
                    System.out.println("This line is never reached.");
                }
            }
        }, "transfer-A-to-B");
        Thread secondThread = new Thread(() -> {
            synchronized (ACCOUNT_B) {
                firstLocksAcquired.countDown();
                awaitOtherThread(firstLocksAcquired);
                synchronized (ACCOUNT_A) {
                    System.out.println("This line is never reached.");
                }
            }
        }, "transfer-B-to-A");

        // Daemon threads allow the demonstration to finish after the timed check.
        firstThread.setDaemon(true);
        secondThread.setDaemon(true);
        firstThread.start();
        secondThread.start();
        firstLocksAcquired.await();
        firstThread.join(200);
        secondThread.join(200);

        System.out.println("Opposite lock order: deadlock detected = "
                + (firstThread.isAlive() && secondThread.isAlive()));
    }

    private static void awaitOtherThread(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Deadlock demonstration was interrupted",
                    exception);
        }
    }

    private static void demonstrateConsistentLockOrder() throws InterruptedException {
        Thread firstThread = new Thread(() -> transferSafely("A to B"), "safe-transfer-1");
        Thread secondThread = new Thread(() -> transferSafely("B to A"), "safe-transfer-2");

        firstThread.start();
        secondThread.start();
        firstThread.join();
        secondThread.join();
        System.out.println("Consistent lock order: both transfers completed.");
    }

    private static void transferSafely(String transferName) {
        // Both transfers acquire SAFE_ACCOUNT_A before SAFE_ACCOUNT_B.
        synchronized (SAFE_ACCOUNT_A) {
            synchronized (SAFE_ACCOUNT_B) {
                System.out.println("Completed transfer " + transferName);
            }
        }
    }
}
