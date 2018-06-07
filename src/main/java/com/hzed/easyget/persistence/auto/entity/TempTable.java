package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TempTable implements Serializable {
    /**
     */
    private Long id;

    /**
     * 关联id
     */
    private Long relaseId;

    /**
     * 定时任务名称
     */
    private String jobName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 重跑次数
     */
    private Byte reRunTimes;

    /**
     * 定时任务描述
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRelaseId() {
        return relaseId;
    }

    public void setRelaseId(Long relaseId) {
        this.relaseId = relaseId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Byte getReRunTimes() {
        return reRunTimes;
    }

    public void setReRunTimes(Byte reRunTimes) {
        this.reRunTimes = reRunTimes;
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
        sb.append(", relaseId=").append(relaseId);
        sb.append(", jobName=").append(jobName);
        sb.append(", createTime=").append(createTime);
        sb.append(", reRunTimes=").append(reRunTimes);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static TempTable.Builder builder() {
        return new TempTable.Builder();
    }

    public static class Builder {
        private TempTable obj;

        public Builder() {
            this.obj = new TempTable();
        }

        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        public Builder relaseId(Long relaseId) {
            obj.setRelaseId(relaseId);
            return this;
        }

        public Builder jobName(String jobName) {
            obj.setJobName(jobName);
            return this;
        }

        public Builder createTime(LocalDateTime createTime) {
            obj.setCreateTime(createTime);
            return this;
        }

        public Builder reRunTimes(Byte reRunTimes) {
            obj.setReRunTimes(reRunTimes);
            return this;
        }

        public Builder remark(String remark) {
            obj.setRemark(remark);
            return this;
        }

        public TempTable build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id"),
        relaseId("relase_id"),
        jobName("job_name"),
        createTime("create_time"),
        reRunTimes("re_run_times"),
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