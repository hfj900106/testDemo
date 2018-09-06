package com.hzed.easyget.application.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.hzed.easyget.controller.model.IdCardRecognitionRequest;
import com.hzed.easyget.infrastructure.config.RiskProp;
import com.hzed.easyget.infrastructure.config.redis.RedisService;
import com.hzed.easyget.infrastructure.config.rest.RestService;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.consts.RedisConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.model.RiskResponse;
import com.hzed.easyget.infrastructure.utils.AesUtil;
import com.hzed.easyget.infrastructure.utils.ComUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 与风控交互
 *
 * @author hfj
 * @date 2018/6/28
 */
@Slf4j
@Service
public class RiskService {

    @Autowired
    private RiskProp riskProp;
    @Autowired
    private RestService restService;
    @Autowired
    private SaService saService;
    @Autowired
    private RedisService redisService;

    /**
     * 通讯录认证
     */
    public RiskResponse authContacts(Object contacts, Object callRecord) {
        Map<String, Object> map = getRiskMap(RequestUtil.getGlobalUser().getUserId());
        map.put("contacts", contacts);
        map.put("callRecord", callRecord);
        map.put("source", getSource());
        return getRiskResponse(map, riskProp.getAbsContactsUrl());
    }

    public RiskResponse authMessages(Object messages) {
        Map<String, Object> map = getRiskMap(RequestUtil.getGlobalUser().getUserId());
        map.put("sms", messages);
        map.put("source", getSource());
        return getRiskResponse(map, riskProp.getAbsMessagesUrl());
    }

    public RiskResponse operatorSendSmsCode() {
        GlobalUser user = RequestUtil.getGlobalUser();
        Map<String, Object> map = getRiskMap(user.getUserId());
        map.put("source", getSource());
        return getRiskResponse(map, riskProp.getAbsOperatorSendSmsCodeUrl());
    }

    public RiskResponse operatorAuth(String smsCode) {
        GlobalUser user = RequestUtil.getGlobalUser();
        Map<String, Object> map = getRiskMap(user.getUserId());
        map.put("smsCode", smsCode);
        RiskResponse response = getRiskResponse(map, riskProp.getAbsOperatorAuthUrl());
        Object bodyObj = response.getBody();

        if (ObjectUtils.isEmpty(bodyObj)) {
            throw new WarnException(BizCodeEnum.FAIL_IDCARD_RECOGNITION);
        }

        Object codeObj = ((LinkedHashMap) bodyObj).get(ComConsts.RISK_CODE);
        if (codeObj.equals(ComConsts.RISK_OPERATOR_FREQ)) {
            //认证频繁，要等一分钟再认证
            saService.saOperator(user, false, BizCodeEnum.FREQUENTLY_AUTH_RISK.getMessage());
            throw new WarnException(BizCodeEnum.FREQUENTLY_AUTH_RISK);
        }
        if (codeObj.equals(ComConsts.RISK_OPERATOR_ERROR)) {
            //认证失败，删除重发标志
            saService.saOperator(user, false, BizCodeEnum.FAIL_AUTH.getMessage());
            redisService.clearCache(RedisConsts.IDENTITY_SMS_CODE_SEND + RedisConsts.SPLIT + user.getUserId());
            throw new WarnException(BizCodeEnum.FAIL_AUTH);
        }
        if (codeObj.equals(ComConsts.RISK_OPERATOR_HAVE_SEND)) {
            //验证码错误，需要输入验证码，后台自动让第三方接口重发验证码
            saService.saOperator(user, false, BizCodeEnum.NEED_SMS_AUTH_RISK.getMessage());
            throw new WarnException(BizCodeEnum.NEED_SMS_AUTH_RISK);
        }
        if (codeObj.equals(ComConsts.RISK_OPERATOR_HAVE_AUTH)) {
            //认证通过
            saService.saOperator(user, true, BizCodeEnum.HAVE_AUTH_RISK.getMessage());
        }
        return response;
    }

    public RiskResponse idCardRecognition(IdCardRecognitionRequest request) {
        Map<String, Object> map = getRiskMap(RequestUtil.getGlobalUser().getUserId());
        map.put("imageFile", request.getIdCardBase64ImgStr());
        map.put("bizToken", request.getBizToken());
        map.put("ocrData", new String(request.getOcrData()));
        RiskResponse response = getRiskResponse(map, riskProp.getAbsIdCardRecognitionUrl());
        if (!response.getHead().getStatus().equals(ComConsts.RISK_OK)) {
            throw new WarnException(BizCodeEnum.FAIL_IDCARD_RECOGNITION);
        }
        return response;
    }

