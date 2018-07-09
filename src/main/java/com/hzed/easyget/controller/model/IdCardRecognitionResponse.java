package com.hzed.easyget.controller.model;

import lombok.Data;

/**
 * 返回身份证识别数据
 * @author hfj
 * @date 2018/6/4
 */
@Data
public class IdCardRecognitionResponse {
     private  String idNumber ;
     private  Integer gender ;
     private  String name ;
     private  String birthday ;

}
