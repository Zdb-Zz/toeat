package com.zdb.demo.controller;

import com.mysql.cj.xdevapi.JsonArray;
import com.zdb.demo.entity.User;
import com.zdb.demo.service.OrderService;
import com.zdb.demo.vo.OrderMenuVo;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.List;

@Slf4j
@Scope("prototype")
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping("/submitOrder")
    public Map<String,Object> submitOrder(@RequestBody JSONObject object,HttpSession session){

        User user = (User) session.getAttribute("user");
        orderService.addOrder(object,user);
        return null;
    }
}
