package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Work implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 工作类型
     */
    private String jobType;

    /**
     * 月收入
     */
    private String monthlyIncome;

    /**
     * 工作证照片保存路径
     */
    private String employeeCard;

    /**
     * 工作场所照片保存路径
     */
    private String workplace;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType == null ? null : jobType.trim();
    }

    public String getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(String monthlyIncome) {
        this.monthlyIncome = monthlyIncome == null ? null : monthlyIncome.trim();
    }

    public String getEmployeeCard() {
        return employeeCard;
    }

    public void setEmployeeCard(String employeeCard) {
        this.employeeCard = employeeCard == null ? null : employeeCard.trim();
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace == null ? null : workplace.trim();
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
        sb.append(", jobType=").append(jobType);
        sb.append(", monthlyIncome=").append(monthlyIncome);
        sb.append(", employeeCard=").append(employeeCard);
        sb.append(", workplace=").append(workplace);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static Work.Builder builder() {
        return new Work.Builder();
    }

    public static class Builder {
        private Work obj;

        public Builder() {
            this.obj = new Work();
        }

        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        public Builder userId(Long userId) {
            obj.setUserId(userId);
            return this;
        }

        public Builder jobType(String jobType) {
            obj.setJobType(jobType);
            return this;
        }

        public Builder monthlyIncome(String monthlyIncome) {
            obj.setMonthlyIncome(monthlyIncome);
            return this;
        }

        public Builder employeeCard(String employeeCard) {
            obj.setEmployeeCard(employeeCard);
            return this;
        }

        public Builder workplace(String workplace) {
            obj.setWorkplace(workplace);
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

        public Work build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id"),
        userId("user_id"),
        jobType("job_type"),
        monthlyIncome("monthly_income"),
        employeeCard("employee_card"),
        workplace("workplace"),
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