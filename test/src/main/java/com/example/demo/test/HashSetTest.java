package com.example.demo.test;

import java.util.HashSet;
import java.util.Iterator;

/**
 * @author hfj
 * @date 2019/4/11
 */
public class HashSetTest {
    public static void main(String[] args) {
        HashSet<String> hashSet = new HashSet<>(4);
        hashSet.add("1");
        hashSet.add("11");
        hashSet.add("111");
        hashSet.add(null);
        Iterator<String> iterator = hashSet.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        /**
         * 输出结果：
         * 11
         * null
         * 1
         * 111
         * 特征：无序 不可重复 线程不安全 集合元素可以为 NULL
         */
    }
}
