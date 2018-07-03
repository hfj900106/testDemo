package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.DictService;
import com.hzed.easyget.application.service.UserService;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.annotation.head.TokenIgnore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 我的
 *
 * @author hfj
 * @date 2018/5/25
 */
@Slf4j
@ExceptionAnno
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private DictService dictService;

    @ModuleFunc("我的")
    @PostMapping("/getAccountInfo")
    public UserResponse getAccountInfo() {
        return userService.getAccountInfo();
    }

    @ModuleFunc("交易记录")
    @PostMapping("/getTransactionRecord")
    public TransactionRecordResponse getTransactionRecord(@Valid @RequestBody TransactionRecordRequest request) {
        return userService.getTransactionRecord(request);
    }

    @TokenIgnore
    @ModuleFunc("联系客服")
    @PostMapping("/getCustomerService")
    public List<DictResponse> getCustomerService(@Valid @RequestBody DictRequest request){
        return dictService.getDictByModule(request.getModuleCode());
    }

}
