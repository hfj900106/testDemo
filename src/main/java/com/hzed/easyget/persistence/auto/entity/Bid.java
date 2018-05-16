package com.hzed.easyget.persistence.auto.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Bid implements Serializable {
    /**
     * 借款标id
     */
    private Long id;

    /**
     * 发布者
     */
    private Long userId;

    /**
     * 发布时间
     */
    private Date time;

    /**
     * 商户订单号（资金托管使用）
     */
    private String merBillNo;

    /**
     * 标的编号（资金托管使用）
     */
    private String bidNo;

    /**
     * 第三方支付返回的订单号（资金托管）
     */
    private String ipsBillNo;

    /**
     * 借款标产品类型id
     */
    private Integer productId;

    /**
     * 借款标题
     */
    private String title;

    /**
     * 借款目的id
     */
    private Integer loanPurposeId;

    /**
     * 返款方式
     */
    private Byte repaymentTypeId;

    /**
     * 借款金额
     */
    private BigDecimal amount;

    /**
     * 借款期限-1: 年;0:月;1:日;
     */
    private Integer periodUnit;

    /**
     * 借款期限
     */
    private Integer period;

    /**
     * 最低金额招标
     */
    private BigDecimal minInvestAmount;

    /**
     * 平均金额招标
     */
    private BigDecimal averageInvestAmount;

    /**
     * 投标期限
     */
    private Integer investPeriod;

    /**
     * 满标时间
     */
    private Date investExpireTime;

    /**
     * 实际满标时间
     */
    private Date realInvestExpireTime;

    /**
     * 年利率
     */
    private BigDecimal apr;

    /**
     * 借款标绑定的银行卡
     */
    private Long bankAccountId;

    /**
     * 奖励方式 0 不奖励 1 固定金额奖励 2 按比例奖励
     */
    private Byte bonusType;

    /**
     * 固定奖金
     */
    private BigDecimal bonus;

    /**
     * 奖励百分比
     */
    private BigDecimal awardScale;

    /**
     * 借款图片
     */
    private String imageFilename;

    /**
     * 借款描述
     */
    private String description;

    /**
     * 附加条目属性
     */
    private String item;

    /**
     * 是否秒还0:否;1:是;
     */
    private Boolean isSecBid;

    /**
     * 是否需要交易密码0:否;1:是;
     */
    private Boolean isDealPassword;

    /**
     * 发布方式1:PC;2:APP;3:PC+APP;
     */
    private Integer showType;

    /**
     * 保证金
     */
    private BigDecimal bail;

    /**
     * 服务费
     */
    private BigDecimal serviceFees;

    /**
     * 0:非优质标;1:优质标
     */
    private Boolean isQuality;

    /**
     * 0:非"火"标;1:"火"标
     */
    private Boolean isHot;

    /**
     * 判定是否是机构标
     */
    private Boolean isAgency;

    /**
     * 机构ID
     */
    private Integer agencyId;

    /**
     * 是否显示机构标名称
     */
    private Boolean isShowAgencyName;

    /**
     * 0.审核中;

     * 1.提前借款;

     * 2.借款中(审核通过);

     * 3.待放款(放款审核通过);

     * 4.还款中(财务放款);

     * 5.已还款;

     * 10.审核中待验证

     * 11.提前借款待验证

     * 12.借款中待验证

     * 20.审核中待支付投标奖励

     * 21.提前借款待支付投标奖励

     * 22.借款中待支付投标奖励

     * 14.本金垫付还款中(已放款)

     * -1.审核不通过;

     * -2.借款中不通过;

     * -3.放款不通过;

     * -4.流标;

     * -5.撤销

     * -10.未验证

     * -11.用户未确认借款
     */
    private Byte status;

    /**
     * 借款进度比例(冗余)
     */
    private BigDecimal loanSchedule;

    /**
     * 已投总额(冗余)
     */
    private BigDecimal hasInvestedAmount;

    /**
     * 阅读次数
     */
    private Integer readCount;

    /**
     * 审核人,hzed初审
     */
    private Long allocationSupervisorId;

    /**
     * 分配审核人ID,hzed满标审核
     */
    private Long manageSupervisorId;

    /**
     * 审核时间(初始审核时间 -> 放款审核时间 -> 放款时间 )

     * 每一个审核就是一个表示的时间
     */
    private Date auditTime;

    /**
     * 审核意见
     */
    private String auditSuggest;

    /**
     * 第一期还款日期
     */
    private Date repaymentTime;

    /**
     * 最后还款时间
     */
    private Date lastRepayTime;

    /**
     * 是否是已经自动投标的标识 0 否 1 是
     */
    private Boolean isAuditmaticInvestBid;

    /**
     * 版本--（用于控制并发）
     */
    private Integer version;

    /**
     * 关联资料的唯一标识
     */
    private String mark;

    /**
     */
    private String qrCode;

    /**
     * 理财费率
     */
    private Double investRate;

    /**
     * 逾期费率
     */
    private Double overdueRate;

    /**
     * 是否已登记担保方 0：未登记 1：已登记
     */
    private Boolean isRegisterGuarantor;

    /**
     * 借款人申请金额
     */
    private BigDecimal applyAmount;

    /**
     * 登记标的入口：1 pc 2 app 3 wechat 4 other
     */
    private Byte client;

    /**
     * 资金托管处理状态，0：正常，1：标的结束处理中，2：放款处理中
     */
    private Byte ipsStatus;

    /**
     * 标类型，0 普通标 1体验标 2新手标3特权标
     */
    private Boolean bidType;

    /**
     * 后台发标人员
     */
    private Long releaseSupervisorId;

    /**
     * 续贷==记录原借款标ID
     */
    private Long relevanceId;

    /**
     * 贷后催收管理人员
     */
    private Long collectSupervisorId;

    /**
     * 循环期开始时间
     */
    private Date beginRunTime;

    /**
     * 循环期结束时间
     */
    private Date runTime;

    /**
     * 利率
     */
    private Double runRate;

    /**
     * 循环授信额度
     */
    private Long runCredit;

    /**
     * 循环期降息的次数
     */
    private Byte runRateTimes;

    /**
     * 循环期提额的次数
     */
    private Byte runCreditTimes;

    /**
     * 循环期内的期数，表示第几期
     */
    private Byte times;

    /**
     * 计划类型。8：智盈存 9：E计划(包括车贷E计划和消费贷E计划)
     */
    private Long productIdFlag;

    /**
     * 推送状态。 1：待推送 2:推送成功 3:推送异常
     */
    private Byte pushStatus;

    /**
     * 推送异常信息
     */
    private String pushErrorMessage;

    /**
     * 推送时间
     */
    private Date pushTime;

    /**
     * 居间服务协议
     */
    private String intermediaryAgreement;

    /**
     * 借款合同(借款人)
     */
    private String pact;

    /**
     * 保障函
     */
    private String guaranteeBid;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMerBillNo() {
        return merBillNo;
    }

    public void setMerBillNo(String merBillNo) {
        this.merBillNo = merBillNo == null ? null : merBillNo.trim();
    }

    public String getBidNo() {
        return bidNo;
    }

    public void setBidNo(String bidNo) {
        this.bidNo = bidNo == null ? null : bidNo.trim();
    }

    public String getIpsBillNo() {
        return ipsBillNo;
    }

    public void setIpsBillNo(String ipsBillNo) {
        this.ipsBillNo = ipsBillNo == null ? null : ipsBillNo.trim();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getLoanPurposeId() {
        return loanPurposeId;
    }

    public void setLoanPurposeId(Integer loanPurposeId) {
        this.loanPurposeId = loanPurposeId;
    }

    public Byte getRepaymentTypeId() {
        return repaymentTypeId;
    }

    public void setRepaymentTypeId(Byte repaymentTypeId) {
        this.repaymentTypeId = repaymentTypeId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getPeriodUnit() {
        return periodUnit;
    }

    public void setPeriodUnit(Integer periodUnit) {
        this.periodUnit = periodUnit;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public BigDecimal getMinInvestAmount() {
        return minInvestAmount;
    }

    public void setMinInvestAmount(BigDecimal minInvestAmount) {
        this.minInvestAmount = minInvestAmount;
    }

    public BigDecimal getAverageInvestAmount() {
        return averageInvestAmount;
    }

    public void setAverageInvestAmount(BigDecimal averageInvestAmount) {
        this.averageInvestAmount = averageInvestAmount;
    }

    public Integer getInvestPeriod() {
        return investPeriod;
    }

    public void setInvestPeriod(Integer investPeriod) {
        this.investPeriod = investPeriod;
    }

    public Date getInvestExpireTime() {
        return investExpireTime;
    }

    public void setInvestExpireTime(Date investExpireTime) {
        this.investExpireTime = investExpireTime;
    }

    public Date getRealInvestExpireTime() {
        return realInvestExpireTime;
    }

    public void setRealInvestExpireTime(Date realInvestExpireTime) {
        this.realInvestExpireTime = realInvestExpireTime;
    }

    public BigDecimal getApr() {
        return apr;
    }

    public void setApr(BigDecimal apr) {
        this.apr = apr;
    }

    public Long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public Byte getBonusType() {
        return bonusType;
    }

    public void setBonusType(Byte bonusType) {
        this.bonusType = bonusType;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public BigDecimal getAwardScale() {
        return awardScale;
    }

    public void setAwardScale(BigDecimal awardScale) {
        this.awardScale = awardScale;
    }

    public String getImageFilename() {
        return imageFilename;
    }

    public void setImageFilename(String imageFilename) {
        this.imageFilename = imageFilename == null ? null : imageFilename.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item == null ? null : item.trim();
    }

    public Boolean getIsSecBid() {
        return isSecBid;
    }

    public void setIsSecBid(Boolean isSecBid) {
        this.isSecBid = isSecBid;
    }

    public Boolean getIsDealPassword() {
        return isDealPassword;
    }

    public void setIsDealPassword(Boolean isDealPassword) {
        this.isDealPassword = isDealPassword;
    }

    public Integer getShowType() {
        return showType;
    }

    public void setShowType(Integer showType) {
        this.showType = showType;
    }

    public BigDecimal getBail() {
        return bail;
    }

    public void setBail(BigDecimal bail) {
        this.bail = bail;
    }

    public BigDecimal getServiceFees() {
        return serviceFees;
    }

    public void setServiceFees(BigDecimal serviceFees) {
        this.serviceFees = serviceFees;
    }

    public Boolean getIsQuality() {
        return isQuality;
    }

    public void setIsQuality(Boolean isQuality) {
        this.isQuality = isQuality;
    }

    public Boolean getIsHot() {
        return isHot;
    }

    public void setIsHot(Boolean isHot) {
        this.isHot = isHot;
    }

    public Boolean getIsAgency() {
        return isAgency;
    }

    public void setIsAgency(Boolean isAgency) {
        this.isAgency = isAgency;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    public Boolean getIsShowAgencyName() {
        return isShowAgencyName;
    }

    public void setIsShowAgencyName(Boolean isShowAgencyName) {
        this.isShowAgencyName = isShowAgencyName;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public BigDecimal getLoanSchedule() {
        return loanSchedule;
    }

    public void setLoanSchedule(BigDecimal loanSchedule) {
        this.loanSchedule = loanSchedule;
    }

    public BigDecimal getHasInvestedAmount() {
        return hasInvestedAmount;
    }

    public void setHasInvestedAmount(BigDecimal hasInvestedAmount) {
        this.hasInvestedAmount = hasInvestedAmount;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Long getAllocationSupervisorId() {
        return allocationSupervisorId;
    }

    public void setAllocationSupervisorId(Long allocationSupervisorId) {
        this.allocationSupervisorId = allocationSupervisorId;
    }

    public Long getManageSupervisorId() {
        return manageSupervisorId;
    }

    public void setManageSupervisorId(Long manageSupervisorId) {
        this.manageSupervisorId = manageSupervisorId;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditSuggest() {
        return auditSuggest;
    }

    public void setAuditSuggest(String auditSuggest) {
        this.auditSuggest = auditSuggest == null ? null : auditSuggest.trim();
    }

    public Date getRepaymentTime() {
        return repaymentTime;
    }

    public void setRepaymentTime(Date repaymentTime) {
        this.repaymentTime = repaymentTime;
    }

    public Date getLastRepayTime() {
        return lastRepayTime;
    }

    public void setLastRepayTime(Date lastRepayTime) {
        this.lastRepayTime = lastRepayTime;
    }

    public Boolean getIsAuditmaticInvestBid() {
        return isAuditmaticInvestBid;
    }

    public void setIsAuditmaticInvestBid(Boolean isAuditmaticInvestBid) {
        this.isAuditmaticInvestBid = isAuditmaticInvestBid;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode == null ? null : qrCode.trim();
    }

    public Double getInvestRate() {
        return investRate;
    }

    public void setInvestRate(Double investRate) {
        this.investRate = investRate;
    }

    public Double getOverdueRate() {
        return overdueRate;
    }

    public void setOverdueRate(Double overdueRate) {
        this.overdueRate = overdueRate;
    }

    public Boolean getIsRegisterGuarantor() {
        return isRegisterGuarantor;
    }

    public void setIsRegisterGuarantor(Boolean isRegisterGuarantor) {
        this.isRegisterGuarantor = isRegisterGuarantor;
    }

    public BigDecimal getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(BigDecimal applyAmount) {
        this.applyAmount = applyAmount;
    }

    public Byte getClient() {
        return client;
    }

    public void setClient(Byte client) {
        this.client = client;
    }

    public Byte getIpsStatus() {
        return ipsStatus;
    }

    public void setIpsStatus(Byte ipsStatus) {
        this.ipsStatus = ipsStatus;
    }

    public Boolean getBidType() {
        return bidType;
    }

    public void setBidType(Boolean bidType) {
        this.bidType = bidType;
    }

    public Long getReleaseSupervisorId() {
        return releaseSupervisorId;
    }

    public void setReleaseSupervisorId(Long releaseSupervisorId) {
        this.releaseSupervisorId = releaseSupervisorId;
    }

    public Long getRelevanceId() {
        return relevanceId;
    }

    public void setRelevanceId(Long relevanceId) {
        this.relevanceId = relevanceId;
    }

    public Long getCollectSupervisorId() {
        return collectSupervisorId;
    }

    public void setCollectSupervisorId(Long collectSupervisorId) {
        this.collectSupervisorId = collectSupervisorId;
    }

    public Date getBeginRunTime() {
        return beginRunTime;
    }

    public void setBeginRunTime(Date beginRunTime) {
        this.beginRunTime = beginRunTime;
    }

    public Date getRunTime() {
        return runTime;
    }

    public void setRunTime(Date runTime) {
        this.runTime = runTime;
    }

    public Double getRunRate() {
        return runRate;
    }

    public void setRunRate(Double runRate) {
        this.runRate = runRate;
    }

    public Long getRunCredit() {
        return runCredit;
    }

    public void setRunCredit(Long runCredit) {
        this.runCredit = runCredit;
    }

    public Byte getRunRateTimes() {
        return runRateTimes;
    }

    public void setRunRateTimes(Byte runRateTimes) {
        this.runRateTimes = runRateTimes;
    }

    public Byte getRunCreditTimes() {
        return runCreditTimes;
    }

    public void setRunCreditTimes(Byte runCreditTimes) {
        this.runCreditTimes = runCreditTimes;
    }

    public Byte getTimes() {
        return times;
    }

    public void setTimes(Byte times) {
        this.times = times;
    }

    public Long getProductIdFlag() {
        return productIdFlag;
    }

    public void setProductIdFlag(Long productIdFlag) {
        this.productIdFlag = productIdFlag;
    }

    public Byte getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(Byte pushStatus) {
        this.pushStatus = pushStatus;
    }

    public String getPushErrorMessage() {
        return pushErrorMessage;
    }

    public void setPushErrorMessage(String pushErrorMessage) {
        this.pushErrorMessage = pushErrorMessage == null ? null : pushErrorMessage.trim();
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public String getIntermediaryAgreement() {
        return intermediaryAgreement;
    }

    public void setIntermediaryAgreement(String intermediaryAgreement) {
        this.intermediaryAgreement = intermediaryAgreement == null ? null : intermediaryAgreement.trim();
    }

    public String getPact() {
        return pact;
    }

    public void setPact(String pact) {
        this.pact = pact == null ? null : pact.trim();
    }

    public String getGuaranteeBid() {
        return guaranteeBid;
    }

    public void setGuaranteeBid(String guaranteeBid) {
        this.guaranteeBid = guaranteeBid == null ? null : guaranteeBid.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", time=").append(time);
        sb.append(", merBillNo=").append(merBillNo);
        sb.append(", bidNo=").append(bidNo);
        sb.append(", ipsBillNo=").append(ipsBillNo);
        sb.append(", productId=").append(productId);
        sb.append(", title=").append(title);
        sb.append(", loanPurposeId=").append(loanPurposeId);
        sb.append(", repaymentTypeId=").append(repaymentTypeId);
        sb.append(", amount=").append(amount);
        sb.append(", periodUnit=").append(periodUnit);
        sb.append(", period=").append(period);
        sb.append(", minInvestAmount=").append(minInvestAmount);
        sb.append(", averageInvestAmount=").append(averageInvestAmount);
        sb.append(", investPeriod=").append(investPeriod);
        sb.append(", investExpireTime=").append(investExpireTime);
        sb.append(", realInvestExpireTime=").append(realInvestExpireTime);
        sb.append(", apr=").append(apr);
        sb.append(", bankAccountId=").append(bankAccountId);
        sb.append(", bonusType=").append(bonusType);
        sb.append(", bonus=").append(bonus);
        sb.append(", awardScale=").append(awardScale);
        sb.append(", imageFilename=").append(imageFilename);
        sb.append(", description=").append(description);
        sb.append(", item=").append(item);
        sb.append(", isSecBid=").append(isSecBid);
        sb.append(", isDealPassword=").append(isDealPassword);
        sb.append(", showType=").append(showType);
        sb.append(", bail=").append(bail);
        sb.append(", serviceFees=").append(serviceFees);
        sb.append(", isQuality=").append(isQuality);
        sb.append(", isHot=").append(isHot);
        sb.append(", isAgency=").append(isAgency);
        sb.append(", agencyId=").append(agencyId);
        sb.append(", isShowAgencyName=").append(isShowAgencyName);
        sb.append(", status=").append(status);
        sb.append(", loanSchedule=").append(loanSchedule);
        sb.append(", hasInvestedAmount=").append(hasInvestedAmount);
        sb.append(", readCount=").append(readCount);
        sb.append(", allocationSupervisorId=").append(allocationSupervisorId);
        sb.append(", manageSupervisorId=").append(manageSupervisorId);
        sb.append(", auditTime=").append(auditTime);
        sb.append(", auditSuggest=").append(auditSuggest);
        sb.append(", repaymentTime=").append(repaymentTime);
        sb.append(", lastRepayTime=").append(lastRepayTime);
        sb.append(", isAuditmaticInvestBid=").append(isAuditmaticInvestBid);
        sb.append(", version=").append(version);
        sb.append(", mark=").append(mark);
        sb.append(", qrCode=").append(qrCode);
        sb.append(", investRate=").append(investRate);
        sb.append(", overdueRate=").append(overdueRate);
        sb.append(", isRegisterGuarantor=").append(isRegisterGuarantor);
        sb.append(", applyAmount=").append(applyAmount);
        sb.append(", client=").append(client);
        sb.append(", ipsStatus=").append(ipsStatus);
        sb.append(", bidType=").append(bidType);
        sb.append(", releaseSupervisorId=").append(releaseSupervisorId);
        sb.append(", relevanceId=").append(relevanceId);
        sb.append(", collectSupervisorId=").append(collectSupervisorId);
        sb.append(", beginRunTime=").append(beginRunTime);
        sb.append(", runTime=").append(runTime);
        sb.append(", runRate=").append(runRate);
        sb.append(", runCredit=").append(runCredit);
        sb.append(", runRateTimes=").append(runRateTimes);
        sb.append(", runCreditTimes=").append(runCreditTimes);
        sb.append(", times=").append(times);
        sb.append(", productIdFlag=").append(productIdFlag);
        sb.append(", pushStatus=").append(pushStatus);
        sb.append(", pushErrorMessage=").append(pushErrorMessage);
        sb.append(", pushTime=").append(pushTime);
        sb.append(", intermediaryAgreement=").append(intermediaryAgreement);
        sb.append(", pact=").append(pact);
        sb.append(", guaranteeBid=").append(guaranteeBid);
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

        public Builder time(Date time) {
            obj.setTime(time);
            return this;
        }

        public Builder merBillNo(String merBillNo) {
            obj.setMerBillNo(merBillNo);
            return this;
        }

        public Builder bidNo(String bidNo) {
            obj.setBidNo(bidNo);
            return this;
        }

        public Builder ipsBillNo(String ipsBillNo) {
            obj.setIpsBillNo(ipsBillNo);
            return this;
        }

        public Builder productId(Integer productId) {
            obj.setProductId(productId);
            return this;
        }

        public Builder title(String title) {
            obj.setTitle(title);
            return this;
        }

        public Builder loanPurposeId(Integer loanPurposeId) {
            obj.setLoanPurposeId(loanPurposeId);
            return this;
        }

        public Builder repaymentTypeId(Byte repaymentTypeId) {
            obj.setRepaymentTypeId(repaymentTypeId);
            return this;
        }

        public Builder amount(BigDecimal amount) {
            obj.setAmount(amount);
            return this;
        }

        public Builder periodUnit(Integer periodUnit) {
            obj.setPeriodUnit(periodUnit);
            return this;
        }

        public Builder period(Integer period) {
            obj.setPeriod(period);
            return this;
        }

        public Builder minInvestAmount(BigDecimal minInvestAmount) {
            obj.setMinInvestAmount(minInvestAmount);
            return this;
        }

        public Builder averageInvestAmount(BigDecimal averageInvestAmount) {
            obj.setAverageInvestAmount(averageInvestAmount);
            return this;
        }

        public Builder investPeriod(Integer investPeriod) {
            obj.setInvestPeriod(investPeriod);
            return this;
        }

        public Builder investExpireTime(Date investExpireTime) {
            obj.setInvestExpireTime(investExpireTime);
            return this;
        }

        public Builder realInvestExpireTime(Date realInvestExpireTime) {
            obj.setRealInvestExpireTime(realInvestExpireTime);
            return this;
        }

        public Builder apr(BigDecimal apr) {
            obj.setApr(apr);
            return this;
        }

        public Builder bankAccountId(Long bankAccountId) {
            obj.setBankAccountId(bankAccountId);
            return this;
        }

        public Builder bonusType(Byte bonusType) {
            obj.setBonusType(bonusType);
            return this;
        }

        public Builder bonus(BigDecimal bonus) {
            obj.setBonus(bonus);
            return this;
        }

        public Builder awardScale(BigDecimal awardScale) {
            obj.setAwardScale(awardScale);
            return this;
        }

        public Builder imageFilename(String imageFilename) {
            obj.setImageFilename(imageFilename);
            return this;
        }

        public Builder description(String description) {
            obj.setDescription(description);
            return this;
        }

        public Builder item(String item) {
            obj.setItem(item);
            return this;
        }

        public Builder isSecBid(Boolean isSecBid) {
            obj.setIsSecBid(isSecBid);
            return this;
        }

        public Builder isDealPassword(Boolean isDealPassword) {
            obj.setIsDealPassword(isDealPassword);
            return this;
        }

        public Builder showType(Integer showType) {
            obj.setShowType(showType);
            return this;
        }

        public Builder bail(BigDecimal bail) {
            obj.setBail(bail);
            return this;
        }

        public Builder serviceFees(BigDecimal serviceFees) {
            obj.setServiceFees(serviceFees);
            return this;
        }

        public Builder isQuality(Boolean isQuality) {
            obj.setIsQuality(isQuality);
            return this;
        }

        public Builder isHot(Boolean isHot) {
            obj.setIsHot(isHot);
            return this;
        }

        public Builder isAgency(Boolean isAgency) {
            obj.setIsAgency(isAgency);
            return this;
        }

        public Builder agencyId(Integer agencyId) {
            obj.setAgencyId(agencyId);
            return this;
        }

        public Builder isShowAgencyName(Boolean isShowAgencyName) {
            obj.setIsShowAgencyName(isShowAgencyName);
            return this;
        }

        public Builder status(Byte status) {
            obj.setStatus(status);
            return this;
        }

        public Builder loanSchedule(BigDecimal loanSchedule) {
            obj.setLoanSchedule(loanSchedule);
            return this;
        }

        public Builder hasInvestedAmount(BigDecimal hasInvestedAmount) {
            obj.setHasInvestedAmount(hasInvestedAmount);
            return this;
        }

        public Builder readCount(Integer readCount) {
            obj.setReadCount(readCount);
            return this;
        }

        public Builder allocationSupervisorId(Long allocationSupervisorId) {
            obj.setAllocationSupervisorId(allocationSupervisorId);
            return this;
        }

        public Builder manageSupervisorId(Long manageSupervisorId) {
            obj.setManageSupervisorId(manageSupervisorId);
            return this;
        }

        public Builder auditTime(Date auditTime) {
            obj.setAuditTime(auditTime);
            return this;
        }

        public Builder auditSuggest(String auditSuggest) {
            obj.setAuditSuggest(auditSuggest);
            return this;
        }

        public Builder repaymentTime(Date repaymentTime) {
            obj.setRepaymentTime(repaymentTime);
            return this;
        }

        public Builder lastRepayTime(Date lastRepayTime) {
            obj.setLastRepayTime(lastRepayTime);
            return this;
        }

        public Builder isAuditmaticInvestBid(Boolean isAuditmaticInvestBid) {
            obj.setIsAuditmaticInvestBid(isAuditmaticInvestBid);
            return this;
        }

        public Builder version(Integer version) {
            obj.setVersion(version);
            return this;
        }

        public Builder mark(String mark) {
            obj.setMark(mark);
            return this;
        }

        public Builder qrCode(String qrCode) {
            obj.setQrCode(qrCode);
            return this;
        }

        public Builder investRate(Double investRate) {
            obj.setInvestRate(investRate);
            return this;
        }

        public Builder overdueRate(Double overdueRate) {
            obj.setOverdueRate(overdueRate);
            return this;
        }

        public Builder isRegisterGuarantor(Boolean isRegisterGuarantor) {
            obj.setIsRegisterGuarantor(isRegisterGuarantor);
            return this;
        }

        public Builder applyAmount(BigDecimal applyAmount) {
            obj.setApplyAmount(applyAmount);
            return this;
        }

        public Builder client(Byte client) {
            obj.setClient(client);
            return this;
        }

        public Builder ipsStatus(Byte ipsStatus) {
            obj.setIpsStatus(ipsStatus);
            return this;
        }

        public Builder bidType(Boolean bidType) {
            obj.setBidType(bidType);
            return this;
        }

        public Builder releaseSupervisorId(Long releaseSupervisorId) {
            obj.setReleaseSupervisorId(releaseSupervisorId);
            return this;
        }

        public Builder relevanceId(Long relevanceId) {
            obj.setRelevanceId(relevanceId);
            return this;
        }

        public Builder collectSupervisorId(Long collectSupervisorId) {
            obj.setCollectSupervisorId(collectSupervisorId);
            return this;
        }

        public Builder beginRunTime(Date beginRunTime) {
            obj.setBeginRunTime(beginRunTime);
            return this;
        }

        public Builder runTime(Date runTime) {
            obj.setRunTime(runTime);
            return this;
        }

        public Builder runRate(Double runRate) {
            obj.setRunRate(runRate);
            return this;
        }

        public Builder runCredit(Long runCredit) {
            obj.setRunCredit(runCredit);
            return this;
        }

        public Builder runRateTimes(Byte runRateTimes) {
            obj.setRunRateTimes(runRateTimes);
            return this;
        }

        public Builder runCreditTimes(Byte runCreditTimes) {
            obj.setRunCreditTimes(runCreditTimes);
            return this;
        }

        public Builder times(Byte times) {
            obj.setTimes(times);
            return this;
        }

        public Builder productIdFlag(Long productIdFlag) {
            obj.setProductIdFlag(productIdFlag);
            return this;
        }

        public Builder pushStatus(Byte pushStatus) {
            obj.setPushStatus(pushStatus);
            return this;
        }

        public Builder pushErrorMessage(String pushErrorMessage) {
            obj.setPushErrorMessage(pushErrorMessage);
            return this;
        }

        public Builder pushTime(Date pushTime) {
            obj.setPushTime(pushTime);
            return this;
        }

        public Builder intermediaryAgreement(String intermediaryAgreement) {
            obj.setIntermediaryAgreement(intermediaryAgreement);
            return this;
        }

        public Builder pact(String pact) {
            obj.setPact(pact);
            return this;
        }

        public Builder guaranteeBid(String guaranteeBid) {
            obj.setGuaranteeBid(guaranteeBid);
            return this;
        }

        public Bid build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id"),
        userId("user_id"),
        time("time"),
        merBillNo("mer_bill_no"),
        bidNo("bid_no"),
        ipsBillNo("ips_bill_no"),
        productId("product_id"),
        title("title"),
        loanPurposeId("loan_purpose_id"),
        repaymentTypeId("repayment_type_id"),
        amount("amount"),
        periodUnit("period_unit"),
        period("period"),
        minInvestAmount("min_invest_amount"),
        averageInvestAmount("average_invest_amount"),
        investPeriod("invest_period"),
        investExpireTime("invest_expire_time"),
        realInvestExpireTime("real_invest_expire_time"),
        apr("apr"),
        bankAccountId("bank_account_id"),
        bonusType("bonus_type"),
        bonus("bonus"),
        awardScale("award_scale"),
        imageFilename("image_filename"),
        description("description"),
        item("item"),
        isSecBid("is_sec_bid"),
        isDealPassword("is_deal_password"),
        showType("show_type"),
        bail("bail"),
        serviceFees("service_fees"),
        isQuality("is_quality"),
        isHot("is_hot"),
        isAgency("is_agency"),
        agencyId("agency_id"),
        isShowAgencyName("is_show_agency_name"),
        status("status"),
        loanSchedule("loan_schedule"),
        hasInvestedAmount("has_invested_amount"),
        readCount("read_count"),
        allocationSupervisorId("allocation_supervisor_id"),
        manageSupervisorId("manage_supervisor_id"),
        auditTime("audit_time"),
        auditSuggest("audit_suggest"),
        repaymentTime("repayment_time"),
        lastRepayTime("last_repay_time"),
        isAuditmaticInvestBid("is_auditmatic_invest_bid"),
        version("version"),
        mark("mark"),
        qrCode("qr_code"),
        investRate("invest_rate"),
        overdueRate("overdue_rate"),
        isRegisterGuarantor("is_register_guarantor"),
        applyAmount("apply_amount"),
        client("client"),
        ipsStatus("ips_status"),
        bidType("bid_type"),
        releaseSupervisorId("release_supervisor_id"),
        relevanceId("relevance_id"),
        collectSupervisorId("collect_supervisor_id"),
        beginRunTime("begin_run_time"),
        runTime("run_time"),
        runRate("run_rate"),
        runCredit("run_credit"),
        runRateTimes("run_rate_times"),
        runCreditTimes("run_credit_times"),
        times("times"),
        productIdFlag("product_id_flag"),
        pushStatus("push_status"),
        pushErrorMessage("push_error_message"),
        pushTime("push_time"),
        intermediaryAgreement("intermediary_agreement"),
        pact("pact"),
        guaranteeBid("guarantee_bid");

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