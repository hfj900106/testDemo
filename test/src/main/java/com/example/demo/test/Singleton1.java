package com.example.demo.test;

/**
 * 单列
 * 双重校验锁
 * 优点：线程安全，延迟加载，效率较高
 *
 * @author hfj
 * @date 2018/10/23
 */
public class Singleton1 {

    /**
     * 将默认构造方法改私有，拒绝使用者创建对象
     */
    private Singleton1() {
    }

    private static volatile Singleton1 singleton1;

    /**
     * 对外提供对象
     */
    public static Singleton1 getSingleton1() {
        if (singleton1 == null) {
            // 可能有多个线程都进来
            synchronized (Singleton1.class) {
                // 可能有多个线程都 依次通过同步锁 进来，那么只能有第一个线程可以创建对象
                if (singleton1 == null) {
                    singleton1 = new Singleton1();
                }
            }
        }
        return singleton1;
    }


    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName() + "：" + Singleton1.getSingleton1().hashCode()), "线程1").start();
        new Thread(() -> System.out.println(Thread.currentThread().getName() + "：" + Singleton1.getSingleton1().hashCode()), "线程2").start();
    }

}
