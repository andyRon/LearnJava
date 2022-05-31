package com.andyron.bcdlj.c22.c225;

import java.util.Date;

public class Student {
        @Label("姓名")
        String name;

        @Label("出生日期")
        @Format(pattern="yyyy/MM/dd")
        Date born;

        @Label("分数")
        double score;

        public Student(String name, Date born, double score) {
                this.name = name;
                this.born = born;
                this.score = score;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Date getBorn() {
                return born;
        }

        public void setBorn(Date born) {
                this.born = born;
        }

        public double getScore() {
                return score;
        }

        public void setScore(double score) {
                this.score = score;
        }
}
