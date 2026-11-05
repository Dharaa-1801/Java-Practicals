import java.util.Scanner;

public class VendingMachine {
    // These are the coin names accepted by the machine.
    enum Coin {
        ONE, TWO, FIVE, TEN
    }

    public static void main(String[] args) {
        final int price = 15; // Price of the snack
        int total = 0;
        Scanner scanner = new Scanner(System.in);

        // Keep accepting coins until enough money is inserted.
        while (total < price) {
            System.out.print("Enter coin (ONE, TWO, FIVE, TEN): ");
            Coin coin = Coin.valueOf(scanner.next().toUpperCase());

            // Convert the selected coin into its numeric value.
            int value = switch (coin) {
                case ONE -> 1;
                case TWO -> 2;
                case FIVE -> 5;
                case TEN -> 10;
            };

            total += value;
            System.out.println("Total so far: " + total);
        }

        // If the user paid extra, return the remaining amount as change.
        System.out.println("Paid. Change: " + (total - price));
    }
}