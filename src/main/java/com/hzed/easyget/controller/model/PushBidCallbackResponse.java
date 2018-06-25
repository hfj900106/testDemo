package com.hzed.easyget.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author hfj
 * @date 2018/6/20
 */
@Data
@AllArgsConstructor
public class PushBidCallbackResponse {
    private String code;
    private String message;


    public static PushBidCallbackResponse getSuccessResponse() {
        return new PushBidCallbackResponse("0", "风控结果我已经收到了");
    }
}
