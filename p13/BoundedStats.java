import java.util.Scanner;

//Calculates averages for arrays whose element type extends Number.

public class BoundedStats {
    private static <N extends Number> double average(N[] values) {
        if (values.length == 0) {
            throw new IllegalArgumentException("At least one number is required");
        }

        double total = 0;
        for (N value : values) {
            total += value.doubleValue();
        }
        return total / values.length;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of values in each array: ");
        int count = Integer.parseInt(scanner.nextLine());
        if (count <= 0) {
            throw new IllegalArgumentException("Count must be positive");
        }

        Integer[] integers = new Integer[count];
        Double[] doubles = new Double[count];
        Long[] longs = new Long[count];

        System.out.println("Enter " + count + " integer values:");
        for (int i = 0; i < count; i++) {
            integers[i] = Integer.parseInt(scanner.nextLine());
        }
        System.out.println("Enter " + count + " decimal values:");
        for (int i = 0; i < count; i++) {
            doubles[i] = Double.parseDouble(scanner.nextLine());
        }
        System.out.println("Enter " + count + " long values:");
        for (int i = 0; i < count; i++) {
            longs[i] = Long.parseLong(scanner.nextLine());
        }

        System.out.println("Integer average: " + average(integers));
        System.out.println("Double average: " + average(doubles));
        System.out.println("Long average: " + average(longs));
        System.out.println("String[] is rejected because String does not extend Number.");
    }
}
