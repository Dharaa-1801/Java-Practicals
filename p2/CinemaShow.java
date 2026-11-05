public class CinemaShow {
    // Details that belong to one cinema show.
    private String title;
    private int seatsAvailable;
    private final int capacity;

    // Counts all seats successfully booked by all CinemaShow objects.
    private static int totalBooked = 0;

    public CinemaShow(String title, int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        this.title = title;
        this.capacity = capacity;
        seatsAvailable = capacity;
    }

    // Default capacity for a show.
    public CinemaShow(String title) {
        this(title, 100);
    }

    // A booking is accepted only when all requested seats are available.
    public boolean book(int n) {
        if (n < 0 || n > seatsAvailable) {
            return false;
        }
        seatsAvailable -= n;
        totalBooked += n;
        return true;
    }

    // Cancelling seats cannot make availability exceed the original capacity.
    public void cancel(int n) {
        if (n > 0) {
            seatsAvailable = Math.min(capacity, seatsAvailable + n);
        }
    }

    // A failed booking does not change either value.
    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public static int getTotalBooked() {
        return totalBooked;
    }

    public static void main(String[] args) {
        CinemaShow show = new CinemaShow("Java: The Movie", 5);

        // The second booking fails because only two seats remain.
        System.out.println("Book 3: " + show.book(3)
                + ", seats: " + show.getSeatsAvailable());
        System.out.println("Book 3: " + show.book(3)
                + ", seats: " + show.getSeatsAvailable());

        // One cancelled seat becomes available again.
        show.cancel(1);
        System.out.println("Cancel 1, seats: " + show.getSeatsAvailable());
        System.out.println("Book 3: " + show.book(3)
                + ", seats: " + show.getSeatsAvailable());
        System.out.println("Book 1: " + show.book(1)
                + ", seats: " + show.getSeatsAvailable());
        System.out.println("Total booked: " + getTotalBooked());
    }
}
