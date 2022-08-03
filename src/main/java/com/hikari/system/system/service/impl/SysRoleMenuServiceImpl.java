package com.hikari.system.system.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.hikari.system.system.entity.SysRoleMenu;
import com.hikari.system.system.mapper.SysRoleMenuMapper;
import com.hikari.system.system.service.SysRoleMenuService;

/**
 * SysRoleMenuServiceImpl
 * @author lkc39miku_cn
 */  
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService{

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public int insert(SysRoleMenu record) {
        return sysRoleMenuMapper.insert(record);
    }

    @Override
    public int batchInsert(List<SysRoleMenu> list) {
        return sysRoleMenuMapper.batchInsert(list);
    }

}
