package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.DictService;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.annotation.TokenIgnore;
import com.hzed.easyget.infrastructure.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author guichang
 * @date 2018/5/22
 */

@Slf4j
@ExceptionAnno
@RestController
@RequestMapping("/hzed/easy-get/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    @TokenIgnore
    @ModuleFunc("清除字典缓存")
    @PostMapping("/sendSmsCode")
    public @ResponseBody
    Response discardsCache(String key) {
        return Response.getSuccessResponse();
    }



}
