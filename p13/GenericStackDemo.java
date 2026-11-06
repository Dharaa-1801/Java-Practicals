import java.util.EmptyStackException;
import java.util.Scanner;

// Implements an array-backed generic stack and reuses it with two types.
 
public class GenericStackDemo {
    private static final class Stack<T> {
        private final Object[] items;
        private int size;

        Stack(int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException("Capacity must be positive");
            }
            items = new Object[capacity];
        }

        void push(T item) {
            if (size == items.length) {
                throw new IllegalStateException("Stack is full");
            }
            items[size++] = item;
        }

        @SuppressWarnings("unchecked")
        T pop() {
            if (size == 0) {
                throw new EmptyStackException();
            }
            T item = (T) items[--size];
            items[size] = null;
            return item;
        }

        boolean isEmpty() {
            return size == 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter stack capacity: ");
        int capacity = Integer.parseInt(scanner.nextLine());

        Stack<Integer> numbers = new Stack<>(capacity);
        Stack<String> words = new Stack<>(capacity);

        int numberItems = Math.min(capacity, 2);
        System.out.println("Enter " + numberItems + " integers:");
        for (int i = 0; i < numberItems; i++) {
            numbers.push(Integer.parseInt(scanner.nextLine()));
        }

        int wordItems = Math.min(capacity, 2);
        System.out.println("Enter " + wordItems + " words:");
        for (int i = 0; i < wordItems; i++) {
            words.push(scanner.nextLine());
        }

        System.out.println("Numbers popped in LIFO order:");
        while (!numbers.isEmpty()) {
            System.out.println(numbers.pop());
        }
        System.out.println("Words popped in LIFO order:");
        while (!words.isEmpty()) {
            System.out.println(words.pop());
        }

        try {
            words.pop();
        } catch (EmptyStackException exception) {
            System.out.println("Empty stack handled: no item to pop.");
        }
    }
}
