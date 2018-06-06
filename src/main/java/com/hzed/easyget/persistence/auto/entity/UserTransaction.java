package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UserTransaction implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 标id
     */
    private Long bidId;

    /**
     * 交易类型 1-入账 2-出账 3-其他
     */
    private Byte type;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 交易流水号
     */
    private String requestSeq;

    /**
     * 交易银行
     */
    private String bank;

    /**
     * 交易账号
     */
    private String account;

    /**
     * 交易方式 1-APP 2-ATM 3-other
     */
    private Byte mode;

    /**
     * 是否显示 1-是 0-否
     */
    private Boolean isDisplay;

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
     * 交易备注
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

    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRequestSeq() {
        return requestSeq;
    }

    public void setRequestSeq(String requestSeq) {
        this.requestSeq = requestSeq == null ? null : requestSeq.trim();
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Byte getMode() {
        return mode;
    }

    public void setMode(Byte mode) {
        this.mode = mode;
    }

    public Boolean getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Boolean isDisplay) {
        this.isDisplay = isDisplay;
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
        sb.append(", userId=").append(userId);
        sb.append(", bidId=").append(bidId);
        sb.append(", type=").append(type);
        sb.append(", amount=").append(amount);
        sb.append(", requestSeq=").append(requestSeq);
        sb.append(", bank=").append(bank);
        sb.append(", account=").append(account);
        sb.append(", mode=").append(mode);
        sb.append(", isDisplay=").append(isDisplay);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static UserTransaction.Builder builder() {
        return new UserTransaction.Builder();
    }

    public static class Builder {
        private UserTransaction obj;

        public Builder() {
            this.obj = new UserTransaction();
        }

        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        public Builder userId(Long userId) {
            obj.setUserId(userId);
            return this;
        }

        public Builder bidId(Long bidId) {
            obj.setBidId(bidId);
            return this;
        }

        public Builder type(Byte type) {
            obj.setType(type);
            return this;
        }

        public Builder amount(BigDecimal amount) {
            obj.setAmount(amount);
            return this;
        }

        public Builder requestSeq(String requestSeq) {
            obj.setRequestSeq(requestSeq);
            return this;
        }

        public Builder bank(String bank) {
            obj.setBank(bank);
            return this;
        }

        public Builder account(String account) {
            obj.setAccount(account);
            return this;
        }

        public Builder mode(Byte mode) {
            obj.setMode(mode);
            return this;
        }

        public Builder isDisplay(Boolean isDisplay) {
            obj.setIsDisplay(isDisplay);
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

        public UserTransaction build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id"),
        userId("user_id"),
        bidId("bid_id"),
        type("type"),
        amount("amount"),
        requestSeq("request_seq"),
        bank("bank"),
        account("account"),
        mode("mode"),
        isDisplay("is_display"),
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