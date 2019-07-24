package com.example.demo.test;


import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @author hfj
 * @date 2019/4/9
 */
public class HashMapTest {

    public static void main(String[] args) {
//        Object key = "123";
//        String key2 = "123";
//        Integer in = 123;
//        int h;
//        int h2;
//        h = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
//        h2 = (key2 == null) ? 0 : (h2 = key2.hashCode()) ^ (h2 >>> 16);
//        System.out.println(h);
//        System.out.println(h2);
//        System.out.println(in.hashCode());
        HashMap<String, String> hashMap = new HashMap<>(9);
        try {
            Field threshold = hashMap.getClass().getDeclaredField("threshold");
            threshold.setAccessible(true);
            System.out.println(threshold.get(hashMap));
            for (int a = 1; a <= 25; a++) {
                hashMap.put(a + "", a + "");
                System.out.println("a:" + a + ",threshold:" + threshold.get(hashMap));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        int n = 8 - 1;
//        n |= n >>> 1;
//        n |= n >>> 2;
//        n |= n >>> 4;
//        n |= n >>> 8;
//        n |= n >>> 16;
//        System.out.println(n);
    }

}
