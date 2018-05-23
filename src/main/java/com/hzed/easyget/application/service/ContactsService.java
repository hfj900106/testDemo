package com.hzed.easyget.application.service;

import com.hzed.easyget.controller.model.ContactsRequest;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.utils.JwtUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 通讯录授权
 * @author hfj
 * @date 2018/5/22
 */
@Slf4j
@Service
public class ContactsService {

    @Autowired
    private AuthService authService;

    public  void authContacts(ContactsRequest request){
        //获取token
        String token = RequestUtil.getGlobalHead().getToken();
        //解析token，获取user
        GlobalUser user = JwtUtil.verify(token, GlobalUser.class);

        String content = request.getContacts();
        //写入用户授权信息返回值
        //写入用户授权信息返回值
        authService.insertAuthContent(content,"通讯录授权");
        //写入用户授权信息
        authService.insertUserAuthStatus( user.getUserId(),"通讯录授权");

    }

}