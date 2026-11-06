import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;


 //Saves and loads an array of serializable objects.
 
public class SaveLoadDemo {
    private static final Path DATA_FILE = Path.of("p11", "people.dat");

    private static final class Person implements Serializable {
        private static final long serialVersionUID = 1L;

        private final String name;
        private final int age;
        private transient String sessionToken;

        Person(String name, int age, String sessionToken) {
            this.name = name;
            this.age = age;
            this.sessionToken = sessionToken;
        }

        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age
                    + ", sessionToken=" + sessionToken + "}";
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of people: ");
        int peopleCount = Integer.parseInt(scanner.nextLine());
        if (peopleCount <= 0) {
            throw new IllegalArgumentException("Number of people must be positive");
        }

        Person[] originalPeople = new Person[peopleCount];
        for (int i = 0; i < peopleCount; i++) {
            System.out.print("Enter name for person " + (i + 1) + ": ");
            String name = scanner.nextLine();
            System.out.print("Enter age for " + name + ": ");
            int age = Integer.parseInt(scanner.nextLine());
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative");
            }
            System.out.print("Enter session token for " + name + ": ");
            String sessionToken = scanner.nextLine();
            originalPeople[i] = new Person(name, age, sessionToken);
        }

        Files.createDirectories(DATA_FILE.getParent());
        try (ObjectOutputStream output = new ObjectOutputStream(
                Files.newOutputStream(DATA_FILE, StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING))) {
            output.writeObject(originalPeople);
        }

        Person[] loadedPeople;
        try (ObjectInputStream input = new ObjectInputStream(Files.newInputStream(DATA_FILE))) {
            loadedPeople = (Person[]) input.readObject();
        }

        System.out.println("Saved objects:  " + Arrays.toString(originalPeople));
        System.out.println("Loaded objects: " + Arrays.toString(loadedPeople));
        System.out.println("Values survived: " + samePersistentValues(originalPeople, loadedPeople));
        System.out.println("Transient field after loading: " + loadedPeople[0].sessionToken);
    }

    private static boolean samePersistentValues(Person[] original, Person[] loaded) {
        if (original.length != loaded.length) {
            return false;
        }
        for (int i = 0; i < original.length; i++) {
            if (!original[i].name.equals(loaded[i].name) || original[i].age != loaded[i].age) {
                return false;
            }
        }
        return true;
    }
}
