package org.ph.share._02_createThread;


public class _01_CreateThread_Run {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("sub thread");
            }
        };
        thread.start();
        System.out.println("main ends");
    }
}
