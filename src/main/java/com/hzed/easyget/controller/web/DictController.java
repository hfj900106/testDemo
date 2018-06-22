package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.DictService;
import com.hzed.easyget.controller.model.DictRequest;
import com.hzed.easyget.controller.model.DictResponse;
import com.hzed.easyget.controller.model.IDAreaRequest;
import com.hzed.easyget.controller.model.IDAreaResponse;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.HeaderIgnore;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
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

    @HeaderIgnore
    @ModuleFunc("根据module清除字典缓存")
    @GetMapping("/clearModuleCache/{module}")
    public void clearModuleCache(@PathVariable String module) {
        dictService.clearModuleCache(module);
    }

    @HeaderIgnore
    @ModuleFunc("根据moduleAndI18n清除字典缓存")
    @GetMapping("/clearModuleAndI18nCache/{module}/{i18n}")
    public void clearModuleAndI18nCache(@PathVariable String module, @PathVariable String i18n) {
        dictService.clearModuleAndI18nCache(module, i18n);
    }


    @ModuleFunc("通过module获取字典列表")
    @PostMapping("/getDictList")
    public List<DictResponse> getDictList(@Valid @RequestBody DictRequest request){
        return dictService.getDictByModule(request);
    }

    @ModuleFunc("获取印尼省市区")
    @PostMapping("/getIDAreaList")
    public List<IDAreaResponse> getIDAreaList(@Valid @RequestBody IDAreaRequest request){
        return dictService.getIDAreaList(request);
    }


}
