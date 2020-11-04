package com.zdb.demo.service.impl;

import com.zdb.demo.entity.User;
import com.zdb.demo.entity.UserExample;
import com.zdb.demo.mapper.UserMapper;
import com.zdb.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public Boolean registerUser(User user) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(user.getUserName());
        List<User> userList = userMapper.selectByExample(example);
        if (!userList.isEmpty() && userList.size() > 0) {
            return false;
        } else return 1 == userMapper.insertSelective(user);
    }

    @Override
    public int loginUser(User user, HttpSession session) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(user.getUserName());
        List<User> userList = userMapper.selectByExample(example);
        if (!userList.isEmpty()) {
            if (user.getUserPassWord().equals(userList.get(0).getUserPassWord())) {
                //"登录成功！"
                //将用户信息放入session中，之后获取用户都从session中获取
                session.setAttribute("user", userList.get(0));
                return 1;
            } else {
                //"密码错误"
                return 2;
            }
            //"用户名不存在,请先注册"
        } else return 3;
    }

    @Override
    public Boolean editUser(User user) {
        return 1 == userMapper.updateByPrimaryKeySelective(user);
    }
}
