package com.zdb.demo.mapper;

import org.apache.ibatis.annotations.Param;
import com.zdb.demo.entity.Menu;
import com.zdb.demo.entity.MenuExample;

import java.util.List;

public interface MenuMapper {
    /**
     *  根据指定的条件获取数据库记录数,menu
     *
     * @param example
     */
    int countByExample(MenuExample example);

    /**
     *  根据指定的条件删除数据库符合条件的记录,menu
     *
     * @param example
     */
    int deleteByExample(MenuExample example);

    /**
     *  根据主键删除数据库的记录,menu
     *
     * @param menuId
     */
    int deleteByPrimaryKey(Integer menuId);

    /**
     *  新写入数据库记录,menu
     *
     * @param record
     */
    int insert(Menu record);

    /**
     *  动态字段,写入数据库记录,menu
     *
     * @param record
     */
    int insertSelective(Menu record);

    /**
     *  根据指定的条件查询符合条件的数据库记录,menu
     *
     * @param example
     */
    List<Menu> selectByExample(MenuExample example);

    /**
     *  根据指定主键获取一条数据库记录,menu
     *
     * @param menuId
     */
    Menu selectByPrimaryKey(Integer menuId);

    /**
     *  动态根据指定的条件来更新符合条件的数据库记录,menu
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuExample example);

    /**
     *  根据指定的条件来更新符合条件的数据库记录,menu
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,menu
     *
     * @param record
     */
    int updateByPrimaryKeySelective(Menu record);

    /**
     *  根据主键来更新符合条件的数据库记录,menu
     *
     * @param record
     */
    int updateByPrimaryKey(Menu record);

    List<Menu> findShopCarList(@Param("userId")Integer userId);

    List<Menu> getMenuList(@Param("storeId")Integer storeId,@Param("menuType")Integer menuType,@Param("userId")Integer userId,@Param("menuName")String menuName);
}