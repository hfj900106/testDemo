package com.hzed.easyget.application.service;

import com.hzed.easyget.controller.model.AuthStatusResponse;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.repository.UserAuthStatusRepository;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.persistence.auto.entity.UserAuthStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author wuchengwu
 * @date 2018/5/23
 */
@Service
public class AuthStatusService {

    @Autowired
    private UserAuthStatusRepository authStatusRepository;

    public AuthStatusResponse getAuthSatus() {

        AuthStatusResponse authStatusResponse = new AuthStatusResponse();
        GlobalUser globalUser = RequestUtil.getGlobalUser();
        Long userId = globalUser.getUserId();
        List<UserAuthStatus> userAuthStatus = authStatusRepository.getAuthSattusByUserId(userId);
        if(userAuthStatus.isEmpty()){
            return authStatusResponse;
        }
        for(UserAuthStatus uas:userAuthStatus){

      //      AuthCodeEnum.ID_CARD.equals(uas.getAuthCode())? authStatusResponse.setAuthAddress(String.valueOf(uas.getAuthStatus())):0;
        }

        return authStatusResponse;
    }
}