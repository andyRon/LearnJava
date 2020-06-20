package objectStream;

import java.io.*;

/**
 * @author Andy Ron
 */
public class ObjectStreamTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Employee jack = new Employee("Jack Ma", 50000, 1989, 10, 1);
        Manager bill = new Manager("Bill Gates", 80000, 1987, 12, 15);
        bill.setSecretary(jack);
        Manager tony = new Manager("Tony Ma", 40000, 1990, 3, 15);
        tony.setSecretary(jack);

        Employee[] staff = new Employee[3];

        staff[0] = bill;
        staff[1] = jack;
        staff[2] = tony;

        try (ObjectOutputStream out =
                new ObjectOutputStream((new FileOutputStream("../employee.dat")))) {
            out.writeObject(staff);
        }

        try (ObjectInputStream in =
                new ObjectInputStream(new FileInputStream("../employee.dat"))) {
            Employee[] newStaff = (Employee[]) in.readObject();

            newStaff[1].raiseSalary(10);

            for (Employee e : newStaff)
                System.out.println(e);
        }
    }
}
