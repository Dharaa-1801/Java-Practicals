import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

// Counts distinct visitors and preserves the order of their first login.
 
public class UniqueVisitors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of login names: ");
        int loginCount = Integer.parseInt(scanner.nextLine());
        if (loginCount < 0) {
            throw new IllegalArgumentException("Login count cannot be negative");
        }

        String[] loginNames = new String[loginCount];
        for (int i = 0; i < loginCount; i++) {
            System.out.print("Enter login name " + (i + 1) + ": ");
            loginNames[i] = scanner.nextLine().trim();
            if (loginNames[i].isEmpty()) {
                throw new IllegalArgumentException("Login name cannot be empty");
            }
        }

        List<String> logins = Arrays.asList(loginNames);
        Set<String> uniqueVisitors = new HashSet<>(logins);
        Set<String> firstSeenOrder = new LinkedHashSet<>(logins);

        System.out.println("Unique visitor count: " + uniqueVisitors.size());
        System.out.println("First three distinct names: "
                + firstSeenOrder.stream().limit(3).toList());
    }
}
