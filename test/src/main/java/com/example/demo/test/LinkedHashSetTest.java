package com.example.demo.test;

import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * @author hfj
 * @date 2019/4/11
 */
public class LinkedHashSetTest {
    public static void main(String[] args) {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("1");
        linkedHashSet.add("1");
        linkedHashSet.add("11");
        linkedHashSet.add("111");
        Iterator<String> iterator = linkedHashSet.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        /**
         * 有序、线程不安全、元素不可重复
         */
    }
}
