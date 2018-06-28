package com.hzed.easyget.application.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hzed.easyget.infrastructure.config.RiskProp;
import com.hzed.easyget.infrastructure.config.redis.RedisService;
import com.hzed.easyget.infrastructure.config.rest.RestService;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.consts.RedisConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.model.RiskResponse;
import com.hzed.easyget.infrastructure.utils.AesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.hzed.easyget.infrastructure.utils.RequestUtil.getGlobalUser;

/**
 * 与风控交互
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
     * @param contacts
     * @param callRecord
     * @param source
     * @return
     */
    public RiskResponse authContacts(Object contacts,Object callRecord,Integer source){
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
        log.info("请求风控URL：{},参数：{}",url,JSONObject.toJSONString(map));
        RiskResponse response = restService.postJson(url, map, RiskResponse.class);
        log.info("风控返回数据：{}" ,JSONObject.toJSONString(response));
        return response;
    }

    public RiskResponse authMessages(Object messages,Integer source){
        GlobalUser user = getGlobalUser();
        Long timeStamp = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>(16);
        map.put("sign", AesUtil.aesEncode(user.getUserId(), timeStamp));
        map.put("userId", user.getUserId());
        map.put("timeStamp", timeStamp);
        map.put("sms", messages);
        map.put("source", source);
        String url = riskProp.getMessagesUrl();
        log.info("请求风控URL：{},参数：{}",url,JSONObject.toJSONString(map));
        RiskResponse response = restService.postJson(url, map, RiskResponse.class);
        log.info("风控返回数据：{}" ,JSONObject.toJSONString(response));
        return response;
    }

    public void operatorSendSmsCode(Integer source){
        GlobalUser user = getGlobalUser();
        Long timeStamp = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>(16);
        map.put("sign", AesUtil.aesEncode(user.getUserId(), timeStamp));
        map.put("userId", user.getUserId());
        map.put("timeStamp", timeStamp);
        map.put("source", source);
        String url = riskProp.getOperatorSendSmsCodeUrl();
        log.info("请求风控URL：{},参数：{}",url,JSONObject.toJSONString(map));
        RiskResponse response = restService.postJson(url, map, RiskResponse.class);
        log.info("风控返回数据：{}" ,JSONObject.toJSONString(response));
        if (null == response) {
            saService.saOperator(user, false, BizCodeEnum.ERROR_RISK__RESULT.getMessage());
            throw new WarnException(BizCodeEnum.ERROR_RISK__RESULT);
        }
        if (!response.getHead().getStatus().equals(ComConsts.RISK_OK)) {
            saService.saOperator(user, false, BizCodeEnum.FAIL_AUTH.getMessage());
            throw new WarnException(BizCodeEnum.FAIL_AUTH);
        }
        if (((LinkedHashMap) response.getBody()).get(ComConsts.RISK_CODE).equals(ComConsts.RISK_OPERATOR_HAVE_AUTH)) {
            //已经认证过
            saService.saOperator(user, false, BizCodeEnum.HAVE_AUTH_RISK.getMessage());
            throw new WarnException(BizCodeEnum.HAVE_AUTH_RISK);
        }
        if (((LinkedHashMap) response.getBody()).get(ComConsts.RISK_CODE).equals(ComConsts.RISK_OPERATOR_PARAMS_ERROR)) {
            //认证数据不正确，数据从数据库取，一般不出现
            saService.saOperator(user, false, BizCodeEnum.PARAMS_AUTH_RISK.getMessage());
            throw new WarnException(BizCodeEnum.PARAMS_AUTH_RISK);
        }
        if (((LinkedHashMap) response.getBody()).get(ComConsts.RISK_CODE).equals(ComConsts.RISK_OPERATOR_ERROR)) {
            //认证失败
            saService.saOperator(user, false, BizCodeEnum.FAIL_AUTH.getMessage());
            throw new WarnException(BizCodeEnum.FAIL_AUTH);
        }
    }


    public RiskResponse operatorAuth(String smsCode){
        GlobalUser user = getGlobalUser();
        Long timeStamp = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>(16);
        map.put("sign", AesUtil.aesEncode(user.getUserId(), timeStamp));
        map.put("userId", user.getUserId());
        map.put("timeStamp", timeStamp);
        map.put("smsCode", smsCode);
        String url = riskProp.getOperatorAuthUrl();
        log.info("请求风控URL：{},参数：{}",url,JSONObject.toJSONString(map));
        RiskResponse response = restService.postJson(url, map, RiskResponse.class);
        log.info("风控返回数据：{}" ,JSONObject.toJSONString(response));
        if (((LinkedHashMap) response.getBody()).get(ComConsts.RISK_CODE).equals(ComConsts.RISK_OPERATOR_FREQ)) {
            //认证频繁，要等一分钟再认证
            saService.saOperator(user, false, BizCodeEnum.FREQUENTLY_AUTH_RISK.getMessage());
            throw new WarnException(BizCodeEnum.FREQUENTLY_AUTH_RISK);
        }
        if (((LinkedHashMap) response.getBody()).get(ComConsts.RISK_CODE).equals(ComConsts.RISK_OPERATOR_ERROR)) {
            //认证失败，删除重发标志
            saService.saOperator(user, false, BizCodeEnum.FAIL_AUTH.getMessage());
            redisService.clearCache(RedisConsts.IDENTITY_SMS_CODE_SEND + RedisConsts.SPLIT + user.getUserId());
            throw new WarnException(BizCodeEnum.FAIL_AUTH);
        }
        if (((LinkedHashMap) response.getBody()).get(ComConsts.RISK_CODE).equals(ComConsts.RISK_OPERATOR_HAVE_SEND)) {
            //验证码错误，需要输入验证码，后台自动让第三方接口重发验证码
            saService.saOperator(user, false, BizCodeEnum.NEED_SMS_AUTH_RISK.getMessage());
            throw new WarnException(BizCodeEnum.NEED_SMS_AUTH_RISK);
        }
        if (((LinkedHashMap) response.getBody()).get(ComConsts.RISK_CODE).equals(ComConsts.RISK_OPERATOR_HAVE_AUTH)) {
            //已经认证过
            saService.saOperator(user, false, BizCodeEnum.HAVE_AUTH_RISK.getMessage());
            throw new WarnException(BizCodeEnum.HAVE_AUTH_RISK);
        }
        return response;
    }

    public RiskResponse idCardRecognition(String idCardBase64ImgStr){
        GlobalUser user = getGlobalUser();
        Long timeStamp = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>(16);
        map.put("sign", AesUtil.aesEncode(user.getUserId(), timeStamp));
        map.put("userId", user.getUserId());
        map.put("timeStamp", timeStamp);
        map.put("imageFile", idCardBase64ImgStr);

        String url = riskProp.getIdCardRecognitionUrl();
        log.info("请求风控URL：{},参数：{}",url,JSONObject.toJSONString(map));
        RiskResponse response = restService.postJson(url, map, RiskResponse.class);
        log.info("风控返回数据：{}", JSONObject.toJSONString(response));
        if (null == response) {
            throw new WarnException(BizCodeEnum.ERROR_RISK__RESULT);
        }
        if (!response.getHead().getStatus().equals(ComConsts.RISK_OK)) {
            throw new WarnException(BizCodeEnum.FAIL_FACE_RECOGNITION);
        }
        return response;
    }

    public void faceRecognition(String faceBase64ImgStr){
        GlobalUser user = getGlobalUser();
        Long timeStamp = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>(16);
        map.put("sign", AesUtil.aesEncode(user.getUserId(), timeStamp));
        map.put("userId", user.getUserId());
        map.put("timeStamp", timeStamp);
        map.put("imageFile", faceBase64ImgStr);

        String url = riskProp.getFaceRecognitionUrl();
        log.info("请求风控URL：{},参数：{}",url,JSONObject.toJSONString(map));
        RiskResponse response = restService.postJson(url, map, RiskResponse.class);
        log.info("风控返回数据：{}", JSONObject.toJSONString(response));
        if (null == response) {
            throw new WarnException(BizCodeEnum.ERROR_RISK__RESULT);
        }
        if (!response.getHead().getStatus().equals(ComConsts.RISK_OK)) {
            throw new WarnException(BizCodeEnum.FAIL_FACE_RECOGNITION);
        }
    }

    public void identityInfoAuth(){
        GlobalUser user = getGlobalUser();
        Long timeStamp = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>(16);
        map.put("sign", AesUtil.aesEncode(user.getUserId(), timeStamp));
        map.put("userId", user.getUserId());
        map.put("timeStamp", timeStamp);

        String url = riskProp.getIdentityInfoUrl();
        log.info("请求风控URL：{},参数：{}",url,JSONObject.toJSONString(map));
        RiskResponse response = restService.postJson(url, map, RiskResponse.class);
        log.info("风控返回数据：{}" ,JSONObject.toJSONString(response));
        if (null == response) {
            throw new WarnException(BizCodeEnum.ERROR_RISK__RESULT);
        }
        if (!response.getHead().getStatus().equals(ComConsts.RISK_OK)) {
            throw new WarnException(BizCodeEnum.FAIL_AUTH);
        }
    }


    public RiskResponse checkRiskEnableBorrow(String mobile, String imei) {
        Map<String, String> paramMap = Maps.newHashMap();
        paramMap.put("mobile", mobile);
        paramMap.put("imei", imei);
        log.info("查询风控是否有贷款规则请求报文：{}", paramMap);
        RiskResponse response = restService.postJson(riskProp.getAbsCheckRiskEnableBorrowUrl(), paramMap, RiskResponse.class);
        log.info("查询风控是否有贷款规则返回报文：{}", response);
        return response;

    }
}


