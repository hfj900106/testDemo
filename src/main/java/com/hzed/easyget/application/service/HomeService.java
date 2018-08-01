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
import com.hzed.easyget.infrastructure.model.AppVersionModel;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.repository.*;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.JwtUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.persistence.auto.entity.*;
import com.hzed.easyget.persistence.ext.entity.UserExt;
import lombok.extern.slf4j.Slf4j;
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
        String channel = request.getChannel();
        Integer versionCode = request.getVersionCode();

        AppVersionResponse appVersionResponse = new AppVersionResponse();
        // 字典配置
        Dict dict = dictService.getDictByCode(channel);

        AppVersionModel appVersionModel = JSONObject.parseObject(dict.getDicLabel(), AppVersionModel.class);
        // 当前用户版本号
        String currentUserVersion = RequestUtil.getGlobalHead().getVersion();
        // 后台版本号
        String currentAppVersion = dict.getDicValue();
        // 当前用户版本号比后台配置版本号大则不提示更新
        if (currentUserVersion.compareTo(currentAppVersion) >= 0) {
            appVersionResponse.setIsUpdate(AppVersionEnum.NO_NEED_TO_UPDATE.getCode());
            appVersionResponse.setIsForce(AppVersionEnum.NO_MANDATORY_UPDATES_ARE_REQUIRED.getCode());
        }
        // 否则按后台配置提示更新
        else {
            appVersionResponse.setIsUpdate(AppVersionEnum.NEED_TO_BE_UPDATED.getCode());
            appVersionResponse.setIsForce(AppVersionEnum.NO_MANDATORY_UPDATES_ARE_REQUIRED.getCode());
            // 最低控制版本大于当前版本则强制更新
            if (appVersionModel.getMinimum_version() > versionCode) {
                appVersionResponse.setIsForce(AppVersionEnum.NEED_TO_FORCE_AN_UPDATE.getCode());
            }
        }

        appVersionResponse.setVersion(currentAppVersion);
        appVersionResponse.setPath(appVersionModel.getUpdate_url());
        return appVersionResponse;
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
            newsResponse.setSummary(news.getSummary());
            newsResponse.setToUrl(systemProp.getH5MessageUrl() + news.getId() + "&type=news");
            newsResponse.setCreateTime(DateUtil.localDateTimeToTimestamp(news.getCreateTime()));
            newsResponse.setId(news.getId());
        }

        return newsResponse;
    }

    public List<CheckLoanResponse> checkLoan() {
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
        // 调风控接口
        riskService.checkRiskEnableBorrow(user.getMobileAccount(), imei,"0");

        return checkLoanResponseList;
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