package com.zdb.demo.controller;

import com.zdb.demo.entity.Orders;
import com.zdb.demo.entity.User;
import com.zdb.demo.service.OrderService;
import com.zdb.demo.util.ResultUtil;
import com.zdb.demo.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.List;

/**
 * 订单管理
 */
@Slf4j
@Scope("prototype")
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    /**
     * 查询订单
     *
     * @param storeId
     * @param session
     * @return
     */
    @GetMapping("/getOrders")
    public Map<String, Object> getOrders(@RequestParam("storeId") Integer storeId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Orders> orders = orderService.getOrders(storeId, user);
        if (orders.isEmpty()) {
            return ResultUtil.resultFail("没有订单", null, null);
        } else return ResultUtil.resultSuccess("存在订单", null, orders);
    }

    /**
     * 提交订单
     *
     * @param object
     * @param session
     * @return
     */
    @PostMapping("/submitOrder")
    public Map<String, Object> submitOrder(@RequestBody JSONObject object, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Boolean isSuccess = orderService.addOrder(object, user);
        if (isSuccess) {
            return ResultUtil.resultSuccess("提交订单成功", null, isSuccess);
        } else return ResultUtil.resultFail("提交订单失败", null, null);
    }
}
