package com.andyron.bcdlj.c14.c144;

import com.andyron.bcdlj.c14.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author andyron
 **/
public class SerializableTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

//        List<Student> students = new ArrayList<>();
//        students.add(new Student("james", 38, "96.6"));
//        students.add(new Student("lives", 28, "94.6"));
//        writeStudents(students);

        System.out.println(readStudents());
    }

    public static void writeStudents(List<Student> students) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream("students2.dat")));
        try {
            out.writeObject(students);
        } finally {
            out.close();
        }
    }

    public static List<Student> readStudents() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream("students2.dat")));
        try {
            return (List<Student>) in.readObject();
        } finally {
            in.close();
        }
    }
}
