import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] logLines = {
            "10:05 alice Hello there",
            "10:06 bob Good morning",
            "Malformed log line",
            "10:07 charlie See you later"
        };

        System.out.print("Enter keyword: ");
        String keyword = scanner.nextLine();

        System.out.print(ChatFilter.filter(logLines, keyword));
    }
}
