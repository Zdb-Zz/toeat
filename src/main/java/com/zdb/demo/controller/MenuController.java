package com.zdb.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zdb.demo.entity.*;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public Map<String, Object> getMenuList(@RequestParam("storeId") Integer storeId,
                                           @RequestParam(value = "menuType", required = false) Integer menuType,
                                           @RequestParam(value = "menuName", required = false) String menuName,
                                           HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<MenuCollect> collects = new ArrayList<>();
        List<Menu> menuList = menuService.getMenuListSql(storeId, menuType, user.getUserId(), menuName);

        if (Objects.nonNull(user)) {
            collects = menuService.getCollectMenu(user.getUserId());
        }

        for (Menu menu : menuList) {
            //收藏
            if (!collects.isEmpty() && collects.size() > 0) {
                for (MenuCollect menuCollect : collects) {
                    if (menuCollect.getMenuId().equals(menu.getMenuId())) {
                        menu.setIsCollect(true);
                    }
                }
            }
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
            return ResultUtil.resultSuccess("取消收藏菜品成功", null, isSuccess);
        } else return ResultUtil.resultFail("取消收藏菜品失败", null, null);
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
    public Map<String, Object> getMenuTypeList(@RequestParam("storeId") Integer storeId) {
        List<MenuType> menuTypeList = menuService.getMenuTypeList(storeId);
        if (!menuTypeList.isEmpty()) {
            return ResultUtil.resultSuccess("获取菜品类别成功", null, menuTypeList);
        } else return ResultUtil.resultFail("获取菜品类别失败", null, null);
    }

    /**
     * 获取商品类别字符串数组
     */
    @GetMapping("/getMenuStringList")
    public Map<String, Object> getMenuStringList(@RequestParam("storeId") Integer storeId) {
        List<MenuType> menuTypeList = menuService.getMenuTypeList(storeId);
        List<Map<String, Object>> maps = new ArrayList<>();
        for (MenuType menuType : menuTypeList) {
            Map<String, Object> map = new HashMap<>();
            map.put("text", menuType.getMenuTypeName());
            map.put("menuTypeId", menuType.getMenuTypeId());
            maps.add(map);
        }
        if (!maps.isEmpty()) {
            return ResultUtil.resultSuccess("获取菜品类别成功", null, maps);
        } else return ResultUtil.resultFail("获取菜品类别失败", null, null);
    }

    /**
     * 消费者菜单列表增加数量
     */
    @GetMapping("/addMenuNum")
    public Map<String, Object> addMenuNum(@RequestParam("menuId") Integer menuId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Boolean isSuccess = menuService.addMenuNum(menuId, user.getUserId());
        if (isSuccess) {
            return ResultUtil.resultSuccess("增加数量成功", null, isSuccess);
        } else return ResultUtil.resultFail("增加数量失败", null, null);
    }

    /**
     * 消费者菜单列表增加数量
     */
    @GetMapping("/subMenuNum")
    public Map<String, Object> subMenuNum(@RequestParam("menuId") Integer menuId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Boolean isSuccess = menuService.subMenuNum(menuId, user.getUserId());
        if (isSuccess) {
            return ResultUtil.resultSuccess("减少数量成功", null, isSuccess);
        } else return ResultUtil.resultFail("减少数量失败", null, null);
    }

    /**
     * 获取总价
     */
    @GetMapping("/getTotalPrice")
    public Map<String, Object> getTotalPrice(HttpSession session) {
        User user = (User) session.getAttribute("user");
        BigDecimal totalPrice = menuService.getTotalPrice(user.getUserId());
        if (totalPrice != null) {
            return ResultUtil.resultSuccess("获取总价成功", null, totalPrice);
        } else return ResultUtil.resultFail("获取总价失败", null, null);
    }

    /**
     * 获取购物车列表
     */
    @GetMapping("/getShopCarList")
    public Map<String, Object> getShopCarList(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Menu> shopCarList = menuService.getShopCarList(user.getUserId());
        if (shopCarList != null && shopCarList.size() > 0) {
            for (Menu menu : shopCarList) {
                menu.setPriceAfterDiscount(menu.getMenuPrice().multiply(menu.getMenuDiscount()).
                        divide(new BigDecimal(100), 2, RoundingMode.HALF_DOWN));
            }
        }
        if (shopCarList != null) {
            return ResultUtil.resultSuccess("获取购物车成功", null, shopCarList);
        } else return ResultUtil.resultFail("获取购物车失败", null, null);
    }

    /**
     * 清空购物车
     */
    @GetMapping("/cleanShopCar")
    public Map<String, Object> cleanShopCar(Integer storeId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Boolean isSuccess = menuService.cleanShopCar(storeId, user.getUserId());
        if (isSuccess) {
            return ResultUtil.resultSuccess("清空购物车成功", null, isSuccess);
        } else return ResultUtil.resultFail("清空购物车失败", null, null);
    }


    /**
     * 推荐菜单列表
     */
    @GetMapping("/getRecommendList")
    public Map<String, Object> getRecommendList(Integer storeId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        //先获取用户收藏列表
        List<Menu> collectList = menuService.getCollectList(user.getUserId());
        //获取所有菜品列表
        List<Menu> allMenuList = menuService.getMenuList(storeId, null);
        List<Menu> recommendList = new ArrayList<>();
        //如果收藏列表不为空
        if (!collectList.isEmpty() && collectList.size() > 0) {
            for (Menu collectMenu : collectList) {
                //匹配字符串 获取菜品名字中带有相同字符的菜品
                String collectMenuName = collectMenu.getMenuName();
                char[] selectParams = collectMenuName.toCharArray();
                for (int i = 0; i < selectParams.length; i++) {
                    for (Menu menu : allMenuList) {
                        boolean contains = menu.getMenuName().contains(String.valueOf(selectParams[i]));
                        //如果存在菜品名字模糊相同且是这个商家的菜品，则添加
                        if (contains) {
                            menu.setRecommendByMenu("根据你收藏的" + collectMenuName + "推荐");
                            recommendList.add(menu);
                        }
                    }
                }
                //匹配相似类型
                collectMenu.setMenuTypeDes(menuTypeMapper.selectByPrimaryKey(collectMenu.getMenuType()).getMenuTypeName());
                String collectMenuTypeDes = collectMenu.getMenuTypeDes();
                List<MenuType> menuTypeList = menuService.getMenuTypeList(storeId);
                char[] menuTypeLists = collectMenuTypeDes.toCharArray();
                for (int i = 0; i < menuTypeLists.length; i++) {
                    for (MenuType menuType : menuTypeList) {
                        //判断类型名称是否相似
                        boolean contains = menuType.getMenuTypeName().contains(String.valueOf(menuTypeLists[i]));
                        if (contains) {
                            //类型相似
                            Integer menuTypeId = menuType.getMenuTypeId();
                            List<Menu> menuList = menuService.getMenuList(storeId, menuTypeId);
                            for (int j = 0; j < menuList.size(); j++) {
                                //每个类型添加三个菜品
                                recommendList.add(menuList.get(j));
                                menuList.get(j).setRecommendByMenu("根据你收藏的" + collectMenuName + "推荐");
                                if (j == 3) break;
                            }
                        }
                    }
                }
            }
        }
        recommendList = recommendList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Menu::getMenuId))), ArrayList::new));

        if (recommendList.size() < 9) {
            //从每个类型中获取销量最高的几个菜品
            List<MenuType> menuTypeList = menuService.getMenuTypeList(storeId);
            for (MenuType menuType : menuTypeList) {
                List<Menu> menuBySale = menuService.getMenuBySale(storeId, menuType.getMenuTypeId());
                for (Menu menu : menuBySale) {
                    menu.setRecommendByMenu("大家都爱吃");
                }
                recommendList.addAll(menuBySale);
            }
        }


        recommendList = recommendList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Menu::getMenuId))), ArrayList::new));
