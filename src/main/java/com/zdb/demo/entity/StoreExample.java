package com.zdb.demo.entity;

import java.util.ArrayList;
import java.util.List;

public class StoreExample {

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table store
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table store
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table store
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table store
     *
     * @mbggenerated
     */
    protected Integer limitStart;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table store
     *
     * @mbggenerated
     */
    protected Integer limitEnd;

    /**
     *  构造查询条件,store
     */
    public StoreExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     *  设置排序字段,store
     *
     * @param orderByClause 排序字段
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     *  获取排序字段,store
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     *  设置过滤重复数据,store
     *
     * @param distinct 是否过滤重复数据
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     *  是否过滤重复数据,store
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     *  获取当前的查询条件实例,store
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * ,store
     *
     * @param criteria 过滤条件实例
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * ,store
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *  创建一个查询条件,store
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     *  内部构建查询条件对象,store
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     *  清除查询条件,store
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * ,store
     *
     * @param limitStart
     */
    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    /**
     * ,store
     */
    public Integer getLimitStart() {
        return limitStart;
    }

    /**
     * ,store
     *
     * @param limitEnd
     */
    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd=limitEnd;
    }

    /**
     * ,store
     */
    public Integer getLimitEnd() {
        return limitEnd;
    }

    /**
     * 商家信息
     * GeneratedCriteria
     * 数据库表：store
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

        public Criteria andStoreNameIsNull() {
            addCriterion("store_name is null");
            return (Criteria) this;
        }

        public Criteria andStoreNameIsNotNull() {
            addCriterion("store_name is not null");
            return (Criteria) this;
        }

        public Criteria andStoreNameEqualTo(String value) {
            addCriterion("store_name =", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotEqualTo(String value) {
            addCriterion("store_name <>", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameGreaterThan(String value) {
            addCriterion("store_name >", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameGreaterThanOrEqualTo(String value) {
            addCriterion("store_name >=", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLessThan(String value) {
            addCriterion("store_name <", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLessThanOrEqualTo(String value) {
            addCriterion("store_name <=", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLike(String value) {
            addCriterion("store_name like", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotLike(String value) {
            addCriterion("store_name not like", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameIn(List<String> values) {
            addCriterion("store_name in", values, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotIn(List<String> values) {
            addCriterion("store_name not in", values, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameBetween(String value1, String value2) {
            addCriterion("store_name between", value1, value2, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotBetween(String value1, String value2) {
            addCriterion("store_name not between", value1, value2, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreCollectIsNull() {
            addCriterion("store_collect is null");
            return (Criteria) this;
        }

        public Criteria andStoreCollectIsNotNull() {
            addCriterion("store_collect is not null");
            return (Criteria) this;
        }

        public Criteria andStoreCollectEqualTo(String value) {
            addCriterion("store_collect =", value, "storeCollect");
            return (Criteria) this;
        }

        public Criteria andStoreCollectNotEqualTo(String value) {
            addCriterion("store_collect <>", value, "storeCollect");
            return (Criteria) this;
        }

        public Criteria andStoreCollectGreaterThan(String value) {
            addCriterion("store_collect >", value, "storeCollect");
            return (Criteria) this;
        }

        public Criteria andStoreCollectGreaterThanOrEqualTo(String value) {
            addCriterion("store_collect >=", value, "storeCollect");
            return (Criteria) this;
        }

        public Criteria andStoreCollectLessThan(String value) {
            addCriterion("store_collect <", value, "storeCollect");
            return (Criteria) this;
        }

        public Criteria andStoreCollectLessThanOrEqualTo(String value) {
            addCriterion("store_collect <=", value, "storeCollect");
            return (Criteria) this;
        }

        public Criteria andStoreCollectLike(String value) {
            addCriterion("store_collect like", value, "storeCollect");
            return (Criteria) this;
        }

        public Criteria andStoreCollectNotLike(String value) {
            addCriterion("store_collect not like", value, "storeCollect");
            return (Criteria) this;
        }

        public Criteria andStoreCollectIn(List<String> values) {
            addCriterion("store_collect in", values, "storeCollect");
            return (Criteria) this;
        }

        public Criteria andStoreCollectNotIn(List<String> values) {
            addCriterion("store_collect not in", values, "storeCollect");
            return (Criteria) this;
        }

        public Criteria andStoreCollectBetween(String value1, String value2) {
            addCriterion("store_collect between", value1, value2, "storeCollect");
            return (Criteria) this;
        }

        public Criteria andStoreCollectNotBetween(String value1, String value2) {
            addCriterion("store_collect not between", value1, value2, "storeCollect");
            return (Criteria) this;
        }

        public Criteria andStoreStarIsNull() {
            addCriterion("store_star is null");
            return (Criteria) this;
        }

        public Criteria andStoreStarIsNotNull() {
            addCriterion("store_star is not null");
            return (Criteria) this;
        }

        public Criteria andStoreStarEqualTo(String value) {
            addCriterion("store_star =", value, "storeStar");
            return (Criteria) this;
        }

        public Criteria andStoreStarNotEqualTo(String value) {
            addCriterion("store_star <>", value, "storeStar");
            return (Criteria) this;
        }

        public Criteria andStoreStarGreaterThan(String value) {
            addCriterion("store_star >", value, "storeStar");
            return (Criteria) this;
        }

        public Criteria andStoreStarGreaterThanOrEqualTo(String value) {
            addCriterion("store_star >=", value, "storeStar");
            return (Criteria) this;
        }

        public Criteria andStoreStarLessThan(String value) {
            addCriterion("store_star <", value, "storeStar");
            return (Criteria) this;
        }

        public Criteria andStoreStarLessThanOrEqualTo(String value) {
            addCriterion("store_star <=", value, "storeStar");
            return (Criteria) this;
        }

        public Criteria andStoreStarLike(String value) {
            addCriterion("store_star like", value, "storeStar");
            return (Criteria) this;
        }

        public Criteria andStoreStarNotLike(String value) {
            addCriterion("store_star not like", value, "storeStar");
            return (Criteria) this;
        }

        public Criteria andStoreStarIn(List<String> values) {
            addCriterion("store_star in", values, "storeStar");
            return (Criteria) this;
        }

        public Criteria andStoreStarNotIn(List<String> values) {
            addCriterion("store_star not in", values, "storeStar");
            return (Criteria) this;
        }

        public Criteria andStoreStarBetween(String value1, String value2) {
            addCriterion("store_star between", value1, value2, "storeStar");
            return (Criteria) this;
        }

        public Criteria andStoreStarNotBetween(String value1, String value2) {
            addCriterion("store_star not between", value1, value2, "storeStar");
            return (Criteria) this;
        }

        public Criteria andStoreRemarkIsNull() {
            addCriterion("store_remark is null");
            return (Criteria) this;
        }

        public Criteria andStoreRemarkIsNotNull() {
            addCriterion("store_remark is not null");
            return (Criteria) this;
        }

        public Criteria andStoreRemarkEqualTo(String value) {
            addCriterion("store_remark =", value, "storeRemark");
            return (Criteria) this;
        }

        public Criteria andStoreRemarkNotEqualTo(String value) {
            addCriterion("store_remark <>", value, "storeRemark");
            return (Criteria) this;
        }

        public Criteria andStoreRemarkGreaterThan(String value) {
            addCriterion("store_remark >", value, "storeRemark");
            return (Criteria) this;
        }

        public Criteria andStoreRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("store_remark >=", value, "storeRemark");
            return (Criteria) this;
        }

        public Criteria andStoreRemarkLessThan(String value) {
            addCriterion("store_remark <", value, "storeRemark");
            return (Criteria) this;
        }

        public Criteria andStoreRemarkLessThanOrEqualTo(String value) {
            addCriterion("store_remark <=", value, "storeRemark");
            return (Criteria) this;
        }

        public Criteria andStoreRemarkLike(String value) {
            addCriterion("store_remark like", value, "storeRemark");
            return (Criteria) this;
        }

        public Criteria andStoreRemarkNotLike(String value) {
            addCriterion("store_remark not like", value, "storeRemark");
            return (Criteria) this;
        }

        public Criteria andStoreRemarkIn(List<String> values) {
            addCriterion("store_remark in", values, "storeRemark");
            return (Criteria) this;
        }

        public Criteria andStoreRemarkNotIn(List<String> values) {
            addCriterion("store_remark not in", values, "storeRemark");
            return (Criteria) this;
        }

        public Criteria andStoreRemarkBetween(String value1, String value2) {
            addCriterion("store_remark between", value1, value2, "storeRemark");
            return (Criteria) this;
        }

        public Criteria andStoreRemarkNotBetween(String value1, String value2) {
            addCriterion("store_remark not between", value1, value2, "storeRemark");
            return (Criteria) this;
        }

        public Criteria andStoreSalesIsNull() {
            addCriterion("store_sales is null");
            return (Criteria) this;
        }

        public Criteria andStoreSalesIsNotNull() {
            addCriterion("store_sales is not null");
            return (Criteria) this;
        }

        public Criteria andStoreSalesEqualTo(Long value) {
            addCriterion("store_sales =", value, "storeSales");
            return (Criteria) this;
        }

        public Criteria andStoreSalesNotEqualTo(Long value) {
            addCriterion("store_sales <>", value, "storeSales");
            return (Criteria) this;
        }

        public Criteria andStoreSalesGreaterThan(Long value) {
            addCriterion("store_sales >", value, "storeSales");
            return (Criteria) this;
        }

        public Criteria andStoreSalesGreaterThanOrEqualTo(Long value) {
            addCriterion("store_sales >=", value, "storeSales");
            return (Criteria) this;
        }

        public Criteria andStoreSalesLessThan(Long value) {
            addCriterion("store_sales <", value, "storeSales");
            return (Criteria) this;
        }

        public Criteria andStoreSalesLessThanOrEqualTo(Long value) {
            addCriterion("store_sales <=", value, "storeSales");
            return (Criteria) this;
        }

        public Criteria andStoreSalesIn(List<Long> values) {
            addCriterion("store_sales in", values, "storeSales");
            return (Criteria) this;
        }

        public Criteria andStoreSalesNotIn(List<Long> values) {
            addCriterion("store_sales not in", values, "storeSales");
            return (Criteria) this;
        }

        public Criteria andStoreSalesBetween(Long value1, Long value2) {
            addCriterion("store_sales between", value1, value2, "storeSales");
            return (Criteria) this;
        }

        public Criteria andStoreSalesNotBetween(Long value1, Long value2) {
            addCriterion("store_sales not between", value1, value2, "storeSales");
            return (Criteria) this;
        }

        public Criteria andStoreStateIsNull() {
            addCriterion("store_state is null");
            return (Criteria) this;
        }

        public Criteria andStoreStateIsNotNull() {
            addCriterion("store_state is not null");
            return (Criteria) this;
        }

        public Criteria andStoreStateEqualTo(Integer value) {
            addCriterion("store_state =", value, "storeState");
            return (Criteria) this;
        }

        public Criteria andStoreStateNotEqualTo(Integer value) {
            addCriterion("store_state <>", value, "storeState");
            return (Criteria) this;
        }

        public Criteria andStoreStateGreaterThan(Integer value) {
            addCriterion("store_state >", value, "storeState");
            return (Criteria) this;
        }

        public Criteria andStoreStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("store_state >=", value, "storeState");
            return (Criteria) this;
        }

        public Criteria andStoreStateLessThan(Integer value) {
            addCriterion("store_state <", value, "storeState");
            return (Criteria) this;
        }

        public Criteria andStoreStateLessThanOrEqualTo(Integer value) {
            addCriterion("store_state <=", value, "storeState");
            return (Criteria) this;
        }

        public Criteria andStoreStateIn(List<Integer> values) {
            addCriterion("store_state in", values, "storeState");
            return (Criteria) this;
        }

        public Criteria andStoreStateNotIn(List<Integer> values) {
            addCriterion("store_state not in", values, "storeState");
            return (Criteria) this;
        }

        public Criteria andStoreStateBetween(Integer value1, Integer value2) {
            addCriterion("store_state between", value1, value2, "storeState");
            return (Criteria) this;
        }

        public Criteria andStoreStateNotBetween(Integer value1, Integer value2) {
            addCriterion("store_state not between", value1, value2, "storeState");
            return (Criteria) this;
        }

        public Criteria andStoreDelIsNull() {
            addCriterion("store_del is null");
            return (Criteria) this;
        }

        public Criteria andStoreDelIsNotNull() {
            addCriterion("store_del is not null");
            return (Criteria) this;
        }

        public Criteria andStoreDelEqualTo(Integer value) {
            addCriterion("store_del =", value, "storeDel");
            return (Criteria) this;
        }

        public Criteria andStoreDelNotEqualTo(Integer value) {
            addCriterion("store_del <>", value, "storeDel");
            return (Criteria) this;
        }

        public Criteria andStoreDelGreaterThan(Integer value) {
            addCriterion("store_del >", value, "storeDel");
            return (Criteria) this;
        }

        public Criteria andStoreDelGreaterThanOrEqualTo(Integer value) {
            addCriterion("store_del >=", value, "storeDel");
            return (Criteria) this;
        }

        public Criteria andStoreDelLessThan(Integer value) {
            addCriterion("store_del <", value, "storeDel");
            return (Criteria) this;
        }

        public Criteria andStoreDelLessThanOrEqualTo(Integer value) {
            addCriterion("store_del <=", value, "storeDel");
            return (Criteria) this;
        }

        public Criteria andStoreDelIn(List<Integer> values) {
            addCriterion("store_del in", values, "storeDel");
            return (Criteria) this;
        }

        public Criteria andStoreDelNotIn(List<Integer> values) {
            addCriterion("store_del not in", values, "storeDel");
            return (Criteria) this;
        }

        public Criteria andStoreDelBetween(Integer value1, Integer value2) {
            addCriterion("store_del between", value1, value2, "storeDel");
            return (Criteria) this;
        }

        public Criteria andStoreDelNotBetween(Integer value1, Integer value2) {
            addCriterion("store_del not between", value1, value2, "storeDel");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：store
     */
    public static class Criteria extends GeneratedCriteria {


        protected Criteria() {
            super();
        }
    }

    /**
     * 商家信息
     * Criterion
     * 数据库表：store
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