package com.zdb.demo.service;

import com.zdb.demo.entity.Orders;
import com.zdb.demo.entity.User;
import com.zdb.demo.vo.OrderVo;
import net.sf.json.JSONObject;

import java.util.List;

public interface OrderService {
    Boolean addOrder(JSONObject object, User user);

    List<Orders> getOrders(Integer storeId, User user);
}
