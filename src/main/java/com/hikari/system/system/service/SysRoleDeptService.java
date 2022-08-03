package com.hikari.system.system.service;

import java.util.List;
import com.hikari.system.system.entity.SysRoleDept;
    
/**
 * SysRoleDeptService
 * @author lkc39miku_cn
 */  
public interface SysRoleDeptService{


    int insert(SysRoleDept record);

    int batchInsert(List<SysRoleDept> list);

}
