package com.zdb.demo.service;

import com.zdb.demo.entity.Store;
import com.zdb.demo.entity.User;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface UserService {
    /**
     * 注册
     * @param user
     * @return
     */
    Boolean registerUser(User user);

    /**
     * 登录
     * @param user
     * @param session
     * @return
     */
    Map<String,Object> loginUser(User user, HttpSession session);

    /**
     * 编辑用户
     * @param user
     * @return
     */
    Boolean editUser(User user);

    /**
     * 根据用户id获取商家信息
     * @param userId
     * @param session
     * @return
     */
    Store getStoreByUser(Integer userId,HttpSession session);

    /**
     * 根据主键获取用户信息
     * @param userId
     * @return
     */
    User findUser(Integer userId);
}
