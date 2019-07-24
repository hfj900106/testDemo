package com.example.demo.test;

/**
 * 单列
 * 饿汉模式
 * @author hfj
 * @date 2018/10/23
 */
public class Singleton1 {

    /**
     * 将默认构造方法改私有，拒绝使用者创建对象
     */
    private Singleton1(){}
    /**
     * 创建私有静态实例，意味着这个类第一次使用的时候就会进行创建，若类中包含其他无关对象的方法，那么也会创建，存在缺陷
     */
    private static Singleton1 singleton1 = new Singleton1();

    /**
     * 对外提供对象
     */
    public static Singleton1 getSingleton1(){
        return singleton1;
    }

}
