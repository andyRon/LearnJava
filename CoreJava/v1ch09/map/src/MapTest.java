import java.util.HashMap;
import java.util.Map;

/**
 * @author Andy Ron
 */
public class MapTest {

    public static void main(String[] args) {
        Map<String, Employee> staff = new HashMap<>();
        staff.put("144-25-5464", new Employee("Andy Ron"));
        staff.put("567-25-5464", new Employee("Jack Ma"));
        staff.put("187-25-5464", new Employee("Bill Gates"));
        staff.put("456-25-5464", new Employee("Tony Ma"));

        System.out.println(staff);

        staff.remove("567-25-5464");

        staff.put("456-62-5527", new Employee("Francesca Miller"));

        System.out.println(staff.get("187-25-5464"));

        staff.forEach((k, v) ->
                System.out.println("key="+ k + ", value=" + v));
    }
}

class Employee {
    private String name;
    private double salary;

    public Employee(String n) {
        name = n;
        salary = 0;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}