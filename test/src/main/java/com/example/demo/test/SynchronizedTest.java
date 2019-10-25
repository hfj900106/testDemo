package com.example.demo.test;

/**
 * @author hfj
 * @date 2019-07-27
 */
public class SynchronizedTest {
    public static void main(String[] args) {
        synchronized (SynchronizedDemo.class) {
            method();
        }
    }

    private static void method() {
    }
}
