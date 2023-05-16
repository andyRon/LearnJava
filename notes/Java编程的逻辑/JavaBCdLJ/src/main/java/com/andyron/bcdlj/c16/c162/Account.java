package com.andyron.bcdlj.c16.c162;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 账户
 */
public class Account {
    private Lock lock = new ReentrantLock();
    private volatile double money; // 当前余额当前余额
    public Account(double initialMoney) {
        this.money = initialMoney;
    }
    public void add(double money) {
        lock.lock();
        try {
            this.money += money;
        } finally {
            lock.unlock();
        }
    }
    public void reduce(double money) {
        lock.lock();
        try {
            this.money -= money;
        } finally {
            lock.unlock();
        }
    }
    public double getMoney() {
        return money;
    }
    void lock() {
        lock.lock();
    }
    void unlock() {
        lock.unlock();
    }
    boolean tryLock() {
        return lock.tryLock();
    }

    @Override
    public String toString() {
        return "Account{" +
                "money=" + money +
                '}';
    }
}
