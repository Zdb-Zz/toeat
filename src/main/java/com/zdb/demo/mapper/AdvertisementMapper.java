package com.zdb.demo.mapper;

import org.apache.ibatis.annotations.Param;
import com.zdb.demo.entity.Advertisement;
import com.zdb.demo.entity.AdvertisementExample;

import java.util.List;

public interface AdvertisementMapper {
    /**
     *  根据指定的条件获取数据库记录数,advertisement
     *
     * @param example
     */
    int countByExample(AdvertisementExample example);

    /**
     *  根据指定的条件删除数据库符合条件的记录,advertisement
     *
     * @param example
     */
    int deleteByExample(AdvertisementExample example);

    /**
     *  根据主键删除数据库的记录,advertisement
     *
     * @param advertisementId
     */
    int deleteByPrimaryKey(Integer advertisementId);

    /**
     *  新写入数据库记录,advertisement
     *
     * @param record
     */
    int insert(Advertisement record);

    /**
     *  动态字段,写入数据库记录,advertisement
     *
     * @param record
     */
    int insertSelective(Advertisement record);

    /**
     *  根据指定的条件查询符合条件的数据库记录,advertisement
     *
     * @param example
     */
    List<Advertisement> selectByExample(AdvertisementExample example);

    /**
     *  根据指定主键获取一条数据库记录,advertisement
     *
     * @param advertisementId
     */
    Advertisement selectByPrimaryKey(Integer advertisementId);

    /**
     *  动态根据指定的条件来更新符合条件的数据库记录,advertisement
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") Advertisement record, @Param("example") AdvertisementExample example);

    /**
     *  根据指定的条件来更新符合条件的数据库记录,advertisement
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") Advertisement record, @Param("example") AdvertisementExample example);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,advertisement
     *
     * @param record
     */
    int updateByPrimaryKeySelective(Advertisement record);

    /**
     *  根据主键来更新符合条件的数据库记录,advertisement
     *
     * @param record
     */
    int updateByPrimaryKey(Advertisement record);
}