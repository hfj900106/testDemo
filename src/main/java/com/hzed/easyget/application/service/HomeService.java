package com.hzed.easyget.application.service;

import com.hzed.easyget.application.enums.AppVersionEnum;
import com.hzed.easyget.controller.model.AppVersionRequest;
import com.hzed.easyget.controller.model.AppVersionResponse;
import com.hzed.easyget.controller.model.ProductInfoResponse;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.model.GlobalHeadr;
import com.hzed.easyget.infrastructure.repository.ProductRepository;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.persistence.auto.entity.Dict;
import com.hzed.easyget.persistence.auto.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author wuchengwu
 * @data 2018/5/22
 */
@Service
public class HomeService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private DictService dictService;

    public ProductInfoResponse getProductInfo() {

        ProductInfoResponse productInfoResponse = new ProductInfoResponse();
        Product product = productRepository.getProductInfo();
        if(Objects.isNull(product)){
            throw new ComBizException(BizCodeEnum.PRODUT_NOTEXISTS);
        }
        productInfoResponse.setLoanAmount(product.getLoanAmount());
        productInfoResponse.setLoanTime(product.getLoanTime());
        return productInfoResponse;
    }

    public AppVersionResponse getAppVersion(AppVersionRequest request) {

        GlobalHeadr globalHead = RequestUtil.getGlobalHead();
        String platform = globalHead.getPlatform();

        String oldVersion = request.getOldVersion();
        String verDicCode;
        String updateDicCode;
        if(AppVersionEnum.ANDROID.getCode().equals(platform)){
            verDicCode = AppVersionEnum.ANDROID_VERSION.getCode();
            updateDicCode = AppVersionEnum.ANDROID_UPDATE.getCode();
        }else if(AppVersionEnum.IOS.getCode().equals(platform)){
            verDicCode = AppVersionEnum.IOS_VERSION.getCode();
            updateDicCode = AppVersionEnum.IOS_UPDATE.getCode();
        }else {
            throw new ComBizException(BizCodeEnum.ILLEGAL_PARAM);
        }
        AppVersionResponse appVersionResponse = new AppVersionResponse();

        Dict verDict = dictService.getDictByCode(verDicCode);
        Dict updateDict = dictService.getDictByCode(updateDicCode);
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