package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserStatus implements Serializable {
    /**
     */
    private Long id;

    /**
     * 用户状态
     */
    private Byte type;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 是否黑名单
     */
    private Boolean isBlacklist;

    /**
     * 是否锁定
     */
    private Boolean isLock;

    /**
     * 拉入黑名单时间
     */
    private LocalDateTime blacklistTime;

    /**
     * 锁定时间
     */
    private LocalDateTime lockTime;

    /**
     * 锁定操作人id
     */
    private Long lockBy;

    /**
     * 黑名单操作人id
     */
    private Long blacklistBy;

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

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getIsBlacklist() {
        return isBlacklist;
    }

    public void setIsBlacklist(Boolean isBlacklist) {
        this.isBlacklist = isBlacklist;
    }

    public Boolean getIsLock() {
        return isLock;
    }

    public void setIsLock(Boolean isLock) {
        this.isLock = isLock;
    }

    public LocalDateTime getBlacklistTime() {
        return blacklistTime;
    }

    public void setBlacklistTime(LocalDateTime blacklistTime) {
        this.blacklistTime = blacklistTime;
    }

    public LocalDateTime getLockTime() {
        return lockTime;
    }

    public void setLockTime(LocalDateTime lockTime) {
        this.lockTime = lockTime;
    }

    public Long getLockBy() {
        return lockBy;
    }

    public void setLockBy(Long lockBy) {
        this.lockBy = lockBy;
    }

    public Long getBlacklistBy() {
        return blacklistBy;
    }

    public void setBlacklistBy(Long blacklistBy) {
        this.blacklistBy = blacklistBy;
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
        sb.append(", type=").append(type);
        sb.append(", userId=").append(userId);
        sb.append(", isBlacklist=").append(isBlacklist);
        sb.append(", isLock=").append(isLock);
        sb.append(", blacklistTime=").append(blacklistTime);
        sb.append(", lockTime=").append(lockTime);
        sb.append(", lockBy=").append(lockBy);
        sb.append(", blacklistBy=").append(blacklistBy);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static UserStatus.Builder builder() {
        return new UserStatus.Builder();
    }

    public static class Builder {
        private UserStatus obj;

        public Builder() {
            this.obj = new UserStatus();
        }

        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        public Builder type(Byte type) {
            obj.setType(type);
            return this;
        }

        public Builder userId(Long userId) {
            obj.setUserId(userId);
            return this;
        }

        public Builder isBlacklist(Boolean isBlacklist) {
            obj.setIsBlacklist(isBlacklist);
            return this;
        }

        public Builder isLock(Boolean isLock) {
            obj.setIsLock(isLock);
            return this;
        }

        public Builder blacklistTime(LocalDateTime blacklistTime) {
            obj.setBlacklistTime(blacklistTime);
            return this;
        }

        public Builder lockTime(LocalDateTime lockTime) {
            obj.setLockTime(lockTime);
            return this;
        }

        public Builder lockBy(Long lockBy) {
            obj.setLockBy(lockBy);
            return this;
        }

        public Builder blacklistBy(Long blacklistBy) {
            obj.setBlacklistBy(blacklistBy);
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

        public UserStatus build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id"),
        type("type"),
        userId("user_id"),
        isBlacklist("is_blacklist"),
        isLock("is_lock"),
        blacklistTime("blacklist_time"),
        lockTime("lock_time"),
        lockBy("lock_by"),
        blacklistBy("blacklist_by"),
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