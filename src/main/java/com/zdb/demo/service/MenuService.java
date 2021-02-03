package com.zdb.demo.service;

import com.zdb.demo.entity.Menu;
import com.zdb.demo.entity.MenuCollect;
import com.zdb.demo.entity.MenuType;
import com.zdb.demo.entity.ShoppingCart;

import java.math.BigDecimal;
import java.util.List;

public interface MenuService {
    Boolean addMenu(Menu menu);

    Boolean editMenu(Menu menu);

    Boolean collectMenu(Integer menuId, Integer userId);

    Boolean unCollectMenu(Integer menuId, Integer userId);

    Boolean delMenuById(Integer menuId);

    List<Menu> getMenuList(Integer storeId,Integer menuType);

    List<Menu> getMenuListSql(Integer storeId,Integer menuType,Integer userId);

    List<Menu> getCollectList(Integer userId);

    Menu getMenuById(Integer menuId);

    List<MenuType> getMenuTypeList(Integer storeId);

    List<ShoppingCart> getShoppingCarts(Integer userId);

    Boolean addMenuNum(Integer menuId,Integer userId);

    Boolean subMenuNum(Integer menuId,Integer userId);

    BigDecimal getTotalPrice(Integer userId);

    List<Menu> getShopCarList(Integer userId);

    List<MenuCollect> getCollectMenu(Integer userId);

    List<Menu> getMenuBySale(Integer storeId,Integer menuType);

    Boolean cleanShopCar(Integer storeId,Integer userId);
}
