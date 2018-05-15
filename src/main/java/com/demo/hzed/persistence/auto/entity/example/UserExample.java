package com.demo.hzed.persistence.auto.entity.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer offset;

    protected Integer rows;

    public UserExample() {
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

    public UserExample limit(Integer rows) {
        this.rows = rows;
        return this;
    }

    public UserExample limit(Integer offset, Integer rows) {
        this.offset = offset;
        this.rows = rows;
        return this;
    }

    public UserExample page(Integer page, Integer pageSize) {
        this.offset = page * pageSize;
        this.rows = pageSize;
        return this;
    }

    public UserExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public UserExample orderBy(String ... orderByClauses) {
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

        public Criteria andClientIsNull() {
            addCriterion("client is null");
            return (Criteria) this;
        }

        public Criteria andClientIsNotNull() {
            addCriterion("client is not null");
            return (Criteria) this;
        }

        public Criteria andClientEqualTo(Integer value) {
            addCriterion("client =", value, "client");
            return (Criteria) this;
        }

        public Criteria andClientNotEqualTo(Integer value) {
            addCriterion("client <>", value, "client");
            return (Criteria) this;
        }

        public Criteria andClientGreaterThan(Integer value) {
            addCriterion("client >", value, "client");
            return (Criteria) this;
        }

        public Criteria andClientGreaterThanOrEqualTo(Integer value) {
            addCriterion("client >=", value, "client");
            return (Criteria) this;
        }

        public Criteria andClientLessThan(Integer value) {
            addCriterion("client <", value, "client");
            return (Criteria) this;
        }

        public Criteria andClientLessThanOrEqualTo(Integer value) {
            addCriterion("client <=", value, "client");
            return (Criteria) this;
        }

        public Criteria andClientIn(List<Integer> values) {
            addCriterion("client in", values, "client");
            return (Criteria) this;
        }

        public Criteria andClientNotIn(List<Integer> values) {
            addCriterion("client not in", values, "client");
            return (Criteria) this;
        }

        public Criteria andClientBetween(Integer value1, Integer value2) {
            addCriterion("client between", value1, value2, "client");
            return (Criteria) this;
        }

        public Criteria andClientNotBetween(Integer value1, Integer value2) {
            addCriterion("client not between", value1, value2, "client");
            return (Criteria) this;
        }

        public Criteria andPhotoIsNull() {
            addCriterion("photo is null");
            return (Criteria) this;
        }

        public Criteria andPhotoIsNotNull() {
            addCriterion("photo is not null");
            return (Criteria) this;
        }

        public Criteria andPhotoEqualTo(String value) {
            addCriterion("photo =", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotEqualTo(String value) {
            addCriterion("photo <>", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoGreaterThan(String value) {
            addCriterion("photo >", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("photo >=", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLessThan(String value) {
            addCriterion("photo <", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLessThanOrEqualTo(String value) {
            addCriterion("photo <=", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoLike(String value) {
            addCriterion("photo like", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotLike(String value) {
            addCriterion("photo not like", value, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoIn(List<String> values) {
            addCriterion("photo in", values, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotIn(List<String> values) {
            addCriterion("photo not in", values, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoBetween(String value1, String value2) {
            addCriterion("photo between", value1, value2, "photo");
            return (Criteria) this;
        }

        public Criteria andPhotoNotBetween(String value1, String value2) {
            addCriterion("photo not between", value1, value2, "photo");
            return (Criteria) this;
        }

        public Criteria andRealityNameIsNull() {
            addCriterion("reality_name is null");
            return (Criteria) this;
        }

        public Criteria andRealityNameIsNotNull() {
            addCriterion("reality_name is not null");
            return (Criteria) this;
        }

        public Criteria andRealityNameEqualTo(String value) {
            addCriterion("reality_name =", value, "realityName");
            return (Criteria) this;
        }

        public Criteria andRealityNameNotEqualTo(String value) {
            addCriterion("reality_name <>", value, "realityName");
            return (Criteria) this;
        }

        public Criteria andRealityNameGreaterThan(String value) {
            addCriterion("reality_name >", value, "realityName");
            return (Criteria) this;
        }

        public Criteria andRealityNameGreaterThanOrEqualTo(String value) {
            addCriterion("reality_name >=", value, "realityName");
            return (Criteria) this;
        }

        public Criteria andRealityNameLessThan(String value) {
            addCriterion("reality_name <", value, "realityName");
            return (Criteria) this;
        }

        public Criteria andRealityNameLessThanOrEqualTo(String value) {
            addCriterion("reality_name <=", value, "realityName");
            return (Criteria) this;
        }

        public Criteria andRealityNameLike(String value) {
            addCriterion("reality_name like", value, "realityName");
            return (Criteria) this;
        }

        public Criteria andRealityNameNotLike(String value) {
            addCriterion("reality_name not like", value, "realityName");
            return (Criteria) this;
        }

        public Criteria andRealityNameIn(List<String> values) {
            addCriterion("reality_name in", values, "realityName");
            return (Criteria) this;
        }

        public Criteria andRealityNameNotIn(List<String> values) {
            addCriterion("reality_name not in", values, "realityName");
            return (Criteria) this;
        }

        public Criteria andRealityNameBetween(String value1, String value2) {
            addCriterion("reality_name between", value1, value2, "realityName");
            return (Criteria) this;
        }

        public Criteria andRealityNameNotBetween(String value1, String value2) {
            addCriterion("reality_name not between", value1, value2, "realityName");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordContinuousErrorsIsNull() {
            addCriterion("password_continuous_errors is null");
            return (Criteria) this;
        }

        public Criteria andPasswordContinuousErrorsIsNotNull() {
            addCriterion("password_continuous_errors is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordContinuousErrorsEqualTo(Integer value) {
            addCriterion("password_continuous_errors =", value, "passwordContinuousErrors");
            return (Criteria) this;
        }

        public Criteria andPasswordContinuousErrorsNotEqualTo(Integer value) {
            addCriterion("password_continuous_errors <>", value, "passwordContinuousErrors");
            return (Criteria) this;
        }

        public Criteria andPasswordContinuousErrorsGreaterThan(Integer value) {
            addCriterion("password_continuous_errors >", value, "passwordContinuousErrors");
            return (Criteria) this;
        }

        public Criteria andPasswordContinuousErrorsGreaterThanOrEqualTo(Integer value) {
            addCriterion("password_continuous_errors >=", value, "passwordContinuousErrors");
            return (Criteria) this;
        }

        public Criteria andPasswordContinuousErrorsLessThan(Integer value) {
            addCriterion("password_continuous_errors <", value, "passwordContinuousErrors");
            return (Criteria) this;
        }

        public Criteria andPasswordContinuousErrorsLessThanOrEqualTo(Integer value) {
            addCriterion("password_continuous_errors <=", value, "passwordContinuousErrors");
            return (Criteria) this;
        }

        public Criteria andPasswordContinuousErrorsIn(List<Integer> values) {
            addCriterion("password_continuous_errors in", values, "passwordContinuousErrors");
            return (Criteria) this;
        }

        public Criteria andPasswordContinuousErrorsNotIn(List<Integer> values) {
            addCriterion("password_continuous_errors not in", values, "passwordContinuousErrors");
            return (Criteria) this;
        }

        public Criteria andPasswordContinuousErrorsBetween(Integer value1, Integer value2) {
            addCriterion("password_continuous_errors between", value1, value2, "passwordContinuousErrors");
            return (Criteria) this;
        }

        public Criteria andPasswordContinuousErrorsNotBetween(Integer value1, Integer value2) {
            addCriterion("password_continuous_errors not between", value1, value2, "passwordContinuousErrors");
            return (Criteria) this;
        }

        public Criteria andIsPasswordErrorLockedIsNull() {
            addCriterion("is_password_error_locked is null");
            return (Criteria) this;
        }

        public Criteria andIsPasswordErrorLockedIsNotNull() {
            addCriterion("is_password_error_locked is not null");
            return (Criteria) this;
        }

        public Criteria andIsPasswordErrorLockedEqualTo(Boolean value) {
            addCriterion("is_password_error_locked =", value, "isPasswordErrorLocked");
            return (Criteria) this;
        }

        public Criteria andIsPasswordErrorLockedNotEqualTo(Boolean value) {
            addCriterion("is_password_error_locked <>", value, "isPasswordErrorLocked");
            return (Criteria) this;
        }

        public Criteria andIsPasswordErrorLockedGreaterThan(Boolean value) {
            addCriterion("is_password_error_locked >", value, "isPasswordErrorLocked");
            return (Criteria) this;
        }

        public Criteria andIsPasswordErrorLockedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_password_error_locked >=", value, "isPasswordErrorLocked");
            return (Criteria) this;
        }

        public Criteria andIsPasswordErrorLockedLessThan(Boolean value) {
            addCriterion("is_password_error_locked <", value, "isPasswordErrorLocked");
            return (Criteria) this;
        }

        public Criteria andIsPasswordErrorLockedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_password_error_locked <=", value, "isPasswordErrorLocked");
            return (Criteria) this;
        }

        public Criteria andIsPasswordErrorLockedIn(List<Boolean> values) {
            addCriterion("is_password_error_locked in", values, "isPasswordErrorLocked");
            return (Criteria) this;
        }

        public Criteria andIsPasswordErrorLockedNotIn(List<Boolean> values) {
            addCriterion("is_password_error_locked not in", values, "isPasswordErrorLocked");
            return (Criteria) this;
        }

        public Criteria andIsPasswordErrorLockedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_password_error_locked between", value1, value2, "isPasswordErrorLocked");
            return (Criteria) this;
        }

        public Criteria andIsPasswordErrorLockedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_password_error_locked not between", value1, value2, "isPasswordErrorLocked");
            return (Criteria) this;
        }

        public Criteria andPasswordErrorLockedTimeIsNull() {
            addCriterion("password_error_locked_time is null");
            return (Criteria) this;
        }

        public Criteria andPasswordErrorLockedTimeIsNotNull() {
            addCriterion("password_error_locked_time is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordErrorLockedTimeEqualTo(Date value) {
            addCriterion("password_error_locked_time =", value, "passwordErrorLockedTime");
            return (Criteria) this;
        }

        public Criteria andPasswordErrorLockedTimeNotEqualTo(Date value) {
            addCriterion("password_error_locked_time <>", value, "passwordErrorLockedTime");
            return (Criteria) this;
        }

        public Criteria andPasswordErrorLockedTimeGreaterThan(Date value) {
            addCriterion("password_error_locked_time >", value, "passwordErrorLockedTime");
            return (Criteria) this;
        }

        public Criteria andPasswordErrorLockedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("password_error_locked_time >=", value, "passwordErrorLockedTime");
            return (Criteria) this;
        }

        public Criteria andPasswordErrorLockedTimeLessThan(Date value) {
            addCriterion("password_error_locked_time <", value, "passwordErrorLockedTime");
            return (Criteria) this;
        }

        public Criteria andPasswordErrorLockedTimeLessThanOrEqualTo(Date value) {
            addCriterion("password_error_locked_time <=", value, "passwordErrorLockedTime");
            return (Criteria) this;
        }

        public Criteria andPasswordErrorLockedTimeIn(List<Date> values) {
            addCriterion("password_error_locked_time in", values, "passwordErrorLockedTime");
            return (Criteria) this;
        }

        public Criteria andPasswordErrorLockedTimeNotIn(List<Date> values) {
            addCriterion("password_error_locked_time not in", values, "passwordErrorLockedTime");
            return (Criteria) this;
        }

        public Criteria andPasswordErrorLockedTimeBetween(Date value1, Date value2) {
            addCriterion("password_error_locked_time between", value1, value2, "passwordErrorLockedTime");
            return (Criteria) this;
        }

        public Criteria andPasswordErrorLockedTimeNotBetween(Date value1, Date value2) {
            addCriterion("password_error_locked_time not between", value1, value2, "passwordErrorLockedTime");
            return (Criteria) this;
        }

        public Criteria andPayPasswordIsNull() {
            addCriterion("pay_password is null");
            return (Criteria) this;
        }

        public Criteria andPayPasswordIsNotNull() {
            addCriterion("pay_password is not null");
            return (Criteria) this;
        }

        public Criteria andPayPasswordEqualTo(String value) {
            addCriterion("pay_password =", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordNotEqualTo(String value) {
            addCriterion("pay_password <>", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordGreaterThan(String value) {
            addCriterion("pay_password >", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("pay_password >=", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordLessThan(String value) {
            addCriterion("pay_password <", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordLessThanOrEqualTo(String value) {
            addCriterion("pay_password <=", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordLike(String value) {
            addCriterion("pay_password like", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordNotLike(String value) {
            addCriterion("pay_password not like", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordIn(List<String> values) {
            addCriterion("pay_password in", values, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordNotIn(List<String> values) {
            addCriterion("pay_password not in", values, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordBetween(String value1, String value2) {
            addCriterion("pay_password between", value1, value2, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordNotBetween(String value1, String value2) {
            addCriterion("pay_password not between", value1, value2, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordContinuousErrorsIsNull() {
            addCriterion("pay_password_continuous_errors is null");
            return (Criteria) this;
        }

        public Criteria andPayPasswordContinuousErrorsIsNotNull() {
            addCriterion("pay_password_continuous_errors is not null");
            return (Criteria) this;
        }

        public Criteria andPayPasswordContinuousErrorsEqualTo(Integer value) {
            addCriterion("pay_password_continuous_errors =", value, "payPasswordContinuousErrors");
            return (Criteria) this;
        }

        public Criteria andPayPasswordContinuousErrorsNotEqualTo(Integer value) {
            addCriterion("pay_password_continuous_errors <>", value, "payPasswordContinuousErrors");
            return (Criteria) this;
        }

        public Criteria andPayPasswordContinuousErrorsGreaterThan(Integer value) {
            addCriterion("pay_password_continuous_errors >", value, "payPasswordContinuousErrors");
            return (Criteria) this;
        }

        public Criteria andPayPasswordContinuousErrorsGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_password_continuous_errors >=", value, "payPasswordContinuousErrors");
            return (Criteria) this;
        }

        public Criteria andPayPasswordContinuousErrorsLessThan(Integer value) {
            addCriterion("pay_password_continuous_errors <", value, "payPasswordContinuousErrors");
            return (Criteria) this;
        }

        public Criteria andPayPasswordContinuousErrorsLessThanOrEqualTo(Integer value) {
            addCriterion("pay_password_continuous_errors <=", value, "payPasswordContinuousErrors");
            return (Criteria) this;
        }

        public Criteria andPayPasswordContinuousErrorsIn(List<Integer> values) {
            addCriterion("pay_password_continuous_errors in", values, "payPasswordContinuousErrors");
            return (Criteria) this;
        }

        public Criteria andPayPasswordContinuousErrorsNotIn(List<Integer> values) {
            addCriterion("pay_password_continuous_errors not in", values, "payPasswordContinuousErrors");
            return (Criteria) this;
        }

        public Criteria andPayPasswordContinuousErrorsBetween(Integer value1, Integer value2) {
            addCriterion("pay_password_continuous_errors between", value1, value2, "payPasswordContinuousErrors");
            return (Criteria) this;
        }

        public Criteria andPayPasswordContinuousErrorsNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_password_continuous_errors not between", value1, value2, "payPasswordContinuousErrors");
            return (Criteria) this;
        }

        public Criteria andIsPayPasswordErrorLockedIsNull() {
            addCriterion("is_pay_password_error_locked is null");
            return (Criteria) this;
        }

        public Criteria andIsPayPasswordErrorLockedIsNotNull() {
            addCriterion("is_pay_password_error_locked is not null");
            return (Criteria) this;
        }

        public Criteria andIsPayPasswordErrorLockedEqualTo(Boolean value) {
            addCriterion("is_pay_password_error_locked =", value, "isPayPasswordErrorLocked");
            return (Criteria) this;
        }

        public Criteria andIsPayPasswordErrorLockedNotEqualTo(Boolean value) {
            addCriterion("is_pay_password_error_locked <>", value, "isPayPasswordErrorLocked");
            return (Criteria) this;
        }

        public Criteria andIsPayPasswordErrorLockedGreaterThan(Boolean value) {
            addCriterion("is_pay_password_error_locked >", value, "isPayPasswordErrorLocked");
            return (Criteria) this;
        }

        public Criteria andIsPayPasswordErrorLockedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_pay_password_error_locked >=", value, "isPayPasswordErrorLocked");
            return (Criteria) this;
        }

        public Criteria andIsPayPasswordErrorLockedLessThan(Boolean value) {
            addCriterion("is_pay_password_error_locked <", value, "isPayPasswordErrorLocked");
            return (Criteria) this;
        }

        public Criteria andIsPayPasswordErrorLockedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_pay_password_error_locked <=", value, "isPayPasswordErrorLocked");
            return (Criteria) this;
        }

        public Criteria andIsPayPasswordErrorLockedIn(List<Boolean> values) {
            addCriterion("is_pay_password_error_locked in", values, "isPayPasswordErrorLocked");
            return (Criteria) this;
        }

        public Criteria andIsPayPasswordErrorLockedNotIn(List<Boolean> values) {
            addCriterion("is_pay_password_error_locked not in", values, "isPayPasswordErrorLocked");
            return (Criteria) this;
        }

        public Criteria andIsPayPasswordErrorLockedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_pay_password_error_locked between", value1, value2, "isPayPasswordErrorLocked");
            return (Criteria) this;
        }

        public Criteria andIsPayPasswordErrorLockedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_pay_password_error_locked not between", value1, value2, "isPayPasswordErrorLocked");
            return (Criteria) this;
        }

        public Criteria andPayPasswordErrorLockedTimeIsNull() {
            addCriterion("pay_password_error_locked_time is null");
            return (Criteria) this;
        }

        public Criteria andPayPasswordErrorLockedTimeIsNotNull() {
            addCriterion("pay_password_error_locked_time is not null");
            return (Criteria) this;
        }

        public Criteria andPayPasswordErrorLockedTimeEqualTo(Date value) {
            addCriterion("pay_password_error_locked_time =", value, "payPasswordErrorLockedTime");
            return (Criteria) this;
        }

        public Criteria andPayPasswordErrorLockedTimeNotEqualTo(Date value) {
            addCriterion("pay_password_error_locked_time <>", value, "payPasswordErrorLockedTime");
            return (Criteria) this;
        }

        public Criteria andPayPasswordErrorLockedTimeGreaterThan(Date value) {
            addCriterion("pay_password_error_locked_time >", value, "payPasswordErrorLockedTime");
            return (Criteria) this;
        }

        public Criteria andPayPasswordErrorLockedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_password_error_locked_time >=", value, "payPasswordErrorLockedTime");
            return (Criteria) this;
        }

        public Criteria andPayPasswordErrorLockedTimeLessThan(Date value) {
            addCriterion("pay_password_error_locked_time <", value, "payPasswordErrorLockedTime");
            return (Criteria) this;
        }

        public Criteria andPayPasswordErrorLockedTimeLessThanOrEqualTo(Date value) {
            addCriterion("pay_password_error_locked_time <=", value, "payPasswordErrorLockedTime");
            return (Criteria) this;
        }

        public Criteria andPayPasswordErrorLockedTimeIn(List<Date> values) {
            addCriterion("pay_password_error_locked_time in", values, "payPasswordErrorLockedTime");
            return (Criteria) this;
        }

        public Criteria andPayPasswordErrorLockedTimeNotIn(List<Date> values) {
            addCriterion("pay_password_error_locked_time not in", values, "payPasswordErrorLockedTime");
            return (Criteria) this;
        }

        public Criteria andPayPasswordErrorLockedTimeBetween(Date value1, Date value2) {
            addCriterion("pay_password_error_locked_time between", value1, value2, "payPasswordErrorLockedTime");
            return (Criteria) this;
        }

        public Criteria andPayPasswordErrorLockedTimeNotBetween(Date value1, Date value2) {
            addCriterion("pay_password_error_locked_time not between", value1, value2, "payPasswordErrorLockedTime");
            return (Criteria) this;
        }

        public Criteria andIsAllowLoginIsNull() {
            addCriterion("is_allow_login is null");
            return (Criteria) this;
        }

        public Criteria andIsAllowLoginIsNotNull() {
            addCriterion("is_allow_login is not null");
            return (Criteria) this;
        }

        public Criteria andIsAllowLoginEqualTo(Boolean value) {
            addCriterion("is_allow_login =", value, "isAllowLogin");
            return (Criteria) this;
        }

        public Criteria andIsAllowLoginNotEqualTo(Boolean value) {
            addCriterion("is_allow_login <>", value, "isAllowLogin");
            return (Criteria) this;
        }

        public Criteria andIsAllowLoginGreaterThan(Boolean value) {
            addCriterion("is_allow_login >", value, "isAllowLogin");
            return (Criteria) this;
        }

        public Criteria andIsAllowLoginGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_allow_login >=", value, "isAllowLogin");
            return (Criteria) this;
        }

        public Criteria andIsAllowLoginLessThan(Boolean value) {
            addCriterion("is_allow_login <", value, "isAllowLogin");
            return (Criteria) this;
        }

        public Criteria andIsAllowLoginLessThanOrEqualTo(Boolean value) {
            addCriterion("is_allow_login <=", value, "isAllowLogin");
            return (Criteria) this;
        }

        public Criteria andIsAllowLoginIn(List<Boolean> values) {
            addCriterion("is_allow_login in", values, "isAllowLogin");
            return (Criteria) this;
        }

        public Criteria andIsAllowLoginNotIn(List<Boolean> values) {
            addCriterion("is_allow_login not in", values, "isAllowLogin");
            return (Criteria) this;
        }

        public Criteria andIsAllowLoginBetween(Boolean value1, Boolean value2) {
            addCriterion("is_allow_login between", value1, value2, "isAllowLogin");
            return (Criteria) this;
        }

        public Criteria andIsAllowLoginNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_allow_login not between", value1, value2, "isAllowLogin");
            return (Criteria) this;
        }

        public Criteria andLockTimeIsNull() {
            addCriterion("lock_time is null");
            return (Criteria) this;
        }

        public Criteria andLockTimeIsNotNull() {
            addCriterion("lock_time is not null");
            return (Criteria) this;
        }

        public Criteria andLockTimeEqualTo(Date value) {
            addCriterion("lock_time =", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeNotEqualTo(Date value) {
            addCriterion("lock_time <>", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeGreaterThan(Date value) {
            addCriterion("lock_time >", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("lock_time >=", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeLessThan(Date value) {
            addCriterion("lock_time <", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeLessThanOrEqualTo(Date value) {
            addCriterion("lock_time <=", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeIn(List<Date> values) {
            addCriterion("lock_time in", values, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeNotIn(List<Date> values) {
            addCriterion("lock_time not in", values, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeBetween(Date value1, Date value2) {
            addCriterion("lock_time between", value1, value2, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeNotBetween(Date value1, Date value2) {
            addCriterion("lock_time not between", value1, value2, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLoginCountIsNull() {
            addCriterion("login_count is null");
            return (Criteria) this;
        }

        public Criteria andLoginCountIsNotNull() {
            addCriterion("login_count is not null");
            return (Criteria) this;
        }

        public Criteria andLoginCountEqualTo(Long value) {
            addCriterion("login_count =", value, "loginCount");
            return (Criteria) this;
        }

        public Criteria andLoginCountNotEqualTo(Long value) {
            addCriterion("login_count <>", value, "loginCount");
            return (Criteria) this;
        }

        public Criteria andLoginCountGreaterThan(Long value) {
            addCriterion("login_count >", value, "loginCount");
            return (Criteria) this;
        }

        public Criteria andLoginCountGreaterThanOrEqualTo(Long value) {
            addCriterion("login_count >=", value, "loginCount");
            return (Criteria) this;
        }

        public Criteria andLoginCountLessThan(Long value) {
            addCriterion("login_count <", value, "loginCount");
            return (Criteria) this;
        }

        public Criteria andLoginCountLessThanOrEqualTo(Long value) {
            addCriterion("login_count <=", value, "loginCount");
            return (Criteria) this;
        }

        public Criteria andLoginCountIn(List<Long> values) {
            addCriterion("login_count in", values, "loginCount");
            return (Criteria) this;
        }

        public Criteria andLoginCountNotIn(List<Long> values) {
            addCriterion("login_count not in", values, "loginCount");
            return (Criteria) this;
        }

        public Criteria andLoginCountBetween(Long value1, Long value2) {
            addCriterion("login_count between", value1, value2, "loginCount");
            return (Criteria) this;
        }

        public Criteria andLoginCountNotBetween(Long value1, Long value2) {
            addCriterion("login_count not between", value1, value2, "loginCount");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeIsNull() {
            addCriterion("last_login_time is null");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeIsNotNull() {
            addCriterion("last_login_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeEqualTo(Date value) {
            addCriterion("last_login_time =", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeNotEqualTo(Date value) {
            addCriterion("last_login_time <>", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeGreaterThan(Date value) {
            addCriterion("last_login_time >", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_login_time >=", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeLessThan(Date value) {
            addCriterion("last_login_time <", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_login_time <=", value, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeIn(List<Date> values) {
            addCriterion("last_login_time in", values, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeNotIn(List<Date> values) {
            addCriterion("last_login_time not in", values, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeBetween(Date value1, Date value2) {
            addCriterion("last_login_time between", value1, value2, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_login_time not between", value1, value2, "lastLoginTime");
            return (Criteria) this;
        }

        public Criteria andLoginClientIsNull() {
            addCriterion("login_client is null");
            return (Criteria) this;
        }

        public Criteria andLoginClientIsNotNull() {
            addCriterion("login_client is not null");
            return (Criteria) this;
        }

        public Criteria andLoginClientEqualTo(Byte value) {
            addCriterion("login_client =", value, "loginClient");
            return (Criteria) this;
        }

        public Criteria andLoginClientNotEqualTo(Byte value) {
            addCriterion("login_client <>", value, "loginClient");
            return (Criteria) this;
        }

        public Criteria andLoginClientGreaterThan(Byte value) {
            addCriterion("login_client >", value, "loginClient");
            return (Criteria) this;
        }

        public Criteria andLoginClientGreaterThanOrEqualTo(Byte value) {
            addCriterion("login_client >=", value, "loginClient");
            return (Criteria) this;
        }

        public Criteria andLoginClientLessThan(Byte value) {
            addCriterion("login_client <", value, "loginClient");
            return (Criteria) this;
        }

        public Criteria andLoginClientLessThanOrEqualTo(Byte value) {
            addCriterion("login_client <=", value, "loginClient");
            return (Criteria) this;
        }

        public Criteria andLoginClientIn(List<Byte> values) {
            addCriterion("login_client in", values, "loginClient");
            return (Criteria) this;
        }

        public Criteria andLoginClientNotIn(List<Byte> values) {
            addCriterion("login_client not in", values, "loginClient");
            return (Criteria) this;
        }

        public Criteria andLoginClientBetween(Byte value1, Byte value2) {
            addCriterion("login_client between", value1, value2, "loginClient");
            return (Criteria) this;
        }

        public Criteria andLoginClientNotBetween(Byte value1, Byte value2) {
            addCriterion("login_client not between", value1, value2, "loginClient");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpIsNull() {
            addCriterion("last_login_ip is null");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpIsNotNull() {
            addCriterion("last_login_ip is not null");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpEqualTo(String value) {
            addCriterion("last_login_ip =", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpNotEqualTo(String value) {
            addCriterion("last_login_ip <>", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpGreaterThan(String value) {
            addCriterion("last_login_ip >", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpGreaterThanOrEqualTo(String value) {
            addCriterion("last_login_ip >=", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpLessThan(String value) {
            addCriterion("last_login_ip <", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpLessThanOrEqualTo(String value) {
            addCriterion("last_login_ip <=", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpLike(String value) {
            addCriterion("last_login_ip like", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpNotLike(String value) {
            addCriterion("last_login_ip not like", value, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpIn(List<String> values) {
            addCriterion("last_login_ip in", values, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpNotIn(List<String> values) {
            addCriterion("last_login_ip not in", values, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpBetween(String value1, String value2) {
            addCriterion("last_login_ip between", value1, value2, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpNotBetween(String value1, String value2) {
            addCriterion("last_login_ip not between", value1, value2, "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andLastLogoutTimeIsNull() {
            addCriterion("last_logout_time is null");
            return (Criteria) this;
        }

        public Criteria andLastLogoutTimeIsNotNull() {
            addCriterion("last_logout_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastLogoutTimeEqualTo(Date value) {
            addCriterion("last_logout_time =", value, "lastLogoutTime");
            return (Criteria) this;
        }

        public Criteria andLastLogoutTimeNotEqualTo(Date value) {
            addCriterion("last_logout_time <>", value, "lastLogoutTime");
            return (Criteria) this;
        }

        public Criteria andLastLogoutTimeGreaterThan(Date value) {
            addCriterion("last_logout_time >", value, "lastLogoutTime");
            return (Criteria) this;
        }

        public Criteria andLastLogoutTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_logout_time >=", value, "lastLogoutTime");
            return (Criteria) this;
        }

        public Criteria andLastLogoutTimeLessThan(Date value) {
            addCriterion("last_logout_time <", value, "lastLogoutTime");
            return (Criteria) this;
        }

        public Criteria andLastLogoutTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_logout_time <=", value, "lastLogoutTime");
            return (Criteria) this;
        }

        public Criteria andLastLogoutTimeIn(List<Date> values) {
            addCriterion("last_logout_time in", values, "lastLogoutTime");
            return (Criteria) this;
        }

        public Criteria andLastLogoutTimeNotIn(List<Date> values) {
            addCriterion("last_logout_time not in", values, "lastLogoutTime");
            return (Criteria) this;
        }

        public Criteria andLastLogoutTimeBetween(Date value1, Date value2) {
            addCriterion("last_logout_time between", value1, value2, "lastLogoutTime");
            return (Criteria) this;
        }

        public Criteria andLastLogoutTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_logout_time not between", value1, value2, "lastLogoutTime");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andIsEmailVerifiedIsNull() {
            addCriterion("is_email_verified is null");
            return (Criteria) this;
        }

        public Criteria andIsEmailVerifiedIsNotNull() {
            addCriterion("is_email_verified is not null");
            return (Criteria) this;
        }

        public Criteria andIsEmailVerifiedEqualTo(Boolean value) {
            addCriterion("is_email_verified =", value, "isEmailVerified");
            return (Criteria) this;
        }

        public Criteria andIsEmailVerifiedNotEqualTo(Boolean value) {
            addCriterion("is_email_verified <>", value, "isEmailVerified");
            return (Criteria) this;
        }

        public Criteria andIsEmailVerifiedGreaterThan(Boolean value) {
            addCriterion("is_email_verified >", value, "isEmailVerified");
            return (Criteria) this;
        }

        public Criteria andIsEmailVerifiedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_email_verified >=", value, "isEmailVerified");
            return (Criteria) this;
        }

        public Criteria andIsEmailVerifiedLessThan(Boolean value) {
            addCriterion("is_email_verified <", value, "isEmailVerified");
            return (Criteria) this;
        }

        public Criteria andIsEmailVerifiedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_email_verified <=", value, "isEmailVerified");
            return (Criteria) this;
        }

        public Criteria andIsEmailVerifiedIn(List<Boolean> values) {
            addCriterion("is_email_verified in", values, "isEmailVerified");
            return (Criteria) this;
        }

        public Criteria andIsEmailVerifiedNotIn(List<Boolean> values) {
            addCriterion("is_email_verified not in", values, "isEmailVerified");
            return (Criteria) this;
        }

        public Criteria andIsEmailVerifiedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_email_verified between", value1, value2, "isEmailVerified");
            return (Criteria) this;
        }

        public Criteria andIsEmailVerifiedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_email_verified not between", value1, value2, "isEmailVerified");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andIsMobileVerifiedIsNull() {
            addCriterion("is_mobile_verified is null");
            return (Criteria) this;
        }

        public Criteria andIsMobileVerifiedIsNotNull() {
            addCriterion("is_mobile_verified is not null");
            return (Criteria) this;
        }

        public Criteria andIsMobileVerifiedEqualTo(Boolean value) {
            addCriterion("is_mobile_verified =", value, "isMobileVerified");
            return (Criteria) this;
        }

        public Criteria andIsMobileVerifiedNotEqualTo(Boolean value) {
            addCriterion("is_mobile_verified <>", value, "isMobileVerified");
            return (Criteria) this;
        }

        public Criteria andIsMobileVerifiedGreaterThan(Boolean value) {
            addCriterion("is_mobile_verified >", value, "isMobileVerified");
            return (Criteria) this;
        }

        public Criteria andIsMobileVerifiedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_mobile_verified >=", value, "isMobileVerified");
            return (Criteria) this;
        }

        public Criteria andIsMobileVerifiedLessThan(Boolean value) {
            addCriterion("is_mobile_verified <", value, "isMobileVerified");
            return (Criteria) this;
        }

        public Criteria andIsMobileVerifiedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_mobile_verified <=", value, "isMobileVerified");
            return (Criteria) this;
        }

        public Criteria andIsMobileVerifiedIn(List<Boolean> values) {
            addCriterion("is_mobile_verified in", values, "isMobileVerified");
            return (Criteria) this;
        }

        public Criteria andIsMobileVerifiedNotIn(List<Boolean> values) {
            addCriterion("is_mobile_verified not in", values, "isMobileVerified");
            return (Criteria) this;
        }

        public Criteria andIsMobileVerifiedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_mobile_verified between", value1, value2, "isMobileVerified");
            return (Criteria) this;
        }

        public Criteria andIsMobileVerifiedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_mobile_verified not between", value1, value2, "isMobileVerified");
            return (Criteria) this;
        }

        public Criteria andIsSecretSetIsNull() {
            addCriterion("is_secret_set is null");
            return (Criteria) this;
        }

        public Criteria andIsSecretSetIsNotNull() {
            addCriterion("is_secret_set is not null");
            return (Criteria) this;
        }

        public Criteria andIsSecretSetEqualTo(Boolean value) {
            addCriterion("is_secret_set =", value, "isSecretSet");
            return (Criteria) this;
        }

        public Criteria andIsSecretSetNotEqualTo(Boolean value) {
            addCriterion("is_secret_set <>", value, "isSecretSet");
            return (Criteria) this;
        }

        public Criteria andIsSecretSetGreaterThan(Boolean value) {
            addCriterion("is_secret_set >", value, "isSecretSet");
            return (Criteria) this;
        }

        public Criteria andIsSecretSetGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_secret_set >=", value, "isSecretSet");
            return (Criteria) this;
        }

        public Criteria andIsSecretSetLessThan(Boolean value) {
            addCriterion("is_secret_set <", value, "isSecretSet");
            return (Criteria) this;
        }

        public Criteria andIsSecretSetLessThanOrEqualTo(Boolean value) {
            addCriterion("is_secret_set <=", value, "isSecretSet");
            return (Criteria) this;
        }

        public Criteria andIsSecretSetIn(List<Boolean> values) {
            addCriterion("is_secret_set in", values, "isSecretSet");
            return (Criteria) this;
        }

        public Criteria andIsSecretSetNotIn(List<Boolean> values) {
            addCriterion("is_secret_set not in", values, "isSecretSet");
            return (Criteria) this;
        }

        public Criteria andIsSecretSetBetween(Boolean value1, Boolean value2) {
            addCriterion("is_secret_set between", value1, value2, "isSecretSet");
            return (Criteria) this;
        }

        public Criteria andIsSecretSetNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_secret_set not between", value1, value2, "isSecretSet");
            return (Criteria) this;
        }

        public Criteria andSecretSetTimeIsNull() {
            addCriterion("secret_set_time is null");
            return (Criteria) this;
        }

        public Criteria andSecretSetTimeIsNotNull() {
            addCriterion("secret_set_time is not null");
            return (Criteria) this;
        }

        public Criteria andSecretSetTimeEqualTo(Date value) {
            addCriterion("secret_set_time =", value, "secretSetTime");
            return (Criteria) this;
        }

        public Criteria andSecretSetTimeNotEqualTo(Date value) {
            addCriterion("secret_set_time <>", value, "secretSetTime");
            return (Criteria) this;
        }

        public Criteria andSecretSetTimeGreaterThan(Date value) {
            addCriterion("secret_set_time >", value, "secretSetTime");
            return (Criteria) this;
        }

        public Criteria andSecretSetTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("secret_set_time >=", value, "secretSetTime");
            return (Criteria) this;
        }

        public Criteria andSecretSetTimeLessThan(Date value) {
            addCriterion("secret_set_time <", value, "secretSetTime");
            return (Criteria) this;
        }

        public Criteria andSecretSetTimeLessThanOrEqualTo(Date value) {
            addCriterion("secret_set_time <=", value, "secretSetTime");
            return (Criteria) this;
        }

        public Criteria andSecretSetTimeIn(List<Date> values) {
            addCriterion("secret_set_time in", values, "secretSetTime");
            return (Criteria) this;
        }

        public Criteria andSecretSetTimeNotIn(List<Date> values) {
            addCriterion("secret_set_time not in", values, "secretSetTime");
            return (Criteria) this;
        }

        public Criteria andSecretSetTimeBetween(Date value1, Date value2) {
            addCriterion("secret_set_time between", value1, value2, "secretSetTime");
            return (Criteria) this;
        }

        public Criteria andSecretSetTimeNotBetween(Date value1, Date value2) {
            addCriterion("secret_set_time not between", value1, value2, "secretSetTime");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId1IsNull() {
            addCriterion("secret_question_id1 is null");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId1IsNotNull() {
            addCriterion("secret_question_id1 is not null");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId1EqualTo(Integer value) {
            addCriterion("secret_question_id1 =", value, "secretQuestionId1");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId1NotEqualTo(Integer value) {
            addCriterion("secret_question_id1 <>", value, "secretQuestionId1");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId1GreaterThan(Integer value) {
            addCriterion("secret_question_id1 >", value, "secretQuestionId1");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId1GreaterThanOrEqualTo(Integer value) {
            addCriterion("secret_question_id1 >=", value, "secretQuestionId1");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId1LessThan(Integer value) {
            addCriterion("secret_question_id1 <", value, "secretQuestionId1");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId1LessThanOrEqualTo(Integer value) {
            addCriterion("secret_question_id1 <=", value, "secretQuestionId1");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId1In(List<Integer> values) {
            addCriterion("secret_question_id1 in", values, "secretQuestionId1");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId1NotIn(List<Integer> values) {
            addCriterion("secret_question_id1 not in", values, "secretQuestionId1");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId1Between(Integer value1, Integer value2) {
            addCriterion("secret_question_id1 between", value1, value2, "secretQuestionId1");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId1NotBetween(Integer value1, Integer value2) {
            addCriterion("secret_question_id1 not between", value1, value2, "secretQuestionId1");
            return (Criteria) this;
        }

        public Criteria andAnswer1IsNull() {
            addCriterion("answer1 is null");
            return (Criteria) this;
        }

        public Criteria andAnswer1IsNotNull() {
            addCriterion("answer1 is not null");
            return (Criteria) this;
        }

        public Criteria andAnswer1EqualTo(String value) {
            addCriterion("answer1 =", value, "answer1");
            return (Criteria) this;
        }

        public Criteria andAnswer1NotEqualTo(String value) {
            addCriterion("answer1 <>", value, "answer1");
            return (Criteria) this;
        }

        public Criteria andAnswer1GreaterThan(String value) {
            addCriterion("answer1 >", value, "answer1");
            return (Criteria) this;
        }

        public Criteria andAnswer1GreaterThanOrEqualTo(String value) {
            addCriterion("answer1 >=", value, "answer1");
            return (Criteria) this;
        }

        public Criteria andAnswer1LessThan(String value) {
            addCriterion("answer1 <", value, "answer1");
            return (Criteria) this;
        }

        public Criteria andAnswer1LessThanOrEqualTo(String value) {
            addCriterion("answer1 <=", value, "answer1");
            return (Criteria) this;
        }

        public Criteria andAnswer1Like(String value) {
            addCriterion("answer1 like", value, "answer1");
            return (Criteria) this;
        }

        public Criteria andAnswer1NotLike(String value) {
            addCriterion("answer1 not like", value, "answer1");
            return (Criteria) this;
        }

        public Criteria andAnswer1In(List<String> values) {
            addCriterion("answer1 in", values, "answer1");
            return (Criteria) this;
        }

        public Criteria andAnswer1NotIn(List<String> values) {
            addCriterion("answer1 not in", values, "answer1");
            return (Criteria) this;
        }

        public Criteria andAnswer1Between(String value1, String value2) {
            addCriterion("answer1 between", value1, value2, "answer1");
            return (Criteria) this;
        }

        public Criteria andAnswer1NotBetween(String value1, String value2) {
            addCriterion("answer1 not between", value1, value2, "answer1");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId2IsNull() {
            addCriterion("secret_question_id2 is null");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId2IsNotNull() {
            addCriterion("secret_question_id2 is not null");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId2EqualTo(Integer value) {
            addCriterion("secret_question_id2 =", value, "secretQuestionId2");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId2NotEqualTo(Integer value) {
            addCriterion("secret_question_id2 <>", value, "secretQuestionId2");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId2GreaterThan(Integer value) {
            addCriterion("secret_question_id2 >", value, "secretQuestionId2");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId2GreaterThanOrEqualTo(Integer value) {
            addCriterion("secret_question_id2 >=", value, "secretQuestionId2");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId2LessThan(Integer value) {
            addCriterion("secret_question_id2 <", value, "secretQuestionId2");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId2LessThanOrEqualTo(Integer value) {
            addCriterion("secret_question_id2 <=", value, "secretQuestionId2");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId2In(List<Integer> values) {
            addCriterion("secret_question_id2 in", values, "secretQuestionId2");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId2NotIn(List<Integer> values) {
            addCriterion("secret_question_id2 not in", values, "secretQuestionId2");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId2Between(Integer value1, Integer value2) {
            addCriterion("secret_question_id2 between", value1, value2, "secretQuestionId2");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId2NotBetween(Integer value1, Integer value2) {
            addCriterion("secret_question_id2 not between", value1, value2, "secretQuestionId2");
            return (Criteria) this;
        }

        public Criteria andAnswer2IsNull() {
            addCriterion("answer2 is null");
            return (Criteria) this;
        }

        public Criteria andAnswer2IsNotNull() {
            addCriterion("answer2 is not null");
            return (Criteria) this;
        }

        public Criteria andAnswer2EqualTo(String value) {
            addCriterion("answer2 =", value, "answer2");
            return (Criteria) this;
        }

        public Criteria andAnswer2NotEqualTo(String value) {
            addCriterion("answer2 <>", value, "answer2");
            return (Criteria) this;
        }

        public Criteria andAnswer2GreaterThan(String value) {
            addCriterion("answer2 >", value, "answer2");
            return (Criteria) this;
        }

        public Criteria andAnswer2GreaterThanOrEqualTo(String value) {
            addCriterion("answer2 >=", value, "answer2");
            return (Criteria) this;
        }

        public Criteria andAnswer2LessThan(String value) {
            addCriterion("answer2 <", value, "answer2");
            return (Criteria) this;
        }

        public Criteria andAnswer2LessThanOrEqualTo(String value) {
            addCriterion("answer2 <=", value, "answer2");
            return (Criteria) this;
        }

        public Criteria andAnswer2Like(String value) {
            addCriterion("answer2 like", value, "answer2");
            return (Criteria) this;
        }

        public Criteria andAnswer2NotLike(String value) {
            addCriterion("answer2 not like", value, "answer2");
            return (Criteria) this;
        }

        public Criteria andAnswer2In(List<String> values) {
            addCriterion("answer2 in", values, "answer2");
            return (Criteria) this;
        }

        public Criteria andAnswer2NotIn(List<String> values) {
            addCriterion("answer2 not in", values, "answer2");
            return (Criteria) this;
        }

        public Criteria andAnswer2Between(String value1, String value2) {
            addCriterion("answer2 between", value1, value2, "answer2");
            return (Criteria) this;
        }

        public Criteria andAnswer2NotBetween(String value1, String value2) {
            addCriterion("answer2 not between", value1, value2, "answer2");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId3IsNull() {
            addCriterion("secret_question_id3 is null");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId3IsNotNull() {
            addCriterion("secret_question_id3 is not null");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId3EqualTo(Integer value) {
            addCriterion("secret_question_id3 =", value, "secretQuestionId3");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId3NotEqualTo(Integer value) {
            addCriterion("secret_question_id3 <>", value, "secretQuestionId3");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId3GreaterThan(Integer value) {
            addCriterion("secret_question_id3 >", value, "secretQuestionId3");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId3GreaterThanOrEqualTo(Integer value) {
            addCriterion("secret_question_id3 >=", value, "secretQuestionId3");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId3LessThan(Integer value) {
            addCriterion("secret_question_id3 <", value, "secretQuestionId3");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId3LessThanOrEqualTo(Integer value) {
            addCriterion("secret_question_id3 <=", value, "secretQuestionId3");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId3In(List<Integer> values) {
            addCriterion("secret_question_id3 in", values, "secretQuestionId3");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId3NotIn(List<Integer> values) {
            addCriterion("secret_question_id3 not in", values, "secretQuestionId3");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId3Between(Integer value1, Integer value2) {
            addCriterion("secret_question_id3 between", value1, value2, "secretQuestionId3");
            return (Criteria) this;
        }

        public Criteria andSecretQuestionId3NotBetween(Integer value1, Integer value2) {
            addCriterion("secret_question_id3 not between", value1, value2, "secretQuestionId3");
            return (Criteria) this;
        }

        public Criteria andAnswer3IsNull() {
            addCriterion("answer3 is null");
            return (Criteria) this;
        }

        public Criteria andAnswer3IsNotNull() {
            addCriterion("answer3 is not null");
            return (Criteria) this;
        }

        public Criteria andAnswer3EqualTo(String value) {
            addCriterion("answer3 =", value, "answer3");
            return (Criteria) this;
        }

        public Criteria andAnswer3NotEqualTo(String value) {
            addCriterion("answer3 <>", value, "answer3");
            return (Criteria) this;
        }

        public Criteria andAnswer3GreaterThan(String value) {
            addCriterion("answer3 >", value, "answer3");
            return (Criteria) this;
        }

        public Criteria andAnswer3GreaterThanOrEqualTo(String value) {
            addCriterion("answer3 >=", value, "answer3");
            return (Criteria) this;
        }

        public Criteria andAnswer3LessThan(String value) {
            addCriterion("answer3 <", value, "answer3");
            return (Criteria) this;
        }

        public Criteria andAnswer3LessThanOrEqualTo(String value) {
            addCriterion("answer3 <=", value, "answer3");
            return (Criteria) this;
        }

        public Criteria andAnswer3Like(String value) {
            addCriterion("answer3 like", value, "answer3");
            return (Criteria) this;
        }

        public Criteria andAnswer3NotLike(String value) {
            addCriterion("answer3 not like", value, "answer3");
            return (Criteria) this;
        }

        public Criteria andAnswer3In(List<String> values) {
            addCriterion("answer3 in", values, "answer3");
            return (Criteria) this;
        }

        public Criteria andAnswer3NotIn(List<String> values) {
            addCriterion("answer3 not in", values, "answer3");
            return (Criteria) this;
        }

        public Criteria andAnswer3Between(String value1, String value2) {
            addCriterion("answer3 between", value1, value2, "answer3");
            return (Criteria) this;
        }

        public Criteria andAnswer3NotBetween(String value1, String value2) {
            addCriterion("answer3 not between", value1, value2, "answer3");
            return (Criteria) this;
        }

        public Criteria andIdNumberIsNull() {
            addCriterion("id_number is null");
            return (Criteria) this;
        }

        public Criteria andIdNumberIsNotNull() {
            addCriterion("id_number is not null");
            return (Criteria) this;
        }

        public Criteria andIdNumberEqualTo(String value) {
            addCriterion("id_number =", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotEqualTo(String value) {
            addCriterion("id_number <>", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberGreaterThan(String value) {
            addCriterion("id_number >", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberGreaterThanOrEqualTo(String value) {
            addCriterion("id_number >=", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberLessThan(String value) {
            addCriterion("id_number <", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberLessThanOrEqualTo(String value) {
            addCriterion("id_number <=", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberLike(String value) {
            addCriterion("id_number like", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotLike(String value) {
            addCriterion("id_number not like", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberIn(List<String> values) {
            addCriterion("id_number in", values, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotIn(List<String> values) {
            addCriterion("id_number not in", values, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberBetween(String value1, String value2) {
            addCriterion("id_number between", value1, value2, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotBetween(String value1, String value2) {
            addCriterion("id_number not between", value1, value2, "idNumber");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andPostcodeIsNull() {
            addCriterion("postcode is null");
            return (Criteria) this;
        }

        public Criteria andPostcodeIsNotNull() {
            addCriterion("postcode is not null");
            return (Criteria) this;
        }

        public Criteria andPostcodeEqualTo(String value) {
            addCriterion("postcode =", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotEqualTo(String value) {
            addCriterion("postcode <>", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeGreaterThan(String value) {
            addCriterion("postcode >", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeGreaterThanOrEqualTo(String value) {
            addCriterion("postcode >=", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLessThan(String value) {
            addCriterion("postcode <", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLessThanOrEqualTo(String value) {
            addCriterion("postcode <=", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLike(String value) {
            addCriterion("postcode like", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotLike(String value) {
            addCriterion("postcode not like", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeIn(List<String> values) {
            addCriterion("postcode in", values, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotIn(List<String> values) {
            addCriterion("postcode not in", values, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeBetween(String value1, String value2) {
            addCriterion("postcode between", value1, value2, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotBetween(String value1, String value2) {
            addCriterion("postcode not between", value1, value2, "postcode");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(Byte value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(Byte value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(Byte value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(Byte value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(Byte value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(Byte value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<Byte> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<Byte> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(Byte value1, Byte value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(Byte value1, Byte value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("birthday is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("birthday is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(Date value) {
            addCriterion("birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(Date value) {
            addCriterion("birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(Date value) {
            addCriterion("birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterion("birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(Date value) {
            addCriterion("birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(Date value) {
            addCriterion("birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<Date> values) {
            addCriterion("birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<Date> values) {
            addCriterion("birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(Date value1, Date value2) {
            addCriterion("birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(Date value1, Date value2) {
            addCriterion("birthday not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNull() {
            addCriterion("city_id is null");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNotNull() {
            addCriterion("city_id is not null");
            return (Criteria) this;
        }

        public Criteria andCityIdEqualTo(Integer value) {
            addCriterion("city_id =", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotEqualTo(Integer value) {
            addCriterion("city_id <>", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThan(Integer value) {
            addCriterion("city_id >", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("city_id >=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThan(Integer value) {
            addCriterion("city_id <", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThanOrEqualTo(Integer value) {
            addCriterion("city_id <=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdIn(List<Integer> values) {
            addCriterion("city_id in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotIn(List<Integer> values) {
            addCriterion("city_id not in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdBetween(Integer value1, Integer value2) {
            addCriterion("city_id between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("city_id not between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andFamilyAddressIsNull() {
            addCriterion("family_address is null");
            return (Criteria) this;
        }

        public Criteria andFamilyAddressIsNotNull() {
            addCriterion("family_address is not null");
            return (Criteria) this;
        }

        public Criteria andFamilyAddressEqualTo(String value) {
            addCriterion("family_address =", value, "familyAddress");
            return (Criteria) this;
        }

        public Criteria andFamilyAddressNotEqualTo(String value) {
            addCriterion("family_address <>", value, "familyAddress");
            return (Criteria) this;
        }

        public Criteria andFamilyAddressGreaterThan(String value) {
            addCriterion("family_address >", value, "familyAddress");
            return (Criteria) this;
        }

        public Criteria andFamilyAddressGreaterThanOrEqualTo(String value) {
            addCriterion("family_address >=", value, "familyAddress");
            return (Criteria) this;
        }

        public Criteria andFamilyAddressLessThan(String value) {
            addCriterion("family_address <", value, "familyAddress");
            return (Criteria) this;
        }

        public Criteria andFamilyAddressLessThanOrEqualTo(String value) {
            addCriterion("family_address <=", value, "familyAddress");
            return (Criteria) this;
        }

        public Criteria andFamilyAddressLike(String value) {
            addCriterion("family_address like", value, "familyAddress");
            return (Criteria) this;
        }

        public Criteria andFamilyAddressNotLike(String value) {
            addCriterion("family_address not like", value, "familyAddress");
            return (Criteria) this;
        }

        public Criteria andFamilyAddressIn(List<String> values) {
            addCriterion("family_address in", values, "familyAddress");
            return (Criteria) this;
        }

        public Criteria andFamilyAddressNotIn(List<String> values) {
            addCriterion("family_address not in", values, "familyAddress");
            return (Criteria) this;
        }

        public Criteria andFamilyAddressBetween(String value1, String value2) {
            addCriterion("family_address between", value1, value2, "familyAddress");
            return (Criteria) this;
        }

        public Criteria andFamilyAddressNotBetween(String value1, String value2) {
            addCriterion("family_address not between", value1, value2, "familyAddress");
            return (Criteria) this;
        }

        public Criteria andFamilyTelephoneIsNull() {
            addCriterion("family_telephone is null");
            return (Criteria) this;
        }

        public Criteria andFamilyTelephoneIsNotNull() {
            addCriterion("family_telephone is not null");
            return (Criteria) this;
        }

        public Criteria andFamilyTelephoneEqualTo(String value) {
            addCriterion("family_telephone =", value, "familyTelephone");
            return (Criteria) this;
        }

        public Criteria andFamilyTelephoneNotEqualTo(String value) {
            addCriterion("family_telephone <>", value, "familyTelephone");
            return (Criteria) this;
        }

        public Criteria andFamilyTelephoneGreaterThan(String value) {
            addCriterion("family_telephone >", value, "familyTelephone");
            return (Criteria) this;
        }

        public Criteria andFamilyTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("family_telephone >=", value, "familyTelephone");
            return (Criteria) this;
        }

        public Criteria andFamilyTelephoneLessThan(String value) {
            addCriterion("family_telephone <", value, "familyTelephone");
            return (Criteria) this;
        }

        public Criteria andFamilyTelephoneLessThanOrEqualTo(String value) {
            addCriterion("family_telephone <=", value, "familyTelephone");
            return (Criteria) this;
        }

        public Criteria andFamilyTelephoneLike(String value) {
            addCriterion("family_telephone like", value, "familyTelephone");
            return (Criteria) this;
        }

        public Criteria andFamilyTelephoneNotLike(String value) {
            addCriterion("family_telephone not like", value, "familyTelephone");
            return (Criteria) this;
        }

        public Criteria andFamilyTelephoneIn(List<String> values) {
            addCriterion("family_telephone in", values, "familyTelephone");
            return (Criteria) this;
        }

        public Criteria andFamilyTelephoneNotIn(List<String> values) {
            addCriterion("family_telephone not in", values, "familyTelephone");
            return (Criteria) this;
        }

        public Criteria andFamilyTelephoneBetween(String value1, String value2) {
            addCriterion("family_telephone between", value1, value2, "familyTelephone");
            return (Criteria) this;
        }

        public Criteria andFamilyTelephoneNotBetween(String value1, String value2) {
            addCriterion("family_telephone not between", value1, value2, "familyTelephone");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNull() {
            addCriterion("company is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNotNull() {
            addCriterion("company is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyEqualTo(String value) {
            addCriterion("company =", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotEqualTo(String value) {
            addCriterion("company <>", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThan(String value) {
            addCriterion("company >", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("company >=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThan(String value) {
            addCriterion("company <", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThanOrEqualTo(String value) {
            addCriterion("company <=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLike(String value) {
            addCriterion("company like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotLike(String value) {
            addCriterion("company not like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyIn(List<String> values) {
            addCriterion("company in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotIn(List<String> values) {
            addCriterion("company not in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyBetween(String value1, String value2) {
            addCriterion("company between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotBetween(String value1, String value2) {
            addCriterion("company not between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIsNull() {
            addCriterion("company_address is null");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIsNotNull() {
            addCriterion("company_address is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressEqualTo(String value) {
            addCriterion("company_address =", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotEqualTo(String value) {
            addCriterion("company_address <>", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressGreaterThan(String value) {
            addCriterion("company_address >", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressGreaterThanOrEqualTo(String value) {
            addCriterion("company_address >=", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLessThan(String value) {
            addCriterion("company_address <", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLessThanOrEqualTo(String value) {
            addCriterion("company_address <=", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLike(String value) {
            addCriterion("company_address like", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotLike(String value) {
            addCriterion("company_address not like", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIn(List<String> values) {
            addCriterion("company_address in", values, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotIn(List<String> values) {
            addCriterion("company_address not in", values, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressBetween(String value1, String value2) {
            addCriterion("company_address between", value1, value2, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotBetween(String value1, String value2) {
            addCriterion("company_address not between", value1, value2, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andOfficeTelephoneIsNull() {
            addCriterion("office_telephone is null");
            return (Criteria) this;
        }

        public Criteria andOfficeTelephoneIsNotNull() {
            addCriterion("office_telephone is not null");
            return (Criteria) this;
        }

        public Criteria andOfficeTelephoneEqualTo(String value) {
            addCriterion("office_telephone =", value, "officeTelephone");
            return (Criteria) this;
        }

        public Criteria andOfficeTelephoneNotEqualTo(String value) {
            addCriterion("office_telephone <>", value, "officeTelephone");
            return (Criteria) this;
        }

        public Criteria andOfficeTelephoneGreaterThan(String value) {
            addCriterion("office_telephone >", value, "officeTelephone");
            return (Criteria) this;
        }

        public Criteria andOfficeTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("office_telephone >=", value, "officeTelephone");
            return (Criteria) this;
        }

        public Criteria andOfficeTelephoneLessThan(String value) {
            addCriterion("office_telephone <", value, "officeTelephone");
            return (Criteria) this;
        }

        public Criteria andOfficeTelephoneLessThanOrEqualTo(String value) {
            addCriterion("office_telephone <=", value, "officeTelephone");
            return (Criteria) this;
        }

        public Criteria andOfficeTelephoneLike(String value) {
            addCriterion("office_telephone like", value, "officeTelephone");
            return (Criteria) this;
        }

        public Criteria andOfficeTelephoneNotLike(String value) {
            addCriterion("office_telephone not like", value, "officeTelephone");
            return (Criteria) this;
        }

        public Criteria andOfficeTelephoneIn(List<String> values) {
            addCriterion("office_telephone in", values, "officeTelephone");
            return (Criteria) this;
        }

        public Criteria andOfficeTelephoneNotIn(List<String> values) {
            addCriterion("office_telephone not in", values, "officeTelephone");
            return (Criteria) this;
        }

        public Criteria andOfficeTelephoneBetween(String value1, String value2) {
            addCriterion("office_telephone between", value1, value2, "officeTelephone");
            return (Criteria) this;
        }

        public Criteria andOfficeTelephoneNotBetween(String value1, String value2) {
            addCriterion("office_telephone not between", value1, value2, "officeTelephone");
            return (Criteria) this;
        }

        public Criteria andFaxNumberIsNull() {
            addCriterion("fax_number is null");
            return (Criteria) this;
        }

        public Criteria andFaxNumberIsNotNull() {
            addCriterion("fax_number is not null");
            return (Criteria) this;
        }

        public Criteria andFaxNumberEqualTo(String value) {
            addCriterion("fax_number =", value, "faxNumber");
            return (Criteria) this;
        }

        public Criteria andFaxNumberNotEqualTo(String value) {
            addCriterion("fax_number <>", value, "faxNumber");
            return (Criteria) this;
        }

        public Criteria andFaxNumberGreaterThan(String value) {
            addCriterion("fax_number >", value, "faxNumber");
            return (Criteria) this;
        }

        public Criteria andFaxNumberGreaterThanOrEqualTo(String value) {
            addCriterion("fax_number >=", value, "faxNumber");
            return (Criteria) this;
        }

        public Criteria andFaxNumberLessThan(String value) {
            addCriterion("fax_number <", value, "faxNumber");
            return (Criteria) this;
        }

        public Criteria andFaxNumberLessThanOrEqualTo(String value) {
            addCriterion("fax_number <=", value, "faxNumber");
            return (Criteria) this;
        }

        public Criteria andFaxNumberLike(String value) {
            addCriterion("fax_number like", value, "faxNumber");
            return (Criteria) this;
        }

        public Criteria andFaxNumberNotLike(String value) {
            addCriterion("fax_number not like", value, "faxNumber");
            return (Criteria) this;
        }

        public Criteria andFaxNumberIn(List<String> values) {
            addCriterion("fax_number in", values, "faxNumber");
            return (Criteria) this;
        }

        public Criteria andFaxNumberNotIn(List<String> values) {
            addCriterion("fax_number not in", values, "faxNumber");
            return (Criteria) this;
        }

        public Criteria andFaxNumberBetween(String value1, String value2) {
            addCriterion("fax_number between", value1, value2, "faxNumber");
            return (Criteria) this;
        }

        public Criteria andFaxNumberNotBetween(String value1, String value2) {
            addCriterion("fax_number not between", value1, value2, "faxNumber");
            return (Criteria) this;
        }

        public Criteria andEducationIdIsNull() {
            addCriterion("education_id is null");
            return (Criteria) this;
        }

        public Criteria andEducationIdIsNotNull() {
            addCriterion("education_id is not null");
            return (Criteria) this;
        }

        public Criteria andEducationIdEqualTo(Integer value) {
            addCriterion("education_id =", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdNotEqualTo(Integer value) {
            addCriterion("education_id <>", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdGreaterThan(Integer value) {
            addCriterion("education_id >", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("education_id >=", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdLessThan(Integer value) {
            addCriterion("education_id <", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdLessThanOrEqualTo(Integer value) {
            addCriterion("education_id <=", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdIn(List<Integer> values) {
            addCriterion("education_id in", values, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdNotIn(List<Integer> values) {
            addCriterion("education_id not in", values, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdBetween(Integer value1, Integer value2) {
            addCriterion("education_id between", value1, value2, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("education_id not between", value1, value2, "educationId");
            return (Criteria) this;
        }

        public Criteria andMaritalIdIsNull() {
            addCriterion("marital_id is null");
            return (Criteria) this;
        }

        public Criteria andMaritalIdIsNotNull() {
            addCriterion("marital_id is not null");
            return (Criteria) this;
        }

        public Criteria andMaritalIdEqualTo(Integer value) {
            addCriterion("marital_id =", value, "maritalId");
            return (Criteria) this;
        }

        public Criteria andMaritalIdNotEqualTo(Integer value) {
            addCriterion("marital_id <>", value, "maritalId");
            return (Criteria) this;
        }

        public Criteria andMaritalIdGreaterThan(Integer value) {
            addCriterion("marital_id >", value, "maritalId");
            return (Criteria) this;
        }

        public Criteria andMaritalIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("marital_id >=", value, "maritalId");
            return (Criteria) this;
        }

        public Criteria andMaritalIdLessThan(Integer value) {
            addCriterion("marital_id <", value, "maritalId");
            return (Criteria) this;
        }

        public Criteria andMaritalIdLessThanOrEqualTo(Integer value) {
            addCriterion("marital_id <=", value, "maritalId");
            return (Criteria) this;
        }

        public Criteria andMaritalIdIn(List<Integer> values) {
            addCriterion("marital_id in", values, "maritalId");
            return (Criteria) this;
        }

        public Criteria andMaritalIdNotIn(List<Integer> values) {
            addCriterion("marital_id not in", values, "maritalId");
            return (Criteria) this;
        }

        public Criteria andMaritalIdBetween(Integer value1, Integer value2) {
            addCriterion("marital_id between", value1, value2, "maritalId");
            return (Criteria) this;
        }

        public Criteria andMaritalIdNotBetween(Integer value1, Integer value2) {
            addCriterion("marital_id not between", value1, value2, "maritalId");
            return (Criteria) this;
        }

        public Criteria andHouseIdIsNull() {
            addCriterion("house_id is null");
            return (Criteria) this;
        }

        public Criteria andHouseIdIsNotNull() {
            addCriterion("house_id is not null");
            return (Criteria) this;
        }

        public Criteria andHouseIdEqualTo(Integer value) {
            addCriterion("house_id =", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdNotEqualTo(Integer value) {
            addCriterion("house_id <>", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdGreaterThan(Integer value) {
            addCriterion("house_id >", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("house_id >=", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdLessThan(Integer value) {
            addCriterion("house_id <", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdLessThanOrEqualTo(Integer value) {
            addCriterion("house_id <=", value, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdIn(List<Integer> values) {
            addCriterion("house_id in", values, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdNotIn(List<Integer> values) {
            addCriterion("house_id not in", values, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdBetween(Integer value1, Integer value2) {
            addCriterion("house_id between", value1, value2, "houseId");
            return (Criteria) this;
        }

        public Criteria andHouseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("house_id not between", value1, value2, "houseId");
            return (Criteria) this;
        }

        public Criteria andCarIdIsNull() {
            addCriterion("car_id is null");
            return (Criteria) this;
        }

        public Criteria andCarIdIsNotNull() {
            addCriterion("car_id is not null");
            return (Criteria) this;
        }

        public Criteria andCarIdEqualTo(Integer value) {
            addCriterion("car_id =", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdNotEqualTo(Integer value) {
            addCriterion("car_id <>", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdGreaterThan(Integer value) {
            addCriterion("car_id >", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("car_id >=", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdLessThan(Integer value) {
            addCriterion("car_id <", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdLessThanOrEqualTo(Integer value) {
            addCriterion("car_id <=", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdIn(List<Integer> values) {
            addCriterion("car_id in", values, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdNotIn(List<Integer> values) {
            addCriterion("car_id not in", values, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdBetween(Integer value1, Integer value2) {
            addCriterion("car_id between", value1, value2, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdNotBetween(Integer value1, Integer value2) {
            addCriterion("car_id not between", value1, value2, "carId");
            return (Criteria) this;
        }

        public Criteria andIsAddBaseInfoIsNull() {
            addCriterion("is_add_base_info is null");
            return (Criteria) this;
        }

        public Criteria andIsAddBaseInfoIsNotNull() {
            addCriterion("is_add_base_info is not null");
            return (Criteria) this;
        }

        public Criteria andIsAddBaseInfoEqualTo(Boolean value) {
            addCriterion("is_add_base_info =", value, "isAddBaseInfo");
            return (Criteria) this;
        }

        public Criteria andIsAddBaseInfoNotEqualTo(Boolean value) {
            addCriterion("is_add_base_info <>", value, "isAddBaseInfo");
            return (Criteria) this;
        }

        public Criteria andIsAddBaseInfoGreaterThan(Boolean value) {
            addCriterion("is_add_base_info >", value, "isAddBaseInfo");
            return (Criteria) this;
        }

        public Criteria andIsAddBaseInfoGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_add_base_info >=", value, "isAddBaseInfo");
            return (Criteria) this;
        }

        public Criteria andIsAddBaseInfoLessThan(Boolean value) {
            addCriterion("is_add_base_info <", value, "isAddBaseInfo");
            return (Criteria) this;
        }

        public Criteria andIsAddBaseInfoLessThanOrEqualTo(Boolean value) {
            addCriterion("is_add_base_info <=", value, "isAddBaseInfo");
            return (Criteria) this;
        }

        public Criteria andIsAddBaseInfoIn(List<Boolean> values) {
            addCriterion("is_add_base_info in", values, "isAddBaseInfo");
            return (Criteria) this;
        }

        public Criteria andIsAddBaseInfoNotIn(List<Boolean> values) {
            addCriterion("is_add_base_info not in", values, "isAddBaseInfo");
            return (Criteria) this;
        }

        public Criteria andIsAddBaseInfoBetween(Boolean value1, Boolean value2) {
            addCriterion("is_add_base_info between", value1, value2, "isAddBaseInfo");
            return (Criteria) this;
        }

        public Criteria andIsAddBaseInfoNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_add_base_info not between", value1, value2, "isAddBaseInfo");
            return (Criteria) this;
        }

        public Criteria andIsErasedIsNull() {
            addCriterion("is_erased is null");
            return (Criteria) this;
        }

        public Criteria andIsErasedIsNotNull() {
            addCriterion("is_erased is not null");
            return (Criteria) this;
        }

        public Criteria andIsErasedEqualTo(Boolean value) {
            addCriterion("is_erased =", value, "isErased");
            return (Criteria) this;
        }

        public Criteria andIsErasedNotEqualTo(Boolean value) {
            addCriterion("is_erased <>", value, "isErased");
            return (Criteria) this;
        }

        public Criteria andIsErasedGreaterThan(Boolean value) {
            addCriterion("is_erased >", value, "isErased");
            return (Criteria) this;
        }

        public Criteria andIsErasedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_erased >=", value, "isErased");
            return (Criteria) this;
        }

        public Criteria andIsErasedLessThan(Boolean value) {
            addCriterion("is_erased <", value, "isErased");
            return (Criteria) this;
        }

        public Criteria andIsErasedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_erased <=", value, "isErased");
            return (Criteria) this;
        }

        public Criteria andIsErasedIn(List<Boolean> values) {
            addCriterion("is_erased in", values, "isErased");
            return (Criteria) this;
        }

        public Criteria andIsErasedNotIn(List<Boolean> values) {
            addCriterion("is_erased not in", values, "isErased");
            return (Criteria) this;
        }

        public Criteria andIsErasedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_erased between", value1, value2, "isErased");
            return (Criteria) this;
        }

        public Criteria andIsErasedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_erased not between", value1, value2, "isErased");
            return (Criteria) this;
        }

        public Criteria andRecommendTimeIsNull() {
            addCriterion("recommend_time is null");
            return (Criteria) this;
        }

        public Criteria andRecommendTimeIsNotNull() {
            addCriterion("recommend_time is not null");
            return (Criteria) this;
        }

        public Criteria andRecommendTimeEqualTo(Date value) {
            addCriterion("recommend_time =", value, "recommendTime");
            return (Criteria) this;
        }

        public Criteria andRecommendTimeNotEqualTo(Date value) {
            addCriterion("recommend_time <>", value, "recommendTime");
            return (Criteria) this;
        }

        public Criteria andRecommendTimeGreaterThan(Date value) {
            addCriterion("recommend_time >", value, "recommendTime");
            return (Criteria) this;
        }

        public Criteria andRecommendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("recommend_time >=", value, "recommendTime");
            return (Criteria) this;
        }

        public Criteria andRecommendTimeLessThan(Date value) {
            addCriterion("recommend_time <", value, "recommendTime");
            return (Criteria) this;
        }

        public Criteria andRecommendTimeLessThanOrEqualTo(Date value) {
            addCriterion("recommend_time <=", value, "recommendTime");
            return (Criteria) this;
        }

        public Criteria andRecommendTimeIn(List<Date> values) {
            addCriterion("recommend_time in", values, "recommendTime");
            return (Criteria) this;
        }

        public Criteria andRecommendTimeNotIn(List<Date> values) {
            addCriterion("recommend_time not in", values, "recommendTime");
            return (Criteria) this;
        }

        public Criteria andRecommendTimeBetween(Date value1, Date value2) {
            addCriterion("recommend_time between", value1, value2, "recommendTime");
            return (Criteria) this;
        }

        public Criteria andRecommendTimeNotBetween(Date value1, Date value2) {
            addCriterion("recommend_time not between", value1, value2, "recommendTime");
            return (Criteria) this;
        }

        public Criteria andRecommendUserIdIsNull() {
            addCriterion("recommend_user_id is null");
            return (Criteria) this;
        }

        public Criteria andRecommendUserIdIsNotNull() {
            addCriterion("recommend_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecommendUserIdEqualTo(Long value) {
            addCriterion("recommend_user_id =", value, "recommendUserId");
            return (Criteria) this;
        }

        public Criteria andRecommendUserIdNotEqualTo(Long value) {
            addCriterion("recommend_user_id <>", value, "recommendUserId");
            return (Criteria) this;
        }

        public Criteria andRecommendUserIdGreaterThan(Long value) {
            addCriterion("recommend_user_id >", value, "recommendUserId");
            return (Criteria) this;
        }

        public Criteria andRecommendUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("recommend_user_id >=", value, "recommendUserId");
            return (Criteria) this;
        }

        public Criteria andRecommendUserIdLessThan(Long value) {
            addCriterion("recommend_user_id <", value, "recommendUserId");
            return (Criteria) this;
        }

        public Criteria andRecommendUserIdLessThanOrEqualTo(Long value) {
            addCriterion("recommend_user_id <=", value, "recommendUserId");
            return (Criteria) this;
        }

        public Criteria andRecommendUserIdIn(List<Long> values) {
            addCriterion("recommend_user_id in", values, "recommendUserId");
            return (Criteria) this;
        }

        public Criteria andRecommendUserIdNotIn(List<Long> values) {
            addCriterion("recommend_user_id not in", values, "recommendUserId");
            return (Criteria) this;
        }

        public Criteria andRecommendUserIdBetween(Long value1, Long value2) {
            addCriterion("recommend_user_id between", value1, value2, "recommendUserId");
            return (Criteria) this;
        }

        public Criteria andRecommendUserIdNotBetween(Long value1, Long value2) {
            addCriterion("recommend_user_id not between", value1, value2, "recommendUserId");
            return (Criteria) this;
        }

        public Criteria andRecommendRewardTypeIsNull() {
            addCriterion("recommend_reward_type is null");
            return (Criteria) this;
        }

        public Criteria andRecommendRewardTypeIsNotNull() {
            addCriterion("recommend_reward_type is not null");
            return (Criteria) this;
        }

        public Criteria andRecommendRewardTypeEqualTo(Byte value) {
            addCriterion("recommend_reward_type =", value, "recommendRewardType");
            return (Criteria) this;
        }

        public Criteria andRecommendRewardTypeNotEqualTo(Byte value) {
            addCriterion("recommend_reward_type <>", value, "recommendRewardType");
            return (Criteria) this;
        }

        public Criteria andRecommendRewardTypeGreaterThan(Byte value) {
            addCriterion("recommend_reward_type >", value, "recommendRewardType");
            return (Criteria) this;
        }

        public Criteria andRecommendRewardTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("recommend_reward_type >=", value, "recommendRewardType");
            return (Criteria) this;
        }

        public Criteria andRecommendRewardTypeLessThan(Byte value) {
            addCriterion("recommend_reward_type <", value, "recommendRewardType");
            return (Criteria) this;
        }

        public Criteria andRecommendRewardTypeLessThanOrEqualTo(Byte value) {
            addCriterion("recommend_reward_type <=", value, "recommendRewardType");
            return (Criteria) this;
        }

        public Criteria andRecommendRewardTypeIn(List<Byte> values) {
            addCriterion("recommend_reward_type in", values, "recommendRewardType");
            return (Criteria) this;
        }

        public Criteria andRecommendRewardTypeNotIn(List<Byte> values) {
            addCriterion("recommend_reward_type not in", values, "recommendRewardType");
            return (Criteria) this;
        }

        public Criteria andRecommendRewardTypeBetween(Byte value1, Byte value2) {
            addCriterion("recommend_reward_type between", value1, value2, "recommendRewardType");
            return (Criteria) this;
        }

        public Criteria andRecommendRewardTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("recommend_reward_type not between", value1, value2, "recommendRewardType");
            return (Criteria) this;
        }

        public Criteria andMasterIdentityIsNull() {
            addCriterion("master_identity is null");
            return (Criteria) this;
        }

        public Criteria andMasterIdentityIsNotNull() {
            addCriterion("master_identity is not null");
            return (Criteria) this;
        }

        public Criteria andMasterIdentityEqualTo(Byte value) {
            addCriterion("master_identity =", value, "masterIdentity");
            return (Criteria) this;
        }

        public Criteria andMasterIdentityNotEqualTo(Byte value) {
            addCriterion("master_identity <>", value, "masterIdentity");
            return (Criteria) this;
        }

        public Criteria andMasterIdentityGreaterThan(Byte value) {
            addCriterion("master_identity >", value, "masterIdentity");
            return (Criteria) this;
        }

        public Criteria andMasterIdentityGreaterThanOrEqualTo(Byte value) {
            addCriterion("master_identity >=", value, "masterIdentity");
            return (Criteria) this;
        }

        public Criteria andMasterIdentityLessThan(Byte value) {
            addCriterion("master_identity <", value, "masterIdentity");
            return (Criteria) this;
        }

        public Criteria andMasterIdentityLessThanOrEqualTo(Byte value) {
            addCriterion("master_identity <=", value, "masterIdentity");
            return (Criteria) this;
        }

        public Criteria andMasterIdentityIn(List<Byte> values) {
            addCriterion("master_identity in", values, "masterIdentity");
            return (Criteria) this;
        }

        public Criteria andMasterIdentityNotIn(List<Byte> values) {
            addCriterion("master_identity not in", values, "masterIdentity");
            return (Criteria) this;
        }

        public Criteria andMasterIdentityBetween(Byte value1, Byte value2) {
            addCriterion("master_identity between", value1, value2, "masterIdentity");
            return (Criteria) this;
        }

        public Criteria andMasterIdentityNotBetween(Byte value1, Byte value2) {
            addCriterion("master_identity not between", value1, value2, "masterIdentity");
            return (Criteria) this;
        }

        public Criteria andMasterClientIsNull() {
            addCriterion("master_client is null");
            return (Criteria) this;
        }

        public Criteria andMasterClientIsNotNull() {
            addCriterion("master_client is not null");
            return (Criteria) this;
        }

        public Criteria andMasterClientEqualTo(Byte value) {
            addCriterion("master_client =", value, "masterClient");
            return (Criteria) this;
        }

        public Criteria andMasterClientNotEqualTo(Byte value) {
            addCriterion("master_client <>", value, "masterClient");
            return (Criteria) this;
        }

        public Criteria andMasterClientGreaterThan(Byte value) {
            addCriterion("master_client >", value, "masterClient");
            return (Criteria) this;
        }

        public Criteria andMasterClientGreaterThanOrEqualTo(Byte value) {
            addCriterion("master_client >=", value, "masterClient");
            return (Criteria) this;
        }

        public Criteria andMasterClientLessThan(Byte value) {
            addCriterion("master_client <", value, "masterClient");
            return (Criteria) this;
        }

        public Criteria andMasterClientLessThanOrEqualTo(Byte value) {
            addCriterion("master_client <=", value, "masterClient");
            return (Criteria) this;
        }

        public Criteria andMasterClientIn(List<Byte> values) {
            addCriterion("master_client in", values, "masterClient");
            return (Criteria) this;
        }

        public Criteria andMasterClientNotIn(List<Byte> values) {
            addCriterion("master_client not in", values, "masterClient");
            return (Criteria) this;
        }

        public Criteria andMasterClientBetween(Byte value1, Byte value2) {
            addCriterion("master_client between", value1, value2, "masterClient");
            return (Criteria) this;
        }

        public Criteria andMasterClientNotBetween(Byte value1, Byte value2) {
            addCriterion("master_client not between", value1, value2, "masterClient");
            return (Criteria) this;
        }

        public Criteria andMasterTimeLoanIsNull() {
            addCriterion("master_time_loan is null");
            return (Criteria) this;
        }

        public Criteria andMasterTimeLoanIsNotNull() {
            addCriterion("master_time_loan is not null");
            return (Criteria) this;
        }

        public Criteria andMasterTimeLoanEqualTo(Date value) {
            addCriterion("master_time_loan =", value, "masterTimeLoan");
            return (Criteria) this;
        }

        public Criteria andMasterTimeLoanNotEqualTo(Date value) {
            addCriterion("master_time_loan <>", value, "masterTimeLoan");
            return (Criteria) this;
        }

        public Criteria andMasterTimeLoanGreaterThan(Date value) {
            addCriterion("master_time_loan >", value, "masterTimeLoan");
            return (Criteria) this;
        }

        public Criteria andMasterTimeLoanGreaterThanOrEqualTo(Date value) {
            addCriterion("master_time_loan >=", value, "masterTimeLoan");
            return (Criteria) this;
        }

        public Criteria andMasterTimeLoanLessThan(Date value) {
            addCriterion("master_time_loan <", value, "masterTimeLoan");
            return (Criteria) this;
        }

        public Criteria andMasterTimeLoanLessThanOrEqualTo(Date value) {
            addCriterion("master_time_loan <=", value, "masterTimeLoan");
            return (Criteria) this;
        }

        public Criteria andMasterTimeLoanIn(List<Date> values) {
            addCriterion("master_time_loan in", values, "masterTimeLoan");
            return (Criteria) this;
        }

        public Criteria andMasterTimeLoanNotIn(List<Date> values) {
            addCriterion("master_time_loan not in", values, "masterTimeLoan");
            return (Criteria) this;
        }

        public Criteria andMasterTimeLoanBetween(Date value1, Date value2) {
            addCriterion("master_time_loan between", value1, value2, "masterTimeLoan");
            return (Criteria) this;
        }

        public Criteria andMasterTimeLoanNotBetween(Date value1, Date value2) {
            addCriterion("master_time_loan not between", value1, value2, "masterTimeLoan");
            return (Criteria) this;
        }

        public Criteria andMasterTimeInvestIsNull() {
            addCriterion("master_time_invest is null");
            return (Criteria) this;
        }

        public Criteria andMasterTimeInvestIsNotNull() {
            addCriterion("master_time_invest is not null");
            return (Criteria) this;
        }

        public Criteria andMasterTimeInvestEqualTo(Date value) {
            addCriterion("master_time_invest =", value, "masterTimeInvest");
            return (Criteria) this;
        }

        public Criteria andMasterTimeInvestNotEqualTo(Date value) {
            addCriterion("master_time_invest <>", value, "masterTimeInvest");
            return (Criteria) this;
        }

        public Criteria andMasterTimeInvestGreaterThan(Date value) {
            addCriterion("master_time_invest >", value, "masterTimeInvest");
            return (Criteria) this;
        }

        public Criteria andMasterTimeInvestGreaterThanOrEqualTo(Date value) {
            addCriterion("master_time_invest >=", value, "masterTimeInvest");
            return (Criteria) this;
        }

        public Criteria andMasterTimeInvestLessThan(Date value) {
            addCriterion("master_time_invest <", value, "masterTimeInvest");
            return (Criteria) this;
        }

        public Criteria andMasterTimeInvestLessThanOrEqualTo(Date value) {
            addCriterion("master_time_invest <=", value, "masterTimeInvest");
            return (Criteria) this;
        }

        public Criteria andMasterTimeInvestIn(List<Date> values) {
            addCriterion("master_time_invest in", values, "masterTimeInvest");
            return (Criteria) this;
        }

        public Criteria andMasterTimeInvestNotIn(List<Date> values) {
            addCriterion("master_time_invest not in", values, "masterTimeInvest");
            return (Criteria) this;
        }

        public Criteria andMasterTimeInvestBetween(Date value1, Date value2) {
            addCriterion("master_time_invest between", value1, value2, "masterTimeInvest");
            return (Criteria) this;
        }

        public Criteria andMasterTimeInvestNotBetween(Date value1, Date value2) {
            addCriterion("master_time_invest not between", value1, value2, "masterTimeInvest");
            return (Criteria) this;
        }

        public Criteria andMasterTimeComplexIsNull() {
            addCriterion("master_time_complex is null");
            return (Criteria) this;
        }

        public Criteria andMasterTimeComplexIsNotNull() {
            addCriterion("master_time_complex is not null");
            return (Criteria) this;
        }

        public Criteria andMasterTimeComplexEqualTo(Date value) {
            addCriterion("master_time_complex =", value, "masterTimeComplex");
            return (Criteria) this;
        }

        public Criteria andMasterTimeComplexNotEqualTo(Date value) {
            addCriterion("master_time_complex <>", value, "masterTimeComplex");
            return (Criteria) this;
        }

        public Criteria andMasterTimeComplexGreaterThan(Date value) {
            addCriterion("master_time_complex >", value, "masterTimeComplex");
            return (Criteria) this;
        }

        public Criteria andMasterTimeComplexGreaterThanOrEqualTo(Date value) {
            addCriterion("master_time_complex >=", value, "masterTimeComplex");
            return (Criteria) this;
        }

        public Criteria andMasterTimeComplexLessThan(Date value) {
            addCriterion("master_time_complex <", value, "masterTimeComplex");
            return (Criteria) this;
        }

        public Criteria andMasterTimeComplexLessThanOrEqualTo(Date value) {
            addCriterion("master_time_complex <=", value, "masterTimeComplex");
            return (Criteria) this;
        }

        public Criteria andMasterTimeComplexIn(List<Date> values) {
            addCriterion("master_time_complex in", values, "masterTimeComplex");
            return (Criteria) this;
        }

        public Criteria andMasterTimeComplexNotIn(List<Date> values) {
            addCriterion("master_time_complex not in", values, "masterTimeComplex");
            return (Criteria) this;
        }

        public Criteria andMasterTimeComplexBetween(Date value1, Date value2) {
            addCriterion("master_time_complex between", value1, value2, "masterTimeComplex");
            return (Criteria) this;
        }

        public Criteria andMasterTimeComplexNotBetween(Date value1, Date value2) {
            addCriterion("master_time_complex not between", value1, value2, "masterTimeComplex");
            return (Criteria) this;
        }

        public Criteria andVipStatusIsNull() {
            addCriterion("vip_status is null");
            return (Criteria) this;
        }

        public Criteria andVipStatusIsNotNull() {
            addCriterion("vip_status is not null");
            return (Criteria) this;
        }

        public Criteria andVipStatusEqualTo(Boolean value) {
            addCriterion("vip_status =", value, "vipStatus");
            return (Criteria) this;
        }

        public Criteria andVipStatusNotEqualTo(Boolean value) {
            addCriterion("vip_status <>", value, "vipStatus");
            return (Criteria) this;
        }

        public Criteria andVipStatusGreaterThan(Boolean value) {
            addCriterion("vip_status >", value, "vipStatus");
            return (Criteria) this;
        }

        public Criteria andVipStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("vip_status >=", value, "vipStatus");
            return (Criteria) this;
        }

        public Criteria andVipStatusLessThan(Boolean value) {
            addCriterion("vip_status <", value, "vipStatus");
            return (Criteria) this;
        }

        public Criteria andVipStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("vip_status <=", value, "vipStatus");
            return (Criteria) this;
        }

        public Criteria andVipStatusIn(List<Boolean> values) {
            addCriterion("vip_status in", values, "vipStatus");
            return (Criteria) this;
        }

        public Criteria andVipStatusNotIn(List<Boolean> values) {
            addCriterion("vip_status not in", values, "vipStatus");
            return (Criteria) this;
        }

        public Criteria andVipStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("vip_status between", value1, value2, "vipStatus");
            return (Criteria) this;
        }

        public Criteria andVipStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("vip_status not between", value1, value2, "vipStatus");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNull() {
            addCriterion("balance is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("balance is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(BigDecimal value) {
            addCriterion("balance =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(BigDecimal value) {
            addCriterion("balance <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(BigDecimal value) {
            addCriterion("balance >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("balance >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(BigDecimal value) {
            addCriterion("balance <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("balance <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<BigDecimal> values) {
            addCriterion("balance in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<BigDecimal> values) {
            addCriterion("balance not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance not between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalance2IsNull() {
            addCriterion("balance2 is null");
            return (Criteria) this;
        }

        public Criteria andBalance2IsNotNull() {
            addCriterion("balance2 is not null");
            return (Criteria) this;
        }

        public Criteria andBalance2EqualTo(BigDecimal value) {
            addCriterion("balance2 =", value, "balance2");
            return (Criteria) this;
        }

        public Criteria andBalance2NotEqualTo(BigDecimal value) {
            addCriterion("balance2 <>", value, "balance2");
            return (Criteria) this;
        }

        public Criteria andBalance2GreaterThan(BigDecimal value) {
            addCriterion("balance2 >", value, "balance2");
            return (Criteria) this;
        }

        public Criteria andBalance2GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("balance2 >=", value, "balance2");
            return (Criteria) this;
        }

        public Criteria andBalance2LessThan(BigDecimal value) {
            addCriterion("balance2 <", value, "balance2");
            return (Criteria) this;
        }

        public Criteria andBalance2LessThanOrEqualTo(BigDecimal value) {
            addCriterion("balance2 <=", value, "balance2");
            return (Criteria) this;
        }

        public Criteria andBalance2In(List<BigDecimal> values) {
            addCriterion("balance2 in", values, "balance2");
            return (Criteria) this;
        }

        public Criteria andBalance2NotIn(List<BigDecimal> values) {
            addCriterion("balance2 not in", values, "balance2");
            return (Criteria) this;
        }

        public Criteria andBalance2Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance2 between", value1, value2, "balance2");
            return (Criteria) this;
        }

        public Criteria andBalance2NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance2 not between", value1, value2, "balance2");
            return (Criteria) this;
        }

        public Criteria andFreezeIsNull() {
            addCriterion("freeze is null");
            return (Criteria) this;
        }

        public Criteria andFreezeIsNotNull() {
            addCriterion("freeze is not null");
            return (Criteria) this;
        }

        public Criteria andFreezeEqualTo(BigDecimal value) {
            addCriterion("freeze =", value, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeNotEqualTo(BigDecimal value) {
            addCriterion("freeze <>", value, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeGreaterThan(BigDecimal value) {
            addCriterion("freeze >", value, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freeze >=", value, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeLessThan(BigDecimal value) {
            addCriterion("freeze <", value, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freeze <=", value, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeIn(List<BigDecimal> values) {
            addCriterion("freeze in", values, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeNotIn(List<BigDecimal> values) {
            addCriterion("freeze not in", values, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freeze between", value1, value2, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freeze not between", value1, value2, "freeze");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Integer value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Integer value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Integer value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Integer value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Integer value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Integer> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Integer> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Integer value1, Integer value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andCreditScoreIsNull() {
            addCriterion("credit_score is null");
            return (Criteria) this;
        }

        public Criteria andCreditScoreIsNotNull() {
            addCriterion("credit_score is not null");
            return (Criteria) this;
        }

        public Criteria andCreditScoreEqualTo(Integer value) {
            addCriterion("credit_score =", value, "creditScore");
            return (Criteria) this;
        }

        public Criteria andCreditScoreNotEqualTo(Integer value) {
            addCriterion("credit_score <>", value, "creditScore");
            return (Criteria) this;
        }

        public Criteria andCreditScoreGreaterThan(Integer value) {
            addCriterion("credit_score >", value, "creditScore");
            return (Criteria) this;
        }

        public Criteria andCreditScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("credit_score >=", value, "creditScore");
            return (Criteria) this;
        }

        public Criteria andCreditScoreLessThan(Integer value) {
            addCriterion("credit_score <", value, "creditScore");
            return (Criteria) this;
        }

        public Criteria andCreditScoreLessThanOrEqualTo(Integer value) {
            addCriterion("credit_score <=", value, "creditScore");
            return (Criteria) this;
        }

        public Criteria andCreditScoreIn(List<Integer> values) {
            addCriterion("credit_score in", values, "creditScore");
            return (Criteria) this;
        }

        public Criteria andCreditScoreNotIn(List<Integer> values) {
            addCriterion("credit_score not in", values, "creditScore");
            return (Criteria) this;
        }

        public Criteria andCreditScoreBetween(Integer value1, Integer value2) {
            addCriterion("credit_score between", value1, value2, "creditScore");
            return (Criteria) this;
        }

        public Criteria andCreditScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("credit_score not between", value1, value2, "creditScore");
            return (Criteria) this;
        }

        public Criteria andCreditLevelIdIsNull() {
            addCriterion("credit_level_id is null");
            return (Criteria) this;
        }

        public Criteria andCreditLevelIdIsNotNull() {
            addCriterion("credit_level_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreditLevelIdEqualTo(Integer value) {
            addCriterion("credit_level_id =", value, "creditLevelId");
            return (Criteria) this;
        }

        public Criteria andCreditLevelIdNotEqualTo(Integer value) {
            addCriterion("credit_level_id <>", value, "creditLevelId");
            return (Criteria) this;
        }

        public Criteria andCreditLevelIdGreaterThan(Integer value) {
            addCriterion("credit_level_id >", value, "creditLevelId");
            return (Criteria) this;
        }

        public Criteria andCreditLevelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("credit_level_id >=", value, "creditLevelId");
            return (Criteria) this;
        }

        public Criteria andCreditLevelIdLessThan(Integer value) {
            addCriterion("credit_level_id <", value, "creditLevelId");
            return (Criteria) this;
        }

        public Criteria andCreditLevelIdLessThanOrEqualTo(Integer value) {
            addCriterion("credit_level_id <=", value, "creditLevelId");
            return (Criteria) this;
        }

        public Criteria andCreditLevelIdIn(List<Integer> values) {
            addCriterion("credit_level_id in", values, "creditLevelId");
            return (Criteria) this;
        }

        public Criteria andCreditLevelIdNotIn(List<Integer> values) {
            addCriterion("credit_level_id not in", values, "creditLevelId");
            return (Criteria) this;
        }

        public Criteria andCreditLevelIdBetween(Integer value1, Integer value2) {
            addCriterion("credit_level_id between", value1, value2, "creditLevelId");
            return (Criteria) this;
        }

        public Criteria andCreditLevelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("credit_level_id not between", value1, value2, "creditLevelId");
            return (Criteria) this;
        }

        public Criteria andIsRefusedReceiveIsNull() {
            addCriterion("is_refused_receive is null");
            return (Criteria) this;
        }

        public Criteria andIsRefusedReceiveIsNotNull() {
            addCriterion("is_refused_receive is not null");
            return (Criteria) this;
        }

        public Criteria andIsRefusedReceiveEqualTo(Boolean value) {
            addCriterion("is_refused_receive =", value, "isRefusedReceive");
            return (Criteria) this;
        }

        public Criteria andIsRefusedReceiveNotEqualTo(Boolean value) {
            addCriterion("is_refused_receive <>", value, "isRefusedReceive");
            return (Criteria) this;
        }

        public Criteria andIsRefusedReceiveGreaterThan(Boolean value) {
            addCriterion("is_refused_receive >", value, "isRefusedReceive");
            return (Criteria) this;
        }

        public Criteria andIsRefusedReceiveGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_refused_receive >=", value, "isRefusedReceive");
            return (Criteria) this;
        }

        public Criteria andIsRefusedReceiveLessThan(Boolean value) {
            addCriterion("is_refused_receive <", value, "isRefusedReceive");
            return (Criteria) this;
        }

        public Criteria andIsRefusedReceiveLessThanOrEqualTo(Boolean value) {
            addCriterion("is_refused_receive <=", value, "isRefusedReceive");
            return (Criteria) this;
        }

        public Criteria andIsRefusedReceiveIn(List<Boolean> values) {
            addCriterion("is_refused_receive in", values, "isRefusedReceive");
            return (Criteria) this;
        }

        public Criteria andIsRefusedReceiveNotIn(List<Boolean> values) {
            addCriterion("is_refused_receive not in", values, "isRefusedReceive");
            return (Criteria) this;
        }

        public Criteria andIsRefusedReceiveBetween(Boolean value1, Boolean value2) {
            addCriterion("is_refused_receive between", value1, value2, "isRefusedReceive");
            return (Criteria) this;
        }

        public Criteria andIsRefusedReceiveNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_refused_receive not between", value1, value2, "isRefusedReceive");
            return (Criteria) this;
        }

        public Criteria andRefusedTimeIsNull() {
            addCriterion("refused_time is null");
            return (Criteria) this;
        }

        public Criteria andRefusedTimeIsNotNull() {
            addCriterion("refused_time is not null");
            return (Criteria) this;
        }

        public Criteria andRefusedTimeEqualTo(Date value) {
            addCriterion("refused_time =", value, "refusedTime");
            return (Criteria) this;
        }

        public Criteria andRefusedTimeNotEqualTo(Date value) {
            addCriterion("refused_time <>", value, "refusedTime");
            return (Criteria) this;
        }

        public Criteria andRefusedTimeGreaterThan(Date value) {
            addCriterion("refused_time >", value, "refusedTime");
            return (Criteria) this;
        }

        public Criteria andRefusedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("refused_time >=", value, "refusedTime");
            return (Criteria) this;
        }

        public Criteria andRefusedTimeLessThan(Date value) {
            addCriterion("refused_time <", value, "refusedTime");
            return (Criteria) this;
        }

        public Criteria andRefusedTimeLessThanOrEqualTo(Date value) {
            addCriterion("refused_time <=", value, "refusedTime");
            return (Criteria) this;
        }

        public Criteria andRefusedTimeIn(List<Date> values) {
            addCriterion("refused_time in", values, "refusedTime");
            return (Criteria) this;
        }

        public Criteria andRefusedTimeNotIn(List<Date> values) {
            addCriterion("refused_time not in", values, "refusedTime");
            return (Criteria) this;
        }

        public Criteria andRefusedTimeBetween(Date value1, Date value2) {
            addCriterion("refused_time between", value1, value2, "refusedTime");
            return (Criteria) this;
        }

        public Criteria andRefusedTimeNotBetween(Date value1, Date value2) {
            addCriterion("refused_time not between", value1, value2, "refusedTime");
            return (Criteria) this;
        }

        public Criteria andRefusedReasonIsNull() {
            addCriterion("refused_reason is null");
            return (Criteria) this;
        }

        public Criteria andRefusedReasonIsNotNull() {
            addCriterion("refused_reason is not null");
            return (Criteria) this;
        }

        public Criteria andRefusedReasonEqualTo(String value) {
            addCriterion("refused_reason =", value, "refusedReason");
            return (Criteria) this;
        }

        public Criteria andRefusedReasonNotEqualTo(String value) {
            addCriterion("refused_reason <>", value, "refusedReason");
            return (Criteria) this;
        }

        public Criteria andRefusedReasonGreaterThan(String value) {
            addCriterion("refused_reason >", value, "refusedReason");
            return (Criteria) this;
        }

        public Criteria andRefusedReasonGreaterThanOrEqualTo(String value) {
            addCriterion("refused_reason >=", value, "refusedReason");
            return (Criteria) this;
        }

        public Criteria andRefusedReasonLessThan(String value) {
            addCriterion("refused_reason <", value, "refusedReason");
            return (Criteria) this;
        }

        public Criteria andRefusedReasonLessThanOrEqualTo(String value) {
            addCriterion("refused_reason <=", value, "refusedReason");
            return (Criteria) this;
        }

        public Criteria andRefusedReasonLike(String value) {
            addCriterion("refused_reason like", value, "refusedReason");
            return (Criteria) this;
        }

        public Criteria andRefusedReasonNotLike(String value) {
            addCriterion("refused_reason not like", value, "refusedReason");
            return (Criteria) this;
        }

        public Criteria andRefusedReasonIn(List<String> values) {
            addCriterion("refused_reason in", values, "refusedReason");
            return (Criteria) this;
        }

        public Criteria andRefusedReasonNotIn(List<String> values) {
            addCriterion("refused_reason not in", values, "refusedReason");
            return (Criteria) this;
        }

        public Criteria andRefusedReasonBetween(String value1, String value2) {
            addCriterion("refused_reason between", value1, value2, "refusedReason");
            return (Criteria) this;
        }

        public Criteria andRefusedReasonNotBetween(String value1, String value2) {
            addCriterion("refused_reason not between", value1, value2, "refusedReason");
            return (Criteria) this;
        }

        public Criteria andIsBlacklistIsNull() {
            addCriterion("is_blacklist is null");
            return (Criteria) this;
        }

        public Criteria andIsBlacklistIsNotNull() {
            addCriterion("is_blacklist is not null");
            return (Criteria) this;
        }

        public Criteria andIsBlacklistEqualTo(Boolean value) {
            addCriterion("is_blacklist =", value, "isBlacklist");
            return (Criteria) this;
        }

        public Criteria andIsBlacklistNotEqualTo(Boolean value) {
            addCriterion("is_blacklist <>", value, "isBlacklist");
            return (Criteria) this;
        }

        public Criteria andIsBlacklistGreaterThan(Boolean value) {
            addCriterion("is_blacklist >", value, "isBlacklist");
            return (Criteria) this;
        }

        public Criteria andIsBlacklistGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_blacklist >=", value, "isBlacklist");
            return (Criteria) this;
        }

        public Criteria andIsBlacklistLessThan(Boolean value) {
            addCriterion("is_blacklist <", value, "isBlacklist");
            return (Criteria) this;
        }

        public Criteria andIsBlacklistLessThanOrEqualTo(Boolean value) {
            addCriterion("is_blacklist <=", value, "isBlacklist");
            return (Criteria) this;
        }

        public Criteria andIsBlacklistIn(List<Boolean> values) {
            addCriterion("is_blacklist in", values, "isBlacklist");
            return (Criteria) this;
        }

        public Criteria andIsBlacklistNotIn(List<Boolean> values) {
            addCriterion("is_blacklist not in", values, "isBlacklist");
            return (Criteria) this;
        }

        public Criteria andIsBlacklistBetween(Boolean value1, Boolean value2) {
            addCriterion("is_blacklist between", value1, value2, "isBlacklist");
            return (Criteria) this;
        }

        public Criteria andIsBlacklistNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_blacklist not between", value1, value2, "isBlacklist");
            return (Criteria) this;
        }

        public Criteria andJoinedTimeIsNull() {
            addCriterion("joined_time is null");
            return (Criteria) this;
        }

        public Criteria andJoinedTimeIsNotNull() {
            addCriterion("joined_time is not null");
            return (Criteria) this;
        }

        public Criteria andJoinedTimeEqualTo(Date value) {
            addCriterion("joined_time =", value, "joinedTime");
            return (Criteria) this;
        }

        public Criteria andJoinedTimeNotEqualTo(Date value) {
            addCriterion("joined_time <>", value, "joinedTime");
            return (Criteria) this;
        }

        public Criteria andJoinedTimeGreaterThan(Date value) {
            addCriterion("joined_time >", value, "joinedTime");
            return (Criteria) this;
        }

        public Criteria andJoinedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("joined_time >=", value, "joinedTime");
            return (Criteria) this;
        }

        public Criteria andJoinedTimeLessThan(Date value) {
            addCriterion("joined_time <", value, "joinedTime");
            return (Criteria) this;
        }

        public Criteria andJoinedTimeLessThanOrEqualTo(Date value) {
            addCriterion("joined_time <=", value, "joinedTime");
            return (Criteria) this;
        }

        public Criteria andJoinedTimeIn(List<Date> values) {
            addCriterion("joined_time in", values, "joinedTime");
            return (Criteria) this;
        }

        public Criteria andJoinedTimeNotIn(List<Date> values) {
            addCriterion("joined_time not in", values, "joinedTime");
            return (Criteria) this;
        }

        public Criteria andJoinedTimeBetween(Date value1, Date value2) {
            addCriterion("joined_time between", value1, value2, "joinedTime");
            return (Criteria) this;
        }

        public Criteria andJoinedTimeNotBetween(Date value1, Date value2) {
            addCriterion("joined_time not between", value1, value2, "joinedTime");
            return (Criteria) this;
        }

        public Criteria andJoinedReasonIsNull() {
            addCriterion("joined_reason is null");
            return (Criteria) this;
        }

        public Criteria andJoinedReasonIsNotNull() {
            addCriterion("joined_reason is not null");
            return (Criteria) this;
        }

        public Criteria andJoinedReasonEqualTo(String value) {
            addCriterion("joined_reason =", value, "joinedReason");
            return (Criteria) this;
        }

        public Criteria andJoinedReasonNotEqualTo(String value) {
            addCriterion("joined_reason <>", value, "joinedReason");
            return (Criteria) this;
        }

        public Criteria andJoinedReasonGreaterThan(String value) {
            addCriterion("joined_reason >", value, "joinedReason");
            return (Criteria) this;
        }

        public Criteria andJoinedReasonGreaterThanOrEqualTo(String value) {
            addCriterion("joined_reason >=", value, "joinedReason");
            return (Criteria) this;
        }

        public Criteria andJoinedReasonLessThan(String value) {
            addCriterion("joined_reason <", value, "joinedReason");
            return (Criteria) this;
        }

        public Criteria andJoinedReasonLessThanOrEqualTo(String value) {
            addCriterion("joined_reason <=", value, "joinedReason");
            return (Criteria) this;
        }

        public Criteria andJoinedReasonLike(String value) {
            addCriterion("joined_reason like", value, "joinedReason");
            return (Criteria) this;
        }

        public Criteria andJoinedReasonNotLike(String value) {
            addCriterion("joined_reason not like", value, "joinedReason");
            return (Criteria) this;
        }

        public Criteria andJoinedReasonIn(List<String> values) {
            addCriterion("joined_reason in", values, "joinedReason");
            return (Criteria) this;
        }

        public Criteria andJoinedReasonNotIn(List<String> values) {
            addCriterion("joined_reason not in", values, "joinedReason");
            return (Criteria) this;
        }

        public Criteria andJoinedReasonBetween(String value1, String value2) {
            addCriterion("joined_reason between", value1, value2, "joinedReason");
            return (Criteria) this;
        }

        public Criteria andJoinedReasonNotBetween(String value1, String value2) {
            addCriterion("joined_reason not between", value1, value2, "joinedReason");
            return (Criteria) this;
        }

        public Criteria andAssignedTimeIsNull() {
            addCriterion("assigned_time is null");
            return (Criteria) this;
        }

        public Criteria andAssignedTimeIsNotNull() {
            addCriterion("assigned_time is not null");
            return (Criteria) this;
        }

        public Criteria andAssignedTimeEqualTo(Date value) {
            addCriterion("assigned_time =", value, "assignedTime");
            return (Criteria) this;
        }

        public Criteria andAssignedTimeNotEqualTo(Date value) {
            addCriterion("assigned_time <>", value, "assignedTime");
            return (Criteria) this;
        }

        public Criteria andAssignedTimeGreaterThan(Date value) {
            addCriterion("assigned_time >", value, "assignedTime");
            return (Criteria) this;
        }

        public Criteria andAssignedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("assigned_time >=", value, "assignedTime");
            return (Criteria) this;
        }

        public Criteria andAssignedTimeLessThan(Date value) {
            addCriterion("assigned_time <", value, "assignedTime");
            return (Criteria) this;
        }

        public Criteria andAssignedTimeLessThanOrEqualTo(Date value) {
            addCriterion("assigned_time <=", value, "assignedTime");
            return (Criteria) this;
        }

        public Criteria andAssignedTimeIn(List<Date> values) {
            addCriterion("assigned_time in", values, "assignedTime");
            return (Criteria) this;
        }

        public Criteria andAssignedTimeNotIn(List<Date> values) {
            addCriterion("assigned_time not in", values, "assignedTime");
            return (Criteria) this;
        }

        public Criteria andAssignedTimeBetween(Date value1, Date value2) {
            addCriterion("assigned_time between", value1, value2, "assignedTime");
            return (Criteria) this;
        }

        public Criteria andAssignedTimeNotBetween(Date value1, Date value2) {
            addCriterion("assigned_time not between", value1, value2, "assignedTime");
            return (Criteria) this;
        }

        public Criteria andAssignedToSupervisorIdIsNull() {
            addCriterion("assigned_to_supervisor_id is null");
            return (Criteria) this;
        }

        public Criteria andAssignedToSupervisorIdIsNotNull() {
            addCriterion("assigned_to_supervisor_id is not null");
            return (Criteria) this;
        }

        public Criteria andAssignedToSupervisorIdEqualTo(Long value) {
            addCriterion("assigned_to_supervisor_id =", value, "assignedToSupervisorId");
            return (Criteria) this;
        }

        public Criteria andAssignedToSupervisorIdNotEqualTo(Long value) {
            addCriterion("assigned_to_supervisor_id <>", value, "assignedToSupervisorId");
            return (Criteria) this;
        }

        public Criteria andAssignedToSupervisorIdGreaterThan(Long value) {
            addCriterion("assigned_to_supervisor_id >", value, "assignedToSupervisorId");
            return (Criteria) this;
        }

        public Criteria andAssignedToSupervisorIdGreaterThanOrEqualTo(Long value) {
            addCriterion("assigned_to_supervisor_id >=", value, "assignedToSupervisorId");
            return (Criteria) this;
        }

        public Criteria andAssignedToSupervisorIdLessThan(Long value) {
            addCriterion("assigned_to_supervisor_id <", value, "assignedToSupervisorId");
            return (Criteria) this;
        }

        public Criteria andAssignedToSupervisorIdLessThanOrEqualTo(Long value) {
            addCriterion("assigned_to_supervisor_id <=", value, "assignedToSupervisorId");
            return (Criteria) this;
        }

        public Criteria andAssignedToSupervisorIdIn(List<Long> values) {
            addCriterion("assigned_to_supervisor_id in", values, "assignedToSupervisorId");
            return (Criteria) this;
        }

        public Criteria andAssignedToSupervisorIdNotIn(List<Long> values) {
            addCriterion("assigned_to_supervisor_id not in", values, "assignedToSupervisorId");
            return (Criteria) this;
        }

        public Criteria andAssignedToSupervisorIdBetween(Long value1, Long value2) {
            addCriterion("assigned_to_supervisor_id between", value1, value2, "assignedToSupervisorId");
            return (Criteria) this;
        }

        public Criteria andAssignedToSupervisorIdNotBetween(Long value1, Long value2) {
            addCriterion("assigned_to_supervisor_id not between", value1, value2, "assignedToSupervisorId");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNull() {
            addCriterion("telephone is null");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNotNull() {
            addCriterion("telephone is not null");
            return (Criteria) this;
        }

        public Criteria andTelephoneEqualTo(String value) {
            addCriterion("telephone =", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotEqualTo(String value) {
            addCriterion("telephone <>", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThan(String value) {
            addCriterion("telephone >", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("telephone >=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThan(String value) {
            addCriterion("telephone <", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThanOrEqualTo(String value) {
            addCriterion("telephone <=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLike(String value) {
            addCriterion("telephone like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotLike(String value) {
            addCriterion("telephone not like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneIn(List<String> values) {
            addCriterion("telephone in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotIn(List<String> values) {
            addCriterion("telephone not in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneBetween(String value1, String value2) {
            addCriterion("telephone between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotBetween(String value1, String value2) {
            addCriterion("telephone not between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andCreditLineIsNull() {
            addCriterion("credit_line is null");
            return (Criteria) this;
        }

        public Criteria andCreditLineIsNotNull() {
            addCriterion("credit_line is not null");
            return (Criteria) this;
        }

        public Criteria andCreditLineEqualTo(BigDecimal value) {
            addCriterion("credit_line =", value, "creditLine");
            return (Criteria) this;
        }

        public Criteria andCreditLineNotEqualTo(BigDecimal value) {
            addCriterion("credit_line <>", value, "creditLine");
            return (Criteria) this;
        }

        public Criteria andCreditLineGreaterThan(BigDecimal value) {
            addCriterion("credit_line >", value, "creditLine");
            return (Criteria) this;
        }

        public Criteria andCreditLineGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("credit_line >=", value, "creditLine");
            return (Criteria) this;
        }

        public Criteria andCreditLineLessThan(BigDecimal value) {
            addCriterion("credit_line <", value, "creditLine");
            return (Criteria) this;
        }

        public Criteria andCreditLineLessThanOrEqualTo(BigDecimal value) {
            addCriterion("credit_line <=", value, "creditLine");
            return (Criteria) this;
        }

        public Criteria andCreditLineIn(List<BigDecimal> values) {
            addCriterion("credit_line in", values, "creditLine");
            return (Criteria) this;
        }

        public Criteria andCreditLineNotIn(List<BigDecimal> values) {
            addCriterion("credit_line not in", values, "creditLine");
            return (Criteria) this;
        }

        public Criteria andCreditLineBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("credit_line between", value1, value2, "creditLine");
            return (Criteria) this;
        }

        public Criteria andCreditLineNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("credit_line not between", value1, value2, "creditLine");
            return (Criteria) this;
        }

        public Criteria andLastCreditLineIsNull() {
            addCriterion("last_credit_line is null");
            return (Criteria) this;
        }

        public Criteria andLastCreditLineIsNotNull() {
            addCriterion("last_credit_line is not null");
            return (Criteria) this;
        }

        public Criteria andLastCreditLineEqualTo(BigDecimal value) {
            addCriterion("last_credit_line =", value, "lastCreditLine");
            return (Criteria) this;
        }

        public Criteria andLastCreditLineNotEqualTo(BigDecimal value) {
            addCriterion("last_credit_line <>", value, "lastCreditLine");
            return (Criteria) this;
        }

        public Criteria andLastCreditLineGreaterThan(BigDecimal value) {
            addCriterion("last_credit_line >", value, "lastCreditLine");
            return (Criteria) this;
        }

        public Criteria andLastCreditLineGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("last_credit_line >=", value, "lastCreditLine");
            return (Criteria) this;
        }

        public Criteria andLastCreditLineLessThan(BigDecimal value) {
            addCriterion("last_credit_line <", value, "lastCreditLine");
            return (Criteria) this;
        }

        public Criteria andLastCreditLineLessThanOrEqualTo(BigDecimal value) {
            addCriterion("last_credit_line <=", value, "lastCreditLine");
            return (Criteria) this;
        }

        public Criteria andLastCreditLineIn(List<BigDecimal> values) {
            addCriterion("last_credit_line in", values, "lastCreditLine");
            return (Criteria) this;
        }

        public Criteria andLastCreditLineNotIn(List<BigDecimal> values) {
            addCriterion("last_credit_line not in", values, "lastCreditLine");
            return (Criteria) this;
        }

        public Criteria andLastCreditLineBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("last_credit_line between", value1, value2, "lastCreditLine");
            return (Criteria) this;
        }

        public Criteria andLastCreditLineNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("last_credit_line not between", value1, value2, "lastCreditLine");
            return (Criteria) this;
        }

        public Criteria andIsActiveIsNull() {
            addCriterion("is_active is null");
            return (Criteria) this;
        }

        public Criteria andIsActiveIsNotNull() {
            addCriterion("is_active is not null");
            return (Criteria) this;
        }

        public Criteria andIsActiveEqualTo(Boolean value) {
            addCriterion("is_active =", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveNotEqualTo(Boolean value) {
            addCriterion("is_active <>", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveGreaterThan(Boolean value) {
            addCriterion("is_active >", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_active >=", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveLessThan(Boolean value) {
            addCriterion("is_active <", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveLessThanOrEqualTo(Boolean value) {
            addCriterion("is_active <=", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveIn(List<Boolean> values) {
            addCriterion("is_active in", values, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveNotIn(List<Boolean> values) {
            addCriterion("is_active not in", values, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveBetween(Boolean value1, Boolean value2) {
            addCriterion("is_active between", value1, value2, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_active not between", value1, value2, "isActive");
            return (Criteria) this;
        }

        public Criteria andSign1IsNull() {
            addCriterion("sign1 is null");
            return (Criteria) this;
        }

        public Criteria andSign1IsNotNull() {
            addCriterion("sign1 is not null");
            return (Criteria) this;
        }

        public Criteria andSign1EqualTo(String value) {
            addCriterion("sign1 =", value, "sign1");
            return (Criteria) this;
        }

        public Criteria andSign1NotEqualTo(String value) {
            addCriterion("sign1 <>", value, "sign1");
            return (Criteria) this;
        }

        public Criteria andSign1GreaterThan(String value) {
            addCriterion("sign1 >", value, "sign1");
            return (Criteria) this;
        }

        public Criteria andSign1GreaterThanOrEqualTo(String value) {
            addCriterion("sign1 >=", value, "sign1");
            return (Criteria) this;
        }

        public Criteria andSign1LessThan(String value) {
            addCriterion("sign1 <", value, "sign1");
            return (Criteria) this;
        }

        public Criteria andSign1LessThanOrEqualTo(String value) {
            addCriterion("sign1 <=", value, "sign1");
            return (Criteria) this;
        }

        public Criteria andSign1Like(String value) {
            addCriterion("sign1 like", value, "sign1");
            return (Criteria) this;
        }

        public Criteria andSign1NotLike(String value) {
            addCriterion("sign1 not like", value, "sign1");
            return (Criteria) this;
        }

        public Criteria andSign1In(List<String> values) {
            addCriterion("sign1 in", values, "sign1");
            return (Criteria) this;
        }

        public Criteria andSign1NotIn(List<String> values) {
            addCriterion("sign1 not in", values, "sign1");
            return (Criteria) this;
        }

        public Criteria andSign1Between(String value1, String value2) {
            addCriterion("sign1 between", value1, value2, "sign1");
            return (Criteria) this;
        }

        public Criteria andSign1NotBetween(String value1, String value2) {
            addCriterion("sign1 not between", value1, value2, "sign1");
            return (Criteria) this;
        }

        public Criteria andSign2IsNull() {
            addCriterion("sign2 is null");
            return (Criteria) this;
        }

        public Criteria andSign2IsNotNull() {
            addCriterion("sign2 is not null");
            return (Criteria) this;
        }

        public Criteria andSign2EqualTo(String value) {
            addCriterion("sign2 =", value, "sign2");
            return (Criteria) this;
        }

        public Criteria andSign2NotEqualTo(String value) {
            addCriterion("sign2 <>", value, "sign2");
            return (Criteria) this;
        }

        public Criteria andSign2GreaterThan(String value) {
            addCriterion("sign2 >", value, "sign2");
            return (Criteria) this;
        }

        public Criteria andSign2GreaterThanOrEqualTo(String value) {
            addCriterion("sign2 >=", value, "sign2");
            return (Criteria) this;
        }

        public Criteria andSign2LessThan(String value) {
            addCriterion("sign2 <", value, "sign2");
            return (Criteria) this;
        }

        public Criteria andSign2LessThanOrEqualTo(String value) {
            addCriterion("sign2 <=", value, "sign2");
            return (Criteria) this;
        }

        public Criteria andSign2Like(String value) {
            addCriterion("sign2 like", value, "sign2");
            return (Criteria) this;
        }

        public Criteria andSign2NotLike(String value) {
            addCriterion("sign2 not like", value, "sign2");
            return (Criteria) this;
        }

        public Criteria andSign2In(List<String> values) {
            addCriterion("sign2 in", values, "sign2");
            return (Criteria) this;
        }

        public Criteria andSign2NotIn(List<String> values) {
            addCriterion("sign2 not in", values, "sign2");
            return (Criteria) this;
        }

        public Criteria andSign2Between(String value1, String value2) {
            addCriterion("sign2 between", value1, value2, "sign2");
            return (Criteria) this;
        }

        public Criteria andSign2NotBetween(String value1, String value2) {
            addCriterion("sign2 not between", value1, value2, "sign2");
            return (Criteria) this;
        }

        public Criteria andQqKeyIsNull() {
            addCriterion("qq_key is null");
            return (Criteria) this;
        }

        public Criteria andQqKeyIsNotNull() {
            addCriterion("qq_key is not null");
            return (Criteria) this;
        }

        public Criteria andQqKeyEqualTo(String value) {
            addCriterion("qq_key =", value, "qqKey");
            return (Criteria) this;
        }

        public Criteria andQqKeyNotEqualTo(String value) {
            addCriterion("qq_key <>", value, "qqKey");
            return (Criteria) this;
        }

        public Criteria andQqKeyGreaterThan(String value) {
            addCriterion("qq_key >", value, "qqKey");
            return (Criteria) this;
        }

        public Criteria andQqKeyGreaterThanOrEqualTo(String value) {
            addCriterion("qq_key >=", value, "qqKey");
            return (Criteria) this;
        }

        public Criteria andQqKeyLessThan(String value) {
            addCriterion("qq_key <", value, "qqKey");
            return (Criteria) this;
        }

        public Criteria andQqKeyLessThanOrEqualTo(String value) {
            addCriterion("qq_key <=", value, "qqKey");
            return (Criteria) this;
        }

        public Criteria andQqKeyLike(String value) {
            addCriterion("qq_key like", value, "qqKey");
            return (Criteria) this;
        }

        public Criteria andQqKeyNotLike(String value) {
            addCriterion("qq_key not like", value, "qqKey");
            return (Criteria) this;
        }

        public Criteria andQqKeyIn(List<String> values) {
            addCriterion("qq_key in", values, "qqKey");
            return (Criteria) this;
        }

        public Criteria andQqKeyNotIn(List<String> values) {
            addCriterion("qq_key not in", values, "qqKey");
            return (Criteria) this;
        }

        public Criteria andQqKeyBetween(String value1, String value2) {
            addCriterion("qq_key between", value1, value2, "qqKey");
            return (Criteria) this;
        }

        public Criteria andQqKeyNotBetween(String value1, String value2) {
            addCriterion("qq_key not between", value1, value2, "qqKey");
            return (Criteria) this;
        }

        public Criteria andWeiboKeyIsNull() {
            addCriterion("weibo_key is null");
            return (Criteria) this;
        }

        public Criteria andWeiboKeyIsNotNull() {
            addCriterion("weibo_key is not null");
            return (Criteria) this;
        }

        public Criteria andWeiboKeyEqualTo(String value) {
            addCriterion("weibo_key =", value, "weiboKey");
            return (Criteria) this;
        }

        public Criteria andWeiboKeyNotEqualTo(String value) {
            addCriterion("weibo_key <>", value, "weiboKey");
            return (Criteria) this;
        }

        public Criteria andWeiboKeyGreaterThan(String value) {
            addCriterion("weibo_key >", value, "weiboKey");
            return (Criteria) this;
        }

        public Criteria andWeiboKeyGreaterThanOrEqualTo(String value) {
            addCriterion("weibo_key >=", value, "weiboKey");
            return (Criteria) this;
        }

        public Criteria andWeiboKeyLessThan(String value) {
            addCriterion("weibo_key <", value, "weiboKey");
            return (Criteria) this;
        }

        public Criteria andWeiboKeyLessThanOrEqualTo(String value) {
            addCriterion("weibo_key <=", value, "weiboKey");
            return (Criteria) this;
        }

        public Criteria andWeiboKeyLike(String value) {
            addCriterion("weibo_key like", value, "weiboKey");
            return (Criteria) this;
        }

        public Criteria andWeiboKeyNotLike(String value) {
            addCriterion("weibo_key not like", value, "weiboKey");
            return (Criteria) this;
        }

        public Criteria andWeiboKeyIn(List<String> values) {
            addCriterion("weibo_key in", values, "weiboKey");
            return (Criteria) this;
        }

        public Criteria andWeiboKeyNotIn(List<String> values) {
            addCriterion("weibo_key not in", values, "weiboKey");
            return (Criteria) this;
        }

        public Criteria andWeiboKeyBetween(String value1, String value2) {
            addCriterion("weibo_key between", value1, value2, "weiboKey");
            return (Criteria) this;
        }

        public Criteria andWeiboKeyNotBetween(String value1, String value2) {
            addCriterion("weibo_key not between", value1, value2, "weiboKey");
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

        public Criteria andIpsAcctNoIsNull() {
            addCriterion("ips_acct_no is null");
            return (Criteria) this;
        }

        public Criteria andIpsAcctNoIsNotNull() {
            addCriterion("ips_acct_no is not null");
            return (Criteria) this;
        }

        public Criteria andIpsAcctNoEqualTo(String value) {
            addCriterion("ips_acct_no =", value, "ipsAcctNo");
            return (Criteria) this;
        }

        public Criteria andIpsAcctNoNotEqualTo(String value) {
            addCriterion("ips_acct_no <>", value, "ipsAcctNo");
            return (Criteria) this;
        }

        public Criteria andIpsAcctNoGreaterThan(String value) {
            addCriterion("ips_acct_no >", value, "ipsAcctNo");
            return (Criteria) this;
        }

        public Criteria andIpsAcctNoGreaterThanOrEqualTo(String value) {
            addCriterion("ips_acct_no >=", value, "ipsAcctNo");
            return (Criteria) this;
        }

        public Criteria andIpsAcctNoLessThan(String value) {
            addCriterion("ips_acct_no <", value, "ipsAcctNo");
            return (Criteria) this;
        }

        public Criteria andIpsAcctNoLessThanOrEqualTo(String value) {
            addCriterion("ips_acct_no <=", value, "ipsAcctNo");
            return (Criteria) this;
        }

        public Criteria andIpsAcctNoLike(String value) {
            addCriterion("ips_acct_no like", value, "ipsAcctNo");
            return (Criteria) this;
        }

        public Criteria andIpsAcctNoNotLike(String value) {
            addCriterion("ips_acct_no not like", value, "ipsAcctNo");
            return (Criteria) this;
        }

        public Criteria andIpsAcctNoIn(List<String> values) {
            addCriterion("ips_acct_no in", values, "ipsAcctNo");
            return (Criteria) this;
        }

        public Criteria andIpsAcctNoNotIn(List<String> values) {
            addCriterion("ips_acct_no not in", values, "ipsAcctNo");
            return (Criteria) this;
        }

        public Criteria andIpsAcctNoBetween(String value1, String value2) {
            addCriterion("ips_acct_no between", value1, value2, "ipsAcctNo");
            return (Criteria) this;
        }

        public Criteria andIpsAcctNoNotBetween(String value1, String value2) {
            addCriterion("ips_acct_no not between", value1, value2, "ipsAcctNo");
            return (Criteria) this;
        }

        public Criteria andIpsBidAuthNoIsNull() {
            addCriterion("ips_bid_auth_no is null");
            return (Criteria) this;
        }

        public Criteria andIpsBidAuthNoIsNotNull() {
            addCriterion("ips_bid_auth_no is not null");
            return (Criteria) this;
        }

        public Criteria andIpsBidAuthNoEqualTo(String value) {
            addCriterion("ips_bid_auth_no =", value, "ipsBidAuthNo");
            return (Criteria) this;
        }

        public Criteria andIpsBidAuthNoNotEqualTo(String value) {
            addCriterion("ips_bid_auth_no <>", value, "ipsBidAuthNo");
            return (Criteria) this;
        }

        public Criteria andIpsBidAuthNoGreaterThan(String value) {
            addCriterion("ips_bid_auth_no >", value, "ipsBidAuthNo");
            return (Criteria) this;
        }

        public Criteria andIpsBidAuthNoGreaterThanOrEqualTo(String value) {
            addCriterion("ips_bid_auth_no >=", value, "ipsBidAuthNo");
            return (Criteria) this;
        }

        public Criteria andIpsBidAuthNoLessThan(String value) {
            addCriterion("ips_bid_auth_no <", value, "ipsBidAuthNo");
            return (Criteria) this;
        }

        public Criteria andIpsBidAuthNoLessThanOrEqualTo(String value) {
            addCriterion("ips_bid_auth_no <=", value, "ipsBidAuthNo");
            return (Criteria) this;
        }

        public Criteria andIpsBidAuthNoLike(String value) {
            addCriterion("ips_bid_auth_no like", value, "ipsBidAuthNo");
            return (Criteria) this;
        }

        public Criteria andIpsBidAuthNoNotLike(String value) {
            addCriterion("ips_bid_auth_no not like", value, "ipsBidAuthNo");
            return (Criteria) this;
        }

        public Criteria andIpsBidAuthNoIn(List<String> values) {
            addCriterion("ips_bid_auth_no in", values, "ipsBidAuthNo");
            return (Criteria) this;
        }

        public Criteria andIpsBidAuthNoNotIn(List<String> values) {
            addCriterion("ips_bid_auth_no not in", values, "ipsBidAuthNo");
            return (Criteria) this;
        }

        public Criteria andIpsBidAuthNoBetween(String value1, String value2) {
            addCriterion("ips_bid_auth_no between", value1, value2, "ipsBidAuthNo");
            return (Criteria) this;
        }

        public Criteria andIpsBidAuthNoNotBetween(String value1, String value2) {
            addCriterion("ips_bid_auth_no not between", value1, value2, "ipsBidAuthNo");
            return (Criteria) this;
        }

        public Criteria andIpsRepayAuthNoIsNull() {
            addCriterion("ips_repay_auth_no is null");
            return (Criteria) this;
        }

        public Criteria andIpsRepayAuthNoIsNotNull() {
            addCriterion("ips_repay_auth_no is not null");
            return (Criteria) this;
        }

        public Criteria andIpsRepayAuthNoEqualTo(String value) {
            addCriterion("ips_repay_auth_no =", value, "ipsRepayAuthNo");
            return (Criteria) this;
        }

        public Criteria andIpsRepayAuthNoNotEqualTo(String value) {
            addCriterion("ips_repay_auth_no <>", value, "ipsRepayAuthNo");
            return (Criteria) this;
        }

        public Criteria andIpsRepayAuthNoGreaterThan(String value) {
            addCriterion("ips_repay_auth_no >", value, "ipsRepayAuthNo");
            return (Criteria) this;
        }

        public Criteria andIpsRepayAuthNoGreaterThanOrEqualTo(String value) {
            addCriterion("ips_repay_auth_no >=", value, "ipsRepayAuthNo");
            return (Criteria) this;
        }

        public Criteria andIpsRepayAuthNoLessThan(String value) {
            addCriterion("ips_repay_auth_no <", value, "ipsRepayAuthNo");
            return (Criteria) this;
        }

        public Criteria andIpsRepayAuthNoLessThanOrEqualTo(String value) {
            addCriterion("ips_repay_auth_no <=", value, "ipsRepayAuthNo");
            return (Criteria) this;
        }

        public Criteria andIpsRepayAuthNoLike(String value) {
            addCriterion("ips_repay_auth_no like", value, "ipsRepayAuthNo");
            return (Criteria) this;
        }

        public Criteria andIpsRepayAuthNoNotLike(String value) {
            addCriterion("ips_repay_auth_no not like", value, "ipsRepayAuthNo");
            return (Criteria) this;
        }

        public Criteria andIpsRepayAuthNoIn(List<String> values) {
            addCriterion("ips_repay_auth_no in", values, "ipsRepayAuthNo");
            return (Criteria) this;
        }

        public Criteria andIpsRepayAuthNoNotIn(List<String> values) {
            addCriterion("ips_repay_auth_no not in", values, "ipsRepayAuthNo");
            return (Criteria) this;
        }

        public Criteria andIpsRepayAuthNoBetween(String value1, String value2) {
            addCriterion("ips_repay_auth_no between", value1, value2, "ipsRepayAuthNo");
            return (Criteria) this;
        }

        public Criteria andIpsRepayAuthNoNotBetween(String value1, String value2) {
            addCriterion("ips_repay_auth_no not between", value1, value2, "ipsRepayAuthNo");
            return (Criteria) this;
        }

        public Criteria andDeviceUserIdIsNull() {
            addCriterion("device_user_id is null");
            return (Criteria) this;
        }

        public Criteria andDeviceUserIdIsNotNull() {
            addCriterion("device_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceUserIdEqualTo(String value) {
            addCriterion("device_user_id =", value, "deviceUserId");
            return (Criteria) this;
        }

        public Criteria andDeviceUserIdNotEqualTo(String value) {
            addCriterion("device_user_id <>", value, "deviceUserId");
            return (Criteria) this;
        }

        public Criteria andDeviceUserIdGreaterThan(String value) {
            addCriterion("device_user_id >", value, "deviceUserId");
            return (Criteria) this;
        }

        public Criteria andDeviceUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("device_user_id >=", value, "deviceUserId");
            return (Criteria) this;
        }

        public Criteria andDeviceUserIdLessThan(String value) {
            addCriterion("device_user_id <", value, "deviceUserId");
            return (Criteria) this;
        }

        public Criteria andDeviceUserIdLessThanOrEqualTo(String value) {
            addCriterion("device_user_id <=", value, "deviceUserId");
            return (Criteria) this;
        }

        public Criteria andDeviceUserIdLike(String value) {
            addCriterion("device_user_id like", value, "deviceUserId");
            return (Criteria) this;
        }

        public Criteria andDeviceUserIdNotLike(String value) {
            addCriterion("device_user_id not like", value, "deviceUserId");
            return (Criteria) this;
        }

        public Criteria andDeviceUserIdIn(List<String> values) {
            addCriterion("device_user_id in", values, "deviceUserId");
            return (Criteria) this;
        }

        public Criteria andDeviceUserIdNotIn(List<String> values) {
            addCriterion("device_user_id not in", values, "deviceUserId");
            return (Criteria) this;
        }

        public Criteria andDeviceUserIdBetween(String value1, String value2) {
            addCriterion("device_user_id between", value1, value2, "deviceUserId");
            return (Criteria) this;
        }

        public Criteria andDeviceUserIdNotBetween(String value1, String value2) {
            addCriterion("device_user_id not between", value1, value2, "deviceUserId");
            return (Criteria) this;
        }

        public Criteria andChannelIdIsNull() {
            addCriterion("channel_id is null");
            return (Criteria) this;
        }

        public Criteria andChannelIdIsNotNull() {
            addCriterion("channel_id is not null");
            return (Criteria) this;
        }

        public Criteria andChannelIdEqualTo(String value) {
            addCriterion("channel_id =", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotEqualTo(String value) {
            addCriterion("channel_id <>", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThan(String value) {
            addCriterion("channel_id >", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThanOrEqualTo(String value) {
            addCriterion("channel_id >=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThan(String value) {
            addCriterion("channel_id <", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThanOrEqualTo(String value) {
            addCriterion("channel_id <=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLike(String value) {
            addCriterion("channel_id like", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotLike(String value) {
            addCriterion("channel_id not like", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdIn(List<String> values) {
            addCriterion("channel_id in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotIn(List<String> values) {
            addCriterion("channel_id not in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdBetween(String value1, String value2) {
            addCriterion("channel_id between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotBetween(String value1, String value2) {
            addCriterion("channel_id not between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIsNull() {
            addCriterion("device_type is null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIsNotNull() {
            addCriterion("device_type is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeEqualTo(Byte value) {
            addCriterion("device_type =", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotEqualTo(Byte value) {
            addCriterion("device_type <>", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeGreaterThan(Byte value) {
            addCriterion("device_type >", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("device_type >=", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLessThan(Byte value) {
            addCriterion("device_type <", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLessThanOrEqualTo(Byte value) {
            addCriterion("device_type <=", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIn(List<Byte> values) {
            addCriterion("device_type in", values, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotIn(List<Byte> values) {
            addCriterion("device_type not in", values, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeBetween(Byte value1, Byte value2) {
            addCriterion("device_type between", value1, value2, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("device_type not between", value1, value2, "deviceType");
            return (Criteria) this;
        }

        public Criteria andIsBillPushIsNull() {
            addCriterion("is_bill_push is null");
            return (Criteria) this;
        }

        public Criteria andIsBillPushIsNotNull() {
            addCriterion("is_bill_push is not null");
            return (Criteria) this;
        }

        public Criteria andIsBillPushEqualTo(Boolean value) {
            addCriterion("is_bill_push =", value, "isBillPush");
            return (Criteria) this;
        }

        public Criteria andIsBillPushNotEqualTo(Boolean value) {
            addCriterion("is_bill_push <>", value, "isBillPush");
            return (Criteria) this;
        }

        public Criteria andIsBillPushGreaterThan(Boolean value) {
            addCriterion("is_bill_push >", value, "isBillPush");
            return (Criteria) this;
        }

        public Criteria andIsBillPushGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_bill_push >=", value, "isBillPush");
            return (Criteria) this;
        }

        public Criteria andIsBillPushLessThan(Boolean value) {
            addCriterion("is_bill_push <", value, "isBillPush");
            return (Criteria) this;
        }

        public Criteria andIsBillPushLessThanOrEqualTo(Boolean value) {
            addCriterion("is_bill_push <=", value, "isBillPush");
            return (Criteria) this;
        }

        public Criteria andIsBillPushIn(List<Boolean> values) {
            addCriterion("is_bill_push in", values, "isBillPush");
            return (Criteria) this;
        }

        public Criteria andIsBillPushNotIn(List<Boolean> values) {
            addCriterion("is_bill_push not in", values, "isBillPush");
            return (Criteria) this;
        }

        public Criteria andIsBillPushBetween(Boolean value1, Boolean value2) {
            addCriterion("is_bill_push between", value1, value2, "isBillPush");
            return (Criteria) this;
        }

        public Criteria andIsBillPushNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_bill_push not between", value1, value2, "isBillPush");
            return (Criteria) this;
        }

        public Criteria andIsInvestPushIsNull() {
            addCriterion("is_invest_push is null");
            return (Criteria) this;
        }

        public Criteria andIsInvestPushIsNotNull() {
            addCriterion("is_invest_push is not null");
            return (Criteria) this;
        }

        public Criteria andIsInvestPushEqualTo(Boolean value) {
            addCriterion("is_invest_push =", value, "isInvestPush");
            return (Criteria) this;
        }

        public Criteria andIsInvestPushNotEqualTo(Boolean value) {
            addCriterion("is_invest_push <>", value, "isInvestPush");
            return (Criteria) this;
        }

        public Criteria andIsInvestPushGreaterThan(Boolean value) {
            addCriterion("is_invest_push >", value, "isInvestPush");
            return (Criteria) this;
        }

        public Criteria andIsInvestPushGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_invest_push >=", value, "isInvestPush");
            return (Criteria) this;
        }

        public Criteria andIsInvestPushLessThan(Boolean value) {
            addCriterion("is_invest_push <", value, "isInvestPush");
            return (Criteria) this;
        }

        public Criteria andIsInvestPushLessThanOrEqualTo(Boolean value) {
            addCriterion("is_invest_push <=", value, "isInvestPush");
            return (Criteria) this;
        }

        public Criteria andIsInvestPushIn(List<Boolean> values) {
            addCriterion("is_invest_push in", values, "isInvestPush");
            return (Criteria) this;
        }

        public Criteria andIsInvestPushNotIn(List<Boolean> values) {
            addCriterion("is_invest_push not in", values, "isInvestPush");
            return (Criteria) this;
        }

        public Criteria andIsInvestPushBetween(Boolean value1, Boolean value2) {
            addCriterion("is_invest_push between", value1, value2, "isInvestPush");
            return (Criteria) this;
        }

        public Criteria andIsInvestPushNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_invest_push not between", value1, value2, "isInvestPush");
            return (Criteria) this;
        }

        public Criteria andIsActivityPushIsNull() {
            addCriterion("is_activity_push is null");
            return (Criteria) this;
        }

        public Criteria andIsActivityPushIsNotNull() {
            addCriterion("is_activity_push is not null");
            return (Criteria) this;
        }

        public Criteria andIsActivityPushEqualTo(Boolean value) {
            addCriterion("is_activity_push =", value, "isActivityPush");
            return (Criteria) this;
        }

        public Criteria andIsActivityPushNotEqualTo(Boolean value) {
            addCriterion("is_activity_push <>", value, "isActivityPush");
            return (Criteria) this;
        }

        public Criteria andIsActivityPushGreaterThan(Boolean value) {
            addCriterion("is_activity_push >", value, "isActivityPush");
            return (Criteria) this;
        }

        public Criteria andIsActivityPushGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_activity_push >=", value, "isActivityPush");
            return (Criteria) this;
        }

        public Criteria andIsActivityPushLessThan(Boolean value) {
            addCriterion("is_activity_push <", value, "isActivityPush");
            return (Criteria) this;
        }

        public Criteria andIsActivityPushLessThanOrEqualTo(Boolean value) {
            addCriterion("is_activity_push <=", value, "isActivityPush");
            return (Criteria) this;
        }

        public Criteria andIsActivityPushIn(List<Boolean> values) {
            addCriterion("is_activity_push in", values, "isActivityPush");
            return (Criteria) this;
        }

        public Criteria andIsActivityPushNotIn(List<Boolean> values) {
            addCriterion("is_activity_push not in", values, "isActivityPush");
            return (Criteria) this;
        }

        public Criteria andIsActivityPushBetween(Boolean value1, Boolean value2) {
            addCriterion("is_activity_push between", value1, value2, "isActivityPush");
            return (Criteria) this;
        }

        public Criteria andIsActivityPushNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_activity_push not between", value1, value2, "isActivityPush");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNull() {
            addCriterion("open_id is null");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNotNull() {
            addCriterion("open_id is not null");
            return (Criteria) this;
        }

        public Criteria andOpenIdEqualTo(String value) {
            addCriterion("open_id =", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotEqualTo(String value) {
            addCriterion("open_id <>", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThan(String value) {
            addCriterion("open_id >", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("open_id >=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThan(String value) {
            addCriterion("open_id <", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThanOrEqualTo(String value) {
            addCriterion("open_id <=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLike(String value) {
            addCriterion("open_id like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotLike(String value) {
            addCriterion("open_id not like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdIn(List<String> values) {
            addCriterion("open_id in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotIn(List<String> values) {
            addCriterion("open_id not in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdBetween(String value1, String value2) {
            addCriterion("open_id between", value1, value2, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotBetween(String value1, String value2) {
            addCriterion("open_id not between", value1, value2, "openId");
            return (Criteria) this;
        }

        public Criteria andIsInvestBidIsNull() {
            addCriterion("is_invest_bid is null");
            return (Criteria) this;
        }

        public Criteria andIsInvestBidIsNotNull() {
            addCriterion("is_invest_bid is not null");
            return (Criteria) this;
        }

        public Criteria andIsInvestBidEqualTo(Boolean value) {
            addCriterion("is_invest_bid =", value, "isInvestBid");
            return (Criteria) this;
        }

        public Criteria andIsInvestBidNotEqualTo(Boolean value) {
            addCriterion("is_invest_bid <>", value, "isInvestBid");
            return (Criteria) this;
        }

        public Criteria andIsInvestBidGreaterThan(Boolean value) {
            addCriterion("is_invest_bid >", value, "isInvestBid");
            return (Criteria) this;
        }

        public Criteria andIsInvestBidGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_invest_bid >=", value, "isInvestBid");
            return (Criteria) this;
        }

        public Criteria andIsInvestBidLessThan(Boolean value) {
            addCriterion("is_invest_bid <", value, "isInvestBid");
            return (Criteria) this;
        }

        public Criteria andIsInvestBidLessThanOrEqualTo(Boolean value) {
            addCriterion("is_invest_bid <=", value, "isInvestBid");
            return (Criteria) this;
        }

        public Criteria andIsInvestBidIn(List<Boolean> values) {
            addCriterion("is_invest_bid in", values, "isInvestBid");
            return (Criteria) this;
        }

        public Criteria andIsInvestBidNotIn(List<Boolean> values) {
            addCriterion("is_invest_bid not in", values, "isInvestBid");
            return (Criteria) this;
        }

        public Criteria andIsInvestBidBetween(Boolean value1, Boolean value2) {
            addCriterion("is_invest_bid between", value1, value2, "isInvestBid");
            return (Criteria) this;
        }

        public Criteria andIsInvestBidNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_invest_bid not between", value1, value2, "isInvestBid");
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

        public Criteria andLabelIsNull() {
            addCriterion("label is null");
            return (Criteria) this;
        }

        public Criteria andLabelIsNotNull() {
            addCriterion("label is not null");
            return (Criteria) this;
        }

        public Criteria andLabelEqualTo(String value) {
            addCriterion("label =", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotEqualTo(String value) {
            addCriterion("label <>", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThan(String value) {
            addCriterion("label >", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThanOrEqualTo(String value) {
            addCriterion("label >=", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLessThan(String value) {
            addCriterion("label <", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLessThanOrEqualTo(String value) {
            addCriterion("label <=", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLike(String value) {
            addCriterion("label like", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotLike(String value) {
            addCriterion("label not like", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelIn(List<String> values) {
            addCriterion("label in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotIn(List<String> values) {
            addCriterion("label not in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelBetween(String value1, String value2) {
            addCriterion("label between", value1, value2, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotBetween(String value1, String value2) {
            addCriterion("label not between", value1, value2, "label");
            return (Criteria) this;
        }

        public Criteria andIsCrownIsNull() {
            addCriterion("is_crown is null");
            return (Criteria) this;
        }

        public Criteria andIsCrownIsNotNull() {
            addCriterion("is_crown is not null");
            return (Criteria) this;
        }

        public Criteria andIsCrownEqualTo(Integer value) {
            addCriterion("is_crown =", value, "isCrown");
            return (Criteria) this;
        }

        public Criteria andIsCrownNotEqualTo(Integer value) {
            addCriterion("is_crown <>", value, "isCrown");
            return (Criteria) this;
        }

        public Criteria andIsCrownGreaterThan(Integer value) {
            addCriterion("is_crown >", value, "isCrown");
            return (Criteria) this;
        }

        public Criteria andIsCrownGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_crown >=", value, "isCrown");
            return (Criteria) this;
        }

        public Criteria andIsCrownLessThan(Integer value) {
            addCriterion("is_crown <", value, "isCrown");
            return (Criteria) this;
        }

        public Criteria andIsCrownLessThanOrEqualTo(Integer value) {
            addCriterion("is_crown <=", value, "isCrown");
            return (Criteria) this;
        }

        public Criteria andIsCrownIn(List<Integer> values) {
            addCriterion("is_crown in", values, "isCrown");
            return (Criteria) this;
        }

        public Criteria andIsCrownNotIn(List<Integer> values) {
            addCriterion("is_crown not in", values, "isCrown");
            return (Criteria) this;
        }

        public Criteria andIsCrownBetween(Integer value1, Integer value2) {
            addCriterion("is_crown between", value1, value2, "isCrown");
            return (Criteria) this;
        }

        public Criteria andIsCrownNotBetween(Integer value1, Integer value2) {
            addCriterion("is_crown not between", value1, value2, "isCrown");
            return (Criteria) this;
        }

        public Criteria andRealIdNumberIsNull() {
            addCriterion("real_id_number is null");
            return (Criteria) this;
        }

        public Criteria andRealIdNumberIsNotNull() {
            addCriterion("real_id_number is not null");
            return (Criteria) this;
        }

        public Criteria andRealIdNumberEqualTo(String value) {
            addCriterion("real_id_number =", value, "realIdNumber");
            return (Criteria) this;
        }

        public Criteria andRealIdNumberNotEqualTo(String value) {
            addCriterion("real_id_number <>", value, "realIdNumber");
            return (Criteria) this;
        }

        public Criteria andRealIdNumberGreaterThan(String value) {
            addCriterion("real_id_number >", value, "realIdNumber");
            return (Criteria) this;
        }

        public Criteria andRealIdNumberGreaterThanOrEqualTo(String value) {
            addCriterion("real_id_number >=", value, "realIdNumber");
            return (Criteria) this;
        }

        public Criteria andRealIdNumberLessThan(String value) {
            addCriterion("real_id_number <", value, "realIdNumber");
            return (Criteria) this;
        }

        public Criteria andRealIdNumberLessThanOrEqualTo(String value) {
            addCriterion("real_id_number <=", value, "realIdNumber");
            return (Criteria) this;
        }

        public Criteria andRealIdNumberLike(String value) {
            addCriterion("real_id_number like", value, "realIdNumber");
            return (Criteria) this;
        }

        public Criteria andRealIdNumberNotLike(String value) {
            addCriterion("real_id_number not like", value, "realIdNumber");
            return (Criteria) this;
        }

        public Criteria andRealIdNumberIn(List<String> values) {
            addCriterion("real_id_number in", values, "realIdNumber");
            return (Criteria) this;
        }

        public Criteria andRealIdNumberNotIn(List<String> values) {
            addCriterion("real_id_number not in", values, "realIdNumber");
            return (Criteria) this;
        }

        public Criteria andRealIdNumberBetween(String value1, String value2) {
            addCriterion("real_id_number between", value1, value2, "realIdNumber");
            return (Criteria) this;
        }

        public Criteria andRealIdNumberNotBetween(String value1, String value2) {
            addCriterion("real_id_number not between", value1, value2, "realIdNumber");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andIpsIsAuthIsNull() {
            addCriterion("ips_is_auth is null");
            return (Criteria) this;
        }

        public Criteria andIpsIsAuthIsNotNull() {
            addCriterion("ips_is_auth is not null");
            return (Criteria) this;
        }

        public Criteria andIpsIsAuthEqualTo(Byte value) {
            addCriterion("ips_is_auth =", value, "ipsIsAuth");
            return (Criteria) this;
        }

        public Criteria andIpsIsAuthNotEqualTo(Byte value) {
            addCriterion("ips_is_auth <>", value, "ipsIsAuth");
            return (Criteria) this;
        }

        public Criteria andIpsIsAuthGreaterThan(Byte value) {
            addCriterion("ips_is_auth >", value, "ipsIsAuth");
            return (Criteria) this;
        }

        public Criteria andIpsIsAuthGreaterThanOrEqualTo(Byte value) {
            addCriterion("ips_is_auth >=", value, "ipsIsAuth");
            return (Criteria) this;
        }

        public Criteria andIpsIsAuthLessThan(Byte value) {
            addCriterion("ips_is_auth <", value, "ipsIsAuth");
            return (Criteria) this;
        }

        public Criteria andIpsIsAuthLessThanOrEqualTo(Byte value) {
            addCriterion("ips_is_auth <=", value, "ipsIsAuth");
            return (Criteria) this;
        }

        public Criteria andIpsIsAuthIn(List<Byte> values) {
            addCriterion("ips_is_auth in", values, "ipsIsAuth");
            return (Criteria) this;
        }

        public Criteria andIpsIsAuthNotIn(List<Byte> values) {
            addCriterion("ips_is_auth not in", values, "ipsIsAuth");
            return (Criteria) this;
        }

        public Criteria andIpsIsAuthBetween(Byte value1, Byte value2) {
            addCriterion("ips_is_auth between", value1, value2, "ipsIsAuth");
            return (Criteria) this;
        }

        public Criteria andIpsIsAuthNotBetween(Byte value1, Byte value2) {
            addCriterion("ips_is_auth not between", value1, value2, "ipsIsAuth");
            return (Criteria) this;
        }

        public Criteria andWithdrawWayIsNull() {
            addCriterion("withdraw_way is null");
            return (Criteria) this;
        }

        public Criteria andWithdrawWayIsNotNull() {
            addCriterion("withdraw_way is not null");
            return (Criteria) this;
        }

        public Criteria andWithdrawWayEqualTo(Byte value) {
            addCriterion("withdraw_way =", value, "withdrawWay");
            return (Criteria) this;
        }

        public Criteria andWithdrawWayNotEqualTo(Byte value) {
            addCriterion("withdraw_way <>", value, "withdrawWay");
            return (Criteria) this;
        }

        public Criteria andWithdrawWayGreaterThan(Byte value) {
            addCriterion("withdraw_way >", value, "withdrawWay");
            return (Criteria) this;
        }

        public Criteria andWithdrawWayGreaterThanOrEqualTo(Byte value) {
            addCriterion("withdraw_way >=", value, "withdrawWay");
            return (Criteria) this;
        }

        public Criteria andWithdrawWayLessThan(Byte value) {
            addCriterion("withdraw_way <", value, "withdrawWay");
            return (Criteria) this;
        }

        public Criteria andWithdrawWayLessThanOrEqualTo(Byte value) {
            addCriterion("withdraw_way <=", value, "withdrawWay");
            return (Criteria) this;
        }

        public Criteria andWithdrawWayIn(List<Byte> values) {
            addCriterion("withdraw_way in", values, "withdrawWay");
            return (Criteria) this;
        }

        public Criteria andWithdrawWayNotIn(List<Byte> values) {
            addCriterion("withdraw_way not in", values, "withdrawWay");
            return (Criteria) this;
        }

        public Criteria andWithdrawWayBetween(Byte value1, Byte value2) {
            addCriterion("withdraw_way between", value1, value2, "withdrawWay");
            return (Criteria) this;
        }

        public Criteria andWithdrawWayNotBetween(Byte value1, Byte value2) {
            addCriterion("withdraw_way not between", value1, value2, "withdrawWay");
            return (Criteria) this;
        }

        public Criteria andMobileIdcardMd5IsNull() {
            addCriterion("mobile_idcard_md5 is null");
            return (Criteria) this;
        }

        public Criteria andMobileIdcardMd5IsNotNull() {
            addCriterion("mobile_idcard_md5 is not null");
            return (Criteria) this;
        }

        public Criteria andMobileIdcardMd5EqualTo(String value) {
            addCriterion("mobile_idcard_md5 =", value, "mobileIdcardMd5");
            return (Criteria) this;
        }

        public Criteria andMobileIdcardMd5NotEqualTo(String value) {
            addCriterion("mobile_idcard_md5 <>", value, "mobileIdcardMd5");
            return (Criteria) this;
        }

        public Criteria andMobileIdcardMd5GreaterThan(String value) {
            addCriterion("mobile_idcard_md5 >", value, "mobileIdcardMd5");
            return (Criteria) this;
        }

        public Criteria andMobileIdcardMd5GreaterThanOrEqualTo(String value) {
            addCriterion("mobile_idcard_md5 >=", value, "mobileIdcardMd5");
            return (Criteria) this;
        }

        public Criteria andMobileIdcardMd5LessThan(String value) {
            addCriterion("mobile_idcard_md5 <", value, "mobileIdcardMd5");
            return (Criteria) this;
        }

        public Criteria andMobileIdcardMd5LessThanOrEqualTo(String value) {
            addCriterion("mobile_idcard_md5 <=", value, "mobileIdcardMd5");
            return (Criteria) this;
        }

        public Criteria andMobileIdcardMd5Like(String value) {
            addCriterion("mobile_idcard_md5 like", value, "mobileIdcardMd5");
            return (Criteria) this;
        }

        public Criteria andMobileIdcardMd5NotLike(String value) {
            addCriterion("mobile_idcard_md5 not like", value, "mobileIdcardMd5");
            return (Criteria) this;
        }

        public Criteria andMobileIdcardMd5In(List<String> values) {
            addCriterion("mobile_idcard_md5 in", values, "mobileIdcardMd5");
            return (Criteria) this;
        }

        public Criteria andMobileIdcardMd5NotIn(List<String> values) {
            addCriterion("mobile_idcard_md5 not in", values, "mobileIdcardMd5");
            return (Criteria) this;
        }

        public Criteria andMobileIdcardMd5Between(String value1, String value2) {
            addCriterion("mobile_idcard_md5 between", value1, value2, "mobileIdcardMd5");
            return (Criteria) this;
        }

        public Criteria andMobileIdcardMd5NotBetween(String value1, String value2) {
            addCriterion("mobile_idcard_md5 not between", value1, value2, "mobileIdcardMd5");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andPhotoLikeInsensitive(String value) {
            addCriterion("upper(photo) like", value.toUpperCase(), "photo");
            return (Criteria) this;
        }

        public Criteria andRealityNameLikeInsensitive(String value) {
            addCriterion("upper(reality_name) like", value.toUpperCase(), "realityName");
            return (Criteria) this;
        }

        public Criteria andPasswordLikeInsensitive(String value) {
            addCriterion("upper(password) like", value.toUpperCase(), "password");
            return (Criteria) this;
        }

        public Criteria andPayPasswordLikeInsensitive(String value) {
            addCriterion("upper(pay_password) like", value.toUpperCase(), "payPassword");
            return (Criteria) this;
        }

        public Criteria andLastLoginIpLikeInsensitive(String value) {
            addCriterion("upper(last_login_ip) like", value.toUpperCase(), "lastLoginIp");
            return (Criteria) this;
        }

        public Criteria andEmailLikeInsensitive(String value) {
            addCriterion("upper(email) like", value.toUpperCase(), "email");
            return (Criteria) this;
        }

        public Criteria andMobileLikeInsensitive(String value) {
            addCriterion("upper(mobile) like", value.toUpperCase(), "mobile");
            return (Criteria) this;
        }

        public Criteria andAnswer1LikeInsensitive(String value) {
            addCriterion("upper(answer1) like", value.toUpperCase(), "answer1");
            return (Criteria) this;
        }

        public Criteria andAnswer2LikeInsensitive(String value) {
            addCriterion("upper(answer2) like", value.toUpperCase(), "answer2");
            return (Criteria) this;
        }

        public Criteria andAnswer3LikeInsensitive(String value) {
            addCriterion("upper(answer3) like", value.toUpperCase(), "answer3");
            return (Criteria) this;
        }

        public Criteria andIdNumberLikeInsensitive(String value) {
            addCriterion("upper(id_number) like", value.toUpperCase(), "idNumber");
            return (Criteria) this;
        }

        public Criteria andAddressLikeInsensitive(String value) {
            addCriterion("upper(address) like", value.toUpperCase(), "address");
            return (Criteria) this;
        }

        public Criteria andPostcodeLikeInsensitive(String value) {
            addCriterion("upper(postcode) like", value.toUpperCase(), "postcode");
            return (Criteria) this;
        }

        public Criteria andFamilyAddressLikeInsensitive(String value) {
            addCriterion("upper(family_address) like", value.toUpperCase(), "familyAddress");
            return (Criteria) this;
        }

        public Criteria andFamilyTelephoneLikeInsensitive(String value) {
            addCriterion("upper(family_telephone) like", value.toUpperCase(), "familyTelephone");
            return (Criteria) this;
        }

        public Criteria andCompanyLikeInsensitive(String value) {
            addCriterion("upper(company) like", value.toUpperCase(), "company");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLikeInsensitive(String value) {
            addCriterion("upper(company_address) like", value.toUpperCase(), "companyAddress");
            return (Criteria) this;
        }

        public Criteria andOfficeTelephoneLikeInsensitive(String value) {
            addCriterion("upper(office_telephone) like", value.toUpperCase(), "officeTelephone");
            return (Criteria) this;
        }

        public Criteria andFaxNumberLikeInsensitive(String value) {
            addCriterion("upper(fax_number) like", value.toUpperCase(), "faxNumber");
            return (Criteria) this;
        }

        public Criteria andRefusedReasonLikeInsensitive(String value) {
            addCriterion("upper(refused_reason) like", value.toUpperCase(), "refusedReason");
            return (Criteria) this;
        }

        public Criteria andJoinedReasonLikeInsensitive(String value) {
            addCriterion("upper(joined_reason) like", value.toUpperCase(), "joinedReason");
            return (Criteria) this;
        }

        public Criteria andTelephoneLikeInsensitive(String value) {
            addCriterion("upper(telephone) like", value.toUpperCase(), "telephone");
            return (Criteria) this;
        }

        public Criteria andSign1LikeInsensitive(String value) {
            addCriterion("upper(sign1) like", value.toUpperCase(), "sign1");
            return (Criteria) this;
        }

        public Criteria andSign2LikeInsensitive(String value) {
            addCriterion("upper(sign2) like", value.toUpperCase(), "sign2");
            return (Criteria) this;
        }

        public Criteria andQqKeyLikeInsensitive(String value) {
            addCriterion("upper(qq_key) like", value.toUpperCase(), "qqKey");
            return (Criteria) this;
        }

        public Criteria andWeiboKeyLikeInsensitive(String value) {
            addCriterion("upper(weibo_key) like", value.toUpperCase(), "weiboKey");
            return (Criteria) this;
        }

        public Criteria andQrCodeLikeInsensitive(String value) {
            addCriterion("upper(qr_code) like", value.toUpperCase(), "qrCode");
            return (Criteria) this;
        }

        public Criteria andIpsAcctNoLikeInsensitive(String value) {
            addCriterion("upper(ips_acct_no) like", value.toUpperCase(), "ipsAcctNo");
            return (Criteria) this;
        }

        public Criteria andIpsBidAuthNoLikeInsensitive(String value) {
            addCriterion("upper(ips_bid_auth_no) like", value.toUpperCase(), "ipsBidAuthNo");
            return (Criteria) this;
        }

        public Criteria andIpsRepayAuthNoLikeInsensitive(String value) {
            addCriterion("upper(ips_repay_auth_no) like", value.toUpperCase(), "ipsRepayAuthNo");
            return (Criteria) this;
        }

        public Criteria andDeviceUserIdLikeInsensitive(String value) {
            addCriterion("upper(device_user_id) like", value.toUpperCase(), "deviceUserId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLikeInsensitive(String value) {
            addCriterion("upper(channel_id) like", value.toUpperCase(), "channelId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLikeInsensitive(String value) {
            addCriterion("upper(open_id) like", value.toUpperCase(), "openId");
            return (Criteria) this;
        }

        public Criteria andRemarkLikeInsensitive(String value) {
            addCriterion("upper(remark) like", value.toUpperCase(), "remark");
            return (Criteria) this;
        }

        public Criteria andLabelLikeInsensitive(String value) {
            addCriterion("upper(label) like", value.toUpperCase(), "label");
            return (Criteria) this;
        }

        public Criteria andRealIdNumberLikeInsensitive(String value) {
            addCriterion("upper(real_id_number) like", value.toUpperCase(), "realIdNumber");
            return (Criteria) this;
        }

        public Criteria andMobileIdcardMd5LikeInsensitive(String value) {
            addCriterion("upper(mobile_idcard_md5) like", value.toUpperCase(), "mobileIdcardMd5");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private UserExample example;

        protected Criteria(UserExample example) {
            super();
            this.example = example;
        }

        public UserExample example() {
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