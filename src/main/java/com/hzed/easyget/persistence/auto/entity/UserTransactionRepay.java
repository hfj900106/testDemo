package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserTransactionRepay implements Serializable {
    /**
     */
    private Long id;

    /**
     * t_loan_user_transaction的id
     */
    private Long transactionId;

    /**
     * 交易方式 BNI ATM ;OTC
     */
    private String mode;

    /**
     * VA码
     */
    private String va;

    /**
     * VA码创建时间
     */
    private LocalDateTime vaCreateTime;

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

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
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

    public LocalDateTime getVaCreateTime() {
        return vaCreateTime;
    }

    public void setVaCreateTime(LocalDateTime vaCreateTime) {
        this.vaCreateTime = vaCreateTime;
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
        sb.append(", transactionId=").append(transactionId);
        sb.append(", mode=").append(mode);
        sb.append(", va=").append(va);
        sb.append(", vaCreateTime=").append(vaCreateTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static UserTransactionRepay.Builder builder() {
        return new UserTransactionRepay.Builder();
    }

    public static class Builder {
        private UserTransactionRepay obj;

        public Builder() {
            this.obj = new UserTransactionRepay();
        }

        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        public Builder transactionId(Long transactionId) {
            obj.setTransactionId(transactionId);
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

        public Builder vaCreateTime(LocalDateTime vaCreateTime) {
            obj.setVaCreateTime(vaCreateTime);
            return this;
        }

        public Builder remark(String remark) {
            obj.setRemark(remark);
            return this;
        }

        public UserTransactionRepay build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id"),
        transactionId("transaction_id"),
        mode("mode"),
        va("va"),
        vaCreateTime("va_create_time"),
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