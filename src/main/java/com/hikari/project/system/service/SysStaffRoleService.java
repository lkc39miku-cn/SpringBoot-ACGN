package com.hikari.project.system.service;

import java.util.List;
import com.hikari.project.system.entity.SysStaffRole;
    
/**
 * SysStaffRoleService
 * @author lkc39miku_cn
 */  
public interface SysStaffRoleService{


    int insert(SysStaffRole record);

    int batchInsert(List<SysStaffRole> list);

}
