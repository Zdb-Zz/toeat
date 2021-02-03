package com.zdb.demo.service;

import com.zdb.demo.entity.Store;
import com.zdb.demo.entity.User;

import java.util.List;

public interface StoreService {
    Boolean addStore(Store store, User user);

    Boolean editStore(Store store);

    List<Store> getStoreList();

    Store findStoreById(Integer storeId);
}
