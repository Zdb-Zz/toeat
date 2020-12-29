package com.zdb.demo.controller;

import com.zdb.demo.entity.Menu;
import com.zdb.demo.entity.MenuCollect;
import com.zdb.demo.entity.MenuType;
import com.zdb.demo.entity.User;
import com.zdb.demo.mapper.MenuTypeMapper;
import com.zdb.demo.service.MenuService;
import com.zdb.demo.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * 菜品管理
 */
@Slf4j
@Scope("prototype")
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private MenuService menuService;

    @Resource
    private MenuTypeMapper menuTypeMapper;

    /**
     * 添加菜品
     *
     * @param menu
     * @param session
     * @return
     */
    @PostMapping("/addMenu")
    public Map<String, Object> addMenu(@RequestBody Menu menu, HttpSession session) {
        User user = (User) session.getAttribute("user");
        menu.setMenuStoreId(user.getUserStore());
        Boolean isSuccess = menuService.addMenu(menu);
        if (isSuccess) {
            return ResultUtil.resultSuccess("添加菜品成功", null, isSuccess);
        } else return ResultUtil.resultFail("添加菜品失败", null, null);
    }

    /**
     * 编辑菜品
     *
     * @param menu
     * @return
     */
    @PostMapping("/editMenu")
    public Map<String, Object> editMenu(@RequestBody Menu menu) {
        Boolean isSuccess = menuService.editMenu(menu);
        if (isSuccess) {
            return ResultUtil.resultSuccess("编辑菜品成功", null, isSuccess);
        } else return ResultUtil.resultFail("编辑菜品失败", null, null);
    }

    /**
     * 获取菜品列表
     *
     * @param storeId
     * @return
     */
    @GetMapping("/getMenuList")
    public Map<String, Object> getMenuList(@RequestParam("storeId") Integer storeId) {
        List<Menu> menuList = menuService.getMenuList(storeId);
        for (Menu menu : menuList) {
            menu.setPriceAfterDiscount(menu.getMenuPrice().multiply(menu.getMenuDiscount()).
                    divide(new BigDecimal(100), 2, RoundingMode.HALF_DOWN));
            menu.setMenuTypeDes(menuTypeMapper.selectByPrimaryKey(menu.getMenuType()).getMenuTypeName());

        }
        if (!menuList.isEmpty()) {
            return ResultUtil.resultSuccess("获取菜品列表成功", null, menuList);
        } else return ResultUtil.resultFail("获取菜品列表失败", null, null);
    }

    /**
     * 获取菜品详情
     *
     * @param menuId
     * @param session
     * @return
     */
    @GetMapping("/getMenuById")
    public Map<String, Object> getMenuById(@RequestParam("menuId") Integer menuId, HttpSession session) {
        Menu menu = menuService.getMenuById(menuId);
        menu.setPriceAfterDiscount(menu.getMenuPrice().multiply(menu.getMenuDiscount()).
                divide(new BigDecimal(100), 2, RoundingMode.HALF_DOWN));
        menu.setMenuTypeDes(menuTypeMapper.selectByPrimaryKey(menu.getMenuType()).getMenuTypeName());

        if (Objects.nonNull(menu)) {
            return ResultUtil.resultSuccess("获取菜品详情成功", null, menu);
        } else return ResultUtil.resultFail("获取菜品详情失败", null, null);
    }

    /**
     * 删除菜品
     *
     * @param menuId
     * @param session
     * @return
     */
    @GetMapping("/delMenuById")
    public Map<String, Object> delMenuById(@RequestParam("menuId") Integer menuId, HttpSession session) {
        Boolean isSuccess = menuService.delMenuById(menuId);
        if (isSuccess) {
            return ResultUtil.resultSuccess("删除菜品成功", null, isSuccess);
        } else return ResultUtil.resultFail("删除菜品失败", null, null);
    }


    /**
     * 收藏菜品
     *
     * @param menuId
     * @param session
     * @return
     */
    @GetMapping("/collectMenu")
    public Map<String, Object> collectMenu(@RequestParam("menuId") Integer menuId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Boolean isSuccess = menuService.collectMenu(menuId, user.getUserId());
        if (isSuccess) {
            return ResultUtil.resultSuccess("收藏菜品成功", null, isSuccess);
        } else return ResultUtil.resultFail("收藏菜品失败", null, null);
    }

    /**
     * 取消收藏
     *
     * @param menuId
     * @param session
     * @return
     */
    @GetMapping("/unCollectMenu")
    public Map<String, Object> unCollectMenu(@RequestParam("menuId") Integer menuId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Boolean isSuccess = menuService.unCollectMenu(menuId, user.getUserId());
        if (isSuccess) {
            return ResultUtil.resultSuccess("收藏菜品成功", null, isSuccess);
        } else return ResultUtil.resultFail("收藏菜品失败", null, null);
    }

    /**
     * 收藏菜品列表
     *
     * @param session
     * @return
     */
    @GetMapping("/getCollectList")
    public Map<String, Object> getCollectList(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Menu> menuList = menuService.getCollectList(user.getUserId());
        if (!menuList.isEmpty()) {
            return ResultUtil.resultSuccess("收藏菜品成功", null, menuList);
        } else return ResultUtil.resultFail("收藏菜品失败", null, null);
    }

    /**
     * 获取菜品类别列表
     */
    @GetMapping("/getMenuTypeList")
    public Map<String, Object> getMenuTypeList() {
        List<MenuType> menuTypeList = menuService.getMenuTypeList(null);
        if (!menuTypeList.isEmpty()) {
            return ResultUtil.resultSuccess("获取菜品类别成功", null, menuTypeList);
        } else return ResultUtil.resultFail("获取菜品类别失败", null, null);
    }

    /**
     * 获取商品类别字符串数组
     */
    @GetMapping("/getMenuStringList")
    public Map<String, Object> getMenuStringList(@RequestParam("storeId")Integer storeId) {
        List<MenuType> menuTypeList = menuService.getMenuTypeList(storeId);
        List<Map<String,Object>> maps = new ArrayList<>();
        for (MenuType menuType : menuTypeList){
            Map<String, Object> map = new HashMap<>();
            map.put("text",menuType.getMenuTypeName());
            map.put("menuTypeId",menuType.getMenuTypeId());
            maps.add(map);
        }
        if (!maps.isEmpty()) {
            return ResultUtil.resultSuccess("获取菜品类别成功", null, maps);
        } else return ResultUtil.resultFail("获取菜品类别失败", null, null);
    }
}
