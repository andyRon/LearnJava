package com.andyron.bcdlj.c06.c63;

/**
 * @author andyron
 **/
public class FinallyTest {
    public static void main(String[] args) {
//        System.out.println(test2());
        test3();
    }

    static int test1() {
        int ret = 0;
        try {
            return ret;
        } finally {
            ret = 2;
        }
    }

   static int test2(){
        int ret = 0;
        try{
            int a = 5/0;
            return ret;
        }finally{
            return 2;
        }
    }

    static void test3() {
        try {
            int a = 5/0;
        } finally {
            throw new RuntimeException("hello");
        }
    }
}
