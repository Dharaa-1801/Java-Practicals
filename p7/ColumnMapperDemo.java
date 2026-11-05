import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column {
    String name();
}

class Person {
    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "city")
    private String city;

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", city='" + city + "'}";
    }
}

class RowMapper {
    public static <T> T populate(
            Class<T> type, String[] headers, String[] values) {
        if (headers.length != values.length) {
            throw new IllegalArgumentException(
                    "Header and data row must have the same length");
        }

        Map<String, String> row = new HashMap<>();
        for (int i = 0; i < headers.length; i++) {
            row.put(headers[i], values[i]);
        }

        try {
            T object = type.getDeclaredConstructor().newInstance();

            // Match each annotated field with its column name.
            for (Field field : type.getDeclaredFields()) {
                Column column = field.getAnnotation(Column.class);
                if (column == null) {
                    continue;
                }

                if (!row.containsKey(column.name())) {
                    throw new IllegalArgumentException(
                            "Missing column: " + column.name());
                }

                field.setAccessible(true);
                setField(field, object, row.get(column.name()));
            }

            return object;
        } catch (ReflectiveOperationException exception) {
            throw new IllegalStateException(
                    "Could not create " + type.getSimpleName(), exception);
        }
    }

    private static void setField(Field field, Object object, String value)
            throws IllegalAccessException {
        if (field.getType() == String.class) {
            field.set(object, value);
        } else if (field.getType() == int.class) {
            field.setInt(object, Integer.parseInt(value));
        } else {
            throw new IllegalArgumentException(
                    "Unsupported field type: " + field.getType().getSimpleName());
        }
    }
}

public class ColumnMapperDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter headers separated by commas: ");
        String[] headers = splitRow(scanner.nextLine());
        System.out.print("Enter values separated by commas: ");
        String[] values = splitRow(scanner.nextLine());

        try {
            Person person = RowMapper.populate(Person.class, headers, values);
            System.out.println("Mapped object: " + person);
        } catch (IllegalArgumentException exception) {
            System.out.println("Mapping error: " + exception.getMessage());
        }
    }

    private static String[] splitRow(String row) {
        String[] parts = row.split(",", -1);
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        return parts;
    }
}
