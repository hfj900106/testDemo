package com.example.demo.test;

/**
 * 单例
 * 静态内部类
 * 静态内部类方式在Singleton类被装载时并不会立即实例化，而是在需要实例化时，调用getInstance方法，才会装载SingletonInstance类，从而完成Singleton的实例化
 * 优点：避免了线程不安全，延迟加载，效率高
 *
 * @author hfj
 * @date 2018/10/23
 */
public class Singleton2 {

    /**
     * 私有化默认构造方法
     */
    private Singleton2() {
    }

    private static class SingletonInstance {
        /**
         * 私有静态常量
         */
        private static final Singleton2 INSTANCE = new Singleton2();
    }

    /**
     * 获取对象方法
     */
    public static Singleton2 getSingleton2() {
        return SingletonInstance.INSTANCE;
    }

    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName() + "：" + Singleton2.getSingleton2().hashCode()), "线程1").start();
        new Thread(() -> System.out.println(Thread.currentThread().getName() + "：" + Singleton2.getSingleton2().hashCode()), "线程2").start();
    }


}
