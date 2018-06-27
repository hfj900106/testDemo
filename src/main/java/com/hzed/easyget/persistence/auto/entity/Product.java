package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 编码 101开始
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 额度 逗号隔开如 200,300,400
     */
    private String loanAmountArr;

    /**
     * 币种 IDR-印度尼西亚盾
     */
    private String currency;

    /**
     * 期限 逗号隔开如 7,8,9
     */
    private String loanTimeArr;

    /**
     * 期限年月日 D-日 M-月 Y-年
     */
    private String loanTimeDmy;

    /**
     * 年化利率
     */
    private Long apr;

    /**
     * 描述 key 保留字段
     */
    private String description;

    /**
     * 图片
     */
    private String picture;

    /**
     * 可用标志 0-不可用 1-可用
     */
    private Boolean isUse;

    /**
     * 快速信审费率
     */
    private BigDecimal headFeeRate;

    /**
     * 尾款费率
     */
    private BigDecimal tailFeeRate;

    /**
     * 逾期费率
     */
    private BigDecimal overdueFeeRate;

    /**
     * 最小还款金额
     */
    private BigDecimal minRepayAmount;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLoanAmountArr() {
        return loanAmountArr;
    }

    public void setLoanAmountArr(String loanAmountArr) {
        this.loanAmountArr = loanAmountArr == null ? null : loanAmountArr.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getLoanTimeArr() {
        return loanTimeArr;
    }

    public void setLoanTimeArr(String loanTimeArr) {
        this.loanTimeArr = loanTimeArr == null ? null : loanTimeArr.trim();
    }

    public String getLoanTimeDmy() {
        return loanTimeDmy;
    }

    public void setLoanTimeDmy(String loanTimeDmy) {
        this.loanTimeDmy = loanTimeDmy == null ? null : loanTimeDmy.trim();
    }

    public Long getApr() {
        return apr;
    }

    public void setApr(Long apr) {
        this.apr = apr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public Boolean getIsUse() {
        return isUse;
    }

    public void setIsUse(Boolean isUse) {
        this.isUse = isUse;
    }

    public BigDecimal getHeadFeeRate() {
        return headFeeRate;
    }

    public void setHeadFeeRate(BigDecimal headFeeRate) {
        this.headFeeRate = headFeeRate;
    }

    public BigDecimal getTailFeeRate() {
        return tailFeeRate;
    }

    public void setTailFeeRate(BigDecimal tailFeeRate) {
        this.tailFeeRate = tailFeeRate;
    }

    public BigDecimal getOverdueFeeRate() {
        return overdueFeeRate;
    }

    public void setOverdueFeeRate(BigDecimal overdueFeeRate) {
        this.overdueFeeRate = overdueFeeRate;
    }

    public BigDecimal getMinRepayAmount() {
        return minRepayAmount;
    }

    public void setMinRepayAmount(BigDecimal minRepayAmount) {
        this.minRepayAmount = minRepayAmount;
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
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", loanAmountArr=").append(loanAmountArr);
        sb.append(", currency=").append(currency);
        sb.append(", loanTimeArr=").append(loanTimeArr);
        sb.append(", loanTimeDmy=").append(loanTimeDmy);
        sb.append(", apr=").append(apr);
        sb.append(", description=").append(description);
        sb.append(", picture=").append(picture);
        sb.append(", isUse=").append(isUse);
        sb.append(", headFeeRate=").append(headFeeRate);
        sb.append(", tailFeeRate=").append(tailFeeRate);
        sb.append(", overdueFeeRate=").append(overdueFeeRate);
        sb.append(", minRepayAmount=").append(minRepayAmount);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static Product.Builder builder() {
        return new Product.Builder();
    }

    public static class Builder {
        private Product obj;

        public Builder() {
            this.obj = new Product();
        }

        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        public Builder code(String code) {
            obj.setCode(code);
            return this;
        }

        public Builder name(String name) {
            obj.setName(name);
            return this;
        }

        public Builder loanAmountArr(String loanAmountArr) {
            obj.setLoanAmountArr(loanAmountArr);
            return this;
        }

        public Builder currency(String currency) {
            obj.setCurrency(currency);
            return this;
        }

        public Builder loanTimeArr(String loanTimeArr) {
            obj.setLoanTimeArr(loanTimeArr);
            return this;
        }

        public Builder loanTimeDmy(String loanTimeDmy) {
            obj.setLoanTimeDmy(loanTimeDmy);
            return this;
        }

        public Builder apr(Long apr) {
            obj.setApr(apr);
            return this;
        }

        public Builder description(String description) {
            obj.setDescription(description);
            return this;
        }

        public Builder picture(String picture) {
            obj.setPicture(picture);
            return this;
        }

        public Builder isUse(Boolean isUse) {
            obj.setIsUse(isUse);
            return this;
        }

        public Builder headFeeRate(BigDecimal headFeeRate) {
            obj.setHeadFeeRate(headFeeRate);
            return this;
        }

        public Builder tailFeeRate(BigDecimal tailFeeRate) {
            obj.setTailFeeRate(tailFeeRate);
            return this;
        }

        public Builder overdueFeeRate(BigDecimal overdueFeeRate) {
            obj.setOverdueFeeRate(overdueFeeRate);
            return this;
        }

        public Builder minRepayAmount(BigDecimal minRepayAmount) {
            obj.setMinRepayAmount(minRepayAmount);
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

        public Product build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id"),
        code("code"),
        name("name"),
        loanAmountArr("loan_amount_arr"),
        currency("currency"),
        loanTimeArr("loan_time_arr"),
        loanTimeDmy("loan_time_dmy"),
        apr("apr"),
        description("description"),
        picture("picture"),
        isUse("is_use"),
        headFeeRate("head_fee_rate"),
        tailFeeRate("tail_fee_rate"),
        overdueFeeRate("overdue_fee_rate"),
        minRepayAmount("min_repay_amount"),
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