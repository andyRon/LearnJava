package com.andyron.bcdlj.c06.c61;

/**
 * @author andyron
 **/
public class ExceptionTest2 {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("请输入数字");
            return;
        }
        int num = Integer.parseInt(args[0]);
        System.out.println(num);
    }
}

class AppException extends RuntimeException {

}