package com.zdb.demo.service.impl;

import com.zdb.demo.entity.OrderMenu;
import com.zdb.demo.entity.Orders;
import com.zdb.demo.entity.User;
import com.zdb.demo.mapper.OrderMenuMapper;
import com.zdb.demo.mapper.OrdersMapper;
import com.zdb.demo.service.OrderService;
import com.zdb.demo.util.DateUtilJava8;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service("OrderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private OrderMenuMapper orderMenuMapper;
    @Override
    public Boolean addOrder(JSONObject object,User user) {
        JSONArray array = JSONArray.fromObject(object.get("menu"));
        int storeId = (int) object.get("storeId");
        BigDecimal orderSumPrice = BigDecimal.valueOf(Long.parseLong(object.get("orderSumPrice").toString()));
        String orderRemark = (String) object.get("orderRemark");
        Orders order = new Orders();
        //用户id
        order.setOrderUserId(user.getUserId());
        //创建日期
        order.setOrderCreateTime(DateUtilJava8.getNow());
        //商家id
        order.setOrderStoreId(storeId);
        //总价格
        order.setOrderSumPrice(orderSumPrice);
        //备注
        order.setOrderRemark(orderRemark);
        ordersMapper.insertSelective(order);
        for (int i = 0; i < array.size(); i++) {
            OrderMenu orderMenu = new OrderMenu();
            orderMenu.setMenuId((Integer) JSONObject.fromObject(array.get(i)).get("menuId"));
            orderMenu.setMenuNum((Integer) JSONObject.fromObject(array.get(i)).get("menuNum"));
            orderMenu.setOrderMenuPrice(BigDecimal.valueOf(Long.parseLong(JSONObject.fromObject(array.get(i)).get("menuPrice").toString())));
            orderMenu.setOrderId(order.getOrderId());
            orderMenuMapper.insertSelective(orderMenu);
        }
        return true;
    }
}
