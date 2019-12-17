package com.example.demo.test;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author hfj
 * @date 2019-12-05
 */
public class TreeSetTest {

    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(2);
        treeSet.add(1);
        treeSet.add(4);
        treeSet.add(3);

        Iterator<Integer> iterator = treeSet.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next()+"\n");
        }

    }
}
