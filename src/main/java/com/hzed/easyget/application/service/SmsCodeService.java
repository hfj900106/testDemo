package com.hzed.easyget.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 验证码校验
 * @author wuchengwu
 * @date 2018/5/21
 */
@Slf4j
@Service
public class SmsCodeService {

    public static boolean cheSmsCode(String mobile, String smsCode) {

       /* if(){//测试环境

        }else{*/
            //获取缓冲数据
            String cacheSmsCode = "hua";
            log.info("验证码");
            if(!smsCode.equals(cacheSmsCode)){

                return false;
            }
            //清理缓存

//        }

        return true;
    }
}