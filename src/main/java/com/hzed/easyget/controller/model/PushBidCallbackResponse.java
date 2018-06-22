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
}
