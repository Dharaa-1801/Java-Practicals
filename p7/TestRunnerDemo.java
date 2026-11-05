import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {
}

class SampleTests {
    @Run
    public void firstTest() {
        System.out.println("firstTest passed");
    }

    @Run
    public void secondTest() {
        System.out.println("secondTest passed");
    }

    // This method is ignored because it has no @Run annotation.
    public void helperMethod() {
        System.out.println("Helper method");
    }
}

class MiniTestRunner {
    public static int runTests(Object testObject) {
        int runCount = 0;

        // Reflection finds and invokes only methods marked with @Run.
        for (Method method : testObject.getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(Run.class)
                    || method.getParameterCount() != 0) {
                continue;
            }

            try {
                method.setAccessible(true);
                method.invoke(testObject);
                runCount++;
            } catch (IllegalAccessException | InvocationTargetException exception) {
                System.out.println("Test failed: " + method.getName());
            }
        }

        return runCount;
    }
}

public class TestRunnerDemo {
    public static void main(String[] args) {
        SampleTests tests = new SampleTests();
        int runCount = MiniTestRunner.runTests(tests);
        System.out.println("Tests run: " + runCount);
    }
}
