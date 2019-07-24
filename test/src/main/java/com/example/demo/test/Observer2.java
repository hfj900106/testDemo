package com.example.demo.test;

/**
 * @author hfj
 * @date 2018/10/23
 */
public class Observer2 extends Observer {

    /**
     * 构造方法中将自己发布到主题观察者列表中
     *
     * @param subject
     */
    public Observer2(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Observer2接收到更新数据：" + subject.getState());
    }
}
