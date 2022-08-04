package com.hikari.project.system.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.hikari.project.system.entity.SysStaffRole;
import com.hikari.project.system.mapper.SysStaffRoleMapper;
import com.hikari.project.system.service.SysStaffRoleService;

/**
 * SysStaffRoleServiceImpl
 * @author lkc39miku_cn
 */  
@Service
public class SysStaffRoleServiceImpl implements SysStaffRoleService{

    @Resource
    private SysStaffRoleMapper sysStaffRoleMapper;

    @Override
    public int insert(SysStaffRole record) {
        return sysStaffRoleMapper.insert(record);
    }

    @Override
    public int batchInsert(List<SysStaffRole> list) {
        return sysStaffRoleMapper.batchInsert(list);
    }

}
