package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;
import java.util.Date;

public class SafekeepReport implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 标的no
     */
    private String bidNo;

    /**
     * 2-借款中(审核通过);3-待放款(放款审核通过);4-还款中(财务放款);APP显示是使用中5-已还款;app显示是已结清
     */
    private Integer bidStatus;

    /**
     * 流水号
     */
    private String requestNo;

    /**
     * 富有状态 00-筹标中 01-满标放款 02-还款 03-正常完结 04-逾期 05-流标 06-逾期完结
     */
    private String fyStatus;

    /**
     * 推送状态 0-待推送 1-已推送
     */
    private Integer pushStatus;

    /**
     */
    private Long createBy;

    /**
     */
    private Date createTime;

    /**
     */
    private Long updateBy;

    /**
     */
    private Date updateTime;

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

    public String getBidNo() {
        return bidNo;
    }

    public void setBidNo(String bidNo) {
        this.bidNo = bidNo == null ? null : bidNo.trim();
    }

    public Integer getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(Integer bidStatus) {
        this.bidStatus = bidStatus;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo == null ? null : requestNo.trim();
    }

    public String getFyStatus() {
        return fyStatus;
    }

    public void setFyStatus(String fyStatus) {
        this.fyStatus = fyStatus == null ? null : fyStatus.trim();
    }

    public Integer getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(Integer pushStatus) {
        this.pushStatus = pushStatus;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
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
        sb.append(", bidNo=").append(bidNo);
        sb.append(", bidStatus=").append(bidStatus);
        sb.append(", requestNo=").append(requestNo);
        sb.append(", fyStatus=").append(fyStatus);
        sb.append(", pushStatus=").append(pushStatus);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static SafekeepReport.Builder builder() {
        return new SafekeepReport.Builder();
    }

    public static class Builder {
        private SafekeepReport obj;

        public Builder() {
            this.obj = new SafekeepReport();
        }

        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        public Builder bidNo(String bidNo) {
            obj.setBidNo(bidNo);
            return this;
        }

        public Builder bidStatus(Integer bidStatus) {
            obj.setBidStatus(bidStatus);
            return this;
        }

        public Builder requestNo(String requestNo) {
            obj.setRequestNo(requestNo);
            return this;
        }

        public Builder fyStatus(String fyStatus) {
            obj.setFyStatus(fyStatus);
            return this;
        }

        public Builder pushStatus(Integer pushStatus) {
            obj.setPushStatus(pushStatus);
            return this;
        }

        public Builder createBy(Long createBy) {
            obj.setCreateBy(createBy);
            return this;
        }

        public Builder createTime(Date createTime) {
            obj.setCreateTime(createTime);
            return this;
        }

        public Builder updateBy(Long updateBy) {
            obj.setUpdateBy(updateBy);
            return this;
        }

        public Builder updateTime(Date updateTime) {
            obj.setUpdateTime(updateTime);
            return this;
        }

        public Builder remark(String remark) {
            obj.setRemark(remark);
            return this;
        }

        public SafekeepReport build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id"),
        bidNo("bid_no"),
        bidStatus("bid_status"),
        requestNo("request_no"),
        fyStatus("fy_status"),
        pushStatus("push_status"),
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