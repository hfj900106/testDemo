package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;

/**
 * 短信授权请求
 *
 * @author hfj
 * @date 2018/5/21
 */

@Data
public class MessagesRequest {

    @NotBlank(message = "{param.auth.message.isNotEmpty}")
    private String message;

    @Valid
    private MessagesRequest1 request;


    class MessagesRequest1 {
        @NotBlank(message = "{param.auth.message.isNotEmpty}")
        private String message1;
        @NotBlank(message = "{param.auth.message.isNotEmpty}")
        private String message2;
        @NotBlank(message = "{param.auth.message.isNotEmpty}")
        private String message3;

        class MessagesRequest2 {
            @NotBlank(message = "{param.auth.message.isNotEmpty}")
            private String message1;
            @NotBlank(message = "{param.auth.message.isNotEmpty}")
            private String message2;
            @NotBlank(message = "{param.auth.message.isNotEmpty}")
            private String message3;
        }
    }

}