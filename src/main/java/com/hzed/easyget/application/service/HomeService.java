package com.hzed.easyget.application.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.hzed.easyget.application.enums.AppVersionEnum;
import com.hzed.easyget.application.enums.BidProgressTypeEnum;
import com.hzed.easyget.application.enums.BidStatusEnum;
import com.hzed.easyget.application.enums.ProductEnum;
import com.hzed.easyget.application.service.product.ProductFactory;
import com.hzed.easyget.application.service.product.model.AbstractProduct;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.config.SystemProp;
import com.hzed.easyget.infrastructure.config.redis.RedisService;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.consts.RedisConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.model.Response;
import com.hzed.easyget.infrastructure.model.RiskResponse;
import com.hzed.easyget.infrastructure.repository.*;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.JwtUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.persistence.auto.entity.*;
import com.hzed.easyget.persistence.ext.entity.UserExt;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author wuchengwu
 * @data 2018/5/22
 */
@Slf4j
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
    private UserRepository userRepository;
    @Autowired
    private BidRepository bidRepository;
    @Autowired
    private UserLoanVisitRepository userLoanVisitRepository;
    @Autowired
    private RiskService riskService;
    @Autowired
    private SystemProp systemProp;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BidProgressRepository bidProgressRepository;
    @Autowired
    private ComService comService;

    private static final String ANDROID_BOMB = "android_bomb";
    private static final String IOS_BOMB = "ios_bomb";

    public ProductInfoResponse getProductInfo() {

        ProductInfoResponse productInfoResponse = new ProductInfoResponse();
        Product product = productRepository.findByCode(ProductEnum.PRODUCT_CODE.getCode());
        if (Objects.isNull(product)) {
            throw new ComBizException(BizCodeEnum.NO_USEFUL_PRODUCT);
        }
        productInfoResponse.setLoanAmount(product.getLoanAmountArr());
        productInfoResponse.setLoanTime(product.getLoanTimeArr());
        return productInfoResponse;
    }

    public AppVersionResponse getAppVersion(AppVersionRequest request) {

        AppVersionResponse appVersionResponse = new AppVersionResponse();

        String channel = request.getChannel();
        Integer versionCode = request.getVersionCode();
        Dict verDict = dictService.getDictByCode(channel);
        String dictLabelJson = verDict.getDicLabel();
        JSONObject jsonObject = JSONObject.parseObject(dictLabelJson);
        appVersionResponse.setVersion(verDict.getDicValue());
        appVersionResponse.setPath(jsonObject.getString("update_url"));
        checkIsUpdate(verDict.getDicValue(), appVersionResponse, jsonObject, versionCode);
        return appVersionResponse;
    }

    private void checkIsUpdate(String newVersion, AppVersionResponse appVersionResponse, JSONObject jsonObject, Integer versionCode) {
        String oldVersion = RequestUtil.getGlobalHead().getVersion();
        if (oldVersion.equals(newVersion)) {
            appVersionResponse.setIsUpdate(AppVersionEnum.NOT_UPDATE.getCode());
            appVersionResponse.setIsForce("1");
        } else {
            appVersionResponse.setIsUpdate(AppVersionEnum.HAS_UPDATE.getCode());
            appVersionResponse.setIsForce(jsonObject.getString("force_update"));
            Integer minimumVersion = Integer.valueOf(jsonObject.getString("minimum_version"));
            if (versionCode < minimumVersion) {
                appVersionResponse.setIsForce("0");
            }
        }
    }

    public LoanCalculateResponse loanCalculate(LoanCalculateRequest request) {
        LoanCalculateResponse loanCalculateResponse = new LoanCalculateResponse();

        BigDecimal loanAmount = request.getLoanAmount();
        Integer period = request.getPeriod();

        AbstractProduct productInfo = ProductFactory.getProduct(com.hzed.easyget.application.service.product.ProductEnum.EasyGet).createProduct(loanAmount, period);
        loanCalculateResponse.setTotalAmount(productInfo.getTotalRepaymentAmount());

        loanCalculateResponse.setPeriod(period);
        loanCalculateResponse.setLoanAmount(loanAmount);
        return loanCalculateResponse;
    }

    public UpdateTokenResponse updateToken() {
        GlobalUser globalUser = RequestUtil.getGlobalUser();
        Long userId = globalUser.getUserId();
        String imei = RequestUtil.getGlobalHead().getImei();
        UserToken utk = userTokenRepository.findByUserIdAndImei(userId, imei);
        // 时间是今天，就不用更新token
        if (!ObjectUtils.isEmpty(utk)) {
            LocalDateTime updateTime = utk.getUpdateTime();
            if (updateTime != null && DateUtil.isToday(updateTime)) {
                return UpdateTokenResponse.builder().token(RequestUtil.getGlobalHead().getToken()).build();
            }
        }

        String newToken = JwtUtil.createToken(globalUser);
        UserToken userToken = new UserToken();
        userToken.setUserId(userId);
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

    public NewsResponse getMessage() {
        NewsResponse newsResponse = new NewsResponse();
        String i18n = RequestUtil.getGlobalHead().getI18n();
        News news = newsRepository.findOne(i18n);
        if (!ObjectUtils.isEmpty(news)) {
            // 如果不在30天内，直接返回
            int day = DateUtil.daysBetween(news.getCreateTime(), LocalDateTime.now());
            if (day > systemProp.getExpiredDay()) {
                return newsResponse;
            }

            newsResponse.setTitle(news.getTitle());
            newsResponse.setContent(news.getContent());
            newsResponse.setToUrl(systemProp.getH5MessageUrl() + news.getId());
            newsResponse.setCreateTime(DateUtil.localDateTimeToTimestamp(news.getCreateTime()));
            newsResponse.setId(news.getId());
        }

        return newsResponse;
    }

    public Response<List<CheckLoanResponse>> checkLoan() {
        final String mk02 = "1100011";
        final String mk06 = "1100014";
        Long userId = RequestUtil.getGlobalUser().getUserId();
        String imei = RequestUtil.getGlobalHead().getImei();
        User user = userRepository.findById(userId);
        List<Bid> bidList = bidRepository.findByUserId(userId);
        List<CheckLoanResponse> checkLoanResponseList = Lists.newArrayList();
        bidList.forEach(bid -> {
            CheckLoanResponse checkLoanResponse = new CheckLoanResponse();
            checkLoanResponse.setBidStatus(bid.getStatus().toString());
            checkLoanResponseList.add(checkLoanResponse);
        });
        RiskResponse response = riskService.checkRiskEnableBorrow(user.getMobileAccount(), imei);
        log.info("贷款资格校验风控返回报文：{}",response);
        String errorCode = response.getHead().getError_code();
        log.info("查询风控是否有贷款资格，风控返回被拒原因:{}，用户id:{}", response.getHead().getError_msg(), userId);

        String code;
        String msg;
        if (StringUtils.isBlank(errorCode)) {
            return Response.getSuccessResponse(checkLoanResponseList);
        }

        //每日通过超过数量
        if (mk02.equals(errorCode)) {
            code = BizCodeEnum.INSUFFICIENT_QUOTA.getCode();
            msg = BizCodeEnum.INSUFFICIENT_QUOTA.getMessage();
        } else if (mk06.equals(errorCode)) {
            code = BizCodeEnum.BID_EXISTS.getCode();
            msg = BizCodeEnum.BID_EXISTS.getMessage();
        } else {
            code = BizCodeEnum.UN_LOAN_QUALIFICATION.getCode();
            msg = BizCodeEnum.UN_LOAN_QUALIFICATION.getMessage();
        }

        return Response.getFailResponse(checkLoanResponseList, code, msg);

    }

    public CheckLoanJumpResponse checkLoanJump() {
        CheckLoanJumpResponse checkLoanJumpResponse = new CheckLoanJumpResponse();
        Long userId = RequestUtil.getGlobalUser().getUserId();
        //bid为空或访问记录表不为空无需跳转，0000为无需跳转，其他需跳转
        List<Bid> bidList = bidRepository.findByUserIdAndStatus(userId, Lists.newArrayList(BidStatusEnum.AUDIT_FAIL.getCode().byteValue(),
                BidStatusEnum.AUDIT_PASS.getCode().byteValue(), BidStatusEnum.REPAYMENT.getCode().byteValue()));
        bidList.forEach(bid -> {
            //首页检测跳转，访问记录表为空需跳转，不为空无需跳转
            UserLoanVisit userVisitRecord = userLoanVisitRepository.findByUserIdAndBidIdAndStatus(userId, bid.getId(), bid.getStatus());
            if (userVisitRecord == null) {
                checkLoanJumpResponse.setBid(bid.getId());
                throw new WarnException(BizCodeEnum.NEED_JUMP, checkLoanJumpResponse);
            }
        });
        return checkLoanJumpResponse;
    }

    public CheckRepaymentResponse checkRepayment() {
        Long userId = RequestUtil.getGlobalUser().getUserId();
        CheckRepaymentResponse result = new CheckRepaymentResponse();
        // 场景1、2，是否借款即将到期、逾期
        // 查询该用户已放款的标的对应未结清的账单的应还时间
        UserExt userExt = userRepository.queryUnRepayment(userId);
        if (userExt != null && userExt.getRepaymentTime() != null) {
            LocalDateTime repaymentTime = userExt.getRepaymentTime();
            LocalDateTime now = LocalDateTime.now();
            int days = DateUtil.daysBetweenNoHMS(repaymentTime, now);
            // 有未结清的标,且离逾期天数小于等于两天。days = 今天日期 - 应还日期
            if (days < -2) {
                return result;
            }
            result.setId(userExt.getId());
            if (days < 0) {
                throw new WarnException(BizCodeEnum.MSG_BID_OVERDUE_BEFORE, result, new Object[]{Math.abs(days)});
            } else if (days == 0) {
                throw new WarnException(BizCodeEnum.MSG_BID_OVERDUE_TODAY, result);
            } else {
                throw new WarnException(BizCodeEnum.MSG_BID_OVERDUE_AFTER, result, new Object[]{days});
            }
        }
        return result;
    }

    public BidProgressResponse getBidProgress() {

        BidProgressResponse bidProgressResponse = new BidProgressResponse();
        Long userId = RequestUtil.getGlobalUser().getUserId();
        Bid bid = bidRepository.findOneByUserId(userId);
        // 不存在标的或已结清不弹窗提示
        if (ObjectUtils.isEmpty(bid) || BidStatusEnum.CLEARED.getCode().byteValue() == bid.getStatus()) {
            return bidProgressResponse;
        }

        // 贷款金额 贷款期限
        bidProgressResponse.setLoanAmount(bid.getLoanAmount());
        bidProgressResponse.setPeriod(bid.getPeriod());
        Long bidId = bid.getId();
        // 已放款
        if (BidStatusEnum.REPAYMENT.getCode().byteValue() == bid.getStatus()) {
            Bill bill = billRepository.findByBid(bidId);
            if (!ObjectUtils.isEmpty(bill)) {

                // 逾期天数计算 大于当前时间表示逾期，小于等于表示没到期
                int days = DateUtil.daysBetweenNoHMS(bill.getRepaymentTime(), LocalDateTime.now());
                bidProgressResponse.setOverdueDay(days > 0 ? days : 0);
                bidProgressResponse.setRepayTime(DateUtil.localDateTimeToTimestamp(bill.getRepaymentTime()));
                bidProgressResponse.setTotalRepayAmount(comService.getBidNoRepayFee(bidId, LocalDateTime.now()));

                // 放款日期
                BidProgress bidProgress = bidProgressRepository.findByBidIdAndType(bidId, BidProgressTypeEnum.LOAN.getCode().byteValue());
                bidProgressResponse.setLoanTime(DateUtil.localDateTimeToTimestamp(bidProgress.getHandleTime()));
                bidProgressResponse.setPopupChoice(1);
                // 计算是否在弹窗日期之内，当前日期减去放款日期，小于等于3，要弹，反之不弹
                int popupDay = DateUtil.daysBetweenNoHMS(bidProgress.getHandleTime(), LocalDateTime.now());
                bidProgressResponse.setPopup(popupDay <= 3 ? true : false);
            }
            return bidProgressResponse;
        }

        // 审核中
        bidProgressResponse.setApplyTime(DateUtil.localDateTimeToTimestamp(bid.getCreateTime()));
        if (BidStatusEnum.RISK_ING.getCode().byteValue() == bid.getStatus() || BidStatusEnum.MANMADE_ING.getCode().byteValue() == bid.getStatus()) {
            bidProgressResponse.setReviewStatus(BidProgressTypeEnum.AUDIT.getCode());
        }
        bidProgressResponse.setReviewStatus(Integer.valueOf(bid.getStatus()));

        BidProgress bidProgresses = bidProgressRepository.findByBidIdAndType(bidId, BidProgressTypeEnum.AUDIT.getCode().byteValue());
        bidProgressResponse.setReviewTime(DateUtil.localDateTimeToTimestamp(bidProgresses.getHandleTime()));
        bidProgressResponse.setPopupChoice(2);
        int popupDay = DateUtil.daysBetweenNoHMS(bidProgresses.getHandleTime(), LocalDateTime.now());
        bidProgressResponse.setPopup(popupDay <= 3 ? true : false);
        return bidProgressResponse;
    }
}