package com.zdb.demo.controller;

import com.zdb.demo.entity.User;
import com.zdb.demo.service.UserService;
import com.zdb.demo.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
@Scope("prototype")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user) {
        Boolean isSuccess = userService.registerUser(user);
        if (isSuccess) {
            return ResultUtil.resultSuccess("注册成功", null, null);
        } else {
            return ResultUtil.resultFail("用户名已存在，请重新输入", null, null);
        }
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user, HttpSession session) {
        int type = userService.loginUser(user,session);
        if (type == 1) {
            return ResultUtil.resultSuccess("登录成功", null, type);
        } else if (type == 2) {
            return ResultUtil.resultFail("密码错误", null, type);
        } else if (type == 3) {
            return ResultUtil.resultFail("用户名不存在,请先注册", null, type);
        } else return ResultUtil.resultFail("错误！！！", null, type);
    }

    /**
     * 编辑用户
     *
     * @param
     * @return
     */
    @PostMapping("/editUser")
    public Map<String, Object> editUser(@RequestBody User user,HttpSession session) {
        User userSession = (User) session.getAttribute("user");
        user.setUserId(userSession.getUserId());
        Boolean isSuccess = userService.editUser(user);
        if (isSuccess) {
            return ResultUtil.resultSuccess("用户编辑成功", null, isSuccess);
        } else {
            return ResultUtil.resultFail("用户编辑失败", null, isSuccess);
        }
    }
}
