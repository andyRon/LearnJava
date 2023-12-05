package com.andyron.mat;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生流量网页的记录程序，记录每个学生访问过的网址
 *
 * -XX:+HeapDumpBeforeFullGC -XX:HeapDumpPath=/Users/andyron/Downloads/mat_log/student.hprof
 *
 * @author andyron
 **/
public class StudentTrace {
    static List<WebPage> webPages = new ArrayList<>();
    static void createWebPages() {
        for (int i = 0; i < 100; i++) {
            WebPage wp = new WebPage();
            wp.setUrl("http://www." + Integer.toString(i) + ".com");
            wp.setContent(Integer.toString(i));
            webPages.add(wp);
        }
    }

    public static void main(String[] args) {
        createWebPages();
        Student st3 = new Student(3, "Tom");
        Student st5 = new Student(5, "Jerry");
        Student st7 = new Student(7, "Lily");

        for (int i = 0; i < webPages.size(); i++) {
            if (i % st3.getId() == 0) {
                st3.visit(webPages.get(i));
            }
            if (i % st5.getId() == 0) {
                st5.visit(webPages.get(i));
            }
            if (i % st7.getId() == 0) {
                st7.visit(webPages.get(i));
            }
        }
        webPages.clear();
        System.gc();
    }

}

class Student {
    private int id;
    private String name;
    private List<WebPage> history = new ArrayList<>();
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<WebPage> getHistory() {
        return history;
    }
    public void setHistory(List<WebPage> history) {
        this.history = history;
    }
    public void visit(WebPage wp) {
        if (wp != null) {
            history.add(wp);
        }
    }
}

class WebPage {
    private String url;
    private String content;
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
