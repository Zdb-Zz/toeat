package com.zdb.demo.mapper;

import org.apache.ibatis.annotations.Param;
import com.zdb.demo.entity.OrderMenu;
import com.zdb.demo.entity.OrderMenuExample;

import java.util.List;

public interface OrderMenuMapper {
    /**
     *  根据指定的条件获取数据库记录数,order_menu
     *
     * @param example
     */
    int countByExample(OrderMenuExample example);

    /**
     *  根据指定的条件删除数据库符合条件的记录,order_menu
     *
     * @param example
     */
    int deleteByExample(OrderMenuExample example);

    /**
     *  根据主键删除数据库的记录,order_menu
     *
     * @param orderMenuId
     */
    int deleteByPrimaryKey(Integer orderMenuId);

    /**
     *  新写入数据库记录,order_menu
     *
     * @param record
     */
    int insert(OrderMenu record);

    /**
     *  动态字段,写入数据库记录,order_menu
     *
     * @param record
     */
    int insertSelective(OrderMenu record);

    /**
     *  根据指定的条件查询符合条件的数据库记录,order_menu
     *
     * @param example
     */
    List<OrderMenu> selectByExample(OrderMenuExample example);

    /**
     *  根据指定主键获取一条数据库记录,order_menu
     *
     * @param orderMenuId
     */
    OrderMenu selectByPrimaryKey(Integer orderMenuId);

    /**
     *  动态根据指定的条件来更新符合条件的数据库记录,order_menu
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") OrderMenu record, @Param("example") OrderMenuExample example);

    /**
     *  根据指定的条件来更新符合条件的数据库记录,order_menu
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") OrderMenu record, @Param("example") OrderMenuExample example);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,order_menu
     *
     * @param record
     */
    int updateByPrimaryKeySelective(OrderMenu record);

    /**
     *  根据主键来更新符合条件的数据库记录,order_menu
     *
     * @param record
     */
    int updateByPrimaryKey(OrderMenu record);
}