package com.example.demo.test;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author hfj
 * @date 2019/4/11
 */
public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add("1");
        linkedList.add("11");
        linkedList.add("111");
        linkedList.add("1111");
        Iterator iterator = linkedList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        /**
         * 底层数据结构是链表，查询慢，增删快，线程不安全，效率高
         */
    }
}
