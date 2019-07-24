package com.example.demo.test;

import lombok.Data;

/**
 * @author hfj
 * @date 2018/9/29
 */
public class ParentTest {


    @Data
    public static class Parent implements Cloneable {
        private String name;
        private int age;
        private Son son;

        @Override
        protected Parent clone() {
            Parent parent = null;
            try {
                parent = (Parent) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return parent;
        }

        public Parent(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Parent[name:" + name + "; age" + age + "];" + "---Son[ name:"+ (son != null ? son.name : "null")+ "; age:"+ (son != null ? son.age : "null");
        }

    }

    @Data
    public static class Son implements Cloneable {
        private String name;
        private int age;

        @Override
        protected Son clone() {
            Son son = null;
            try {
                son = (Son) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return son;
        }

        public Son(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Son{" + "name='" + name + '\'' + ", age=" + age + '}';
        }

    }

    public static void main(String[] args) {
        Parent parent1 = new Parent("lion", 12);
        Son son = new Son("lilei",2);
        parent1.setSon(son);
        Parent parent2 = parent1.clone();
        parent1.setName("修改后的parent");
        son.setName("修改后的son");
        System.out.println("Parent1内存地址:" + System.identityHashCode(parent1));
        System.out.println("Parent2内存地址:" + System.identityHashCode(parent2));
        System.out.println("Parent1:" + parent1.toString());
        System.out.println("Parent2:" + parent2.toString());

    }


}
