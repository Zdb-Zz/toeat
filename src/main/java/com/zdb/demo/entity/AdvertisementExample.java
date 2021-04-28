package com.zdb.demo.entity;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementExample {

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table advertisement
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table advertisement
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table advertisement
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table advertisement
     *
     * @mbggenerated
     */
    protected Integer limitStart;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table advertisement
     *
     * @mbggenerated
     */
    protected Integer limitEnd;

    /**
     *  构造查询条件,advertisement
     */
    public AdvertisementExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     *  设置排序字段,advertisement
     *
     * @param orderByClause 排序字段
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     *  获取排序字段,advertisement
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     *  设置过滤重复数据,advertisement
     *
     * @param distinct 是否过滤重复数据
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     *  是否过滤重复数据,advertisement
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     *  获取当前的查询条件实例,advertisement
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * ,advertisement
     *
     * @param criteria 过滤条件实例
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * ,advertisement
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *  创建一个查询条件,advertisement
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     *  内部构建查询条件对象,advertisement
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     *  清除查询条件,advertisement
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * ,advertisement
     *
     * @param limitStart
     */
    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    /**
     * ,advertisement
     */
    public Integer getLimitStart() {
        return limitStart;
    }

    /**
     * ,advertisement
     *
     * @param limitEnd
     */
    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd=limitEnd;
    }

    /**
     * ,advertisement
     */
    public Integer getLimitEnd() {
        return limitEnd;
    }

    /**
     * 广告中心
     * GeneratedCriteria
     * 数据库表：advertisement
     */
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

        public Criteria andAdvertisementIdIsNull() {
            addCriterion("advertisement_id is null");
            return (Criteria) this;
        }

        public Criteria andAdvertisementIdIsNotNull() {
            addCriterion("advertisement_id is not null");
            return (Criteria) this;
        }

        public Criteria andAdvertisementIdEqualTo(Integer value) {
            addCriterion("advertisement_id =", value, "advertisementId");
            return (Criteria) this;
        }

        public Criteria andAdvertisementIdNotEqualTo(Integer value) {
            addCriterion("advertisement_id <>", value, "advertisementId");
            return (Criteria) this;
        }

        public Criteria andAdvertisementIdGreaterThan(Integer value) {
            addCriterion("advertisement_id >", value, "advertisementId");
            return (Criteria) this;
        }

        public Criteria andAdvertisementIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("advertisement_id >=", value, "advertisementId");
            return (Criteria) this;
        }

        public Criteria andAdvertisementIdLessThan(Integer value) {
            addCriterion("advertisement_id <", value, "advertisementId");
            return (Criteria) this;
        }

        public Criteria andAdvertisementIdLessThanOrEqualTo(Integer value) {
            addCriterion("advertisement_id <=", value, "advertisementId");
            return (Criteria) this;
        }

        public Criteria andAdvertisementIdIn(List<Integer> values) {
            addCriterion("advertisement_id in", values, "advertisementId");
            return (Criteria) this;
        }

        public Criteria andAdvertisementIdNotIn(List<Integer> values) {
            addCriterion("advertisement_id not in", values, "advertisementId");
            return (Criteria) this;
        }

        public Criteria andAdvertisementIdBetween(Integer value1, Integer value2) {
            addCriterion("advertisement_id between", value1, value2, "advertisementId");
            return (Criteria) this;
        }

        public Criteria andAdvertisementIdNotBetween(Integer value1, Integer value2) {
            addCriterion("advertisement_id not between", value1, value2, "advertisementId");
            return (Criteria) this;
        }

        public Criteria andStoreIdIsNull() {
            addCriterion("store_id is null");
            return (Criteria) this;
        }

        public Criteria andStoreIdIsNotNull() {
            addCriterion("store_id is not null");
            return (Criteria) this;
        }

        public Criteria andStoreIdEqualTo(Integer value) {
            addCriterion("store_id =", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotEqualTo(Integer value) {
            addCriterion("store_id <>", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdGreaterThan(Integer value) {
            addCriterion("store_id >", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("store_id >=", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdLessThan(Integer value) {
            addCriterion("store_id <", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdLessThanOrEqualTo(Integer value) {
            addCriterion("store_id <=", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdIn(List<Integer> values) {
            addCriterion("store_id in", values, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotIn(List<Integer> values) {
            addCriterion("store_id not in", values, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdBetween(Integer value1, Integer value2) {
            addCriterion("store_id between", value1, value2, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotBetween(Integer value1, Integer value2) {
            addCriterion("store_id not between", value1, value2, "storeId");
            return (Criteria) this;
        }

        public Criteria andAdvertisementTypeIsNull() {
            addCriterion("advertisement_type is null");
            return (Criteria) this;
        }

        public Criteria andAdvertisementTypeIsNotNull() {
            addCriterion("advertisement_type is not null");
            return (Criteria) this;
        }

        public Criteria andAdvertisementTypeEqualTo(Integer value) {
            addCriterion("advertisement_type =", value, "advertisementType");
            return (Criteria) this;
        }

        public Criteria andAdvertisementTypeNotEqualTo(Integer value) {
            addCriterion("advertisement_type <>", value, "advertisementType");
            return (Criteria) this;
        }

        public Criteria andAdvertisementTypeGreaterThan(Integer value) {
            addCriterion("advertisement_type >", value, "advertisementType");
            return (Criteria) this;
        }

        public Criteria andAdvertisementTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("advertisement_type >=", value, "advertisementType");
            return (Criteria) this;
        }

        public Criteria andAdvertisementTypeLessThan(Integer value) {
            addCriterion("advertisement_type <", value, "advertisementType");
            return (Criteria) this;
        }

        public Criteria andAdvertisementTypeLessThanOrEqualTo(Integer value) {
            addCriterion("advertisement_type <=", value, "advertisementType");
            return (Criteria) this;
        }

        public Criteria andAdvertisementTypeIn(List<Integer> values) {
            addCriterion("advertisement_type in", values, "advertisementType");
            return (Criteria) this;
        }

        public Criteria andAdvertisementTypeNotIn(List<Integer> values) {
            addCriterion("advertisement_type not in", values, "advertisementType");
            return (Criteria) this;
        }

        public Criteria andAdvertisementTypeBetween(Integer value1, Integer value2) {
            addCriterion("advertisement_type between", value1, value2, "advertisementType");
            return (Criteria) this;
        }

        public Criteria andAdvertisementTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("advertisement_type not between", value1, value2, "advertisementType");
            return (Criteria) this;
        }

        public Criteria andAdvertisementImgIsNull() {
            addCriterion("advertisement_img is null");
            return (Criteria) this;
        }

        public Criteria andAdvertisementImgIsNotNull() {
            addCriterion("advertisement_img is not null");
            return (Criteria) this;
        }

        public Criteria andAdvertisementImgEqualTo(String value) {
            addCriterion("advertisement_img =", value, "advertisementImg");
            return (Criteria) this;
        }

        public Criteria andAdvertisementImgNotEqualTo(String value) {
            addCriterion("advertisement_img <>", value, "advertisementImg");
            return (Criteria) this;
        }

        public Criteria andAdvertisementImgGreaterThan(String value) {
            addCriterion("advertisement_img >", value, "advertisementImg");
            return (Criteria) this;
        }

        public Criteria andAdvertisementImgGreaterThanOrEqualTo(String value) {
            addCriterion("advertisement_img >=", value, "advertisementImg");
            return (Criteria) this;
        }

        public Criteria andAdvertisementImgLessThan(String value) {
            addCriterion("advertisement_img <", value, "advertisementImg");
            return (Criteria) this;
        }

        public Criteria andAdvertisementImgLessThanOrEqualTo(String value) {
            addCriterion("advertisement_img <=", value, "advertisementImg");
            return (Criteria) this;
        }

        public Criteria andAdvertisementImgLike(String value) {
            addCriterion("advertisement_img like", value, "advertisementImg");
            return (Criteria) this;
        }

        public Criteria andAdvertisementImgNotLike(String value) {
            addCriterion("advertisement_img not like", value, "advertisementImg");
            return (Criteria) this;
        }

        public Criteria andAdvertisementImgIn(List<String> values) {
            addCriterion("advertisement_img in", values, "advertisementImg");
            return (Criteria) this;
        }

        public Criteria andAdvertisementImgNotIn(List<String> values) {
            addCriterion("advertisement_img not in", values, "advertisementImg");
            return (Criteria) this;
        }

        public Criteria andAdvertisementImgBetween(String value1, String value2) {
            addCriterion("advertisement_img between", value1, value2, "advertisementImg");
            return (Criteria) this;
        }

        public Criteria andAdvertisementImgNotBetween(String value1, String value2) {
            addCriterion("advertisement_img not between", value1, value2, "advertisementImg");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：advertisement
     */
    public static class Criteria extends GeneratedCriteria {


        protected Criteria() {
            super();
        }
    }

    /**
     * 广告中心
     * Criterion
     * 数据库表：advertisement
     */
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