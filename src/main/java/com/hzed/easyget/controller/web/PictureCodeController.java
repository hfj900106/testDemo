package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.PictureCodeService;
import com.hzed.easyget.controller.model.CheckPictureCodeRequest;
import com.hzed.easyget.controller.model.PictureCodeRequest;
import com.hzed.easyget.controller.model.PictureCodeResponse;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.annotation.TokenIgnore;
import com.hzed.easyget.infrastructure.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 图片验证码
 *
 * @author hfj
 * @date 2018/6/4
 */

@Slf4j
@ExceptionAnno
@RestController
@RequestMapping("/pictureCode")
public class PictureCodeController {

    @TokenIgnore
    @ModuleFunc("获取图片验证码")
    @PostMapping("/getPictureCode")
    public Response<PictureCodeResponse> getPictureCode(@RequestBody PictureCodeRequest request) {
        PictureCodeService pictureCodeService = new PictureCodeService();
        return Response.getSuccessResponse(pictureCodeService.getPictureCode(request.getMobile()));
    }

    @TokenIgnore
    @ModuleFunc("验证图片验证码")
    @PostMapping("/checkPictureCode")
    public Response checkPictureCode(@RequestBody CheckPictureCodeRequest request) {
        PictureCodeService pictureCodeService = new PictureCodeService();
        pictureCodeService.checkPictureCode(request.getMobile(),request.getCode());
        return Response.getSuccessResponse();
    }
}
