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

    public String getAbsCheckRiskEnableBorrowUrl(){ return this.domain + this.checkRiskEnableBorrowUrl; }
    public String getIdCardRecognitionUrl(){ return this.domain + this.idCardRecognitionUrl; }
    public String getFaceRecognitionUrl(){ return this.domain + this.faceRecognitionUrl; }
    public String getContactsUrl(){ return this.domain + this.contactsUrl; }
    public String getMessagesUrl(){ return this.domain + this.messagesUrl; }
    public String getOperatorSendSmsCodeUrl(){ return this.domain + this.operatorSendSmsCodeUrl; }
    public String getOperatorAuthUrl(){ return this.domain + this.operatorAuthUrl; }
    public String getIdentityInfoUrl(){ return this.domain + this.identityInfoUrl; }
    public String getPushBidUrl(){ return this.domain + this.pushBidUrl; }
    public String getFacebookAndInsUrl(){ return this.domain + this.facebookAndInsUrl; }

}