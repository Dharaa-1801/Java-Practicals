import java.util.InputMismatchException;
import java.util.Scanner;

class DivideByZeroException extends Exception {
    public DivideByZeroException(String message) {
        super(message);
    }
}

public class GuardedCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean calculationSucceeded = false;
        int attempt = 0;

        while (!calculationSucceeded) {
            attempt++;

            try {
                System.out.print("Enter first number: ");
                double first = readNumber(scanner);
                System.out.print("Enter operator (+, -, *, /): ");
                String operator = scanner.next();
                System.out.print("Enter second number: ");
                double second = readNumber(scanner);

                double result = calculate(first, operator, second);
                System.out.println("Result: " + result);
                calculationSucceeded = true;
            } catch (NumberFormatException | InputMismatchException exception) {
                System.out.println("Invalid number. Please enter a numeric value.");
                if (exception instanceof InputMismatchException) {
                    scanner.nextLine();
                }
            } catch (DivideByZeroException exception) {
                System.out.println("Calculation error: " + exception.getMessage());
            } catch (IllegalArgumentException exception) {
                System.out.println("Calculation error: " + exception.getMessage());
            } finally {
                System.out.println("Attempt " + attempt + " logged.");
            }
        }
    }

    private static double readNumber(Scanner scanner) {
        if (!scanner.hasNextDouble()) {
            String invalidValue = scanner.next();
            throw new NumberFormatException(invalidValue);
        }
        return scanner.nextDouble();
    }

    private static double calculate(double first, String operator, double second)
            throws DivideByZeroException {
        switch (operator) {
            case "+":
                return first + second;
            case "-":
                return first - second;
            case "*":
                return first * second;
            case "/":
                if (second == 0) {
                    throw new DivideByZeroException(
                            "Division by zero is not allowed.");
                }
                return first / second;
            default:
                throw new IllegalArgumentException("Invalid operator.");
        }
    }
}
