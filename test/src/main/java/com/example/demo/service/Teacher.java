package com.example.demo.service;

import com.example.demo.annotation.EnableLog;
import org.springframework.stereotype.Component;

/**
 * @author hfj
 * @date 2019/3/27
 */
//@Component
public class Teacher implements Person {
    @Override
    @EnableLog("使用log打印")
    public void sing() {
        System.out.println("老师在唱歌");
    }

    @Override
    public void dance() {
        System.out.println("老师在跳舞");
    }
}
