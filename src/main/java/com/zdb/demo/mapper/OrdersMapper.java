package com.zdb.demo.mapper;

import org.apache.ibatis.annotations.Param;
import com.zdb.demo.entity.Orders;
import com.zdb.demo.entity.OrdersExample;

import java.util.List;

public interface OrdersMapper {
    /**
     *  根据指定的条件获取数据库记录数,orders
     *
     * @param example
     */
    int countByExample(OrdersExample example);

    /**
     *  根据指定的条件删除数据库符合条件的记录,orders
     *
     * @param example
     */
    int deleteByExample(OrdersExample example);

    /**
     *  根据主键删除数据库的记录,orders
     *
     * @param orderId
     */
    int deleteByPrimaryKey(Integer orderId);

    /**
     *  新写入数据库记录,orders
     *
     * @param record
     */
    int insert(Orders record);

    /**
     *  动态字段,写入数据库记录,orders
     *
     * @param record
     */
    int insertSelective(Orders record);

    /**
     *  根据指定的条件查询符合条件的数据库记录,orders
     *
     * @param example
     */
    List<Orders> selectByExample(OrdersExample example);

    /**
     *  根据指定主键获取一条数据库记录,orders
     *
     * @param orderId
     */
    Orders selectByPrimaryKey(Integer orderId);

    /**
     *  动态根据指定的条件来更新符合条件的数据库记录,orders
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersExample example);

    /**
     *  根据指定的条件来更新符合条件的数据库记录,orders
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,orders
     *
     * @param record
     */
    int updateByPrimaryKeySelective(Orders record);

    /**
     *  根据主键来更新符合条件的数据库记录,orders
     *
     * @param record
     */
    int updateByPrimaryKey(Orders record);
}