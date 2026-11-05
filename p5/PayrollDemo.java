import java.util.Scanner;

abstract class Employee {
    private String name;
    private String id;

    public Employee(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    // Each employee type calculates monthly salary differently.
    public abstract double monthlySalary();
}


class FullTime extends Employee {
    private double monthlyPay;

    public FullTime(String name, String id, double monthlyPay) {
        super(name, id);
        if (monthlyPay < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        this.monthlyPay = monthlyPay;
    }

    @Override
    public double monthlySalary() {
        return monthlyPay;
    }
}

class Intern extends Employee {
    private double stipend;

    public Intern(String name, String id, double stipend) {
        super(name, id);
        if (stipend < 0) {
            throw new IllegalArgumentException("Stipend cannot be negative");
        }
        this.stipend = stipend;
    }

    @Override
    public double monthlySalary() {
        return stipend;
    }
}

class PartTime extends Employee {
    private double hours;
    private double hourlyRate;

    public PartTime(String name, String id, double hours, double hourlyRate) {
        super(name, id);
        if (hours < 0 || hourlyRate < 0) {
            throw new IllegalArgumentException("Hours and rate cannot be negative");
        }
        this.hours = hours;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double monthlySalary() {
        return hours * hourlyRate;
    }
}

public class PayrollDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of employees: ");
        int numberOfEmployees = scanner.nextInt();
        Employee[] employees = new Employee[numberOfEmployees];

        // Create different employee types in one common array.
        for (int i = 0; i < employees.length; i++) {
            System.out.print("Enter employee type (fulltime/parttime/intern): ");
            String type = scanner.next().toLowerCase();
            System.out.print("Enter employee name and id: ");
            String name = scanner.next();
            String id = scanner.next();

            switch (type) {
                case "fulltime":
                    System.out.print("Enter fixed monthly salary: ");
                    employees[i] = new FullTime(name, id, scanner.nextDouble());
                    break;
                case "parttime":
                    System.out.print("Enter hours and hourly rate: ");
                    employees[i] = new PartTime(
                            name, id, scanner.nextDouble(), scanner.nextDouble());
                    break;
                case "intern":
                    System.out.print("Enter monthly stipend: ");
                    employees[i] = new Intern(name, id, scanner.nextDouble());
                    break;
                default:
                    throw new IllegalArgumentException("Unknown employee type");
            }
        }

        double totalSalary = 0;
        for (Employee employee : employees) {
            double salary = employee.monthlySalary();
            totalSalary += salary;
            System.out.printf("%s (%s): %.2f%n",
                    employee.getName(), employee.getId(), salary);

            // instanceof adds the special note only for interns.
            if (employee instanceof Intern) {
                System.out.println("Note: Intern stipend");
            }
        }

        System.out.printf("Total payroll: %.2f%n", totalSalary);
    }
}
