import java.util.Scanner;

public class ShapeDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of shapes: ");
        int numberOfShapes = scanner.nextInt();
        Shape[] shapes = new Shape[numberOfShapes];

        // Build a mixed array of concrete shapes through the abstract type.
        for (int i = 0; i < shapes.length; i++) {
            System.out.print("Enter shape type (circle/rectangle/triangle): ");
            String type = scanner.next().toLowerCase();

            switch (type) {
                case "circle":
                    System.out.print("Enter radius: ");
                    shapes[i] = new Circle(scanner.nextDouble());
                    break;
                case "rectangle":
                    System.out.print("Enter length and width: ");
                    shapes[i] = new Rectangle(scanner.nextDouble(), scanner.nextDouble());
                    break;
                case "triangle":
                    System.out.print("Enter base and height: ");
                    shapes[i] = new Triangle(scanner.nextDouble(), scanner.nextDouble());
                    break;
                default:
                    throw new IllegalArgumentException("Unknown shape type");
            }
        }

        double totalArea = 0;
        double largestArea = 0;

        // One loop handles every shape through polymorphism.
        for (int i = 0; i < shapes.length; i++) {
            double currentArea = shapes[i].area();
            totalArea += currentArea;
            largestArea = Math.max(largestArea, currentArea);
            System.out.printf("Shape %d area: %.2f%n", i + 1, currentArea);
            System.out.printf("Running total: %.2f%n", totalArea);
        }

        System.out.printf("Largest area: %.2f%n", largestArea);
        System.out.printf("Total area: %.2f%n", totalArea);
    }
}
