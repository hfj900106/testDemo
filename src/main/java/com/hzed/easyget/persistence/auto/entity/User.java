package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class User implements Serializable {
    /**
     * 用户ID
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date time;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 注册入口：1 pc 2 app 3 wechat 4 other
     */
    private Integer client;

    /**
     * 图像
     */
    private String photo;

    /**
     * 真实姓名
     */
    private String realityName;

    /**
     * 录登密码
     */
    private String password;

    /**
     * 密码连续错误次数
     */
    private Integer passwordContinuousErrors;

    /**
     * 密码连续错误被锁定
     */
    private Boolean isPasswordErrorLocked;

    /**
     * 密码连续错误被锁定时间
     */
    private Date passwordErrorLockedTime;

    /**
     * 支付密码
     */
    private String payPassword;

    /**
     * 支付密码连续错误次数
     */
    private Integer payPasswordContinuousErrors;

    /**
     * 支付密码错误被锁定
     */
    private Boolean isPayPasswordErrorLocked;

    /**
     * 支付密码错误被锁定时间
     */
    private Date payPasswordErrorLockedTime;

    /**
     * 是否允许登录(锁定) 0 否(可以登录) 1 是
     */
    private Boolean isAllowLogin;

    /**
     * 后台管理员锁定时间
     */
    private Date lockTime;

    /**
     * 登录次数
     */
    private Long loginCount;

    /**
     * 上次登录时间
     */
    private Date lastLoginTime;

    /**
     * 登录入

     * 

     * 口：1 pc 2 app 3 wechat 4 other
     */
    private Byte loginClient;

    /**
     * 上次登录ip
     */
    private String lastLoginIp;

    /**
     * 上次退出时间
     */
    private Date lastLogoutTime;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 箱邮是否已经验证通过
     */
    private Boolean isEmailVerified;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 手机是否已经验证通过
     */
    private Boolean isMobileVerified;

    /**
     * 是否已设置密码保护问题（冗余）
     */
    private Boolean isSecretSet;

    /**
     * 安全问题设置时间
     */
    private Date secretSetTime;

    /**
     * 安全问题1ID
     */
    private Integer secretQuestionId1;

    /**
     * 安全问题答案1
     */
    private String answer1;

    /**
     * 安全问题2ID
     */
    private Integer secretQuestionId2;

    /**
     * 安全问题答案2
     */
    private String answer2;

    /**
     * 安全问题3ID
     */
    private Integer secretQuestionId3;

    /**
     * 安全问题答案3
     */
    private String answer3;

    /**
     * 身份证号吗
     */
    private String idNumber;

    /**
     * 地址
     */
    private String address;

    /**
     * 邮编
     */
    private String postcode;

    /**
     * 1 男 2 女 3 未知
     */
    private Byte sex;

    /**
     */
    private Date birthday;

    /**
     * 城市Id
     */
    private Integer cityId;

    /**
     * 家庭住址
     */
    private String familyAddress;

    /**
     * 家庭电话
     */
    private String familyTelephone;

    /**
     * 公司
     */
    private String company;

    /**
     * 公司地址
     */
    private String companyAddress;

    /**
     * 办公电话
     */
    private String officeTelephone;

    /**
     * 传真号码
     */
    private String faxNumber;

    /**
     * 教育情况ID
     */
    private Integer educationId;

    /**
     * 婚姻状况ID
     */
    private Integer maritalId;

    /**
     * 房贷情况ID
     */
    private Integer houseId;

    /**
     * 车贷情况ID
     */
    private Integer carId;

    /**
     * 是否编辑了基本信息 0 否 1 是
     */
    private Boolean isAddBaseInfo;

    /**
     * 0 = 正常状态; 1 = 已擦除状态; 
     */
    private Boolean isErased;

    /**
     * 推荐时间
     */
    private Date recommendTime;

    /**
     * 推荐者user_id
     */
    private Long recommendUserId;

    /**
     * 注册会员时的奖励方式 0.按个数 1. 提成
     */
    private Byte recommendRewardType;

    /**
     * 0 未确定 1 借款会员 2 投资会员 3 复合会员
     */
    private Byte masterIdentity;

    /**
     * 标记

     * 

     * 会员入口：1 pc 2 app 3 wechat 4 other
     */
    private Byte masterClient;

    /**
     * 成为借款会员时间
     */
    private Date masterTimeLoan;

    /**
     * 成为理财会员时间
     */
    private Date masterTimeInvest;

    /**
     * 成为复合会员时间
     */
    private Date masterTimeComplex;

    /**
     * 是否是vip  1-vip会员  0-非vip会员
     */
    private Boolean vipStatus;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 资金池余额（资金托管模式新增字段）
     */
    private BigDecimal balance2;

    /**
     * 冻结金额
     */
    private BigDecimal freeze;

    /**
     * 积分
     */
    private Integer score;

    /**
     * 信用积分
     */
    private Integer creditScore;

    /**
     * 信用等级
     */
    private Integer creditLevelId;

    /**
     * 管理员是否拒收站内信 0 否 1 是  
     */
    private Boolean isRefusedReceive;

    /**
     * 拒收时间
     */
    private Date refusedTime;

    /**
     * 拒收原因
     */
    private String refusedReason;

    /**
     * 是否是黑名单 0 否 1 是
     */
    private Boolean isBlacklist;

    /**
     * 被加入黑名单时间
     */
    private Date joinedTime;

    /**
     * 被加入黑名单的原因
     */
    private String joinedReason;

    /**
     * 分配的时间
     */
    private Date assignedTime;

    /**
     * 分配给哪个管理员管理
     */
    private Long assignedToSupervisorId;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 信用额度
     */
    private BigDecimal creditLine;

    /**
     * 注册时的信用额度
     */
    private BigDecimal lastCreditLine;

    /**
     * 是否是有效会员 0 否 1 是
     */
    private Boolean isActive;

    /**
     * 用户表资金改动标记
     */
    private String sign1;

    /**
     * 用户明细表改动标记
     */
    private String sign2;

    /**
     * QQ绑定key
     */
    private String qqKey;

    /**
     * 微博绑定key
     */
    private String weiboKey;

    /**
     * 二维码图片
     */
    private String qrCode;

    /**
     * ips账号
     */
    private String ipsAcctNo;

    /**
     * 自动投标授权号
     */
    private String ipsBidAuthNo;

    /**
     * 自动还款授权号
     */
    private String ipsRepayAuthNo;

    /**
     * user_id (百度云推送)
     */
    private String deviceUserId;

    /**
     * channel_id (百度云推送)
     */
    private String channelId;

    /**
     * 设备类型 1 android 2 ios
     */
    private Byte deviceType;

    /**
     * 是否账单提醒推送 0 否 1 是
     */
    private Boolean isBillPush;

    /**
     * 是否投资满标提醒推送 0 否 1 是
     */
    private Boolean isInvestPush;

    /**
     * 是否活动提醒推送 0 否 1 是
     */
    private Boolean isActivityPush;

    /**
     */
    private String openId;

    /**
     * 是否投资过标，0 不是 1 是
     */
    private Boolean isInvestBid;

    /**
     * 备注
     */
    private String remark;

    /**
     * 用户标注
     */
    private String label;

    /**
     * 皇冠标识 1:表示皇冠
     */
    private Integer isCrown;

    /**
     * 真正身份证号码
     */
    private String realIdNumber;

    /**
     * 0=理财会员;1=借款会员
     */
    private Integer type;

    /**
     * 是否已授权免签
     */
    private Byte ipsIsAuth;

    /**
     * 提现方式：０=t+0提现;1=t+1提现
     */
    private Byte withdrawWay;

    /**
     * 融360老用户专用
     */
    private String mobileIdcardMd5;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getRealityName() {
        return realityName;
    }

    public void setRealityName(String realityName) {
        this.realityName = realityName == null ? null : realityName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getPasswordContinuousErrors() {
        return passwordContinuousErrors;
    }

    public void setPasswordContinuousErrors(Integer passwordContinuousErrors) {
        this.passwordContinuousErrors = passwordContinuousErrors;
    }

    public Boolean getIsPasswordErrorLocked() {
        return isPasswordErrorLocked;
    }

    public void setIsPasswordErrorLocked(Boolean isPasswordErrorLocked) {
        this.isPasswordErrorLocked = isPasswordErrorLocked;
    }

    public Date getPasswordErrorLockedTime() {
        return passwordErrorLockedTime;
    }

    public void setPasswordErrorLockedTime(Date passwordErrorLockedTime) {
        this.passwordErrorLockedTime = passwordErrorLockedTime;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword == null ? null : payPassword.trim();
    }

    public Integer getPayPasswordContinuousErrors() {
        return payPasswordContinuousErrors;
    }

    public void setPayPasswordContinuousErrors(Integer payPasswordContinuousErrors) {
        this.payPasswordContinuousErrors = payPasswordContinuousErrors;
    }

    public Boolean getIsPayPasswordErrorLocked() {
        return isPayPasswordErrorLocked;
    }

    public void setIsPayPasswordErrorLocked(Boolean isPayPasswordErrorLocked) {
        this.isPayPasswordErrorLocked = isPayPasswordErrorLocked;
    }

    public Date getPayPasswordErrorLockedTime() {
        return payPasswordErrorLockedTime;
    }

    public void setPayPasswordErrorLockedTime(Date payPasswordErrorLockedTime) {
        this.payPasswordErrorLockedTime = payPasswordErrorLockedTime;
    }

    public Boolean getIsAllowLogin() {
        return isAllowLogin;
    }

    public void setIsAllowLogin(Boolean isAllowLogin) {
        this.isAllowLogin = isAllowLogin;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    public Long getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Long loginCount) {
        this.loginCount = loginCount;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Byte getLoginClient() {
        return loginClient;
    }

    public void setLoginClient(Byte loginClient) {
        this.loginClient = loginClient;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    public Date getLastLogoutTime() {
        return lastLogoutTime;
    }

    public void setLastLogoutTime(Date lastLogoutTime) {
        this.lastLogoutTime = lastLogoutTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Boolean getIsEmailVerified() {
        return isEmailVerified;
    }

    public void setIsEmailVerified(Boolean isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Boolean getIsMobileVerified() {
        return isMobileVerified;
    }

    public void setIsMobileVerified(Boolean isMobileVerified) {
        this.isMobileVerified = isMobileVerified;
    }

    public Boolean getIsSecretSet() {
        return isSecretSet;
    }

    public void setIsSecretSet(Boolean isSecretSet) {
        this.isSecretSet = isSecretSet;
    }

    public Date getSecretSetTime() {
        return secretSetTime;
    }

    public void setSecretSetTime(Date secretSetTime) {
        this.secretSetTime = secretSetTime;
    }

    public Integer getSecretQuestionId1() {
        return secretQuestionId1;
    }

    public void setSecretQuestionId1(Integer secretQuestionId1) {
        this.secretQuestionId1 = secretQuestionId1;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1 == null ? null : answer1.trim();
    }

    public Integer getSecretQuestionId2() {
        return secretQuestionId2;
    }

    public void setSecretQuestionId2(Integer secretQuestionId2) {
        this.secretQuestionId2 = secretQuestionId2;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2 == null ? null : answer2.trim();
    }

    public Integer getSecretQuestionId3() {
        return secretQuestionId3;
    }

    public void setSecretQuestionId3(Integer secretQuestionId3) {
        this.secretQuestionId3 = secretQuestionId3;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3 == null ? null : answer3.trim();
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getFamilyAddress() {
        return familyAddress;
    }

    public void setFamilyAddress(String familyAddress) {
        this.familyAddress = familyAddress == null ? null : familyAddress.trim();
    }

    public String getFamilyTelephone() {
        return familyTelephone;
    }

    public void setFamilyTelephone(String familyTelephone) {
        this.familyTelephone = familyTelephone == null ? null : familyTelephone.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public String getOfficeTelephone() {
        return officeTelephone;
    }

    public void setOfficeTelephone(String officeTelephone) {
        this.officeTelephone = officeTelephone == null ? null : officeTelephone.trim();
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber == null ? null : faxNumber.trim();
    }

    public Integer getEducationId() {
        return educationId;
    }

    public void setEducationId(Integer educationId) {
        this.educationId = educationId;
    }

    public Integer getMaritalId() {
        return maritalId;
    }

    public void setMaritalId(Integer maritalId) {
        this.maritalId = maritalId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Boolean getIsAddBaseInfo() {
        return isAddBaseInfo;
    }

    public void setIsAddBaseInfo(Boolean isAddBaseInfo) {
        this.isAddBaseInfo = isAddBaseInfo;
    }

    public Boolean getIsErased() {
        return isErased;
    }

    public void setIsErased(Boolean isErased) {
        this.isErased = isErased;
    }

    public Date getRecommendTime() {
        return recommendTime;
    }

    public void setRecommendTime(Date recommendTime) {
        this.recommendTime = recommendTime;
    }

    public Long getRecommendUserId() {
        return recommendUserId;
    }

    public void setRecommendUserId(Long recommendUserId) {
        this.recommendUserId = recommendUserId;
    }

    public Byte getRecommendRewardType() {
        return recommendRewardType;
    }

    public void setRecommendRewardType(Byte recommendRewardType) {
        this.recommendRewardType = recommendRewardType;
    }

    public Byte getMasterIdentity() {
        return masterIdentity;
    }

    public void setMasterIdentity(Byte masterIdentity) {
        this.masterIdentity = masterIdentity;
    }

    public Byte getMasterClient() {
        return masterClient;
    }

    public void setMasterClient(Byte masterClient) {
        this.masterClient = masterClient;
    }

    public Date getMasterTimeLoan() {
        return masterTimeLoan;
    }

    public void setMasterTimeLoan(Date masterTimeLoan) {
        this.masterTimeLoan = masterTimeLoan;
    }

    public Date getMasterTimeInvest() {
        return masterTimeInvest;
    }

    public void setMasterTimeInvest(Date masterTimeInvest) {
        this.masterTimeInvest = masterTimeInvest;
    }

    public Date getMasterTimeComplex() {
        return masterTimeComplex;
    }

    public void setMasterTimeComplex(Date masterTimeComplex) {
        this.masterTimeComplex = masterTimeComplex;
    }

    public Boolean getVipStatus() {
        return vipStatus;
    }

    public void setVipStatus(Boolean vipStatus) {
        this.vipStatus = vipStatus;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance2() {
        return balance2;
    }

    public void setBalance2(BigDecimal balance2) {
        this.balance2 = balance2;
    }

    public BigDecimal getFreeze() {
        return freeze;
    }

    public void setFreeze(BigDecimal freeze) {
        this.freeze = freeze;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public Integer getCreditLevelId() {
        return creditLevelId;
    }

    public void setCreditLevelId(Integer creditLevelId) {
        this.creditLevelId = creditLevelId;
    }

    public Boolean getIsRefusedReceive() {
        return isRefusedReceive;
    }

    public void setIsRefusedReceive(Boolean isRefusedReceive) {
        this.isRefusedReceive = isRefusedReceive;
    }

    public Date getRefusedTime() {
        return refusedTime;
    }

    public void setRefusedTime(Date refusedTime) {
        this.refusedTime = refusedTime;
    }

    public String getRefusedReason() {
        return refusedReason;
    }

    public void setRefusedReason(String refusedReason) {
        this.refusedReason = refusedReason == null ? null : refusedReason.trim();
    }

    public Boolean getIsBlacklist() {
        return isBlacklist;
    }

    public void setIsBlacklist(Boolean isBlacklist) {
        this.isBlacklist = isBlacklist;
    }

    public Date getJoinedTime() {
        return joinedTime;
    }

    public void setJoinedTime(Date joinedTime) {
        this.joinedTime = joinedTime;
    }

    public String getJoinedReason() {
        return joinedReason;
    }

    public void setJoinedReason(String joinedReason) {
        this.joinedReason = joinedReason == null ? null : joinedReason.trim();
    }

    public Date getAssignedTime() {
        return assignedTime;
    }

    public void setAssignedTime(Date assignedTime) {
        this.assignedTime = assignedTime;
    }

    public Long getAssignedToSupervisorId() {
        return assignedToSupervisorId;
    }

    public void setAssignedToSupervisorId(Long assignedToSupervisorId) {
        this.assignedToSupervisorId = assignedToSupervisorId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public BigDecimal getCreditLine() {
        return creditLine;
    }

    public void setCreditLine(BigDecimal creditLine) {
        this.creditLine = creditLine;
    }

    public BigDecimal getLastCreditLine() {
        return lastCreditLine;
    }

    public void setLastCreditLine(BigDecimal lastCreditLine) {
        this.lastCreditLine = lastCreditLine;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getSign1() {
        return sign1;
    }

    public void setSign1(String sign1) {
        this.sign1 = sign1 == null ? null : sign1.trim();
    }

    public String getSign2() {
        return sign2;
    }

    public void setSign2(String sign2) {
        this.sign2 = sign2 == null ? null : sign2.trim();
    }

    public String getQqKey() {
        return qqKey;
    }

    public void setQqKey(String qqKey) {
        this.qqKey = qqKey == null ? null : qqKey.trim();
    }

    public String getWeiboKey() {
        return weiboKey;
    }

    public void setWeiboKey(String weiboKey) {
        this.weiboKey = weiboKey == null ? null : weiboKey.trim();
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode == null ? null : qrCode.trim();
    }

    public String getIpsAcctNo() {
        return ipsAcctNo;
    }

    public void setIpsAcctNo(String ipsAcctNo) {
        this.ipsAcctNo = ipsAcctNo == null ? null : ipsAcctNo.trim();
    }

    public String getIpsBidAuthNo() {
        return ipsBidAuthNo;
    }

    public void setIpsBidAuthNo(String ipsBidAuthNo) {
        this.ipsBidAuthNo = ipsBidAuthNo == null ? null : ipsBidAuthNo.trim();
    }

    public String getIpsRepayAuthNo() {
        return ipsRepayAuthNo;
    }

    public void setIpsRepayAuthNo(String ipsRepayAuthNo) {
        this.ipsRepayAuthNo = ipsRepayAuthNo == null ? null : ipsRepayAuthNo.trim();
    }

    public String getDeviceUserId() {
        return deviceUserId;
    }

    public void setDeviceUserId(String deviceUserId) {
        this.deviceUserId = deviceUserId == null ? null : deviceUserId.trim();
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public Byte getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Byte deviceType) {
        this.deviceType = deviceType;
    }

    public Boolean getIsBillPush() {
        return isBillPush;
    }

    public void setIsBillPush(Boolean isBillPush) {
        this.isBillPush = isBillPush;
    }

    public Boolean getIsInvestPush() {
        return isInvestPush;
    }

    public void setIsInvestPush(Boolean isInvestPush) {
        this.isInvestPush = isInvestPush;
    }

    public Boolean getIsActivityPush() {
        return isActivityPush;
    }

    public void setIsActivityPush(Boolean isActivityPush) {
        this.isActivityPush = isActivityPush;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Boolean getIsInvestBid() {
        return isInvestBid;
    }

    public void setIsInvestBid(Boolean isInvestBid) {
        this.isInvestBid = isInvestBid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public Integer getIsCrown() {
        return isCrown;
    }

    public void setIsCrown(Integer isCrown) {
        this.isCrown = isCrown;
    }

    public String getRealIdNumber() {
        return realIdNumber;
    }

    public void setRealIdNumber(String realIdNumber) {
        this.realIdNumber = realIdNumber == null ? null : realIdNumber.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Byte getIpsIsAuth() {
        return ipsIsAuth;
    }

    public void setIpsIsAuth(Byte ipsIsAuth) {
        this.ipsIsAuth = ipsIsAuth;
    }

    public Byte getWithdrawWay() {
        return withdrawWay;
    }

    public void setWithdrawWay(Byte withdrawWay) {
        this.withdrawWay = withdrawWay;
    }

    public String getMobileIdcardMd5() {
        return mobileIdcardMd5;
    }

    public void setMobileIdcardMd5(String mobileIdcardMd5) {
        this.mobileIdcardMd5 = mobileIdcardMd5 == null ? null : mobileIdcardMd5.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", time=").append(time);
        sb.append(", name=").append(name);
        sb.append(", client=").append(client);
        sb.append(", photo=").append(photo);
        sb.append(", realityName=").append(realityName);
        sb.append(", password=").append(password);
        sb.append(", passwordContinuousErrors=").append(passwordContinuousErrors);
        sb.append(", isPasswordErrorLocked=").append(isPasswordErrorLocked);
        sb.append(", passwordErrorLockedTime=").append(passwordErrorLockedTime);
        sb.append(", payPassword=").append(payPassword);
        sb.append(", payPasswordContinuousErrors=").append(payPasswordContinuousErrors);
        sb.append(", isPayPasswordErrorLocked=").append(isPayPasswordErrorLocked);
        sb.append(", payPasswordErrorLockedTime=").append(payPasswordErrorLockedTime);
        sb.append(", isAllowLogin=").append(isAllowLogin);
        sb.append(", lockTime=").append(lockTime);
        sb.append(", loginCount=").append(loginCount);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", loginClient=").append(loginClient);
        sb.append(", lastLoginIp=").append(lastLoginIp);
        sb.append(", lastLogoutTime=").append(lastLogoutTime);
        sb.append(", email=").append(email);
        sb.append(", isEmailVerified=").append(isEmailVerified);
        sb.append(", mobile=").append(mobile);
        sb.append(", isMobileVerified=").append(isMobileVerified);
        sb.append(", isSecretSet=").append(isSecretSet);
        sb.append(", secretSetTime=").append(secretSetTime);
        sb.append(", secretQuestionId1=").append(secretQuestionId1);
        sb.append(", answer1=").append(answer1);
        sb.append(", secretQuestionId2=").append(secretQuestionId2);
        sb.append(", answer2=").append(answer2);
        sb.append(", secretQuestionId3=").append(secretQuestionId3);
        sb.append(", answer3=").append(answer3);
        sb.append(", idNumber=").append(idNumber);
        sb.append(", address=").append(address);
        sb.append(", postcode=").append(postcode);
        sb.append(", sex=").append(sex);
        sb.append(", birthday=").append(birthday);
        sb.append(", cityId=").append(cityId);
        sb.append(", familyAddress=").append(familyAddress);
        sb.append(", familyTelephone=").append(familyTelephone);
        sb.append(", company=").append(company);
        sb.append(", companyAddress=").append(companyAddress);
        sb.append(", officeTelephone=").append(officeTelephone);
        sb.append(", faxNumber=").append(faxNumber);
        sb.append(", educationId=").append(educationId);
        sb.append(", maritalId=").append(maritalId);
        sb.append(", houseId=").append(houseId);
        sb.append(", carId=").append(carId);
        sb.append(", isAddBaseInfo=").append(isAddBaseInfo);
        sb.append(", isErased=").append(isErased);
        sb.append(", recommendTime=").append(recommendTime);
        sb.append(", recommendUserId=").append(recommendUserId);
        sb.append(", recommendRewardType=").append(recommendRewardType);
        sb.append(", masterIdentity=").append(masterIdentity);
        sb.append(", masterClient=").append(masterClient);
        sb.append(", masterTimeLoan=").append(masterTimeLoan);
        sb.append(", masterTimeInvest=").append(masterTimeInvest);
        sb.append(", masterTimeComplex=").append(masterTimeComplex);
        sb.append(", vipStatus=").append(vipStatus);
        sb.append(", balance=").append(balance);
        sb.append(", balance2=").append(balance2);
        sb.append(", freeze=").append(freeze);
        sb.append(", score=").append(score);
        sb.append(", creditScore=").append(creditScore);
        sb.append(", creditLevelId=").append(creditLevelId);
        sb.append(", isRefusedReceive=").append(isRefusedReceive);
        sb.append(", refusedTime=").append(refusedTime);
        sb.append(", refusedReason=").append(refusedReason);
        sb.append(", isBlacklist=").append(isBlacklist);
        sb.append(", joinedTime=").append(joinedTime);
        sb.append(", joinedReason=").append(joinedReason);
        sb.append(", assignedTime=").append(assignedTime);
        sb.append(", assignedToSupervisorId=").append(assignedToSupervisorId);
        sb.append(", telephone=").append(telephone);
        sb.append(", creditLine=").append(creditLine);
        sb.append(", lastCreditLine=").append(lastCreditLine);
        sb.append(", isActive=").append(isActive);
        sb.append(", sign1=").append(sign1);
        sb.append(", sign2=").append(sign2);
        sb.append(", qqKey=").append(qqKey);
        sb.append(", weiboKey=").append(weiboKey);
        sb.append(", qrCode=").append(qrCode);
        sb.append(", ipsAcctNo=").append(ipsAcctNo);
        sb.append(", ipsBidAuthNo=").append(ipsBidAuthNo);
        sb.append(", ipsRepayAuthNo=").append(ipsRepayAuthNo);
        sb.append(", deviceUserId=").append(deviceUserId);
        sb.append(", channelId=").append(channelId);
        sb.append(", deviceType=").append(deviceType);
        sb.append(", isBillPush=").append(isBillPush);
        sb.append(", isInvestPush=").append(isInvestPush);
        sb.append(", isActivityPush=").append(isActivityPush);
        sb.append(", openId=").append(openId);
        sb.append(", isInvestBid=").append(isInvestBid);
        sb.append(", remark=").append(remark);
        sb.append(", label=").append(label);
        sb.append(", isCrown=").append(isCrown);
        sb.append(", realIdNumber=").append(realIdNumber);
        sb.append(", type=").append(type);
        sb.append(", ipsIsAuth=").append(ipsIsAuth);
        sb.append(", withdrawWay=").append(withdrawWay);
        sb.append(", mobileIdcardMd5=").append(mobileIdcardMd5);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static User.Builder builder() {
        return new User.Builder();
    }

    public static class Builder {
        private User obj;

        public Builder() {
            this.obj = new User();
        }

        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        public Builder time(Date time) {
            obj.setTime(time);
            return this;
        }

        public Builder name(String name) {
            obj.setName(name);
            return this;
        }

        public Builder client(Integer client) {
            obj.setClient(client);
            return this;
        }

        public Builder photo(String photo) {
            obj.setPhoto(photo);
            return this;
        }

        public Builder realityName(String realityName) {
            obj.setRealityName(realityName);
            return this;
        }

        public Builder password(String password) {
            obj.setPassword(password);
            return this;
        }

        public Builder passwordContinuousErrors(Integer passwordContinuousErrors) {
            obj.setPasswordContinuousErrors(passwordContinuousErrors);
            return this;
        }

        public Builder isPasswordErrorLocked(Boolean isPasswordErrorLocked) {
            obj.setIsPasswordErrorLocked(isPasswordErrorLocked);
            return this;
        }

        public Builder passwordErrorLockedTime(Date passwordErrorLockedTime) {
            obj.setPasswordErrorLockedTime(passwordErrorLockedTime);
            return this;
        }

        public Builder payPassword(String payPassword) {
            obj.setPayPassword(payPassword);
            return this;
        }

        public Builder payPasswordContinuousErrors(Integer payPasswordContinuousErrors) {
            obj.setPayPasswordContinuousErrors(payPasswordContinuousErrors);
            return this;
        }

        public Builder isPayPasswordErrorLocked(Boolean isPayPasswordErrorLocked) {
            obj.setIsPayPasswordErrorLocked(isPayPasswordErrorLocked);
            return this;
        }

        public Builder payPasswordErrorLockedTime(Date payPasswordErrorLockedTime) {
            obj.setPayPasswordErrorLockedTime(payPasswordErrorLockedTime);
            return this;
        }

        public Builder isAllowLogin(Boolean isAllowLogin) {
            obj.setIsAllowLogin(isAllowLogin);
            return this;
        }

        public Builder lockTime(Date lockTime) {
            obj.setLockTime(lockTime);
            return this;
        }

        public Builder loginCount(Long loginCount) {
            obj.setLoginCount(loginCount);
            return this;
        }

        public Builder lastLoginTime(Date lastLoginTime) {
            obj.setLastLoginTime(lastLoginTime);
            return this;
        }

        public Builder loginClient(Byte loginClient) {
            obj.setLoginClient(loginClient);
            return this;
        }

        public Builder lastLoginIp(String lastLoginIp) {
            obj.setLastLoginIp(lastLoginIp);
            return this;
        }

        public Builder lastLogoutTime(Date lastLogoutTime) {
            obj.setLastLogoutTime(lastLogoutTime);
            return this;
        }

        public Builder email(String email) {
            obj.setEmail(email);
            return this;
        }

        public Builder isEmailVerified(Boolean isEmailVerified) {
            obj.setIsEmailVerified(isEmailVerified);
            return this;
        }

        public Builder mobile(String mobile) {
            obj.setMobile(mobile);
            return this;
        }

        public Builder isMobileVerified(Boolean isMobileVerified) {
            obj.setIsMobileVerified(isMobileVerified);
            return this;
        }

        public Builder isSecretSet(Boolean isSecretSet) {
            obj.setIsSecretSet(isSecretSet);
            return this;
        }

        public Builder secretSetTime(Date secretSetTime) {
            obj.setSecretSetTime(secretSetTime);
            return this;
        }

        public Builder secretQuestionId1(Integer secretQuestionId1) {
            obj.setSecretQuestionId1(secretQuestionId1);
            return this;
        }

        public Builder answer1(String answer1) {
            obj.setAnswer1(answer1);
            return this;
        }

        public Builder secretQuestionId2(Integer secretQuestionId2) {
            obj.setSecretQuestionId2(secretQuestionId2);
            return this;
        }

        public Builder answer2(String answer2) {
            obj.setAnswer2(answer2);
            return this;
        }

        public Builder secretQuestionId3(Integer secretQuestionId3) {
            obj.setSecretQuestionId3(secretQuestionId3);
            return this;
        }

        public Builder answer3(String answer3) {
            obj.setAnswer3(answer3);
            return this;
        }

        public Builder idNumber(String idNumber) {
            obj.setIdNumber(idNumber);
            return this;
        }

        public Builder address(String address) {
            obj.setAddress(address);
            return this;
        }

        public Builder postcode(String postcode) {
            obj.setPostcode(postcode);
            return this;
        }

        public Builder sex(Byte sex) {
            obj.setSex(sex);
            return this;
        }

        public Builder birthday(Date birthday) {
            obj.setBirthday(birthday);
            return this;
        }

        public Builder cityId(Integer cityId) {
            obj.setCityId(cityId);
            return this;
        }

        public Builder familyAddress(String familyAddress) {
            obj.setFamilyAddress(familyAddress);
            return this;
        }

        public Builder familyTelephone(String familyTelephone) {
            obj.setFamilyTelephone(familyTelephone);
            return this;
        }

        public Builder company(String company) {
            obj.setCompany(company);
            return this;
        }

        public Builder companyAddress(String companyAddress) {
            obj.setCompanyAddress(companyAddress);
            return this;
        }

        public Builder officeTelephone(String officeTelephone) {
            obj.setOfficeTelephone(officeTelephone);
            return this;
        }

        public Builder faxNumber(String faxNumber) {
            obj.setFaxNumber(faxNumber);
            return this;
        }

        public Builder educationId(Integer educationId) {
            obj.setEducationId(educationId);
            return this;
        }

        public Builder maritalId(Integer maritalId) {
            obj.setMaritalId(maritalId);
            return this;
        }

        public Builder houseId(Integer houseId) {
            obj.setHouseId(houseId);
            return this;
        }

        public Builder carId(Integer carId) {
            obj.setCarId(carId);
            return this;
        }

        public Builder isAddBaseInfo(Boolean isAddBaseInfo) {
            obj.setIsAddBaseInfo(isAddBaseInfo);
            return this;
        }

        public Builder isErased(Boolean isErased) {
            obj.setIsErased(isErased);
            return this;
        }

        public Builder recommendTime(Date recommendTime) {
            obj.setRecommendTime(recommendTime);
            return this;
        }

        public Builder recommendUserId(Long recommendUserId) {
            obj.setRecommendUserId(recommendUserId);
            return this;
        }

        public Builder recommendRewardType(Byte recommendRewardType) {
            obj.setRecommendRewardType(recommendRewardType);
            return this;
        }

        public Builder masterIdentity(Byte masterIdentity) {
            obj.setMasterIdentity(masterIdentity);
            return this;
        }

        public Builder masterClient(Byte masterClient) {
            obj.setMasterClient(masterClient);
            return this;
        }

        public Builder masterTimeLoan(Date masterTimeLoan) {
            obj.setMasterTimeLoan(masterTimeLoan);
            return this;
        }

        public Builder masterTimeInvest(Date masterTimeInvest) {
            obj.setMasterTimeInvest(masterTimeInvest);
            return this;
        }

        public Builder masterTimeComplex(Date masterTimeComplex) {
            obj.setMasterTimeComplex(masterTimeComplex);
            return this;
        }

        public Builder vipStatus(Boolean vipStatus) {
            obj.setVipStatus(vipStatus);
            return this;
        }

        public Builder balance(BigDecimal balance) {
            obj.setBalance(balance);
            return this;
        }

        public Builder balance2(BigDecimal balance2) {
            obj.setBalance2(balance2);
            return this;
        }

        public Builder freeze(BigDecimal freeze) {
            obj.setFreeze(freeze);
            return this;
        }

        public Builder score(Integer score) {
            obj.setScore(score);
            return this;
        }

        public Builder creditScore(Integer creditScore) {
            obj.setCreditScore(creditScore);
            return this;
        }

        public Builder creditLevelId(Integer creditLevelId) {
            obj.setCreditLevelId(creditLevelId);
            return this;
        }

        public Builder isRefusedReceive(Boolean isRefusedReceive) {
            obj.setIsRefusedReceive(isRefusedReceive);
            return this;
        }

        public Builder refusedTime(Date refusedTime) {
            obj.setRefusedTime(refusedTime);
            return this;
        }

        public Builder refusedReason(String refusedReason) {
            obj.setRefusedReason(refusedReason);
            return this;
        }

        public Builder isBlacklist(Boolean isBlacklist) {
            obj.setIsBlacklist(isBlacklist);
            return this;
        }

        public Builder joinedTime(Date joinedTime) {
            obj.setJoinedTime(joinedTime);
            return this;
        }

        public Builder joinedReason(String joinedReason) {
            obj.setJoinedReason(joinedReason);
            return this;
        }

        public Builder assignedTime(Date assignedTime) {
            obj.setAssignedTime(assignedTime);
            return this;
        }

        public Builder assignedToSupervisorId(Long assignedToSupervisorId) {
            obj.setAssignedToSupervisorId(assignedToSupervisorId);
            return this;
        }

        public Builder telephone(String telephone) {
            obj.setTelephone(telephone);
            return this;
        }

        public Builder creditLine(BigDecimal creditLine) {
            obj.setCreditLine(creditLine);
            return this;
        }

        public Builder lastCreditLine(BigDecimal lastCreditLine) {
            obj.setLastCreditLine(lastCreditLine);
            return this;
        }

        public Builder isActive(Boolean isActive) {
            obj.setIsActive(isActive);
            return this;
        }

        public Builder sign1(String sign1) {
            obj.setSign1(sign1);
            return this;
        }

        public Builder sign2(String sign2) {
            obj.setSign2(sign2);
            return this;
        }

        public Builder qqKey(String qqKey) {
            obj.setQqKey(qqKey);
            return this;
        }

        public Builder weiboKey(String weiboKey) {
            obj.setWeiboKey(weiboKey);
            return this;
        }

        public Builder qrCode(String qrCode) {
            obj.setQrCode(qrCode);
            return this;
        }

        public Builder ipsAcctNo(String ipsAcctNo) {
            obj.setIpsAcctNo(ipsAcctNo);
            return this;
        }

        public Builder ipsBidAuthNo(String ipsBidAuthNo) {
            obj.setIpsBidAuthNo(ipsBidAuthNo);
            return this;
        }

        public Builder ipsRepayAuthNo(String ipsRepayAuthNo) {
            obj.setIpsRepayAuthNo(ipsRepayAuthNo);
            return this;
        }

        public Builder deviceUserId(String deviceUserId) {
            obj.setDeviceUserId(deviceUserId);
            return this;
        }

        public Builder channelId(String channelId) {
            obj.setChannelId(channelId);
            return this;
        }

        public Builder deviceType(Byte deviceType) {
            obj.setDeviceType(deviceType);
            return this;
        }

        public Builder isBillPush(Boolean isBillPush) {
            obj.setIsBillPush(isBillPush);
            return this;
        }

        public Builder isInvestPush(Boolean isInvestPush) {
            obj.setIsInvestPush(isInvestPush);
            return this;
        }

        public Builder isActivityPush(Boolean isActivityPush) {
            obj.setIsActivityPush(isActivityPush);
            return this;
        }

        public Builder openId(String openId) {
            obj.setOpenId(openId);
            return this;
        }

        public Builder isInvestBid(Boolean isInvestBid) {
            obj.setIsInvestBid(isInvestBid);
            return this;
        }

        public Builder remark(String remark) {
            obj.setRemark(remark);
            return this;
        }

        public Builder label(String label) {
            obj.setLabel(label);
            return this;
        }

        public Builder isCrown(Integer isCrown) {
            obj.setIsCrown(isCrown);
            return this;
        }

        public Builder realIdNumber(String realIdNumber) {
            obj.setRealIdNumber(realIdNumber);
            return this;
        }

        public Builder type(Integer type) {
            obj.setType(type);
            return this;
        }

        public Builder ipsIsAuth(Byte ipsIsAuth) {
            obj.setIpsIsAuth(ipsIsAuth);
            return this;
        }

        public Builder withdrawWay(Byte withdrawWay) {
            obj.setWithdrawWay(withdrawWay);
            return this;
        }

        public Builder mobileIdcardMd5(String mobileIdcardMd5) {
            obj.setMobileIdcardMd5(mobileIdcardMd5);
            return this;
        }

        public User build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id"),
        time("time"),
        name("name"),
        client("client"),
        photo("photo"),
        realityName("reality_name"),
        password("password"),
        passwordContinuousErrors("password_continuous_errors"),
        isPasswordErrorLocked("is_password_error_locked"),
        passwordErrorLockedTime("password_error_locked_time"),
        payPassword("pay_password"),
        payPasswordContinuousErrors("pay_password_continuous_errors"),
        isPayPasswordErrorLocked("is_pay_password_error_locked"),
        payPasswordErrorLockedTime("pay_password_error_locked_time"),
        isAllowLogin("is_allow_login"),
        lockTime("lock_time"),
        loginCount("login_count"),
        lastLoginTime("last_login_time"),
        loginClient("login_client"),
        lastLoginIp("last_login_ip"),
        lastLogoutTime("last_logout_time"),
        email("email"),
        isEmailVerified("is_email_verified"),
        mobile("mobile"),
        isMobileVerified("is_mobile_verified"),
        isSecretSet("is_secret_set"),
        secretSetTime("secret_set_time"),
        secretQuestionId1("secret_question_id1"),
        answer1("answer1"),
        secretQuestionId2("secret_question_id2"),
        answer2("answer2"),
        secretQuestionId3("secret_question_id3"),
        answer3("answer3"),
        idNumber("id_number"),
        address("address"),
        postcode("postcode"),
        sex("sex"),
        birthday("birthday"),
        cityId("city_id"),
        familyAddress("family_address"),
        familyTelephone("family_telephone"),
        company("company"),
        companyAddress("company_address"),
        officeTelephone("office_telephone"),
        faxNumber("fax_number"),
        educationId("education_id"),
        maritalId("marital_id"),
        houseId("house_id"),
        carId("car_id"),
        isAddBaseInfo("is_add_base_info"),
        isErased("is_erased"),
        recommendTime("recommend_time"),
        recommendUserId("recommend_user_id"),
        recommendRewardType("recommend_reward_type"),
        masterIdentity("master_identity"),
        masterClient("master_client"),
        masterTimeLoan("master_time_loan"),
        masterTimeInvest("master_time_invest"),
        masterTimeComplex("master_time_complex"),
        vipStatus("vip_status"),
        balance("balance"),
        balance2("balance2"),
        freeze("freeze"),
        score("score"),
        creditScore("credit_score"),
        creditLevelId("credit_level_id"),
        isRefusedReceive("is_refused_receive"),
        refusedTime("refused_time"),
        refusedReason("refused_reason"),
        isBlacklist("is_blacklist"),
        joinedTime("joined_time"),
        joinedReason("joined_reason"),
        assignedTime("assigned_time"),
        assignedToSupervisorId("assigned_to_supervisor_id"),
        telephone("telephone"),
        creditLine("credit_line"),
        lastCreditLine("last_credit_line"),
        isActive("is_active"),
        sign1("sign1"),
        sign2("sign2"),
        qqKey("qq_key"),
        weiboKey("weibo_key"),
        qrCode("qr_code"),
        ipsAcctNo("ips_acct_no"),
        ipsBidAuthNo("ips_bid_auth_no"),
        ipsRepayAuthNo("ips_repay_auth_no"),
        deviceUserId("device_user_id"),
        channelId("channel_id"),
        deviceType("device_type"),
        isBillPush("is_bill_push"),
        isInvestPush("is_invest_push"),
        isActivityPush("is_activity_push"),
        openId("open_id"),
        isInvestBid("is_invest_bid"),
        remark("remark"),
        label("label"),
        isCrown("is_crown"),
        realIdNumber("real_id_number"),
        type("type"),
        ipsIsAuth("ips_is_auth"),
        withdrawWay("withdraw_way"),
        mobileIdcardMd5("mobile_idcard_md5");

        private final String column;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        Column(String column) {
            this.column = column;
        }

        public String desc() {
            return this.column + " DESC";
        }

        public String asc() {
            return this.column + " ASC";
        }
    }
}