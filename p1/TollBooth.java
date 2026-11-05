import java.util.Scanner;

public class TollBooth {
    // A record stores the vehicle number and vehicle type.
    record Vehicle(String number, String type) {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int total = 0; // Total toll collected
        int bikeCount = 0;
        int carCount = 0;
        int truckCount = 0;

        while (true) {
            System.out.print("Enter vehicle number or done: ");
            String number = scanner.next();

            // The word done ends the input loop.
            if (number.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter vehicle type (bike, car, truck): ");
            String type = scanner.next().toLowerCase();
            Vehicle vehicle = new Vehicle(number, type);

            // Calculate the toll and increase the matching counter.
            int toll = switch (vehicle.type()) {
                case "bike" -> {
                    bikeCount++;
                    yield 20;
                }
                case "car" -> {
                    carCount++;
                    yield 50;
                }
                case "truck" -> {
                    truckCount++;
                    yield 150;
                }
                default -> throw new IllegalArgumentException("Invalid vehicle type");
            };

            total += toll;
        }

        System.out.println("Total toll: " + total);
        // Compare the counters to find the most common vehicle type.
        if (carCount >= bikeCount && carCount >= truckCount) {
            System.out.println("Most frequent: car");
        } else if (bikeCount >= truckCount) {
            System.out.println("Most frequent: bike");
        } else {
            System.out.println("Most frequent: truck");
        }
    }
}