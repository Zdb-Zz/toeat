package com.zdb.demo.service;

import com.zdb.demo.entity.Advertisement;
import com.zdb.demo.entity.Store;
import com.zdb.demo.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface StoreService {
    Integer addStore(Store store, User user);

    Boolean editStore(Store store);

    List<Store> getStoreList(String storeName);

    Store findStoreById(Integer storeId);

    Map<String,Object> todayBusiness(Integer storeId);

    List<Map<String,Object>> totalBusiness(Integer storeId,Integer timeType);

    Boolean getRateAvg(Integer storeId);

    List<String> editAdvertisement(List<String> imgList,Integer storeId,Integer type);

    List<Advertisement> getAdvertisement(Integer storeId, Integer type);
}
