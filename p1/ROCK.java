import java.util.Random;
import java.util.Scanner;

public class ROCK {
    // The five possible moves in the game.
    enum Move {
        ROCK, PAPER, SCISSORS, LIZARD, SPOCK
    }

    // Return 1 when the first move wins, -1 when the second wins,
    // and 0 when both moves are the same.
    static int winner(Move a, Move b) {
        if (a == b) {
            return 0;
        }

        // Each move defeats exactly two other moves.
        return switch (a) {
            case ROCK -> (b == Move.SCISSORS || b == Move.LIZARD) ? 1 : -1;
            case PAPER -> (b == Move.ROCK || b == Move.SPOCK) ? 1 : -1;
            case SCISSORS -> (b == Move.PAPER || b == Move.LIZARD) ? 1 : -1;
            case LIZARD -> (b == Move.PAPER || b == Move.SPOCK) ? 1 : -1;
            case SPOCK -> (b == Move.SCISSORS || b == Move.ROCK) ? 1 : -1;
        };
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int playerScore = 0;
        int computerScore = 0;

        // The game has five rounds.
        for (int round = 1; round <= 5; round++) {
            // Choose a random move for the computer.
            Move computerMove = Move.values()[random.nextInt(Move.values().length)];

            System.out.print("Enter your move (ROCK, PAPER, SCISSORS, LIZARD, SPOCK): ");
            Move playerMove = Move.valueOf(scanner.next().toUpperCase());

            int result = winner(playerMove, computerMove);

            System.out.println("Round " + round);
            System.out.println("You: " + playerMove);
            System.out.println("Computer: " + computerMove);

            if (result == 1) {
                playerScore++;
                System.out.println("Round winner: You");
            } else if (result == -1) {
                computerScore++;
                System.out.println("Round winner: Computer");
            } else {
                System.out.println("Round winner: Tie");
            }
        }

        // Display the final result after all five rounds.
        if (playerScore > computerScore) {
            System.out.println("You win " + playerScore + "-" + computerScore);
        } else if (computerScore > playerScore) {
            System.out.println("Computer wins " + computerScore + "-" + playerScore);
        } else {
            System.out.println("Draw " + playerScore + "-" + computerScore);
        }
    }
}