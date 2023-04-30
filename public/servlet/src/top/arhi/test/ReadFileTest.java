package top.arhi.test;

import top.arhi.thread.ReadFile;

public class ReadFileTest {
    public static void main(String[] args) {
        Runnable runnable = new ReadFile();
        Thread threadA=new Thread(runnable);
        Thread threadB=new Thread(runnable);
        Thread threadC=new Thread(runnable);
        Thread threadD=new Thread(runnable);
        Thread threadE=new Thread(runnable);
        //开始执行线程任务
        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
        threadE.start();
    }
}
