package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;

public class Feedback implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 图片路径，多张“;”间隔
     */
    private String picUrl;

    /**
     * 问题类型
     */
    private String questionType;

    /**
     * 问题描述
     */
    private String questionDesc;

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

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType == null ? null : questionType.trim();
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc == null ? null : questionDesc.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", picUrl=").append(picUrl);
        sb.append(", questionType=").append(questionType);
        sb.append(", questionDesc=").append(questionDesc);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static Feedback.Builder builder() {
        return new Feedback.Builder();
    }

    public static class Builder {
        private Feedback obj;

        public Builder() {
            this.obj = new Feedback();
        }

        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        public Builder userId(Long userId) {
            obj.setUserId(userId);
            return this;
        }

        public Builder picUrl(String picUrl) {
            obj.setPicUrl(picUrl);
            return this;
        }

        public Builder questionType(String questionType) {
            obj.setQuestionType(questionType);
            return this;
        }

        public Builder questionDesc(String questionDesc) {
            obj.setQuestionDesc(questionDesc);
            return this;
        }

        public Feedback build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id"),
        userId("user_id"),
        picUrl("pic_url"),
        questionType("question_type"),
        questionDesc("question_desc");

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