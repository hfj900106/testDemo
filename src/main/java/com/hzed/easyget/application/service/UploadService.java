package com.hzed.easyget.application.service;

import com.hzed.easyget.controller.model.UploadImgRequest;
import com.hzed.easyget.controller.model.UploadImgResponse;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.model.FileModel;
import com.hzed.easyget.infrastructure.model.Response;
import com.hzed.easyget.infrastructure.utils.FileUtil;
import org.springframework.stereotype.Service;

/**
 * 上传文件相关
 *
 * @author wuchengwu
 * @data 2018/6/11
 */
@Service
public class UploadService {

    public UploadImgResponse uploadImg(UploadImgRequest request) {

        String imgBase64 = request.getImgBase64();
        String pictureSuffix = request.getPictureSuffix();
        Response<FileModel> response = FileUtil.uploadByBase64(imgBase64, pictureSuffix);

        if (BizCodeEnum.SUCCESS.getCode().equals(response.getCode())) {

            return UploadImgResponse.builder().visitUrl(response.getData().getFilePath()).build();

        }
        throw new ComBizException(BizCodeEnum.SERVICE_EXCEPTION);

    }
}