package com.hikari.project.system.mapper;

import com.hikari.project.system.entity.SysStaffPost;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * SysStaffPostMapper
 * @author lkc39miku_cn
 */  
@Mapper
public interface SysStaffPostMapper {
    int insert(SysStaffPost record);

    int batchInsert(@Param("list") List<SysStaffPost> list);
}