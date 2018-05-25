package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class User implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 手机号账号
     */
    private String mobileAccount;

    /**
     * 密码（加密保存）
     */
    private String password;

    /**
     * 用户头像
     */
    private String profilePhoto;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 性别 0-未知 1-男 2-女 3-女改男 4-男改女 5-其他
     */
    private String gender;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 身份证号
     */
    private String idCardNo;

    /**
     * 注册平台 android、ios等等
     */
    private String platform;

    /**
     * 锁定状态 0-解锁 1-锁定
     */
    private Boolean isLocked;

    /**
     * 是否黑名单 0-否 1-是
     */
    private Boolean isBlacklist;

    /**
     * 注册渠道 1-印尼APP
     */
    private Byte client;

    /**
     * 服务运营商密码
     */
    private String smsPassword;

    /**
     */
    private Long createBy;

    /**
     */
    private LocalDateTime createTime;

    /**
     */
    private Long updateBy;

    /**
     */
    private LocalDateTime updateTime;

    /**
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobileAccount() {
        return mobileAccount;
    }

    public void setMobileAccount(String mobileAccount) {
        this.mobileAccount = mobileAccount == null ? null : mobileAccount.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto == null ? null : profilePhoto.trim();
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo == null ? null : idCardNo.trim();
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }

    public Boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    public Boolean getIsBlacklist() {
        return isBlacklist;
    }

    public void setIsBlacklist(Boolean isBlacklist) {
        this.isBlacklist = isBlacklist;
    }

    public Byte getClient() {
        return client;
    }

    public void setClient(Byte client) {
        this.client = client;
    }

    public String getSmsPassword() {
        return smsPassword;
    }

    public void setSmsPassword(String smsPassword) {
        this.smsPassword = smsPassword == null ? null : smsPassword.trim();
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", mobileAccount=").append(mobileAccount);
        sb.append(", password=").append(password);
        sb.append(", profilePhoto=").append(profilePhoto);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", gender=").append(gender);
        sb.append(", realName=").append(realName);
        sb.append(", idCardNo=").append(idCardNo);
        sb.append(", platform=").append(platform);
        sb.append(", isLocked=").append(isLocked);
        sb.append(", isBlacklist=").append(isBlacklist);
        sb.append(", client=").append(client);
        sb.append(", smsPassword=").append(smsPassword);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
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

        public Builder mobileAccount(String mobileAccount) {
            obj.setMobileAccount(mobileAccount);
            return this;
        }

        public Builder password(String password) {
            obj.setPassword(password);
            return this;
        }

        public Builder profilePhoto(String profilePhoto) {
            obj.setProfilePhoto(profilePhoto);
            return this;
        }

        public Builder lastLoginTime(LocalDateTime lastLoginTime) {
            obj.setLastLoginTime(lastLoginTime);
            return this;
        }

        public Builder gender(String gender) {
            obj.setGender(gender);
            return this;
        }

        public Builder realName(String realName) {
            obj.setRealName(realName);
            return this;
        }

        public Builder idCardNo(String idCardNo) {
            obj.setIdCardNo(idCardNo);
            return this;
        }

        public Builder platform(String platform) {
            obj.setPlatform(platform);
            return this;
        }

        public Builder isLocked(Boolean isLocked) {
            obj.setIsLocked(isLocked);
            return this;
        }

        public Builder isBlacklist(Boolean isBlacklist) {
            obj.setIsBlacklist(isBlacklist);
            return this;
        }

        public Builder client(Byte client) {
            obj.setClient(client);
            return this;
        }

        public Builder smsPassword(String smsPassword) {
            obj.setSmsPassword(smsPassword);
            return this;
        }

        public Builder createBy(Long createBy) {
            obj.setCreateBy(createBy);
            return this;
        }

        public Builder createTime(LocalDateTime createTime) {
            obj.setCreateTime(createTime);
            return this;
        }

        public Builder updateBy(Long updateBy) {
            obj.setUpdateBy(updateBy);
            return this;
        }

        public Builder updateTime(LocalDateTime updateTime) {
            obj.setUpdateTime(updateTime);
            return this;
        }

        public Builder remark(String remark) {
            obj.setRemark(remark);
            return this;
        }

        public User build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id"),
        mobileAccount("mobile_account"),
        password("password"),
        profilePhoto("profile_photo"),
        lastLoginTime("last_login_time"),
        gender("gender"),
        realName("real_name"),
        idCardNo("id_card_no"),
        platform("platform"),
        isLocked("is_locked"),
        isBlacklist("is_blacklist"),
        client("client"),
        smsPassword("sms_password"),
        createBy("create_by"),
        createTime("create_time"),
        updateBy("update_by"),
        updateTime("update_time"),
        remark("remark");

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