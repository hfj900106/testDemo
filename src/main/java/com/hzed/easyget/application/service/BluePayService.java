package com.hzed.easyget.application.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hzed.easyget.application.enums.EnvEnum;
import com.hzed.easyget.application.enums.MobileEnum;
import com.hzed.easyget.application.enums.RepayMentEnum;
import com.hzed.easyget.controller.model.CheckAccountRequest;
import com.hzed.easyget.controller.model.LoanTransactionRequest;
import com.hzed.easyget.controller.model.PaymentCodeRequest;
import com.hzed.easyget.controller.model.RepaymentCompleRequest;
import com.hzed.easyget.infrastructure.config.PayProp;
import com.hzed.easyget.infrastructure.config.SystemProp;
import com.hzed.easyget.infrastructure.config.rest.RestService;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.model.PayResponse;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.infrastructure.utils.id.IDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * bluepay交易相关
 *
 * @author zhangrl
 * @date 2018/6/4
 */
@Service
@Slf4j
public class BluePayService {

    @Autowired
    private RestService restService;
    @Autowired
    private PayProp prop;
    @Autowired
    private SystemProp systemProp;

    private static final List<String> LISTCODE = Arrays.asList(BizCodeEnum.PROCESS_LENDING.getCode(), BizCodeEnum.SUCCESS.getCode(), BizCodeEnum.REPAYMENTS.getCode());

    /**
     * 获取还款码
     *
     * @param mode   交易方式
     * @param amount 交易金额
     * @return
     */
    public PayResponse bluePaymentCode(String mode, BigDecimal amount, String paymentId) {
        //组装请求信息
        String env = systemProp.getEnv();
        String mobile = RequestUtil.getGlobalUser().getMobile();
        boolean flag = StringUtils.substring(mobile, 0, 1).equals("0");
        if (flag) {
            mobile = StringUtils.substring(mobile, 1, mobile.length());
        }
        String payeeMsisdn = EnvEnum.isTestEnv(env) ? MobileEnum.CHINA.getMobile() + mobile : MobileEnum.IDR.getMobile() + mobile;
        PaymentCodeRequest paymentRequest = new PaymentCodeRequest();
        paymentRequest.setBankType(RepayMentEnum.getBank(mode));
        paymentRequest.setTransactionId(paymentId);
        paymentRequest.setPrice(amount);
        paymentRequest.setMsisdn(payeeMsisdn);
        paymentRequest.setPayType(RepayMentEnum.getBlue(mode));
        // OTC方式不可传银行类型，否则报错
        if (mode.equals(RepayMentEnum.OTC.getMode())) {
            paymentRequest.setBankType(null);
        }
        log.info("========获取还款码===========请求bluePay开始========================");
        log.info("获取还款码，bluepay请求地址{}，参数：{}", prop.getAbsGetPaymentCodeUrl(), JSON.toJSONString(paymentRequest));
        String result = restService.doPostJson(prop.getAbsGetPaymentCodeUrl(), JSON.toJSONString(paymentRequest));
        log.info("获取还款码，bluepay返回数据：{}", result);
        log.info("========获取还款码===========请求bluePay结束========================");
        PayResponse response = JSON.parseObject(result, PayResponse.class);
        if (!response.getCode().equals(BizCodeEnum.SUCCESS.getCode())) {
            throw new ComBizException(BizCodeEnum.PAYMENTCODE_ERROR);
        }
        return response;
    }

    /**
     * 测试还款接口
     *
     * @param request app请求后台实体
     * @return 返回状态
     */
    public PayResponse testRepayment(RepaymentCompleRequest request) {
        request.setRequestNo(IDGenerator.nextSeqNo());
        log.info("测试还款接口请求地址{},报文：{}", prop.getAbsReceiverTransactionUrl(), JSON.toJSONString(request));
        String result = restService.doPostJson(prop.getAbsReceiverTransactionUrl(), JSON.toJSONString(request));
        log.info("测试还款接口返回报文：{}", result);
        return JSON.parseObject(result, PayResponse.class);
    }

    /**
     * 放款请求接口
     *
     * @param request bluepay请求对象
     * @return 返回请求结果
     */
    public PayResponse loanTransaction(LoanTransactionRequest request) {
        String env = systemProp.getEnv();
        String mobile = request.getPayeeMsisdn();
        if (mobile.startsWith("0")) {
            mobile = StringUtils.substring(mobile, 1, mobile.length());
        }
        String payeeMsisdn = EnvEnum.isTestEnv(env) ? MobileEnum.CHINA.getMobile() + mobile : MobileEnum.IDR.getMobile() + mobile;
        request.setPayeeMsisdn(payeeMsisdn);
        log.info("========放款请求接口===========请求bluePay开始========================");
        log.info("请求放款地址：{}", prop.getAbsLoanTransactionUrl());
        log.info("请求报文：{}", JSONObject.toJSONString(request));
        String result = restService.doPostJson(prop.getAbsLoanTransactionUrl(), JSON.toJSONString(request));
        log.info("返回报文：{}", result);
        log.info("========放款请求接口===========请求bluePay结束========================");
        PayResponse response = JSON.parseObject(result, PayResponse.class);
        // 判断返回状态 0000 0001 0002
        if (!LISTCODE.contains(response.getCode())) {
            log.error("请求放款服务放款失败，请求报文：{}，返回报文：{}", JSONObject.toJSONString(request), result);
        }
        return response;
    }

    /**
     * 校验用户银行卡信息
     */
    public PayResponse checkAccount(String bankName,String accountNo, String phoneNum, String CustomerName) {

        CheckAccountRequest checkAccountRequest = new CheckAccountRequest();
        checkAccountRequest.setPhoneNum(phoneNum);
        checkAccountRequest.setCustomerName(CustomerName);
        checkAccountRequest.setAccountNo(accountNo);
        checkAccountRequest.setBankName(bankName);
        checkAccountRequest.setTransactionId(IDGenerator.nextSeqNo());

        log.info("========校验用户银行卡信息===========请求bluePay开始========================");
        log.info("请求地址：{}", prop.getAbsCheckAccountUrl());
        log.info("请求报文：{}", JSONObject.toJSONString(checkAccountRequest));
        String result = restService.doPostJson(prop.getAbsCheckAccountUrl(), JSON.toJSONString(checkAccountRequest));
        log.info("返回报文：{}", result);
        log.info("========校验用户银行卡信息===========请求bluePay结束========================");
        PayResponse response = JSON.parseObject(result, PayResponse.class);
        if (!BizCodeEnum.SUCCESS.getCode().equals(response.getCode())) {
            log.error("校验用户银行卡信息，调用服务异常");
            throw new WarnException(BizCodeEnum.CHECK_ACCOUNT_ERROR);
        }
        return response;
    }
}
