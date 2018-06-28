package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserTransactionPic implements Serializable {
    /**
     */
    private Long id;

    /**
     * 标ID
     */
    private Long bidId;

    /**
     * 凭证图片地址
     */
    private String evidencePicUrl;

    /**
     * 交易方式 BNI ATM ;OTC
     */
    private String mode;

    /**
     * 还款码
     */
    private String va;

    /**
     */
    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    public String getEvidencePicUrl() {
        return evidencePicUrl;
    }

    public void setEvidencePicUrl(String evidencePicUrl) {
        this.evidencePicUrl = evidencePicUrl == null ? null : evidencePicUrl.trim();
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode == null ? null : mode.trim();
    }

    public String getVa() {
        return va;
    }

    public void setVa(String va) {
        this.va = va == null ? null : va.trim();
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
        sb.append(", bidId=").append(bidId);
        sb.append(", evidencePicUrl=").append(evidencePicUrl);
        sb.append(", mode=").append(mode);
        sb.append(", va=").append(va);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static UserTransactionPic.Builder builder() {
        return new UserTransactionPic.Builder();
    }

    public static class Builder {
        private UserTransactionPic obj;

        public Builder() {
            this.obj = new UserTransactionPic();
        }

        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        public Builder bidId(Long bidId) {
            obj.setBidId(bidId);
            return this;
        }

        public Builder evidencePicUrl(String evidencePicUrl) {
            obj.setEvidencePicUrl(evidencePicUrl);
            return this;
        }

        public Builder mode(String mode) {
            obj.setMode(mode);
            return this;
        }

        public Builder va(String va) {
            obj.setVa(va);
            return this;
        }

        public Builder createTime(LocalDateTime createTime) {
            obj.setCreateTime(createTime);
            return this;
        }

        public UserTransactionPic build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id"),
        bidId("bid_id"),
        evidencePicUrl("evidence_pic_url"),
        mode("mode"),
        va("va"),
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