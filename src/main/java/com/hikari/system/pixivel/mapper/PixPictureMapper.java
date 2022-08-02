package com.hikari.system.pixivel.mapper;

import com.hikari.system.pixivel.entity.PixPicture;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * PixPictureMapper
 *
 * @author lkc39miku_cn
 */
@Mapper
public interface PixPictureMapper {
    int deleteByPrimaryKey(String id);

    int insert(PixPicture record);

    PixPicture selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PixPicture record);

    int updateBatchSelective(List<PixPicture> list);

    int batchInsert(@Param("list") List<PixPicture> list);

    List<PixPicture> selectByCollectionId(String id);
}