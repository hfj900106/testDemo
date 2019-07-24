package com.example.demo.test;

/**
 * @author hfj
 * @date 2018/10/8
 */
public class ThreadTest {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        //两个线程是在对同一个对象进行操作
        Thread ta = new Thread(thread, "Thread-A");
        Thread tb = new Thread(thread, "Thread-B");
        ta.start();
        tb.start();
    }
}


class MyThread implements Runnable{
    /**
     * 变量 a 被两个线程共同操作，可能会造成线程竞争
      */
    int a = 10;

    @Override
    public void run() {
        int i1 = 5;
        for (int i = 0; i < i1; i++) {
            a -= 1;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {}
            System.out.println(Thread.currentThread().getName() + " → a = " + a);
        }
    }
}