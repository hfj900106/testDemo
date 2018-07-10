package com.hzed.easyget.application.service;

import com.hzed.easyget.application.enums.BidProgressTypeEnum;
import com.hzed.easyget.application.enums.BidStatusEnum;
import com.hzed.easyget.application.enums.EnvEnum;
import com.hzed.easyget.application.service.product.ProductEnum;
import com.hzed.easyget.application.service.product.ProductFactory;
import com.hzed.easyget.application.service.product.model.AbstractProduct;
import com.hzed.easyget.controller.model.DictResponse;
import com.hzed.easyget.controller.model.PushBidCallbackRequest;
import com.hzed.easyget.controller.model.PushBidCallbackResponse;
import com.hzed.easyget.infrastructure.config.SystemProp;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.repository.BidRepository;
import com.hzed.easyget.infrastructure.repository.TempTableRepository;
import com.hzed.easyget.infrastructure.repository.UserRepository;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.SpringContextUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.Bid;
import com.hzed.easyget.persistence.auto.entity.BidProgress;
import com.hzed.easyget.persistence.auto.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hfj
 * @date 2018/6/9
 */
@Slf4j
@Service
public class CallbackService {

    @Autowired
    private TempTableRepository tempTableRepository;
    @Autowired
    private BidRepository bidRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SmsService smsService;

    public PushBidCallbackResponse pushBidCallback(PushBidCallbackRequest request) {
        Long bidId = request.getBidId();
        BigDecimal loanAmount = request.getLoanAmount();
        byte status = (byte) 0;
        boolean isPass;
        //通过
        if (ComConsts.RISK_4.equals(request.getResultCode())) {
            isPass = true;
            status = BidStatusEnum.AUDIT_PASS.getCode().byteValue();
            log.info("审核通过标id：{}，金额{}", bidId, loanAmount);
            // 发送短信
            sendSmsOfPushResult(bidId, isPass);
        }
        // 失败
        else if (ComConsts.RISK_3.equals(request.getResultCode())) {
            isPass = false;
            status = BidStatusEnum.AUDIT_FAIL.getCode().byteValue();
            log.info("审核失败标id：{}，原因{}", bidId, request.getMessage());
            // 发送短信
            sendSmsOfPushResult(bidId, isPass);
        }
        // 人审
        else if (ComConsts.RISK_2.equals(request.getResultCode())) {
            status = BidStatusEnum.MANMADE_ING.getCode().byteValue();
            log.info("人审标id：{}，原因{}", bidId, request.getMessage());
        }

        LocalDateTime dateTime = DateUtil.timestampToLocalDateTimeTo(request.getHandleTime());
        Bid bid = bidRepository.findById(bidId);
        if (ObjectUtils.isEmpty(bid)) {
            log.error("回调处理的标ID：{} 不存在标的表中", bidId);
        } else {
            AbstractProduct absProduct = ProductFactory.getProduct(ProductEnum.EasyGet).createProduct(loanAmount, bid.getPeriod());
            tempTableRepository.pushBidCallback(
                    Bid.builder().id(bidId).loanAmount(loanAmount).updateTime(LocalDateTime.now()).auditFee(absProduct.getHeadFee()).status(status).build(),
                    BidProgress.builder().id(IdentifierGenerator.nextId()).bidId(bidId).type(BidProgressTypeEnum.AUDIT.getCode().byteValue()).handleResult(request.getMessage())
                            .handleTime(dateTime).build(),
                    bidId);
        }
        // 成功或者失败都返回
        return PushBidCallbackResponse.getSuccessResponse();
    }

    /**
     * 审核成功或者失败短信通知用户
     */
    @Async
    public void sendSmsOfPushResult(Long bidId, boolean isPass) {
        // 获取标
        Bid bid = bidRepository.getUserIdByBidId(bidId);
        // 获取用户
        User user = userRepository.findById(bid.getUserId());
        if (ObjectUtils.isEmpty(user)) {
            throw new WarnException(BizCodeEnum.USER_NOT_EXIST);
        }
        // 手机号
        String mobile = user.getMobileAccount();
        if (StringUtils.isBlank(mobile)) {
            throw new WarnException(BizCodeEnum.USER_NOT_EXIST);
        }
        // 打印日志
        log.info("标id：{}审核结果短信通知用户：{}", bidId, mobile);
        SystemProp systemProp = SpringContextUtil.getBean(SystemProp.class);
        DictService dictService = SpringContextUtil.getBean(DictService.class);
        List<DictResponse> smsContent;
        // 通过审核
        if (isPass) {
            smsContent = dictService.getDictByDicCodeAndLanguage(ComConsts.SMS_CONTENT_3, systemProp.getLocal());
        }
        // 不通过
        else {
            smsContent = dictService.getDictByDicCodeAndLanguage(ComConsts.SMS_CONTENT_2, systemProp.getLocal());
        }
        if (ObjectUtils.isEmpty(smsContent)) {
            log.error("没有配置短信模板");
            throw new WarnException(BizCodeEnum.UNKNOWN_EXCEPTION);
        }
        String dicValue = smsContent.get(0).getDictValue();
        // 获取验证码
        String code = smsService.getCode();
        // 替换验证码
        String content = StringUtils.replace(dicValue, "{0}", code);
        // 短信标识
        Long smsId = IdentifierGenerator.nextId();

        if (!EnvEnum.isTestEnv(systemProp.getEnv())) {
            // 非测试环境发送短信
            smsService.sendSms(mobile, content, String.valueOf(smsId));
        }
        // 保存短信记录
        smsService.saveSmsLog(smsId, content, mobile, (byte) 2, "审核结果短信通知用户");

    }

}
