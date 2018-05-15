package com.demo.hzed.persistence.auto.entity.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BidExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer offset;

    protected Integer rows;

    public BidExample() {
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

    public BidExample limit(Integer rows) {
        this.rows = rows;
        return this;
    }

    public BidExample limit(Integer offset, Integer rows) {
        this.offset = offset;
        this.rows = rows;
        return this;
    }

    public BidExample page(Integer page, Integer pageSize) {
        this.offset = page * pageSize;
        this.rows = pageSize;
        return this;
    }

    public BidExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public BidExample orderBy(String ... orderByClauses) {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Date value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Date value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Date value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Date value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Date value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Date> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Date> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Date value1, Date value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Date value1, Date value2) {
            addCriterion("time not between", value1, value2, "time");
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

        public Criteria andBidNoIsNull() {
            addCriterion("bid_no is null");
            return (Criteria) this;
        }

        public Criteria andBidNoIsNotNull() {
            addCriterion("bid_no is not null");
            return (Criteria) this;
        }

        public Criteria andBidNoEqualTo(String value) {
            addCriterion("bid_no =", value, "bidNo");
            return (Criteria) this;
        }

        public Criteria andBidNoNotEqualTo(String value) {
            addCriterion("bid_no <>", value, "bidNo");
            return (Criteria) this;
        }

        public Criteria andBidNoGreaterThan(String value) {
            addCriterion("bid_no >", value, "bidNo");
            return (Criteria) this;
        }

        public Criteria andBidNoGreaterThanOrEqualTo(String value) {
            addCriterion("bid_no >=", value, "bidNo");
            return (Criteria) this;
        }

        public Criteria andBidNoLessThan(String value) {
            addCriterion("bid_no <", value, "bidNo");
            return (Criteria) this;
        }

        public Criteria andBidNoLessThanOrEqualTo(String value) {
            addCriterion("bid_no <=", value, "bidNo");
            return (Criteria) this;
        }

        public Criteria andBidNoLike(String value) {
            addCriterion("bid_no like", value, "bidNo");
            return (Criteria) this;
        }

        public Criteria andBidNoNotLike(String value) {
            addCriterion("bid_no not like", value, "bidNo");
            return (Criteria) this;
        }

        public Criteria andBidNoIn(List<String> values) {
            addCriterion("bid_no in", values, "bidNo");
            return (Criteria) this;
        }

        public Criteria andBidNoNotIn(List<String> values) {
            addCriterion("bid_no not in", values, "bidNo");
            return (Criteria) this;
        }

        public Criteria andBidNoBetween(String value1, String value2) {
            addCriterion("bid_no between", value1, value2, "bidNo");
            return (Criteria) this;
        }

        public Criteria andBidNoNotBetween(String value1, String value2) {
            addCriterion("bid_no not between", value1, value2, "bidNo");
            return (Criteria) this;
        }

        public Criteria andIpsBillNoIsNull() {
            addCriterion("ips_bill_no is null");
            return (Criteria) this;
        }

        public Criteria andIpsBillNoIsNotNull() {
            addCriterion("ips_bill_no is not null");
            return (Criteria) this;
        }

        public Criteria andIpsBillNoEqualTo(String value) {
            addCriterion("ips_bill_no =", value, "ipsBillNo");
            return (Criteria) this;
        }

        public Criteria andIpsBillNoNotEqualTo(String value) {
            addCriterion("ips_bill_no <>", value, "ipsBillNo");
            return (Criteria) this;
        }

        public Criteria andIpsBillNoGreaterThan(String value) {
            addCriterion("ips_bill_no >", value, "ipsBillNo");
            return (Criteria) this;
        }

        public Criteria andIpsBillNoGreaterThanOrEqualTo(String value) {
            addCriterion("ips_bill_no >=", value, "ipsBillNo");
            return (Criteria) this;
        }

        public Criteria andIpsBillNoLessThan(String value) {
            addCriterion("ips_bill_no <", value, "ipsBillNo");
            return (Criteria) this;
        }

        public Criteria andIpsBillNoLessThanOrEqualTo(String value) {
            addCriterion("ips_bill_no <=", value, "ipsBillNo");
            return (Criteria) this;
        }

        public Criteria andIpsBillNoLike(String value) {
            addCriterion("ips_bill_no like", value, "ipsBillNo");
            return (Criteria) this;
        }

        public Criteria andIpsBillNoNotLike(String value) {
            addCriterion("ips_bill_no not like", value, "ipsBillNo");
            return (Criteria) this;
        }

        public Criteria andIpsBillNoIn(List<String> values) {
            addCriterion("ips_bill_no in", values, "ipsBillNo");
            return (Criteria) this;
        }

        public Criteria andIpsBillNoNotIn(List<String> values) {
            addCriterion("ips_bill_no not in", values, "ipsBillNo");
            return (Criteria) this;
        }

        public Criteria andIpsBillNoBetween(String value1, String value2) {
            addCriterion("ips_bill_no between", value1, value2, "ipsBillNo");
            return (Criteria) this;
        }

        public Criteria andIpsBillNoNotBetween(String value1, String value2) {
            addCriterion("ips_bill_no not between", value1, value2, "ipsBillNo");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Integer value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Integer value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Integer value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Integer value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Integer> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Integer> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Integer value1, Integer value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_id not between", value1, value2, "productId");
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

        public Criteria andLoanPurposeIdIsNull() {
            addCriterion("loan_purpose_id is null");
            return (Criteria) this;
        }

        public Criteria andLoanPurposeIdIsNotNull() {
            addCriterion("loan_purpose_id is not null");
            return (Criteria) this;
        }

        public Criteria andLoanPurposeIdEqualTo(Integer value) {
            addCriterion("loan_purpose_id =", value, "loanPurposeId");
            return (Criteria) this;
        }

        public Criteria andLoanPurposeIdNotEqualTo(Integer value) {
            addCriterion("loan_purpose_id <>", value, "loanPurposeId");
            return (Criteria) this;
        }

        public Criteria andLoanPurposeIdGreaterThan(Integer value) {
            addCriterion("loan_purpose_id >", value, "loanPurposeId");
            return (Criteria) this;
        }

        public Criteria andLoanPurposeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("loan_purpose_id >=", value, "loanPurposeId");
            return (Criteria) this;
        }

        public Criteria andLoanPurposeIdLessThan(Integer value) {
            addCriterion("loan_purpose_id <", value, "loanPurposeId");
            return (Criteria) this;
        }

        public Criteria andLoanPurposeIdLessThanOrEqualTo(Integer value) {
            addCriterion("loan_purpose_id <=", value, "loanPurposeId");
            return (Criteria) this;
        }

        public Criteria andLoanPurposeIdIn(List<Integer> values) {
            addCriterion("loan_purpose_id in", values, "loanPurposeId");
            return (Criteria) this;
        }

        public Criteria andLoanPurposeIdNotIn(List<Integer> values) {
            addCriterion("loan_purpose_id not in", values, "loanPurposeId");
            return (Criteria) this;
        }

        public Criteria andLoanPurposeIdBetween(Integer value1, Integer value2) {
            addCriterion("loan_purpose_id between", value1, value2, "loanPurposeId");
            return (Criteria) this;
        }

        public Criteria andLoanPurposeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("loan_purpose_id not between", value1, value2, "loanPurposeId");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeIdIsNull() {
            addCriterion("repayment_type_id is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeIdIsNotNull() {
            addCriterion("repayment_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeIdEqualTo(Byte value) {
            addCriterion("repayment_type_id =", value, "repaymentTypeId");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeIdNotEqualTo(Byte value) {
            addCriterion("repayment_type_id <>", value, "repaymentTypeId");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeIdGreaterThan(Byte value) {
            addCriterion("repayment_type_id >", value, "repaymentTypeId");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeIdGreaterThanOrEqualTo(Byte value) {
            addCriterion("repayment_type_id >=", value, "repaymentTypeId");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeIdLessThan(Byte value) {
            addCriterion("repayment_type_id <", value, "repaymentTypeId");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeIdLessThanOrEqualTo(Byte value) {
            addCriterion("repayment_type_id <=", value, "repaymentTypeId");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeIdIn(List<Byte> values) {
            addCriterion("repayment_type_id in", values, "repaymentTypeId");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeIdNotIn(List<Byte> values) {
            addCriterion("repayment_type_id not in", values, "repaymentTypeId");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeIdBetween(Byte value1, Byte value2) {
            addCriterion("repayment_type_id between", value1, value2, "repaymentTypeId");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeIdNotBetween(Byte value1, Byte value2) {
            addCriterion("repayment_type_id not between", value1, value2, "repaymentTypeId");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(BigDecimal value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(BigDecimal value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(BigDecimal value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(BigDecimal value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<BigDecimal> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<BigDecimal> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andPeriodUnitIsNull() {
            addCriterion("period_unit is null");
            return (Criteria) this;
        }

        public Criteria andPeriodUnitIsNotNull() {
            addCriterion("period_unit is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodUnitEqualTo(Integer value) {
            addCriterion("period_unit =", value, "periodUnit");
            return (Criteria) this;
        }

        public Criteria andPeriodUnitNotEqualTo(Integer value) {
            addCriterion("period_unit <>", value, "periodUnit");
            return (Criteria) this;
        }

        public Criteria andPeriodUnitGreaterThan(Integer value) {
            addCriterion("period_unit >", value, "periodUnit");
            return (Criteria) this;
        }

        public Criteria andPeriodUnitGreaterThanOrEqualTo(Integer value) {
            addCriterion("period_unit >=", value, "periodUnit");
            return (Criteria) this;
        }

        public Criteria andPeriodUnitLessThan(Integer value) {
            addCriterion("period_unit <", value, "periodUnit");
            return (Criteria) this;
        }

        public Criteria andPeriodUnitLessThanOrEqualTo(Integer value) {
            addCriterion("period_unit <=", value, "periodUnit");
            return (Criteria) this;
        }

        public Criteria andPeriodUnitIn(List<Integer> values) {
            addCriterion("period_unit in", values, "periodUnit");
            return (Criteria) this;
        }

        public Criteria andPeriodUnitNotIn(List<Integer> values) {
            addCriterion("period_unit not in", values, "periodUnit");
            return (Criteria) this;
        }

        public Criteria andPeriodUnitBetween(Integer value1, Integer value2) {
            addCriterion("period_unit between", value1, value2, "periodUnit");
            return (Criteria) this;
        }

        public Criteria andPeriodUnitNotBetween(Integer value1, Integer value2) {
            addCriterion("period_unit not between", value1, value2, "periodUnit");
            return (Criteria) this;
        }

        public Criteria andPeriodIsNull() {
            addCriterion("period is null");
            return (Criteria) this;
        }

        public Criteria andPeriodIsNotNull() {
            addCriterion("period is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodEqualTo(Integer value) {
            addCriterion("period =", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotEqualTo(Integer value) {
            addCriterion("period <>", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThan(Integer value) {
            addCriterion("period >", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("period >=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThan(Integer value) {
            addCriterion("period <", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("period <=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodIn(List<Integer> values) {
            addCriterion("period in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotIn(List<Integer> values) {
            addCriterion("period not in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodBetween(Integer value1, Integer value2) {
            addCriterion("period between", value1, value2, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("period not between", value1, value2, "period");
            return (Criteria) this;
        }

        public Criteria andMinInvestAmountIsNull() {
            addCriterion("min_invest_amount is null");
            return (Criteria) this;
        }

        public Criteria andMinInvestAmountIsNotNull() {
            addCriterion("min_invest_amount is not null");
            return (Criteria) this;
        }

        public Criteria andMinInvestAmountEqualTo(BigDecimal value) {
            addCriterion("min_invest_amount =", value, "minInvestAmount");
            return (Criteria) this;
        }

        public Criteria andMinInvestAmountNotEqualTo(BigDecimal value) {
            addCriterion("min_invest_amount <>", value, "minInvestAmount");
            return (Criteria) this;
        }

        public Criteria andMinInvestAmountGreaterThan(BigDecimal value) {
            addCriterion("min_invest_amount >", value, "minInvestAmount");
            return (Criteria) this;
        }

        public Criteria andMinInvestAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("min_invest_amount >=", value, "minInvestAmount");
            return (Criteria) this;
        }

        public Criteria andMinInvestAmountLessThan(BigDecimal value) {
            addCriterion("min_invest_amount <", value, "minInvestAmount");
            return (Criteria) this;
        }

        public Criteria andMinInvestAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("min_invest_amount <=", value, "minInvestAmount");
            return (Criteria) this;
        }

        public Criteria andMinInvestAmountIn(List<BigDecimal> values) {
            addCriterion("min_invest_amount in", values, "minInvestAmount");
            return (Criteria) this;
        }

        public Criteria andMinInvestAmountNotIn(List<BigDecimal> values) {
            addCriterion("min_invest_amount not in", values, "minInvestAmount");
            return (Criteria) this;
        }

        public Criteria andMinInvestAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_invest_amount between", value1, value2, "minInvestAmount");
            return (Criteria) this;
        }

        public Criteria andMinInvestAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_invest_amount not between", value1, value2, "minInvestAmount");
            return (Criteria) this;
        }

        public Criteria andAverageInvestAmountIsNull() {
            addCriterion("average_invest_amount is null");
            return (Criteria) this;
        }

        public Criteria andAverageInvestAmountIsNotNull() {
            addCriterion("average_invest_amount is not null");
            return (Criteria) this;
        }

        public Criteria andAverageInvestAmountEqualTo(BigDecimal value) {
            addCriterion("average_invest_amount =", value, "averageInvestAmount");
            return (Criteria) this;
        }

        public Criteria andAverageInvestAmountNotEqualTo(BigDecimal value) {
            addCriterion("average_invest_amount <>", value, "averageInvestAmount");
            return (Criteria) this;
        }

        public Criteria andAverageInvestAmountGreaterThan(BigDecimal value) {
            addCriterion("average_invest_amount >", value, "averageInvestAmount");
            return (Criteria) this;
        }

        public Criteria andAverageInvestAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("average_invest_amount >=", value, "averageInvestAmount");
            return (Criteria) this;
        }

        public Criteria andAverageInvestAmountLessThan(BigDecimal value) {
            addCriterion("average_invest_amount <", value, "averageInvestAmount");
            return (Criteria) this;
        }

        public Criteria andAverageInvestAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("average_invest_amount <=", value, "averageInvestAmount");
            return (Criteria) this;
        }

        public Criteria andAverageInvestAmountIn(List<BigDecimal> values) {
            addCriterion("average_invest_amount in", values, "averageInvestAmount");
            return (Criteria) this;
        }

        public Criteria andAverageInvestAmountNotIn(List<BigDecimal> values) {
            addCriterion("average_invest_amount not in", values, "averageInvestAmount");
            return (Criteria) this;
        }

        public Criteria andAverageInvestAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("average_invest_amount between", value1, value2, "averageInvestAmount");
            return (Criteria) this;
        }

        public Criteria andAverageInvestAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("average_invest_amount not between", value1, value2, "averageInvestAmount");
            return (Criteria) this;
        }

        public Criteria andInvestPeriodIsNull() {
            addCriterion("invest_period is null");
            return (Criteria) this;
        }

        public Criteria andInvestPeriodIsNotNull() {
            addCriterion("invest_period is not null");
            return (Criteria) this;
        }

        public Criteria andInvestPeriodEqualTo(Integer value) {
            addCriterion("invest_period =", value, "investPeriod");
            return (Criteria) this;
        }

        public Criteria andInvestPeriodNotEqualTo(Integer value) {
            addCriterion("invest_period <>", value, "investPeriod");
            return (Criteria) this;
        }

        public Criteria andInvestPeriodGreaterThan(Integer value) {
            addCriterion("invest_period >", value, "investPeriod");
            return (Criteria) this;
        }

        public Criteria andInvestPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("invest_period >=", value, "investPeriod");
            return (Criteria) this;
        }

        public Criteria andInvestPeriodLessThan(Integer value) {
            addCriterion("invest_period <", value, "investPeriod");
            return (Criteria) this;
        }

        public Criteria andInvestPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("invest_period <=", value, "investPeriod");
            return (Criteria) this;
        }

        public Criteria andInvestPeriodIn(List<Integer> values) {
            addCriterion("invest_period in", values, "investPeriod");
            return (Criteria) this;
        }

        public Criteria andInvestPeriodNotIn(List<Integer> values) {
            addCriterion("invest_period not in", values, "investPeriod");
            return (Criteria) this;
        }

        public Criteria andInvestPeriodBetween(Integer value1, Integer value2) {
            addCriterion("invest_period between", value1, value2, "investPeriod");
            return (Criteria) this;
        }

        public Criteria andInvestPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("invest_period not between", value1, value2, "investPeriod");
            return (Criteria) this;
        }

        public Criteria andInvestExpireTimeIsNull() {
            addCriterion("invest_expire_time is null");
            return (Criteria) this;
        }

        public Criteria andInvestExpireTimeIsNotNull() {
            addCriterion("invest_expire_time is not null");
            return (Criteria) this;
        }

        public Criteria andInvestExpireTimeEqualTo(Date value) {
            addCriterion("invest_expire_time =", value, "investExpireTime");
            return (Criteria) this;
        }

        public Criteria andInvestExpireTimeNotEqualTo(Date value) {
            addCriterion("invest_expire_time <>", value, "investExpireTime");
            return (Criteria) this;
        }

        public Criteria andInvestExpireTimeGreaterThan(Date value) {
            addCriterion("invest_expire_time >", value, "investExpireTime");
            return (Criteria) this;
        }

        public Criteria andInvestExpireTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("invest_expire_time >=", value, "investExpireTime");
            return (Criteria) this;
        }

        public Criteria andInvestExpireTimeLessThan(Date value) {
            addCriterion("invest_expire_time <", value, "investExpireTime");
            return (Criteria) this;
        }

        public Criteria andInvestExpireTimeLessThanOrEqualTo(Date value) {
            addCriterion("invest_expire_time <=", value, "investExpireTime");
            return (Criteria) this;
        }

        public Criteria andInvestExpireTimeIn(List<Date> values) {
            addCriterion("invest_expire_time in", values, "investExpireTime");
            return (Criteria) this;
        }

        public Criteria andInvestExpireTimeNotIn(List<Date> values) {
            addCriterion("invest_expire_time not in", values, "investExpireTime");
            return (Criteria) this;
        }

        public Criteria andInvestExpireTimeBetween(Date value1, Date value2) {
            addCriterion("invest_expire_time between", value1, value2, "investExpireTime");
            return (Criteria) this;
        }

        public Criteria andInvestExpireTimeNotBetween(Date value1, Date value2) {
            addCriterion("invest_expire_time not between", value1, value2, "investExpireTime");
            return (Criteria) this;
        }

        public Criteria andRealInvestExpireTimeIsNull() {
            addCriterion("real_invest_expire_time is null");
            return (Criteria) this;
        }

        public Criteria andRealInvestExpireTimeIsNotNull() {
            addCriterion("real_invest_expire_time is not null");
            return (Criteria) this;
        }

        public Criteria andRealInvestExpireTimeEqualTo(Date value) {
            addCriterion("real_invest_expire_time =", value, "realInvestExpireTime");
            return (Criteria) this;
        }

        public Criteria andRealInvestExpireTimeNotEqualTo(Date value) {
            addCriterion("real_invest_expire_time <>", value, "realInvestExpireTime");
            return (Criteria) this;
        }

        public Criteria andRealInvestExpireTimeGreaterThan(Date value) {
            addCriterion("real_invest_expire_time >", value, "realInvestExpireTime");
            return (Criteria) this;
        }

        public Criteria andRealInvestExpireTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("real_invest_expire_time >=", value, "realInvestExpireTime");
            return (Criteria) this;
        }

        public Criteria andRealInvestExpireTimeLessThan(Date value) {
            addCriterion("real_invest_expire_time <", value, "realInvestExpireTime");
            return (Criteria) this;
        }

        public Criteria andRealInvestExpireTimeLessThanOrEqualTo(Date value) {
            addCriterion("real_invest_expire_time <=", value, "realInvestExpireTime");
            return (Criteria) this;
        }

        public Criteria andRealInvestExpireTimeIn(List<Date> values) {
            addCriterion("real_invest_expire_time in", values, "realInvestExpireTime");
            return (Criteria) this;
        }

        public Criteria andRealInvestExpireTimeNotIn(List<Date> values) {
            addCriterion("real_invest_expire_time not in", values, "realInvestExpireTime");
            return (Criteria) this;
        }

        public Criteria andRealInvestExpireTimeBetween(Date value1, Date value2) {
            addCriterion("real_invest_expire_time between", value1, value2, "realInvestExpireTime");
            return (Criteria) this;
        }

        public Criteria andRealInvestExpireTimeNotBetween(Date value1, Date value2) {
            addCriterion("real_invest_expire_time not between", value1, value2, "realInvestExpireTime");
            return (Criteria) this;
        }

        public Criteria andAprIsNull() {
            addCriterion("apr is null");
            return (Criteria) this;
        }

        public Criteria andAprIsNotNull() {
            addCriterion("apr is not null");
            return (Criteria) this;
        }

        public Criteria andAprEqualTo(BigDecimal value) {
            addCriterion("apr =", value, "apr");
            return (Criteria) this;
        }

        public Criteria andAprNotEqualTo(BigDecimal value) {
            addCriterion("apr <>", value, "apr");
            return (Criteria) this;
        }

        public Criteria andAprGreaterThan(BigDecimal value) {
            addCriterion("apr >", value, "apr");
            return (Criteria) this;
        }

        public Criteria andAprGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("apr >=", value, "apr");
            return (Criteria) this;
        }

        public Criteria andAprLessThan(BigDecimal value) {
            addCriterion("apr <", value, "apr");
            return (Criteria) this;
        }

        public Criteria andAprLessThanOrEqualTo(BigDecimal value) {
            addCriterion("apr <=", value, "apr");
            return (Criteria) this;
        }

        public Criteria andAprIn(List<BigDecimal> values) {
            addCriterion("apr in", values, "apr");
            return (Criteria) this;
        }

        public Criteria andAprNotIn(List<BigDecimal> values) {
            addCriterion("apr not in", values, "apr");
            return (Criteria) this;
        }

        public Criteria andAprBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("apr between", value1, value2, "apr");
            return (Criteria) this;
        }

        public Criteria andAprNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("apr not between", value1, value2, "apr");
            return (Criteria) this;
        }

        public Criteria andBankAccountIdIsNull() {
            addCriterion("bank_account_id is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountIdIsNotNull() {
            addCriterion("bank_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountIdEqualTo(Long value) {
            addCriterion("bank_account_id =", value, "bankAccountId");
            return (Criteria) this;
        }

        public Criteria andBankAccountIdNotEqualTo(Long value) {
            addCriterion("bank_account_id <>", value, "bankAccountId");
            return (Criteria) this;
        }

        public Criteria andBankAccountIdGreaterThan(Long value) {
            addCriterion("bank_account_id >", value, "bankAccountId");
            return (Criteria) this;
        }

        public Criteria andBankAccountIdGreaterThanOrEqualTo(Long value) {
            addCriterion("bank_account_id >=", value, "bankAccountId");
            return (Criteria) this;
        }

        public Criteria andBankAccountIdLessThan(Long value) {
            addCriterion("bank_account_id <", value, "bankAccountId");
            return (Criteria) this;
        }

        public Criteria andBankAccountIdLessThanOrEqualTo(Long value) {
            addCriterion("bank_account_id <=", value, "bankAccountId");
            return (Criteria) this;
        }

        public Criteria andBankAccountIdIn(List<Long> values) {
            addCriterion("bank_account_id in", values, "bankAccountId");
            return (Criteria) this;
        }

        public Criteria andBankAccountIdNotIn(List<Long> values) {
            addCriterion("bank_account_id not in", values, "bankAccountId");
            return (Criteria) this;
        }

        public Criteria andBankAccountIdBetween(Long value1, Long value2) {
            addCriterion("bank_account_id between", value1, value2, "bankAccountId");
            return (Criteria) this;
        }

        public Criteria andBankAccountIdNotBetween(Long value1, Long value2) {
            addCriterion("bank_account_id not between", value1, value2, "bankAccountId");
            return (Criteria) this;
        }

        public Criteria andBonusTypeIsNull() {
            addCriterion("bonus_type is null");
            return (Criteria) this;
        }

        public Criteria andBonusTypeIsNotNull() {
            addCriterion("bonus_type is not null");
            return (Criteria) this;
        }

        public Criteria andBonusTypeEqualTo(Byte value) {
            addCriterion("bonus_type =", value, "bonusType");
            return (Criteria) this;
        }

        public Criteria andBonusTypeNotEqualTo(Byte value) {
            addCriterion("bonus_type <>", value, "bonusType");
            return (Criteria) this;
        }

        public Criteria andBonusTypeGreaterThan(Byte value) {
            addCriterion("bonus_type >", value, "bonusType");
            return (Criteria) this;
        }

        public Criteria andBonusTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("bonus_type >=", value, "bonusType");
            return (Criteria) this;
        }

        public Criteria andBonusTypeLessThan(Byte value) {
            addCriterion("bonus_type <", value, "bonusType");
            return (Criteria) this;
        }

        public Criteria andBonusTypeLessThanOrEqualTo(Byte value) {
            addCriterion("bonus_type <=", value, "bonusType");
            return (Criteria) this;
        }

        public Criteria andBonusTypeIn(List<Byte> values) {
            addCriterion("bonus_type in", values, "bonusType");
            return (Criteria) this;
        }

        public Criteria andBonusTypeNotIn(List<Byte> values) {
            addCriterion("bonus_type not in", values, "bonusType");
            return (Criteria) this;
        }

        public Criteria andBonusTypeBetween(Byte value1, Byte value2) {
            addCriterion("bonus_type between", value1, value2, "bonusType");
            return (Criteria) this;
        }

        public Criteria andBonusTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("bonus_type not between", value1, value2, "bonusType");
            return (Criteria) this;
        }

        public Criteria andBonusIsNull() {
            addCriterion("bonus is null");
            return (Criteria) this;
        }

        public Criteria andBonusIsNotNull() {
            addCriterion("bonus is not null");
            return (Criteria) this;
        }

        public Criteria andBonusEqualTo(BigDecimal value) {
            addCriterion("bonus =", value, "bonus");
            return (Criteria) this;
        }

        public Criteria andBonusNotEqualTo(BigDecimal value) {
            addCriterion("bonus <>", value, "bonus");
            return (Criteria) this;
        }

        public Criteria andBonusGreaterThan(BigDecimal value) {
            addCriterion("bonus >", value, "bonus");
            return (Criteria) this;
        }

        public Criteria andBonusGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("bonus >=", value, "bonus");
            return (Criteria) this;
        }

        public Criteria andBonusLessThan(BigDecimal value) {
            addCriterion("bonus <", value, "bonus");
            return (Criteria) this;
        }

        public Criteria andBonusLessThanOrEqualTo(BigDecimal value) {
            addCriterion("bonus <=", value, "bonus");
            return (Criteria) this;
        }

        public Criteria andBonusIn(List<BigDecimal> values) {
            addCriterion("bonus in", values, "bonus");
            return (Criteria) this;
        }

        public Criteria andBonusNotIn(List<BigDecimal> values) {
            addCriterion("bonus not in", values, "bonus");
            return (Criteria) this;
        }

        public Criteria andBonusBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bonus between", value1, value2, "bonus");
            return (Criteria) this;
        }

        public Criteria andBonusNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bonus not between", value1, value2, "bonus");
            return (Criteria) this;
        }

        public Criteria andAwardScaleIsNull() {
            addCriterion("award_scale is null");
            return (Criteria) this;
        }

        public Criteria andAwardScaleIsNotNull() {
            addCriterion("award_scale is not null");
            return (Criteria) this;
        }

        public Criteria andAwardScaleEqualTo(BigDecimal value) {
            addCriterion("award_scale =", value, "awardScale");
            return (Criteria) this;
        }

        public Criteria andAwardScaleNotEqualTo(BigDecimal value) {
            addCriterion("award_scale <>", value, "awardScale");
            return (Criteria) this;
        }

        public Criteria andAwardScaleGreaterThan(BigDecimal value) {
            addCriterion("award_scale >", value, "awardScale");
            return (Criteria) this;
        }

        public Criteria andAwardScaleGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("award_scale >=", value, "awardScale");
            return (Criteria) this;
        }

        public Criteria andAwardScaleLessThan(BigDecimal value) {
            addCriterion("award_scale <", value, "awardScale");
            return (Criteria) this;
        }

        public Criteria andAwardScaleLessThanOrEqualTo(BigDecimal value) {
            addCriterion("award_scale <=", value, "awardScale");
            return (Criteria) this;
        }

        public Criteria andAwardScaleIn(List<BigDecimal> values) {
            addCriterion("award_scale in", values, "awardScale");
            return (Criteria) this;
        }

        public Criteria andAwardScaleNotIn(List<BigDecimal> values) {
            addCriterion("award_scale not in", values, "awardScale");
            return (Criteria) this;
        }

        public Criteria andAwardScaleBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("award_scale between", value1, value2, "awardScale");
            return (Criteria) this;
        }

        public Criteria andAwardScaleNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("award_scale not between", value1, value2, "awardScale");
            return (Criteria) this;
        }

        public Criteria andImageFilenameIsNull() {
            addCriterion("image_filename is null");
            return (Criteria) this;
        }

        public Criteria andImageFilenameIsNotNull() {
            addCriterion("image_filename is not null");
            return (Criteria) this;
        }

        public Criteria andImageFilenameEqualTo(String value) {
            addCriterion("image_filename =", value, "imageFilename");
            return (Criteria) this;
        }

        public Criteria andImageFilenameNotEqualTo(String value) {
            addCriterion("image_filename <>", value, "imageFilename");
            return (Criteria) this;
        }

        public Criteria andImageFilenameGreaterThan(String value) {
            addCriterion("image_filename >", value, "imageFilename");
            return (Criteria) this;
        }

        public Criteria andImageFilenameGreaterThanOrEqualTo(String value) {
            addCriterion("image_filename >=", value, "imageFilename");
            return (Criteria) this;
        }

        public Criteria andImageFilenameLessThan(String value) {
            addCriterion("image_filename <", value, "imageFilename");
            return (Criteria) this;
        }

        public Criteria andImageFilenameLessThanOrEqualTo(String value) {
            addCriterion("image_filename <=", value, "imageFilename");
            return (Criteria) this;
        }

        public Criteria andImageFilenameLike(String value) {
            addCriterion("image_filename like", value, "imageFilename");
            return (Criteria) this;
        }

        public Criteria andImageFilenameNotLike(String value) {
            addCriterion("image_filename not like", value, "imageFilename");
            return (Criteria) this;
        }

        public Criteria andImageFilenameIn(List<String> values) {
            addCriterion("image_filename in", values, "imageFilename");
            return (Criteria) this;
        }

        public Criteria andImageFilenameNotIn(List<String> values) {
            addCriterion("image_filename not in", values, "imageFilename");
            return (Criteria) this;
        }

        public Criteria andImageFilenameBetween(String value1, String value2) {
            addCriterion("image_filename between", value1, value2, "imageFilename");
            return (Criteria) this;
        }

        public Criteria andImageFilenameNotBetween(String value1, String value2) {
            addCriterion("image_filename not between", value1, value2, "imageFilename");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andItemIsNull() {
            addCriterion("item is null");
            return (Criteria) this;
        }

        public Criteria andItemIsNotNull() {
            addCriterion("item is not null");
            return (Criteria) this;
        }

        public Criteria andItemEqualTo(String value) {
            addCriterion("item =", value, "item");
            return (Criteria) this;
        }

        public Criteria andItemNotEqualTo(String value) {
            addCriterion("item <>", value, "item");
            return (Criteria) this;
        }

        public Criteria andItemGreaterThan(String value) {
            addCriterion("item >", value, "item");
            return (Criteria) this;
        }

        public Criteria andItemGreaterThanOrEqualTo(String value) {
            addCriterion("item >=", value, "item");
            return (Criteria) this;
        }

        public Criteria andItemLessThan(String value) {
            addCriterion("item <", value, "item");
            return (Criteria) this;
        }

        public Criteria andItemLessThanOrEqualTo(String value) {
            addCriterion("item <=", value, "item");
            return (Criteria) this;
        }

        public Criteria andItemLike(String value) {
            addCriterion("item like", value, "item");
            return (Criteria) this;
        }

        public Criteria andItemNotLike(String value) {
            addCriterion("item not like", value, "item");
            return (Criteria) this;
        }

        public Criteria andItemIn(List<String> values) {
            addCriterion("item in", values, "item");
            return (Criteria) this;
        }

        public Criteria andItemNotIn(List<String> values) {
            addCriterion("item not in", values, "item");
            return (Criteria) this;
        }

        public Criteria andItemBetween(String value1, String value2) {
            addCriterion("item between", value1, value2, "item");
            return (Criteria) this;
        }

        public Criteria andItemNotBetween(String value1, String value2) {
            addCriterion("item not between", value1, value2, "item");
            return (Criteria) this;
        }

        public Criteria andIsSecBidIsNull() {
            addCriterion("is_sec_bid is null");
            return (Criteria) this;
        }

        public Criteria andIsSecBidIsNotNull() {
            addCriterion("is_sec_bid is not null");
            return (Criteria) this;
        }

        public Criteria andIsSecBidEqualTo(Boolean value) {
            addCriterion("is_sec_bid =", value, "isSecBid");
            return (Criteria) this;
        }

        public Criteria andIsSecBidNotEqualTo(Boolean value) {
            addCriterion("is_sec_bid <>", value, "isSecBid");
            return (Criteria) this;
        }

        public Criteria andIsSecBidGreaterThan(Boolean value) {
            addCriterion("is_sec_bid >", value, "isSecBid");
            return (Criteria) this;
        }

        public Criteria andIsSecBidGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_sec_bid >=", value, "isSecBid");
            return (Criteria) this;
        }

        public Criteria andIsSecBidLessThan(Boolean value) {
            addCriterion("is_sec_bid <", value, "isSecBid");
            return (Criteria) this;
        }

        public Criteria andIsSecBidLessThanOrEqualTo(Boolean value) {
            addCriterion("is_sec_bid <=", value, "isSecBid");
            return (Criteria) this;
        }

        public Criteria andIsSecBidIn(List<Boolean> values) {
            addCriterion("is_sec_bid in", values, "isSecBid");
            return (Criteria) this;
        }

        public Criteria andIsSecBidNotIn(List<Boolean> values) {
            addCriterion("is_sec_bid not in", values, "isSecBid");
            return (Criteria) this;
        }

        public Criteria andIsSecBidBetween(Boolean value1, Boolean value2) {
            addCriterion("is_sec_bid between", value1, value2, "isSecBid");
            return (Criteria) this;
        }

        public Criteria andIsSecBidNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_sec_bid not between", value1, value2, "isSecBid");
            return (Criteria) this;
        }

        public Criteria andIsDealPasswordIsNull() {
            addCriterion("is_deal_password is null");
            return (Criteria) this;
        }

        public Criteria andIsDealPasswordIsNotNull() {
            addCriterion("is_deal_password is not null");
            return (Criteria) this;
        }

        public Criteria andIsDealPasswordEqualTo(Boolean value) {
            addCriterion("is_deal_password =", value, "isDealPassword");
            return (Criteria) this;
        }

        public Criteria andIsDealPasswordNotEqualTo(Boolean value) {
            addCriterion("is_deal_password <>", value, "isDealPassword");
            return (Criteria) this;
        }

        public Criteria andIsDealPasswordGreaterThan(Boolean value) {
            addCriterion("is_deal_password >", value, "isDealPassword");
            return (Criteria) this;
        }

        public Criteria andIsDealPasswordGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_deal_password >=", value, "isDealPassword");
            return (Criteria) this;
        }

        public Criteria andIsDealPasswordLessThan(Boolean value) {
            addCriterion("is_deal_password <", value, "isDealPassword");
            return (Criteria) this;
        }

        public Criteria andIsDealPasswordLessThanOrEqualTo(Boolean value) {
            addCriterion("is_deal_password <=", value, "isDealPassword");
            return (Criteria) this;
        }

        public Criteria andIsDealPasswordIn(List<Boolean> values) {
            addCriterion("is_deal_password in", values, "isDealPassword");
            return (Criteria) this;
        }

        public Criteria andIsDealPasswordNotIn(List<Boolean> values) {
            addCriterion("is_deal_password not in", values, "isDealPassword");
            return (Criteria) this;
        }

        public Criteria andIsDealPasswordBetween(Boolean value1, Boolean value2) {
            addCriterion("is_deal_password between", value1, value2, "isDealPassword");
            return (Criteria) this;
        }

        public Criteria andIsDealPasswordNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_deal_password not between", value1, value2, "isDealPassword");
            return (Criteria) this;
        }

        public Criteria andShowTypeIsNull() {
            addCriterion("show_type is null");
            return (Criteria) this;
        }

        public Criteria andShowTypeIsNotNull() {
            addCriterion("show_type is not null");
            return (Criteria) this;
        }

        public Criteria andShowTypeEqualTo(Integer value) {
            addCriterion("show_type =", value, "showType");
            return (Criteria) this;
        }

        public Criteria andShowTypeNotEqualTo(Integer value) {
            addCriterion("show_type <>", value, "showType");
            return (Criteria) this;
        }

        public Criteria andShowTypeGreaterThan(Integer value) {
            addCriterion("show_type >", value, "showType");
            return (Criteria) this;
        }

        public Criteria andShowTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("show_type >=", value, "showType");
            return (Criteria) this;
        }

        public Criteria andShowTypeLessThan(Integer value) {
            addCriterion("show_type <", value, "showType");
            return (Criteria) this;
        }

        public Criteria andShowTypeLessThanOrEqualTo(Integer value) {
            addCriterion("show_type <=", value, "showType");
            return (Criteria) this;
        }

        public Criteria andShowTypeIn(List<Integer> values) {
            addCriterion("show_type in", values, "showType");
            return (Criteria) this;
        }

        public Criteria andShowTypeNotIn(List<Integer> values) {
            addCriterion("show_type not in", values, "showType");
            return (Criteria) this;
        }

        public Criteria andShowTypeBetween(Integer value1, Integer value2) {
            addCriterion("show_type between", value1, value2, "showType");
            return (Criteria) this;
        }

        public Criteria andShowTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("show_type not between", value1, value2, "showType");
            return (Criteria) this;
        }

        public Criteria andBailIsNull() {
            addCriterion("bail is null");
            return (Criteria) this;
        }

        public Criteria andBailIsNotNull() {
            addCriterion("bail is not null");
            return (Criteria) this;
        }

        public Criteria andBailEqualTo(BigDecimal value) {
            addCriterion("bail =", value, "bail");
            return (Criteria) this;
        }

        public Criteria andBailNotEqualTo(BigDecimal value) {
            addCriterion("bail <>", value, "bail");
            return (Criteria) this;
        }

        public Criteria andBailGreaterThan(BigDecimal value) {
            addCriterion("bail >", value, "bail");
            return (Criteria) this;
        }

        public Criteria andBailGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("bail >=", value, "bail");
            return (Criteria) this;
        }

        public Criteria andBailLessThan(BigDecimal value) {
            addCriterion("bail <", value, "bail");
            return (Criteria) this;
        }

        public Criteria andBailLessThanOrEqualTo(BigDecimal value) {
            addCriterion("bail <=", value, "bail");
            return (Criteria) this;
        }

        public Criteria andBailIn(List<BigDecimal> values) {
            addCriterion("bail in", values, "bail");
            return (Criteria) this;
        }

        public Criteria andBailNotIn(List<BigDecimal> values) {
            addCriterion("bail not in", values, "bail");
            return (Criteria) this;
        }

        public Criteria andBailBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bail between", value1, value2, "bail");
            return (Criteria) this;
        }

        public Criteria andBailNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("bail not between", value1, value2, "bail");
            return (Criteria) this;
        }

        public Criteria andServiceFeesIsNull() {
            addCriterion("service_fees is null");
            return (Criteria) this;
        }

        public Criteria andServiceFeesIsNotNull() {
            addCriterion("service_fees is not null");
            return (Criteria) this;
        }

        public Criteria andServiceFeesEqualTo(BigDecimal value) {
            addCriterion("service_fees =", value, "serviceFees");
            return (Criteria) this;
        }

        public Criteria andServiceFeesNotEqualTo(BigDecimal value) {
            addCriterion("service_fees <>", value, "serviceFees");
            return (Criteria) this;
        }

        public Criteria andServiceFeesGreaterThan(BigDecimal value) {
            addCriterion("service_fees >", value, "serviceFees");
            return (Criteria) this;
        }

        public Criteria andServiceFeesGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("service_fees >=", value, "serviceFees");
            return (Criteria) this;
        }

        public Criteria andServiceFeesLessThan(BigDecimal value) {
            addCriterion("service_fees <", value, "serviceFees");
            return (Criteria) this;
        }

        public Criteria andServiceFeesLessThanOrEqualTo(BigDecimal value) {
            addCriterion("service_fees <=", value, "serviceFees");
            return (Criteria) this;
        }

        public Criteria andServiceFeesIn(List<BigDecimal> values) {
            addCriterion("service_fees in", values, "serviceFees");
            return (Criteria) this;
        }

        public Criteria andServiceFeesNotIn(List<BigDecimal> values) {
            addCriterion("service_fees not in", values, "serviceFees");
            return (Criteria) this;
        }

        public Criteria andServiceFeesBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("service_fees between", value1, value2, "serviceFees");
            return (Criteria) this;
        }

        public Criteria andServiceFeesNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("service_fees not between", value1, value2, "serviceFees");
            return (Criteria) this;
        }

        public Criteria andIsQualityIsNull() {
            addCriterion("is_quality is null");
            return (Criteria) this;
        }

        public Criteria andIsQualityIsNotNull() {
            addCriterion("is_quality is not null");
            return (Criteria) this;
        }

        public Criteria andIsQualityEqualTo(Boolean value) {
            addCriterion("is_quality =", value, "isQuality");
            return (Criteria) this;
        }

        public Criteria andIsQualityNotEqualTo(Boolean value) {
            addCriterion("is_quality <>", value, "isQuality");
            return (Criteria) this;
        }

        public Criteria andIsQualityGreaterThan(Boolean value) {
            addCriterion("is_quality >", value, "isQuality");
            return (Criteria) this;
        }

        public Criteria andIsQualityGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_quality >=", value, "isQuality");
            return (Criteria) this;
        }

        public Criteria andIsQualityLessThan(Boolean value) {
            addCriterion("is_quality <", value, "isQuality");
            return (Criteria) this;
        }

        public Criteria andIsQualityLessThanOrEqualTo(Boolean value) {
            addCriterion("is_quality <=", value, "isQuality");
            return (Criteria) this;
        }

        public Criteria andIsQualityIn(List<Boolean> values) {
            addCriterion("is_quality in", values, "isQuality");
            return (Criteria) this;
        }

        public Criteria andIsQualityNotIn(List<Boolean> values) {
            addCriterion("is_quality not in", values, "isQuality");
            return (Criteria) this;
        }

        public Criteria andIsQualityBetween(Boolean value1, Boolean value2) {
            addCriterion("is_quality between", value1, value2, "isQuality");
            return (Criteria) this;
        }

        public Criteria andIsQualityNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_quality not between", value1, value2, "isQuality");
            return (Criteria) this;
        }

        public Criteria andIsHotIsNull() {
            addCriterion("is_hot is null");
            return (Criteria) this;
        }

        public Criteria andIsHotIsNotNull() {
            addCriterion("is_hot is not null");
            return (Criteria) this;
        }

        public Criteria andIsHotEqualTo(Boolean value) {
            addCriterion("is_hot =", value, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsHotNotEqualTo(Boolean value) {
            addCriterion("is_hot <>", value, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsHotGreaterThan(Boolean value) {
            addCriterion("is_hot >", value, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsHotGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_hot >=", value, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsHotLessThan(Boolean value) {
            addCriterion("is_hot <", value, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsHotLessThanOrEqualTo(Boolean value) {
            addCriterion("is_hot <=", value, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsHotIn(List<Boolean> values) {
            addCriterion("is_hot in", values, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsHotNotIn(List<Boolean> values) {
            addCriterion("is_hot not in", values, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsHotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_hot between", value1, value2, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsHotNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_hot not between", value1, value2, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsAgencyIsNull() {
            addCriterion("is_agency is null");
            return (Criteria) this;
        }

        public Criteria andIsAgencyIsNotNull() {
            addCriterion("is_agency is not null");
            return (Criteria) this;
        }

        public Criteria andIsAgencyEqualTo(Boolean value) {
            addCriterion("is_agency =", value, "isAgency");
            return (Criteria) this;
        }

        public Criteria andIsAgencyNotEqualTo(Boolean value) {
            addCriterion("is_agency <>", value, "isAgency");
            return (Criteria) this;
        }

        public Criteria andIsAgencyGreaterThan(Boolean value) {
            addCriterion("is_agency >", value, "isAgency");
            return (Criteria) this;
        }

        public Criteria andIsAgencyGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_agency >=", value, "isAgency");
            return (Criteria) this;
        }

        public Criteria andIsAgencyLessThan(Boolean value) {
            addCriterion("is_agency <", value, "isAgency");
            return (Criteria) this;
        }

        public Criteria andIsAgencyLessThanOrEqualTo(Boolean value) {
            addCriterion("is_agency <=", value, "isAgency");
            return (Criteria) this;
        }

        public Criteria andIsAgencyIn(List<Boolean> values) {
            addCriterion("is_agency in", values, "isAgency");
            return (Criteria) this;
        }

        public Criteria andIsAgencyNotIn(List<Boolean> values) {
            addCriterion("is_agency not in", values, "isAgency");
            return (Criteria) this;
        }

        public Criteria andIsAgencyBetween(Boolean value1, Boolean value2) {
            addCriterion("is_agency between", value1, value2, "isAgency");
            return (Criteria) this;
        }

        public Criteria andIsAgencyNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_agency not between", value1, value2, "isAgency");
            return (Criteria) this;
        }

        public Criteria andAgencyIdIsNull() {
            addCriterion("agency_id is null");
            return (Criteria) this;
        }

        public Criteria andAgencyIdIsNotNull() {
            addCriterion("agency_id is not null");
            return (Criteria) this;
        }

        public Criteria andAgencyIdEqualTo(Integer value) {
            addCriterion("agency_id =", value, "agencyId");
            return (Criteria) this;
        }

        public Criteria andAgencyIdNotEqualTo(Integer value) {
            addCriterion("agency_id <>", value, "agencyId");
            return (Criteria) this;
        }

        public Criteria andAgencyIdGreaterThan(Integer value) {
            addCriterion("agency_id >", value, "agencyId");
            return (Criteria) this;
        }

        public Criteria andAgencyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("agency_id >=", value, "agencyId");
            return (Criteria) this;
        }

        public Criteria andAgencyIdLessThan(Integer value) {
            addCriterion("agency_id <", value, "agencyId");
            return (Criteria) this;
        }

        public Criteria andAgencyIdLessThanOrEqualTo(Integer value) {
            addCriterion("agency_id <=", value, "agencyId");
            return (Criteria) this;
        }

        public Criteria andAgencyIdIn(List<Integer> values) {
            addCriterion("agency_id in", values, "agencyId");
            return (Criteria) this;
        }

        public Criteria andAgencyIdNotIn(List<Integer> values) {
            addCriterion("agency_id not in", values, "agencyId");
            return (Criteria) this;
        }

        public Criteria andAgencyIdBetween(Integer value1, Integer value2) {
            addCriterion("agency_id between", value1, value2, "agencyId");
            return (Criteria) this;
        }

        public Criteria andAgencyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("agency_id not between", value1, value2, "agencyId");
            return (Criteria) this;
        }

        public Criteria andIsShowAgencyNameIsNull() {
            addCriterion("is_show_agency_name is null");
            return (Criteria) this;
        }

        public Criteria andIsShowAgencyNameIsNotNull() {
            addCriterion("is_show_agency_name is not null");
            return (Criteria) this;
        }

        public Criteria andIsShowAgencyNameEqualTo(Boolean value) {
            addCriterion("is_show_agency_name =", value, "isShowAgencyName");
            return (Criteria) this;
        }

        public Criteria andIsShowAgencyNameNotEqualTo(Boolean value) {
            addCriterion("is_show_agency_name <>", value, "isShowAgencyName");
            return (Criteria) this;
        }

        public Criteria andIsShowAgencyNameGreaterThan(Boolean value) {
            addCriterion("is_show_agency_name >", value, "isShowAgencyName");
            return (Criteria) this;
        }

        public Criteria andIsShowAgencyNameGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_show_agency_name >=", value, "isShowAgencyName");
            return (Criteria) this;
        }

        public Criteria andIsShowAgencyNameLessThan(Boolean value) {
            addCriterion("is_show_agency_name <", value, "isShowAgencyName");
            return (Criteria) this;
        }

        public Criteria andIsShowAgencyNameLessThanOrEqualTo(Boolean value) {
            addCriterion("is_show_agency_name <=", value, "isShowAgencyName");
            return (Criteria) this;
        }

        public Criteria andIsShowAgencyNameIn(List<Boolean> values) {
            addCriterion("is_show_agency_name in", values, "isShowAgencyName");
            return (Criteria) this;
        }

        public Criteria andIsShowAgencyNameNotIn(List<Boolean> values) {
            addCriterion("is_show_agency_name not in", values, "isShowAgencyName");
            return (Criteria) this;
        }

        public Criteria andIsShowAgencyNameBetween(Boolean value1, Boolean value2) {
            addCriterion("is_show_agency_name between", value1, value2, "isShowAgencyName");
            return (Criteria) this;
        }

        public Criteria andIsShowAgencyNameNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_show_agency_name not between", value1, value2, "isShowAgencyName");
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

        public Criteria andLoanScheduleIsNull() {
            addCriterion("loan_schedule is null");
            return (Criteria) this;
        }

        public Criteria andLoanScheduleIsNotNull() {
            addCriterion("loan_schedule is not null");
            return (Criteria) this;
        }

        public Criteria andLoanScheduleEqualTo(BigDecimal value) {
            addCriterion("loan_schedule =", value, "loanSchedule");
            return (Criteria) this;
        }

        public Criteria andLoanScheduleNotEqualTo(BigDecimal value) {
            addCriterion("loan_schedule <>", value, "loanSchedule");
            return (Criteria) this;
        }

        public Criteria andLoanScheduleGreaterThan(BigDecimal value) {
            addCriterion("loan_schedule >", value, "loanSchedule");
            return (Criteria) this;
        }

        public Criteria andLoanScheduleGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("loan_schedule >=", value, "loanSchedule");
            return (Criteria) this;
        }

        public Criteria andLoanScheduleLessThan(BigDecimal value) {
            addCriterion("loan_schedule <", value, "loanSchedule");
            return (Criteria) this;
        }

        public Criteria andLoanScheduleLessThanOrEqualTo(BigDecimal value) {
            addCriterion("loan_schedule <=", value, "loanSchedule");
            return (Criteria) this;
        }

        public Criteria andLoanScheduleIn(List<BigDecimal> values) {
            addCriterion("loan_schedule in", values, "loanSchedule");
            return (Criteria) this;
        }

        public Criteria andLoanScheduleNotIn(List<BigDecimal> values) {
            addCriterion("loan_schedule not in", values, "loanSchedule");
            return (Criteria) this;
        }

        public Criteria andLoanScheduleBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("loan_schedule between", value1, value2, "loanSchedule");
            return (Criteria) this;
        }

        public Criteria andLoanScheduleNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("loan_schedule not between", value1, value2, "loanSchedule");
            return (Criteria) this;
        }

        public Criteria andHasInvestedAmountIsNull() {
            addCriterion("has_invested_amount is null");
            return (Criteria) this;
        }

        public Criteria andHasInvestedAmountIsNotNull() {
            addCriterion("has_invested_amount is not null");
            return (Criteria) this;
        }

        public Criteria andHasInvestedAmountEqualTo(BigDecimal value) {
            addCriterion("has_invested_amount =", value, "hasInvestedAmount");
            return (Criteria) this;
        }

        public Criteria andHasInvestedAmountNotEqualTo(BigDecimal value) {
            addCriterion("has_invested_amount <>", value, "hasInvestedAmount");
            return (Criteria) this;
        }

        public Criteria andHasInvestedAmountGreaterThan(BigDecimal value) {
            addCriterion("has_invested_amount >", value, "hasInvestedAmount");
            return (Criteria) this;
        }

        public Criteria andHasInvestedAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("has_invested_amount >=", value, "hasInvestedAmount");
            return (Criteria) this;
        }

        public Criteria andHasInvestedAmountLessThan(BigDecimal value) {
            addCriterion("has_invested_amount <", value, "hasInvestedAmount");
            return (Criteria) this;
        }

        public Criteria andHasInvestedAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("has_invested_amount <=", value, "hasInvestedAmount");
            return (Criteria) this;
        }

        public Criteria andHasInvestedAmountIn(List<BigDecimal> values) {
            addCriterion("has_invested_amount in", values, "hasInvestedAmount");
            return (Criteria) this;
        }

        public Criteria andHasInvestedAmountNotIn(List<BigDecimal> values) {
            addCriterion("has_invested_amount not in", values, "hasInvestedAmount");
            return (Criteria) this;
        }

        public Criteria andHasInvestedAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("has_invested_amount between", value1, value2, "hasInvestedAmount");
            return (Criteria) this;
        }

        public Criteria andHasInvestedAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("has_invested_amount not between", value1, value2, "hasInvestedAmount");
            return (Criteria) this;
        }

        public Criteria andReadCountIsNull() {
            addCriterion("read_count is null");
            return (Criteria) this;
        }

        public Criteria andReadCountIsNotNull() {
            addCriterion("read_count is not null");
            return (Criteria) this;
        }

        public Criteria andReadCountEqualTo(Integer value) {
            addCriterion("read_count =", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountNotEqualTo(Integer value) {
            addCriterion("read_count <>", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountGreaterThan(Integer value) {
            addCriterion("read_count >", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("read_count >=", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountLessThan(Integer value) {
            addCriterion("read_count <", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountLessThanOrEqualTo(Integer value) {
            addCriterion("read_count <=", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountIn(List<Integer> values) {
            addCriterion("read_count in", values, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountNotIn(List<Integer> values) {
            addCriterion("read_count not in", values, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountBetween(Integer value1, Integer value2) {
            addCriterion("read_count between", value1, value2, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountNotBetween(Integer value1, Integer value2) {
            addCriterion("read_count not between", value1, value2, "readCount");
            return (Criteria) this;
        }

        public Criteria andAllocationSupervisorIdIsNull() {
            addCriterion("allocation_supervisor_id is null");
            return (Criteria) this;
        }

        public Criteria andAllocationSupervisorIdIsNotNull() {
            addCriterion("allocation_supervisor_id is not null");
            return (Criteria) this;
        }

        public Criteria andAllocationSupervisorIdEqualTo(Long value) {
            addCriterion("allocation_supervisor_id =", value, "allocationSupervisorId");
            return (Criteria) this;
        }

        public Criteria andAllocationSupervisorIdNotEqualTo(Long value) {
            addCriterion("allocation_supervisor_id <>", value, "allocationSupervisorId");
            return (Criteria) this;
        }

        public Criteria andAllocationSupervisorIdGreaterThan(Long value) {
            addCriterion("allocation_supervisor_id >", value, "allocationSupervisorId");
            return (Criteria) this;
        }

        public Criteria andAllocationSupervisorIdGreaterThanOrEqualTo(Long value) {
            addCriterion("allocation_supervisor_id >=", value, "allocationSupervisorId");
            return (Criteria) this;
        }

        public Criteria andAllocationSupervisorIdLessThan(Long value) {
            addCriterion("allocation_supervisor_id <", value, "allocationSupervisorId");
            return (Criteria) this;
        }

        public Criteria andAllocationSupervisorIdLessThanOrEqualTo(Long value) {
            addCriterion("allocation_supervisor_id <=", value, "allocationSupervisorId");
            return (Criteria) this;
        }

        public Criteria andAllocationSupervisorIdIn(List<Long> values) {
            addCriterion("allocation_supervisor_id in", values, "allocationSupervisorId");
            return (Criteria) this;
        }

        public Criteria andAllocationSupervisorIdNotIn(List<Long> values) {
            addCriterion("allocation_supervisor_id not in", values, "allocationSupervisorId");
            return (Criteria) this;
        }

        public Criteria andAllocationSupervisorIdBetween(Long value1, Long value2) {
            addCriterion("allocation_supervisor_id between", value1, value2, "allocationSupervisorId");
            return (Criteria) this;
        }

        public Criteria andAllocationSupervisorIdNotBetween(Long value1, Long value2) {
            addCriterion("allocation_supervisor_id not between", value1, value2, "allocationSupervisorId");
            return (Criteria) this;
        }

        public Criteria andManageSupervisorIdIsNull() {
            addCriterion("manage_supervisor_id is null");
            return (Criteria) this;
        }

        public Criteria andManageSupervisorIdIsNotNull() {
            addCriterion("manage_supervisor_id is not null");
            return (Criteria) this;
        }

        public Criteria andManageSupervisorIdEqualTo(Long value) {
            addCriterion("manage_supervisor_id =", value, "manageSupervisorId");
            return (Criteria) this;
        }

        public Criteria andManageSupervisorIdNotEqualTo(Long value) {
            addCriterion("manage_supervisor_id <>", value, "manageSupervisorId");
            return (Criteria) this;
        }

        public Criteria andManageSupervisorIdGreaterThan(Long value) {
            addCriterion("manage_supervisor_id >", value, "manageSupervisorId");
            return (Criteria) this;
        }

        public Criteria andManageSupervisorIdGreaterThanOrEqualTo(Long value) {
            addCriterion("manage_supervisor_id >=", value, "manageSupervisorId");
            return (Criteria) this;
        }

        public Criteria andManageSupervisorIdLessThan(Long value) {
            addCriterion("manage_supervisor_id <", value, "manageSupervisorId");
            return (Criteria) this;
        }

        public Criteria andManageSupervisorIdLessThanOrEqualTo(Long value) {
            addCriterion("manage_supervisor_id <=", value, "manageSupervisorId");
            return (Criteria) this;
        }

        public Criteria andManageSupervisorIdIn(List<Long> values) {
            addCriterion("manage_supervisor_id in", values, "manageSupervisorId");
            return (Criteria) this;
        }

        public Criteria andManageSupervisorIdNotIn(List<Long> values) {
            addCriterion("manage_supervisor_id not in", values, "manageSupervisorId");
            return (Criteria) this;
        }

        public Criteria andManageSupervisorIdBetween(Long value1, Long value2) {
            addCriterion("manage_supervisor_id between", value1, value2, "manageSupervisorId");
            return (Criteria) this;
        }

        public Criteria andManageSupervisorIdNotBetween(Long value1, Long value2) {
            addCriterion("manage_supervisor_id not between", value1, value2, "manageSupervisorId");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNull() {
            addCriterion("audit_time is null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNotNull() {
            addCriterion("audit_time is not null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeEqualTo(Date value) {
            addCriterion("audit_time =", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotEqualTo(Date value) {
            addCriterion("audit_time <>", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThan(Date value) {
            addCriterion("audit_time >", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("audit_time >=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThan(Date value) {
            addCriterion("audit_time <", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThanOrEqualTo(Date value) {
            addCriterion("audit_time <=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIn(List<Date> values) {
            addCriterion("audit_time in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotIn(List<Date> values) {
            addCriterion("audit_time not in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeBetween(Date value1, Date value2) {
            addCriterion("audit_time between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotBetween(Date value1, Date value2) {
            addCriterion("audit_time not between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditSuggestIsNull() {
            addCriterion("audit_suggest is null");
            return (Criteria) this;
        }

        public Criteria andAuditSuggestIsNotNull() {
            addCriterion("audit_suggest is not null");
            return (Criteria) this;
        }

        public Criteria andAuditSuggestEqualTo(String value) {
            addCriterion("audit_suggest =", value, "auditSuggest");
            return (Criteria) this;
        }

        public Criteria andAuditSuggestNotEqualTo(String value) {
            addCriterion("audit_suggest <>", value, "auditSuggest");
            return (Criteria) this;
        }

        public Criteria andAuditSuggestGreaterThan(String value) {
            addCriterion("audit_suggest >", value, "auditSuggest");
            return (Criteria) this;
        }

        public Criteria andAuditSuggestGreaterThanOrEqualTo(String value) {
            addCriterion("audit_suggest >=", value, "auditSuggest");
            return (Criteria) this;
        }

        public Criteria andAuditSuggestLessThan(String value) {
            addCriterion("audit_suggest <", value, "auditSuggest");
            return (Criteria) this;
        }

        public Criteria andAuditSuggestLessThanOrEqualTo(String value) {
            addCriterion("audit_suggest <=", value, "auditSuggest");
            return (Criteria) this;
        }

        public Criteria andAuditSuggestLike(String value) {
            addCriterion("audit_suggest like", value, "auditSuggest");
            return (Criteria) this;
        }

        public Criteria andAuditSuggestNotLike(String value) {
            addCriterion("audit_suggest not like", value, "auditSuggest");
            return (Criteria) this;
        }

        public Criteria andAuditSuggestIn(List<String> values) {
            addCriterion("audit_suggest in", values, "auditSuggest");
            return (Criteria) this;
        }

        public Criteria andAuditSuggestNotIn(List<String> values) {
            addCriterion("audit_suggest not in", values, "auditSuggest");
            return (Criteria) this;
        }

        public Criteria andAuditSuggestBetween(String value1, String value2) {
            addCriterion("audit_suggest between", value1, value2, "auditSuggest");
            return (Criteria) this;
        }

        public Criteria andAuditSuggestNotBetween(String value1, String value2) {
            addCriterion("audit_suggest not between", value1, value2, "auditSuggest");
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

        public Criteria andLastRepayTimeIsNull() {
            addCriterion("last_repay_time is null");
            return (Criteria) this;
        }

        public Criteria andLastRepayTimeIsNotNull() {
            addCriterion("last_repay_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastRepayTimeEqualTo(Date value) {
            addCriterion("last_repay_time =", value, "lastRepayTime");
            return (Criteria) this;
        }

        public Criteria andLastRepayTimeNotEqualTo(Date value) {
            addCriterion("last_repay_time <>", value, "lastRepayTime");
            return (Criteria) this;
        }

        public Criteria andLastRepayTimeGreaterThan(Date value) {
            addCriterion("last_repay_time >", value, "lastRepayTime");
            return (Criteria) this;
        }

        public Criteria andLastRepayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_repay_time >=", value, "lastRepayTime");
            return (Criteria) this;
        }

        public Criteria andLastRepayTimeLessThan(Date value) {
            addCriterion("last_repay_time <", value, "lastRepayTime");
            return (Criteria) this;
        }

        public Criteria andLastRepayTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_repay_time <=", value, "lastRepayTime");
            return (Criteria) this;
        }

        public Criteria andLastRepayTimeIn(List<Date> values) {
            addCriterion("last_repay_time in", values, "lastRepayTime");
            return (Criteria) this;
        }

        public Criteria andLastRepayTimeNotIn(List<Date> values) {
            addCriterion("last_repay_time not in", values, "lastRepayTime");
            return (Criteria) this;
        }

        public Criteria andLastRepayTimeBetween(Date value1, Date value2) {
            addCriterion("last_repay_time between", value1, value2, "lastRepayTime");
            return (Criteria) this;
        }

        public Criteria andLastRepayTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_repay_time not between", value1, value2, "lastRepayTime");
            return (Criteria) this;
        }

        public Criteria andIsAuditmaticInvestBidIsNull() {
            addCriterion("is_auditmatic_invest_bid is null");
            return (Criteria) this;
        }

        public Criteria andIsAuditmaticInvestBidIsNotNull() {
            addCriterion("is_auditmatic_invest_bid is not null");
            return (Criteria) this;
        }

        public Criteria andIsAuditmaticInvestBidEqualTo(Boolean value) {
            addCriterion("is_auditmatic_invest_bid =", value, "isAuditmaticInvestBid");
            return (Criteria) this;
        }

        public Criteria andIsAuditmaticInvestBidNotEqualTo(Boolean value) {
            addCriterion("is_auditmatic_invest_bid <>", value, "isAuditmaticInvestBid");
            return (Criteria) this;
        }

        public Criteria andIsAuditmaticInvestBidGreaterThan(Boolean value) {
            addCriterion("is_auditmatic_invest_bid >", value, "isAuditmaticInvestBid");
            return (Criteria) this;
        }

        public Criteria andIsAuditmaticInvestBidGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_auditmatic_invest_bid >=", value, "isAuditmaticInvestBid");
            return (Criteria) this;
        }

        public Criteria andIsAuditmaticInvestBidLessThan(Boolean value) {
            addCriterion("is_auditmatic_invest_bid <", value, "isAuditmaticInvestBid");
            return (Criteria) this;
        }

        public Criteria andIsAuditmaticInvestBidLessThanOrEqualTo(Boolean value) {
            addCriterion("is_auditmatic_invest_bid <=", value, "isAuditmaticInvestBid");
            return (Criteria) this;
        }

        public Criteria andIsAuditmaticInvestBidIn(List<Boolean> values) {
            addCriterion("is_auditmatic_invest_bid in", values, "isAuditmaticInvestBid");
            return (Criteria) this;
        }

        public Criteria andIsAuditmaticInvestBidNotIn(List<Boolean> values) {
            addCriterion("is_auditmatic_invest_bid not in", values, "isAuditmaticInvestBid");
            return (Criteria) this;
        }

        public Criteria andIsAuditmaticInvestBidBetween(Boolean value1, Boolean value2) {
            addCriterion("is_auditmatic_invest_bid between", value1, value2, "isAuditmaticInvestBid");
            return (Criteria) this;
        }

        public Criteria andIsAuditmaticInvestBidNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_auditmatic_invest_bid not between", value1, value2, "isAuditmaticInvestBid");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Integer value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Integer value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Integer value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Integer value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Integer value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Integer> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Integer> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Integer value1, Integer value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andMarkIsNull() {
            addCriterion("mark is null");
            return (Criteria) this;
        }

        public Criteria andMarkIsNotNull() {
            addCriterion("mark is not null");
            return (Criteria) this;
        }

        public Criteria andMarkEqualTo(String value) {
            addCriterion("mark =", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotEqualTo(String value) {
            addCriterion("mark <>", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkGreaterThan(String value) {
            addCriterion("mark >", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkGreaterThanOrEqualTo(String value) {
            addCriterion("mark >=", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLessThan(String value) {
            addCriterion("mark <", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLessThanOrEqualTo(String value) {
            addCriterion("mark <=", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLike(String value) {
            addCriterion("mark like", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotLike(String value) {
            addCriterion("mark not like", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkIn(List<String> values) {
            addCriterion("mark in", values, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotIn(List<String> values) {
            addCriterion("mark not in", values, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkBetween(String value1, String value2) {
            addCriterion("mark between", value1, value2, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotBetween(String value1, String value2) {
            addCriterion("mark not between", value1, value2, "mark");
            return (Criteria) this;
        }

        public Criteria andQrCodeIsNull() {
            addCriterion("qr_code is null");
            return (Criteria) this;
        }

        public Criteria andQrCodeIsNotNull() {
            addCriterion("qr_code is not null");
            return (Criteria) this;
        }

        public Criteria andQrCodeEqualTo(String value) {
            addCriterion("qr_code =", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeNotEqualTo(String value) {
            addCriterion("qr_code <>", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeGreaterThan(String value) {
            addCriterion("qr_code >", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeGreaterThanOrEqualTo(String value) {
            addCriterion("qr_code >=", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeLessThan(String value) {
            addCriterion("qr_code <", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeLessThanOrEqualTo(String value) {
            addCriterion("qr_code <=", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeLike(String value) {
            addCriterion("qr_code like", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeNotLike(String value) {
            addCriterion("qr_code not like", value, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeIn(List<String> values) {
            addCriterion("qr_code in", values, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeNotIn(List<String> values) {
            addCriterion("qr_code not in", values, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeBetween(String value1, String value2) {
            addCriterion("qr_code between", value1, value2, "qrCode");
            return (Criteria) this;
        }

        public Criteria andQrCodeNotBetween(String value1, String value2) {
            addCriterion("qr_code not between", value1, value2, "qrCode");
            return (Criteria) this;
        }

        public Criteria andInvestRateIsNull() {
            addCriterion("invest_rate is null");
            return (Criteria) this;
        }

        public Criteria andInvestRateIsNotNull() {
            addCriterion("invest_rate is not null");
            return (Criteria) this;
        }

        public Criteria andInvestRateEqualTo(Double value) {
            addCriterion("invest_rate =", value, "investRate");
            return (Criteria) this;
        }

        public Criteria andInvestRateNotEqualTo(Double value) {
            addCriterion("invest_rate <>", value, "investRate");
            return (Criteria) this;
        }

        public Criteria andInvestRateGreaterThan(Double value) {
            addCriterion("invest_rate >", value, "investRate");
            return (Criteria) this;
        }

        public Criteria andInvestRateGreaterThanOrEqualTo(Double value) {
            addCriterion("invest_rate >=", value, "investRate");
            return (Criteria) this;
        }

        public Criteria andInvestRateLessThan(Double value) {
            addCriterion("invest_rate <", value, "investRate");
            return (Criteria) this;
        }

        public Criteria andInvestRateLessThanOrEqualTo(Double value) {
            addCriterion("invest_rate <=", value, "investRate");
            return (Criteria) this;
        }

        public Criteria andInvestRateIn(List<Double> values) {
            addCriterion("invest_rate in", values, "investRate");
            return (Criteria) this;
        }

        public Criteria andInvestRateNotIn(List<Double> values) {
            addCriterion("invest_rate not in", values, "investRate");
            return (Criteria) this;
        }

        public Criteria andInvestRateBetween(Double value1, Double value2) {
            addCriterion("invest_rate between", value1, value2, "investRate");
            return (Criteria) this;
        }

        public Criteria andInvestRateNotBetween(Double value1, Double value2) {
            addCriterion("invest_rate not between", value1, value2, "investRate");
            return (Criteria) this;
        }

        public Criteria andOverdueRateIsNull() {
            addCriterion("overdue_rate is null");
            return (Criteria) this;
        }

        public Criteria andOverdueRateIsNotNull() {
            addCriterion("overdue_rate is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueRateEqualTo(Double value) {
            addCriterion("overdue_rate =", value, "overdueRate");
            return (Criteria) this;
        }

        public Criteria andOverdueRateNotEqualTo(Double value) {
            addCriterion("overdue_rate <>", value, "overdueRate");
            return (Criteria) this;
        }

        public Criteria andOverdueRateGreaterThan(Double value) {
            addCriterion("overdue_rate >", value, "overdueRate");
            return (Criteria) this;
        }

        public Criteria andOverdueRateGreaterThanOrEqualTo(Double value) {
            addCriterion("overdue_rate >=", value, "overdueRate");
            return (Criteria) this;
        }

        public Criteria andOverdueRateLessThan(Double value) {
            addCriterion("overdue_rate <", value, "overdueRate");
            return (Criteria) this;
        }

        public Criteria andOverdueRateLessThanOrEqualTo(Double value) {
            addCriterion("overdue_rate <=", value, "overdueRate");
            return (Criteria) this;
        }

        public Criteria andOverdueRateIn(List<Double> values) {
            addCriterion("overdue_rate in", values, "overdueRate");
            return (Criteria) this;
        }

        public Criteria andOverdueRateNotIn(List<Double> values) {
            addCriterion("overdue_rate not in", values, "overdueRate");
            return (Criteria) this;
        }

        public Criteria andOverdueRateBetween(Double value1, Double value2) {
            addCriterion("overdue_rate between", value1, value2, "overdueRate");
            return (Criteria) this;
        }

        public Criteria andOverdueRateNotBetween(Double value1, Double value2) {
            addCriterion("overdue_rate not between", value1, value2, "overdueRate");
            return (Criteria) this;
        }

        public Criteria andIsRegisterGuarantorIsNull() {
            addCriterion("is_register_guarantor is null");
            return (Criteria) this;
        }

        public Criteria andIsRegisterGuarantorIsNotNull() {
            addCriterion("is_register_guarantor is not null");
            return (Criteria) this;
        }

        public Criteria andIsRegisterGuarantorEqualTo(Boolean value) {
            addCriterion("is_register_guarantor =", value, "isRegisterGuarantor");
            return (Criteria) this;
        }

        public Criteria andIsRegisterGuarantorNotEqualTo(Boolean value) {
            addCriterion("is_register_guarantor <>", value, "isRegisterGuarantor");
            return (Criteria) this;
        }

        public Criteria andIsRegisterGuarantorGreaterThan(Boolean value) {
            addCriterion("is_register_guarantor >", value, "isRegisterGuarantor");
            return (Criteria) this;
        }

        public Criteria andIsRegisterGuarantorGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_register_guarantor >=", value, "isRegisterGuarantor");
            return (Criteria) this;
        }

        public Criteria andIsRegisterGuarantorLessThan(Boolean value) {
            addCriterion("is_register_guarantor <", value, "isRegisterGuarantor");
            return (Criteria) this;
        }

        public Criteria andIsRegisterGuarantorLessThanOrEqualTo(Boolean value) {
            addCriterion("is_register_guarantor <=", value, "isRegisterGuarantor");
            return (Criteria) this;
        }

        public Criteria andIsRegisterGuarantorIn(List<Boolean> values) {
            addCriterion("is_register_guarantor in", values, "isRegisterGuarantor");
            return (Criteria) this;
        }

        public Criteria andIsRegisterGuarantorNotIn(List<Boolean> values) {
            addCriterion("is_register_guarantor not in", values, "isRegisterGuarantor");
            return (Criteria) this;
        }

        public Criteria andIsRegisterGuarantorBetween(Boolean value1, Boolean value2) {
            addCriterion("is_register_guarantor between", value1, value2, "isRegisterGuarantor");
            return (Criteria) this;
        }

        public Criteria andIsRegisterGuarantorNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_register_guarantor not between", value1, value2, "isRegisterGuarantor");
            return (Criteria) this;
        }

        public Criteria andApplyAmountIsNull() {
            addCriterion("apply_amount is null");
            return (Criteria) this;
        }

        public Criteria andApplyAmountIsNotNull() {
            addCriterion("apply_amount is not null");
            return (Criteria) this;
        }

        public Criteria andApplyAmountEqualTo(BigDecimal value) {
            addCriterion("apply_amount =", value, "applyAmount");
            return (Criteria) this;
        }

        public Criteria andApplyAmountNotEqualTo(BigDecimal value) {
            addCriterion("apply_amount <>", value, "applyAmount");
            return (Criteria) this;
        }

        public Criteria andApplyAmountGreaterThan(BigDecimal value) {
            addCriterion("apply_amount >", value, "applyAmount");
            return (Criteria) this;
        }

        public Criteria andApplyAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("apply_amount >=", value, "applyAmount");
            return (Criteria) this;
        }

        public Criteria andApplyAmountLessThan(BigDecimal value) {
            addCriterion("apply_amount <", value, "applyAmount");
            return (Criteria) this;
        }

        public Criteria andApplyAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("apply_amount <=", value, "applyAmount");
            return (Criteria) this;
        }

        public Criteria andApplyAmountIn(List<BigDecimal> values) {
            addCriterion("apply_amount in", values, "applyAmount");
            return (Criteria) this;
        }

        public Criteria andApplyAmountNotIn(List<BigDecimal> values) {
            addCriterion("apply_amount not in", values, "applyAmount");
            return (Criteria) this;
        }

        public Criteria andApplyAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("apply_amount between", value1, value2, "applyAmount");
            return (Criteria) this;
        }

        public Criteria andApplyAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("apply_amount not between", value1, value2, "applyAmount");
            return (Criteria) this;
        }

        public Criteria andClientIsNull() {
            addCriterion("client is null");
            return (Criteria) this;
        }

        public Criteria andClientIsNotNull() {
            addCriterion("client is not null");
            return (Criteria) this;
        }

        public Criteria andClientEqualTo(Byte value) {
            addCriterion("client =", value, "client");
            return (Criteria) this;
        }

        public Criteria andClientNotEqualTo(Byte value) {
            addCriterion("client <>", value, "client");
            return (Criteria) this;
        }

        public Criteria andClientGreaterThan(Byte value) {
            addCriterion("client >", value, "client");
            return (Criteria) this;
        }

        public Criteria andClientGreaterThanOrEqualTo(Byte value) {
            addCriterion("client >=", value, "client");
            return (Criteria) this;
        }

        public Criteria andClientLessThan(Byte value) {
            addCriterion("client <", value, "client");
            return (Criteria) this;
        }

        public Criteria andClientLessThanOrEqualTo(Byte value) {
            addCriterion("client <=", value, "client");
            return (Criteria) this;
        }

        public Criteria andClientIn(List<Byte> values) {
            addCriterion("client in", values, "client");
            return (Criteria) this;
        }

        public Criteria andClientNotIn(List<Byte> values) {
            addCriterion("client not in", values, "client");
            return (Criteria) this;
        }

        public Criteria andClientBetween(Byte value1, Byte value2) {
            addCriterion("client between", value1, value2, "client");
            return (Criteria) this;
        }

        public Criteria andClientNotBetween(Byte value1, Byte value2) {
            addCriterion("client not between", value1, value2, "client");
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

        public Criteria andBidTypeIsNull() {
            addCriterion("bid_type is null");
            return (Criteria) this;
        }

        public Criteria andBidTypeIsNotNull() {
            addCriterion("bid_type is not null");
            return (Criteria) this;
        }

        public Criteria andBidTypeEqualTo(Boolean value) {
            addCriterion("bid_type =", value, "bidType");
            return (Criteria) this;
        }

        public Criteria andBidTypeNotEqualTo(Boolean value) {
            addCriterion("bid_type <>", value, "bidType");
            return (Criteria) this;
        }

        public Criteria andBidTypeGreaterThan(Boolean value) {
            addCriterion("bid_type >", value, "bidType");
            return (Criteria) this;
        }

        public Criteria andBidTypeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bid_type >=", value, "bidType");
            return (Criteria) this;
        }

        public Criteria andBidTypeLessThan(Boolean value) {
            addCriterion("bid_type <", value, "bidType");
            return (Criteria) this;
        }

        public Criteria andBidTypeLessThanOrEqualTo(Boolean value) {
            addCriterion("bid_type <=", value, "bidType");
            return (Criteria) this;
        }

        public Criteria andBidTypeIn(List<Boolean> values) {
            addCriterion("bid_type in", values, "bidType");
            return (Criteria) this;
        }

        public Criteria andBidTypeNotIn(List<Boolean> values) {
            addCriterion("bid_type not in", values, "bidType");
            return (Criteria) this;
        }

        public Criteria andBidTypeBetween(Boolean value1, Boolean value2) {
            addCriterion("bid_type between", value1, value2, "bidType");
            return (Criteria) this;
        }

        public Criteria andBidTypeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bid_type not between", value1, value2, "bidType");
            return (Criteria) this;
        }

        public Criteria andReleaseSupervisorIdIsNull() {
            addCriterion("release_supervisor_id is null");
            return (Criteria) this;
        }

        public Criteria andReleaseSupervisorIdIsNotNull() {
            addCriterion("release_supervisor_id is not null");
            return (Criteria) this;
        }

        public Criteria andReleaseSupervisorIdEqualTo(Long value) {
            addCriterion("release_supervisor_id =", value, "releaseSupervisorId");
            return (Criteria) this;
        }

        public Criteria andReleaseSupervisorIdNotEqualTo(Long value) {
            addCriterion("release_supervisor_id <>", value, "releaseSupervisorId");
            return (Criteria) this;
        }

        public Criteria andReleaseSupervisorIdGreaterThan(Long value) {
            addCriterion("release_supervisor_id >", value, "releaseSupervisorId");
            return (Criteria) this;
        }

        public Criteria andReleaseSupervisorIdGreaterThanOrEqualTo(Long value) {
            addCriterion("release_supervisor_id >=", value, "releaseSupervisorId");
            return (Criteria) this;
        }

        public Criteria andReleaseSupervisorIdLessThan(Long value) {
            addCriterion("release_supervisor_id <", value, "releaseSupervisorId");
            return (Criteria) this;
        }

        public Criteria andReleaseSupervisorIdLessThanOrEqualTo(Long value) {
            addCriterion("release_supervisor_id <=", value, "releaseSupervisorId");
            return (Criteria) this;
        }

        public Criteria andReleaseSupervisorIdIn(List<Long> values) {
            addCriterion("release_supervisor_id in", values, "releaseSupervisorId");
            return (Criteria) this;
        }

        public Criteria andReleaseSupervisorIdNotIn(List<Long> values) {
            addCriterion("release_supervisor_id not in", values, "releaseSupervisorId");
            return (Criteria) this;
        }

        public Criteria andReleaseSupervisorIdBetween(Long value1, Long value2) {
            addCriterion("release_supervisor_id between", value1, value2, "releaseSupervisorId");
            return (Criteria) this;
        }

        public Criteria andReleaseSupervisorIdNotBetween(Long value1, Long value2) {
            addCriterion("release_supervisor_id not between", value1, value2, "releaseSupervisorId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdIsNull() {
            addCriterion("relevance_id is null");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdIsNotNull() {
            addCriterion("relevance_id is not null");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdEqualTo(Long value) {
            addCriterion("relevance_id =", value, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdNotEqualTo(Long value) {
            addCriterion("relevance_id <>", value, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdGreaterThan(Long value) {
            addCriterion("relevance_id >", value, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("relevance_id >=", value, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdLessThan(Long value) {
            addCriterion("relevance_id <", value, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdLessThanOrEqualTo(Long value) {
            addCriterion("relevance_id <=", value, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdIn(List<Long> values) {
            addCriterion("relevance_id in", values, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdNotIn(List<Long> values) {
            addCriterion("relevance_id not in", values, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdBetween(Long value1, Long value2) {
            addCriterion("relevance_id between", value1, value2, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andRelevanceIdNotBetween(Long value1, Long value2) {
            addCriterion("relevance_id not between", value1, value2, "relevanceId");
            return (Criteria) this;
        }

        public Criteria andCollectSupervisorIdIsNull() {
            addCriterion("collect_supervisor_id is null");
            return (Criteria) this;
        }

        public Criteria andCollectSupervisorIdIsNotNull() {
            addCriterion("collect_supervisor_id is not null");
            return (Criteria) this;
        }

        public Criteria andCollectSupervisorIdEqualTo(Long value) {
            addCriterion("collect_supervisor_id =", value, "collectSupervisorId");
            return (Criteria) this;
        }

        public Criteria andCollectSupervisorIdNotEqualTo(Long value) {
            addCriterion("collect_supervisor_id <>", value, "collectSupervisorId");
            return (Criteria) this;
        }

        public Criteria andCollectSupervisorIdGreaterThan(Long value) {
            addCriterion("collect_supervisor_id >", value, "collectSupervisorId");
            return (Criteria) this;
        }

        public Criteria andCollectSupervisorIdGreaterThanOrEqualTo(Long value) {
            addCriterion("collect_supervisor_id >=", value, "collectSupervisorId");
            return (Criteria) this;
        }

        public Criteria andCollectSupervisorIdLessThan(Long value) {
            addCriterion("collect_supervisor_id <", value, "collectSupervisorId");
            return (Criteria) this;
        }

        public Criteria andCollectSupervisorIdLessThanOrEqualTo(Long value) {
            addCriterion("collect_supervisor_id <=", value, "collectSupervisorId");
            return (Criteria) this;
        }

        public Criteria andCollectSupervisorIdIn(List<Long> values) {
            addCriterion("collect_supervisor_id in", values, "collectSupervisorId");
            return (Criteria) this;
        }

        public Criteria andCollectSupervisorIdNotIn(List<Long> values) {
            addCriterion("collect_supervisor_id not in", values, "collectSupervisorId");
            return (Criteria) this;
        }

        public Criteria andCollectSupervisorIdBetween(Long value1, Long value2) {
            addCriterion("collect_supervisor_id between", value1, value2, "collectSupervisorId");
            return (Criteria) this;
        }

        public Criteria andCollectSupervisorIdNotBetween(Long value1, Long value2) {
            addCriterion("collect_supervisor_id not between", value1, value2, "collectSupervisorId");
            return (Criteria) this;
        }

        public Criteria andBeginRunTimeIsNull() {
            addCriterion("begin_run_time is null");
            return (Criteria) this;
        }

        public Criteria andBeginRunTimeIsNotNull() {
            addCriterion("begin_run_time is not null");
            return (Criteria) this;
        }

        public Criteria andBeginRunTimeEqualTo(Date value) {
            addCriterionForJDBCDate("begin_run_time =", value, "beginRunTime");
            return (Criteria) this;
        }

        public Criteria andBeginRunTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("begin_run_time <>", value, "beginRunTime");
            return (Criteria) this;
        }

        public Criteria andBeginRunTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("begin_run_time >", value, "beginRunTime");
            return (Criteria) this;
        }

        public Criteria andBeginRunTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("begin_run_time >=", value, "beginRunTime");
            return (Criteria) this;
        }

        public Criteria andBeginRunTimeLessThan(Date value) {
            addCriterionForJDBCDate("begin_run_time <", value, "beginRunTime");
            return (Criteria) this;
        }

        public Criteria andBeginRunTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("begin_run_time <=", value, "beginRunTime");
            return (Criteria) this;
        }

        public Criteria andBeginRunTimeIn(List<Date> values) {
            addCriterionForJDBCDate("begin_run_time in", values, "beginRunTime");
            return (Criteria) this;
        }

        public Criteria andBeginRunTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("begin_run_time not in", values, "beginRunTime");
            return (Criteria) this;
        }

        public Criteria andBeginRunTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("begin_run_time between", value1, value2, "beginRunTime");
            return (Criteria) this;
        }

        public Criteria andBeginRunTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("begin_run_time not between", value1, value2, "beginRunTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeIsNull() {
            addCriterion("run_time is null");
            return (Criteria) this;
        }

        public Criteria andRunTimeIsNotNull() {
            addCriterion("run_time is not null");
            return (Criteria) this;
        }

        public Criteria andRunTimeEqualTo(Date value) {
            addCriterionForJDBCDate("run_time =", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("run_time <>", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("run_time >", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("run_time >=", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeLessThan(Date value) {
            addCriterionForJDBCDate("run_time <", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("run_time <=", value, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeIn(List<Date> values) {
            addCriterionForJDBCDate("run_time in", values, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("run_time not in", values, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("run_time between", value1, value2, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("run_time not between", value1, value2, "runTime");
            return (Criteria) this;
        }

        public Criteria andRunRateIsNull() {
            addCriterion("run_rate is null");
            return (Criteria) this;
        }

        public Criteria andRunRateIsNotNull() {
            addCriterion("run_rate is not null");
            return (Criteria) this;
        }

        public Criteria andRunRateEqualTo(Double value) {
            addCriterion("run_rate =", value, "runRate");
            return (Criteria) this;
        }

        public Criteria andRunRateNotEqualTo(Double value) {
            addCriterion("run_rate <>", value, "runRate");
            return (Criteria) this;
        }

        public Criteria andRunRateGreaterThan(Double value) {
            addCriterion("run_rate >", value, "runRate");
            return (Criteria) this;
        }

        public Criteria andRunRateGreaterThanOrEqualTo(Double value) {
            addCriterion("run_rate >=", value, "runRate");
            return (Criteria) this;
        }

        public Criteria andRunRateLessThan(Double value) {
            addCriterion("run_rate <", value, "runRate");
            return (Criteria) this;
        }

        public Criteria andRunRateLessThanOrEqualTo(Double value) {
            addCriterion("run_rate <=", value, "runRate");
            return (Criteria) this;
        }

        public Criteria andRunRateIn(List<Double> values) {
            addCriterion("run_rate in", values, "runRate");
            return (Criteria) this;
        }

        public Criteria andRunRateNotIn(List<Double> values) {
            addCriterion("run_rate not in", values, "runRate");
            return (Criteria) this;
        }

        public Criteria andRunRateBetween(Double value1, Double value2) {
            addCriterion("run_rate between", value1, value2, "runRate");
            return (Criteria) this;
        }

        public Criteria andRunRateNotBetween(Double value1, Double value2) {
            addCriterion("run_rate not between", value1, value2, "runRate");
            return (Criteria) this;
        }

        public Criteria andRunCreditIsNull() {
            addCriterion("run_credit is null");
            return (Criteria) this;
        }

        public Criteria andRunCreditIsNotNull() {
            addCriterion("run_credit is not null");
            return (Criteria) this;
        }

        public Criteria andRunCreditEqualTo(Long value) {
            addCriterion("run_credit =", value, "runCredit");
            return (Criteria) this;
        }

        public Criteria andRunCreditNotEqualTo(Long value) {
            addCriterion("run_credit <>", value, "runCredit");
            return (Criteria) this;
        }

        public Criteria andRunCreditGreaterThan(Long value) {
            addCriterion("run_credit >", value, "runCredit");
            return (Criteria) this;
        }

        public Criteria andRunCreditGreaterThanOrEqualTo(Long value) {
            addCriterion("run_credit >=", value, "runCredit");
            return (Criteria) this;
        }

        public Criteria andRunCreditLessThan(Long value) {
            addCriterion("run_credit <", value, "runCredit");
            return (Criteria) this;
        }

        public Criteria andRunCreditLessThanOrEqualTo(Long value) {
            addCriterion("run_credit <=", value, "runCredit");
            return (Criteria) this;
        }

        public Criteria andRunCreditIn(List<Long> values) {
            addCriterion("run_credit in", values, "runCredit");
            return (Criteria) this;
        }

        public Criteria andRunCreditNotIn(List<Long> values) {
            addCriterion("run_credit not in", values, "runCredit");
            return (Criteria) this;
        }

        public Criteria andRunCreditBetween(Long value1, Long value2) {
            addCriterion("run_credit between", value1, value2, "runCredit");
            return (Criteria) this;
        }

        public Criteria andRunCreditNotBetween(Long value1, Long value2) {
            addCriterion("run_credit not between", value1, value2, "runCredit");
            return (Criteria) this;
        }

        public Criteria andRunRateTimesIsNull() {
            addCriterion("run_rate_times is null");
            return (Criteria) this;
        }

        public Criteria andRunRateTimesIsNotNull() {
            addCriterion("run_rate_times is not null");
            return (Criteria) this;
        }

        public Criteria andRunRateTimesEqualTo(Byte value) {
            addCriterion("run_rate_times =", value, "runRateTimes");
            return (Criteria) this;
        }

        public Criteria andRunRateTimesNotEqualTo(Byte value) {
            addCriterion("run_rate_times <>", value, "runRateTimes");
            return (Criteria) this;
        }

        public Criteria andRunRateTimesGreaterThan(Byte value) {
            addCriterion("run_rate_times >", value, "runRateTimes");
            return (Criteria) this;
        }

        public Criteria andRunRateTimesGreaterThanOrEqualTo(Byte value) {
            addCriterion("run_rate_times >=", value, "runRateTimes");
            return (Criteria) this;
        }

        public Criteria andRunRateTimesLessThan(Byte value) {
            addCriterion("run_rate_times <", value, "runRateTimes");
            return (Criteria) this;
        }

        public Criteria andRunRateTimesLessThanOrEqualTo(Byte value) {
            addCriterion("run_rate_times <=", value, "runRateTimes");
            return (Criteria) this;
        }

        public Criteria andRunRateTimesIn(List<Byte> values) {
            addCriterion("run_rate_times in", values, "runRateTimes");
            return (Criteria) this;
        }

        public Criteria andRunRateTimesNotIn(List<Byte> values) {
            addCriterion("run_rate_times not in", values, "runRateTimes");
            return (Criteria) this;
        }

        public Criteria andRunRateTimesBetween(Byte value1, Byte value2) {
            addCriterion("run_rate_times between", value1, value2, "runRateTimes");
            return (Criteria) this;
        }

        public Criteria andRunRateTimesNotBetween(Byte value1, Byte value2) {
            addCriterion("run_rate_times not between", value1, value2, "runRateTimes");
            return (Criteria) this;
        }

        public Criteria andRunCreditTimesIsNull() {
            addCriterion("run_credit_times is null");
            return (Criteria) this;
        }

        public Criteria andRunCreditTimesIsNotNull() {
            addCriterion("run_credit_times is not null");
            return (Criteria) this;
        }

        public Criteria andRunCreditTimesEqualTo(Byte value) {
            addCriterion("run_credit_times =", value, "runCreditTimes");
            return (Criteria) this;
        }

        public Criteria andRunCreditTimesNotEqualTo(Byte value) {
            addCriterion("run_credit_times <>", value, "runCreditTimes");
            return (Criteria) this;
        }

        public Criteria andRunCreditTimesGreaterThan(Byte value) {
            addCriterion("run_credit_times >", value, "runCreditTimes");
            return (Criteria) this;
        }

        public Criteria andRunCreditTimesGreaterThanOrEqualTo(Byte value) {
            addCriterion("run_credit_times >=", value, "runCreditTimes");
            return (Criteria) this;
        }

        public Criteria andRunCreditTimesLessThan(Byte value) {
            addCriterion("run_credit_times <", value, "runCreditTimes");
            return (Criteria) this;
        }

        public Criteria andRunCreditTimesLessThanOrEqualTo(Byte value) {
            addCriterion("run_credit_times <=", value, "runCreditTimes");
            return (Criteria) this;
        }

        public Criteria andRunCreditTimesIn(List<Byte> values) {
            addCriterion("run_credit_times in", values, "runCreditTimes");
            return (Criteria) this;
        }

        public Criteria andRunCreditTimesNotIn(List<Byte> values) {
            addCriterion("run_credit_times not in", values, "runCreditTimes");
            return (Criteria) this;
        }

        public Criteria andRunCreditTimesBetween(Byte value1, Byte value2) {
            addCriterion("run_credit_times between", value1, value2, "runCreditTimes");
            return (Criteria) this;
        }

        public Criteria andRunCreditTimesNotBetween(Byte value1, Byte value2) {
            addCriterion("run_credit_times not between", value1, value2, "runCreditTimes");
            return (Criteria) this;
        }

        public Criteria andTimesIsNull() {
            addCriterion("times is null");
            return (Criteria) this;
        }

        public Criteria andTimesIsNotNull() {
            addCriterion("times is not null");
            return (Criteria) this;
        }

        public Criteria andTimesEqualTo(Byte value) {
            addCriterion("times =", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesNotEqualTo(Byte value) {
            addCriterion("times <>", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesGreaterThan(Byte value) {
            addCriterion("times >", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesGreaterThanOrEqualTo(Byte value) {
            addCriterion("times >=", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesLessThan(Byte value) {
            addCriterion("times <", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesLessThanOrEqualTo(Byte value) {
            addCriterion("times <=", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesIn(List<Byte> values) {
            addCriterion("times in", values, "times");
            return (Criteria) this;
        }

        public Criteria andTimesNotIn(List<Byte> values) {
            addCriterion("times not in", values, "times");
            return (Criteria) this;
        }

        public Criteria andTimesBetween(Byte value1, Byte value2) {
            addCriterion("times between", value1, value2, "times");
            return (Criteria) this;
        }

        public Criteria andTimesNotBetween(Byte value1, Byte value2) {
            addCriterion("times not between", value1, value2, "times");
            return (Criteria) this;
        }

        public Criteria andProductIdFlagIsNull() {
            addCriterion("product_id_flag is null");
            return (Criteria) this;
        }

        public Criteria andProductIdFlagIsNotNull() {
            addCriterion("product_id_flag is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdFlagEqualTo(Long value) {
            addCriterion("product_id_flag =", value, "productIdFlag");
            return (Criteria) this;
        }

        public Criteria andProductIdFlagNotEqualTo(Long value) {
            addCriterion("product_id_flag <>", value, "productIdFlag");
            return (Criteria) this;
        }

        public Criteria andProductIdFlagGreaterThan(Long value) {
            addCriterion("product_id_flag >", value, "productIdFlag");
            return (Criteria) this;
        }

        public Criteria andProductIdFlagGreaterThanOrEqualTo(Long value) {
            addCriterion("product_id_flag >=", value, "productIdFlag");
            return (Criteria) this;
        }

        public Criteria andProductIdFlagLessThan(Long value) {
            addCriterion("product_id_flag <", value, "productIdFlag");
            return (Criteria) this;
        }

        public Criteria andProductIdFlagLessThanOrEqualTo(Long value) {
            addCriterion("product_id_flag <=", value, "productIdFlag");
            return (Criteria) this;
        }

        public Criteria andProductIdFlagIn(List<Long> values) {
            addCriterion("product_id_flag in", values, "productIdFlag");
            return (Criteria) this;
        }

        public Criteria andProductIdFlagNotIn(List<Long> values) {
            addCriterion("product_id_flag not in", values, "productIdFlag");
            return (Criteria) this;
        }

        public Criteria andProductIdFlagBetween(Long value1, Long value2) {
            addCriterion("product_id_flag between", value1, value2, "productIdFlag");
            return (Criteria) this;
        }

        public Criteria andProductIdFlagNotBetween(Long value1, Long value2) {
            addCriterion("product_id_flag not between", value1, value2, "productIdFlag");
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

        public Criteria andMerBillNoLikeInsensitive(String value) {
            addCriterion("upper(mer_bill_no) like", value.toUpperCase(), "merBillNo");
            return (Criteria) this;
        }

        public Criteria andBidNoLikeInsensitive(String value) {
            addCriterion("upper(bid_no) like", value.toUpperCase(), "bidNo");
            return (Criteria) this;
        }

        public Criteria andIpsBillNoLikeInsensitive(String value) {
            addCriterion("upper(ips_bill_no) like", value.toUpperCase(), "ipsBillNo");
            return (Criteria) this;
        }

        public Criteria andTitleLikeInsensitive(String value) {
            addCriterion("upper(title) like", value.toUpperCase(), "title");
            return (Criteria) this;
        }

        public Criteria andImageFilenameLikeInsensitive(String value) {
            addCriterion("upper(image_filename) like", value.toUpperCase(), "imageFilename");
            return (Criteria) this;
        }

        public Criteria andDescriptionLikeInsensitive(String value) {
            addCriterion("upper(description) like", value.toUpperCase(), "description");
            return (Criteria) this;
        }

        public Criteria andItemLikeInsensitive(String value) {
            addCriterion("upper(item) like", value.toUpperCase(), "item");
            return (Criteria) this;
        }

        public Criteria andAuditSuggestLikeInsensitive(String value) {
            addCriterion("upper(audit_suggest) like", value.toUpperCase(), "auditSuggest");
            return (Criteria) this;
        }

        public Criteria andMarkLikeInsensitive(String value) {
            addCriterion("upper(mark) like", value.toUpperCase(), "mark");
            return (Criteria) this;
        }

        public Criteria andQrCodeLikeInsensitive(String value) {
            addCriterion("upper(qr_code) like", value.toUpperCase(), "qrCode");
            return (Criteria) this;
        }

        public Criteria andPushErrorMessageLikeInsensitive(String value) {
            addCriterion("upper(push_error_message) like", value.toUpperCase(), "pushErrorMessage");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private BidExample example;

        protected Criteria(BidExample example) {
            super();
            this.example = example;
        }

        public BidExample example() {
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