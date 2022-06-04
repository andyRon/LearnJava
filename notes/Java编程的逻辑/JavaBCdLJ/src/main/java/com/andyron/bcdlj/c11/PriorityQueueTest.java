package com.andyron.bcdlj.c11;

import java.util.*;

public class PriorityQueueTest {
    public static void main(String[] args) {

        ex2();
    }

    static void ex1() {
        Queue<Integer> pq = new PriorityQueue<>();
//        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        pq.offer(10);
        pq.add(22);
        pq.addAll(Arrays.asList(new Integer[]{11, 12, 34, 2, 7, 4, 15, 12, 8, 6, 19, 13}));
        while (pq.peek() != null) {
            System.out.print(pq.poll() + " ");
        }
    }

    private static Comparator<Task> taskComparator = new Comparator<Task>() {
        @Override
        public int compare(Task o1, Task o2) {
            if (o1.getPriority() > o2.getPriority()) {
                return -1;
            } else if (o1.getPriority() < o2.getPriority()) {
                return 1;
            }
            return 0;
        }
    };

    static void ex2() {
        Queue<Task> tasks = new PriorityQueue<>(11, taskComparator);
        tasks.offer(new Task(20, "写日记"));
        tasks.offer(new Task(10, "看电视"));
        tasks.offer(new Task(100, "写代码"));
        Task task = tasks.poll();
        while (task != null) {
            System.out.println("处理任务：" + task.getName() + ", 优先级：" + task.getPriority());
            task = tasks.poll();
        }
    }
}

class Task {
    int priority;
    String name;

    @Override
    public String toString() {
        return "Task{" +
                "priority=" + priority +
                ", name='" + name + '\'' +
                '}';
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Task(int priority, String name) {
        this.priority = priority;
        this.name = name;
    }
}