package com.hzed.easyget.persistence.auto.entity.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserTransactionPicExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer offset;

    protected Integer rows;

    public UserTransactionPicExample() {
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

    public UserTransactionPicExample limit(Integer rows) {
        this.rows = rows;
        return this;
    }

    public UserTransactionPicExample limit(Integer offset, Integer rows) {
        this.offset = offset;
        this.rows = rows;
        return this;
    }

    public UserTransactionPicExample page(Integer page, Integer pageSize) {
        this.offset = page * pageSize;
        this.rows = pageSize;
        return this;
    }

    public UserTransactionPicExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public UserTransactionPicExample orderBy(String ... orderByClauses) {
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

        public Criteria andEvidencePicUrlIsNull() {
            addCriterion("evidence_pic_url is null");
            return (Criteria) this;
        }

        public Criteria andEvidencePicUrlIsNotNull() {
            addCriterion("evidence_pic_url is not null");
            return (Criteria) this;
        }

        public Criteria andEvidencePicUrlEqualTo(String value) {
            addCriterion("evidence_pic_url =", value, "evidencePicUrl");
            return (Criteria) this;
        }

        public Criteria andEvidencePicUrlNotEqualTo(String value) {
            addCriterion("evidence_pic_url <>", value, "evidencePicUrl");
            return (Criteria) this;
        }

        public Criteria andEvidencePicUrlGreaterThan(String value) {
            addCriterion("evidence_pic_url >", value, "evidencePicUrl");
            return (Criteria) this;
        }

        public Criteria andEvidencePicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("evidence_pic_url >=", value, "evidencePicUrl");
            return (Criteria) this;
        }

        public Criteria andEvidencePicUrlLessThan(String value) {
            addCriterion("evidence_pic_url <", value, "evidencePicUrl");
            return (Criteria) this;
        }

        public Criteria andEvidencePicUrlLessThanOrEqualTo(String value) {
            addCriterion("evidence_pic_url <=", value, "evidencePicUrl");
            return (Criteria) this;
        }

        public Criteria andEvidencePicUrlLike(String value) {
            addCriterion("evidence_pic_url like", value, "evidencePicUrl");
            return (Criteria) this;
        }

        public Criteria andEvidencePicUrlNotLike(String value) {
            addCriterion("evidence_pic_url not like", value, "evidencePicUrl");
            return (Criteria) this;
        }

        public Criteria andEvidencePicUrlIn(List<String> values) {
            addCriterion("evidence_pic_url in", values, "evidencePicUrl");
            return (Criteria) this;
        }

        public Criteria andEvidencePicUrlNotIn(List<String> values) {
            addCriterion("evidence_pic_url not in", values, "evidencePicUrl");
            return (Criteria) this;
        }

        public Criteria andEvidencePicUrlBetween(String value1, String value2) {
            addCriterion("evidence_pic_url between", value1, value2, "evidencePicUrl");
            return (Criteria) this;
        }

        public Criteria andEvidencePicUrlNotBetween(String value1, String value2) {
            addCriterion("evidence_pic_url not between", value1, value2, "evidencePicUrl");
            return (Criteria) this;
        }

        public Criteria andModeIsNull() {
            addCriterion("mode is null");
            return (Criteria) this;
        }

        public Criteria andModeIsNotNull() {
            addCriterion("mode is not null");
            return (Criteria) this;
        }

        public Criteria andModeEqualTo(String value) {
            addCriterion("mode =", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotEqualTo(String value) {
            addCriterion("mode <>", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeGreaterThan(String value) {
            addCriterion("mode >", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeGreaterThanOrEqualTo(String value) {
            addCriterion("mode >=", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeLessThan(String value) {
            addCriterion("mode <", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeLessThanOrEqualTo(String value) {
            addCriterion("mode <=", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeLike(String value) {
            addCriterion("mode like", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotLike(String value) {
            addCriterion("mode not like", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeIn(List<String> values) {
            addCriterion("mode in", values, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotIn(List<String> values) {
            addCriterion("mode not in", values, "mode");
            return (Criteria) this;
        }

        public Criteria andModeBetween(String value1, String value2) {
            addCriterion("mode between", value1, value2, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotBetween(String value1, String value2) {
            addCriterion("mode not between", value1, value2, "mode");
            return (Criteria) this;
        }

        public Criteria andVaIsNull() {
            addCriterion("va is null");
            return (Criteria) this;
        }

        public Criteria andVaIsNotNull() {
            addCriterion("va is not null");
            return (Criteria) this;
        }

        public Criteria andVaEqualTo(String value) {
            addCriterion("va =", value, "va");
            return (Criteria) this;
        }

        public Criteria andVaNotEqualTo(String value) {
            addCriterion("va <>", value, "va");
            return (Criteria) this;
        }

        public Criteria andVaGreaterThan(String value) {
            addCriterion("va >", value, "va");
            return (Criteria) this;
        }

        public Criteria andVaGreaterThanOrEqualTo(String value) {
            addCriterion("va >=", value, "va");
            return (Criteria) this;
        }

        public Criteria andVaLessThan(String value) {
            addCriterion("va <", value, "va");
            return (Criteria) this;
        }

        public Criteria andVaLessThanOrEqualTo(String value) {
            addCriterion("va <=", value, "va");
            return (Criteria) this;
        }

        public Criteria andVaLike(String value) {
            addCriterion("va like", value, "va");
            return (Criteria) this;
        }

        public Criteria andVaNotLike(String value) {
            addCriterion("va not like", value, "va");
            return (Criteria) this;
        }

        public Criteria andVaIn(List<String> values) {
            addCriterion("va in", values, "va");
            return (Criteria) this;
        }

        public Criteria andVaNotIn(List<String> values) {
            addCriterion("va not in", values, "va");
            return (Criteria) this;
        }

        public Criteria andVaBetween(String value1, String value2) {
            addCriterion("va between", value1, value2, "va");
            return (Criteria) this;
        }

        public Criteria andVaNotBetween(String value1, String value2) {
            addCriterion("va not between", value1, value2, "va");
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

        public Criteria andEvidencePicUrlLikeInsensitive(String value) {
            addCriterion("upper(evidence_pic_url) like", value.toUpperCase(), "evidencePicUrl");
            return (Criteria) this;
        }

        public Criteria andModeLikeInsensitive(String value) {
            addCriterion("upper(mode) like", value.toUpperCase(), "mode");
            return (Criteria) this;
        }

        public Criteria andVaLikeInsensitive(String value) {
            addCriterion("upper(va) like", value.toUpperCase(), "va");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private UserTransactionPicExample example;

        protected Criteria(UserTransactionPicExample example) {
            super();
            this.example = example;
        }

        public UserTransactionPicExample example() {
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