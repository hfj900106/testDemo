package com.example.demo.test;

import java.util.Iterator;
import java.util.Vector;

/**
 * @author hfj
 * @date 2019/4/11
 */
public class VectorTest {
    public static void main(String[] args) {
        Vector vector = new Vector();
        vector.add("1");
        vector.add("1");
        vector.add("11");
        vector.add("111");
        vector.add(null);
        Iterator iterator = vector.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        /**
         * 底层数据结构是数组，查询快，增删慢，线程安全，效率低，耗性能
         */
    }
}
