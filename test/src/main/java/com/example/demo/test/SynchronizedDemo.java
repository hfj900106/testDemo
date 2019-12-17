package com.example.demo.test;

/**
 * 如果synchronized所修饰的是静态方法, 则其所用的锁为Class对象
 * 如果synchronized所修饰的是非静态方法, 则其所用的锁为方法调用所在的对象；
 * this = 方法调用所在的对象
 *
 * @author hfj
 * @date 2018/10/24
 */
public class SynchronizedDemo implements Runnable {

    private static int count = 0;

    /**
     * 线程不安全 ，结果不等于20000000
     */
    public void test1() {
        for (int j = 0; j < 10000000; j++) {
            count++;
        }
    }

    /**
     * 锁住类的实例对象 ，当有多个对象时也是线程不安全，结果不一定等于20000000
     */
    public void test2() {
        synchronized (this) {
            for (int j = 0; j < 10000000; j++) {
                count++;
            }
        }
    }

    /**
     * 锁住任意其他类实例对象 ，当有多个对象时也是线程安全，结果是20000000
     */
    public void test3() {
        synchronized ("123") {
            for (int j = 0; j < 10000000; j++) {
                count++;
            }
        }
    }


    /**
     * 锁住静态方法 ，当有多个对象时也是线程安全，结果是20000000
     */
    public synchronized static void test4() {
        for (int j = 0; j < 10000000; j++) {
            count++;
        }
    }


    /**
     * 锁住非静态方法 ，当有多个对象时不能保证线程安全，结果不一定是20000000
     */
    public synchronized  void test5() {
        for (int j = 0; j < 10000000; j++) {
            count++;
        }
    }
    /**
     * // 等同如下：
     * public void test5() {
     *     synchronized(this) {
     *         // 由锁保护的代码
     *          for (int j = 0; j < 10000000; j++) {
     *             count++;
     *         }
     *     }
     * }
     */


    /**
     * 锁当前类，当有多个对象时保证线程安全，结果是20000000
     */
    public void test6() {
        synchronized (SynchronizedDemo.class){
            for (int j = 0; j < 10000000; j++) {
                count++;
            }
        }
    }



    @Override
    public void run() {
//        test1();
//        test2();
//        test3();
//        test4();
        test5();
//        test6();
    }


    public static void main(String[] args) {
        Thread myThread1 = new Thread(new SynchronizedDemo());
        Thread myThread2 = new Thread(new SynchronizedDemo());
        myThread1.start();
        myThread2.start();
        try {
            myThread1.join();
            myThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count == 20000000);


    }


}
