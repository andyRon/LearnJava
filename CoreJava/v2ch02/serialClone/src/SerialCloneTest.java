import java.io.*;
import java.time.LocalDate;

/**
 * @author Andy Ron
 */
public class SerialCloneTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        Employee jack = new Employee("Jack Ma", 35000, 1989, 10, 1);
        Employee jack2 = (Employee)jack.clone();

        jack.raiseSalary(10);

        System.out.println(jack);
        System.out.println(jack2);
    }
}

class SerialCloneable implements Cloneable, Serializable {

    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            try (ObjectOutputStream out = new ObjectOutputStream(bout)) {
                out.writeObject(this);
            }

            try (InputStream bin = new ByteArrayInputStream(bout.toByteArray())){
                ObjectInputStream in = new ObjectInputStream(bin);
                return in.readObject();
            }
        }
        catch (IOException | ClassNotFoundException e) {
            CloneNotSupportedException e2 = new CloneNotSupportedException();
            e2.initCause(e);
            throw e2;
        }
    }
}

class Employee extends SerialCloneable {

    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String n, double s, int year, int month, int day) {
        name = n;
        salary = s;
        hireDay = LocalDate.of(year, month, day);
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "[name=" + name
                + ", salary=" + salary
                + ", hireDay=" + hireDay
                + ']';
    }
}