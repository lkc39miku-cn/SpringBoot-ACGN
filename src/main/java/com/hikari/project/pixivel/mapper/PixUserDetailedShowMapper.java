package com.hikari.project.pixivel.mapper;

import com.hikari.project.pixivel.entity.PixUserDetailedShow;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * PixUserDetailedShowMapper
 *
 * @author lkc39miku_cn
 */
@Mapper
public interface PixUserDetailedShowMapper {
    int deleteByPrimaryKey(String id);

    int insert(PixUserDetailedShow record);

    PixUserDetailedShow selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PixUserDetailedShow record);

    int updateBatchSelective(List<PixUserDetailedShow> list);

    int batchInsert(@Param("list") List<PixUserDetailedShow> list);

    PixUserDetailedShow selectByAuthorId(String authorId);
}