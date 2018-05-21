package com.hzed.easyget.application.service;

import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.model.Response;
import com.hzed.easyget.infrastructure.utils.FaJsonUtil;
import com.hzed.easyget.infrastructure.utils.RegexUtils;
import com.hzed.easyget.infrastructure.utils.SMSUtil;
import com.hzed.service.common.config.redis.RedisCacheService;
import com.hzed.service.common.consts.RedisConsts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 登录实现
 */
public class LoginService {
    @Autowired
    private static RedisCacheService redisCacheService;

    public  static void sendSmsCode(String mobile){
        if(StringUtils.isBlank(mobile) ) {
            Response.getSuccessResponse(new WarnException("-1", "手机号码不能为空"));
            return;
        }

        if(!RegexUtils.isMobileNum(mobile)) {
            Response.getSuccessResponse(new WarnException("-1", "手机号格式有误"));
            return;
        }
        //获取短信验证码
        Map<String ,String> codeMap = SMSUtil.sendCode(mobile);
        //保存到Redis
//        redisCacheService.setCache(ComConsts.SMS_CODE + RedisConsts.DIR_SPLIT + mobile, FaJsonUtil.objToString(repaymentData), 30 * 60L);


    }


}
