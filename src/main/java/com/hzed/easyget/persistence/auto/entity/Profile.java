package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Profile implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 教育信息
     */
    private String education;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司电话
     */
    private String companyTel;

    /**
     * 单位地址
     */
    private String companyAddr;

    /**
     * 单位详细地址
     */
    private String companyAddrDetail;

    /**
     * 个人邮件
     */
    private String email;

    /**
     * 孩子个数
     */
    private String childrenNumber;

    /**
     * 婚姻状态
     */
    private String maritalStatus;

    /**
     * 亲生母亲姓名
     */
    private String birthMotherName;

    /**
     * 父亲/母亲/兄弟/配偶
     */
    private String relationship1;

    /**
     * 同学/同事/朋友
     */
    private String relationship2;

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

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel == null ? null : companyTel.trim();
    }

    public String getCompanyAddr() {
        return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr == null ? null : companyAddr.trim();
    }

    public String getCompanyAddrDetail() {
        return companyAddrDetail;
    }

    public void setCompanyAddrDetail(String companyAddrDetail) {
        this.companyAddrDetail = companyAddrDetail == null ? null : companyAddrDetail.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getChildrenNumber() {
        return childrenNumber;
    }

    public void setChildrenNumber(String childrenNumber) {
        this.childrenNumber = childrenNumber == null ? null : childrenNumber.trim();
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus == null ? null : maritalStatus.trim();
    }

    public String getBirthMotherName() {
        return birthMotherName;
    }

    public void setBirthMotherName(String birthMotherName) {
        this.birthMotherName = birthMotherName == null ? null : birthMotherName.trim();
    }

    public String getRelationship1() {
        return relationship1;
    }

    public void setRelationship1(String relationship1) {
        this.relationship1 = relationship1 == null ? null : relationship1.trim();
    }

    public String getRelationship2() {
        return relationship2;
    }

    public void setRelationship2(String relationship2) {
        this.relationship2 = relationship2 == null ? null : relationship2.trim();
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
        sb.append(", education=").append(education);
        sb.append(", companyName=").append(companyName);
        sb.append(", companyTel=").append(companyTel);
        sb.append(", companyAddr=").append(companyAddr);
        sb.append(", companyAddrDetail=").append(companyAddrDetail);
        sb.append(", email=").append(email);
        sb.append(", childrenNumber=").append(childrenNumber);
        sb.append(", maritalStatus=").append(maritalStatus);
        sb.append(", birthMotherName=").append(birthMotherName);
        sb.append(", relationship1=").append(relationship1);
        sb.append(", relationship2=").append(relationship2);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static Profile.Builder builder() {
        return new Profile.Builder();
    }

    public static class Builder {
        private Profile obj;

        public Builder() {
            this.obj = new Profile();
        }

        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        public Builder userId(Long userId) {
            obj.setUserId(userId);
            return this;
        }

        public Builder education(String education) {
            obj.setEducation(education);
            return this;
        }

        public Builder companyName(String companyName) {
            obj.setCompanyName(companyName);
            return this;
        }

        public Builder companyTel(String companyTel) {
            obj.setCompanyTel(companyTel);
            return this;
        }

        public Builder companyAddr(String companyAddr) {
            obj.setCompanyAddr(companyAddr);
            return this;
        }

        public Builder companyAddrDetail(String companyAddrDetail) {
            obj.setCompanyAddrDetail(companyAddrDetail);
            return this;
        }

        public Builder email(String email) {
            obj.setEmail(email);
            return this;
        }

        public Builder childrenNumber(String childrenNumber) {
            obj.setChildrenNumber(childrenNumber);
            return this;
        }

        public Builder maritalStatus(String maritalStatus) {
            obj.setMaritalStatus(maritalStatus);
            return this;
        }

        public Builder birthMotherName(String birthMotherName) {
            obj.setBirthMotherName(birthMotherName);
            return this;
        }

        public Builder relationship1(String relationship1) {
            obj.setRelationship1(relationship1);
            return this;
        }

        public Builder relationship2(String relationship2) {
            obj.setRelationship2(relationship2);
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

        public Profile build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id"),
        userId("user_id"),
        education("education"),
        companyName("company_name"),
        companyTel("company_tel"),
        companyAddr("company_addr"),
        companyAddrDetail("company_addr_detail"),
        email("email"),
        childrenNumber("children_number"),
        maritalStatus("marital_status"),
        birthMotherName("birth_mother_name"),
        relationship1("relationship_1"),
        relationship2("relationship_2"),
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