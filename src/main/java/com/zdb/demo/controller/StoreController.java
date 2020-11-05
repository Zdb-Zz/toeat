package com.zdb.demo.controller;

import com.zdb.demo.entity.Store;
import com.zdb.demo.entity.User;
import com.zdb.demo.service.StoreService;
import com.zdb.demo.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.List;

/**
 * 商家管理
 */
@Slf4j
@Scope("prototype")
@RestController
@RequestMapping("/store")
public class StoreController {

    @Resource
    private StoreService storeService;

    /**
     * 创建商家
     *
     * @param store
     * @param session
     * @return
     */
    @PostMapping("/addStore")
    public Map<String, Object> addStore(@RequestBody Store store, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Boolean isSuccess = storeService.addStore(store, user);
        if (isSuccess) {
            return ResultUtil.resultSuccess("创建商家成功", null, isSuccess);
        } else return ResultUtil.resultFail("创建商家失败", null, null);
    }

    /**
     * 编辑商家
     *
     * @param store
     * @return
     */
    @PostMapping("/editStore")
    public Map<String, Object> editStore(@RequestBody Store store) {
        Boolean isSuccess = storeService.editStore(store);
        if (isSuccess) {
            return ResultUtil.resultSuccess("编辑商家成功", null, isSuccess);
        } else return ResultUtil.resultFail("编辑商家失败", null, null);
    }

    /**
     * 获取商家列表
     * @return
     */
    @GetMapping("/getStoreList")
    public Map<String, Object> getStoreList() {
        List<Store> storeList = storeService.getStoreList();
        if (!storeList.isEmpty()) {
            return ResultUtil.resultSuccess("获取商家列表成功", null, storeList);
        } else return ResultUtil.resultFail("获取商家列表失败", null, null);
    }
}
