package com.hikari.project.system.mapper;

import com.hikari.project.system.entity.SysPost;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * SysPostMapper
 * @author lkc39miku_cn
 */  
@Mapper
public interface SysPostMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysPost record);

    SysPost selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysPost record);

    int updateBatchSelective(List<SysPost> list);

    int batchInsert(@Param("list") List<SysPost> list);

    List<SysPost> selectList(SysPost sysPost);

    int checkStaffPost(String id);

    List<SysPost> selectNotList(SysPost sysPost);

    List<SysPost> selectListByStaffId(String id);
}