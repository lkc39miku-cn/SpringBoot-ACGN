package com.hikari.project.pixivel.mapper;

import com.hikari.project.pixivel.entity.PixUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * PixUserMapper
 * @author lkc39miku_cn
 */  
@Mapper
public interface PixUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(PixUser record);

    PixUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PixUser record);

    int updateBatchSelective(List<PixUser> list);

    int batchInsert(@Param("list") List<PixUser> list);

    List<PixUser> selectAll(PixUser pixUser);

    List<PixUser> selectPraise();
}