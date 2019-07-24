package com.example.demo.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1.Lock前缀的指令会引起处理器缓存写回内存；
 * 2.一个处理器的缓存回写到内存会导致其他处理器的缓存失效；
 * 3.当处理器发现本地缓存失效后，就会从内存中重读该变量数据，即可以获取当前最新值。
 * 这样针对volatile变量通过这样的机制就使得每个线程都能获得该变量的最新值。
 *
 * @author hfj
 * @date 2018/10/23
 */
public class VolatileTest {


    //    static volatile Integer x = 0;
    static Integer x = 0;

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        TestThread1 thread1 = new TestThread1();
        service.execute(thread1);
        try {
            thread1.wait(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            TestThread2 thread2 = new TestThread2();
            service.execute(thread2);
            thread1.notifyAll();
            service.shutdown();
        }
    }

    static class TestThread1 implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                x++;
                System.out.println("thread1 is " + "time is " + i + "result is " + x);
            }
        }
    }

    static class TestThread2 implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                x++;
                System.out.println("thread2 is " + "time is " + i + "result is " + x);
            }
        }
    }
}
