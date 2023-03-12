package com.andyron.bcdlj.c07.c76;

import java.util.Random;

/**
 * 抢红包算法
 * 维护一个剩余总金额和总数量，分配时，如果数量等于1，直接返回总金额，
 * 如果大于1，则计算平均值，并设定随机最大值为平均值的两倍，然后取一个随机值，
 * 如果随机值小于0.01，则为0.01，这个随机值就是下一个的红包金额。
 * @author andyron
 **/
public class RandomRedPacket {
    private int leftMoney;
    private int leftNum;
    private Random rnd;

    public RandomRedPacket(int leftMoney, int leftNum) {
        this.leftMoney = leftMoney;
        this.leftNum = leftNum;
        this.rnd = new Random();
    }

    public synchronized int nextMoney() {
        if (this.leftNum <= 0) {
            throw new IllegalStateException("抢光了");
        }
        if (this.leftNum == 1) {
            return this.leftMoney;
        }
        double max = this.leftMoney / this.leftNum * 2d;
        int money = (int)(rnd.nextDouble() * max);
        money = Math.max(1, money);
        this.leftMoney -= money;
        this.leftNum--;
        return money;
    }
}
