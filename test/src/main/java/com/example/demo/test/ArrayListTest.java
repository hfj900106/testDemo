package com.example.demo.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 底层数据结构是数组，查询快，增删慢，线程不安全，效率高
 *
 * @author hfj
 * @date 2018/11/3
 */
public class ArrayListTest {

    public static void main(String[] args) {
        // 进行 10次测试

        test();
    }

    public static void test() {
        // 线程不安全
//        List<Object> list = new ArrayList<>();
        // 线程安全
        List<Object> listSync = Collections.synchronizedList(new ArrayList<>());
        // 线程数量(1000)
        int threadCount = 1000;
        // 用来让主线程等待threadCount个子线程执行完毕
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        // 启动threadCount个子线程
        for (int i = 0; i < threadCount; i++) {
            ThreadFactory threadFactory = Executors.defaultThreadFactory();
            threadFactory.newThread(new MyThread1(listSync, countDownLatch)).start();
        }
        try {
            // 主线程等待所有子线程执行完成，再向下执行
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // List 的size
        System.out.println(listSync.size());
    }
}

class MyThread1 implements Runnable {

    private List<Object> list;
    private CountDownLatch countDownLatch;

    public MyThread1(List<Object> list, CountDownLatch countDownLatch) {
        this.list = list;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        // 每个线程向List中添加100个元素
        for (int i = 0; i < 100; i++) {
            list.add(new Object());
        }
        // 完成一个子线程
        countDownLatch.countDown();
    }

}