    public void faceRecognition(String faceBase64ImgStr) {
        Map<String, Object> map = getRiskMap(RequestUtil.getGlobalUser().getUserId());
        map.put("imageFile", faceBase64ImgStr);
        RiskResponse response = getRiskResponse(map, riskProp.getAbsFaceRecognitionUrl());
        if (!response.getHead().getStatus().equals(ComConsts.RISK_OK)) {
            throw new WarnException(BizCodeEnum.FAIL_FACE_RECOGNITION);
        }
    }

    public RiskResponse identityInfoAuth() {
        GlobalUser user = RequestUtil.getGlobalUser();
        Map<String, Object> map = getRiskMap(user.getUserId());
        map.put("mobile", user.getMobile());
        return getRiskResponse(map, riskProp.getAbsIdentityInfoUrl());
    }

    /**
     * 根据用户手机号，imei通过用户查询风控是否有贷款规则
     */
    public void checkRiskEnableBorrow(String mobile, String imei, String flag) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("mobile", mobile);
        map.put("imei", imei);
        map.put("flag", flag);
        RiskResponse response = getRiskResponse(map, riskProp.getAbsCheckRiskEnableBorrowUrl());
        if (ObjectUtils.isEmpty(response)) {
            throw new ComBizException(BizCodeEnum.ERROR_RISK_RESULT);
        }
        log.info("贷款资格校验风控返回报文：{}", response);
        String errorCode = response.getHead().getError_code();
        if (ObjectUtils.isEmpty(errorCode)) {
            return;
        }
        log.info("查询风控是否有贷款资格，风控返回被拒原因:{}，用户id:{}", response.getHead().getError_msg(), mobile);
        final String mk02 = "MK02";
        final String mk06 = "MK06";
        //每日通过超过数量
        if (mk02.equals(errorCode)) {
            throw new WarnException(BizCodeEnum.INSUFFICIENT_QUOTA);
        } else if (mk06.equals(errorCode)) {
            throw new WarnException(BizCodeEnum.BID_EXISTS);
        } else {
            throw new WarnException(BizCodeEnum.UN_LOAN_QUALIFICATION);
        }
    }

    public void facebookAndIns(Long userId, String taskId) {
        Map<String, Object> map = getRiskMap(userId);
        map.put("taskId", taskId);
        map.put("source", getSource());
        RiskResponse response = getRiskResponse(map, riskProp.getAbsFacebookAndInsUrl());
        if (ObjectUtils.isEmpty(response)) {
            throw new WarnException(BizCodeEnum.ERROR_RISK_RESULT);
        }
        if (!response.getHead().getStatus().equals(ComConsts.RISK_OK)) {
            throw new WarnException(BizCodeEnum.FAIL_AUTH);
        }
    }

    /**
     * 个人信息推风控
     */
    @Async
    public void pushProfile(List<Map<String, String>> list, Long userId) {
        Map<String, Object> map = getRiskMap(userId);
        map.put("contacts", list);
        getRiskResponse(map, riskProp.getAbsAuthPersonInfoUrl());
    }

    private Map<String, Object> getRiskMap(Long userId) {
        Long timeStamp = System.currentTimeMillis();
        Map<String, Object> map = Maps.newHashMap();
        map.put("sign", AesUtil.aesEncode(userId, timeStamp));
        map.put("userId", userId);
        map.put("timeStamp", timeStamp);
        return map;
    }

    private RiskResponse getRiskResponse(Map<String, Object> map, String url) {
        log.info("============================请求风控开始===============================");
        log.info("请求风控URL：{}", url);
        log.info("请求风控参数：{}", ComUtil.subJsonString(JSON.toJSONString(map), 300));
        RiskResponse response = restService.postJson(url, map, RiskResponse.class);
        log.info("风控返回数据：{}", ComUtil.subJsonString(JSON.toJSONString(response), 300));
        log.info("============================请求风控结束===============================");
        if (ObjectUtils.isEmpty(response)) {
            throw new WarnException(BizCodeEnum.ERROR_RISK_RESULT);
        }
        return response;
    }

    private int getSource() {
        return "android".equals(RequestUtil.getGlobalHead().getPlatform()) ? ComConsts.IS_ANDROID : ComConsts.IS_IOS;
    }

}


