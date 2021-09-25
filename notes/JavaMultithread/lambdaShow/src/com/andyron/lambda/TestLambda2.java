package com.andyron.lambda;

/**
 * Lambda表示的简化
 */
public class TestLambda2 {
    public static void main(String[] args) {
        ILove love = null;

        love = (int a) -> {
            System.out.println("I love you -->" + a);
        };

        // 简化1：去掉参数类型，多个参数也可以去掉参数类型
        love = (a) -> {
            System.out.println("I love you -->" + a);
        };

        // 简化2：去掉括号
        love = a -> {
            System.out.println("I love you -->" + a);
            System.out.println("I love you too.");
        };

        // 简化3：去掉大括号（因为代码只有一行）
        love = a -> System.out.println("I love you -->" + a);

        love.love(520);
    }
}

interface ILove {
    void love(int a);
}
