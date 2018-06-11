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
    @NotNull(message = "[contacts]不能为空")
    private List<Contact> contacts;
    @NotNull(message = "[callLog]不能为空")
    private List<CallLog> callLogs;

    @Data
    public static class Contact {
        @NotBlank(message = "[name]不能为空")
        private String name;
        @NotBlank(message = "[mobile]不能为空")
        private String mobile;
    }

    @Data
    public static class CallLog {
        @NotBlank(message = "[name]不能为空")
        private String name;
        @NotBlank(message = "[mobile]不能为空")
        private String mobile;
        @NotNull(message = "[type]不能为空")
        private Integer type;
        @NotBlank(message = "[date]不能为空")
        private String date;
    }

}