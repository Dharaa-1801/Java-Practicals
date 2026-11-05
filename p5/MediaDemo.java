import java.util.Scanner;

class Book extends Media {
    public Book(String title, int lateDays) {
        super(title, lateDays);
    }

    @Override
    public double lateFee() {
        return getLateDays() * 2.0;
    }
}

class Game extends Media {
    public Game(String title, int lateDays) {
        super(title, lateDays);
    }

    @Override
    public double lateFee() {
        return getLateDays() * 8.0;
    }
}

abstract class Media {
    private String title;
    private int lateDays;

    public Media(String title, int lateDays) {
        if (lateDays < 0) {
            throw new IllegalArgumentException("Late days cannot be negative");
        }
        this.title = title;
        this.lateDays = lateDays;
    }

    public String getTitle() {
        return title;
    }

    public int getLateDays() {
        return lateDays;
    }

    // Every media type defines its own late-fee rule.
    public abstract double lateFee();
}

class Movie extends Media {
    public Movie(String title, int lateDays) {
        super(title, lateDays);
    }

    @Override
    public double lateFee() {
        return getLateDays() * 5.0;
    }
}

public class MediaDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of returned media items: ");
        int numberOfItems = scanner.nextInt();
        Media[] returnedItems = new Media[numberOfItems];

        // Store different media types through the common abstract type.
        for (int i = 0; i < returnedItems.length; i++) {
            System.out.print("Enter media type (movie/book/game): ");
            String type = scanner.next().toLowerCase();
            System.out.print("Enter title and late days: ");
            String title = scanner.next();
            int lateDays = scanner.nextInt();

            switch (type) {
                case "movie":
                    returnedItems[i] = new Movie(title, lateDays);
                    break;
                case "book":
                    returnedItems[i] = new Book(title, lateDays);
                    break;
                case "game":
                    returnedItems[i] = new Game(title, lateDays);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown media type");
            }
        }

        double totalFees = 0;
        for (Media item : returnedItems) {
            double fee = item.lateFee();
            totalFees += fee;
            System.out.printf("%s late fee: %.2f%n", item.getTitle(), fee);
        }

        System.out.printf("Total late fees: %.2f%n", totalFees);
    }
}
