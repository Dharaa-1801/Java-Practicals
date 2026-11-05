import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfFractions;
        do {
            System.out.print("Enter a positive number of fractions: ");
            numberOfFractions = scanner.nextInt();
        } while (numberOfFractions <= 0);

        Fraction[] fractions = new Fraction[numberOfFractions];

        // Read and reduce each fraction through the Fraction constructor.
        for (int i = 0; i < fractions.length; i++) {
            int numerator;
            int denominator;

            do {
                System.out.print("Enter numerator and non-zero denominator for fraction "
                        + (i + 1) + ": ");
                numerator = scanner.nextInt();
                denominator = scanner.nextInt();
                if (denominator == 0) {
                    System.out.println("Denominator cannot be zero.");
                }
            } while (denominator == 0);

            fractions[i] = new Fraction(numerator, denominator);
        }

        System.out.println("Reduced fractions:");
        for (Fraction fraction : fractions) {
            System.out.println(fraction);
        }

        // Compare each fraction with the first one entered.
        for (int i = 1; i < fractions.length; i++) {
            System.out.println(fractions[i] + " equals " + fractions[0]
                    + ": " + fractions[i].equals(fractions[0]));
        }
    }
}
