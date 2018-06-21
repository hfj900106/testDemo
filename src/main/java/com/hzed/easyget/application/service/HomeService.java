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
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.model.GlobalHead;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.model.RiskResponse;
import com.hzed.easyget.infrastructure.repository.*;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.JwtUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.persistence.auto.entity.*;
import com.hzed.easyget.persistence.ext.entity.TransactionExt;
import com.hzed.easyget.persistence.ext.entity.UserExt;
import com.hzed.easyget.persistence.ext.entity.VaData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BidRepository bidRepository;
    @Autowired
    private UserVisitRecordRepository userVisitRecordRepository;
    @Autowired
    private I18nService i18nService;

    private static final String ANDROID_BOMB = "android_bomb";
    private static final String IOS_BOMB = "ios_bomb";

    public ProductInfoResponse getProductInfo() {

        ProductInfoResponse productInfoResponse = new ProductInfoResponse();
        Product product = productRepository.getProductInfo();
        if (Objects.isNull(product)) {
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
        if (oldVersion.equals(newVersion)) {
            return AppVersionEnum.NOT_UPDATE.getCode();
        } else {
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

    public void checkLoan() {

        final String MK_02 = "MK02";

        Long userId = RequestUtil.getGlobalUser().getUserId();
        String imei = RequestUtil.getGlobalHead().getImei();
        User user = userRepository.findById(userId);

        RiskResponse response = comService.checkRiskEnableBorrow(user.getMobileAccount(), imei);
        String errorCode = response.getHead().getError_code();

        if (StringUtils.isNotBlank(errorCode)) {
            //每日通过超过数量
            if (MK_02.equals(errorCode)) {
                throw new WarnException(BizCodeEnum.INSUFFICIENT_QUOTA);
            } else {
                throw new WarnException(BizCodeEnum.UN_LOAN_QUALIFICATION);
            }
        }

    }

    public void checkJump() {

        Long userId = RequestUtil.getGlobalUser().getUserId();
        //bid为空或访问记录表不为空无需跳转，0000为无需跳转，其他需跳转
        List<Bid> bidList = bidRepository.findByUserId(userId);
        bidList.forEach(bid -> {
            //首页检测跳转，访问记录表为空需跳转，不为空无需跳转
            UserVisitRecord userVisitRecord = userVisitRecordRepository.findByUserIdAndBidId(userId, bid.getId());
            if (userVisitRecord == null) {
                throw new ComBizException(BizCodeEnum.NEED_JUMP);
            }
        });
    }

    public HomePageResponse checkRepayment() {
        Long userId = RequestUtil.getGlobalUser().getUserId();
        HomePageResponse result = new HomePageResponse();

        // 场景4、已经提交还款凭证，但未能等到最后结果情况
        // 当前用户未结清的标是否有提交过凭证
        List<String> evidences = userRepository.queryEvidences(userId);
        LocalDateTime now = LocalDateTime.now();
        if (!ObjectUtils.isEmpty(evidences)) {
            // 查询当前未完成还款的交易记录id，并检查交易是否已过期
            TransactionExt transactionExt = userRepository.queryTransaction(userId);
            result.setId(transactionExt.getTransactionId());
            // 超过两个小时即失效
            LocalDateTime confirmTime = transactionExt.getConfirmTime();
            LocalDateTime expireTime = DateUtil.addHour(confirmTime, 2);
            boolean expire = DateUtil.compare(now, expireTime);
            result.setExpire(expire);
            throw new ComBizException(BizCodeEnum.MSG_REPAY_APPLY, result);
        }

        // 场景3、判断是否已经生成va码,并且未提交还款凭证
        // 找出最新的va码数据
        VaData va = userRepository.queryLastVa(userId);
        if (va != null) {
            // 判断va是否过期
            LocalDateTime createTime = va.getCreateTime();
            LocalDateTime vaExpireTime = DateUtil.addHour(createTime, 6);
            boolean vaExpire = DateUtil.compare(now, vaExpireTime);
            va.setVaExpire(vaExpire);
            result.setVaData(va);
            result.setId(va.getTransactionId());
            throw new ComBizException(BizCodeEnum.MSG_REPAY_UNSUCCESS, result);
        }

        // 场景1、2，是否借款即将到期、逾期
        UserExt userExt = userRepository.queryOverdueDay(userId);
        if (userExt != null) {
            Integer overdueDay = userExt.getOverdueDay();
            // 有未结清的标,且离逾期天数小于等于两天。overdueDay = 今天日期 - 应还日期
            if (overdueDay != null) {
                if (overdueDay < -2) {
                    return result;
                }
                result.setId(userExt.getBidId());
                if (overdueDay > 0) {
                    throw new ComBizException(BizCodeEnum.MSG_BID_OVERDUE_AFTER, result, new Object[]{overdueDay});
                } else if (overdueDay == 0) {
                    throw new ComBizException(BizCodeEnum.MSG_BID_OVERDUE_TODAY, result);
                } else {
                    throw new ComBizException(BizCodeEnum.MSG_BID_OVERDUE_BEFORE, result, new Object[]{Math.abs(overdueDay)});
                }
            }
        }
        return result;
    }
}