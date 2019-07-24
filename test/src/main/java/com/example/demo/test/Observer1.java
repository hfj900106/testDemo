package com.example.demo.test;


/**
 * @author hfj
 * @date 2018/10/23
 */
public class Observer1 extends Observer {

    /**
     * 在构造方法中进行订阅主题
     */
    public Observer1(Subject subject) {
        this.subject = subject;
        // 通常在构造方法中将 this 发布到主题观察者列表中
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Observer1被通知内容更新：" + subject.getState());
    }

}
