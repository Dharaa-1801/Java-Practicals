import java.util.Objects;

// Represents a playing card identified by its rank and suit.
public class Card {
    private String rank;
    private String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    // Display the card in a readable form.
    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Card)) {
            return false;
        }

        Card other = (Card) object;
        return Objects.equals(rank, other.rank)
                && Objects.equals(suit, other.suit);
    }

    // Equal cards must have the same hash code.
    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }
}
