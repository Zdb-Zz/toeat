package com.zdb.demo.mapper;

import org.apache.ibatis.annotations.Param;
import com.zdb.demo.entity.ShoppingCart;
import com.zdb.demo.entity.ShoppingCartExample;

import java.util.List;

public interface ShoppingCartMapper {
    /**
     *  根据指定的条件获取数据库记录数,shopping_cart
     *
     * @param example
     */
    int countByExample(ShoppingCartExample example);

    /**
     *  根据指定的条件删除数据库符合条件的记录,shopping_cart
     *
     * @param example
     */
    int deleteByExample(ShoppingCartExample example);

    /**
     *  根据主键删除数据库的记录,shopping_cart
     *
     * @param shoppingCartId
     */
    int deleteByPrimaryKey(Integer shoppingCartId);

    /**
     *  新写入数据库记录,shopping_cart
     *
     * @param record
     */
    int insert(ShoppingCart record);

    /**
     *  动态字段,写入数据库记录,shopping_cart
     *
     * @param record
     */
    int insertSelective(ShoppingCart record);

    /**
     *  根据指定的条件查询符合条件的数据库记录,shopping_cart
     *
     * @param example
     */
    List<ShoppingCart> selectByExample(ShoppingCartExample example);

    /**
     *  根据指定主键获取一条数据库记录,shopping_cart
     *
     * @param shoppingCartId
     */
    ShoppingCart selectByPrimaryKey(Integer shoppingCartId);

    /**
     *  动态根据指定的条件来更新符合条件的数据库记录,shopping_cart
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") ShoppingCart record, @Param("example") ShoppingCartExample example);

    /**
     *  根据指定的条件来更新符合条件的数据库记录,shopping_cart
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") ShoppingCart record, @Param("example") ShoppingCartExample example);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,shopping_cart
     *
     * @param record
     */
    int updateByPrimaryKeySelective(ShoppingCart record);

    /**
     *  根据主键来更新符合条件的数据库记录,shopping_cart
     *
     * @param record
     */
    int updateByPrimaryKey(ShoppingCart record);

    List<ShoppingCart> cleanShopCar(@Param("storeId")Integer storeId,@Param("userId")Integer userId);
}