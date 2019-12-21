package com.example.demo.designpattern.prototypepattern;

/**
 * 原型模式
 *
 * @author hfj
 * @date 2019-12-18
 */
public class PrototypePattern1 {
    public static void main(String[] args) throws Exception {
        System.out.println("============== shallow copy =============");
        Sheep sheep = new Sheep("多利", 1, "白色");
        Sheep clone1 = sheep.clone();
        Sheep clone2 = sheep.clone();

        System.out.println(clone1 == clone2);
        System.out.println(clone1.toString());
        System.out.println(clone2.toString());

        ShallowCopy shallowCopy = new ShallowCopy("浅浅", 2, "浅色", new Sheep("多利", 1, "白色"));
        ShallowCopy clone = shallowCopy.clone();
        System.out.println(shallowCopy.getFriends() == clone.getFriends());

        /**
         * false
         * Sheep{name='多利', age=1, color='白色'}
         * Sheep{name='多利', age=1, color='白色'}
         * ============== shallow copy =============
         * true
         *
         * 浅拷贝 基本数据类型值传递，clone会复制一份属性值，但是如果有属性是引用类型，那么只是会把引用地址复制一份，原对象和clone对象都指向同一个引用地址
         * 深拷贝 所有属性都需要复制一份新的
         */

        System.out.println("============== deep copy 自己处理引用类型=============");
        DeepCopy deepCopy = new DeepCopy("深深", 3, "深色", new Sheep("多利", 1, "白色"));
        DeepCopy deepCopy1 = deepCopy.clone();
        System.out.println(deepCopy.toString());
        System.out.println(deepCopy1.toString());
        System.out.println(deepCopy.getFriends() == deepCopy1.getFriends());

        System.out.println("============== deep copy 自己处理引用类型=============");
        DeepCopySerialization copySerialization = new DeepCopySerialization("深深", 4, "深色", new Sheep("多利", 1, "白色"));
        DeepCopySerialization deepCopy2 = copySerialization.clone();
        System.out.println(copySerialization.getFriends() == deepCopy2.getFriends());
        System.out.println(copySerialization.toString());
        System.out.println(deepCopy2.toString());
    }

}
