package com.hzed.easyget.application.service;

import com.alibaba.fastjson.JSON;
import com.hzed.easyget.controller.model.LoanTransactionRequest;
import com.hzed.easyget.controller.model.ReceiverTransactionRequest;
import com.hzed.easyget.infrastructure.config.ThirdPartyProp;
import com.hzed.easyget.infrastructure.config.rest.RestService;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.model.Response;
import com.hzed.easyget.infrastructure.utils.FaJsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 交易相关
 * 放款和收款
 *
 * @author wuchengwu
 * @data 2018/6/9
 */
@Slf4j
@Service
public class TransactionService {

    @Autowired
    private RestService restService;
    @Autowired
    private ThirdPartyProp prop;

    /**
     * 放款
     */
    public Response loanTransaction(LoanTransactionRequest request) {

        log.info("支付放款接口请求报文：{}", JSON.toJSONString(request));
        Response response = restService.postJson(prop.getLoanTransactionUrl(), request, Response.class);
        log.info("支付放款接口返回报文：{}", JSON.toJSONString(response));
        if (!BizCodeEnum.SUCCESS.equals(response.getCode())) {

            throw new ComBizException(BizCodeEnum.LOAN_TRANSACTION_ERROR, response.getMessage());
        }
        return Response.getSuccessResponse();
    }


    /**
     * 收款
     */
    public Response receiverTransaction(ReceiverTransactionRequest request) {

        log.info("支付收款接口请求报文：{}", FaJsonUtil.objToString(request));
        Response response = restService.postJson(prop.getReceiverTransactionUrl(), request, Response.class);
        log.info("支付收款接口返回报文：{}", FaJsonUtil.objToString(response));
        if (!BizCodeEnum.SUCCESS.equals(response.getCode())) {

            throw new ComBizException(BizCodeEnum.RECEIVER_TRANSACTION_ERROR, response.getMessage());
        }
        return Response.getSuccessResponse();
    }
}