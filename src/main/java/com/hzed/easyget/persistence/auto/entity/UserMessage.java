package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserMessage implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String message;

    /**
     * 是否已读
     */
    private Boolean hasRead;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建者
     */
    private Long createBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 更新者
     */
    private Long updateBy;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Boolean getHasRead() {
        return hasRead;
    }

    public void setHasRead(Boolean hasRead) {
        this.hasRead = hasRead;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
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
        sb.append(", userId=").append(userId);
        sb.append(", title=").append(title);
        sb.append(", message=").append(message);
        sb.append(", hasRead=").append(hasRead);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static UserMessage.Builder builder() {
        return new UserMessage.Builder();
    }

    public static class Builder {
        private UserMessage obj;

        public Builder() {
            this.obj = new UserMessage();
        }

        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        public Builder userId(Long userId) {
            obj.setUserId(userId);
            return this;
        }

        public Builder title(String title) {
            obj.setTitle(title);
            return this;
        }

        public Builder message(String message) {
            obj.setMessage(message);
            return this;
        }

        public Builder hasRead(Boolean hasRead) {
            obj.setHasRead(hasRead);
            return this;
        }

        public Builder createTime(LocalDateTime createTime) {
            obj.setCreateTime(createTime);
            return this;
        }

        public Builder createBy(Long createBy) {
            obj.setCreateBy(createBy);
            return this;
        }

        public Builder updateTime(LocalDateTime updateTime) {
            obj.setUpdateTime(updateTime);
            return this;
        }

        public Builder updateBy(Long updateBy) {
            obj.setUpdateBy(updateBy);
            return this;
        }

        public Builder remark(String remark) {
            obj.setRemark(remark);
            return this;
        }

        public UserMessage build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id"),
        userId("user_id"),
        title("title"),
        message("message"),
        hasRead("has_read"),
        createTime("create_time"),
        createBy("create_by"),
        updateTime("update_time"),
        updateBy("update_by"),
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