package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.UploadService;
import com.hzed.easyget.controller.model.UploadImgRequest;
import com.hzed.easyget.controller.model.UploadImgResponse;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 上传文件
 *
 * @author wuchengwu
 * @data 2018/6/11
 */
@ExceptionAnno
@RestController
@RequestMapping("/upload")
public class UploadImgController {

   @Autowired
   private UploadService uploadService;
    /**
     * 上传文件
     */
    @ModuleFunc("上传图片")
    @PostMapping("/img")
    public Response<UploadImgResponse> uploadImg(@RequestBody UploadImgRequest request) throws Exception {
        return Response.getSuccessResponse(uploadService.uploadImg(request));
    }
}