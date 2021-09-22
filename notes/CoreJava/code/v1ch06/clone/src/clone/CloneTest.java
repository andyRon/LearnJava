package clone;

/**
 * 实现深拷贝
 * @author Andy Ron
 */
public class CloneTest {

    public static void main(String[] args) {
        try {
            Employee original = new Employee("Andy M. Ron", 50000);
            original.setHireDay(2000, 1, 1);
            Employee copy = original.clone();
            copy.raiseSalary(10);
            copy.setHireDay(2002, 12, 31);

            System.out.println("original=" + original);
            System.out.println("copy=" + copy);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
