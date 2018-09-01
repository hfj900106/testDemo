package com.hzed.easyget.application.service;

import com.hzed.easyget.infrastructure.config.SystemProp;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.repository.BidRepository;
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

import java.text.MessageFormat;

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
        String contentCode;
        String titleCode;
        // 通过审核
        String local = systemProp.getLocal();
        if (isPass) {
            titleCode = ComConsts.MESSAGE_TITLE_2;
            contentCode = ComConsts.SMS_CONTENT_3;
        } else {
            // 不通过
            titleCode = ComConsts.MESSAGE_TITLE_1;
            contentCode = ComConsts.SMS_CONTENT_2;
        }
        String title = dictService.getDictByCodeAndLanguage(titleCode, local).getDicValue();
        String content = dictService.getDictByCodeAndLanguage(contentCode, local).getDicValue();
        // 替换验证码
        content = MessageFormat.format(content, user.getRealName());
        // 发送及保存短信
        smsService.sendDefaultSms(mobile, content, "审核结果短信通知用户");
        // 保存信息记录
        messageRepository.addUserMessage(user.getId(), title, content, "审核结果短信通知用户");
    }


}