package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Bill implements Serializable {
    /**
     * 返款id
     */
    private Long id;

    /**
     * 标的id
     */
    private Long bidId;

    /**
     * 账单标题
     */
    private String title;

    /**
     * 账单期数
     */
    private Byte periods;

    /**
     * 还款日
     */
    private Date repaymentTime;

    /**
     * 应还本金
     */
    private BigDecimal repaymentCorpus;

    /**
     * 应还利息
     */
    private BigDecimal repaymentInterest;

    /**
     * 还款状态：-1-未还款；-2-逾期还款；-3-垫付还款；0-正常还款；1-提前还款
     */
    private Byte status;

    /**
     * 代偿（本金垫付）订单号
     */
    private String merBillNo;

    /**
     * 还款订单号
     */
    private String repaymentBillNo;

    /**
     * 实际还款时间
     */
    private Date realRepaymentTime;

    /**
     * 实际还款本金
     */
    private BigDecimal realRepaymentCorpus;

    /**
     * 实际还款利息
     */
    private BigDecimal realRepaymentInterest;

    /**
     * 逾期标记：-1 系统标记逾期-2 标记逾期-3 标记坏账0 未标记逾期,1=已逾期还款
     */
    private Byte overdueMark;

    /**
     * 逾期标记时间
     */
    private Date markOverdueTime;

    /**
     * 逾期罚款
     */
    private BigDecimal overdueFine;

    /**
     * 坏账标记时间
     */
    private Date markBadTime;

    /**
     * 发送站内信次数
     */
    private Integer noticeCountMessage;

    /**
     * 发送邮件次数
     */
    private Integer noticeCountMail;

    /**
     * 电话通知次数
     */
    private Integer noticeCountTelphone;

    /**
     * 资金托管处理状态, 0：正常，1：还款处理中，2：线下收款处理中，3：本金垫付处理中
     */
    private Byte ipsStatus;

    /**
     * 快速信审费
     */
    private Double auditFree;

    /**
     * 融资管理费
     */
    private Double serviceFree;

    /**
     * 提前还款违约金
     */
    private Double advanceRepayFee;

    /**
     * 逾期滞纳金
     */
    private Double overdueFineFee;

    /**
     * 逾期费
     */
    private Double overdueFree;

    /**
     * 逾期利息
     */
    private Double overdueInterestFree;

    /**
     * 推送状态。 1：待推送 2:推送成功 3:推送异常
     */
    private Byte pushStatus;

    /**
     * 推送时间
     */
    private Date pushTime;

    /**
     * 推送异常信息
     */
    private String pushErrorMessage;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Byte getPeriods() {
        return periods;
    }

    public void setPeriods(Byte periods) {
        this.periods = periods;
    }

    public Date getRepaymentTime() {
        return repaymentTime;
    }

    public void setRepaymentTime(Date repaymentTime) {
        this.repaymentTime = repaymentTime;
    }

    public BigDecimal getRepaymentCorpus() {
        return repaymentCorpus;
    }

    public void setRepaymentCorpus(BigDecimal repaymentCorpus) {
        this.repaymentCorpus = repaymentCorpus;
    }

    public BigDecimal getRepaymentInterest() {
        return repaymentInterest;
    }

    public void setRepaymentInterest(BigDecimal repaymentInterest) {
        this.repaymentInterest = repaymentInterest;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getMerBillNo() {
        return merBillNo;
    }

    public void setMerBillNo(String merBillNo) {
        this.merBillNo = merBillNo == null ? null : merBillNo.trim();
    }

    public String getRepaymentBillNo() {
        return repaymentBillNo;
    }

    public void setRepaymentBillNo(String repaymentBillNo) {
        this.repaymentBillNo = repaymentBillNo == null ? null : repaymentBillNo.trim();
    }

    public Date getRealRepaymentTime() {
        return realRepaymentTime;
    }

    public void setRealRepaymentTime(Date realRepaymentTime) {
        this.realRepaymentTime = realRepaymentTime;
    }

    public BigDecimal getRealRepaymentCorpus() {
        return realRepaymentCorpus;
    }

    public void setRealRepaymentCorpus(BigDecimal realRepaymentCorpus) {
        this.realRepaymentCorpus = realRepaymentCorpus;
    }

    public BigDecimal getRealRepaymentInterest() {
        return realRepaymentInterest;
    }

    public void setRealRepaymentInterest(BigDecimal realRepaymentInterest) {
        this.realRepaymentInterest = realRepaymentInterest;
    }

    public Byte getOverdueMark() {
        return overdueMark;
    }

    public void setOverdueMark(Byte overdueMark) {
        this.overdueMark = overdueMark;
    }

    public Date getMarkOverdueTime() {
        return markOverdueTime;
    }

    public void setMarkOverdueTime(Date markOverdueTime) {
        this.markOverdueTime = markOverdueTime;
    }

    public BigDecimal getOverdueFine() {
        return overdueFine;
    }

    public void setOverdueFine(BigDecimal overdueFine) {
        this.overdueFine = overdueFine;
    }

    public Date getMarkBadTime() {
        return markBadTime;
    }

    public void setMarkBadTime(Date markBadTime) {
        this.markBadTime = markBadTime;
    }

    public Integer getNoticeCountMessage() {
        return noticeCountMessage;
    }

    public void setNoticeCountMessage(Integer noticeCountMessage) {
        this.noticeCountMessage = noticeCountMessage;
    }

    public Integer getNoticeCountMail() {
        return noticeCountMail;
    }

    public void setNoticeCountMail(Integer noticeCountMail) {
        this.noticeCountMail = noticeCountMail;
    }

    public Integer getNoticeCountTelphone() {
        return noticeCountTelphone;
    }

    public void setNoticeCountTelphone(Integer noticeCountTelphone) {
        this.noticeCountTelphone = noticeCountTelphone;
    }

    public Byte getIpsStatus() {
        return ipsStatus;
    }

    public void setIpsStatus(Byte ipsStatus) {
        this.ipsStatus = ipsStatus;
    }

    public Double getAuditFree() {
        return auditFree;
    }

    public void setAuditFree(Double auditFree) {
        this.auditFree = auditFree;
    }

    public Double getServiceFree() {
        return serviceFree;
    }

    public void setServiceFree(Double serviceFree) {
        this.serviceFree = serviceFree;
    }

    public Double getAdvanceRepayFee() {
        return advanceRepayFee;
    }

    public void setAdvanceRepayFee(Double advanceRepayFee) {
        this.advanceRepayFee = advanceRepayFee;
    }

    public Double getOverdueFineFee() {
        return overdueFineFee;
    }

    public void setOverdueFineFee(Double overdueFineFee) {
        this.overdueFineFee = overdueFineFee;
    }

    public Double getOverdueFree() {
        return overdueFree;
    }

    public void setOverdueFree(Double overdueFree) {
        this.overdueFree = overdueFree;
    }

    public Double getOverdueInterestFree() {
        return overdueInterestFree;
    }

    public void setOverdueInterestFree(Double overdueInterestFree) {
        this.overdueInterestFree = overdueInterestFree;
    }

    public Byte getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(Byte pushStatus) {
        this.pushStatus = pushStatus;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public String getPushErrorMessage() {
        return pushErrorMessage;
    }

    public void setPushErrorMessage(String pushErrorMessage) {
        this.pushErrorMessage = pushErrorMessage == null ? null : pushErrorMessage.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", bidId=").append(bidId);
        sb.append(", title=").append(title);
        sb.append(", periods=").append(periods);
        sb.append(", repaymentTime=").append(repaymentTime);
        sb.append(", repaymentCorpus=").append(repaymentCorpus);
        sb.append(", repaymentInterest=").append(repaymentInterest);
        sb.append(", status=").append(status);
        sb.append(", merBillNo=").append(merBillNo);
        sb.append(", repaymentBillNo=").append(repaymentBillNo);
        sb.append(", realRepaymentTime=").append(realRepaymentTime);
        sb.append(", realRepaymentCorpus=").append(realRepaymentCorpus);
        sb.append(", realRepaymentInterest=").append(realRepaymentInterest);
        sb.append(", overdueMark=").append(overdueMark);
        sb.append(", markOverdueTime=").append(markOverdueTime);
        sb.append(", overdueFine=").append(overdueFine);
        sb.append(", markBadTime=").append(markBadTime);
        sb.append(", noticeCountMessage=").append(noticeCountMessage);
        sb.append(", noticeCountMail=").append(noticeCountMail);
        sb.append(", noticeCountTelphone=").append(noticeCountTelphone);
        sb.append(", ipsStatus=").append(ipsStatus);
        sb.append(", auditFree=").append(auditFree);
        sb.append(", serviceFree=").append(serviceFree);
        sb.append(", advanceRepayFee=").append(advanceRepayFee);
        sb.append(", overdueFineFee=").append(overdueFineFee);
        sb.append(", overdueFree=").append(overdueFree);
        sb.append(", overdueInterestFree=").append(overdueInterestFree);
        sb.append(", pushStatus=").append(pushStatus);
        sb.append(", pushTime=").append(pushTime);
        sb.append(", pushErrorMessage=").append(pushErrorMessage);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static Bill.Builder builder() {
        return new Bill.Builder();
    }

    public static class Builder {
        private Bill obj;

        public Builder() {
            this.obj = new Bill();
        }

        public Builder id(Long id) {
            obj.setId(id);
            return this;
        }

        public Builder bidId(Long bidId) {
            obj.setBidId(bidId);
            return this;
        }

        public Builder title(String title) {
            obj.setTitle(title);
            return this;
        }

        public Builder periods(Byte periods) {
            obj.setPeriods(periods);
            return this;
        }

        public Builder repaymentTime(Date repaymentTime) {
            obj.setRepaymentTime(repaymentTime);
            return this;
        }

        public Builder repaymentCorpus(BigDecimal repaymentCorpus) {
            obj.setRepaymentCorpus(repaymentCorpus);
            return this;
        }

        public Builder repaymentInterest(BigDecimal repaymentInterest) {
            obj.setRepaymentInterest(repaymentInterest);
            return this;
        }

        public Builder status(Byte status) {
            obj.setStatus(status);
            return this;
        }

        public Builder merBillNo(String merBillNo) {
            obj.setMerBillNo(merBillNo);
            return this;
        }

        public Builder repaymentBillNo(String repaymentBillNo) {
            obj.setRepaymentBillNo(repaymentBillNo);
            return this;
        }

        public Builder realRepaymentTime(Date realRepaymentTime) {
            obj.setRealRepaymentTime(realRepaymentTime);
            return this;
        }

        public Builder realRepaymentCorpus(BigDecimal realRepaymentCorpus) {
            obj.setRealRepaymentCorpus(realRepaymentCorpus);
            return this;
        }

        public Builder realRepaymentInterest(BigDecimal realRepaymentInterest) {
            obj.setRealRepaymentInterest(realRepaymentInterest);
            return this;
        }

        public Builder overdueMark(Byte overdueMark) {
            obj.setOverdueMark(overdueMark);
            return this;
        }

        public Builder markOverdueTime(Date markOverdueTime) {
            obj.setMarkOverdueTime(markOverdueTime);
            return this;
        }

        public Builder overdueFine(BigDecimal overdueFine) {
            obj.setOverdueFine(overdueFine);
            return this;
        }

        public Builder markBadTime(Date markBadTime) {
            obj.setMarkBadTime(markBadTime);
            return this;
        }

        public Builder noticeCountMessage(Integer noticeCountMessage) {
            obj.setNoticeCountMessage(noticeCountMessage);
            return this;
        }

        public Builder noticeCountMail(Integer noticeCountMail) {
            obj.setNoticeCountMail(noticeCountMail);
            return this;
        }

        public Builder noticeCountTelphone(Integer noticeCountTelphone) {
            obj.setNoticeCountTelphone(noticeCountTelphone);
            return this;
        }

        public Builder ipsStatus(Byte ipsStatus) {
            obj.setIpsStatus(ipsStatus);
            return this;
        }

        public Builder auditFree(Double auditFree) {
            obj.setAuditFree(auditFree);
            return this;
        }

        public Builder serviceFree(Double serviceFree) {
            obj.setServiceFree(serviceFree);
            return this;
        }

        public Builder advanceRepayFee(Double advanceRepayFee) {
            obj.setAdvanceRepayFee(advanceRepayFee);
            return this;
        }

        public Builder overdueFineFee(Double overdueFineFee) {
            obj.setOverdueFineFee(overdueFineFee);
            return this;
        }

        public Builder overdueFree(Double overdueFree) {
            obj.setOverdueFree(overdueFree);
            return this;
        }

        public Builder overdueInterestFree(Double overdueInterestFree) {
            obj.setOverdueInterestFree(overdueInterestFree);
            return this;
        }

        public Builder pushStatus(Byte pushStatus) {
            obj.setPushStatus(pushStatus);
            return this;
        }

        public Builder pushTime(Date pushTime) {
            obj.setPushTime(pushTime);
            return this;
        }

        public Builder pushErrorMessage(String pushErrorMessage) {
            obj.setPushErrorMessage(pushErrorMessage);
            return this;
        }

        public Bill build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id"),
        bidId("bid_id"),
        title("title"),
        periods("periods"),
        repaymentTime("repayment_time"),
        repaymentCorpus("repayment_corpus"),
        repaymentInterest("repayment_interest"),
        status("status"),
        merBillNo("mer_bill_no"),
        repaymentBillNo("repayment_bill_no"),
        realRepaymentTime("real_repayment_time"),
        realRepaymentCorpus("real_repayment_corpus"),
        realRepaymentInterest("real_repayment_interest"),
        overdueMark("overdue_mark"),
        markOverdueTime("mark_overdue_time"),
        overdueFine("overdue_fine"),
        markBadTime("mark_bad_time"),
        noticeCountMessage("notice_count_message"),
        noticeCountMail("notice_count_mail"),
        noticeCountTelphone("notice_count_telphone"),
        ipsStatus("ips_status"),
        auditFree("audit_free"),
        serviceFree("service_free"),
        advanceRepayFee("advance_repay_fee"),
        overdueFineFee("overdue_fine_fee"),
        overdueFree("overdue_free"),
        overdueInterestFree("overdue_interest_free"),
        pushStatus("push_status"),
        pushTime("push_time"),
        pushErrorMessage("push_error_message");

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