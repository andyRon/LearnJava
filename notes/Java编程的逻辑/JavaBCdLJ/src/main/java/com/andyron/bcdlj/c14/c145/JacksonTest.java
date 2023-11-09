package com.andyron.bcdlj.c14.c145;

import com.andyron.bcdlj.c13.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.msgpack.jackson.dataformat.MessagePackFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author andyron
 **/
public class JacksonTest {
    public static void main(String[] args) throws IOException {
//        json();
//        json2();
//        collection2();

//        complex();

//        messagePack();
        messagePack2();
    }


    static void json() throws JsonProcessingException {
        Student student = new Student("张三", 21, 80.9d);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String str = mapper.writeValueAsString(student);
        System.out.println(str);
    }

    static void json2() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Student s = mapper.readValue(new File("student.json"), Student.class);
        System.out.println(s.toString());
    }

    static void xml() {

    }

    static void messagePack() throws IOException {
        Student student = new Student("李三", 28, 81.9d);
        ObjectMapper mapper = new ObjectMapper(new MessagePackFactory());
        byte[] bytes = mapper.writeValueAsBytes(student);
        mapper.writeValue(new File("student.bson"), student);
    }

    static void messagePack2() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new MessagePackFactory());
        Student s = mapper.readValue(new File("student.bson"), Student.class);
        System.out.println(s.toString());
    }

    static void collection() throws IOException {
        List<Student> students = Arrays.asList(new Student[] {
                new Student("张三", 18, 80.9d), new Student("李四", 17, 67.5d) });
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String str = mapper.writeValueAsString(students);
        mapper.writeValue(new File("students.json"), students);
        System.out.println(str);
    }

    static void collection2() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Student> list = mapper.readValue(new File("students.json"),
                new TypeReference<List<Student>>() {});
        System.out.println(list.toString());
    }

    static void complex() throws IOException {
        ComplexStudent student = new ComplexStudent("张三", 18);
        Map<String, Double> scoreMap = new HashMap<>();
        scoreMap.put("语文", 89d);
        scoreMap.put("数学", 83d);
        student.setScores(scoreMap);
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setPhone("18500308990");
        contactInfo.setEmail("zhangsan@sina.com");
        contactInfo.setAddress("中关村");
        student.setContactInfo(contactInfo);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(System.out, student);
    }

}

