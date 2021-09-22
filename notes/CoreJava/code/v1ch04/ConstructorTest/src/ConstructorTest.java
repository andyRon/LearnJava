import java.util.*;

/** 4.6.7 初始化块
 * @author Andy Ron
 */
public class ConstructorTest {
    public static void main(String[] args) {

        //
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Andy", 40000);
        staff[1] = new Employee(60000);
        staff[2] = new Employee();

        for (Employee e : staff)
            System.out.println("name=" + e.getName() + ",id=" + e.getId() + ",salary=" + e.getSalary());
    }
}

class Employee {

    private  static int nextId; // 静态变量

    private int id;
    private String name = "";
    private double salary;

    // 静态初始化块
    static {
        Random generator = new Random();
        nextId = generator.nextInt(10000);
    }
    // 对象初始化块
    {
        id = nextId;
        nextId++;
    }

    public Employee(String n, double s) {
        name = n;
        salary = s;
    }

    public Employee(double s) {
        // 构造器中使用另一个构造器
        this("Employee #" + nextId, s);
    }
    // 默认构造器
    public Employee() {}

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }
}