package com.andyron.bcdlj.c13;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author andyron
 **/
public class InputStreamOutputStreamTest {
    public static void main(String[] args) throws IOException {
//        t1();
//        t2();

//        List<Student> students = Arrays.asList(new Student[]{
//                new Student("张三", 18, 80.9d), new Student("李四", 17, 67.5d)
//        });
//        writeStudents(students);

        System.out.println(readStudents().toString());

    }


    static void t1() throws IOException {
        OutputStream output = new FileOutputStream("hello.txt");
        try {
            String data = "hello, my name is andy";
            byte[] bytes = data.getBytes(Charset.forName("UTF-8"));
            output.write(bytes);
        } finally {
            output.close();
        }
    }

    static void t2() throws IOException {
        InputStream input = new FileInputStream("hello.txt");
        try {
            byte[] buf = new byte[1024];
            int bytesRead = input.read(buf);
            String data = new String(buf, 0, bytesRead, "UTF-8");
            System.out.println(data);
        } finally {
            input.close();
        }
    }

    static void writeStudents(List<Student> students) throws IOException{
        DataOutputStream output = new DataOutputStream(new FileOutputStream("students.dat"));
        try{
            output.writeInt(students.size());
            for(Student s : students){
                output.writeUTF(s.getName());
                output.writeInt(s.getAge());
                output.writeDouble(s.getScore());
            }
        }finally{
            output.close();
        }
    }

    static List<Student> readStudents() throws IOException{
        DataInputStream input = new DataInputStream(new FileInputStream("students.dat"));
        try{
            int size = input.readInt();
            List<Student> students = new ArrayList<Student>(size);
            for(int i=0; i<size; i++){
                Student s = new Student();
                s.setName(input.readUTF());
                s.setAge(input.readInt());
                s.setScore(input.readDouble());
                students.add(s);
            }
            return students;
        }finally{
            input.close();
        }
    }

    public static void copy(InputStream input, OutputStream output) throws IOException{
        byte[] buf = new byte[4096];
        int bytesRead = 0;
        while((bytesRead = input.read(buf)) != -1){
            output.write(buf, 0, bytesRead);
        }
    }
}
