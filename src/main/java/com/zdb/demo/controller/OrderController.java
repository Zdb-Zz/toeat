package com.zdb.demo.controller;

import com.alibaba.fastjson.JSON;
import com.zdb.demo.entity.*;
import com.zdb.demo.mapper.OrdersMapper;
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

    @Resource
    private OrdersMapper ordersMapper;

    /**
     * 查询订单
     * @param storeId 商家id
     * @param state 0全部 1已支付 2未支付
     * @param timeOrder 0默认 1顺序 2倒序
     * @param session
     * @return
     */
    @GetMapping("/getOrders")
    public Map<String, Object> getOrders(@RequestParam(value = "storeId",required = false) Integer storeId,
                                         @RequestParam(value = "state",required = false) Integer state,
                                         @RequestParam(value = "timeOrder",required = false) Integer timeOrder,
                                         @RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
                                         @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                         HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Orders> orders = orderService.getOrders(storeId, user,state,timeOrder,pageIndex,pageSize);
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
        storeService.getRateAvg(storeId);
        if (Objects.isNull(order)) {
            return ResultUtil.resultFail("没有订单", null, null);
        } else return ResultUtil.resultSuccess("存在订单", null, order);
    }
    /**
     * 支付
     */
    @GetMapping("/payOrder")
    public Map<String, Object> payOrder(@RequestParam("orderId") Integer orderId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Orders orderById = orderService.getOrderById(orderId);
        orderById.setOrderState(1);
        int i = ordersMapper.updateByPrimaryKeySelective(orderById);
        if (i==1) {
            return ResultUtil.resultSuccess("支付成功", null, true);
        } else return ResultUtil.resultFail("支付失败", null, false);
    }

    /**
     * 商家查询订单
     * @param storeId 商家id
     * @param state 0全部 1已支付 2未支付
     * @param timeOrder 0默认 1顺序 2倒序
     * @param session
     * @return
     */
    @GetMapping("/getStoreOrders")
    public Map<String, Object> getStoreOrders(@RequestParam(value = "storeId",required = false) Integer storeId,
                                         @RequestParam(value = "state",required = false) Integer state,
                                         @RequestParam(value = "timeOrder",required = false) Integer timeOrder,
                                         @RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
                                         @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                         HttpSession session) {
        Store store = new Store();
        store.setStoreNotify(0);
        store.setStoreId(storeId);
        storeService.editStore(store);
        List<Orders> orders = orderService.getOrders(storeId, null,state,timeOrder,pageIndex,pageSize);
        if (orders.isEmpty()) {
            return ResultUtil.resultFail("没有订单", null, null);
        } else return ResultUtil.resultSuccess("存在订单", null, orders);
    }

    /**
     * 改变状态
     * @param session
     * @return
     */
    @GetMapping("/completeMenu")
    public Map<String, Object> completeMenu(@RequestParam(value = "orderMenuId") Integer orderMenuId,
                                              HttpSession session) {
        Boolean isSuccess = orderService.completeMenu(orderMenuId);

        if (isSuccess) {
            return ResultUtil.resultFail("修改成功", null, null);
        } else return ResultUtil.resultSuccess("修改失败", null, null);
    }

}
