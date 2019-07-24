package com.example.demo;

import com.example.demo.service.Teacher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author hfj
 * @date 2019/4/1
 */
public class App {

    public static void main(String[] args) {
        // 用我们的配置文件来启动一个 ApplicationContext
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        ((ClassPathXmlApplicationContext) context).refresh();
        System.out.println("context 启动成功");

        // 从 context 中取出我们的 Bean，而不是用 new MessageServiceImpl() 这种方式
        Teacher teacher = context.getBean(Teacher.class);
        teacher.sing();
    }
}
