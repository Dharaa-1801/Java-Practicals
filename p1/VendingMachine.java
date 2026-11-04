import java.util.Scanner;

public class VendingMachine {
    enum Coin {
        ONE, TWO, FIVE, TEN
    }

    public static void main(String[] args) {
        final int price = 15;
        int total = 0;
        Scanner scanner = new Scanner(System.in);

        while (total < price) {
            Coin coin = Coin.valueOf(scanner.next().toUpperCase());

            int value = switch (coin) {
                case ONE -> 1;
                case TWO -> 2;
                case FIVE -> 5;
                case TEN -> 10;
            };

            total += value;
            System.out.println("Total so far: " + total);
        }

        System.out.println("Paid. Change: " + (total - price));
    }
}