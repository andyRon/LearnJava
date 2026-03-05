package top.andyron.ch05.ch53;

public class Person {

    @Range(min = 1, max = 20)
    public String name;

    @Range(min = 1, max = 10)
    public String city;
}
