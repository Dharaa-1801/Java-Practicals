import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfPoints;
        do {
            System.out.print("Enter a positive number of points: ");
            numberOfPoints = scanner.nextInt();
        } while (numberOfPoints <= 0);

        Point[] points = new Point[numberOfPoints];

        // Read every point from the user.
        for (int i = 0; i < points.length; i++) {
            System.out.print("Enter x and y for point " + (i + 1) + ": ");
            points[i] = new Point(scanner.nextInt(), scanner.nextInt());
        }

        int distinctCount = 0;

        // Count a point only when it has not appeared earlier.
        for (int current = 0; current < points.length; current++) {
            boolean appearedEarlier = false;

            // Compare this point with every point before it.
            for (int previous = 0; previous < current; previous++) {
                if (points[current].equals(points[previous])) {
                    appearedEarlier = true;
                    break;
                }
            }

            if (!appearedEarlier) {
                distinctCount++;
            }
        }

        System.out.println("Distinct: " + distinctCount);
    }
}
