package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.LocaleMessageSourceService;
import com.hzed.easyget.infrastructure.annotation.TokenIgnore;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Waylon
 * @date 2018/6/8
 */
@RestController
@RequestMapping("/easy-get/i18n/messages")
public class I18nController {
    @Resource
    private LocaleMessageSourceService localeMessageSourceService;

    @TokenIgnore
    @GetMapping("/codes/{code}")
    public String test(@PathVariable("code") String code) {
        System.out.println("code = " + code);
        String code1 = "bizcode." + BizCodeEnum.ILLEGAL_REQUEST.getCode();
        return localeMessageSourceService.getMessage(code1);
    }
}
