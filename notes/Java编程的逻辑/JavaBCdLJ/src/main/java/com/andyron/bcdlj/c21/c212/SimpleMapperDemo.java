package com.andyron.bcdlj.c21.c212;

public class SimpleMapperDemo {
    static class Student {
        String name;
        int age;
        Double score;
        Sex sex;

        public Student() {
        }

        public Student(String name, int age, double score, Sex sex) {
            this.name = name;
            this.age = age;
            this.score = score;
            this.sex = sex;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", score=" + score +
                    ", sex='" + sex + '\'' +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Double getScore() {
            return score;
        }

        public void setScore(Double score) {
            this.score = score;
        }

        public Sex getSex() {
            return sex;
        }

        public void setSex(Sex sex) {
            this.sex = sex;
        }
    }

    static class Sex {
        String sex;
        public Sex(String sex) {
            this.sex = sex == "man" ? "man" : "women";
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        @Override
        public String toString() {
            return sex;
        }
    }

    public static void main(String[] args) {
        Student andy = new Student("andy", 18, 88d, new Sex("man"));
        String str = SimpleMapper.toString(andy);
        System.out.println(str);
        Student andy_ = (Student) SimpleMapper.fromString(str);
        System.out.println(andy_);
    }
}
