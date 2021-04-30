package com.zdb.demo.service.impl;


import com.zdb.demo.entity.*;
import com.zdb.demo.mapper.AdvertisementMapper;
import com.zdb.demo.mapper.MenuTypeMapper;
import com.zdb.demo.mapper.StoreMapper;
import com.zdb.demo.mapper.UserMapper;
import com.zdb.demo.service.StoreService;
import com.zdb.demo.util.DateUtilJava8;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service("storeService")
public class StoreServiceImpl implements StoreService {
    @Resource
    private StoreMapper storeMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private MenuTypeMapper menuTypeMapper;
    @Resource
    private AdvertisementMapper advertisementMapper;

    @Override
    public Integer addStore(Store store, User user) {
        storeMapper.insertSelective(store);
        MenuType menuType = new MenuType();
        menuType.setStoreId(store.getStoreId());
        //创建商家后自动为该商家生成一个菜品类别
        menuType.setMenuTypeName("全部");
        menuType.setMenuTypeWeight(100);
        menuTypeMapper.insertSelective(menuType);
        //将商家id绑定到用户表中
        user.setUserStore(store.getStoreId());
        userMapper.updateByPrimaryKeySelective(user);
        return store.getStoreId();
    }

    @Override
    public Boolean editStore(Store store) {
        return 1 == storeMapper.updateByPrimaryKeySelective(store);
    }

    @Override
    public List<Store> getStoreList(String storeName, BigDecimal lng, BigDecimal lat) {
        StoreExample example = new StoreExample();
        StoreExample.Criteria criteria = example.createCriteria();
        criteria.andStoreStateEqualTo(1);
        criteria.andStoreDelEqualTo(0);
        if (storeName != null && storeName != "") {
            criteria.andStoreNameLike("%" + storeName + "%");
        }
        if (lng != null && lat != null) {
            String orderStr = "( POWER( store_lng - " + lng + ", 2 ) + POWER( store_lat - " + lat + ", 2 ) )";
            example.setOrderByClause(orderStr);
        }
        List<Store> stores = storeMapper.selectByExample(example);
        return stores;
    }

    @Override
    public Store findStoreById(Integer storeId) {
        return storeMapper.selectByPrimaryKey(storeId);
    }

    @Override
    public Map<String, Object> todayBusiness(Integer storeId) {
        Map<String, Object> getOrderCountMap = new HashMap<>();
        Date startTime = DateUtilJava8.getDateStart(DateUtilJava8.getNow());
        Date endTime = DateUtilJava8.getDateFinish(DateUtilJava8.getNow());
        getOrderCountMap.put("startTime", DateUtilJava8.dateToString(startTime, "yyyy-MM-dd HH:mm:ss"));
        getOrderCountMap.put("endTime", DateUtilJava8.dateToString(endTime, "yyyy-MM-dd HH:mm:ss"));
        getOrderCountMap.put("storeId", storeId);
        Map<String, Object> result = storeMapper.getOrderCount(getOrderCountMap);
        return result;
    }

    @Override
    public List<Map<String, Object>> totalBusiness(Integer storeId, Integer timeType) {
        Map<String, Object> map = new HashMap<>();
        Date startTime = null;
        Date endTime = null;
        List<Map<String, Object>> result = new ArrayList<>();
        map.put("storeId", storeId);
        if (timeType == 0) {
            //年
            startTime = DateUtilJava8.getYearFirst(DateUtilJava8.getNowYear());
            endTime = DateUtilJava8.getYearLast(DateUtilJava8.getNowYear());
            map.put("startTime", DateUtilJava8.dateToString(startTime, "yyyy-MM-dd HH:mm:ss"));
            map.put("endTime", DateUtilJava8.dateToString(endTime, "yyyy-MM-dd HH:mm:ss"));
            result = storeMapper.getYearBusiness(map);
            return result;
        } else {
            //月
            startTime = DateUtilJava8.getMonthstart(DateUtilJava8.getNow());
            endTime = DateUtilJava8.getMonthend(DateUtilJava8.getNow());
            map.put("startTime", DateUtilJava8.dateToString(startTime, "yyyy-MM-dd HH:mm:ss"));
            map.put("endTime", DateUtilJava8.dateToString(endTime, "yyyy-MM-dd HH:mm:ss"));
            result = storeMapper.getDayBusiness(map);
            return result;
        }

    }

    @Override
    public Boolean getRateAvg(Integer storeId) {
        storeMapper.getRateAvg(storeId);
        return true;
    }

    @Override
    public List<String> editAdvertisement(List<String> imgList, Integer storeId, Integer type) {
        AdvertisementExample example = new AdvertisementExample();
        example.createCriteria().andStoreIdEqualTo(storeId).andAdvertisementTypeEqualTo(type);
        advertisementMapper.deleteByExample(example);
        for (String imgSrc : imgList) {
            Advertisement advertisement = new Advertisement();
            advertisement.setAdvertisementType(type);
            advertisement.setStoreId(storeId);
            advertisement.setAdvertisementImg(imgSrc);
            advertisementMapper.insertSelective(advertisement);
        }
        return null;
    }

    @Override
    public List<Advertisement> getAdvertisement(Integer storeId, Integer type) {
        AdvertisementExample example = new AdvertisementExample();
        example.createCriteria().andStoreIdEqualTo(storeId).andAdvertisementTypeEqualTo(type);
        List<Advertisement> advertisements = advertisementMapper.selectByExample(example);
        return advertisements;
    }
}
