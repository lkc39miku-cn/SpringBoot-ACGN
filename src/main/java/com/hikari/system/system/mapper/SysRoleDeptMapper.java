package com.hikari.system.system.mapper;

import com.hikari.system.system.entity.SysRoleDept;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * SysRoleDeptMapper
 * @author lkc39miku_cn
 */  
@Mapper
public interface SysRoleDeptMapper {
    int insert(SysRoleDept record);

    int batchInsert(@Param("list") List<SysRoleDept> list);
}