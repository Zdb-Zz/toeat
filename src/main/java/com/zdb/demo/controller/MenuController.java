package com.zdb.demo.controller;

import com.zdb.demo.entity.Menu;
import com.zdb.demo.entity.MenuCollect;
import com.zdb.demo.entity.User;
import com.zdb.demo.service.MenuService;
import com.zdb.demo.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.List;

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
        if (!menuList.isEmpty()) {
            return ResultUtil.resultSuccess("获取菜品列表成功", null, menuList);
        } else return ResultUtil.resultFail("获取菜品列表失败", null, null);
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

}
