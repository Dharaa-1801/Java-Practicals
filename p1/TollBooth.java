import java.util.Scanner;

public class TollBooth {
    record Vehicle(String number, String type) {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int total = 0;
        int bikeCount = 0;
        int carCount = 0;
        int truckCount = 0;

        while (true) {
            String number = scanner.next();

            if (number.equalsIgnoreCase("done")) {
                break;
            }

            String type = scanner.next().toLowerCase();
            Vehicle vehicle = new Vehicle(number, type);

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

        if (carCount >= bikeCount && carCount >= truckCount) {
            System.out.println("Most frequent: car");
        } else if (bikeCount >= truckCount) {
            System.out.println("Most frequent: bike");
        } else {
            System.out.println("Most frequent: truck");
        }
    }
}