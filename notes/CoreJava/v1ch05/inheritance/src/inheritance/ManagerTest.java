package inheritance;
/**
 *
 */
public class ManagerTest {

    public static void main(String[] args) {
        Manager boss = new Manager("Jack Ma", 100000000, 1965, 8,18);
        boss.setBonus(50000);

        Employee[] staff = new Employee[3];

        staff[0] = boss;
        staff[1] = new Employee("Pony Ma", 55000, 1993, 11,2);
        staff[2] = new Employee("Robin Li", 25000, 1994, 4,12);

        for (Employee e: staff) {
            System.out.println("name=" + e.getName() + ", salary=" + e.getSalary());
        }

    }

}
