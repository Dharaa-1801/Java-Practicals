import java.util.Scanner;

// Demonstrates generic containers and a generic first-element method.

public class GenericContainers {
    private static final class Box<T> {
        private T value;

        Box(T value) {
            this.value = value;
        }

        T get() {
            return value;
        }

        void set(T value) {
            this.value = value;
        }
    }

    private static final class Pair<A, B> {
        private final A first;
        private final B second;

        Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        A getFirst() {
            return first;
        }

        B getSecond() {
            return second;
        }
    }

    private static <T> T firstElement(T[] values) {
        if (values.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty");
        }
        return values[0];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int integerValue = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter a text value: ");
        String textValue = scanner.nextLine();

        Box<Integer> integerBox = new Box<>(integerValue);
        Box<String> textBox = new Box<>(textValue);
        Pair<Integer, String> pair = new Pair<>(integerBox.get(), textBox.get());

        System.out.print("Enter a replacement integer: ");
        integerBox.set(Integer.parseInt(scanner.nextLine()));

        Integer[] numbers = {integerBox.get(), 20, 30};
        String[] words = {textBox.get(), "generic", "types"};

        System.out.println("Integer box: " + integerBox.get());
        System.out.println("Text box: " + textBox.get());
        System.out.println("Pair: (" + pair.getFirst() + ", " + pair.getSecond() + ")");
        System.out.println("First integer: " + firstElement(numbers));
        System.out.println("First text: " + firstElement(words));
        System.out.println("No casts were needed; each type is checked at compile time.");
    }
}
