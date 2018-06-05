package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LoanBidProgress implements Serializable {
    /**
     */
    private Long id;

    /**
     * 标id
     */
    private Long bidId;

    /**
     * 处理类型 1-审核 2-放款 3-结清
     */
    private Byte type;

    /**
     * 处理时间
     */
    private LocalDateTime handleTime;

    /**
     * 处理结果描述
     */
    private String handleResult;

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

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public LocalDateTime getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(LocalDateTime handleTime) {
        this.handleTime = handleTime;
    }

    public String getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult == null ? null : handleResult.trim();
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
        sb.append(", type=").append(type);
        sb.append(", handleTime=").append(handleTime);
        sb.append(", handleResult=").append(handleResult);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static LoanBidProgress.Builder builder() {
        return new LoanBidProgress.Builder();
    }

    public static class Builder {
        private LoanBidProgress obj;

        public Builder() {
            this.obj = new LoanBidProgress();
        }

        public Builder id(Long id) {
            obj.setId(id);
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

        public Builder handleTime(LocalDateTime handleTime) {
            obj.setHandleTime(handleTime);
            return this;
        }

        public Builder handleResult(String handleResult) {
            obj.setHandleResult(handleResult);
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

        public LoanBidProgress build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id"),
        bidId("bid_id"),
        type("type"),
        handleTime("handle_time"),
        handleResult("handle_result"),
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