package com.andyron.bcdlj.c16.c162;

import java.util.Random;

/**
 * 转账的错误写法
 * @author andyron
 **/
public class AccountMgr {
    public static class NoEnoughMoneyException extends Exception {}
    public static void transfer(Account from, Account to, double money) throws NoEnoughMoneyException {
        from.lock();
        try {
            to.lock();
            try {
                if (from.getMoney() >= money) {
                    from.reduce(money);
                    to.add(money);
                } else {
                    throw new NoEnoughMoneyException();
                }
            } finally {
                to.unlock();
            }
        } finally {
            from.unlock();
        }
    }

    /**
     * 模拟账户转账的死锁过程'
     * 创建了10个账户，100个线程，每个线程执行100次循环，在每次循环中，随机挑选两个账户进行转账
     */
    public static void simulateDeadLock() {
        final int accountNum = 10;
        final Account[] accounts = new Account[accountNum];
        final Random rnd = new Random();
        for (int i = 0; i < accountNum; i++) {
            accounts[i] = new Account(rnd.nextInt(10000));
        }
        int threadNum = 100;
        Thread[] threads = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            threads[i] = new Thread() {
                @Override
                public void run() {
                    int loopNum = 100;
                    for (int k = 0; k < loopNum; k++) {
                        int i = rnd.nextInt(accountNum);
                        int j = rnd.nextInt(accountNum);
                        int money = rnd.nextInt(10);
                        if (i != j) {
                            try {
                                transfer(accounts[i], accounts[j], money);
                            } catch (NoEnoughMoneyException e) {

                            }
                        }
                    }
                }
            };
            threads[i].start();
        }
    }

    /**
     * 使用tryLock尝试转账
     * 如果两个锁都能够获得，且转账成功，则返回true，否则返回false。
     */
    public static boolean tryTransfer(Account from, Account to, double money) throws NoEnoughMoneyException {
        if (from.tryLock()) {
            try {
                if (to.tryLock()) {
                    try {
                        if (from.getMoney() >= money) {
                            from.reduce(money);
                            to.add(money);
                        } else {
                            throw new NoEnoughMoneyException();
                        }
                        return true;
                    } finally {
                        to.unlock();
                    }
                }
            } finally {
                from.unlock();
            }
        }
        return false;
    }

    /**
     * transfer方法改进版：循环调用tryTransfer以避免死锁
     */
    public static void transfer_(Account from, Account to, double money) throws NoEnoughMoneyException {
        boolean success = false;
        do {
            success = tryTransfer(from, to, money);
            if (!success) {
                Thread.yield();
            }
        } while (!success);
    }
}
