package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BidDetailFee implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 标id
     */
    private Long bidId;

    /**
     * 1-信息认证费 2-信息审核费 3-手续费
     */
    private Byte feeType;

    /**
     * 费用
     */
    private BigDecimal fee;

    /**
     * 创建时间
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

    public Byte getFeeType() {
        return feeType;
    }

    public void setFeeType(Byte feeType) {
        this.feeType = feeType;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
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
        sb.append(", feeType=").append(feeType);
        sb.append(", fee=").append(fee);
        sb.append(", createTime=").append(createTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static BidDetailFee.Builder builder() {
        return new BidDetailFee.Builder();
    }

    public static class Builder {
        private BidDetailFee obj;

        public Builder() {
            this.obj = new BidDetailFee();
        }

        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        public Builder bidId(Long bidId) {
            obj.setBidId(bidId);
            return this;
        }

        public Builder feeType(Byte feeType) {
            obj.setFeeType(feeType);
            return this;
        }

        public Builder fee(BigDecimal fee) {
            obj.setFee(fee);
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

        public BidDetailFee build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id"),
        bidId("bid_id"),
        feeType("fee_type"),
        fee("fee"),
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