package com.zdb.demo.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商家信息
 * Store
 * 数据库表：store
 */
public class Store implements Serializable {

    /**
     * 商家id
     * 表字段 : store.store_id
     */
    private Integer storeId;

    /**
     * 商家名称
     * 表字段 : store.store_name
     */
    private String storeName;

    /**
     * 商家被收藏数
     * 表字段 : store.store_collect
     */
    private String storeCollect;

    /**
     * 商家星级
     * 表字段 : store.store_star
     */
    private Integer storeStar;

    /**
     * 商家备注信息
     * 表字段 : store.store_remark
     */
    private String storeRemark;

    /**
     * 商家销量
     * 表字段 : store.store_sales
     */
    private Long storeSales;

    /**
     * 商家状态 0未营业/1正在营业
     * 表字段 : store.store_state
     */
    private Integer storeState;

    /**
     * 删除 0未删除/1已删除
     * 表字段 : store.store_del
     */
    private Integer storeDel;

    /**
     * 商家图片
     * 表字段 : store.store_img
     */
    private String storeImg;
    /**
     * 通知
     * 表字段 : store.store_notify
     */
    private Integer storeNotify;
    /**
     * 商家地址
     * 表字段 : store.store_address
     */
    private String storeAddress;

    /**
     * 商家广告
     * 表字段 : store.store_advertisement
     */
    private String storeAdvertisement;

    /**
     * 经度
     * 表字段 : store.store_lng
     */
    private BigDecimal storeLng;

    /**
     * 纬度
     * 表字段 : store.store_lat
     */
    private BigDecimal storeLat;

    /**
     * 定位地址
     * 表字段 : store.store_position
     */
    private String storePosition;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table store
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * 获取 商家id 字段:store.store_id
     *
     * @return store.store_id, 商家id
     */
    public Integer getStoreId() {
        return storeId;
    }

    /**
     * 设置 商家id 字段:store.store_id
     *
     * @param storeId the value for store.store_id, 商家id
     */
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    /**
     * 获取 商家名称 字段:store.store_name
     *
     * @return store.store_name, 商家名称
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * 设置 商家名称 字段:store.store_name
     *
     * @param storeName the value for store.store_name, 商家名称
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    /**
     * 获取 商家被收藏数 字段:store.store_collect
     *
     * @return store.store_collect, 商家被收藏数
     */
    public String getStoreCollect() {
        return storeCollect;
    }

    /**
     * 设置 商家被收藏数 字段:store.store_collect
     *
     * @param storeCollect the value for store.store_collect, 商家被收藏数
     */
    public void setStoreCollect(String storeCollect) {
        this.storeCollect = storeCollect == null ? null : storeCollect.trim();
    }

    public Integer getStoreStar() {
        return storeStar;
    }

    public void setStoreStar(Integer storeStar) {
        this.storeStar = storeStar;
    }

    /**
     * 获取 商家备注信息 字段:store.store_remark
     *
     * @return store.store_remark, 商家备注信息
     */
    public String getStoreRemark() {
        return storeRemark;
    }

    /**
     * 设置 商家备注信息 字段:store.store_remark
     *
     * @param storeRemark the value for store.store_remark, 商家备注信息
     */
    public void setStoreRemark(String storeRemark) {
        this.storeRemark = storeRemark == null ? null : storeRemark.trim();
    }

    /**
     * 获取 商家销量 字段:store.store_sales
     *
     * @return store.store_sales, 商家销量
     */
    public Long getStoreSales() {
        return storeSales;
    }

    /**
     * 设置 商家销量 字段:store.store_sales
     *
     * @param storeSales the value for store.store_sales, 商家销量
     */
    public void setStoreSales(Long storeSales) {
        this.storeSales = storeSales;
    }

    /**
     * 获取 商家状态 0未营业/1正在营业 字段:store.store_state
     *
     * @return store.store_state, 商家状态 0未营业/1正在营业
     */
    public Integer getStoreState() {
        return storeState;
    }

    /**
     * 设置 商家状态 0未营业/1正在营业 字段:store.store_state
     *
     * @param storeState the value for store.store_state, 商家状态 0未营业/1正在营业
     */
    public void setStoreState(Integer storeState) {
        this.storeState = storeState;
    }

    /**
     * 获取 删除 0未删除/1已删除 字段:store.store_del
     *
     * @return store.store_del, 删除 0未删除/1已删除
     */
    public Integer getStoreDel() {
        return storeDel;
    }

    /**
     * 设置 删除 0未删除/1已删除 字段:store.store_del
     *
     * @param storeDel the value for store.store_del, 删除 0未删除/1已删除
     */
    public void setStoreDel(Integer storeDel) {
        this.storeDel = storeDel;
    }

    public String getStoreImg() {
        return storeImg;
    }

    public void setStoreImg(String storeImg) {
        this.storeImg = storeImg;
    }

    /**
     * 获取 商家地址 字段:store.store_address
     *
     * @return store.store_address, 商家地址
     */
    public String getStoreAddress() {
        return storeAddress;
    }

    /**
     * 设置 商家地址 字段:store.store_address
     *
     * @param storeAddress the value for store.store_address, 商家地址
     */
    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress == null ? null : storeAddress.trim();
    }

    public Integer getStoreNotify() {
        return storeNotify;
    }

    public void setStoreNotify(Integer storeNotify) {
        this.storeNotify = storeNotify;
    }

    public String getStoreAdvertisement() {
        return storeAdvertisement;
    }

    public void setStoreAdvertisement(String storeAdvertisement) {
        this.storeAdvertisement = storeAdvertisement;
    }

    public BigDecimal getStoreLng() {
        return storeLng;
    }

    public void setStoreLng(BigDecimal storeLng) {
        this.storeLng = storeLng;
    }

    public BigDecimal getStoreLat() {
        return storeLat;
    }

    public void setStoreLat(BigDecimal storeLat) {
        this.storeLat = storeLat;
    }

    public String getStorePosition() {
        return storePosition;
    }

    public void setStorePosition(String storePosition) {
        this.storePosition = storePosition;
    }
}