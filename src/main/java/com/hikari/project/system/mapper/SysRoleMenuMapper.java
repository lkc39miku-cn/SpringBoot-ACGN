package com.hikari.project.system.mapper;

import com.hikari.project.system.entity.SysRoleMenu;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * SysRoleMenuMapper
 * @author lkc39miku_cn
 */  
@Mapper
public interface SysRoleMenuMapper {
    int insert(SysRoleMenu record);

    int batchInsert(@Param("list") List<SysRoleMenu> list);
}