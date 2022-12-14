package com.hikari.project.pixivel.mapper;

import com.hikari.project.pixivel.entity.PixPictureDetailed;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * PixPictureDetailedMapper
 *
 * @author lkc39miku_cn
 */
@Mapper
public interface PixPictureDetailedMapper {
    int deleteByPrimaryKey(String id);

    int insert(PixPictureDetailed record);

    PixPictureDetailed selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PixPictureDetailed record);

    int updateBatchSelective(List<PixPictureDetailed> list);

    int batchInsert(@Param("list") List<PixPictureDetailed> list);

    int deleteByIdList(List<String> list);
}