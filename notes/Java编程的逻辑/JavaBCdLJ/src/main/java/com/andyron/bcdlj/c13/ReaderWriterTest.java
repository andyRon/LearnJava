package com.andyron.bcdlj.c13;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author andyron
 **/
public class ReaderWriterTest {
    public static void main(String[] args) throws IOException {
//        t2();

//        Character ch = '戎';
//        System.out.println(Integer.toHexString((int) ch));
//
//        t5();

//        List<Student> students = Arrays.asList(new Student[]{
//            new Student("王五", 18, 80.9d), new Student("赵六", 17, 67.5d)
//        });
//        writeStudents(students);

//        System.out.println(readStudents());

//        PrintWriter writer = new PrintWriter("PrintWriter.txt");
//        writer.format("%.2f", 123,456f);
    }


    static void t1() throws IOException {
        DataOutputStream output = new DataOutputStream(new FileOutputStream("test.data"));
        try {
            output.writeInt(123);
        } finally {
            output.close();
        }
    }

    static void t2() throws IOException {
        OutputStream output = new FileOutputStream("test.txt");
        try {
            String data = Integer.toString(123);
            output.write(data.getBytes("UTF-8"));
        } finally {
            output.close();
        }
    }

    static void t3() throws IOException {
        Writer writer = new OutputStreamWriter(new FileOutputStream("hello.txt"), "GB2312");
        try {
            String str = "hello, 123, 老戎";
            writer.write(str);
        } finally {
            writer.close();
        }
    }

    static void t4() throws IOException {
        Reader reader = new InputStreamReader(new FileInputStream("hello.txt"), "GB2312");
        try {
            char[] cbuf = new char[1024];
            int charsRead = reader.read(cbuf);
            System.out.println(new String(cbuf, 0, charsRead));
        } finally {
            reader.close();
        }
    }

    static void t5() throws IOException {
        Reader reader = new InputStreamReader(new FileInputStream("hello.txt"), "GB2312");
        try {
            CharArrayWriter writer = new CharArrayWriter();
            char[] cbuf = new char[1024];
            int charsRead = 0;
            while ((charsRead = reader.read(cbuf)) != -1) {
                writer.write(cbuf, 0, charsRead);
            }
            System.out.println(writer.toString());
        } finally {
            reader.close();
        }
    }

    static void writeStudents(List<Student> students) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("students.txt"));
            for (Student s : students) {
                writer.write(s.getName() + ", " + s.getAge() + ", " + s.getScore());
                writer.newLine();
            }
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    static List<Student> readStudents() throws IOException{
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader("students.txt"));
            List<Student> students = new ArrayList<>();
            String line = reader.readLine();
            while(line != null){
                String[] fields = line.split(", ");
                Student s = new Student();
                s.setName(fields[0]);
                s.setAge(Integer.parseInt(fields[1]));
                s.setScore(Double.parseDouble(fields[2]));
                students.add(s);
                line = reader.readLine();
            }
            return students;
        } finally {
            if(reader != null){
                reader.close();
            }
        }
    }

    public static void writeStudents2(List<Student> students) throws IOException{
        PrintWriter writer = new PrintWriter("students.txt");
        try{
            for(Student s : students){
                writer.println(s.getName()+", "+s.getAge()+", "+s.getScore());
            }
        }finally{
            writer.close();
        }
    }
}
