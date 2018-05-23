package com.hzed.easyget.application.service;

import com.hzed.easyget.controller.model.ContactsRequest;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.repository.AuthContentRepository;
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
    @Autowired
    private AuthContentRepository authContentRepository;

    public  void authContacts(ContactsRequest request){
        GlobalUser user = RequestUtil.getGlobalUser();

        String content = request.getContacts();
        //写入用户授权信息返回值
        //写入用户授权信息返回值
        authService.insertAuthContent(content,"通讯录授权");
        //写入用户授权信息
        authService.insertUserAuthStatus( user.getUserId(),"通讯录授权");



    }

}