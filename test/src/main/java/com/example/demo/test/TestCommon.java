package com.example.demo.test;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @author hfj
 * @date 2018/11/3
 */
public class TestCommon {
    public static void main(String[] args) {
//        ArrayList list = new ArrayList(2);
//        for (int i = 0; i < 20; i++) {
//            list.add(1);
//            System.out.println("当前i=" + i + "；当前elementData的length=" + getArrayListCapacity(list));
//        }

        ArrayList list = new ArrayList();
        System.out.println("new对象后elementData的length=" + getArrayListCapacity(list));
        list.add(1);
        System.out.println("放第一个数据后elementData的length=" + getArrayListCapacity(list));
//        for (int i = 0; i < 20; i++) {
//            list.add(1);
//
//            System.out.println("当前i=" + i + "；当前elementData的length=" + getArrayListCapacity(list));
//        }
//        System.out.println(15>>1);
    }

    /**
     * 反射获取ArrayList的elementData的length
     *
     * @param arrayList
     * @return
     */
    public static int getArrayListCapacity(ArrayList<?> arrayList) {
        Class<ArrayList> arrayListClass = ArrayList.class;
        try {
            Field field = arrayListClass.getDeclaredField("elementData");
            field.setAccessible(true);
            Object[] objects = (Object[]) field.get(arrayList);
            return objects.length;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return -1;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
