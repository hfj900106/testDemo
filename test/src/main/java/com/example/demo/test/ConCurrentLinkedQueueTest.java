package com.example.demo.test;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author hfj
 * @date 2019-07-24
 */
public class ConCurrentLinkedQueueTest {

    public static void main(String[] args) {
        ConcurrentLinkedQueue<Integer> linkedQueue = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < 100; i++) {
            linkedQueue.add(i);
            System.out.println("添加" + i);
        }
        for (int i = 0; i < 100; i++) {
            Integer poll = linkedQueue.poll();
            System.out.println("取出" + poll);
        }
    }

}
