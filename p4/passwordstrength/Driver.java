import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of passwords: ");
        int numberOfPasswords = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numberOfPasswords; i++) {
            System.out.print("Enter password " + (i + 1) + ": ");
            String password = scanner.nextLine();

            System.out.println("Length >= 8: "
                    + PasswordChecker.hasMinimumLength(password));
            System.out.println("Contains uppercase: "
                    + PasswordChecker.hasUppercase(password));
            System.out.println("Contains digit: "
                    + PasswordChecker.hasDigit(password));
            System.out.println("Contains special character: "
                    + PasswordChecker.hasSpecialCharacter(password));
            System.out.println("Strength: " + PasswordChecker.strength(password));
            System.out.println();
        }
    }
}
