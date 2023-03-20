package com.andyron.bcdlj.c13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author andyron
 **/
public class ScannerTest {
    public static void main(String[] args) throws IOException {

        System.out.println(readStudents());
    }

    static List<Student> readStudents() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("students.txt"));
        try {
            List<Student> students = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                Student s = new Student();
                Scanner scanner = new Scanner(line).useDelimiter(", ");
                s.setName(scanner.next());
                s.setAge(scanner.nextInt());
                s.setScore(scanner.nextDouble());
                students.add(s);
                line = reader.readLine();
            }
            return students;
        } finally {
            reader.close();
        }
    }
}
