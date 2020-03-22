package randomAccess;

import java.io.*;
import java.util.*;
import java.time.*;

/**
 * 2.3.2 随机访问文件
 * @author Andy Ron
 */
public class RandomAccessTest {

    public static void main(String[] args) throws IOException {
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Jack Ma", 75000, 1987, 12, 15);
        staff[1] = new Employee("Tony Ma", 55000, 1989, 10, 15);
        staff[2] = new Employee("Bill Gates", 95000, 1990, 3, 15);

        try (DataOutputStream out =
                     new DataOutputStream(new FileOutputStream("../employee.dat"))) {
            for (Employee e : staff)
                writeData(out, e);
        }

        try (RandomAccessFile in = new RandomAccessFile("../employee.dat", "r")) {
            int n = (int)(in.length() / Employee.RECORD_SIZE);
            Employee[] newStaff = new Employee[n];

            for (int i = n - 1; i >= 0; i--) {
                newStaff[i] = new Employee();
                in.seek(i * Employee.RECORD_SIZE);
                newStaff[i] = readData(in);
            }

            for (Employee e : newStaff)
                System.out.println(e);
        }

    }

    public static void writeData(DataOutput out, Employee e) throws IOException {
        DataIO.writeFixedString(e.getName(), Employee.NAME_SIZE, out);
        out.writeDouble(e.getSalary());

        LocalDate hireDay = e.getHireDay();
        out.writeInt(hireDay.getYear());
        out.writeInt(hireDay.getMonthValue());
        out.writeInt(hireDay.getDayOfMonth());
    }

    public static Employee readData(DataInput in) throws IOException {
        String name = DataIO.readFixedString(Employee.NAME_SIZE, in);
        double salary = in.readDouble();
        int y = in.readInt();
        int m = in.readInt();
        int d = in.readInt();
        return new Employee(name, salary, y, m - 1, d);
    }

}
