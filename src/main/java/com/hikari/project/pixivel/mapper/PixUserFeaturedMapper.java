package com.hikari.project.pixivel.mapper;

import com.hikari.project.pixivel.entity.PixUserFeatured;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * PixUserFeaturedMapper
 * @author lkc39miku_cn
 */  
@Mapper
public interface PixUserFeaturedMapper {
    int deleteByPrimaryKey(String id);

    int insert(PixUserFeatured record);

    PixUserFeatured selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PixUserFeatured record);

    int updateBatchSelective(List<PixUserFeatured> list);

    int batchInsert(@Param("list") List<PixUserFeatured> list);
}