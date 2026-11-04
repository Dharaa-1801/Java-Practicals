
import java.util.*;

class Student {
    private String studentId;
    private String name;
    private String email;

    public Student(String studentId, String name, String email) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void display() {
        System.out.println(studentId + " | " + name + " | " + email);
    }
}

class CollegeEvent {
    private int eventId;
    private String eventName;
    private String date;
    private String venue;
    private int capacity;

    // Stores unique student IDs registered for this event.
    private Set<String> registeredStudents = new HashSet<>();

    public CollegeEvent(int eventId, String eventName, String date,
                        String venue, int capacity) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.date = date;
        this.venue = venue;
        this.capacity = capacity;
    }

    public int getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableSeats() {
        return capacity - registeredStudents.size();
    }

    public boolean isRegistered(String studentId) {
        return registeredStudents.contains(studentId);
    }

    public boolean registerStudent(String studentId) {
        if (isRegistered(studentId)) {
            return false;
        }

        if (getAvailableSeats() <= 0) {
            return false;
        }

        registeredStudents.add(studentId);
        return true;
    }

    public void display() {
        System.out.println(
            "Event ID: " + eventId +
            " | Name: " + eventName +
            " | Date: " + date +
            " | Venue: " + venue +
            " | Capacity: " + capacity +
            " | Available Seats: " + getAvailableSeats()
        );
    }
}

class NotificationService {
    public void sendNotification(Student student, String message) {
        System.out.println("\n========== NOTIFICATION ==========");
        System.out.println("To: " + student.getName()
                           + " (" + student.getEmail() + ")");
        System.out.println("Message: " + message);
        System.out.println("==================================");
    }
}

public class CollegeEventManagement {

    private static Scanner sc = new Scanner(System.in);

    private static Map<String, Student> students = new LinkedHashMap<>();
    private static Map<Integer, CollegeEvent> events = new LinkedHashMap<>();

    private static NotificationService notificationService =
            new NotificationService();

    private static void addStudent() {
        System.out.print("Enter student ID: ");
        String id = sc.nextLine().trim();

        if (students.containsKey(id)) {
            System.out.println("Student ID already exists!");
            return;
        }

        System.out.print("Enter student name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter email: ");
        String email = sc.nextLine().trim();

        if (id.isEmpty() || name.isEmpty() || email.isEmpty()) {
            System.out.println("All fields are required.");
            return;
        }

        for (Student s : students.values()) {
        if (s.getEmail().equalsIgnoreCase(email)) {
            System.out.println(
                "This email is already registered to another student!"
            );
            return;
        }
    }

        students.put(id, new Student(id, name, email));
        System.out.println("Student added successfully!");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\nID | Name | Email");
        for (Student student : students.values()) {
            student.display();
        }
    }

    private static void addEvent() {
        System.out.print("Enter event ID: ");
        int id = readInt();

        if (events.containsKey(id)) {
            System.out.println("Event ID already exists!");
            return;
        }

        System.out.print("Enter event name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter event date: ");
        String date = sc.nextLine().trim();

        System.out.print("Enter venue: ");
        String venue = sc.nextLine().trim();

        System.out.print("Enter maximum capacity: ");
        int capacity = readInt();

        if (name.isEmpty() || date.isEmpty() || venue.isEmpty()
                || capacity <= 0) {
            System.out.println(
                "Invalid event details. Capacity must be positive."
            );
            return;
        }

        events.put(id, new CollegeEvent(
            id, name, date, venue, capacity
        ));

        System.out.println("Event created successfully!");
    }

    private static void viewEvents() {
        if (events.isEmpty()) {
            System.out.println("No events found.");
            return;
        }

        for (CollegeEvent event : events.values()) {
            event.display();
        }
    }

    private static void registerStudent() {
        System.out.print("Enter student ID: ");
        String studentId = sc.nextLine().trim();

        Student student = students.get(studentId);

        if (student == null) {
            System.out.println("Student not found. Add the student first.");
            return;
        }

        System.out.print("Enter event ID: ");
        int eventId = readInt();

        CollegeEvent event = events.get(eventId);

        if (event == null) {
            notificationService.sendNotification(
                student, "Registration failed: event not found."
            );
            return;
        }

        if (event.isRegistered(studentId)) {
            notificationService.sendNotification(
                student, "You are already registered for "
                         + event.getEventName() + "."
            );
            return;
        }

        if (event.getAvailableSeats() <= 0) {
            notificationService.sendNotification(
                student, "Registration failed: "
                         + event.getEventName() + " is full."
            );
            return;
        }

        if (event.registerStudent(studentId)) {
            notificationService.sendNotification(
                student, "Registration confirmed for "
                         + event.getEventName()
                         + ". Event ID: " + eventId
                         + ". Your seat is reserved."
            );
        }
    }

    private static int readInt() {
        while (true) {
            try {
                int value = Integer.parseInt(sc.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid integer: ");
            }
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== COLLEGE EVENT MANAGEMENT =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Create Event");
            System.out.println("4. View Events");
            System.out.println("5. Register Student for Event");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = readInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    addEvent();
                    break;
                case 4:
                    viewEvents();
                    break;
                case 5:
                    registerStudent();
                    break;
                case 6:
                    System.out.println("Thank you for using the system!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
