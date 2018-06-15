package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserTransactionRepayPic implements Serializable {
    /**
     */
    private Long id;

    /**
     */
    private Long transactionRepayId;

    /**
     * 凭证地址
     */
    private String evidencePicUrl;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTransactionRepayId() {
        return transactionRepayId;
    }

    public void setTransactionRepayId(Long transactionRepayId) {
        this.transactionRepayId = transactionRepayId;
    }

    public String getEvidencePicUrl() {
        return evidencePicUrl;
    }

    public void setEvidencePicUrl(String evidencePicUrl) {
        this.evidencePicUrl = evidencePicUrl == null ? null : evidencePicUrl.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", transactionRepayId=").append(transactionRepayId);
        sb.append(", evidencePicUrl=").append(evidencePicUrl);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static UserTransactionRepayPic.Builder builder() {
        return new UserTransactionRepayPic.Builder();
    }

    public static class Builder {
        private UserTransactionRepayPic obj;

        public Builder() {
            this.obj = new UserTransactionRepayPic();
        }

        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        public Builder transactionRepayId(Long transactionRepayId) {
            obj.setTransactionRepayId(transactionRepayId);
            return this;
        }

        public Builder evidencePicUrl(String evidencePicUrl) {
            obj.setEvidencePicUrl(evidencePicUrl);
            return this;
        }

        public Builder createTime(LocalDateTime createTime) {
            obj.setCreateTime(createTime);
            return this;
        }

        public UserTransactionRepayPic build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id"),
        transactionRepayId("transaction_repay_id"),
        evidencePicUrl("evidence_pic_url"),
        createTime("create_time");

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