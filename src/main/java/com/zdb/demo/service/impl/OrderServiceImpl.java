package com.zdb.demo.service.impl;

import com.zdb.demo.entity.*;
import com.zdb.demo.mapper.MenuMapper;
import com.zdb.demo.mapper.OrderMenuMapper;
import com.zdb.demo.mapper.OrdersMapper;
import com.zdb.demo.mapper.StoreMapper;
import com.zdb.demo.service.OrderService;
import com.zdb.demo.util.DateUtilJava8;
import com.zdb.demo.vo.OrderVo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.criterion.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service("OrderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private OrderMenuMapper orderMenuMapper;

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private StoreMapper storeMapper;

    @Override
    public Integer addOrder(List<Menu> menus, String remark, BigDecimal totalPrice, Integer storeId, User user) {
        Orders order = new Orders();
        order.setOrderCreateTime(new Date());
        order.setOrderSumPrice(totalPrice);
        order.setOrderRemark(remark);
        order.setOrderStoreId(storeId);
        order.setOrderUserId(user.getUserId());
        order.setOrderState(0);
        ordersMapper.insertSelective(order);
        Integer orderId = order.getOrderId();
        for (Menu menu : menus) {
            OrderMenu orderMenu = new OrderMenu();
            orderMenu.setMenuId(menu.getMenuId());
            orderMenu.setMenuName(menu.getMenuName());
            orderMenu.setMenuNum(menu.getMenuNum());
            orderMenu.setOrderId(orderId);
            orderMenu.setMenuPriceOne(menu.getPriceAfterDiscount());
            orderMenu.setOrderMenuPrice(menu.getPriceAfterDiscount().multiply(new BigDecimal(menu.getMenuNum())));
            orderMenu.setMenuPepper(menu.getMenuFlavor());
            orderMenuMapper.insertSelective(orderMenu);
            Menu oldMenu = menuMapper.selectByPrimaryKey(menu.getMenuId());
            if (Objects.nonNull(oldMenu) && menu.getMenuNum() != null) {
                menu.setMenuSales(oldMenu.getMenuSales() + menu.getMenuNum());

            }
            menuMapper.updateByPrimaryKeySelective(menu);
        }
        return orderId;
    }

    @Override
    public Boolean cancelOrder(Integer orderId) {
        return 1 == ordersMapper.deleteByPrimaryKey(orderId);
    }

    @Override
    public List<Orders> getOrders(Integer storeId, User user) {
        OrdersExample example = new OrdersExample();
        OrdersExample.Criteria criteria = example.createCriteria();
        if (storeId != null) {
            criteria.andOrderStoreIdEqualTo(storeId);
        }
        criteria.andOrderUserIdEqualTo(user.getUserId());
        //查询未删除的订单
        criteria.andOrderDelEqualTo(0);
        List<Orders> orders = ordersMapper.selectByExample(example);
        for (Orders order : orders) {
            int orderId = order.getOrderId();
            OrderMenuExample orderMenuExample = new OrderMenuExample();
            OrderMenuExample.Criteria criteria1 = orderMenuExample.createCriteria();
            criteria1.andOrderIdEqualTo(orderId);
            List<OrderMenu> orderMenus = orderMenuMapper.selectByExample(orderMenuExample);
            for (OrderMenu orderMenu : orderMenus) {
                Menu menu = menuMapper.selectByPrimaryKey(orderMenu.getMenuId());
                orderMenu.setMenuName(menu.getMenuName());
            }
            if (order.getOrderCreateTime() != null) {
                String format = DateUtilJava8.dateToString(order.getOrderCreateTime(), "yyyy-MM-dd HH:mm:ss");
                order.setOrderCreateTime(DateUtilJava8.StringToDate(format));
            }
            order.setStoreName(storeMapper.selectByPrimaryKey(order.getOrderStoreId()).getStoreName());
            order.setOrderMenus(orderMenus);
        }
        return orders;
    }

    @Override
    public Orders getOrderById(Integer orderId) {
        OrderVo orderVo = new OrderVo();
        Orders orders = ordersMapper.selectByPrimaryKey(orderId);
//        orderVo.setOrderId(orders.getOrderId());
//        orderVo.setOrderCreateTime(orders.getOrderCreateTime());
//        orderVo.setOrderState(orders.getOrderState());
//        orderVo.setOrderStoreId(orders.getOrderStoreId());
//        orderVo.setOrderSumPrice(orders.getOrderSumPrice());
//        orderVo.setOrderRemark(orders.getOrderRemark());
//        orderVo.setOrderEvaluate(orders.getOrderEvaluate());
//        orderVo.setOrderRate(orders.getOrderRate());
        BeanUtils.copyProperties(orders,orderVo);
        OrderMenuExample orderMenuExample = new OrderMenuExample();
        orderMenuExample.createCriteria().andOrderIdEqualTo(orderId);
        List<OrderMenu> orderMenus = orderMenuMapper.selectByExample(orderMenuExample);
        orderVo.setOrderMenus(orderMenus);
        return orderVo;
    }

    @Override
    public Boolean editOrder(Orders orders) {
        return 1 == ordersMapper.updateByPrimaryKeySelective(orders);
    }


}
