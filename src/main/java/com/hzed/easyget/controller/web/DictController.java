package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.DictService;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.annotation.head.IgnoreHeader;
import com.hzed.easyget.infrastructure.annotation.head.TokenIgnore;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * @author guichang
 * @date 2018/5/22
 */

@Slf4j
@ExceptionAnno
@RestController
@RequestMapping("/api/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    @IgnoreHeader
    @ModuleFunc("清除字典缓存")
    @GetMapping("/clearCodeCache/{code}")
    public void clearCodeCache(@PathVariable String code) {
        log.info("请求参数：{}", code);
        dictService.clearCodeCache(code);
    }

    @IgnoreHeader
    @ModuleFunc("清除地区字典缓存")
    @GetMapping("/clearCodeAndLanguageCache/{code}/{language}")
    public void clearCodeAndLanguageCache(@PathVariable String code, @PathVariable String language) {
        log.info("请求参数：{}, {}", code, language);
        dictService.clearCodeAndLanguageCache(code, language);
    }

    @TokenIgnore
    @ModuleFunc("获取字典列表")
    @PostMapping("/getDictList")
    public List<DictResponse> getDictList(@Valid @RequestBody DictRequest request) {
        return dictService.getDictByModuleCodeAndLanguage(request.getModuleCode(), RequestUtil.getGlobalHead().getI18n());
    }

    @TokenIgnore
    @ModuleFunc("获取印尼省市区")
    @PostMapping("/getIDAreaList")
    public List<IDAreaResponse> getIDAreaList(@Valid @RequestBody IDAreaRequest request) {
        return dictService.getIDAreaList(request.getParent());
    }

    /**
     * =======================================================================================================================================
     **/
    @IgnoreHeader
    @ModuleFunc("切换短信通道")
    @GetMapping("/switchSmsChannel/{channel}")
    public void switchSmsChannel(@PathVariable String channel) {
        log.info("请求报文：{}", channel);
        dictService.switchSmsChannel(channel);
    }

    @IgnoreHeader
    @ModuleFunc("修改版本号")
    @GetMapping("/updateVersion/{channel}/{newVersion}/{minVersionCode}")
    public void updateVersion(@PathVariable String channel, @PathVariable String newVersion, @PathVariable String minVersionCode) {
        log.info("请求报文，channel：{}, newVersion：{}, minVersionCode：{}", channel, newVersion, minVersionCode);
        dictService.updateVersion(channel, newVersion, minVersionCode);
    }

}
