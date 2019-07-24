package com.example.demo.test;

/**
 * 单例
 * 饱汉模式
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

    /**
     * 声明对象
     */
    private static volatile Singleton2 singleton2 = null;

    /**
     * 获取对象方法
     */
    public static Singleton2 getSingleton2() {
        if (singleton2 == null) {
            // 可能有多个线程在此等待
            synchronized (Singleton2.class) {
                // 第一个进来的线程会初始化对象
                if (singleton2 == null) {
                    singleton2 = new Singleton2();
                }
            }
        }
        return singleton2;
    }


}
