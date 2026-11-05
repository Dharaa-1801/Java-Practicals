package discountengine;

import java.util.Scanner;

@FunctionalInterface
interface DiscountRule {
    double apply(double price);
}

public class DiscountDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of prices: ");
        int numberOfPrices = scanner.nextInt();
        double[] prices = new double[numberOfPrices];

        for (int i = 0; i < prices.length; i++) {
            System.out.print("Enter price " + (i + 1) + ": ");
            prices[i] = scanner.nextDouble();
            if (prices[i] < 0) {
                throw new IllegalArgumentException("Price cannot be negative");
            }
        }

        System.out.println("Choose discount rule:");
        System.out.println("1. No discount");
        System.out.println("2. Ten percent discount");
        System.out.println("3. Flat 100 discount");
        int choice = scanner.nextInt();

        // Each option is a lambda implementing the same functional interface.
        DiscountRule rule;
        switch (choice) {
            case 1:
                rule = price -> price;
                break;
            case 2:
                rule = price -> price * 0.90;
                break;
            case 3:
                rule = price -> Math.max(0, price - 100);
                break;
            default:
                throw new IllegalArgumentException("Invalid discount choice");
        }

        double total = 0;
        for (double price : prices) {
            double finalPrice = rule.apply(price);
            total += finalPrice;
            System.out.printf("Original: %.2f, Final: %.2f%n", price, finalPrice);
        }

        System.out.printf("Total after discount: %.2f%n", total);
    }
}
