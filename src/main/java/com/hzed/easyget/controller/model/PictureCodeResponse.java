package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.websocket.Encoder;

/**
 * 返回验证码图片
 * @author hfj
 * @date 2018/6/4
 */
@Data
public class PictureCodeResponse {
     private  byte[] picture ;
     private String code;
}
