package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 通讯录验证请求
 *
 * @author hfj
 * @date 2018/5/21
 */

@Data
public class ContactsRequest {
    @NotNull(message = "通讯录不能为空")
    private List<Contact> contacts;
    @NotNull(message = "通话记录不能为空")
    private List<CallLog> callLogs;

    @Data
    public static class Contact {
        @NotBlank(message = "通讯录姓名不能为空")
        private String name;
        @NotBlank(message = "通讯录手机号不能为空")
        private String mobile;
    }

    @Data
    public static class CallLog {
        @NotBlank(message = "通话记录姓名不能为空")
        private String name;
        @NotBlank(message = "通话记录手机号不能为空")
        private String mobile;
        @NotNull(message = "通话类型不能为空")
        private Integer type;
        @NotBlank(message = "通话时间不能为空")
        private String date;
    }

}