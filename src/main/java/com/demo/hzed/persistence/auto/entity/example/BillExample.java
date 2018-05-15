package com.demo.hzed.persistence.auto.entity.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer offset;

    protected Integer rows;

    public BillExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria(this);
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
        rows = null;
        offset = null;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return this.offset;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getRows() {
        return this.rows;
    }

    public BillExample limit(Integer rows) {
        this.rows = rows;
        return this;
    }

    public BillExample limit(Integer offset, Integer rows) {
        this.offset = offset;
        this.rows = rows;
        return this;
    }

    public BillExample page(Integer page, Integer pageSize) {
        this.offset = page * pageSize;
        this.rows = pageSize;
        return this;
    }

    public BillExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public BillExample orderBy(String ... orderByClauses) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < orderByClauses.length; i++) {
            sb.append(orderByClauses[i]);
            if (i < orderByClauses.length - 1) {
                sb.append(" , ");
            }
        }
        this.setOrderByClause(sb.toString());
        return this;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBidIdIsNull() {
            addCriterion("bid_id is null");
            return (Criteria) this;
        }

        public Criteria andBidIdIsNotNull() {
            addCriterion("bid_id is not null");
            return (Criteria) this;
        }

        public Criteria andBidIdEqualTo(Long value) {
            addCriterion("bid_id =", value, "bidId");
            return (Criteria) this;
        }

        public Criteria andBidIdNotEqualTo(Long value) {
            addCriterion("bid_id <>", value, "bidId");
            return (Criteria) this;
        }

        public Criteria andBidIdGreaterThan(Long value) {
            addCriterion("bid_id >", value, "bidId");
            return (Criteria) this;
        }

        public Criteria andBidIdGreaterThanOrEqualTo(Long value) {
            addCriterion("bid_id >=", value, "bidId");
            return (Criteria) this;
        }

        public Criteria andBidIdLessThan(Long value) {
            addCriterion("bid_id <", value, "bidId");
            return (Criteria) this;
        }

        public Criteria andBidIdLessThanOrEqualTo(Long value) {
            addCriterion("bid_id <=", value, "bidId");
            return (Criteria) this;
        }

        public Criteria andBidIdIn(List<Long> values) {
            addCriterion("bid_id in", values, "bidId");
            return (Criteria) this;
        }

        public Criteria andBidIdNotIn(List<Long> values) {
            addCriterion("bid_id not in", values, "bidId");
            return (Criteria) this;
        }

        public Criteria andBidIdBetween(Long value1, Long value2) {
            addCriterion("bid_id between", value1, value2, "bidId");
            return (Criteria) this;
        }

        public Criteria andBidIdNotBetween(Long value1, Long value2) {
            addCriterion("bid_id not between", value1, value2, "bidId");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andPeriodsIsNull() {
            addCriterion("periods is null");
            return (Criteria) this;
        }

        public Criteria andPeriodsIsNotNull() {
            addCriterion("periods is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodsEqualTo(Byte value) {
            addCriterion("periods =", value, "periods");
            return (Criteria) this;
        }

        public Criteria andPeriodsNotEqualTo(Byte value) {
            addCriterion("periods <>", value, "periods");
            return (Criteria) this;
        }

        public Criteria andPeriodsGreaterThan(Byte value) {
            addCriterion("periods >", value, "periods");
            return (Criteria) this;
        }

        public Criteria andPeriodsGreaterThanOrEqualTo(Byte value) {
            addCriterion("periods >=", value, "periods");
            return (Criteria) this;
        }

        public Criteria andPeriodsLessThan(Byte value) {
            addCriterion("periods <", value, "periods");
            return (Criteria) this;
        }

        public Criteria andPeriodsLessThanOrEqualTo(Byte value) {
            addCriterion("periods <=", value, "periods");
            return (Criteria) this;
        }

        public Criteria andPeriodsIn(List<Byte> values) {
            addCriterion("periods in", values, "periods");
            return (Criteria) this;
        }

        public Criteria andPeriodsNotIn(List<Byte> values) {
            addCriterion("periods not in", values, "periods");
            return (Criteria) this;
        }

        public Criteria andPeriodsBetween(Byte value1, Byte value2) {
            addCriterion("periods between", value1, value2, "periods");
            return (Criteria) this;
        }

        public Criteria andPeriodsNotBetween(Byte value1, Byte value2) {
            addCriterion("periods not between", value1, value2, "periods");
            return (Criteria) this;
        }

        public Criteria andRepaymentTimeIsNull() {
            addCriterion("repayment_time is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentTimeIsNotNull() {
            addCriterion("repayment_time is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentTimeEqualTo(Date value) {
            addCriterion("repayment_time =", value, "repaymentTime");
            return (Criteria) this;
        }

        public Criteria andRepaymentTimeNotEqualTo(Date value) {
            addCriterion("repayment_time <>", value, "repaymentTime");
            return (Criteria) this;
        }

        public Criteria andRepaymentTimeGreaterThan(Date value) {
            addCriterion("repayment_time >", value, "repaymentTime");
            return (Criteria) this;
        }

        public Criteria andRepaymentTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("repayment_time >=", value, "repaymentTime");
            return (Criteria) this;
        }

        public Criteria andRepaymentTimeLessThan(Date value) {
            addCriterion("repayment_time <", value, "repaymentTime");
            return (Criteria) this;
        }

        public Criteria andRepaymentTimeLessThanOrEqualTo(Date value) {
            addCriterion("repayment_time <=", value, "repaymentTime");
            return (Criteria) this;
        }

        public Criteria andRepaymentTimeIn(List<Date> values) {
            addCriterion("repayment_time in", values, "repaymentTime");
            return (Criteria) this;
        }

        public Criteria andRepaymentTimeNotIn(List<Date> values) {
            addCriterion("repayment_time not in", values, "repaymentTime");
            return (Criteria) this;
        }

        public Criteria andRepaymentTimeBetween(Date value1, Date value2) {
            addCriterion("repayment_time between", value1, value2, "repaymentTime");
            return (Criteria) this;
        }

        public Criteria andRepaymentTimeNotBetween(Date value1, Date value2) {
            addCriterion("repayment_time not between", value1, value2, "repaymentTime");
            return (Criteria) this;
        }

        public Criteria andRepaymentCorpusIsNull() {
            addCriterion("repayment_corpus is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentCorpusIsNotNull() {
            addCriterion("repayment_corpus is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentCorpusEqualTo(BigDecimal value) {
            addCriterion("repayment_corpus =", value, "repaymentCorpus");
            return (Criteria) this;
        }

        public Criteria andRepaymentCorpusNotEqualTo(BigDecimal value) {
            addCriterion("repayment_corpus <>", value, "repaymentCorpus");
            return (Criteria) this;
        }

        public Criteria andRepaymentCorpusGreaterThan(BigDecimal value) {
            addCriterion("repayment_corpus >", value, "repaymentCorpus");
            return (Criteria) this;
        }

        public Criteria andRepaymentCorpusGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("repayment_corpus >=", value, "repaymentCorpus");
            return (Criteria) this;
        }

        public Criteria andRepaymentCorpusLessThan(BigDecimal value) {
            addCriterion("repayment_corpus <", value, "repaymentCorpus");
            return (Criteria) this;
        }

        public Criteria andRepaymentCorpusLessThanOrEqualTo(BigDecimal value) {
            addCriterion("repayment_corpus <=", value, "repaymentCorpus");
            return (Criteria) this;
        }

        public Criteria andRepaymentCorpusIn(List<BigDecimal> values) {
            addCriterion("repayment_corpus in", values, "repaymentCorpus");
            return (Criteria) this;
        }

        public Criteria andRepaymentCorpusNotIn(List<BigDecimal> values) {
            addCriterion("repayment_corpus not in", values, "repaymentCorpus");
            return (Criteria) this;
        }

        public Criteria andRepaymentCorpusBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("repayment_corpus between", value1, value2, "repaymentCorpus");
            return (Criteria) this;
        }

        public Criteria andRepaymentCorpusNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("repayment_corpus not between", value1, value2, "repaymentCorpus");
            return (Criteria) this;
        }

        public Criteria andRepaymentInterestIsNull() {
            addCriterion("repayment_interest is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentInterestIsNotNull() {
            addCriterion("repayment_interest is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentInterestEqualTo(BigDecimal value) {
            addCriterion("repayment_interest =", value, "repaymentInterest");
            return (Criteria) this;
        }

        public Criteria andRepaymentInterestNotEqualTo(BigDecimal value) {
            addCriterion("repayment_interest <>", value, "repaymentInterest");
            return (Criteria) this;
        }

        public Criteria andRepaymentInterestGreaterThan(BigDecimal value) {
            addCriterion("repayment_interest >", value, "repaymentInterest");
            return (Criteria) this;
        }

        public Criteria andRepaymentInterestGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("repayment_interest >=", value, "repaymentInterest");
            return (Criteria) this;
        }

        public Criteria andRepaymentInterestLessThan(BigDecimal value) {
            addCriterion("repayment_interest <", value, "repaymentInterest");
            return (Criteria) this;
        }

        public Criteria andRepaymentInterestLessThanOrEqualTo(BigDecimal value) {
            addCriterion("repayment_interest <=", value, "repaymentInterest");
            return (Criteria) this;
        }

        public Criteria andRepaymentInterestIn(List<BigDecimal> values) {
            addCriterion("repayment_interest in", values, "repaymentInterest");
            return (Criteria) this;
        }

        public Criteria andRepaymentInterestNotIn(List<BigDecimal> values) {
            addCriterion("repayment_interest not in", values, "repaymentInterest");
            return (Criteria) this;
        }

        public Criteria andRepaymentInterestBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("repayment_interest between", value1, value2, "repaymentInterest");
            return (Criteria) this;
        }

        public Criteria andRepaymentInterestNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("repayment_interest not between", value1, value2, "repaymentInterest");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andMerBillNoIsNull() {
            addCriterion("mer_bill_no is null");
            return (Criteria) this;
        }

        public Criteria andMerBillNoIsNotNull() {
            addCriterion("mer_bill_no is not null");
            return (Criteria) this;
        }

        public Criteria andMerBillNoEqualTo(String value) {
            addCriterion("mer_bill_no =", value, "merBillNo");
            return (Criteria) this;
        }

        public Criteria andMerBillNoNotEqualTo(String value) {
            addCriterion("mer_bill_no <>", value, "merBillNo");
            return (Criteria) this;
        }

        public Criteria andMerBillNoGreaterThan(String value) {
            addCriterion("mer_bill_no >", value, "merBillNo");
            return (Criteria) this;
        }

        public Criteria andMerBillNoGreaterThanOrEqualTo(String value) {
            addCriterion("mer_bill_no >=", value, "merBillNo");
            return (Criteria) this;
        }

        public Criteria andMerBillNoLessThan(String value) {
            addCriterion("mer_bill_no <", value, "merBillNo");
            return (Criteria) this;
        }

        public Criteria andMerBillNoLessThanOrEqualTo(String value) {
            addCriterion("mer_bill_no <=", value, "merBillNo");
            return (Criteria) this;
        }

        public Criteria andMerBillNoLike(String value) {
            addCriterion("mer_bill_no like", value, "merBillNo");
            return (Criteria) this;
        }

        public Criteria andMerBillNoNotLike(String value) {
            addCriterion("mer_bill_no not like", value, "merBillNo");
            return (Criteria) this;
        }

        public Criteria andMerBillNoIn(List<String> values) {
            addCriterion("mer_bill_no in", values, "merBillNo");
            return (Criteria) this;
        }

        public Criteria andMerBillNoNotIn(List<String> values) {
            addCriterion("mer_bill_no not in", values, "merBillNo");
            return (Criteria) this;
        }

        public Criteria andMerBillNoBetween(String value1, String value2) {
            addCriterion("mer_bill_no between", value1, value2, "merBillNo");
            return (Criteria) this;
        }

        public Criteria andMerBillNoNotBetween(String value1, String value2) {
            addCriterion("mer_bill_no not between", value1, value2, "merBillNo");
            return (Criteria) this;
        }

        public Criteria andRepaymentBillNoIsNull() {
            addCriterion("repayment_bill_no is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentBillNoIsNotNull() {
            addCriterion("repayment_bill_no is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentBillNoEqualTo(String value) {
            addCriterion("repayment_bill_no =", value, "repaymentBillNo");
            return (Criteria) this;
        }

        public Criteria andRepaymentBillNoNotEqualTo(String value) {
            addCriterion("repayment_bill_no <>", value, "repaymentBillNo");
            return (Criteria) this;
        }

        public Criteria andRepaymentBillNoGreaterThan(String value) {
            addCriterion("repayment_bill_no >", value, "repaymentBillNo");
            return (Criteria) this;
        }

        public Criteria andRepaymentBillNoGreaterThanOrEqualTo(String value) {
            addCriterion("repayment_bill_no >=", value, "repaymentBillNo");
            return (Criteria) this;
        }

        public Criteria andRepaymentBillNoLessThan(String value) {
            addCriterion("repayment_bill_no <", value, "repaymentBillNo");
            return (Criteria) this;
        }

        public Criteria andRepaymentBillNoLessThanOrEqualTo(String value) {
            addCriterion("repayment_bill_no <=", value, "repaymentBillNo");
            return (Criteria) this;
        }

        public Criteria andRepaymentBillNoLike(String value) {
            addCriterion("repayment_bill_no like", value, "repaymentBillNo");
            return (Criteria) this;
        }

        public Criteria andRepaymentBillNoNotLike(String value) {
            addCriterion("repayment_bill_no not like", value, "repaymentBillNo");
            return (Criteria) this;
        }

        public Criteria andRepaymentBillNoIn(List<String> values) {
            addCriterion("repayment_bill_no in", values, "repaymentBillNo");
            return (Criteria) this;
        }

        public Criteria andRepaymentBillNoNotIn(List<String> values) {
            addCriterion("repayment_bill_no not in", values, "repaymentBillNo");
            return (Criteria) this;
        }

        public Criteria andRepaymentBillNoBetween(String value1, String value2) {
            addCriterion("repayment_bill_no between", value1, value2, "repaymentBillNo");
            return (Criteria) this;
        }

        public Criteria andRepaymentBillNoNotBetween(String value1, String value2) {
            addCriterion("repayment_bill_no not between", value1, value2, "repaymentBillNo");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentTimeIsNull() {
            addCriterion("real_repayment_time is null");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentTimeIsNotNull() {
            addCriterion("real_repayment_time is not null");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentTimeEqualTo(Date value) {
            addCriterion("real_repayment_time =", value, "realRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentTimeNotEqualTo(Date value) {
            addCriterion("real_repayment_time <>", value, "realRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentTimeGreaterThan(Date value) {
            addCriterion("real_repayment_time >", value, "realRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("real_repayment_time >=", value, "realRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentTimeLessThan(Date value) {
            addCriterion("real_repayment_time <", value, "realRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentTimeLessThanOrEqualTo(Date value) {
            addCriterion("real_repayment_time <=", value, "realRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentTimeIn(List<Date> values) {
            addCriterion("real_repayment_time in", values, "realRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentTimeNotIn(List<Date> values) {
            addCriterion("real_repayment_time not in", values, "realRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentTimeBetween(Date value1, Date value2) {
            addCriterion("real_repayment_time between", value1, value2, "realRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentTimeNotBetween(Date value1, Date value2) {
            addCriterion("real_repayment_time not between", value1, value2, "realRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentCorpusIsNull() {
            addCriterion("real_repayment_corpus is null");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentCorpusIsNotNull() {
            addCriterion("real_repayment_corpus is not null");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentCorpusEqualTo(BigDecimal value) {
            addCriterion("real_repayment_corpus =", value, "realRepaymentCorpus");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentCorpusNotEqualTo(BigDecimal value) {
            addCriterion("real_repayment_corpus <>", value, "realRepaymentCorpus");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentCorpusGreaterThan(BigDecimal value) {
            addCriterion("real_repayment_corpus >", value, "realRepaymentCorpus");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentCorpusGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("real_repayment_corpus >=", value, "realRepaymentCorpus");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentCorpusLessThan(BigDecimal value) {
            addCriterion("real_repayment_corpus <", value, "realRepaymentCorpus");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentCorpusLessThanOrEqualTo(BigDecimal value) {
            addCriterion("real_repayment_corpus <=", value, "realRepaymentCorpus");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentCorpusIn(List<BigDecimal> values) {
            addCriterion("real_repayment_corpus in", values, "realRepaymentCorpus");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentCorpusNotIn(List<BigDecimal> values) {
            addCriterion("real_repayment_corpus not in", values, "realRepaymentCorpus");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentCorpusBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("real_repayment_corpus between", value1, value2, "realRepaymentCorpus");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentCorpusNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("real_repayment_corpus not between", value1, value2, "realRepaymentCorpus");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentInterestIsNull() {
            addCriterion("real_repayment_interest is null");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentInterestIsNotNull() {
            addCriterion("real_repayment_interest is not null");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentInterestEqualTo(BigDecimal value) {
            addCriterion("real_repayment_interest =", value, "realRepaymentInterest");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentInterestNotEqualTo(BigDecimal value) {
            addCriterion("real_repayment_interest <>", value, "realRepaymentInterest");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentInterestGreaterThan(BigDecimal value) {
            addCriterion("real_repayment_interest >", value, "realRepaymentInterest");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentInterestGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("real_repayment_interest >=", value, "realRepaymentInterest");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentInterestLessThan(BigDecimal value) {
            addCriterion("real_repayment_interest <", value, "realRepaymentInterest");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentInterestLessThanOrEqualTo(BigDecimal value) {
            addCriterion("real_repayment_interest <=", value, "realRepaymentInterest");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentInterestIn(List<BigDecimal> values) {
            addCriterion("real_repayment_interest in", values, "realRepaymentInterest");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentInterestNotIn(List<BigDecimal> values) {
            addCriterion("real_repayment_interest not in", values, "realRepaymentInterest");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentInterestBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("real_repayment_interest between", value1, value2, "realRepaymentInterest");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentInterestNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("real_repayment_interest not between", value1, value2, "realRepaymentInterest");
            return (Criteria) this;
        }

        public Criteria andOverdueMarkIsNull() {
            addCriterion("overdue_mark is null");
            return (Criteria) this;
        }

        public Criteria andOverdueMarkIsNotNull() {
            addCriterion("overdue_mark is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueMarkEqualTo(Byte value) {
            addCriterion("overdue_mark =", value, "overdueMark");
            return (Criteria) this;
        }

        public Criteria andOverdueMarkNotEqualTo(Byte value) {
            addCriterion("overdue_mark <>", value, "overdueMark");
            return (Criteria) this;
        }

        public Criteria andOverdueMarkGreaterThan(Byte value) {
            addCriterion("overdue_mark >", value, "overdueMark");
            return (Criteria) this;
        }

        public Criteria andOverdueMarkGreaterThanOrEqualTo(Byte value) {
            addCriterion("overdue_mark >=", value, "overdueMark");
            return (Criteria) this;
        }

        public Criteria andOverdueMarkLessThan(Byte value) {
            addCriterion("overdue_mark <", value, "overdueMark");
            return (Criteria) this;
        }

        public Criteria andOverdueMarkLessThanOrEqualTo(Byte value) {
            addCriterion("overdue_mark <=", value, "overdueMark");
            return (Criteria) this;
        }

        public Criteria andOverdueMarkIn(List<Byte> values) {
            addCriterion("overdue_mark in", values, "overdueMark");
            return (Criteria) this;
        }

        public Criteria andOverdueMarkNotIn(List<Byte> values) {
            addCriterion("overdue_mark not in", values, "overdueMark");
            return (Criteria) this;
        }

        public Criteria andOverdueMarkBetween(Byte value1, Byte value2) {
            addCriterion("overdue_mark between", value1, value2, "overdueMark");
            return (Criteria) this;
        }

        public Criteria andOverdueMarkNotBetween(Byte value1, Byte value2) {
            addCriterion("overdue_mark not between", value1, value2, "overdueMark");
            return (Criteria) this;
        }

        public Criteria andMarkOverdueTimeIsNull() {
            addCriterion("mark_overdue_time is null");
            return (Criteria) this;
        }

        public Criteria andMarkOverdueTimeIsNotNull() {
            addCriterion("mark_overdue_time is not null");
            return (Criteria) this;
        }

        public Criteria andMarkOverdueTimeEqualTo(Date value) {
            addCriterion("mark_overdue_time =", value, "markOverdueTime");
            return (Criteria) this;
        }

        public Criteria andMarkOverdueTimeNotEqualTo(Date value) {
            addCriterion("mark_overdue_time <>", value, "markOverdueTime");
            return (Criteria) this;
        }

        public Criteria andMarkOverdueTimeGreaterThan(Date value) {
            addCriterion("mark_overdue_time >", value, "markOverdueTime");
            return (Criteria) this;
        }

        public Criteria andMarkOverdueTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("mark_overdue_time >=", value, "markOverdueTime");
            return (Criteria) this;
        }

        public Criteria andMarkOverdueTimeLessThan(Date value) {
            addCriterion("mark_overdue_time <", value, "markOverdueTime");
            return (Criteria) this;
        }

        public Criteria andMarkOverdueTimeLessThanOrEqualTo(Date value) {
            addCriterion("mark_overdue_time <=", value, "markOverdueTime");
            return (Criteria) this;
        }

        public Criteria andMarkOverdueTimeIn(List<Date> values) {
            addCriterion("mark_overdue_time in", values, "markOverdueTime");
            return (Criteria) this;
        }

        public Criteria andMarkOverdueTimeNotIn(List<Date> values) {
            addCriterion("mark_overdue_time not in", values, "markOverdueTime");
            return (Criteria) this;
        }

        public Criteria andMarkOverdueTimeBetween(Date value1, Date value2) {
            addCriterion("mark_overdue_time between", value1, value2, "markOverdueTime");
            return (Criteria) this;
        }

        public Criteria andMarkOverdueTimeNotBetween(Date value1, Date value2) {
            addCriterion("mark_overdue_time not between", value1, value2, "markOverdueTime");
            return (Criteria) this;
        }

        public Criteria andOverdueFineIsNull() {
            addCriterion("overdue_fine is null");
            return (Criteria) this;
        }

        public Criteria andOverdueFineIsNotNull() {
            addCriterion("overdue_fine is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueFineEqualTo(BigDecimal value) {
            addCriterion("overdue_fine =", value, "overdueFine");
            return (Criteria) this;
        }

        public Criteria andOverdueFineNotEqualTo(BigDecimal value) {
            addCriterion("overdue_fine <>", value, "overdueFine");
            return (Criteria) this;
        }

        public Criteria andOverdueFineGreaterThan(BigDecimal value) {
            addCriterion("overdue_fine >", value, "overdueFine");
            return (Criteria) this;
        }

        public Criteria andOverdueFineGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("overdue_fine >=", value, "overdueFine");
            return (Criteria) this;
        }

        public Criteria andOverdueFineLessThan(BigDecimal value) {
            addCriterion("overdue_fine <", value, "overdueFine");
            return (Criteria) this;
        }

        public Criteria andOverdueFineLessThanOrEqualTo(BigDecimal value) {
            addCriterion("overdue_fine <=", value, "overdueFine");
            return (Criteria) this;
        }

        public Criteria andOverdueFineIn(List<BigDecimal> values) {
            addCriterion("overdue_fine in", values, "overdueFine");
            return (Criteria) this;
        }

        public Criteria andOverdueFineNotIn(List<BigDecimal> values) {
            addCriterion("overdue_fine not in", values, "overdueFine");
            return (Criteria) this;
        }

        public Criteria andOverdueFineBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("overdue_fine between", value1, value2, "overdueFine");
            return (Criteria) this;
        }

        public Criteria andOverdueFineNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("overdue_fine not between", value1, value2, "overdueFine");
            return (Criteria) this;
        }

        public Criteria andMarkBadTimeIsNull() {
            addCriterion("mark_bad_time is null");
            return (Criteria) this;
        }

        public Criteria andMarkBadTimeIsNotNull() {
            addCriterion("mark_bad_time is not null");
            return (Criteria) this;
        }

        public Criteria andMarkBadTimeEqualTo(Date value) {
            addCriterion("mark_bad_time =", value, "markBadTime");
            return (Criteria) this;
        }

        public Criteria andMarkBadTimeNotEqualTo(Date value) {
            addCriterion("mark_bad_time <>", value, "markBadTime");
            return (Criteria) this;
        }

        public Criteria andMarkBadTimeGreaterThan(Date value) {
            addCriterion("mark_bad_time >", value, "markBadTime");
            return (Criteria) this;
        }

        public Criteria andMarkBadTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("mark_bad_time >=", value, "markBadTime");
            return (Criteria) this;
        }

        public Criteria andMarkBadTimeLessThan(Date value) {
            addCriterion("mark_bad_time <", value, "markBadTime");
            return (Criteria) this;
        }

        public Criteria andMarkBadTimeLessThanOrEqualTo(Date value) {
            addCriterion("mark_bad_time <=", value, "markBadTime");
            return (Criteria) this;
        }

        public Criteria andMarkBadTimeIn(List<Date> values) {
            addCriterion("mark_bad_time in", values, "markBadTime");
            return (Criteria) this;
        }

        public Criteria andMarkBadTimeNotIn(List<Date> values) {
            addCriterion("mark_bad_time not in", values, "markBadTime");
            return (Criteria) this;
        }

        public Criteria andMarkBadTimeBetween(Date value1, Date value2) {
            addCriterion("mark_bad_time between", value1, value2, "markBadTime");
            return (Criteria) this;
        }

        public Criteria andMarkBadTimeNotBetween(Date value1, Date value2) {
            addCriterion("mark_bad_time not between", value1, value2, "markBadTime");
            return (Criteria) this;
        }

        public Criteria andNoticeCountMessageIsNull() {
            addCriterion("notice_count_message is null");
            return (Criteria) this;
        }

        public Criteria andNoticeCountMessageIsNotNull() {
            addCriterion("notice_count_message is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeCountMessageEqualTo(Integer value) {
            addCriterion("notice_count_message =", value, "noticeCountMessage");
            return (Criteria) this;
        }

        public Criteria andNoticeCountMessageNotEqualTo(Integer value) {
            addCriterion("notice_count_message <>", value, "noticeCountMessage");
            return (Criteria) this;
        }

        public Criteria andNoticeCountMessageGreaterThan(Integer value) {
            addCriterion("notice_count_message >", value, "noticeCountMessage");
            return (Criteria) this;
        }

        public Criteria andNoticeCountMessageGreaterThanOrEqualTo(Integer value) {
            addCriterion("notice_count_message >=", value, "noticeCountMessage");
            return (Criteria) this;
        }

        public Criteria andNoticeCountMessageLessThan(Integer value) {
            addCriterion("notice_count_message <", value, "noticeCountMessage");
            return (Criteria) this;
        }

        public Criteria andNoticeCountMessageLessThanOrEqualTo(Integer value) {
            addCriterion("notice_count_message <=", value, "noticeCountMessage");
            return (Criteria) this;
        }

        public Criteria andNoticeCountMessageIn(List<Integer> values) {
            addCriterion("notice_count_message in", values, "noticeCountMessage");
            return (Criteria) this;
        }

        public Criteria andNoticeCountMessageNotIn(List<Integer> values) {
            addCriterion("notice_count_message not in", values, "noticeCountMessage");
            return (Criteria) this;
        }

        public Criteria andNoticeCountMessageBetween(Integer value1, Integer value2) {
            addCriterion("notice_count_message between", value1, value2, "noticeCountMessage");
            return (Criteria) this;
        }

        public Criteria andNoticeCountMessageNotBetween(Integer value1, Integer value2) {
            addCriterion("notice_count_message not between", value1, value2, "noticeCountMessage");
            return (Criteria) this;
        }

        public Criteria andNoticeCountMailIsNull() {
            addCriterion("notice_count_mail is null");
            return (Criteria) this;
        }

        public Criteria andNoticeCountMailIsNotNull() {
            addCriterion("notice_count_mail is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeCountMailEqualTo(Integer value) {
            addCriterion("notice_count_mail =", value, "noticeCountMail");
            return (Criteria) this;
        }

        public Criteria andNoticeCountMailNotEqualTo(Integer value) {
            addCriterion("notice_count_mail <>", value, "noticeCountMail");
            return (Criteria) this;
        }

        public Criteria andNoticeCountMailGreaterThan(Integer value) {
            addCriterion("notice_count_mail >", value, "noticeCountMail");
            return (Criteria) this;
        }

        public Criteria andNoticeCountMailGreaterThanOrEqualTo(Integer value) {
            addCriterion("notice_count_mail >=", value, "noticeCountMail");
            return (Criteria) this;
        }

        public Criteria andNoticeCountMailLessThan(Integer value) {
            addCriterion("notice_count_mail <", value, "noticeCountMail");
            return (Criteria) this;
        }

        public Criteria andNoticeCountMailLessThanOrEqualTo(Integer value) {
            addCriterion("notice_count_mail <=", value, "noticeCountMail");
            return (Criteria) this;
        }

        public Criteria andNoticeCountMailIn(List<Integer> values) {
            addCriterion("notice_count_mail in", values, "noticeCountMail");
            return (Criteria) this;
        }

        public Criteria andNoticeCountMailNotIn(List<Integer> values) {
            addCriterion("notice_count_mail not in", values, "noticeCountMail");
            return (Criteria) this;
        }

        public Criteria andNoticeCountMailBetween(Integer value1, Integer value2) {
            addCriterion("notice_count_mail between", value1, value2, "noticeCountMail");
            return (Criteria) this;
        }

        public Criteria andNoticeCountMailNotBetween(Integer value1, Integer value2) {
            addCriterion("notice_count_mail not between", value1, value2, "noticeCountMail");
            return (Criteria) this;
        }

        public Criteria andNoticeCountTelphoneIsNull() {
            addCriterion("notice_count_telphone is null");
            return (Criteria) this;
        }

        public Criteria andNoticeCountTelphoneIsNotNull() {
            addCriterion("notice_count_telphone is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeCountTelphoneEqualTo(Integer value) {
            addCriterion("notice_count_telphone =", value, "noticeCountTelphone");
            return (Criteria) this;
        }

        public Criteria andNoticeCountTelphoneNotEqualTo(Integer value) {
            addCriterion("notice_count_telphone <>", value, "noticeCountTelphone");
            return (Criteria) this;
        }

        public Criteria andNoticeCountTelphoneGreaterThan(Integer value) {
            addCriterion("notice_count_telphone >", value, "noticeCountTelphone");
            return (Criteria) this;
        }

        public Criteria andNoticeCountTelphoneGreaterThanOrEqualTo(Integer value) {
            addCriterion("notice_count_telphone >=", value, "noticeCountTelphone");
            return (Criteria) this;
        }

        public Criteria andNoticeCountTelphoneLessThan(Integer value) {
            addCriterion("notice_count_telphone <", value, "noticeCountTelphone");
            return (Criteria) this;
        }

        public Criteria andNoticeCountTelphoneLessThanOrEqualTo(Integer value) {
            addCriterion("notice_count_telphone <=", value, "noticeCountTelphone");
            return (Criteria) this;
        }

        public Criteria andNoticeCountTelphoneIn(List<Integer> values) {
            addCriterion("notice_count_telphone in", values, "noticeCountTelphone");
            return (Criteria) this;
        }

        public Criteria andNoticeCountTelphoneNotIn(List<Integer> values) {
            addCriterion("notice_count_telphone not in", values, "noticeCountTelphone");
            return (Criteria) this;
        }

        public Criteria andNoticeCountTelphoneBetween(Integer value1, Integer value2) {
            addCriterion("notice_count_telphone between", value1, value2, "noticeCountTelphone");
            return (Criteria) this;
        }

        public Criteria andNoticeCountTelphoneNotBetween(Integer value1, Integer value2) {
            addCriterion("notice_count_telphone not between", value1, value2, "noticeCountTelphone");
            return (Criteria) this;
        }

        public Criteria andIpsStatusIsNull() {
            addCriterion("ips_status is null");
            return (Criteria) this;
        }

        public Criteria andIpsStatusIsNotNull() {
            addCriterion("ips_status is not null");
            return (Criteria) this;
        }

        public Criteria andIpsStatusEqualTo(Byte value) {
            addCriterion("ips_status =", value, "ipsStatus");
            return (Criteria) this;
        }

        public Criteria andIpsStatusNotEqualTo(Byte value) {
            addCriterion("ips_status <>", value, "ipsStatus");
            return (Criteria) this;
        }

        public Criteria andIpsStatusGreaterThan(Byte value) {
            addCriterion("ips_status >", value, "ipsStatus");
            return (Criteria) this;
        }

        public Criteria andIpsStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("ips_status >=", value, "ipsStatus");
            return (Criteria) this;
        }

        public Criteria andIpsStatusLessThan(Byte value) {
            addCriterion("ips_status <", value, "ipsStatus");
            return (Criteria) this;
        }

        public Criteria andIpsStatusLessThanOrEqualTo(Byte value) {
            addCriterion("ips_status <=", value, "ipsStatus");
            return (Criteria) this;
        }

        public Criteria andIpsStatusIn(List<Byte> values) {
            addCriterion("ips_status in", values, "ipsStatus");
            return (Criteria) this;
        }

        public Criteria andIpsStatusNotIn(List<Byte> values) {
            addCriterion("ips_status not in", values, "ipsStatus");
            return (Criteria) this;
        }

        public Criteria andIpsStatusBetween(Byte value1, Byte value2) {
            addCriterion("ips_status between", value1, value2, "ipsStatus");
            return (Criteria) this;
        }

        public Criteria andIpsStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("ips_status not between", value1, value2, "ipsStatus");
            return (Criteria) this;
        }

        public Criteria andAuditFreeIsNull() {
            addCriterion("audit_free is null");
            return (Criteria) this;
        }

        public Criteria andAuditFreeIsNotNull() {
            addCriterion("audit_free is not null");
            return (Criteria) this;
        }

        public Criteria andAuditFreeEqualTo(Double value) {
            addCriterion("audit_free =", value, "auditFree");
            return (Criteria) this;
        }

        public Criteria andAuditFreeNotEqualTo(Double value) {
            addCriterion("audit_free <>", value, "auditFree");
            return (Criteria) this;
        }

        public Criteria andAuditFreeGreaterThan(Double value) {
            addCriterion("audit_free >", value, "auditFree");
            return (Criteria) this;
        }

        public Criteria andAuditFreeGreaterThanOrEqualTo(Double value) {
            addCriterion("audit_free >=", value, "auditFree");
            return (Criteria) this;
        }

        public Criteria andAuditFreeLessThan(Double value) {
            addCriterion("audit_free <", value, "auditFree");
            return (Criteria) this;
        }

        public Criteria andAuditFreeLessThanOrEqualTo(Double value) {
            addCriterion("audit_free <=", value, "auditFree");
            return (Criteria) this;
        }

        public Criteria andAuditFreeIn(List<Double> values) {
            addCriterion("audit_free in", values, "auditFree");
            return (Criteria) this;
        }

        public Criteria andAuditFreeNotIn(List<Double> values) {
            addCriterion("audit_free not in", values, "auditFree");
            return (Criteria) this;
        }

        public Criteria andAuditFreeBetween(Double value1, Double value2) {
            addCriterion("audit_free between", value1, value2, "auditFree");
            return (Criteria) this;
        }

        public Criteria andAuditFreeNotBetween(Double value1, Double value2) {
            addCriterion("audit_free not between", value1, value2, "auditFree");
            return (Criteria) this;
        }

        public Criteria andServiceFreeIsNull() {
            addCriterion("service_free is null");
            return (Criteria) this;
        }

        public Criteria andServiceFreeIsNotNull() {
            addCriterion("service_free is not null");
            return (Criteria) this;
        }

        public Criteria andServiceFreeEqualTo(Double value) {
            addCriterion("service_free =", value, "serviceFree");
            return (Criteria) this;
        }

        public Criteria andServiceFreeNotEqualTo(Double value) {
            addCriterion("service_free <>", value, "serviceFree");
            return (Criteria) this;
        }

        public Criteria andServiceFreeGreaterThan(Double value) {
            addCriterion("service_free >", value, "serviceFree");
            return (Criteria) this;
        }

        public Criteria andServiceFreeGreaterThanOrEqualTo(Double value) {
            addCriterion("service_free >=", value, "serviceFree");
            return (Criteria) this;
        }

        public Criteria andServiceFreeLessThan(Double value) {
            addCriterion("service_free <", value, "serviceFree");
            return (Criteria) this;
        }

        public Criteria andServiceFreeLessThanOrEqualTo(Double value) {
            addCriterion("service_free <=", value, "serviceFree");
            return (Criteria) this;
        }

        public Criteria andServiceFreeIn(List<Double> values) {
            addCriterion("service_free in", values, "serviceFree");
            return (Criteria) this;
        }

        public Criteria andServiceFreeNotIn(List<Double> values) {
            addCriterion("service_free not in", values, "serviceFree");
            return (Criteria) this;
        }

        public Criteria andServiceFreeBetween(Double value1, Double value2) {
            addCriterion("service_free between", value1, value2, "serviceFree");
            return (Criteria) this;
        }

        public Criteria andServiceFreeNotBetween(Double value1, Double value2) {
            addCriterion("service_free not between", value1, value2, "serviceFree");
            return (Criteria) this;
        }

        public Criteria andAdvanceRepayFeeIsNull() {
            addCriterion("advance_repay_fee is null");
            return (Criteria) this;
        }

        public Criteria andAdvanceRepayFeeIsNotNull() {
            addCriterion("advance_repay_fee is not null");
            return (Criteria) this;
        }

        public Criteria andAdvanceRepayFeeEqualTo(Double value) {
            addCriterion("advance_repay_fee =", value, "advanceRepayFee");
            return (Criteria) this;
        }

        public Criteria andAdvanceRepayFeeNotEqualTo(Double value) {
            addCriterion("advance_repay_fee <>", value, "advanceRepayFee");
            return (Criteria) this;
        }

        public Criteria andAdvanceRepayFeeGreaterThan(Double value) {
            addCriterion("advance_repay_fee >", value, "advanceRepayFee");
            return (Criteria) this;
        }

        public Criteria andAdvanceRepayFeeGreaterThanOrEqualTo(Double value) {
            addCriterion("advance_repay_fee >=", value, "advanceRepayFee");
            return (Criteria) this;
        }

        public Criteria andAdvanceRepayFeeLessThan(Double value) {
            addCriterion("advance_repay_fee <", value, "advanceRepayFee");
            return (Criteria) this;
        }

        public Criteria andAdvanceRepayFeeLessThanOrEqualTo(Double value) {
            addCriterion("advance_repay_fee <=", value, "advanceRepayFee");
            return (Criteria) this;
        }

        public Criteria andAdvanceRepayFeeIn(List<Double> values) {
            addCriterion("advance_repay_fee in", values, "advanceRepayFee");
            return (Criteria) this;
        }

        public Criteria andAdvanceRepayFeeNotIn(List<Double> values) {
            addCriterion("advance_repay_fee not in", values, "advanceRepayFee");
            return (Criteria) this;
        }

        public Criteria andAdvanceRepayFeeBetween(Double value1, Double value2) {
            addCriterion("advance_repay_fee between", value1, value2, "advanceRepayFee");
            return (Criteria) this;
        }

        public Criteria andAdvanceRepayFeeNotBetween(Double value1, Double value2) {
            addCriterion("advance_repay_fee not between", value1, value2, "advanceRepayFee");
            return (Criteria) this;
        }

        public Criteria andOverdueFineFeeIsNull() {
            addCriterion("overdue_fine_fee is null");
            return (Criteria) this;
        }

        public Criteria andOverdueFineFeeIsNotNull() {
            addCriterion("overdue_fine_fee is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueFineFeeEqualTo(Double value) {
            addCriterion("overdue_fine_fee =", value, "overdueFineFee");
            return (Criteria) this;
        }

        public Criteria andOverdueFineFeeNotEqualTo(Double value) {
            addCriterion("overdue_fine_fee <>", value, "overdueFineFee");
            return (Criteria) this;
        }

        public Criteria andOverdueFineFeeGreaterThan(Double value) {
            addCriterion("overdue_fine_fee >", value, "overdueFineFee");
            return (Criteria) this;
        }

        public Criteria andOverdueFineFeeGreaterThanOrEqualTo(Double value) {
            addCriterion("overdue_fine_fee >=", value, "overdueFineFee");
            return (Criteria) this;
        }

        public Criteria andOverdueFineFeeLessThan(Double value) {
            addCriterion("overdue_fine_fee <", value, "overdueFineFee");
            return (Criteria) this;
        }

        public Criteria andOverdueFineFeeLessThanOrEqualTo(Double value) {
            addCriterion("overdue_fine_fee <=", value, "overdueFineFee");
            return (Criteria) this;
        }

        public Criteria andOverdueFineFeeIn(List<Double> values) {
            addCriterion("overdue_fine_fee in", values, "overdueFineFee");
            return (Criteria) this;
        }

        public Criteria andOverdueFineFeeNotIn(List<Double> values) {
            addCriterion("overdue_fine_fee not in", values, "overdueFineFee");
            return (Criteria) this;
        }

        public Criteria andOverdueFineFeeBetween(Double value1, Double value2) {
            addCriterion("overdue_fine_fee between", value1, value2, "overdueFineFee");
            return (Criteria) this;
        }

        public Criteria andOverdueFineFeeNotBetween(Double value1, Double value2) {
            addCriterion("overdue_fine_fee not between", value1, value2, "overdueFineFee");
            return (Criteria) this;
        }

        public Criteria andOverdueFreeIsNull() {
            addCriterion("overdue_free is null");
            return (Criteria) this;
        }

        public Criteria andOverdueFreeIsNotNull() {
            addCriterion("overdue_free is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueFreeEqualTo(Double value) {
            addCriterion("overdue_free =", value, "overdueFree");
            return (Criteria) this;
        }

        public Criteria andOverdueFreeNotEqualTo(Double value) {
            addCriterion("overdue_free <>", value, "overdueFree");
            return (Criteria) this;
        }

        public Criteria andOverdueFreeGreaterThan(Double value) {
            addCriterion("overdue_free >", value, "overdueFree");
            return (Criteria) this;
        }

        public Criteria andOverdueFreeGreaterThanOrEqualTo(Double value) {
            addCriterion("overdue_free >=", value, "overdueFree");
            return (Criteria) this;
        }

        public Criteria andOverdueFreeLessThan(Double value) {
            addCriterion("overdue_free <", value, "overdueFree");
            return (Criteria) this;
        }

        public Criteria andOverdueFreeLessThanOrEqualTo(Double value) {
            addCriterion("overdue_free <=", value, "overdueFree");
            return (Criteria) this;
        }

        public Criteria andOverdueFreeIn(List<Double> values) {
            addCriterion("overdue_free in", values, "overdueFree");
            return (Criteria) this;
        }

        public Criteria andOverdueFreeNotIn(List<Double> values) {
            addCriterion("overdue_free not in", values, "overdueFree");
            return (Criteria) this;
        }

        public Criteria andOverdueFreeBetween(Double value1, Double value2) {
            addCriterion("overdue_free between", value1, value2, "overdueFree");
            return (Criteria) this;
        }

        public Criteria andOverdueFreeNotBetween(Double value1, Double value2) {
            addCriterion("overdue_free not between", value1, value2, "overdueFree");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestFreeIsNull() {
            addCriterion("overdue_interest_free is null");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestFreeIsNotNull() {
            addCriterion("overdue_interest_free is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestFreeEqualTo(Double value) {
            addCriterion("overdue_interest_free =", value, "overdueInterestFree");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestFreeNotEqualTo(Double value) {
            addCriterion("overdue_interest_free <>", value, "overdueInterestFree");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestFreeGreaterThan(Double value) {
            addCriterion("overdue_interest_free >", value, "overdueInterestFree");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestFreeGreaterThanOrEqualTo(Double value) {
            addCriterion("overdue_interest_free >=", value, "overdueInterestFree");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestFreeLessThan(Double value) {
            addCriterion("overdue_interest_free <", value, "overdueInterestFree");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestFreeLessThanOrEqualTo(Double value) {
            addCriterion("overdue_interest_free <=", value, "overdueInterestFree");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestFreeIn(List<Double> values) {
            addCriterion("overdue_interest_free in", values, "overdueInterestFree");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestFreeNotIn(List<Double> values) {
            addCriterion("overdue_interest_free not in", values, "overdueInterestFree");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestFreeBetween(Double value1, Double value2) {
            addCriterion("overdue_interest_free between", value1, value2, "overdueInterestFree");
            return (Criteria) this;
        }

        public Criteria andOverdueInterestFreeNotBetween(Double value1, Double value2) {
            addCriterion("overdue_interest_free not between", value1, value2, "overdueInterestFree");
            return (Criteria) this;
        }

        public Criteria andPushStatusIsNull() {
            addCriterion("push_status is null");
            return (Criteria) this;
        }

        public Criteria andPushStatusIsNotNull() {
            addCriterion("push_status is not null");
            return (Criteria) this;
        }

        public Criteria andPushStatusEqualTo(Byte value) {
            addCriterion("push_status =", value, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusNotEqualTo(Byte value) {
            addCriterion("push_status <>", value, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusGreaterThan(Byte value) {
            addCriterion("push_status >", value, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("push_status >=", value, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusLessThan(Byte value) {
            addCriterion("push_status <", value, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusLessThanOrEqualTo(Byte value) {
            addCriterion("push_status <=", value, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusIn(List<Byte> values) {
            addCriterion("push_status in", values, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusNotIn(List<Byte> values) {
            addCriterion("push_status not in", values, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusBetween(Byte value1, Byte value2) {
            addCriterion("push_status between", value1, value2, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("push_status not between", value1, value2, "pushStatus");
            return (Criteria) this;
        }

        public Criteria andPushTimeIsNull() {
            addCriterion("push_time is null");
            return (Criteria) this;
        }

        public Criteria andPushTimeIsNotNull() {
            addCriterion("push_time is not null");
            return (Criteria) this;
        }

        public Criteria andPushTimeEqualTo(Date value) {
            addCriterion("push_time =", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeNotEqualTo(Date value) {
            addCriterion("push_time <>", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeGreaterThan(Date value) {
            addCriterion("push_time >", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("push_time >=", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeLessThan(Date value) {
            addCriterion("push_time <", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeLessThanOrEqualTo(Date value) {
            addCriterion("push_time <=", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeIn(List<Date> values) {
            addCriterion("push_time in", values, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeNotIn(List<Date> values) {
            addCriterion("push_time not in", values, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeBetween(Date value1, Date value2) {
            addCriterion("push_time between", value1, value2, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeNotBetween(Date value1, Date value2) {
            addCriterion("push_time not between", value1, value2, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushErrorMessageIsNull() {
            addCriterion("push_error_message is null");
            return (Criteria) this;
        }

        public Criteria andPushErrorMessageIsNotNull() {
            addCriterion("push_error_message is not null");
            return (Criteria) this;
        }

        public Criteria andPushErrorMessageEqualTo(String value) {
            addCriterion("push_error_message =", value, "pushErrorMessage");
            return (Criteria) this;
        }

        public Criteria andPushErrorMessageNotEqualTo(String value) {
            addCriterion("push_error_message <>", value, "pushErrorMessage");
            return (Criteria) this;
        }

        public Criteria andPushErrorMessageGreaterThan(String value) {
            addCriterion("push_error_message >", value, "pushErrorMessage");
            return (Criteria) this;
        }

        public Criteria andPushErrorMessageGreaterThanOrEqualTo(String value) {
            addCriterion("push_error_message >=", value, "pushErrorMessage");
            return (Criteria) this;
        }

        public Criteria andPushErrorMessageLessThan(String value) {
            addCriterion("push_error_message <", value, "pushErrorMessage");
            return (Criteria) this;
        }

        public Criteria andPushErrorMessageLessThanOrEqualTo(String value) {
            addCriterion("push_error_message <=", value, "pushErrorMessage");
            return (Criteria) this;
        }

        public Criteria andPushErrorMessageLike(String value) {
            addCriterion("push_error_message like", value, "pushErrorMessage");
            return (Criteria) this;
        }

        public Criteria andPushErrorMessageNotLike(String value) {
            addCriterion("push_error_message not like", value, "pushErrorMessage");
            return (Criteria) this;
        }

        public Criteria andPushErrorMessageIn(List<String> values) {
            addCriterion("push_error_message in", values, "pushErrorMessage");
            return (Criteria) this;
        }

        public Criteria andPushErrorMessageNotIn(List<String> values) {
            addCriterion("push_error_message not in", values, "pushErrorMessage");
            return (Criteria) this;
        }

        public Criteria andPushErrorMessageBetween(String value1, String value2) {
            addCriterion("push_error_message between", value1, value2, "pushErrorMessage");
            return (Criteria) this;
        }

        public Criteria andPushErrorMessageNotBetween(String value1, String value2) {
            addCriterion("push_error_message not between", value1, value2, "pushErrorMessage");
            return (Criteria) this;
        }

        public Criteria andTitleLikeInsensitive(String value) {
            addCriterion("upper(title) like", value.toUpperCase(), "title");
            return (Criteria) this;
        }

        public Criteria andMerBillNoLikeInsensitive(String value) {
            addCriterion("upper(mer_bill_no) like", value.toUpperCase(), "merBillNo");
            return (Criteria) this;
        }

        public Criteria andRepaymentBillNoLikeInsensitive(String value) {
            addCriterion("upper(repayment_bill_no) like", value.toUpperCase(), "repaymentBillNo");
            return (Criteria) this;
        }

        public Criteria andPushErrorMessageLikeInsensitive(String value) {
            addCriterion("upper(push_error_message) like", value.toUpperCase(), "pushErrorMessage");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private BillExample example;

        protected Criteria(BillExample example) {
            super();
            this.example = example;
        }

        public BillExample example() {
            return this.example;
        }

        public Criteria andIf(boolean ifAdd, ICriteriaAdd add) {
            if (ifAdd) {
                add.add(this);
            }
            return this;
        }

        public interface ICriteriaAdd {
            Criteria add(Criteria add);
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}