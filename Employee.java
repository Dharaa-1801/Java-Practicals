abstract class Employee {
    String name;
    int id;

    Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    abstract double monthlySalary();
}

class FullTime extends Employee {
    double salary;

    FullTime(String name, int id, double salary) {
        super(name, id);
        this.salary = salary;
    }

    double monthlySalary() {
        return salary;
    }
}

class PartTime extends Employee {
    double hours;
    double rate;

    PartTime(String name, int id, double hours, double rate) {
        super(name, id);
        this.hours = hours;
        this.rate = rate;
    }

    double monthlySalary() {
        return hours * rate;
    }
}

class Intern extends Employee {
    double stipend;

    Intern(String name, int id, double stipend) {
        super(name, id);
        this.stipend = stipend;
    }

    double monthlySalary() {
        return stipend;
    }
}

class EmployeeDemo {
    public static void main(String[] args) {

        Employee[] emp = {
            new FullTime("Dhara", 101, 30000),
            new PartTime("Durva", 102, 100, 200),
            new Intern("Riddhi", 103, 12000)
        };

        double total = 0;

        for (Employee e : emp) {
            double salary = e.monthlySalary();

            System.out.println("ID: " + e.id);
            System.out.println("Name: " + e.name);
            System.out.println("Salary: " + salary);

            if (e instanceof Intern) {
                System.out.println("This employee is an Intern.");
            }

            total = total + salary;
        }

        System.out.println("Total Salary: " + total);
    }
}

