package com.hzed.easyget.application.service;

import com.google.common.collect.Lists;
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

    public List<AuthStatusResponse> getAuthStatus() {

        List<AuthStatusResponse> authStatusList = Lists.newArrayList();

        GlobalUser globalUser = RequestUtil.getGlobalUser();
        Long userId = globalUser.getUserId();
        List<UserAuthStatus> userAuthStatus = authStatusRepository.getAuthStatusByUserId(userId);

        for (UserAuthStatus uas : userAuthStatus) {
            AuthStatusResponse authStatusResponse = new AuthStatusResponse();
            authStatusResponse.setCode(uas.getAuthCode());
            authStatusResponse.setStatus(String.valueOf(uas.getAuthStatus()));
            authStatusList.add(authStatusResponse);
        }

        return authStatusList;
    }

}