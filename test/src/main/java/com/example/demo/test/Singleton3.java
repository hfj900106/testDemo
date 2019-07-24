package com.example.demo.test;

/**
 * 单例
 * 饱汉模式
 * @author hfj
 * @date 2018/10/23
 */
public class Singleton3 {

    private Singleton3(){}

    /**
     * 主要是使用了 嵌套类可以访问外部类的静态属性和静态方法 的特性
     */
    private static class innerSingleton3{
        private static Singleton3 singleton3 = new Singleton3();
    }

    public static Singleton3 getSingleton3(){
        return innerSingleton3.singleton3;
    }
}
