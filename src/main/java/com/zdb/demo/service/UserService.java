package com.zdb.demo.service;

import com.zdb.demo.entity.Store;
import com.zdb.demo.entity.User;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface UserService {
    Boolean registerUser(User user);

    Map<String,Object> loginUser(User user, HttpSession session);

    Boolean editUser(User user);

    Store getStoreByUser(Integer userId,HttpSession session);
}
