package com.hzed.easyget.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 风控相关配置
 *
 * @author wuchengwu
 * @date 2018/6/14
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "thirdParty.risk")
public class RiskProp {
    private String domain;
    private String checkRiskEnableBorrowUrl;
    private String idCardRecognitionUrl;
    private String faceRecognitionUrl;
    private String contactsUrl;
    private String messagesUrl;
    private String operatorSendSmsCodeUrl;
    private String operatorAuthUrl;
    private String identityInfoUrl;
    private String pushBidUrl;
    private String facebookAndInsUrl;
    private String authPersonInfoUrl;

    public String getAbsCheckRiskEnableBorrowUrl() {
        return this.domain + this.checkRiskEnableBorrowUrl;
    }

    public String getAbsIdCardRecognitionUrl() {
        return this.domain + this.idCardRecognitionUrl;
    }

    public String getAbsFaceRecognitionUrl() {
        return this.domain + this.faceRecognitionUrl;
    }

    public String getAbsContactsUrl() {
        return this.domain + this.contactsUrl;
    }

    public String getAbsMessagesUrl() {
        return this.domain + this.messagesUrl;
    }

    public String getAbsOperatorSendSmsCodeUrl() {
        return this.domain + this.operatorSendSmsCodeUrl;
    }

    public String getAbsOperatorAuthUrl() {
        return this.domain + this.operatorAuthUrl;
    }

    public String getAbsIdentityInfoUrl() {
        return this.domain + this.identityInfoUrl;
    }

    public String getAbsPushBidUrl() {
        return this.domain + this.pushBidUrl;
    }

    public String getAbsFacebookAndInsUrl() {
        return this.domain + this.facebookAndInsUrl;
    }

    public String getAbsAuthPersonInfoUrl() {
        return this.domain + this.authPersonInfoUrl;
    }

}