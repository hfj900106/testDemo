package com.hzed.easyget.application.service;

import com.hzed.easyget.controller.model.DictResponse;
import com.hzed.easyget.infrastructure.config.SystemProp;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.repository.BidRepository;
import com.hzed.easyget.infrastructure.repository.DictRepository;
import com.hzed.easyget.infrastructure.repository.UserMessageRepository;
import com.hzed.easyget.infrastructure.repository.UserRepository;
import com.hzed.easyget.persistence.auto.entity.Bid;
import com.hzed.easyget.persistence.auto.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 异步操作专用
 *
 * @author guichang
 * @date 2018/8/3
 */
@Slf4j
@Service
public class AsyncService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SmsService smsService;
    @Autowired
    private UserMessageRepository messageRepository;
    @Autowired
    private DictRepository dictRepository;
    @Autowired
    private DictService dictService;
    @Autowired
    private SystemProp systemProp;
    @Autowired
    private BidRepository bidRepository;

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
        List<DictResponse> smsContent;
        String title;
        // 通过审核
        if (isPass) {
            title = dictRepository.findByCodeAndLanguage(ComConsts.MESSAGE_TITLE_2, systemProp.getLocal()).getDicValue();
            smsContent = dictService.getDictByDicCodeAndLanguage(ComConsts.SMS_CONTENT_3, systemProp.getLocal());
        }
        // 不通过
        else {
            title = dictRepository.findByCodeAndLanguage(ComConsts.MESSAGE_TITLE_1, systemProp.getLocal()).getDicValue();
            smsContent = dictService.getDictByDicCodeAndLanguage(ComConsts.SMS_CONTENT_2, systemProp.getLocal());
        }
        if (ObjectUtils.isEmpty(smsContent)) {
            log.error("没有配置短信模板");
            throw new WarnException(BizCodeEnum.UNKNOWN_EXCEPTION);
        }
        String dicValue = smsContent.get(0).getDictValue();
        // 替换验证码
        String content = StringUtils.replace(dicValue, "{0}", user.getRealName());
        // 发送及保存短信
        smsService.sendAndSaveSms(mobile, content, "审核结果短信通知用户");
        // 保存信息记录
        if (StringUtils.isBlank(title)) {
            log.error("没有配置信息title");
            throw new WarnException(BizCodeEnum.UNKNOWN_EXCEPTION);
        }
        messageRepository.addUserMessage(user.getId(), title, content, "审核结果短信通知用户");
    }



}