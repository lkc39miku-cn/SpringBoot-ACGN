package com.hikari.project.system.service;

import java.util.List;
import com.hikari.project.system.entity.SysRoleMenu;
    
/**
 * SysRoleMenuService
 * @author lkc39miku_cn
 */  
public interface SysRoleMenuService{


    int insert(SysRoleMenu record);

    int batchInsert(List<SysRoleMenu> list);

}
