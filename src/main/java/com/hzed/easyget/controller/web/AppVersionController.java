package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.AppVersionService;
import com.hzed.easyget.controller.model.AppVersionRequest;
import com.hzed.easyget.controller.model.AppVersionResponse;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 版本号更新
 *
 * @author wuchengwu
 * @data 2018/5/22
 */
@RestController
@ExceptionAnno
@RequestMapping("/hzed/easy-get/version")
public class AppVersionController {

    @Autowired
    private AppVersionService appVersionService;

    @ModuleFunc("版本号检测是否更新")
    @PostMapping("/getAppVersion")
    public Response<AppVersionResponse> getAppVersion(@RequestBody AppVersionRequest request){

        return Response.getSuccessResponse(appVersionService.getAppVersion(request));

    }
}