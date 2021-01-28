package com.zdb.demo.controller;

import com.zdb.demo.entity.Store;
import com.zdb.demo.entity.User;
import com.zdb.demo.service.UserService;
import com.zdb.demo.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;

/**
 * 用户管理
 */
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
            return ResultUtil.resultSuccess("注册成功", null, 1);
        } else {
            return ResultUtil.resultFail("用户名已存在，请重新输入", null, 3);
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
        Map<String,Object> map = userService.loginUser(user, session);
        int type = (int) map.get("type");
        User newUser = new User();
        newUser = (User) map.get("user");
        if (type == 1) {
            return ResultUtil.resultSuccess("登录成功", null, newUser);
        } else if (type == 2) {
            return ResultUtil.resultFail("密码错误", null, null);
        } else if (type == 3) {
            return ResultUtil.resultFail("用户名不存在,请先注册", null, null);
        } else return ResultUtil.resultFail("错误！！！", null, null);
    }

    /**
     * 编辑用户
     *
     * @param
     * @return
     */
    @PostMapping("/editUser")
    public Map<String, Object> editUser(@RequestBody User user, HttpSession session) {
        User userSession = (User) session.getAttribute("user");
        user.setUserId(userSession.getUserId());
        Boolean isSuccess = userService.editUser(user);
        if (isSuccess) {
            return ResultUtil.resultSuccess("用户编辑成功", null, isSuccess);
        } else {
            return ResultUtil.resultFail("用户编辑失败", null, isSuccess);
        }
    }

    /**
     * 根据id获取用户信息
     *
     * @param
     * @return
     */
    @GetMapping("/findUser")
    public Map<String, Object> findUser(HttpSession session) {
        User userSession = (User) session.getAttribute("user");
        User user = userService.findUser(userSession.getUserId());
        if (Objects.nonNull(user)) {
            return ResultUtil.resultSuccess("用户信息获取成功", null, user);
        } else {
            return ResultUtil.resultFail("用户信息获取失败", null, null);
        }
    }
    /**
     * 根据用户获取商家
     * @param session
     * @return
     */
    @GetMapping("/getStoreByUser")
    public Map<String, Object> getStoreByUser(HttpSession session) {
        User userSession = (User) session.getAttribute("user");
        Store store = userService.getStoreByUser(userSession.getUserId(),session);
        if (Objects.nonNull(store)) {
            return ResultUtil.resultSuccess("用户商家获取成功", null, store);
        } else {
            return ResultUtil.resultFail("用户商家获取失败", null, null);
        }
    }
}
