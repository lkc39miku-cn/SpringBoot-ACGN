package com.hikari.project.pixivel.mapper;

import com.hikari.project.pixivel.entity.PixUserPraise;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * PixUserPraiseMapper
 * @author lkc39miku_cn
 */  
@Mapper
public interface PixUserPraiseMapper {
    int deleteByPrimaryKey(String id);

    int insert(PixUserPraise record);

    PixUserPraise selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PixUserPraise record);

    int updateBatchSelective(List<PixUserPraise> list);

    int batchInsert(@Param("list") List<PixUserPraise> list);

    List<PixUserPraise> selectList(String id);
}