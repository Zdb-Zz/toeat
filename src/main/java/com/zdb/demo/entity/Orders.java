package com.zdb.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单
 * Orders
 * 数据库表：orders
 */
public class Orders implements Serializable {

    /**
     * 订单id
     * 表字段 : orders.order_id
     */
    private Integer orderId;

    /**
     * 用户id
     * 表字段 : orders.order_user_id
     */
    private Integer orderUserId;

    /**
     * 订单创建时间
     * 表字段 : orders.order_create_time
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date orderCreateTime;

    /**
     * 0未支付/1已支付
     * 表字段 : orders.order_state
     */
    private Integer orderState;

    /**
     * 商家id
     * 表字段 : orders.order_store_id
     */
    private Integer orderStoreId;

    /**
     * 总价格
     * 表字段 : orders.order_sum_price
     */
    private BigDecimal orderSumPrice;

    /**
     * 备注
     * 表字段 : orders.order_remark
     */
    private String orderRemark;

    /**
     * 评价
     * 表字段 : orders.order_evaluate
     */
    private String orderEvaluate;

    /**
     * 删除 0未删除/1已删除
     * 表字段 : orders.order_del
     */
    private Integer orderDel;

    /**
     * 菜品列表
     */
    private List<OrderMenu> orderMenus;

    /**
     * 0未提交 1已提交
     * 表字段 : orders.order_submit
     */
    private Integer orderSubmit;

    /**
     * 商家名称
     */
    private String storeName;
    /**
     * 评分
     * 表字段 : orders.order_rate
     */
    private Integer orderRate;

    //用户姓名
    private String userName;
    //用户手机号码
    private String userPhone;

    private Integer isComplete;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table orders
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * 获取 订单id 字段:orders.order_id
     *
     * @return orders.order_id, 订单id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置 订单id 字段:orders.order_id
     *
     * @param orderId the value for orders.order_id, 订单id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取 用户id 字段:orders.order_user_id
     *
     * @return orders.order_user_id, 用户id
     */
    public Integer getOrderUserId() {
        return orderUserId;
    }

    /**
     * 设置 用户id 字段:orders.order_user_id
     *
     * @param orderUserId the value for orders.order_user_id, 用户id
     */
    public void setOrderUserId(Integer orderUserId) {
        this.orderUserId = orderUserId;
    }

    /**
     * 获取 订单创建时间 字段:orders.order_create_time
     *
     * @return orders.order_create_time, 订单创建时间
     */
    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    /**
     * 设置 订单创建时间 字段:orders.order_create_time
     *
     * @param orderCreateTime the value for orders.order_create_time, 订单创建时间
     */
    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    /**
     * 获取 0未支付/1已支付 字段:orders.order_state
     *
     * @return orders.order_state, 0未支付/1已支付
     */
    public Integer getOrderState() {
        return orderState;
    }

    /**
     * 设置 0未支付/1已支付 字段:orders.order_state
     *
     * @param orderState the value for orders.order_state, 0未支付/1已支付
     */
    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    /**
     * 获取 商家id 字段:orders.order_store_id
     *
     * @return orders.order_store_id, 商家id
     */
    public Integer getOrderStoreId() {
        return orderStoreId;
    }

    /**
     * 设置 商家id 字段:orders.order_store_id
     *
     * @param orderStoreId the value for orders.order_store_id, 商家id
     */
    public void setOrderStoreId(Integer orderStoreId) {
        this.orderStoreId = orderStoreId;
    }

    /**
     * 获取 总价格 字段:orders.order_sum_price
     *
     * @return orders.order_sum_price, 总价格
     */
    public BigDecimal getOrderSumPrice() {
        return orderSumPrice;
    }

    /**
     * 设置 总价格 字段:orders.order_sum_price
     *
     * @param orderSumPrice the value for orders.order_sum_price, 总价格
     */
    public void setOrderSumPrice(BigDecimal orderSumPrice) {
        this.orderSumPrice = orderSumPrice;
    }

    /**
     * 获取 备注 字段:orders.order_remark
     *
     * @return orders.order_remark, 备注
     */
    public String getOrderRemark() {
        return orderRemark;
    }

    /**
     * 设置 备注 字段:orders.order_remark
     *
     * @param orderRemark the value for orders.order_remark, 备注
     */
    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark == null ? null : orderRemark.trim();
    }

    /**
     * 获取 评价 字段:orders.order_evaluate
     *
     * @return orders.order_evaluate, 评价
     */
    public String getOrderEvaluate() {
        return orderEvaluate;
    }

    /**
     * 设置 评价 字段:orders.order_evaluate
     *
     * @param orderEvaluate the value for orders.order_evaluate, 评价
     */
    public void setOrderEvaluate(String orderEvaluate) {
        this.orderEvaluate = orderEvaluate == null ? null : orderEvaluate.trim();
    }

    /**
     * 获取 删除 0未删除/1已删除 字段:orders.order_del
     *
     * @return orders.order_del, 删除 0未删除/1已删除
     */
    public Integer getOrderDel() {
        return orderDel;
    }

    /**
     * 设置 删除 0未删除/1已删除 字段:orders.order_del
     *
     * @param orderDel the value for orders.order_del, 删除 0未删除/1已删除
     */
    public void setOrderDel(Integer orderDel) {
        this.orderDel = orderDel;
    }

    public List<OrderMenu> getOrderMenus() {
        return orderMenus;
    }

    public void setOrderMenus(List<OrderMenu> orderMenus) {
        this.orderMenus = orderMenus;
    }


    public Integer getOrderSubmit() {
        return orderSubmit;
    }

    public void setOrderSubmit(Integer orderSubmit) {
        this.orderSubmit = orderSubmit;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getOrderRate() {
        return orderRate;
    }

    public void setOrderRate(Integer orderRate) {
        this.orderRate = orderRate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Integer isComplete) {
        this.isComplete = isComplete;
    }
}