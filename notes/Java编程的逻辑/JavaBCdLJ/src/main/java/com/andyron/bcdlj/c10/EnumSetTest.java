package com.andyron.bcdlj.c10;


import java.util.*;

/**
 * @author andyron
 **/
public class EnumSetTest {
    public static void main(String[] args) {
        Set<Day> weekend = EnumSet.noneOf(Day.class);
        weekend.add(Day.SATURDAY);
        weekend.add(Day.SUNDAY);
        System.out.println(weekend);
        System.out.println("---------");

        Worker[] workers = new Worker[]{
                new Worker("张三", EnumSet.of(
                        Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.FRIDAY)),
                new Worker("李四", EnumSet.of(
                        Day.TUESDAY, Day.THURSDAY, Day.SATURDAY)),
                new Worker("王五", EnumSet.of(Day.TUESDAY, Day.THURSDAY)),
        };
        // 哪些天一个人都不会来？
        Set<Day> days = EnumSet.allOf(Day.class);
        for(Worker w : workers){
            days.removeAll(w.getAvailableDays());
        }
        System.out.println(days);
        // 有哪些天至少会有一个人来？就是求worker时间的并集
        days = EnumSet.noneOf(Day.class);
        for(Worker w : workers){
            days.addAll(w.getAvailableDays());
        }
        System.out.println(days);
        // 有哪些天所有人都会来？就是求worker时间的交集
        days = EnumSet.allOf(Day.class);
        for(Worker w : workers){
            days.retainAll(w.getAvailableDays());
        }
        System.out.println(days);
        // 哪些人周一和周二都会来？
        Set<Worker> availableWorkers = new HashSet<Worker>();
        for(Worker w : workers){
            if(w.getAvailableDays().containsAll(
                    EnumSet.of(Day.MONDAY, Day.TUESDAY))){
                availableWorkers.add(w);
            }
        }
        for(Worker w : availableWorkers){
            System.out.println(w.getName());
        }

        // 哪些天至少会有两个人来？先使用EnumMap统计每天的人数，然后找出至少有两个人的天
        Map<Day, Integer> countMap = new EnumMap<>(Day.class);
        for(Worker w : workers){
            for(Day d : w.getAvailableDays()){
                Integer count = countMap.get(d);
                countMap.put(d, count==null?1:count+1);
            }
        }
        days = EnumSet.noneOf(Day.class);
        for(Map.Entry<Day, Integer> entry : countMap.entrySet()){
            if(entry.getValue()>=2){
                days.add(entry.getKey());
            }
        }
        System.out.println(days);
    }
}

enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

class Worker {
    String name;
    Set<Day> availableDays;
    public Worker(String name, Set<Day> availableDays) {
        this.name = name;
        this.availableDays = availableDays;
    }

    public String getName() {
        return name;
    }

    public Set<Day> getAvailableDays() {
        return availableDays;
    }
}