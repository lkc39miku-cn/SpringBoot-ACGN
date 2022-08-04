package com.hikari.project.system.mapper;

import com.hikari.project.system.entity.SysStaffRole;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * SysStaffRoleMapper
 * @author lkc39miku_cn
 */  
@Mapper
public interface SysStaffRoleMapper {
    int insert(SysStaffRole record);

    int batchInsert(@Param("list") List<SysStaffRole> list);

    int deleteByStaffId(String id);

    int batchInsertByRoleIds(@Param(value = "id") String id, @Param(value = "idList") List<String> roleIds);
}