package com.zdb.demo.service.impl;

import com.zdb.demo.entity.*;
import com.zdb.demo.mapper.MenuCollectMapper;
import com.zdb.demo.mapper.MenuMapper;
import com.zdb.demo.mapper.MenuTypeMapper;
import com.zdb.demo.mapper.StoreMapper;
import com.zdb.demo.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
        MenuCollectExample.Criteria criteria = example.createCriteria();
        criteria.andMenuIdEqualTo(menuId);
        criteria.andUserIdEqualTo(userId);
        List<MenuCollect> menuCollects = menuCollectMapper.selectByExample(example);
        if (menuCollects.isEmpty()) {
            return false;
        } else {
            for (MenuCollect menuCollect : menuCollects) {
                menuCollectMapper.deleteByPrimaryKey(menuCollect.getMenuCollectId());
            }
            return true;
        }
    }

    @Override
    public Boolean delMenuById(Integer menuId) {
        return menuMapper.deleteByPrimaryKey(menuId) == 1;
    }

    @Override
    public List<Menu> getMenuList(Integer storeId) {
        MenuExample example = new MenuExample();
        MenuExample.Criteria criteria = example.createCriteria();
        criteria.andMenuStoreIdEqualTo(storeId);
        return menuMapper.selectByExample(example);
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


}
