package com.hikari.system.system.service;

import java.util.List;
import com.hikari.system.system.entity.SysMenu;
    
/**
 * SysMenuService
 * @author lkc39miku_cn
 */  
public interface SysMenuService{


    int deleteByPrimaryKey(String id);

    int insert(SysMenu record);

    SysMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateBatchSelective(List<SysMenu> list);

    int batchInsert(List<SysMenu> list);

    List<SysMenu> selectList(SysMenu sysMenu);

    boolean checkMenuName(String name);

    boolean checkChildMenu(String id);

    boolean checkRoleMenu(String id);

    boolean checkMenuName(String name, String id);

    List<SysMenu> selectNotList(SysMenu sysMenu);

    List<SysMenu> tree(List<SysMenu> setStatus);

    List<SysMenu> roleTree(String roleId);
}
