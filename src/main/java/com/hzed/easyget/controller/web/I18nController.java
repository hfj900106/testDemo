package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.I18nService;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.annotation.TokenIgnore;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Waylon
 * @date 2018/6/8
 */
@ExceptionAnno
@RestController
@RequestMapping("/easy-get/i18n/messages")
public class I18nController {
    @Resource
    private I18nService i18nService;

    @ModuleFunc("I18N测试")
    @TokenIgnore
    @GetMapping("/codes/{code}")
    public String test(@PathVariable("code") String code) {
        System.out.println("code = " + code);
        String code1 = "bizcode." + BizCodeEnum.ILLEGAL_REQUEST.getCode();
        if(true) throw new ComBizException(BizCodeEnum.ILLEGAL_PARAM);
        return i18nService.getMessage(code1);
    }
}
