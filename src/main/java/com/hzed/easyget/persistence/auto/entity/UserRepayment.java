package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UserRepayment implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 交易表id
     */
    private Long transactionId;

    /**
     * 账单id
     */
    private Long billId;

    /**
     * 应收科目 1-本金 2-利息 3-尾款 4-逾期费
     */
    private Byte repaymentItem;

    /**
     * 应还金额
     */
    private BigDecimal repaymentAmount;

    /**
     * 实还金额
     */
    private BigDecimal realRepaymentAmount;

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

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Byte getRepaymentItem() {
        return repaymentItem;
    }

    public void setRepaymentItem(Byte repaymentItem) {
        this.repaymentItem = repaymentItem;
    }

    public BigDecimal getRepaymentAmount() {
        return repaymentAmount;
    }

    public void setRepaymentAmount(BigDecimal repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
    }

    public BigDecimal getRealRepaymentAmount() {
        return realRepaymentAmount;
    }

    public void setRealRepaymentAmount(BigDecimal realRepaymentAmount) {
        this.realRepaymentAmount = realRepaymentAmount;
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
        sb.append(", transactionId=").append(transactionId);
        sb.append(", billId=").append(billId);
        sb.append(", repaymentItem=").append(repaymentItem);
        sb.append(", repaymentAmount=").append(repaymentAmount);
        sb.append(", realRepaymentAmount=").append(realRepaymentAmount);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static UserRepayment.Builder builder() {
        return new UserRepayment.Builder();
    }

    public static class Builder {
        private UserRepayment obj;

        public Builder() {
            this.obj = new UserRepayment();
        }

        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        public Builder transactionId(Long transactionId) {
            obj.setTransactionId(transactionId);
            return this;
        }

        public Builder billId(Long billId) {
            obj.setBillId(billId);
            return this;
        }

        public Builder repaymentItem(Byte repaymentItem) {
            obj.setRepaymentItem(repaymentItem);
            return this;
        }

        public Builder repaymentAmount(BigDecimal repaymentAmount) {
            obj.setRepaymentAmount(repaymentAmount);
            return this;
        }

        public Builder realRepaymentAmount(BigDecimal realRepaymentAmount) {
            obj.setRealRepaymentAmount(realRepaymentAmount);
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

        public UserRepayment build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id"),
        transactionId("transaction_id"),
        billId("bill_id"),
        repaymentItem("repayment_item"),
        repaymentAmount("repayment_amount"),
        realRepaymentAmount("real_repayment_amount"),
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