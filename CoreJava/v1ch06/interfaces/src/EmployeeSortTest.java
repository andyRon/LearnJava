import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author Andy Ron
 */
public class EmployeeSortTest {
    public static void main(String[] args) {

        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Jack Ma", 75000);
        staff[1] = new Employee("Pony Ma", 73000);
        staff[2] = new Employee("Bill Gates", 90000);

        Arrays.sort(staff);

        for (Employee e : staff)
            System.out.println("name=" + e.getName() + ", salary=" + e.getSalary());

    }
}
