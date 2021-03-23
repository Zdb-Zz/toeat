package com.zdb.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.zdb.demo.entity.*;
import com.zdb.demo.mapper.*;
import com.zdb.demo.service.MenuService;
import com.zdb.demo.util.DateUtilJava8;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service("menuService")
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private MenuCollectMapper menuCollectMapper;
    @Resource
    private StoreMapper storeMapper;
    @Resource
    private MenuTypeMapper menuTypeMapper;
    @Resource
    private ShoppingCartMapper shoppingCartMapper;

    @Override
    public Boolean addMenu(Menu menu) {
        return 1 == menuMapper.insertSelective(menu);
    }

    @Override
    public Boolean editMenu(Menu menu) {
        return 1 == menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public Boolean collectMenu(Integer menuId, Integer userId) {
        MenuCollect menuCollect = new MenuCollect();
        menuCollect.setMenuId(menuId);
        menuCollect.setUserId(userId);
        return 1 == menuCollectMapper.insertSelective(menuCollect);
    }

    @Override
    public Boolean unCollectMenu(Integer menuId, Integer userId) {
        MenuCollectExample example = new MenuCollectExample();
        example.createCriteria().andMenuIdEqualTo(menuId).andUserIdEqualTo(userId);
        return 1 == menuCollectMapper.deleteByExample(example);
    }

    @Override
    public Boolean delMenuById(Integer menuId) {
        return menuMapper.deleteByPrimaryKey(menuId) == 1;
    }

    @Override
    public List<Menu> getMenuList(Integer storeId, Integer menuType) {
        MenuExample example = new MenuExample();
        MenuExample.Criteria criteria = example.createCriteria();
        if (storeId != null) {
            criteria.andMenuStoreIdEqualTo(storeId);
        }
        if (menuType != null) {
            criteria.andMenuTypeEqualTo(menuType);
        }
        criteria.andMenuDelEqualTo(0);
        return menuMapper.selectByExample(example);
    }

    @Override
    public List<Menu> getMenuListSql(Integer storeId, Integer menuType, Integer userId, String menuName) {
        if (menuName != null && menuName != "") {
            menuType = null;
        }
        return menuMapper.getMenuList(storeId, menuType, userId, menuName);
    }

    @Override
    public List<Menu> getCollectList(Integer userId) {
        List<Menu> menuList = new ArrayList<>();
        MenuCollectExample example = new MenuCollectExample();
        MenuCollectExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<MenuCollect> menuCollectList = menuCollectMapper.selectByExample(example);
        for (MenuCollect collect : menuCollectList) {
            int menuId = collect.getMenuId();
            Menu menu = menuMapper.selectByPrimaryKey(menuId);
            menu.setStoreName(storeMapper.selectByPrimaryKey(menu.getMenuStoreId()).getStoreName());
            menuList.add(menu);
        }
        return menuList;
    }

    @Override
    public Menu getMenuById(Integer menuId) {
        return menuMapper.selectByPrimaryKey(menuId);
    }

    @Override
    public List<MenuType> getMenuTypeList(Integer storeId) {
        List<MenuType> menuTypes = menuTypeMapper.findMenuListByStoreId(storeId);
        return menuTypes;
    }

    @Override
    public List<ShoppingCart> getShoppingCarts(Integer userId) {
        ShoppingCartExample example = new ShoppingCartExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<ShoppingCart> shoppingCarts = shoppingCartMapper.selectByExample(example);
        return shoppingCarts;
    }

    @Override
    public Boolean addMenuNum(Integer menuId, Integer userId) {
        ShoppingCartExample example = new ShoppingCartExample();
        example.createCriteria().andUserIdEqualTo(userId).andMenuIdEqualTo(menuId);
        List<ShoppingCart> shoppingCarts = shoppingCartMapper.selectByExample(example);
        if (shoppingCarts.isEmpty()) {
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setMenuId(menuId);
            shoppingCart.setUserId(userId);
            shoppingCart.setMenuNum(1);
            return 1 == shoppingCartMapper.insertSelective(shoppingCart);
        } else {
            ShoppingCart shoppingCart = shoppingCarts.get(0);
            shoppingCart.setMenuNum(shoppingCart.getMenuNum() + 1);
            return 1 == shoppingCartMapper.updateByPrimaryKeySelective(shoppingCart);
        }
    }

    @Override
    public Boolean subMenuNum(Integer menuId, Integer userId) {
        ShoppingCartExample example = new ShoppingCartExample();
        example.createCriteria().andUserIdEqualTo(userId).andMenuIdEqualTo(menuId);
        List<ShoppingCart> shoppingCarts = shoppingCartMapper.selectByExample(example);
        ShoppingCart shoppingCart = shoppingCarts.get(0);
        if (shoppingCart.getMenuNum() == 1) {
            return 1 == shoppingCartMapper.deleteByPrimaryKey(shoppingCart.getShoppingCartId());
        } else {
            shoppingCart.setMenuNum(shoppingCart.getMenuNum() - 1);
            return 1 == shoppingCartMapper.updateByPrimaryKeySelective(shoppingCart);
        }
    }

    @Override
    public BigDecimal getTotalPrice(Integer userId) {
        BigDecimal totalPrice = new BigDecimal(0);
        ShoppingCartExample example = new ShoppingCartExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<ShoppingCart> shoppingCarts = shoppingCartMapper.selectByExample(example);
        for (ShoppingCart shoppingCart : shoppingCarts) {
            Menu menu = menuMapper.selectByPrimaryKey(shoppingCart.getMenuId());
            BigDecimal thisTotalPrice = menu.getMenuPrice().multiply(menu.getMenuDiscount()).
                    divide(new BigDecimal(100), 2, RoundingMode.HALF_DOWN).
                    multiply(BigDecimal.valueOf(shoppingCart.getMenuNum()));
            totalPrice = totalPrice.add(thisTotalPrice);
        }
        return totalPrice;
    }

    @Override
    public List<Menu> getShopCarList(Integer userId) {
        List<Menu> shopCarList = menuMapper.findShopCarList(userId);
        return shopCarList;
    }

    @Override
    public List<MenuCollect> getCollectMenu(Integer userId) {
        MenuCollectExample example = new MenuCollectExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<MenuCollect> collects = menuCollectMapper.selectByExample(example);
        return collects;
    }

    @Override
    public List<Menu> getMenuBySale(Integer storeId, Integer menuType, Integer limitNum, Integer rankType, Integer timeType) {
        Map<String,Object> map = new HashMap<>();
        Date startTime = null;
        Date endTime = null;
        if (rankType == 1) {
            //年
            startTime = DateUtilJava8.getYearFirst(DateUtilJava8.getNowYear());
            endTime = DateUtilJava8.getYearLast(DateUtilJava8.getNowYear());
        }
        if (rankType == 2) {
            //月
            startTime = DateUtilJava8.getMonthstart(DateUtilJava8.getNow());
            endTime = DateUtilJava8.getMonthend(DateUtilJava8.getNow());
        }
        if (rankType == 3) {
            //日
            startTime = DateUtilJava8.getDateStart(DateUtilJava8.getNow());
            endTime = DateUtilJava8.getDateFinish(DateUtilJava8.getNow());
        }

        map.put("startTime",DateUtilJava8.dateToString(startTime, "yyyy-MM-dd HH:mm:ss"));
        map.put("endTime",DateUtilJava8.dateToString(endTime, "yyyy-MM-dd HH:mm:ss"));
        map.put("storeId",storeId);
        map.put("menuType",menuType);
        map.put("limitNum",4);

        return menuMapper.menuSalesRank(map);

    }

    @Override
    public Boolean cleanShopCar(Integer storeId, Integer userId) {
        List<ShoppingCart> shoppingCarts = shoppingCartMapper.cleanShopCar(storeId, userId);
        for (ShoppingCart shoppingCart : shoppingCarts) {
            shoppingCartMapper.deleteByPrimaryKey(shoppingCart.getShoppingCartId());
        }
        return true;
    }
}
