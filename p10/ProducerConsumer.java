import java.util.ArrayDeque;
import java.util.Queue;


 // Coordinates one producer and one consumer using wait() and notifyAll().
 
public class ProducerConsumer {
    private static final int ITEM_COUNT = 10;
    private static final int BUFFER_CAPACITY = 3;

    private static final class SharedBuffer {
        private final Queue<Integer> items = new ArrayDeque<>();

        synchronized void put(int item) throws InterruptedException {
            while (items.size() == BUFFER_CAPACITY) {
                wait();
            }
            items.add(item);
            System.out.println("Produced: " + item);
            notifyAll();
        }

        synchronized int take() throws InterruptedException {
            while (items.isEmpty()) {
                wait();
            }
            int item = items.remove();
            System.out.println("Consumed: " + item);
            notifyAll();
            return item;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SharedBuffer buffer = new SharedBuffer();
        Throwable[] failures = new Throwable[2];

        Thread producer = new Thread(() -> {
            try {
                for (int item = 1; item <= ITEM_COUNT; item++) {
                    buffer.put(item);
                }
            } catch (Throwable failure) {
                failures[0] = failure;
            }
        }, "producer");

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < ITEM_COUNT; i++) {
                    buffer.take();
                }
            } catch (Throwable failure) {
                failures[1] = failure;
            }
        }, "consumer");

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();

        for (Throwable failure : failures) {
            if (failure != null) {
                throw new IllegalStateException("Producer-consumer worker failed", failure);
            }
        }
        System.out.println("All " + ITEM_COUNT + " items were transferred in order.");
    }
}
