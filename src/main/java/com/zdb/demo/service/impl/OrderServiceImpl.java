package com.zdb.demo.service.impl;

import com.zdb.demo.entity.*;
import com.zdb.demo.mapper.MenuMapper;
import com.zdb.demo.mapper.OrderMenuMapper;
import com.zdb.demo.mapper.OrdersMapper;
import com.zdb.demo.service.OrderService;
import com.zdb.demo.util.DateUtilJava8;
import com.zdb.demo.vo.OrderVo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service("OrderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private OrderMenuMapper orderMenuMapper;
    @Resource
    private MenuMapper menuMapper;

    @Override
    public Boolean addOrder(JSONObject object, User user) {
        JSONArray array = JSONArray.fromObject(object.get("menu"));
        int storeId = (int) object.get("storeId");
        BigDecimal orderSumPrice = BigDecimal.valueOf(Long.parseLong(object.get("orderSumPrice").toString()));
        String orderRemark = (String) object.get("orderRemark");
        Orders order = new Orders();
        order.setOrderUserId(user.getUserId());//用户id
        order.setOrderCreateTime(DateUtilJava8.getNow());//创建日期
        order.setOrderStoreId(storeId);//商家id
        order.setOrderSumPrice(orderSumPrice);//总价格
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

    @Override
    public List<Orders> getOrders(Integer storeId, User user) {
        OrdersExample example = new OrdersExample();
        OrdersExample.Criteria criteria = example.createCriteria();
        criteria.andOrderStoreIdEqualTo(storeId);
        criteria.andOrderUserIdEqualTo(user.getUserId());
        //查询未支付未删除的订单
        criteria.andOrderStateEqualTo(0);
        criteria.andOrderDelEqualTo(0);
        List<Orders> orders = ordersMapper.selectByExample(example);
        for (Orders order : orders) {
            int orderId = order.getOrderId();
            OrderMenuExample orderMenuExample = new OrderMenuExample();
            OrderMenuExample.Criteria criteria1 = orderMenuExample.createCriteria();
            criteria1.andOrderIdEqualTo(orderId);
            List<OrderMenu> orderMenus = orderMenuMapper.selectByExample(orderMenuExample);
            for (OrderMenu orderMenu :orderMenus){
                Menu menu = menuMapper.selectByPrimaryKey(orderMenu.getMenuId());
                orderMenu.setMenuName(menu.getMenuName());
            }
            order.setOrderMenus(orderMenus);
        }
        return orders;
    }
}
