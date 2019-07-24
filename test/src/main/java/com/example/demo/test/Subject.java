package com.example.demo.test;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hfj
 * @date 2018/10/23
 */
public class Subject {

    /**
     * 定义观察者列表
     */
    private List<Observer> observers = new ArrayList<>();

    @Getter
    private int state;

    public void setState(int state) {
        this.state = state;
        // 数据已变更，通知观察者们
        notifyAllObservers();
    }

    /**
     * 通知观察者们
     */
    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    /**
     * 添加观察者
     */
    public void attach(Observer observer) {
        observers.add(observer);
    }


}
