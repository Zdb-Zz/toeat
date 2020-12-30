package com.zdb.demo.service;

import com.zdb.demo.entity.Menu;
import com.zdb.demo.entity.MenuCollect;
import com.zdb.demo.entity.MenuType;

import java.math.BigDecimal;
import java.util.List;

public interface MenuService {
    Boolean addMenu(Menu menu);

    Boolean editMenu(Menu menu);

    Boolean collectMenu(Integer menuId, Integer userId);

    Boolean unCollectMenu(Integer menuId, Integer userId);

    Boolean delMenuById(Integer menuId);

    List<Menu> getMenuList(Integer storeId,Integer menuType);

    List<Menu> getCollectList(Integer userId);

    Menu getMenuById(Integer menuId);

    List<MenuType> getMenuTypeList(Integer storeId);

}
