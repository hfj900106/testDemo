package com.example.demo.test;

/**
 * @author hfj
 * @date 2020-01-01
 */
public class ClassLoaderDemo {
    public static void main(String[] args) {
        System.out.println("ClassLoaderDemo's ClassLoader is " + ClassLoaderDemo.class.getClassLoader());
        System.out.println("The Parent of ClassLoaderDemo's ClassLoader is " + ClassLoaderDemo.class.getClassLoader().getParent());
        System.out.println("The GrandParent of ClassLoaderDemo's ClassLoader is " + ClassLoaderDemo.class.getClassLoader().getParent().getParent());
    }
}
