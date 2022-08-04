package com.hikari.project.system.service;

import java.util.List;
import com.hikari.project.system.entity.SysRoleDept;
    
/**
 * SysRoleDeptService
 * @author lkc39miku_cn
 */  
public interface SysRoleDeptService{


    int insert(SysRoleDept record);

    int batchInsert(List<SysRoleDept> list);

}
