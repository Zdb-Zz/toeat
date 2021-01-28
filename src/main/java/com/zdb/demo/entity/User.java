package com.zdb.demo.entity;

import java.io.Serializable;

/**
 * 用户表
 * User
 * 数据库表：user
 */
public class User implements Serializable {

    /**
     * 用户id
     * 表字段 : user.user_id
     */
    private Integer userId;

    /**
     * 用户名
     * 表字段 : user.user_name
     */
    private String userName;

    /**
     * 用户密码
     * 表字段 : user.user_pass_word
     */
    private String userPassWord;

    /**
     * 用户昵称
     * 表字段 : user.user_nick
     */
    private String userNick;

    /**
     * 用户性别
     * 表字段 : user.user_sex
     */
    private Integer userSex;

    /**
     * 电话号码
     * 表字段 : user.user_phone
     */
    private String userPhone;

    /**
     * 邮箱
     * 表字段 : user.user_mail
     */
    private String userMail;

    /**
     * 0管理员/1顾客/2商家
     * 表字段 : user.user_type
     */
    private Integer userType;

    /**
     * 用户拥有的商家
     * 表字段 : user.user_store
     */
    private Integer userStore;

    /**
     * 拥有的商家名
     */
    private String userStoreName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table user
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * 获取 用户id 字段:user.user_id
     *
     * @return user.user_id, 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置 用户id 字段:user.user_id
     *
     * @param userId the value for user.user_id, 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取 用户名 字段:user.user_name
     *
     * @return user.user_name, 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置 用户名 字段:user.user_name
     *
     * @param userName the value for user.user_name, 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取 用户密码 字段:user.user_pass_word
     *
     * @return user.user_pass_word, 用户密码
     */
    public String getUserPassWord() {
        return userPassWord;
    }

    /**
     * 设置 用户密码 字段:user.user_pass_word
     *
     * @param userPassWord the value for user.user_pass_word, 用户密码
     */
    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord == null ? null : userPassWord.trim();
    }

    /**
     * 获取 用户昵称 字段:user.user_nick
     *
     * @return user.user_nick, 用户昵称
     */
    public String getUserNick() {
        return userNick;
    }

    /**
     * 设置 用户昵称 字段:user.user_nick
     *
     * @param userNick the value for user.user_nick, 用户昵称
     */
    public void setUserNick(String userNick) {
        this.userNick = userNick == null ? null : userNick.trim();
    }

    /**
     * 获取 用户性别 字段:user.user_sex
     *
     * @return user.user_sex, 用户性别
     */
    public Integer getUserSex() {
        return userSex;
    }

    /**
     * 设置 用户性别 字段:user.user_sex
     *
     * @param userSex the value for user.user_sex, 用户性别
     */
    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    /**
     * 获取 电话号码 字段:user.user_phone
     *
     * @return user.user_phone, 电话号码
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * 设置 电话号码 字段:user.user_phone
     *
     * @param userPhone the value for user.user_phone, 电话号码
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    /**
     * 获取 邮箱 字段:user.user_mail
     *
     * @return user.user_mail, 邮箱
     */
    public String getUserMail() {
        return userMail;
    }

    /**
     * 设置 邮箱 字段:user.user_mail
     *
     * @param userMail the value for user.user_mail, 邮箱
     */
    public void setUserMail(String userMail) {
        this.userMail = userMail == null ? null : userMail.trim();
    }

    /**
     * 获取 0管理员/1顾客/2商家 字段:user.user_type
     *
     * @return user.user_type, 0管理员/1顾客/2商家
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * 设置 0管理员/1顾客/2商家 字段:user.user_type
     *
     * @param userType the value for user.user_type, 0管理员/1顾客/2商家
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * 获取 用户拥有的商家 字段:user.user_store
     *
     * @return user.user_store, 用户拥有的商家
     */
    public Integer getUserStore() {
        return userStore;
    }

    /**
     * 设置 用户拥有的商家 字段:user.user_store
     *
     * @param userStore the value for user.user_store, 用户拥有的商家
     */
    public void setUserStore(Integer userStore) {
        this.userStore = userStore;
    }

    public String getUserStoreName() {
        return userStoreName;
    }

    public void setUserStoreName(String userStoreName) {
        this.userStoreName = userStoreName;
    }
}