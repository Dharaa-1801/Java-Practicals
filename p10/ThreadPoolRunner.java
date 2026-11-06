import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


 //Runs ten tasks on a fixed pool of three workers.
 
public class ThreadPoolRunner {
    private static final int POOL_SIZE = 3;
    private static final int TASK_COUNT = 10;

    public static void main(String[] args)
            throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(POOL_SIZE);
        List<Future<?>> completedTasks = new ArrayList<>();

        try {
            for (int taskId = 1; taskId <= TASK_COUNT; taskId++) {
                final int id = taskId;
                completedTasks.add(executor.submit(() -> {
                    System.out.println("Task " + id + " running on "
                            + Thread.currentThread().getName());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException exception) {
                        Thread.currentThread().interrupt();
                        throw new IllegalStateException("Task " + id + " was interrupted",
                                exception);
                    }
                }));
            }
        } finally {
            executor.shutdown();
        }

        if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
            executor.shutdownNow();
            throw new IllegalStateException("The thread pool did not finish in time");
        }

        // Future.get() makes task failures visible to the caller.
        for (Future<?> completedTask : completedTasks) {
            completedTask.get();
        }
        System.out.println("All " + TASK_COUNT + " tasks completed.");
    }
}
