package com.zdb.demo.service;

import com.zdb.demo.entity.Menu;
import com.zdb.demo.entity.MenuCollect;

import java.util.List;

public interface MenuService {
    Boolean addMenu(Menu menu);

    Boolean editMenu(Menu menu);

    Boolean collectMenu(Integer menuId, Integer userId);

    Boolean unCollectMenu(Integer menuId, Integer userId);

    List<Menu> getMenuList(Integer storeId);

    List<Menu> getCollectList(Integer userId);
}
