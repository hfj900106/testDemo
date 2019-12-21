package com.example.demo.test;

/**
 * 单例
 * 枚举
 * 优点：能避免多线程同步问题，而且还自动支持序列化机制，防止反序列化重新创建新的对象，绝对防止多次实例化
 * @author hfj
 * @date 2018/10/23
 */
public enum Singleton3 {

   INSTANCE;

   public void getOne(){
       System.out.println("getOne");
   }

    public static void main(String[] args) {
        Singleton3.INSTANCE.getOne();
    }

}
