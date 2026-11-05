public class Thermostat {
    // State and limits for one thermostat.
    private String location;
    private int temperature;
    private static final int MIN = 16;
    private static final int MAX = 30;
    private static int activeCount = 0;

    public Thermostat(String location, int startTemp) {
        this.location = location;
        // An invalid starting temperature uses the required default value.
        temperature = startTemp >= MIN && startTemp <= MAX ? startTemp : 22;
        activeCount++;
    }

    // Constructor chaining avoids repeating the default temperature.
    public Thermostat(String location) {
        this(location, 22);
    }

    // Increase the temperature only while it is below the upper limit.
    public void raise() {
        if (temperature < MAX) {
            temperature++;
        } else {
            System.out.println("Already at maximum (30)");
        }
    }

    // Decrease the temperature only while it is above the lower limit.
    public void lower() {
        if (temperature > MIN) {
            temperature--;
        } else {
            System.out.println("Already at minimum (16)");
        }
    }

    // There is intentionally no setter, so callers cannot bypass the limits.
    public int getTemperature() {
        return temperature;
    }

    // This value belongs to the class, so it is accessed through the class.
    public static int getActiveCount() {
        return activeCount;
    }

    public static void main(String[] args) {
        Thermostat livingRoom = new Thermostat("Living Room", 22);
        Thermostat bedroom = new Thermostat("Bedroom");

        // Ten raises reach the maximum and exercise the maximum check.
        for (int i = 0; i < 10; i++) {
            livingRoom.raise();
            System.out.println(livingRoom.location + " temperature: "
                    + livingRoom.getTemperature());
        }

        // Twenty lowers reach the minimum and exercise the minimum check.
        for (int i = 0; i < 20; i++) {
            bedroom.lower();
            System.out.println(bedroom.location + " temperature: "
                    + bedroom.getTemperature());
        }

        System.out.println("Active thermostats(count): " + getActiveCount());
    }
}
