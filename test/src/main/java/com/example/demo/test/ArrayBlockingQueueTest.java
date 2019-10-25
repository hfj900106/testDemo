package com.example.demo.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 *
 * @author hfj
 * @date 2019-07-24
 */
public class ArrayBlockingQueueTest {

    public static void main(String[] args) {

        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);
        /**
         * add() 调用的是offer()，当queue满了offer返回false，add()方法抛异常
         * java.lang.IllegalStateException: Queue full
         */
        try {
            blockingQueue.add(11);
            blockingQueue.add(12);
            blockingQueue.add(13);
            blockingQueue.add(14);
        } catch (Exception e) {
            System.out.println("queue满了add 抛异常：java.lang.IllegalStateException: Queue full");
        }

        // 当queue满了offer返回false
        System.out.println("queue满了offer 返回： " + blockingQueue.offer(2));

        // 当queue满了，指定时间内完成返回true，完不成返回false
        try {
            System.out.println("offer前：" + System.currentTimeMillis());
            boolean offer = blockingQueue.offer(4, 2, TimeUnit.SECONDS);
            System.out.println(offer);
            System.out.println("queue满了，用offer(E e, long timeout, TimeUnit unit)，指定时间内阻塞，等待加入队列，指定时间后还不能入队列则返回false");
            System.out.println("offer结束：" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        // 队列满了就一直阻塞，直到可以操作成功
//        try {
//            blockingQueue.put(3);
//            System.out.println("queue满了，用put，一直阻塞，直到可以执行put，这个话不能打印出来");
//        } catch (InterruptedException e) {
//            System.out.println(e);
//        }

        // 返回移除的值，没有值返回null
        Integer poll = blockingQueue.poll();
        System.out.println("poll：" + poll);

        // 返回移除的值，没有值抛异常：NoSuchElementException
        blockingQueue.remove();

        // 下不赘述

    }
}
