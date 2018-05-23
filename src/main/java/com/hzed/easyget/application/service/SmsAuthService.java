package com.hzed.easyget.application.service;

import com.hzed.easyget.controller.model.MessagesRequest;
import com.hzed.easyget.controller.model.SmsAuthRequest;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.utils.JwtUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 运营商认证
 * @author hfj
 * @date 2018/5/23
 */
@Slf4j
@Service
public class SmsAuthService {

    public  void smsAuth(SmsAuthRequest request){
        //获取token
        String token = RequestUtil.getGlobalHead().getToken();
        //解析token，获取user
        GlobalUser user = JwtUtil.verify(token, GlobalUser.class);
        //获取手机号
        String mobile = request.getMobile();
        //获取服务密码
        String serverKey = request.getServerKey();
        //根据用户名更新用户运营商服务密码



    }

}