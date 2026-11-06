import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

 // Demonstrates overselling seats when check-and-decrement is not atomic.
 
public class SeatBookingRace {
    private static final int INITIAL_SEATS = 5;
    private static final int CUSTOMER_COUNT = 10;

    private static final class UnsafeBooking {
        private int seatsLeft = INITIAL_SEATS;
        private final CyclicBarrier checkBarrier = new CyclicBarrier(CUSTOMER_COUNT);

        boolean book() throws InterruptedException, BrokenBarrierException {
            boolean seatWasAvailable = seatsLeft > 0;
            // Every customer checks before any customer performs a decrement.
            checkBarrier.await();
            if (seatWasAvailable) {
                seatsLeft--;
                return true;
            }
            return false;
        }

        int getSeatsLeft() {
            return seatsLeft;
        }
    }

    private static final class SafeBooking {
        private int seatsLeft = INITIAL_SEATS;

        synchronized boolean book() {
            if (seatsLeft == 0) {
                return false;
            }
            seatsLeft--;
            return true;
        }

        synchronized int getSeatsLeft() {
            return seatsLeft;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UnsafeBooking unsafeBooking = new UnsafeBooking();
        int unsafeSuccessfulBookings = runCustomers((customerNumber) -> unsafeBooking.book());
        System.out.println("Without synchronization: " + unsafeSuccessfulBookings
                + " successful bookings, " + unsafeBooking.getSeatsLeft() + " seats left");

        SafeBooking safeBooking = new SafeBooking();
        int safeSuccessfulBookings = runCustomers((customerNumber) -> safeBooking.book());
        System.out.println("With synchronized book(): " + safeSuccessfulBookings
                + " successful bookings, " + safeBooking.getSeatsLeft() + " seats left");
    }

    @FunctionalInterface
    private interface BookingAction {
        boolean book(int customerNumber) throws Exception;
    }

    private static int runCustomers(BookingAction action) throws InterruptedException {
        AtomicInteger successfulBookings = new AtomicInteger();
        Thread[] customers = new Thread[CUSTOMER_COUNT];
        Throwable[] failures = new Throwable[CUSTOMER_COUNT];

        for (int i = 0; i < CUSTOMER_COUNT; i++) {
            final int customerNumber = i;
            customers[i] = new Thread(() -> {
                try {
                    if (action.book(customerNumber)) {
                        successfulBookings.incrementAndGet();
                    }
                } catch (Throwable failure) {
                    failures[customerNumber] = failure;
                }
            }, "customer-" + i);
            customers[i].start();
        }

        for (Thread customer : customers) {
            customer.join();
        }
        for (Throwable failure : failures) {
            if (failure != null) {
                throw new IllegalStateException("A customer failed", failure);
            }
        }
        return successfulBookings.get();
    }
}
