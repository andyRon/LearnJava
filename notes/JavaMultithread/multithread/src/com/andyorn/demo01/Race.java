package com.andyorn.demo01;

/**
 模拟龟兔赛跑

 1. 首先来个赛道距离，然后要离终点越来越近
 2. 判断比赛是否结束
 3. 打印出胜利者
 4. 龟兔赛跑开始
 5. 故事中是乌龟赢的，兔子需要睡觉，所以我们来模拟兔子睡觉
 6. 终于，乌龟赢得比赛

 */
public class Race implements Runnable{

    private static String winner;

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {

            // 模拟兔子休息
            if (Thread.currentThread().getName().equals("兔子") && i%10 == 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //判断比赛是否结束
            if (gameOver(i)) {
                break;
            }

            System.out.println(Thread.currentThread().getName() + "--> 跑了" + i + "步");
        }
    }

    /**
     * 判断是否完成比赛
     */
    private boolean gameOver(int steps) {

        if (winner != null) {
            return true;
        } else if (steps >= 100) {
            winner = Thread.currentThread().getName();
            System.out.println("Winner is " + winner);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Race race = new Race();

        new Thread(race, "兔子").start();
        new Thread(race, "乌龟").start();
    }
}
