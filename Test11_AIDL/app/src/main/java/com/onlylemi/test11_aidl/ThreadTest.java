package com.onlylemi.test11_aidl;

/**
 * ThreadTest
 *
 * @author qijianbin
 * @time 2016-9-27 08:35
 */
public class ThreadTest {

    public static void main(String[] args) {
        Thread.currentThread().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                System.out.println(thread.getName() + " " + throwable);
            }
        });

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {


                int b = 8 / 0;
            }
        });

        t.start();
    }
}
