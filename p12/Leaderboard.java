import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Prints the requested top players. Higher scores rank first; ties use the
// player's name in alphabetical order.
 
public class Leaderboard {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of players: ");
        int playerCount = Integer.parseInt(scanner.nextLine());
        if (playerCount < 0) {
            throw new IllegalArgumentException("Player count cannot be negative");
        }

        Map<String, Integer> scores = new HashMap<>();
        for (int i = 0; i < playerCount; i++) {
            System.out.print("Enter player name " + (i + 1) + ": ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty() || scores.containsKey(name)) {
                throw new IllegalArgumentException("Player names must be non-empty and unique");
            }
            System.out.print("Enter score for " + name + ": ");
            int score = Integer.parseInt(scanner.nextLine());
            scores.put(name, score);
        }

        System.out.print("Enter top-N value: ");
        int topN = Integer.parseInt(scanner.nextLine());
        if (topN < 0) {
            throw new IllegalArgumentException("Top-N cannot be negative");
        }

        List<Map.Entry<String, Integer>> ranking = new ArrayList<>(scores.entrySet());
        ranking.sort(Comparator
                .comparing(Map.Entry<String, Integer>::getValue)
                .reversed()
                .thenComparing(Map.Entry::getKey));

        System.out.println("Leaderboard:");
        int rank = 1;
        for (Map.Entry<String, Integer> entry : ranking.stream().limit(topN).toList()) {
            System.out.println(rank++ + ". " + entry.getKey() + " - " + entry.getValue());
        }
    }
}
