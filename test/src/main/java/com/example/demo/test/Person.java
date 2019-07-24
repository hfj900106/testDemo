package com.example.demo.test;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

/**
 * equals和hashCode一定要同时重写，否则会出现equals相等的两个对象hashCode却不相同，而Java中equals相等的两个对象hashCode一定相同的原则
 * @author hfj
 * @date 2019/5/21
 */
@Data
@AllArgsConstructor
public class Person {
    private String name;
    private int age;

    @Override
    public boolean equals(Object o) {
        Person person = (Person) o;
        return this.age == person.age && this.name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + this.age;
    }

    public static void main(String[] args) {
        Person person = new Person("张三", 12);
        Person person2 = new Person("张三", 12);
        System.out.println(person.equals(person2));
        System.out.println(person.hashCode());
        System.out.println(person2.hashCode());
        HashMap<Person, String> map = new HashMap<>(2);
        map.put(person, "a");
        System.out.println(map.get(person));
        System.out.println(map.get(person2));

    }

}
