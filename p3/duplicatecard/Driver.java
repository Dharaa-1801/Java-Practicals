import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfCards;
        do {
            System.out.print("Enter a positive number of cards: ");
            numberOfCards = scanner.nextInt();
        } while (numberOfCards <= 0);

        Card[] cards = new Card[numberOfCards];
        int cardCount = 0;

        // Read cards one at a time and check each against earlier cards.
        for (int i = 0; i < numberOfCards; i++) {
            System.out.print("Enter rank and suit for card " + (i + 1) + ": ");
            Card card = new Card(scanner.next(), scanner.next());
            boolean duplicate = false;

            // Check the new card against cards already stored.
            for (int previous = 0; previous < cardCount; previous++) {
                if (card.equals(cards[previous])) {
                    duplicate = true;
                    break;
                }
            }

            if (duplicate) {
                System.out.println("Duplicate found: " + card);
                break;
            }

            cards[cardCount] = card;
            cardCount++;
        }
    }
}
