package com.hzed.easyget.application.service;

import com.google.common.collect.Lists;
import com.hzed.easyget.application.enums.AppVersionEnum;
import com.hzed.easyget.application.service.product.model.EasyGetProduct;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.config.redis.RedisService;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.consts.RedisConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.model.GlobalHead;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.repository.*;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.JwtUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.persistence.auto.entity.Dict;
import com.hzed.easyget.persistence.auto.entity.News;
import com.hzed.easyget.persistence.auto.entity.Product;
import com.hzed.easyget.persistence.auto.entity.UserToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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
    @Autowired
    private UserTokenRepository userTokenRepository;
    @Autowired
    private RedisService redisService;
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private ComService comService;

    private static final String ANDROID_BOMB = "android_bomb";
    private static final String IOS_BOMB = "ios_bomb";

    public ProductInfoResponse getProductInfo() {

        ProductInfoResponse productInfoResponse = new ProductInfoResponse();
        Product product = productRepository.getProductInfo();
        if(Objects.isNull(product)){
            throw new ComBizException(BizCodeEnum.NO_USEFUL_PRODUCT);
        }
        productInfoResponse.setLoanAmount(product.getLoanAmountArr());
        productInfoResponse.setLoanTime(product.getLoanTimeArr());
        return productInfoResponse;
    }

    public AppVersionResponse getAppVersion(AppVersionRequest request) {

        AppVersionResponse appVersionResponse = new AppVersionResponse();
        String platform = RequestUtil.getGlobalHead().getPlatform();
        String oldVersion = request.getOldVersion();
        String verDicCode;
        String updateDicCode;
        if (AppVersionEnum.ANDROID.getCode().equals(platform)) {
            verDicCode = AppVersionEnum.ANDROID_VERSION.getCode();
            updateDicCode = AppVersionEnum.ANDROID_UPDATE.getCode();
        } else if (AppVersionEnum.IOS.getCode().equals(platform)) {
            verDicCode = AppVersionEnum.IOS_VERSION.getCode();
            updateDicCode = AppVersionEnum.IOS_UPDATE.getCode();
        } else {
            throw new ComBizException(BizCodeEnum.ILLEGAL_PARAM);
        }

        Dict verDict = dictService.getDictByCode(verDicCode);
        Dict updateDict = dictService.getDictByCode(updateDicCode);
        appVersionResponse.setVersion(verDict.getDicValue());
        appVersionResponse.setPath(verDict.getDicLabel());
        appVersionResponse.setIsUpdate(checkIsUpdate(oldVersion, verDict.getDicValue()));
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

    public LoanCalculateResponse loanCalculate(LoanCalculateRequest request) {
        LoanCalculateResponse loanCalculateResponse = new LoanCalculateResponse();

        EasyGetProduct product = new EasyGetProduct(new BigDecimal(request.getLoanAmount()));

        loanCalculateResponse.setTotalAmount(product.getRepaymentAmount());
        return loanCalculateResponse;
    }

    public UpdateTokenResponse updateToken() {
        GlobalUser globalUser = RequestUtil.getGlobalUser();
        Long userId = globalUser.getUserId();
        String imei = RequestUtil.getGlobalHead().getImei();
        String newToken = JwtUtil.createToken(globalUser);
        UserToken userToken = new UserToken();
        userToken.setUpdateTime(LocalDateTime.now());
        userToken.setToken(newToken);
        userToken.setImei(imei);
        userToken.setExpireTime(DateUtil.addDays(LocalDateTime.now(), ComConsts.EXPIRE_DAYS));
        userTokenRepository.updateByUserIdAndImei(userToken);
        //放入redis 3个小时
        redisService.setCache(RedisConsts.TOKEN + RedisConsts.SPLIT + String.valueOf(userId) + RedisConsts.SPLIT + imei, newToken, 3 * 3600L);
        // 将新token返回给APP
        return UpdateTokenResponse.builder().token(newToken).build();
    }

    public List<NewsResponse> getNewsList() {
        List<NewsResponse> bombResponseList = Lists.newArrayList();

        GlobalHead globalHead = RequestUtil.getGlobalHead();
        String platform = globalHead.getPlatform();
        String version = globalHead.getVersion();
        //安卓是否要弹窗
        if (AppVersionEnum.ANDROID.getCode().equals(platform)) {
            Dict dictBomb = dictService.getDictByCode(ANDROID_BOMB);
            String dicValue = dictBomb.getDicValue();
            if (StringUtils.isBlank(dicValue) || version.equals(dicValue)) {

                return bombResponseList;
            }
        }
        //苹果是否要弹窗
        if (AppVersionEnum.IOS_VERSION.getCode().equals(platform)) {
            Dict dictBomb = dictService.getDictByCode(IOS_BOMB);
            String dicValue = dictBomb.getDicValue();
            if (StringUtils.isBlank(dicValue) || version.equals(dicValue)) {

                return bombResponseList;
            }
        }

        List<News> bombList = newsRepository.getBombList();
        for (News bomb : bombList) {
            NewsResponse bombResponse = new NewsResponse();
            bombResponse.setNewsTitle(bomb.getTitle());
            bombResponse.setImgUrl(bomb.getImgUrl());
            bombResponse.setToUrl(bomb.getToUrl());
            bombResponseList.add(bombResponse);
        }
        return bombResponseList;
    }

    public LoanResponse startLoan() {
        Long userId = RequestUtil.getGlobalUser().getUserId();
        boolean isLoan = comService.isLoan(userId);
        return LoanResponse.builder().isLoan(isLoan).build();
    }
}