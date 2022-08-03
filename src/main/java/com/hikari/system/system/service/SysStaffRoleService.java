package com.hikari.system.system.service;

import java.util.List;
import com.hikari.system.system.entity.SysStaffRole;
    
/**
 * SysStaffRoleService
 * @author lkc39miku_cn
 */  
public interface SysStaffRoleService{


    int insert(SysStaffRole record);

    int batchInsert(List<SysStaffRole> list);

}
