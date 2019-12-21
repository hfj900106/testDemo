package com.example.demo.designpattern.prototypepattern;


import lombok.Cleanup;

import java.io.*;

/**
 * @author hfj
 * @date 2019-12-18
 */
public class Sheep implements Cloneable, Serializable {
    protected String name;
    protected int age;
    protected String color;

    public Sheep(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public Sheep clone() throws CloneNotSupportedException {
        return (Sheep) super.clone();
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }
}

/**
 * 浅拷贝
 */
class ShallowCopy extends Sheep{
    private Sheep friends;

    public ShallowCopy(String name, int age, String color, Sheep friends) {
        super(name, age, color);
        this.friends = friends;
    }

    public Sheep getFriends() {
        return friends;
    }

    public void setFriends(Sheep friends) {
        this.friends = friends;
    }

    @Override
    public ShallowCopy clone() throws CloneNotSupportedException {
        return (ShallowCopy) super.clone();
    }
}

/**
 * 深拷贝，自己处理引用类型的属性
 */
class DeepCopy extends Sheep{
    private Sheep friends;

    public DeepCopy(String name, int age, String color, Sheep friends) {
        super(name, age, color);
        this.friends = friends;
    }

    public Sheep getFriends() {
        return friends;
    }

    public void setFriends(Sheep friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "DeepCopy{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                ", friends=" + friends +
                '}';
    }

    @Override
    public DeepCopy clone() throws CloneNotSupportedException{
        DeepCopy deepCopy = (DeepCopy)super.clone();
        deepCopy.setFriends(deepCopy.friends.clone());
        return deepCopy;
    }
}

/**
 * 深拷贝 序列化与反序列化
 */
class DeepCopySerialization extends Sheep{
    private Sheep friends;

    public DeepCopySerialization(String name, int age, String color, Sheep friends) {
        super(name, age, color);
        this.friends = friends;
    }

    public Sheep getFriends() {
        return friends;
    }

    public void setFriends(Sheep friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "DeepCopySerialization{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                ", friends=" + friends +
                '}';
    }

    @Override
    public DeepCopySerialization clone() {
        // 序列化
        DeepCopySerialization object = null;
        try {
            @Cleanup
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            @Cleanup
            ObjectOutputStream outputStream = new ObjectOutputStream(bos);
            outputStream.writeObject(this);

            // 反序列化
            @Cleanup
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            @Cleanup
            ObjectInputStream reader = new ObjectInputStream(bis);
            object = (DeepCopySerialization)reader.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }
}