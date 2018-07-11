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
    private Byte userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String message;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 是否已读
     */
    private Boolean read;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getUserId() {
        return userId;
    }

    public void setUserId(Byte userId) {
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
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
        sb.append(", createTime=").append(createTime);
        sb.append(", read=").append(read);
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

        public Builder userId(Byte userId) {
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

        public Builder createTime(LocalDateTime createTime) {
            obj.setCreateTime(createTime);
            return this;
        }

        public Builder read(Boolean read) {
            obj.setRead(read);
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
        createTime("create_time"),
        read("read");

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