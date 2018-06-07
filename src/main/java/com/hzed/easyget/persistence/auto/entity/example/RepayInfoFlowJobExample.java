package com.hzed.easyget.persistence.auto.entity.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RepayInfoFlowJobExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer offset;

    protected Integer rows;

    public RepayInfoFlowJobExample() {
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

    public RepayInfoFlowJobExample limit(Integer rows) {
        this.rows = rows;
        return this;
    }

    public RepayInfoFlowJobExample limit(Integer offset, Integer rows) {
        this.offset = offset;
        this.rows = rows;
        return this;
    }

    public RepayInfoFlowJobExample page(Integer page, Integer pageSize) {
        this.offset = page * pageSize;
        this.rows = pageSize;
        return this;
    }

    public RepayInfoFlowJobExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public RepayInfoFlowJobExample orderBy(String ... orderByClauses) {
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

        public Criteria andRepaymentAmountIsNull() {
            addCriterion("repayment_amount is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentAmountIsNotNull() {
            addCriterion("repayment_amount is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentAmountEqualTo(BigDecimal value) {
            addCriterion("repayment_amount =", value, "repaymentAmount");
            return (Criteria) this;
        }

        public Criteria andRepaymentAmountNotEqualTo(BigDecimal value) {
            addCriterion("repayment_amount <>", value, "repaymentAmount");
            return (Criteria) this;
        }

        public Criteria andRepaymentAmountGreaterThan(BigDecimal value) {
            addCriterion("repayment_amount >", value, "repaymentAmount");
            return (Criteria) this;
        }

        public Criteria andRepaymentAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("repayment_amount >=", value, "repaymentAmount");
            return (Criteria) this;
        }

        public Criteria andRepaymentAmountLessThan(BigDecimal value) {
            addCriterion("repayment_amount <", value, "repaymentAmount");
            return (Criteria) this;
        }

        public Criteria andRepaymentAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("repayment_amount <=", value, "repaymentAmount");
            return (Criteria) this;
        }

        public Criteria andRepaymentAmountIn(List<BigDecimal> values) {
            addCriterion("repayment_amount in", values, "repaymentAmount");
            return (Criteria) this;
        }

        public Criteria andRepaymentAmountNotIn(List<BigDecimal> values) {
            addCriterion("repayment_amount not in", values, "repaymentAmount");
            return (Criteria) this;
        }

        public Criteria andRepaymentAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("repayment_amount between", value1, value2, "repaymentAmount");
            return (Criteria) this;
        }

        public Criteria andRepaymentAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("repayment_amount not between", value1, value2, "repaymentAmount");
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

        public Criteria andRealRepaymentTimeEqualTo(LocalDateTime value) {
            addCriterion("real_repayment_time =", value, "realRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentTimeNotEqualTo(LocalDateTime value) {
            addCriterion("real_repayment_time <>", value, "realRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentTimeGreaterThan(LocalDateTime value) {
            addCriterion("real_repayment_time >", value, "realRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("real_repayment_time >=", value, "realRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentTimeLessThan(LocalDateTime value) {
            addCriterion("real_repayment_time <", value, "realRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("real_repayment_time <=", value, "realRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentTimeIn(List<LocalDateTime> values) {
            addCriterion("real_repayment_time in", values, "realRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentTimeNotIn(List<LocalDateTime> values) {
            addCriterion("real_repayment_time not in", values, "realRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("real_repayment_time between", value1, value2, "realRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andRealRepaymentTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("real_repayment_time not between", value1, value2, "realRepaymentTime");
            return (Criteria) this;
        }

        public Criteria andRepaymentModeIsNull() {
            addCriterion("repayment_mode is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentModeIsNotNull() {
            addCriterion("repayment_mode is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentModeEqualTo(Byte value) {
            addCriterion("repayment_mode =", value, "repaymentMode");
            return (Criteria) this;
        }

        public Criteria andRepaymentModeNotEqualTo(Byte value) {
            addCriterion("repayment_mode <>", value, "repaymentMode");
            return (Criteria) this;
        }

        public Criteria andRepaymentModeGreaterThan(Byte value) {
            addCriterion("repayment_mode >", value, "repaymentMode");
            return (Criteria) this;
        }

        public Criteria andRepaymentModeGreaterThanOrEqualTo(Byte value) {
            addCriterion("repayment_mode >=", value, "repaymentMode");
            return (Criteria) this;
        }

        public Criteria andRepaymentModeLessThan(Byte value) {
            addCriterion("repayment_mode <", value, "repaymentMode");
            return (Criteria) this;
        }

        public Criteria andRepaymentModeLessThanOrEqualTo(Byte value) {
            addCriterion("repayment_mode <=", value, "repaymentMode");
            return (Criteria) this;
        }

        public Criteria andRepaymentModeIn(List<Byte> values) {
            addCriterion("repayment_mode in", values, "repaymentMode");
            return (Criteria) this;
        }

        public Criteria andRepaymentModeNotIn(List<Byte> values) {
            addCriterion("repayment_mode not in", values, "repaymentMode");
            return (Criteria) this;
        }

        public Criteria andRepaymentModeBetween(Byte value1, Byte value2) {
            addCriterion("repayment_mode between", value1, value2, "repaymentMode");
            return (Criteria) this;
        }

        public Criteria andRepaymentModeNotBetween(Byte value1, Byte value2) {
            addCriterion("repayment_mode not between", value1, value2, "repaymentMode");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeIsNull() {
            addCriterion("repayment_type is null");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeIsNotNull() {
            addCriterion("repayment_type is not null");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeEqualTo(Byte value) {
            addCriterion("repayment_type =", value, "repaymentType");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeNotEqualTo(Byte value) {
            addCriterion("repayment_type <>", value, "repaymentType");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeGreaterThan(Byte value) {
            addCriterion("repayment_type >", value, "repaymentType");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("repayment_type >=", value, "repaymentType");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeLessThan(Byte value) {
            addCriterion("repayment_type <", value, "repaymentType");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeLessThanOrEqualTo(Byte value) {
            addCriterion("repayment_type <=", value, "repaymentType");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeIn(List<Byte> values) {
            addCriterion("repayment_type in", values, "repaymentType");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeNotIn(List<Byte> values) {
            addCriterion("repayment_type not in", values, "repaymentType");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeBetween(Byte value1, Byte value2) {
            addCriterion("repayment_type between", value1, value2, "repaymentType");
            return (Criteria) this;
        }

        public Criteria andRepaymentTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("repayment_type not between", value1, value2, "repaymentType");
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

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(LocalDateTime value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(LocalDateTime value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(LocalDateTime value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(LocalDateTime value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<LocalDateTime> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<LocalDateTime> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(LocalDateTime value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(LocalDateTime value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(LocalDateTime value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(LocalDateTime value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<LocalDateTime> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<LocalDateTime> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLikeInsensitive(String value) {
            addCriterion("upper(remark) like", value.toUpperCase(), "remark");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private RepayInfoFlowJobExample example;

        protected Criteria(RepayInfoFlowJobExample example) {
            super();
            this.example = example;
        }

        public RepayInfoFlowJobExample example() {
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