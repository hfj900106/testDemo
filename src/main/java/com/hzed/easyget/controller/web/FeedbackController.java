package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.FeedbackService;
import com.hzed.easyget.controller.model.FeedbackRequest;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户问题反馈
 *
 * @author hfj
 * @date 2018/08/30
 */

@Slf4j
@ExceptionAnno
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @ModuleFunc(value = "新增反馈", printParameterLength = 300)
    @PostMapping("/add")
    public void add(@Valid @RequestBody FeedbackRequest request) {
        feedbackService.add(request);
    }

}
