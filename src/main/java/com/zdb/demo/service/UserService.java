package com.zdb.demo.service;

import com.zdb.demo.entity.User;

import javax.servlet.http.HttpSession;

public interface UserService {
    Boolean registerUser(User user);

    int loginUser(User user, HttpSession session);

    Boolean editUser(User user);
}
