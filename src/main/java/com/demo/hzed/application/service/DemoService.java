package com.demo.hzed.application.service;

import com.demo.hzed.infrastructure.repository.UserRepository;
import com.demo.hzed.persistence.auto.entity.User;
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

    public User getUserByMobileAndIdcard(String mobile, String idCard) {
        return userRepository.findByMobileAndIdcard(mobile, idCard);
    }
}