package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Bid implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 借款人
     */
    private Long userId;

    /**
     * 标的号
     */
    private String bidNo;

    /**
     * 标题
     */
    private String title;

    /**
     * 产品编码
     */
    private String productCode;

    /**
     * 贷款目的编码
     */
    private String purposeCode;

    /**
     * 借款金额
     */
    private BigDecimal applyAmount;

    /**
     * 审核金额
     */
    private BigDecimal loanAmount;

    /**
     * 快速信审费（砍头息），放款时收取
     */
    private BigDecimal auditFee;

    /**
     * 借款期限
     */
    private Integer period;

    /**
     * 收款银行
     */
    private String inBank;

    /**
     * 收款账户
     */
    private String inAccount;

    /**
     * 进件渠道 Rupiah Get-印尼app进件
     */
    private String client;

    /**
     * 状态 1-待走风控 2-待人审 3-人工审核不通过 4-审核通过 5-已放款 6-已结清
     */
    private Byte status;

    /**
     */
    private Long createBy;

    /**
     * 进件时间
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

    public String getBidNo() {
        return bidNo;
    }

    public void setBidNo(String bidNo) {
        this.bidNo = bidNo == null ? null : bidNo.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public String getPurposeCode() {
        return purposeCode;
    }

    public void setPurposeCode(String purposeCode) {
        this.purposeCode = purposeCode == null ? null : purposeCode.trim();
    }

    public BigDecimal getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(BigDecimal applyAmount) {
        this.applyAmount = applyAmount;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public BigDecimal getAuditFee() {
        return auditFee;
    }

    public void setAuditFee(BigDecimal auditFee) {
        this.auditFee = auditFee;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getInBank() {
        return inBank;
    }

    public void setInBank(String inBank) {
        this.inBank = inBank == null ? null : inBank.trim();
    }

    public String getInAccount() {
        return inAccount;
    }

    public void setInAccount(String inAccount) {
        this.inAccount = inAccount == null ? null : inAccount.trim();
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client == null ? null : client.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
        sb.append(", bidNo=").append(bidNo);
        sb.append(", title=").append(title);
        sb.append(", productCode=").append(productCode);
        sb.append(", purposeCode=").append(purposeCode);
        sb.append(", applyAmount=").append(applyAmount);
        sb.append(", loanAmount=").append(loanAmount);
        sb.append(", auditFee=").append(auditFee);
        sb.append(", period=").append(period);
        sb.append(", inBank=").append(inBank);
        sb.append(", inAccount=").append(inAccount);
        sb.append(", client=").append(client);
        sb.append(", status=").append(status);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static Bid.Builder builder() {
        return new Bid.Builder();
    }

    public static class Builder {
        private Bid obj;

        public Builder() {
            this.obj = new Bid();
        }

        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        public Builder userId(Long userId) {
            obj.setUserId(userId);
            return this;
        }

        public Builder bidNo(String bidNo) {
            obj.setBidNo(bidNo);
            return this;
        }

        public Builder title(String title) {
            obj.setTitle(title);
            return this;
        }

        public Builder productCode(String productCode) {
            obj.setProductCode(productCode);
            return this;
        }

        public Builder purposeCode(String purposeCode) {
            obj.setPurposeCode(purposeCode);
            return this;
        }

        public Builder applyAmount(BigDecimal applyAmount) {
            obj.setApplyAmount(applyAmount);
            return this;
        }

        public Builder loanAmount(BigDecimal loanAmount) {
            obj.setLoanAmount(loanAmount);
            return this;
        }

        public Builder auditFee(BigDecimal auditFee) {
            obj.setAuditFee(auditFee);
            return this;
        }

        public Builder period(Integer period) {
            obj.setPeriod(period);
            return this;
        }

        public Builder inBank(String inBank) {
            obj.setInBank(inBank);
            return this;
        }

        public Builder inAccount(String inAccount) {
            obj.setInAccount(inAccount);
            return this;
        }

        public Builder client(String client) {
            obj.setClient(client);
            return this;
        }

        public Builder status(Byte status) {
            obj.setStatus(status);
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

        public Bid build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id"),
        userId("user_id"),
        bidNo("bid_no"),
        title("title"),
        productCode("product_code"),
        purposeCode("purpose_code"),
        applyAmount("apply_amount"),
        loanAmount("loan_amount"),
        auditFee("audit_fee"),
        period("period"),
        inBank("in_bank"),
        inAccount("in_account"),
        client("client"),
        status("status"),
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