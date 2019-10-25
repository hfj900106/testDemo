package com.example.demo.test;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 一次便利删除第n个元素，并且返回头元素
 *
 * @author hfj
 * @date 2019/4/11
 */
public class LinkedListTest {




    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");

        System.out.println("链表size：" + linkedList.size());
        Iterator iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        /**
         * 底层数据结构是链表，查询慢，增删快，线程不安全，效率高
         */

    }
}
