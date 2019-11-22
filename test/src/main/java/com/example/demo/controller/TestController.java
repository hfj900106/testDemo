//package com.example.demo.controller;
//
//import com.example.demo.service.Teacher;
//import com.example.demo.vo.Company;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import javax.validation.constraints.NotNull;
//
///**
// * @author hfj
// * @date 2018/11/6
// */
//@Controller
//@RequestMapping("/test")
//public class TestController {
//    @Autowired
//    private Teacher teacher;
//    @Resource
//    private Company company;
//
//    @GetMapping("/redirect")
//    public String testRedirect() {
//        return "http://baidu.com";
//    }
//
//    @GetMapping("/toPage/{id}")
//    public String toPage(@PathVariable("id") int id, @NotNull @RequestParam int count) {
//        System.out.println("id：" + id + "，count：" + count);
//        teacher.sing();
//        return "success";
//    }
//
//    @GetMapping("/getObject")
//    @ResponseBody
//    public Company getObject() {
//        Company company2 = new Company();
//
//        return company;
//    }
//
//}
