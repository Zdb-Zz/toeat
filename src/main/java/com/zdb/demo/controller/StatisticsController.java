package com.zdb.demo.controller;

import com.zdb.demo.entity.Menu;
import com.zdb.demo.service.MenuService;
import com.zdb.demo.service.StoreService;
import com.zdb.demo.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统计
 */
@Slf4j
@Scope("prototype")
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Resource
    MenuService menuService;

    @Resource
    StoreService storeService;

    /**
     * 总销量排行
     * @param menuType 菜品类型
     * @param storeId
     * @param rankType 1--由高到低  2--由低到高
     * @return
     */
    @GetMapping("salesRank")
    private Map<String,Object> salesRank(Integer menuType,Integer storeId,Integer rankType,Integer timeType){
        List<Menu> menuBySale = menuService.getMenuBySale(storeId, menuType, 4,rankType,timeType);
        List<String> menuNames = new ArrayList<>();
        List<Long> menuSales = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        for (Menu menu:menuBySale){
            menuNames.add(menu.getMenuName());
            menuSales.add(menu.getMenuSales());
        }
        map.put("menuNames",menuNames);
        map.put("menuSales",menuSales);
        return ResultUtil.resultSuccess("总销量排行获取成功", null, map);
    }


    /**
     * 今日营业状况
     * @param storeId
     * @return
     */
    @GetMapping("todayBusiness")
    private Map<String,Object> todayBusiness(Integer storeId){
        Map<String, Object> map = storeService.todayBusiness(storeId);

        return ResultUtil.resultSuccess("今日营业状况获取成功", null, map);
    }

    /**
     * 总营业状况
     * @param storeId
     * @return
     */
    @GetMapping("totalBusiness")
    private Map<String,Object> totalBusiness(Integer storeId,Integer timeType){
        List<Map<String, Object>> mapList = storeService.totalBusiness(storeId,timeType);
        String[] timeList = new String[mapList.size()];
        Long[] countList = new Long[mapList.size()];
        BigDecimal[] sumList = new BigDecimal[mapList.size()];
        for (int i =0;i<mapList.size();i++){
            if (timeType==0){
                timeList[i]=mapList.get(i).get("dayTime")+"月";
            }else {
                timeList[i]=mapList.get(i).get("dayTime")+"日";
            }
            countList[i] = (Long) mapList.get(i).get("orderCount");
            sumList[i] = (BigDecimal) mapList.get(i).get("orderSum");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("timeList",timeList);
        map.put("countList",countList);
        map.put("sumList",sumList);
        return ResultUtil.resultSuccess("总销量排行获取成功", null, map);
    }
}
