package com.zdb.demo.service;

import com.zdb.demo.entity.Menu;
import com.zdb.demo.entity.Orders;
import com.zdb.demo.entity.User;
import com.zdb.demo.vo.OrderVo;
import net.sf.json.JSONObject;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    Integer addOrder(List<Menu> menus,String remark,BigDecimal totalPrice,Integer storeId, User user);

    Boolean cancelOrder(Integer orderId);

    List<Orders> getOrders(Integer storeId, User user,Integer state,Integer timeOrder);

    Orders getOrderById(Integer orderId);

    Boolean editOrder(Orders orders);

    Boolean completeMenu(Integer orderMenuId);


}
