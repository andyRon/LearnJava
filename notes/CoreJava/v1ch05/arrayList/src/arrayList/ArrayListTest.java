package arrayList;

import java.util.ArrayList;

/**
 * @author Andy Ron
 */
public class ArrayListTest {

    public static void main(String[] args) {
        ArrayList<Employee> staff = new ArrayList<>();

        staff.add(new Employee("Jack Ma", 500000, 1995,12,12));
        staff.add(new Employee("Pony Ma", 530000, 1999,11,12));
        staff.add(new Employee("Steven Ma", 550000, 2001,8,12));

        for (Employee e : staff)
            e.raiseSalary(5);

        for (Employee e : staff)
            System.out.println("name=" + e.getName() + ", salary=" + e.getSalary() + ", hireDay=" + e.getHireDay());
    }
}
