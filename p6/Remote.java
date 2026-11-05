import java.util.Scanner;

interface Switchable {
    void on();

    void off();

    boolean isOn();

    // The default method changes the current state.
    default void toggle() {
        if (isOn()) {
            off();
        } else {
            on();
        }
    }
}

@FunctionalInterface
interface SwitchOnRule {
    boolean maySwitchOn(Switchable device, int hour);
}

class Fan implements Switchable {
    private boolean on;

    @Override
    public void on() {
        on = true;
        System.out.println("Fan is on");
    }

    @Override
    public void off() {
        on = false;
        System.out.println("Fan is off");
    }

    @Override
    public boolean isOn() {
        return on;
    }
}

class Light implements Switchable {
    private boolean on;

    @Override
    public void on() {
        on = true;
        System.out.println("Light is on");
    }

    @Override
    public void off() {
        on = false;
        System.out.println("Light is off");
    }

    @Override
    public boolean isOn() {
        return on;
    }
}

public class Remote {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter hour (0-23): ");
        int hour = scanner.nextInt();

        Switchable[] devices = {new Fan(), new Light()};

        // The same loop toggles every device through the interface.
        for (Switchable device : devices) {
            device.toggle();
        }

        // Supply the rule once as an anonymous class.
        SwitchOnRule daytimeRule = new SwitchOnRule() {
            @Override
            public boolean maySwitchOn(Switchable device, int currentHour) {
                return currentHour >= 6 && currentHour <= 22;
            }
        };

        // Supply the same rule as a lambda.
        SwitchOnRule quietHoursRule =
                (device, currentHour) -> currentHour >= 7 && currentHour <= 21;

        System.out.println("Anonymous rule allows fan: "
                + daytimeRule.maySwitchOn(devices[0], hour));
        System.out.println("Lambda rule allows light: "
                + quietHoursRule.maySwitchOn(devices[1], hour));
    }
}
