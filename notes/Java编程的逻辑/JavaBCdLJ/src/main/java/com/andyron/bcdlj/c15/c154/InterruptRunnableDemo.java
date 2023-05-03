package com.andyron.bcdlj.c15.c154;

/**
 * @author andyron
 **/
public class InterruptRunnableDemo extends Thread {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            //
        }
        System.out.println("done ");
    }
    //

}
