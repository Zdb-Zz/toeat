package com.zdb.demo.service.impl;

import com.zdb.demo.entity.Store;
import com.zdb.demo.entity.User;
import com.zdb.demo.entity.UserExample;
import com.zdb.demo.mapper.StoreMapper;
import com.zdb.demo.mapper.UserMapper;
import com.zdb.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Resource
    StoreMapper storeMapper;

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
    public Map<String,Object> loginUser(User user, HttpSession session) {
        HashMap<String, Object> map = new HashMap<>();
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(user.getUserName());
        List<User> userList = userMapper.selectByExample(example);
        if (!userList.isEmpty()) {
            if (user.getUserPassWord().equals(userList.get(0).getUserPassWord())) {
                //"登录成功！"
                //将用户信息放入session中，之后获取用户都从session中获取
                session.setAttribute("user", userList.get(0));
                map.put("type",1);
                map.put("user",userList.get(0));
                return map;
            } else {
                //"密码错误"
                map.put("type",2);
                return map;
            }
            //"用户名不存在,请先注册"
        } else {
            map.put("type",2);
            return map;
        }
    }

    @Override
    public Boolean editUser(User user) {
        return 1 == userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public Store getStoreByUser(Integer userId,HttpSession session) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (Objects.nonNull(user.getUserStore())){
            Store store = storeMapper.selectByPrimaryKey(user.getUserStore());
            session.setAttribute("store", store);
            return store;
        }
        return null;
    }
}
