import java.util.List;
import java.util.Scanner;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {
}

class FormValidator {
    private FormValidator() {
        // Utility class; objects are not required.
    }

    public static List<String> validate(Object object) {
        List<String> errors = new ArrayList<>();

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            try {
                Object value = field.get(object);
                String text = value == null ? null : value.toString();

                if (field.isAnnotationPresent(NotBlank.class)
                        && (text == null || text.trim().isEmpty())) {
                    errors.add(field.getName() + " must not be blank");
                }

                MaxLength maxLength = field.getAnnotation(MaxLength.class);
                if (maxLength != null && text != null
                        && text.length() > maxLength.value()) {
                    errors.add(field.getName() + " must be at most "
                            + maxLength.value() + " characters");
                }
            } catch (IllegalAccessException exception) {
                throw new IllegalStateException(
                        "Could not read field: " + field.getName(), exception);
            }
        }

        return errors;
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

class SignupForm {
    @NotBlank
    @MaxLength(30)
    private String username;

    @NotBlank
    @MaxLength(50)
    private String email;

    @NotBlank
    @MaxLength(20)
    private String password;

    public SignupForm(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

public class FormValidationDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        SignupForm form = new SignupForm(username, email, password);
        List<String> errors = FormValidator.validate(form);

        if (errors.isEmpty()) {
            System.out.println("Form is valid");
        } else {
            System.out.println("Validation errors:");
            for (String error : errors) {
                System.out.println("- " + error);
            }
        }
    }
}
