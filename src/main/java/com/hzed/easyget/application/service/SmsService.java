package com.hzed.easyget.application.service;

import com.hzed.easyget.infrastructure.config.SystemProp;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.repository.DictRepository;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.SmsUtils;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zhangrl
 * @date 2018/6/7
 */

@Slf4j
@Service
public class SmsService {
    @Autowired
    private SystemProp systemProp;
    @Autowired
    private DictRepository dictRepository;
    @Autowired
    private ComService comService;

    /**
     * 部分结清/全部结清 短信通知
     *
     * @param repaymentAmount 本次还款金额
     * @param mobile          手机号
     * @param bidId           标的id 结清不传
     */
    @Async
    public void repaymentNotice(BigDecimal repaymentAmount, String mobile, Long bidId) {
        String local = systemProp.getLocal();
        // 默认全部结清
        String smsCode = ComConsts.SMS_CONTENT_5;
        // 默认代还总额0
        BigDecimal balance = BigDecimal.valueOf(0);
        if (!ObjectUtils.isEmpty(bidId)) {
            smsCode = ComConsts.SMS_CONTENT_6;
            // 获取剩余代还总额
            balance = comService.getBidNoRepayFee(bidId, LocalDateTime.now());
        }
        String content = dictRepository.findByCodeAndLoacl(local, smsCode).getDicValue()
                .replaceAll("TTTT", DateUtil.localDateTimeToStr(LocalDateTime.now(), DateUtil.FORMAT6))
                .replaceAll("YYYY", repaymentAmount.toString())
                .replaceAll("RRRR", balance.toString());
        SmsUtils.sendSms(mobile, content, IdentifierGenerator.nextSeqNo());
        log.info("用户还款短信通知，手机号码：{},短信类容：{}", mobile, content);
    }
}
