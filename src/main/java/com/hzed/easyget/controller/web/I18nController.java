package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.LocaleMessageSourceService;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Waylon
 * @date 2018/6/8
 */
@RestController
@RequestMapping("/i18n/messages")
public class I18nController {
    @Resource
    private LocaleMessageSourceService localeMessageSourceService;

    @GetMapping("/code")
    public String test(String code) {
        String code1 = "bizcode." + BizCodeEnum.ILLEGAL_REQUEST.getCode();
        return localeMessageSourceService.getMessage(code1);
    }
}
