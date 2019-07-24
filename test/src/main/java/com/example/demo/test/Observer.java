package com.example.demo.test;

/**
 * 观察者 - 抽象
 *
 * @author hfj
 * @date 2018/10/23
 */
public abstract class Observer {
    /**
     * 观察的主题
     */
    protected Subject subject;

    /**
     * 抽象出来更新行为
     */
    public abstract void update();
}