//        //去重
//        List<Menu> list = new ArrayList<>();
//        for (Menu menu : recommendList) {
//            if (!list.contains(menu)) {
//                list.add(menu);
//            }
//        }
        for (Menu menu : recommendList) {
            menu.setPriceAfterDiscount(menu.getMenuPrice().multiply(menu.getMenuDiscount()).
                    divide(new BigDecimal(100), 2, RoundingMode.HALF_DOWN));
            menu.setMenuTypeDes(menuTypeMapper.selectByPrimaryKey(menu.getMenuType()).getMenuTypeName());

        }
        //打乱顺序
        shuffle1(recommendList);
        //最多只返回9条
        if (recommendList.size() > 10) {
            recommendList = recommendList.subList(0, 9);
        }

        List<ShoppingCart> shoppingCarts = menuService.getShoppingCarts(user.getUserId());
        for (Menu menu : recommendList) {
            if (!shoppingCarts.isEmpty() && shoppingCarts.size() > 0) {
                for (ShoppingCart shoppingCart : shoppingCarts) {
                    if (menu.getMenuId() == shoppingCart.getMenuId()) {
                        menu.setMenuNum(shoppingCart.getMenuNum());
                    }
                }
            }
        }

        if (recommendList != null) {
            return ResultUtil.resultSuccess("获取推荐菜单列表成功", null, recommendList);
        } else return ResultUtil.resultFail("获取推荐菜单列表失败", null, null);
    }

    /**
     * 新增编辑菜品类型
     *
     * @param object
     * @return
     */
    @PostMapping("editMenuType")
    public Map<String, Object> editMenuType(@RequestBody JSONObject object) {
        JSONArray array = object.getJSONArray("array");
        List<MenuType> menuTypes = JSONArray.parseArray(array.toString(), MenuType.class);
        Integer storeId = object.getInteger("storeId");
        for (MenuType menuType : menuTypes) {
            if (Objects.isNull(menuType.getMenuTypeId()) || (Objects.isNull(menuType.getMenuTypeName())
                    && menuType.getMenuTypeName() != "") || Objects.isNull(menuType.getMenuTypeWeight())) {
                menuType.setStoreId(storeId);
                menuTypeMapper.insertSelective(menuType);
            }
            menuTypeMapper.updateByPrimaryKeySelective(menuType);
        }
        return ResultUtil.resultSuccess("编辑成功", null, true);
    }

    /**
     * 新增编辑菜品类型
     *
     * @param menuTypeId
     * @return
     */
    @GetMapping("delMenuType")
    public Map<String, Object> delMenuType(Integer menuTypeId) {
        menuTypeMapper.deleteByPrimaryKey(menuTypeId);
        return ResultUtil.resultSuccess("编辑成功", null, true);
    }

    /**
     * 列表打乱顺序
     *
     * @param list
     * @param <T>
     */
    public <T> void shuffle1(List<T> list) {
        int size = list.size();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            // 获取随机位置
            int randomPos = random.nextInt(size);

            // 当前元素与随机元素交换
            T temp = list.get(i);
            list.set(i, list.get(randomPos));
            list.set(randomPos, temp);
        }
    }
}
