package com.hzed.easyget.application.service;

import com.hzed.easyget.application.enums.AppVersionEnum;
import com.hzed.easyget.controller.model.AppVersionRequest;
import com.hzed.easyget.controller.model.AppVersionResponse;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.persistence.auto.entity.Dict;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 暂无描述
 *
 * @author wuchengwu
 * @data 2018/5/22
 */
@Slf4j
@Service
public class AppVersionService {

    @Autowired
    private DictService dictService;

    public AppVersionResponse getAppVersion(AppVersionRequest request) {


        String channel = request.getChannel();

        String oldVersion = request.getOldVersion();
        String verDicCode;
        String updateDicCode;
        if(channel.equals(AppVersionEnum.ANDROID.getCode())){
            verDicCode = AppVersionEnum.ANDROID_VERSION.getCode();
            updateDicCode = AppVersionEnum.ANDROID_UPDATE.getCode();
        }else if(channel.equals(AppVersionEnum.IOS.getCode())){
            verDicCode = AppVersionEnum.IOS_VERSION.getCode();
            updateDicCode = AppVersionEnum.IOS_UPDATE.getCode();
        }else {
            throw new ComBizException(BizCodeEnum.ILLEGAL_PARAM);
        }
        AppVersionResponse appVersionResponse = new AppVersionResponse();

        Dict verDict = dictService.getAppVersionByDicCode(verDicCode);
        Dict updateDict = dictService.getAppVersionByDicCode(updateDicCode);
        appVersionResponse.setVersion(verDict.getDicValue());
        appVersionResponse.setPath(verDict.getDicLabel());
        appVersionResponse.setIsUpdate(checkIsUpdate(oldVersion,verDict.getDicValue()));
        appVersionResponse.setIsForce(updateDict.getDicValue());
        return appVersionResponse;
    }

    private String checkIsUpdate(String oldVersion, String newVersion) {
        if(oldVersion.equals(newVersion)){
            return AppVersionEnum.NOT_UPDATE.getCode();
        }else {
            return AppVersionEnum.HAS_UPDATE.getCode();
        }
    }
}