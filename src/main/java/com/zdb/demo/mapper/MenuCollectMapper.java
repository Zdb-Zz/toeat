package com.zdb.demo.mapper;

import org.apache.ibatis.annotations.Param;
import com.zdb.demo.entity.MenuCollect;
import com.zdb.demo.entity.MenuCollectExample;

import java.util.List;

public interface MenuCollectMapper {
    /**
     *  根据指定的条件获取数据库记录数,menu_collect
     *
     * @param example
     */
    int countByExample(MenuCollectExample example);

    /**
     *  根据指定的条件删除数据库符合条件的记录,menu_collect
     *
     * @param example
     */
    int deleteByExample(MenuCollectExample example);

    /**
     *  根据主键删除数据库的记录,menu_collect
     *
     * @param menuCollectId
     */
    int deleteByPrimaryKey(Integer menuCollectId);

    /**
     *  新写入数据库记录,menu_collect
     *
     * @param record
     */
    int insert(MenuCollect record);

    /**
     *  动态字段,写入数据库记录,menu_collect
     *
     * @param record
     */
    int insertSelective(MenuCollect record);

    /**
     *  根据指定的条件查询符合条件的数据库记录,menu_collect
     *
     * @param example
     */
    List<MenuCollect> selectByExample(MenuCollectExample example);

    /**
     *  根据指定主键获取一条数据库记录,menu_collect
     *
     * @param menuCollectId
     */
    MenuCollect selectByPrimaryKey(Integer menuCollectId);

    /**
     *  动态根据指定的条件来更新符合条件的数据库记录,menu_collect
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") MenuCollect record, @Param("example") MenuCollectExample example);

    /**
     *  根据指定的条件来更新符合条件的数据库记录,menu_collect
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") MenuCollect record, @Param("example") MenuCollectExample example);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,menu_collect
     *
     * @param record
     */
    int updateByPrimaryKeySelective(MenuCollect record);

    /**
     *  根据主键来更新符合条件的数据库记录,menu_collect
     *
     * @param record
     */
    int updateByPrimaryKey(MenuCollect record);
}