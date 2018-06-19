package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RepayInfoFlowJob implements Serializable {
    /**
     */
    private Long id;

    /**
     * 交易表id
     */
    private Long transactionId;

    /**
     * 标id
     */
    private Long bidId;

    /**
     * 还款金额
     */
    private BigDecimal repaymentAmount;

    /**
     * 实际还款时间
     */
    private LocalDateTime realRepaymentTime;

    /**
     * 还款类型 1-线上还款 2-线下还款
     */
    private Byte repaymentMode;

    /**
     * 还款方式 1-全部结清 2-部分还款
     */
    private Byte repaymentType;

    /**
     * 状态 1-待处理 2-处理失败 3-处理成功
     */
    private Byte status;

    /**
     * 处理次数
     */
    private Byte times;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 备注 如处理失败原因
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

    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    public BigDecimal getRepaymentAmount() {
        return repaymentAmount;
    }

    public void setRepaymentAmount(BigDecimal repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
    }

    public LocalDateTime getRealRepaymentTime() {
        return realRepaymentTime;
    }

    public void setRealRepaymentTime(LocalDateTime realRepaymentTime) {
        this.realRepaymentTime = realRepaymentTime;
    }

    public Byte getRepaymentMode() {
        return repaymentMode;
    }

    public void setRepaymentMode(Byte repaymentMode) {
        this.repaymentMode = repaymentMode;
    }

    public Byte getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(Byte repaymentType) {
        this.repaymentType = repaymentType;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getTimes() {
        return times;
    }

    public void setTimes(Byte times) {
        this.times = times;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
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
        sb.append(", bidId=").append(bidId);
        sb.append(", repaymentAmount=").append(repaymentAmount);
        sb.append(", realRepaymentTime=").append(realRepaymentTime);
        sb.append(", repaymentMode=").append(repaymentMode);
        sb.append(", repaymentType=").append(repaymentType);
        sb.append(", status=").append(status);
        sb.append(", times=").append(times);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static RepayInfoFlowJob.Builder builder() {
        return new RepayInfoFlowJob.Builder();
    }

    public static class Builder {
        private RepayInfoFlowJob obj;

        public Builder() {
            this.obj = new RepayInfoFlowJob();
        }

        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        public Builder transactionId(Long transactionId) {
            obj.setTransactionId(transactionId);
            return this;
        }

        public Builder bidId(Long bidId) {
            obj.setBidId(bidId);
            return this;
        }

        public Builder repaymentAmount(BigDecimal repaymentAmount) {
            obj.setRepaymentAmount(repaymentAmount);
            return this;
        }

        public Builder realRepaymentTime(LocalDateTime realRepaymentTime) {
            obj.setRealRepaymentTime(realRepaymentTime);
            return this;
        }

        public Builder repaymentMode(Byte repaymentMode) {
            obj.setRepaymentMode(repaymentMode);
            return this;
        }

        public Builder repaymentType(Byte repaymentType) {
            obj.setRepaymentType(repaymentType);
            return this;
        }

        public Builder status(Byte status) {
            obj.setStatus(status);
            return this;
        }

        public Builder times(Byte times) {
            obj.setTimes(times);
            return this;
        }

        public Builder createTime(LocalDateTime createTime) {
            obj.setCreateTime(createTime);
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

        public RepayInfoFlowJob build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id"),
        transactionId("transaction_id"),
        bidId("bid_id"),
        repaymentAmount("repayment_amount"),
        realRepaymentTime("real_repayment_time"),
        repaymentMode("repayment_mode"),
        repaymentType("repayment_type"),
        status("status"),
        times("times"),
        createTime("create_time"),
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