package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LoanBill implements Serializable {
    /**
     */
    private Long id;

    /**
     * 标的id
     */
    private Long bidId;

    /**
     * 期数
     */
    private Integer indexPeriods;

    /**
     * 应还时间
     */
    private LocalDateTime repaymentTime;

    /**
     * 应收金额
     */
    private BigDecimal repaymentAmount;

    /**
     * 结清时间(结清才更新)
     */
    private LocalDateTime settlementTime;

    /**
     * 实还总额
     */
    private BigDecimal realRepaymentAmount;

    /**
     * 状态 1-未结清 2-正常结清 3-逾期结清
     */
    private Byte status;

    /**
     * 部分还款标志 1-是 0-否
     */
    private Boolean isPartialRepayment;

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

    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    public Integer getIndexPeriods() {
        return indexPeriods;
    }

    public void setIndexPeriods(Integer indexPeriods) {
        this.indexPeriods = indexPeriods;
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

    public LocalDateTime getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(LocalDateTime settlementTime) {
        this.settlementTime = settlementTime;
    }

    public BigDecimal getRealRepaymentAmount() {
        return realRepaymentAmount;
    }

    public void setRealRepaymentAmount(BigDecimal realRepaymentAmount) {
        this.realRepaymentAmount = realRepaymentAmount;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Boolean getIsPartialRepayment() {
        return isPartialRepayment;
    }

    public void setIsPartialRepayment(Boolean isPartialRepayment) {
        this.isPartialRepayment = isPartialRepayment;
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
        sb.append(", bidId=").append(bidId);
        sb.append(", indexPeriods=").append(indexPeriods);
        sb.append(", repaymentTime=").append(repaymentTime);
        sb.append(", repaymentAmount=").append(repaymentAmount);
        sb.append(", settlementTime=").append(settlementTime);
        sb.append(", realRepaymentAmount=").append(realRepaymentAmount);
        sb.append(", status=").append(status);
        sb.append(", isPartialRepayment=").append(isPartialRepayment);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static LoanBill.Builder builder() {
        return new LoanBill.Builder();
    }

    public static class Builder {
        private LoanBill obj;

        public Builder() {
            this.obj = new LoanBill();
        }

        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        public Builder bidId(Long bidId) {
            obj.setBidId(bidId);
            return this;
        }

        public Builder indexPeriods(Integer indexPeriods) {
            obj.setIndexPeriods(indexPeriods);
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

        public Builder settlementTime(LocalDateTime settlementTime) {
            obj.setSettlementTime(settlementTime);
            return this;
        }

        public Builder realRepaymentAmount(BigDecimal realRepaymentAmount) {
            obj.setRealRepaymentAmount(realRepaymentAmount);
            return this;
        }

        public Builder status(Byte status) {
            obj.setStatus(status);
            return this;
        }

        public Builder isPartialRepayment(Boolean isPartialRepayment) {
            obj.setIsPartialRepayment(isPartialRepayment);
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

        public LoanBill build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id"),
        bidId("bid_id"),
        indexPeriods("index_periods"),
        repaymentTime("repayment_time"),
        repaymentAmount("repayment_amount"),
        settlementTime("settlement_time"),
        realRepaymentAmount("real_repayment_amount"),
        status("status"),
        isPartialRepayment("is_partial repayment"),
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