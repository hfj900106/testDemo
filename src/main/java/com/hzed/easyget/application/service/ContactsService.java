package com.hzed.easyget.application.service;

import com.hzed.easyget.controller.model.ContactsRequest;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.utils.JwtUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 通讯录验证
 * @author hfj
 * @date 2018/5/22
 */
@Slf4j
@Service
public class ContactsService {

    public  void contacts(ContactsRequest request){
        //获取token
        String token = RequestUtil.getGlobalHead().getToken();
        //解析token，获取user
        GlobalUser user2 = JwtUtil.verify(token, GlobalUser.class);



    }


}