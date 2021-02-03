package com.zdb.demo.service.impl;



import com.zdb.demo.entity.Store;
import com.zdb.demo.entity.StoreExample;
import com.zdb.demo.entity.User;
import com.zdb.demo.mapper.StoreMapper;
import com.zdb.demo.mapper.UserMapper;
import com.zdb.demo.service.StoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("storeService")
public class StoreServiceImpl implements StoreService {
    @Resource
    private StoreMapper storeMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public Boolean addStore(Store store, User user) {
        storeMapper.insertSelective(store);
        user.setUserStore(store.getStoreId());
        return 1 == userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public Boolean editStore(Store store) {
        return 1 == storeMapper.updateByPrimaryKeySelective(store);
    }

    @Override
    public List<Store> getStoreList() {
        StoreExample example = new StoreExample();
        StoreExample.Criteria criteria = example.createCriteria();
        criteria.andStoreStateEqualTo(1);
        criteria.andStoreDelEqualTo(0);
        List<Store> stores = storeMapper.selectByExample(example);
        return stores;
    }

    @Override
    public Store findStoreById(Integer storeId) {
        return storeMapper.selectByPrimaryKey(storeId);
    }
}
