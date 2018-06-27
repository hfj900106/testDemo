package com.hzed.easyget.persistence.auto.entity.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer offset;

    protected Integer rows;

    public ProductExample() {
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

    public ProductExample limit(Integer rows) {
        this.rows = rows;
        return this;
    }

    public ProductExample limit(Integer offset, Integer rows) {
        this.offset = offset;
        this.rows = rows;
        return this;
    }

    public ProductExample page(Integer page, Integer pageSize) {
        this.offset = page * pageSize;
        this.rows = pageSize;
        return this;
    }

    public ProductExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public ProductExample orderBy(String ... orderByClauses) {
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

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andLoanAmountArrIsNull() {
            addCriterion("loan_amount_arr is null");
            return (Criteria) this;
        }

        public Criteria andLoanAmountArrIsNotNull() {
            addCriterion("loan_amount_arr is not null");
            return (Criteria) this;
        }

        public Criteria andLoanAmountArrEqualTo(String value) {
            addCriterion("loan_amount_arr =", value, "loanAmountArr");
            return (Criteria) this;
        }

        public Criteria andLoanAmountArrNotEqualTo(String value) {
            addCriterion("loan_amount_arr <>", value, "loanAmountArr");
            return (Criteria) this;
        }

        public Criteria andLoanAmountArrGreaterThan(String value) {
            addCriterion("loan_amount_arr >", value, "loanAmountArr");
            return (Criteria) this;
        }

        public Criteria andLoanAmountArrGreaterThanOrEqualTo(String value) {
            addCriterion("loan_amount_arr >=", value, "loanAmountArr");
            return (Criteria) this;
        }

        public Criteria andLoanAmountArrLessThan(String value) {
            addCriterion("loan_amount_arr <", value, "loanAmountArr");
            return (Criteria) this;
        }

        public Criteria andLoanAmountArrLessThanOrEqualTo(String value) {
            addCriterion("loan_amount_arr <=", value, "loanAmountArr");
            return (Criteria) this;
        }

        public Criteria andLoanAmountArrLike(String value) {
            addCriterion("loan_amount_arr like", value, "loanAmountArr");
            return (Criteria) this;
        }

        public Criteria andLoanAmountArrNotLike(String value) {
            addCriterion("loan_amount_arr not like", value, "loanAmountArr");
            return (Criteria) this;
        }

        public Criteria andLoanAmountArrIn(List<String> values) {
            addCriterion("loan_amount_arr in", values, "loanAmountArr");
            return (Criteria) this;
        }

        public Criteria andLoanAmountArrNotIn(List<String> values) {
            addCriterion("loan_amount_arr not in", values, "loanAmountArr");
            return (Criteria) this;
        }

        public Criteria andLoanAmountArrBetween(String value1, String value2) {
            addCriterion("loan_amount_arr between", value1, value2, "loanAmountArr");
            return (Criteria) this;
        }

        public Criteria andLoanAmountArrNotBetween(String value1, String value2) {
            addCriterion("loan_amount_arr not between", value1, value2, "loanAmountArr");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNull() {
            addCriterion("currency is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNotNull() {
            addCriterion("currency is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyEqualTo(String value) {
            addCriterion("currency =", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotEqualTo(String value) {
            addCriterion("currency <>", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThan(String value) {
            addCriterion("currency >", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("currency >=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThan(String value) {
            addCriterion("currency <", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThanOrEqualTo(String value) {
            addCriterion("currency <=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLike(String value) {
            addCriterion("currency like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotLike(String value) {
            addCriterion("currency not like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyIn(List<String> values) {
            addCriterion("currency in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotIn(List<String> values) {
            addCriterion("currency not in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyBetween(String value1, String value2) {
            addCriterion("currency between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotBetween(String value1, String value2) {
            addCriterion("currency not between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andLoanTimeArrIsNull() {
            addCriterion("loan_time_arr is null");
            return (Criteria) this;
        }

        public Criteria andLoanTimeArrIsNotNull() {
            addCriterion("loan_time_arr is not null");
            return (Criteria) this;
        }

        public Criteria andLoanTimeArrEqualTo(String value) {
            addCriterion("loan_time_arr =", value, "loanTimeArr");
            return (Criteria) this;
        }

        public Criteria andLoanTimeArrNotEqualTo(String value) {
            addCriterion("loan_time_arr <>", value, "loanTimeArr");
            return (Criteria) this;
        }

        public Criteria andLoanTimeArrGreaterThan(String value) {
            addCriterion("loan_time_arr >", value, "loanTimeArr");
            return (Criteria) this;
        }

        public Criteria andLoanTimeArrGreaterThanOrEqualTo(String value) {
            addCriterion("loan_time_arr >=", value, "loanTimeArr");
            return (Criteria) this;
        }

        public Criteria andLoanTimeArrLessThan(String value) {
            addCriterion("loan_time_arr <", value, "loanTimeArr");
            return (Criteria) this;
        }

        public Criteria andLoanTimeArrLessThanOrEqualTo(String value) {
            addCriterion("loan_time_arr <=", value, "loanTimeArr");
            return (Criteria) this;
        }

        public Criteria andLoanTimeArrLike(String value) {
            addCriterion("loan_time_arr like", value, "loanTimeArr");
            return (Criteria) this;
        }

        public Criteria andLoanTimeArrNotLike(String value) {
            addCriterion("loan_time_arr not like", value, "loanTimeArr");
            return (Criteria) this;
        }

        public Criteria andLoanTimeArrIn(List<String> values) {
            addCriterion("loan_time_arr in", values, "loanTimeArr");
            return (Criteria) this;
        }

        public Criteria andLoanTimeArrNotIn(List<String> values) {
            addCriterion("loan_time_arr not in", values, "loanTimeArr");
            return (Criteria) this;
        }

        public Criteria andLoanTimeArrBetween(String value1, String value2) {
            addCriterion("loan_time_arr between", value1, value2, "loanTimeArr");
            return (Criteria) this;
        }

        public Criteria andLoanTimeArrNotBetween(String value1, String value2) {
            addCriterion("loan_time_arr not between", value1, value2, "loanTimeArr");
            return (Criteria) this;
        }

        public Criteria andLoanTimeDmyIsNull() {
            addCriterion("loan_time_dmy is null");
            return (Criteria) this;
        }

        public Criteria andLoanTimeDmyIsNotNull() {
            addCriterion("loan_time_dmy is not null");
            return (Criteria) this;
        }

        public Criteria andLoanTimeDmyEqualTo(String value) {
            addCriterion("loan_time_dmy =", value, "loanTimeDmy");
            return (Criteria) this;
        }

        public Criteria andLoanTimeDmyNotEqualTo(String value) {
            addCriterion("loan_time_dmy <>", value, "loanTimeDmy");
            return (Criteria) this;
        }

        public Criteria andLoanTimeDmyGreaterThan(String value) {
            addCriterion("loan_time_dmy >", value, "loanTimeDmy");
            return (Criteria) this;
        }

        public Criteria andLoanTimeDmyGreaterThanOrEqualTo(String value) {
            addCriterion("loan_time_dmy >=", value, "loanTimeDmy");
            return (Criteria) this;
        }

        public Criteria andLoanTimeDmyLessThan(String value) {
            addCriterion("loan_time_dmy <", value, "loanTimeDmy");
            return (Criteria) this;
        }

        public Criteria andLoanTimeDmyLessThanOrEqualTo(String value) {
            addCriterion("loan_time_dmy <=", value, "loanTimeDmy");
            return (Criteria) this;
        }

        public Criteria andLoanTimeDmyLike(String value) {
            addCriterion("loan_time_dmy like", value, "loanTimeDmy");
            return (Criteria) this;
        }

        public Criteria andLoanTimeDmyNotLike(String value) {
            addCriterion("loan_time_dmy not like", value, "loanTimeDmy");
            return (Criteria) this;
        }

        public Criteria andLoanTimeDmyIn(List<String> values) {
            addCriterion("loan_time_dmy in", values, "loanTimeDmy");
            return (Criteria) this;
        }

        public Criteria andLoanTimeDmyNotIn(List<String> values) {
            addCriterion("loan_time_dmy not in", values, "loanTimeDmy");
            return (Criteria) this;
        }

        public Criteria andLoanTimeDmyBetween(String value1, String value2) {
            addCriterion("loan_time_dmy between", value1, value2, "loanTimeDmy");
            return (Criteria) this;
        }

        public Criteria andLoanTimeDmyNotBetween(String value1, String value2) {
            addCriterion("loan_time_dmy not between", value1, value2, "loanTimeDmy");
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

        public Criteria andAprEqualTo(Long value) {
            addCriterion("apr =", value, "apr");
            return (Criteria) this;
        }

        public Criteria andAprNotEqualTo(Long value) {
            addCriterion("apr <>", value, "apr");
            return (Criteria) this;
        }

        public Criteria andAprGreaterThan(Long value) {
            addCriterion("apr >", value, "apr");
            return (Criteria) this;
        }

        public Criteria andAprGreaterThanOrEqualTo(Long value) {
            addCriterion("apr >=", value, "apr");
            return (Criteria) this;
        }

        public Criteria andAprLessThan(Long value) {
            addCriterion("apr <", value, "apr");
            return (Criteria) this;
        }

        public Criteria andAprLessThanOrEqualTo(Long value) {
            addCriterion("apr <=", value, "apr");
            return (Criteria) this;
        }

        public Criteria andAprIn(List<Long> values) {
            addCriterion("apr in", values, "apr");
            return (Criteria) this;
        }

        public Criteria andAprNotIn(List<Long> values) {
            addCriterion("apr not in", values, "apr");
            return (Criteria) this;
        }

        public Criteria andAprBetween(Long value1, Long value2) {
            addCriterion("apr between", value1, value2, "apr");
            return (Criteria) this;
        }

        public Criteria andAprNotBetween(Long value1, Long value2) {
            addCriterion("apr not between", value1, value2, "apr");
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

        public Criteria andPictureIsNull() {
            addCriterion("picture is null");
            return (Criteria) this;
        }

        public Criteria andPictureIsNotNull() {
            addCriterion("picture is not null");
            return (Criteria) this;
        }

        public Criteria andPictureEqualTo(String value) {
            addCriterion("picture =", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotEqualTo(String value) {
            addCriterion("picture <>", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureGreaterThan(String value) {
            addCriterion("picture >", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureGreaterThanOrEqualTo(String value) {
            addCriterion("picture >=", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureLessThan(String value) {
            addCriterion("picture <", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureLessThanOrEqualTo(String value) {
            addCriterion("picture <=", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureLike(String value) {
            addCriterion("picture like", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotLike(String value) {
            addCriterion("picture not like", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureIn(List<String> values) {
            addCriterion("picture in", values, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotIn(List<String> values) {
            addCriterion("picture not in", values, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureBetween(String value1, String value2) {
            addCriterion("picture between", value1, value2, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotBetween(String value1, String value2) {
            addCriterion("picture not between", value1, value2, "picture");
            return (Criteria) this;
        }

        public Criteria andIsUseIsNull() {
            addCriterion("is_use is null");
            return (Criteria) this;
        }

        public Criteria andIsUseIsNotNull() {
            addCriterion("is_use is not null");
            return (Criteria) this;
        }

        public Criteria andIsUseEqualTo(Boolean value) {
            addCriterion("is_use =", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotEqualTo(Boolean value) {
            addCriterion("is_use <>", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseGreaterThan(Boolean value) {
            addCriterion("is_use >", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_use >=", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseLessThan(Boolean value) {
            addCriterion("is_use <", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseLessThanOrEqualTo(Boolean value) {
            addCriterion("is_use <=", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseIn(List<Boolean> values) {
            addCriterion("is_use in", values, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotIn(List<Boolean> values) {
            addCriterion("is_use not in", values, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseBetween(Boolean value1, Boolean value2) {
            addCriterion("is_use between", value1, value2, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_use not between", value1, value2, "isUse");
            return (Criteria) this;
        }

        public Criteria andHeadFeeRateIsNull() {
            addCriterion("head_fee_rate is null");
            return (Criteria) this;
        }

        public Criteria andHeadFeeRateIsNotNull() {
            addCriterion("head_fee_rate is not null");
            return (Criteria) this;
        }

        public Criteria andHeadFeeRateEqualTo(BigDecimal value) {
            addCriterion("head_fee_rate =", value, "headFeeRate");
            return (Criteria) this;
        }

        public Criteria andHeadFeeRateNotEqualTo(BigDecimal value) {
            addCriterion("head_fee_rate <>", value, "headFeeRate");
            return (Criteria) this;
        }

        public Criteria andHeadFeeRateGreaterThan(BigDecimal value) {
            addCriterion("head_fee_rate >", value, "headFeeRate");
            return (Criteria) this;
        }

        public Criteria andHeadFeeRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("head_fee_rate >=", value, "headFeeRate");
            return (Criteria) this;
        }

        public Criteria andHeadFeeRateLessThan(BigDecimal value) {
            addCriterion("head_fee_rate <", value, "headFeeRate");
            return (Criteria) this;
        }

        public Criteria andHeadFeeRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("head_fee_rate <=", value, "headFeeRate");
            return (Criteria) this;
        }

        public Criteria andHeadFeeRateIn(List<BigDecimal> values) {
            addCriterion("head_fee_rate in", values, "headFeeRate");
            return (Criteria) this;
        }

        public Criteria andHeadFeeRateNotIn(List<BigDecimal> values) {
            addCriterion("head_fee_rate not in", values, "headFeeRate");
            return (Criteria) this;
        }

        public Criteria andHeadFeeRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("head_fee_rate between", value1, value2, "headFeeRate");
            return (Criteria) this;
        }

        public Criteria andHeadFeeRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("head_fee_rate not between", value1, value2, "headFeeRate");
            return (Criteria) this;
        }

        public Criteria andTailFeeRateIsNull() {
            addCriterion("tail_fee_rate is null");
            return (Criteria) this;
        }

        public Criteria andTailFeeRateIsNotNull() {
            addCriterion("tail_fee_rate is not null");
            return (Criteria) this;
        }

        public Criteria andTailFeeRateEqualTo(BigDecimal value) {
            addCriterion("tail_fee_rate =", value, "tailFeeRate");
            return (Criteria) this;
        }

        public Criteria andTailFeeRateNotEqualTo(BigDecimal value) {
            addCriterion("tail_fee_rate <>", value, "tailFeeRate");
            return (Criteria) this;
        }

        public Criteria andTailFeeRateGreaterThan(BigDecimal value) {
            addCriterion("tail_fee_rate >", value, "tailFeeRate");
            return (Criteria) this;
        }

        public Criteria andTailFeeRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("tail_fee_rate >=", value, "tailFeeRate");
            return (Criteria) this;
        }

        public Criteria andTailFeeRateLessThan(BigDecimal value) {
            addCriterion("tail_fee_rate <", value, "tailFeeRate");
            return (Criteria) this;
        }

        public Criteria andTailFeeRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("tail_fee_rate <=", value, "tailFeeRate");
            return (Criteria) this;
        }

        public Criteria andTailFeeRateIn(List<BigDecimal> values) {
            addCriterion("tail_fee_rate in", values, "tailFeeRate");
            return (Criteria) this;
        }

        public Criteria andTailFeeRateNotIn(List<BigDecimal> values) {
            addCriterion("tail_fee_rate not in", values, "tailFeeRate");
            return (Criteria) this;
        }

        public Criteria andTailFeeRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tail_fee_rate between", value1, value2, "tailFeeRate");
            return (Criteria) this;
        }

        public Criteria andTailFeeRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tail_fee_rate not between", value1, value2, "tailFeeRate");
            return (Criteria) this;
        }

        public Criteria andOverdueFeeRateIsNull() {
            addCriterion("overdue_fee_rate is null");
            return (Criteria) this;
        }

        public Criteria andOverdueFeeRateIsNotNull() {
            addCriterion("overdue_fee_rate is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueFeeRateEqualTo(BigDecimal value) {
            addCriterion("overdue_fee_rate =", value, "overdueFeeRate");
            return (Criteria) this;
        }

        public Criteria andOverdueFeeRateNotEqualTo(BigDecimal value) {
            addCriterion("overdue_fee_rate <>", value, "overdueFeeRate");
            return (Criteria) this;
        }

        public Criteria andOverdueFeeRateGreaterThan(BigDecimal value) {
            addCriterion("overdue_fee_rate >", value, "overdueFeeRate");
            return (Criteria) this;
        }

        public Criteria andOverdueFeeRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("overdue_fee_rate >=", value, "overdueFeeRate");
            return (Criteria) this;
        }

        public Criteria andOverdueFeeRateLessThan(BigDecimal value) {
            addCriterion("overdue_fee_rate <", value, "overdueFeeRate");
            return (Criteria) this;
        }

        public Criteria andOverdueFeeRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("overdue_fee_rate <=", value, "overdueFeeRate");
            return (Criteria) this;
        }

        public Criteria andOverdueFeeRateIn(List<BigDecimal> values) {
            addCriterion("overdue_fee_rate in", values, "overdueFeeRate");
            return (Criteria) this;
        }

        public Criteria andOverdueFeeRateNotIn(List<BigDecimal> values) {
            addCriterion("overdue_fee_rate not in", values, "overdueFeeRate");
            return (Criteria) this;
        }

        public Criteria andOverdueFeeRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("overdue_fee_rate between", value1, value2, "overdueFeeRate");
            return (Criteria) this;
        }

        public Criteria andOverdueFeeRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("overdue_fee_rate not between", value1, value2, "overdueFeeRate");
            return (Criteria) this;
        }

        public Criteria andMinRepayAmountIsNull() {
            addCriterion("min_repay_amount is null");
            return (Criteria) this;
        }

        public Criteria andMinRepayAmountIsNotNull() {
            addCriterion("min_repay_amount is not null");
            return (Criteria) this;
        }

        public Criteria andMinRepayAmountEqualTo(BigDecimal value) {
            addCriterion("min_repay_amount =", value, "minRepayAmount");
            return (Criteria) this;
        }

        public Criteria andMinRepayAmountNotEqualTo(BigDecimal value) {
            addCriterion("min_repay_amount <>", value, "minRepayAmount");
            return (Criteria) this;
        }

        public Criteria andMinRepayAmountGreaterThan(BigDecimal value) {
            addCriterion("min_repay_amount >", value, "minRepayAmount");
            return (Criteria) this;
        }

        public Criteria andMinRepayAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("min_repay_amount >=", value, "minRepayAmount");
            return (Criteria) this;
        }

        public Criteria andMinRepayAmountLessThan(BigDecimal value) {
            addCriterion("min_repay_amount <", value, "minRepayAmount");
            return (Criteria) this;
        }

        public Criteria andMinRepayAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("min_repay_amount <=", value, "minRepayAmount");
            return (Criteria) this;
        }

        public Criteria andMinRepayAmountIn(List<BigDecimal> values) {
            addCriterion("min_repay_amount in", values, "minRepayAmount");
            return (Criteria) this;
        }

        public Criteria andMinRepayAmountNotIn(List<BigDecimal> values) {
            addCriterion("min_repay_amount not in", values, "minRepayAmount");
            return (Criteria) this;
        }

        public Criteria andMinRepayAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_repay_amount between", value1, value2, "minRepayAmount");
            return (Criteria) this;
        }

        public Criteria andMinRepayAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_repay_amount not between", value1, value2, "minRepayAmount");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(Long value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(Long value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(Long value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(Long value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(Long value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(Long value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<Long> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<Long> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(Long value1, Long value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(Long value1, Long value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
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

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(Long value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(Long value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(Long value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(Long value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(Long value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(Long value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<Long> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<Long> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(Long value1, Long value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(Long value1, Long value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
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

        public Criteria andCodeLikeInsensitive(String value) {
            addCriterion("upper(code) like", value.toUpperCase(), "code");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andLoanAmountArrLikeInsensitive(String value) {
            addCriterion("upper(loan_amount_arr) like", value.toUpperCase(), "loanAmountArr");
            return (Criteria) this;
        }

        public Criteria andCurrencyLikeInsensitive(String value) {
            addCriterion("upper(currency) like", value.toUpperCase(), "currency");
            return (Criteria) this;
        }

        public Criteria andLoanTimeArrLikeInsensitive(String value) {
            addCriterion("upper(loan_time_arr) like", value.toUpperCase(), "loanTimeArr");
            return (Criteria) this;
        }

        public Criteria andLoanTimeDmyLikeInsensitive(String value) {
            addCriterion("upper(loan_time_dmy) like", value.toUpperCase(), "loanTimeDmy");
            return (Criteria) this;
        }

        public Criteria andDescriptionLikeInsensitive(String value) {
            addCriterion("upper(description) like", value.toUpperCase(), "description");
            return (Criteria) this;
        }

        public Criteria andPictureLikeInsensitive(String value) {
            addCriterion("upper(picture) like", value.toUpperCase(), "picture");
            return (Criteria) this;
        }

        public Criteria andRemarkLikeInsensitive(String value) {
            addCriterion("upper(remark) like", value.toUpperCase(), "remark");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private ProductExample example;

        protected Criteria(ProductExample example) {
            super();
            this.example = example;
        }

        public ProductExample example() {
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