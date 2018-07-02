package com.hzed.easyget.application.service;

import com.alibaba.fastjson.JSON;
import com.hzed.easyget.application.enums.RepayMentEnum;
import com.hzed.easyget.controller.model.LoanTransactionRequest;
import com.hzed.easyget.controller.model.PaymentCodeRequest;
import com.hzed.easyget.controller.model.RepaymentCompleRequest;
import com.hzed.easyget.infrastructure.config.PayProp;
import com.hzed.easyget.infrastructure.config.rest.RestService;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.model.PayResponse;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.Bid;
import lombok.extern.slf4j.Slf4j;
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

    private static final String TIMEOUT = "TIMEOUT";
    private static final List<String> LISTCODE = Arrays.asList(BizCodeEnum.PROCESS_LENDING.getCode(), BizCodeEnum.SUCCESS.getCode(), BizCodeEnum.REPAYMENTS.getCode());


    /**
     * 获取还款码
     *
     * @param bid    标的
     * @param mode   交易方式
     * @param amount 交易金额
     * @return
     */
    public PayResponse bluePaymentCode(Bid bid, String mode, BigDecimal amount, String paymentId) {
        //组装请求信息
        PaymentCodeRequest paymentRequest = new PaymentCodeRequest();
        paymentRequest.setBankType(bid.getInBank().toLowerCase());
        paymentRequest.setTransactionId(paymentId);
        paymentRequest.setCardNo(bid.getInAccount());
        paymentRequest.setPrice(amount);
//        paymentRequest.setMsisdn(RequestUtil.getGlobalUser().getMobile());
        paymentRequest.setMsisdn("8615926633889");
        paymentRequest.setPayType(RepayMentEnum.getBlue(mode));
        // OTC方式不可传银行类型，否则报错
        if (mode.equals(RepayMentEnum.OTC.getMode())) {
            paymentRequest.setBankType(null);
        }
        log.info("获取还款码，bluepay请求地址{}，参数：{}", prop.getAbsGetPaymentCodeUrl(), JSON.toJSONString(paymentRequest));
        String result = restService.doPostJson(prop.getAbsGetPaymentCodeUrl(), JSON.toJSONString(paymentRequest));
        log.info("获取还款码，bluepay返回数据：{}", result);
        if (result.equalsIgnoreCase(TIMEOUT)) {
            throw new ComBizException(BizCodeEnum.PAYMENTCODE_ERROR);
        }
        PayResponse response=JSON.parseObject(result, PayResponse.class);
        if(!response.getCode().equals(BizCodeEnum.SUCCESS.getCode())){
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
        request.setRequestNo(IdentifierGenerator.nextSeqNo());
        log.info("测试还款接口请求地址{},报文：{}", prop.getAbsReceiverTransactionUrl(), JSON.toJSONString(request));
        String result = restService.doPostJson(prop.getAbsReceiverTransactionUrl(), JSON.toJSONString(request));
        log.info("测试还款接口返回报文：{}", result);
        if (result.equalsIgnoreCase(TIMEOUT)) {
            throw new ComBizException(BizCodeEnum.RECEIVER_TRANSACTION_ERROR);
        }
        return JSON.parseObject(result, PayResponse.class);
    }

    /**
     * 放款请求接口
     *
     * @param request bluepay请求对象
     * @return 返回请求结果
     */
    public PayResponse loanTransaction(LoanTransactionRequest request) {
        log.info("请求地址{},请求报文：{}", prop.getAbsLoanTransactionUrl(), JSON.toJSONString(request));
        String result = restService.doPostJson(prop.getAbsLoanTransactionUrl(), JSON.toJSONString(request));
        log.info("请求地址{},返回报文：{}", prop.getAbsLoanTransactionUrl(), result);
        if (TIMEOUT.equals(result)) {
            throw new ComBizException(BizCodeEnum.LOAN_TRANSACTION_ERROR);
        }
        PayResponse response = JSON.parseObject(result, PayResponse.class);
        //判断返回状态 0000 0001 0002
        if (!LISTCODE.contains(response.getCode())) {
            throw new ComBizException(BizCodeEnum.LOAN_TRANSACTION_ERROR);
        }
        return response;
    }


}
