import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Counts words in a paragraph and prints them from most frequent to least
// frequent. Words with equal frequency are printed alphabetically.
public class WordFrequency {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a paragraph: ");
        String paragraph = scanner.nextLine().toLowerCase();

        Map<String, Integer> frequencies = new HashMap<>();
        for (String word : paragraph.split("[^a-z0-9]+")) {
            if (!word.isEmpty()) {
                frequencies.merge(word, 1, Integer::sum);
            }
        }

        List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(
                frequencies.entrySet());
        sortedWords.sort(Comparator
                .comparing(Map.Entry<String, Integer>::getValue)
                .reversed()
                .thenComparing(Map.Entry::getKey));

        System.out.println("Words sorted by frequency:");
        for (Map.Entry<String, Integer> entry : sortedWords) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
