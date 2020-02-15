package abstractClasses;

/**
 * @author Andy Ron
 */
public class PersonTest {
    public static void main(String[] args) {

        Person[] people = new Person[2];

        people[0] = new Employee("Jack Ma", 500000, 1965,12,12);
        people[1] = new Student("Xiao Ming", "computer science");

        for(Person p : people) {
            System.out.println(p.getName() + ", " + p.getDescription());
        }
    }
}
