package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BillLedger implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 账单id
     */
    private Long billId;

    /**
     * 应还时间
     */
    private LocalDateTime repaymentTime;

    /**
     * 应还金额
     */
    private BigDecimal repaymentAmount;

    /**
     * 应收科目 1-本金 2-利息 3-砍头息 4-尾款 5-逾期费
     */
    private Byte repaymentItem;

    /**
     * 实还时间
     */
    private LocalDateTime realRepaymentTime;

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

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public LocalDateTime getRepaymentTime() {
        return repaymentTime;
    }

    public void setRepaymentTime(LocalDateTime repaymentTime) {
        this.repaymentTime = repaymentTime;
    }

    public BigDecimal getRepaymentAmount() {
        return repaymentAmount;
    }

    public void setRepaymentAmount(BigDecimal repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
    }

    public Byte getRepaymentItem() {
        return repaymentItem;
    }

    public void setRepaymentItem(Byte repaymentItem) {
        this.repaymentItem = repaymentItem;
    }

    public LocalDateTime getRealRepaymentTime() {
        return realRepaymentTime;
    }

    public void setRealRepaymentTime(LocalDateTime realRepaymentTime) {
        this.realRepaymentTime = realRepaymentTime;
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
        sb.append(", billId=").append(billId);
        sb.append(", repaymentTime=").append(repaymentTime);
        sb.append(", repaymentAmount=").append(repaymentAmount);
        sb.append(", repaymentItem=").append(repaymentItem);
        sb.append(", realRepaymentTime=").append(realRepaymentTime);
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

    public static BillLedger.Builder builder() {
        return new BillLedger.Builder();
    }

    public static class Builder {
        private BillLedger obj;

        public Builder() {
            this.obj = new BillLedger();
        }

        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        public Builder billId(Long billId) {
            obj.setBillId(billId);
            return this;
        }

        public Builder repaymentTime(LocalDateTime repaymentTime) {
            obj.setRepaymentTime(repaymentTime);
            return this;
        }

        public Builder repaymentAmount(BigDecimal repaymentAmount) {
            obj.setRepaymentAmount(repaymentAmount);
            return this;
        }

        public Builder repaymentItem(Byte repaymentItem) {
            obj.setRepaymentItem(repaymentItem);
            return this;
        }

        public Builder realRepaymentTime(LocalDateTime realRepaymentTime) {
            obj.setRealRepaymentTime(realRepaymentTime);
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

        public BillLedger build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id"),
        billId("bill_id"),
        repaymentTime("repayment_time"),
        repaymentAmount("repayment_amount"),
        repaymentItem("repayment_item"),
        realRepaymentTime("real_repayment_time"),
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