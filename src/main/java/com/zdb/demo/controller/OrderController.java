package com.zdb.demo.controller;

import com.alibaba.fastjson.JSON;
import com.zdb.demo.entity.Menu;
import com.zdb.demo.entity.Orders;
import com.zdb.demo.entity.Store;
import com.zdb.demo.entity.User;
import com.zdb.demo.service.MenuService;
import com.zdb.demo.service.OrderService;
import com.zdb.demo.service.StoreService;
import com.zdb.demo.util.ResultUtil;
import com.zdb.demo.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Map;
import java.util.List;
import java.util.Objects;

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

    @Resource
    private MenuService menuService;

    @Resource
    private StoreService storeService;

    /**
     * 查询订单
     *
     * @param storeId
     * @param session
     * @return
     */
    @GetMapping("/getOrders")
    public Map<String, Object> getOrders(@RequestParam(value = "storeId",required = false) Integer storeId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Orders> orders = orderService.getOrders(storeId, user);
        if (orders.isEmpty()) {
            return ResultUtil.resultFail("没有订单", null, null);
        } else return ResultUtil.resultSuccess("存在订单", null, orders);
    }

    /**
     * 提交订单
     *
     * @param
     * @param session
     * @return
     */
    @PostMapping("/submitOrder")
    public Map<String, Object> submitOrder(@RequestBody JSONObject object, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String remark = object.getString("remark");
        Integer storeId = object.getInt("storeId");
        BigDecimal totalPrice = BigDecimal.valueOf(object.getDouble("totalPrice"));
        List<Menu> menuList = JSON.parseArray(object.getString("menus"), Menu.class);
        Integer orderId = orderService.addOrder(menuList, remark, totalPrice, storeId, user);
        menuService.cleanShopCar(storeId, user.getUserId());
        if (orderId != null) {
            return ResultUtil.resultSuccess("提交订单成功", null, orderId);
        } else return ResultUtil.resultFail("提交订单失败", null, null);
    }

    /**
     * 取消订单
     *
     * @param orderId
     * @return
     */
    @GetMapping("/cancelOrder")
    public Map<String, Object> cancelOrder(@RequestParam("orderId") Integer orderId) {
        Boolean isSuccess = orderService.cancelOrder(orderId);
        if (isSuccess) {
            return ResultUtil.resultSuccess("取消订单成功", null, isSuccess);
        } else return ResultUtil.resultFail("取消订单失败", null, null);
    }

    /**
     * 查询订单id查询订单
     *
     * @param
     * @param session
     * @return
     */
    @GetMapping("/getOrderById")
    public Map<String, Object> getOrderById(@RequestParam("orderId") Integer orderId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Orders order = orderService.getOrderById(orderId);
        if (Objects.isNull(order)) {
            return ResultUtil.resultFail("没有订单", null, null);
        } else return ResultUtil.resultSuccess("存在订单", null, order);
    }

    /**
     * 评价
     */
    @PostMapping("/evaluateOrder")
    public Map<String, Object> evaluateOrder(@RequestBody JSONObject object, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Integer orderId = object.getInt("orderId");
        String orderEvaluate = object.getString("orderEvaluate");
        Integer rate = object.getInt("rate");
        Orders order = orderService.getOrderById(orderId);
        order.setOrderEvaluate(orderEvaluate);
        order.setOrderRate(rate);
        orderService.editOrder(order);
        Integer storeId = order.getOrderStoreId();
        Store store = storeService.findStoreById(storeId);
        store.setStoreStar((store.getStoreStar()+rate)/2);
        storeService.editStore(store);
        if (Objects.isNull(order)) {
            return ResultUtil.resultFail("没有订单", null, null);
        } else return ResultUtil.resultSuccess("存在订单", null, order);
    }

}
