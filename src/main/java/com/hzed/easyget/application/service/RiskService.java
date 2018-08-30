package com.hzed.easyget.application.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hzed.easyget.application.enums.AuthStatusEnum;
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
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.hzed.easyget.infrastructure.utils.RequestUtil.getGlobalUser;

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
     *
     * @param contacts
     * @param callRecord
     * @param source
     * @return
     */
    public RiskResponse authContacts(Object contacts, Object callRecord, Integer source) {
        GlobalUser user = getGlobalUser();
        Long timeStamp = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>(16);
        map.put("sign", AesUtil.aesEncode(user.getUserId(), timeStamp));
        map.put("userId", user.getUserId());
        map.put("timeStamp", timeStamp);
        map.put("contacts", contacts);
        map.put("callRecord", callRecord);
        map.put("source", source);
        String url = riskProp.getContactsUrl();
        log.info("============================请求风控开始===============================");
        log.info("请求风控URL：{}", url);
        log.info("请求风控参数：{}", ComUtil.subJsonString(JSON.toJSONString(map), 500));

        RiskResponse response = restService.postJson(url, map, RiskResponse.class);
        log.info("风控返回数据：{}", JSONObject.toJSONString(response));
        log.info("============================请求风控结束===============================");
        return response;
    }

    public RiskResponse authMessages(Object messages, Integer source) {
        GlobalUser user = getGlobalUser();
        Long timeStamp = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>(16);
        map.put("sign", AesUtil.aesEncode(user.getUserId(), timeStamp));
        map.put("userId", user.getUserId());
        map.put("timeStamp", timeStamp);
        map.put("sms", messages);
        map.put("source", source);
        String url = riskProp.getMessagesUrl();
        log.info("============================请求风控开始===============================");
        log.info("请求风控URL：{}", url);
        log.info("请求风控参数：{}", ComUtil.subJsonString(JSON.toJSONString(map), 500));
        RiskResponse response = restService.postJson(url, map, RiskResponse.class);
        log.info("风控返回数据：{}", JSONObject.toJSONString(response));
        log.info("============================请求风控结束===============================");
        return response;
    }

    public void operatorSendSmsCode(Integer source) {
        GlobalUser user = getGlobalUser();
        Long timeStamp = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>(16);
        map.put("sign", AesUtil.aesEncode(user.getUserId(), timeStamp));
        map.put("userId", user.getUserId());
        map.put("timeStamp", timeStamp);
        map.put("source", source);
        String url = riskProp.getOperatorSendSmsCodeUrl();
        log.info("============================请求风控开始===============================");
        log.info("请求风控URL：{}", url);
        log.info("请求风控参数：{}", ComUtil.subJsonString(JSON.toJSONString(map), 500));
        RiskResponse response = restService.postJson(url, map, RiskResponse.class);
        log.info("风控返回数据：{}", JSONObject.toJSONString(response));
        log.info("============================请求风控结束===============================");
        if (ObjectUtils.isEmpty(response)) {
            saService.saOperator(user, false, BizCodeEnum.ERROR_RISK_RESULT.getMessage());
            throw new WarnException(BizCodeEnum.ERROR_RISK_RESULT);
        }
        if (!response.getHead().getStatus().equals(ComConsts.RISK_OK)) {
            saService.saOperator(user, false, BizCodeEnum.FAIL_AUTH.getMessage());
            throw new WarnException(BizCodeEnum.FAIL_AUTH);
        }
        Object bodyObj = response.getBody();
        if (ObjectUtils.isEmpty(bodyObj)) {
            throw new WarnException(BizCodeEnum.ERROR_RISK_RESULT);
        }

        Object codeObj = ((LinkedHashMap) bodyObj).get(ComConsts.RISK_CODE);
        if (codeObj.equals(ComConsts.RISK_OPERATOR_HAVE_AUTH)) {
            //已经认证过
            saService.saOperator(user, false, BizCodeEnum.HAVE_AUTH_RISK.getMessage());
            throw new WarnException(BizCodeEnum.HAVE_AUTH_RISK);
        }
        if (codeObj.equals(ComConsts.RISK_OPERATOR_PARAMS_ERROR)) {
            //认证数据不正确，数据从数据库取，一般不出现
            saService.saOperator(user, false, BizCodeEnum.PARAMS_AUTH_RISK.getMessage());
            throw new WarnException(BizCodeEnum.PARAMS_AUTH_RISK);
        }
        if (codeObj.equals(ComConsts.RISK_OPERATOR_ERROR)) {
            //认证失败
            saService.saOperator(user, false, BizCodeEnum.FAIL_AUTH.getMessage());
            throw new WarnException(BizCodeEnum.FAIL_AUTH);
        }
    }


    public RiskResponse operatorAuth(String smsCode) {
        GlobalUser user = getGlobalUser();
        Long timeStamp = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>(16);
        map.put("sign", AesUtil.aesEncode(user.getUserId(), timeStamp));
        map.put("userId", user.getUserId());
        map.put("timeStamp", timeStamp);
        map.put("smsCode", smsCode);
        String url = riskProp.getOperatorAuthUrl();
        log.info("============================请求风控开始===============================");
        log.info("请求风控URL：{}", url);
        log.info("请求风控参数：{}", ComUtil.subJsonString(JSON.toJSONString(map), 500));
        RiskResponse response = restService.postJson(url, map, RiskResponse.class);
        log.info("风控返回数据：{}", JSON.toJSONString(response));
        log.info("============================请求风控结束===============================");
        if (ObjectUtils.isEmpty(response)) {
            throw new WarnException(BizCodeEnum.ERROR_RISK_RESULT);
        }
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
        GlobalUser user = getGlobalUser();
        Long timeStamp = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>(16);
        map.put("sign", AesUtil.aesEncode(user.getUserId(), timeStamp));
        map.put("userId", user.getUserId());
        map.put("timeStamp", timeStamp);
        map.put("imageFile", request.getIdCardBase64ImgStr());
        map.put("bizToken", request.getBizToken());

        String url = riskProp.getIdCardRecognitionUrl();
        log.info("============================请求风控开始===============================");
        log.info("请求风控URL：{}", url);
        log.info("请求风控参数：{}", ComUtil.subJsonString(JSON.toJSONString(map), 500));
        RiskResponse response = restService.postJson(url, map, RiskResponse.class);
        log.info("风控返回数据：{}", JSON.toJSONString(response));
        log.info("============================请求风控结束===============================");
        if (ObjectUtils.isEmpty(response)) {
            throw new WarnException(BizCodeEnum.ERROR_RISK_RESULT);
        }
        if (!response.getHead().getStatus().equals(ComConsts.RISK_OK)) {
            throw new WarnException(BizCodeEnum.FAIL_FACE_RECOGNITION);
        }
        return response;
    }

    public void faceRecognition(String faceBase64ImgStr) {
        GlobalUser user = getGlobalUser();
        Long timeStamp = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>(16);
        map.put("sign", AesUtil.aesEncode(user.getUserId(), timeStamp));
        map.put("userId", user.getUserId());
        map.put("timeStamp", timeStamp);
        map.put("imageFile", faceBase64ImgStr);

        String url = riskProp.getFaceRecognitionUrl();
        log.info("============================请求风控开始===============================");
        log.info("请求风控URL：{}", url);
        log.info("请求风控参数：{}", ComUtil.subJsonString(JSON.toJSONString(map), 500));
        RiskResponse response = restService.postJson(url, map, RiskResponse.class);
        log.info("风控返回数据：{}", JSON.toJSONString(response));
        log.info("============================请求风控结束===============================");
        if (ObjectUtils.isEmpty(response)) {
            throw new WarnException(BizCodeEnum.ERROR_RISK_RESULT);
        }
        if (!response.getHead().getStatus().equals(ComConsts.RISK_OK)) {
            throw new WarnException(BizCodeEnum.FAIL_FACE_RECOGNITION);
        }

        // 识别成功缓存结果，用于判断用户身份认证时是否已经人脸识别成功 ，缓存两小时，识别成功后两小时内没有提交认证则重新识别
        redisService.setCache(RedisConsts.FACE + RedisConsts.SPLIT + user.getMobile(), "face", 7200L);
    }

    public Integer identityInfoAuth() {

        // 默认成功
        Integer status = AuthStatusEnum.HAS_AUTH.getCode();
        GlobalUser user = getGlobalUser();
        String face = redisService.getCache(RedisConsts.FACE + RedisConsts.SPLIT + user.getMobile());
        if (StringUtils.isBlank(face)) {
            log.info("用户{}需进行人脸识别", user.getMobile());
            throw new WarnException(BizCodeEnum.FAIL_AUTH);
        }
        Long timeStamp = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>(16);
        map.put("sign", AesUtil.aesEncode(user.getUserId(), timeStamp));
        map.put("userId", user.getUserId());
        map.put("timeStamp", timeStamp);
        map.put("mobile", getGlobalUser().getMobile());

        String url = riskProp.getIdentityInfoUrl();
        log.info("============================请求风控开始===============================");
        log.info("请求风控URL：{}", url);
        log.info("请求风控参数：{}", ComUtil.subJsonString(JSON.toJSONString(map), 500));
        RiskResponse response = restService.postJson(url, map, RiskResponse.class);
        log.info("风控返回数据：{}", JSON.toJSONString(response));
        log.info("============================请求风控结束===============================");
        if (ObjectUtils.isEmpty(response)) {
            throw new WarnException(BizCodeEnum.ERROR_RISK_RESULT);
        }

        // 认证失败
        if (!response.getHead().getStatus().equals(ComConsts.RISK_OK)) {
            status =  AuthStatusEnum.FAIl_AUTH.getCode();
        }
        // 成功后删除redis标志
        redisService.clearCache(RedisConsts.FACE + RedisConsts.SPLIT + user.getMobile());
        return status;
    }

    /**
     * 根据用户手机号，imei通过用户查询风控是否有贷款规则
     */
    public void checkRiskEnableBorrow(String mobile, String imei, String flag) {
        Map<String, String> paramMap = Maps.newHashMap();
        paramMap.put("mobile", mobile);
        paramMap.put("imei", imei);
        paramMap.put("flag", flag);
        String url = riskProp.getAbsCheckRiskEnableBorrowUrl();
        log.info("============================请求风控开始===============================");
        log.info("请求风控URL：{}", url);
        log.info("请求风控参数：{}", JSON.toJSONString(paramMap));
        RiskResponse response = restService.postJson(url, paramMap, RiskResponse.class);
        log.info("风控返回数据：{}", JSON.toJSONString(response));
        log.info("============================请求风控结束===============================");
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

    public void facebookAndIns(Long userId, String taskId, Integer source) {
        Long timeStamp = System.currentTimeMillis();
        Map<String, Object> map = Maps.newHashMap();
        map.put("sign", AesUtil.aesEncode(userId, timeStamp));
        map.put("userId", userId);
        map.put("timeStamp", timeStamp);
        map.put("taskId", taskId);
        map.put("source", source);
        String url = riskProp.getFacebookAndInsUrl();
        log.info("============================请求风控开始===============================");
        log.info("请求风控URL：{}", url);
        log.info("请求风控参数：{}", JSON.toJSONString(map));
        RiskResponse response = restService.postJson(url, map, RiskResponse.class);
        log.info("风控返回数据：{}", JSON.toJSONString(response));
        log.info("============================请求风控结束===============================");
        if (ObjectUtils.isEmpty(response)) {
            throw new WarnException(BizCodeEnum.ERROR_RISK_RESULT);
        }
        if (!response.getHead().getStatus().equals(ComConsts.RISK_OK)) {
            throw new WarnException(BizCodeEnum.FAIL_AUTH);
        }
    }

    /**
     * 个人信息推风控
     * @param list
     * @param userId
     */
    @Async
    public void pushProfile(List<Map<String,String>> list, Long userId){
        Long timeStamp = System.currentTimeMillis();
        Map<String, Object> map = Maps.newHashMap();
        map.put("sign", AesUtil.aesEncode(userId, timeStamp));
        map.put("userId", userId);
        map.put("timeStamp", timeStamp);
        map.put("contacts", list);
        String url = riskProp.getAuthPersonInfoUrl();
        log.info("请求风控URL：{},参数：{}", url, JSON.toJSONString(map));
        RiskResponse response = restService.postJson(url, map, RiskResponse.class);
        log.info("风控返回数据：{}", JSON.toJSONString(response));
    }

}


