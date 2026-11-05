import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class OutOfStockException extends Exception {
    private final int shortfall;

    public OutOfStockException(String message, int shortfall) {
        super(message);
        this.shortfall = shortfall;
    }

    public int getShortfall() {
        return shortfall;
    }
}

class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String message) {
        super(message);
    }
}

class Warehouse {
    private final String[] items = {"apple", "orange", "watermelon"};
    private final int[] quantities = {10, 7, 8};

    public void issue(String item, int quantity)
            throws InvalidQuantityException, OutOfStockException {
        if (quantity <= 0) {
            throw new InvalidQuantityException(
                    "Quantity must be greater than zero.");
        }

        int index = findItem(item);
        if (index == -1) {
            throw new IllegalArgumentException("Item not found: " + item);
        }

        if (quantities[index] < quantity) {
            int shortfall = quantity - quantities[index];
            throw new OutOfStockException(
                    "Not enough stock for " + item + ".", shortfall);
        }

        quantities[index] -= quantity;
        System.out.println("Issued " + quantity + " " + item
                + "(s) successfully.");
    }

    private int findItem(String item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].equalsIgnoreCase(item)) {
                return i;
            }
        }
        return -1;
    }
}

public class WarehouseDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Warehouse warehouse = new Warehouse();
        List<String> requests = new ArrayList<>();

        System.out.print("Enter number of requests: ");
        int numberOfRequests = scanner.nextInt();

        for (int i = 0; i < numberOfRequests; i++) {
            System.out.print("Enter item and quantity: ");
            String item = scanner.next();
            int quantity = scanner.nextInt();
            requests.add(item + " " + quantity);
        }

        for (String request : requests) {
            String[] parts = request.split(" ");
            String item = parts[0];
            int quantity = Integer.parseInt(parts[1]);

            try {
                warehouse.issue(item, quantity);
            } catch (InvalidQuantityException exception) {
                System.out.println("Invalid request: " + exception.getMessage());
            } catch (OutOfStockException exception) {
                System.out.println("Stock error: " + exception.getMessage()
                        + " Shortfall: " + exception.getShortfall());
            } catch (IllegalArgumentException exception) {
                System.out.println("Request error: " + exception.getMessage());
            }
        }
    }
}
