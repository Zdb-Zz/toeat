package com.zdb.demo.mapper;

import com.zdb.demo.entity.MenuType;
import com.zdb.demo.entity.MenuTypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuTypeMapper {
    /**
     *  根据指定的条件获取数据库记录数,menu_type
     *
     * @param example
     */
    int countByExample(MenuTypeExample example);

    /**
     *  根据指定的条件删除数据库符合条件的记录,menu_type
     *
     * @param example
     */
    int deleteByExample(MenuTypeExample example);

    /**
     *  根据主键删除数据库的记录,menu_type
     *
     * @param menuTypeId
     */
    int deleteByPrimaryKey(Integer menuTypeId);

    /**
     *  新写入数据库记录,menu_type
     *
     * @param record
     */
    int insert(MenuType record);

    /**
     *  动态字段,写入数据库记录,menu_type
     *
     * @param record
     */
    int insertSelective(MenuType record);

    /**
     *  根据指定的条件查询符合条件的数据库记录,menu_type
     *
     * @param example
     */
    List<MenuType> selectByExample(MenuTypeExample example);

    /**
     *  根据指定主键获取一条数据库记录,menu_type
     *
     * @param menuTypeId
     */
    MenuType selectByPrimaryKey(Integer menuTypeId);

    /**
     *  动态根据指定的条件来更新符合条件的数据库记录,menu_type
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") MenuType record, @Param("example") MenuTypeExample example);

    /**
     *  根据指定的条件来更新符合条件的数据库记录,menu_type
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") MenuType record, @Param("example") MenuTypeExample example);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,menu_type
     *
     * @param record
     */
    int updateByPrimaryKeySelective(MenuType record);

    /**
     *  根据主键来更新符合条件的数据库记录,menu_type
     *
     * @param record
     */
    int updateByPrimaryKey(MenuType record);


    List<MenuType> findMenuListByStoreId(@Param("storeId")Integer storeId);
}