package com.zdb.demo.mapper;

import org.apache.ibatis.annotations.Param;
import com.zdb.demo.entity.Store;
import com.zdb.demo.entity.StoreExample;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface StoreMapper {
    /**
     *  根据指定的条件获取数据库记录数,store
     *
     * @param example
     */
    int countByExample(StoreExample example);

    /**
     *  根据指定的条件删除数据库符合条件的记录,store
     *
     * @param example
     */
    int deleteByExample(StoreExample example);

    /**
     *  根据主键删除数据库的记录,store
     *
     * @param storeId
     */
    int deleteByPrimaryKey(Integer storeId);

    /**
     *  新写入数据库记录,store
     *
     * @param record
     */
    int insert(Store record);

    /**
     *  动态字段,写入数据库记录,store
     *
     * @param record
     */
    int insertSelective(Store record);

    /**
     *  根据指定的条件查询符合条件的数据库记录,store
     *
     * @param example
     */
    List<Store> selectByExample(StoreExample example);

    /**
     *  根据指定主键获取一条数据库记录,store
     *
     * @param storeId
     */
    Store selectByPrimaryKey(Integer storeId);

    /**
     *  动态根据指定的条件来更新符合条件的数据库记录,store
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") Store record, @Param("example") StoreExample example);

    /**
     *  根据指定的条件来更新符合条件的数据库记录,store
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") Store record, @Param("example") StoreExample example);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,store
     *
     * @param record
     */
    int updateByPrimaryKeySelective(Store record);

    /**
     *  根据主键来更新符合条件的数据库记录,store
     *
     * @param record
     */
    int updateByPrimaryKey(Store record);

    Map<String,Object> getOrderCount(@Param("map") Map<String,Object> map);

    List<Map<String,Object>> getYearBusiness(@Param("map") Map<String,Object> map);


    List<Map<String,Object>> getDayBusiness(@Param("map") Map<String,Object> map);

    BigDecimal getRateAvg(@Param("storeId") Integer storeId);
}