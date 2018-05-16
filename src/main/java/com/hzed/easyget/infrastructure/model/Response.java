package com.hzed.easyget.infrastructure.model;

import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import lombok.Data;

@Data
public class Response<T> {
    private String code;

    private String message;

    private T data;

    public Response(String errorCode, String message) {
        this.code = errorCode;
        this.message = message;
    }

    public Response(String errorCode, String message, T data) {
        this.code = errorCode;
        this.message = message;
        this.data = data;
    }

    public static <T> Response getSuccessResponse(T data) {
        return new Response(BizCodeEnum.SUCCESS.getCode(), BizCodeEnum.SUCCESS.getMsg(), data);
    }

    public static <T> Response getSuccessResponse(T data, String message) {
        return new Response(BizCodeEnum.SUCCESS.getCode(), message, data);
    }

    public static Response getSuccessResponse() {
        return getSuccessResponse(null);
    }

    public static Response getFailResponse() {
        Response response = new Response(BizCodeEnum.UNKNOWN_EXCEPTION.getCode(), BizCodeEnum.UNKNOWN_EXCEPTION.getMsg());
        return response;
    }

    public static Response getFailResponse(String errorMsg) {
        Response response = new Response(BizCodeEnum.UNKNOWN_EXCEPTION.getCode(), errorMsg);
        return response;
    }

}
