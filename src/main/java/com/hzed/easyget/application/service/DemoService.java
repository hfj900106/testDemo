package com.hzed.easyget.application.service;

import com.hzed.easyget.controller.model.UserRequest;
import com.hzed.easyget.infrastructure.repository.UserRepository;
import com.hzed.easyget.persistence.auto.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * demo
 *
 * @author guichang
 * @since 2018/4/3
 */

@Service
public class DemoService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByMobileAndIdcard(UserRequest request) {
        return userRepository.findByMobileAndIdcard(request.getMobile(), request.getIdCard());
    }
}