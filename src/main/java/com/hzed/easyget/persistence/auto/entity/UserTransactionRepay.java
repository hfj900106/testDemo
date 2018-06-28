package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UserTransactionRepay implements Serializable {
    /**
     */
    private Long id;

    /**
     * 标id
     */
    private Long bidId;

    /**
     * bluepay交易id
     */
    private String paymentId;

    /**
     * 交易金额
     */
    private BigDecimal amount;

    /**
     * 还款日期,没有时分秒
     */
    private LocalDateTime repaymentTime;

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
     * VA码失效时间
     */
    private LocalDateTime vaExpireTime;

    /**
     * 交易状态 1-初始状态 2-交易成功 3-交易失败
     */
    private Byte status;

    /**
     */
    private LocalDateTime createTime;

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

    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId == null ? null : paymentId.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getRepaymentTime() {
        return repaymentTime;
    }

    public void setRepaymentTime(LocalDateTime repaymentTime) {
        this.repaymentTime = repaymentTime;
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

    public LocalDateTime getVaExpireTime() {
        return vaExpireTime;
    }

    public void setVaExpireTime(LocalDateTime vaExpireTime) {
        this.vaExpireTime = vaExpireTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
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
        sb.append(", bidId=").append(bidId);
        sb.append(", paymentId=").append(paymentId);
        sb.append(", amount=").append(amount);
        sb.append(", repaymentTime=").append(repaymentTime);
        sb.append(", mode=").append(mode);
        sb.append(", va=").append(va);
        sb.append(", vaCreateTime=").append(vaCreateTime);
        sb.append(", vaExpireTime=").append(vaExpireTime);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
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

        public Builder bidId(Long bidId) {
            obj.setBidId(bidId);
            return this;
        }

        public Builder paymentId(String paymentId) {
            obj.setPaymentId(paymentId);
            return this;
        }

        public Builder amount(BigDecimal amount) {
            obj.setAmount(amount);
            return this;
        }

        public Builder repaymentTime(LocalDateTime repaymentTime) {
            obj.setRepaymentTime(repaymentTime);
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

        public Builder vaExpireTime(LocalDateTime vaExpireTime) {
            obj.setVaExpireTime(vaExpireTime);
            return this;
        }

        public Builder status(Byte status) {
            obj.setStatus(status);
            return this;
        }

        public Builder createTime(LocalDateTime createTime) {
            obj.setCreateTime(createTime);
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
        bidId("bid_id"),
        paymentId("payment_id"),
        amount("amount"),
        repaymentTime("repayment_time"),
        mode("mode"),
        va("va"),
        vaCreateTime("va_create_time"),
        vaExpireTime("va_expire_time"),
        status("status"),
        createTime("create_time"),
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