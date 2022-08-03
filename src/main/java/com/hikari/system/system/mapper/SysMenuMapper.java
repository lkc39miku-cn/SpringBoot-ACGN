package com.hikari.system.system.mapper;

import com.hikari.system.system.entity.SysMenu;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * SysMenuMapper
 * @author lkc39miku_cn
 */  
@Mapper
public interface SysMenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysMenu record);

    SysMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateBatchSelective(List<SysMenu> list);

    int batchInsert(@Param("list") List<SysMenu> list);

    List<SysMenu> selectList(SysMenu sysMenu);

    int checkRoleMenu(String id);

    List<SysMenu> selectNotList(SysMenu sysMenu);

    List<String> roleTree(@Param(value = "id") String id, @Param(value = "menuCheckStrictly") boolean menuCheckStrictly);
}