package com.hikari.system.system.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.hikari.system.system.mapper.SysRoleDeptMapper;
import java.util.List;
import com.hikari.system.system.entity.SysRoleDept;
import com.hikari.system.system.service.SysRoleDeptService;

/**
 * SysRoleDeptServiceImpl
 * @author lkc39miku_cn
 */  
@Service
public class SysRoleDeptServiceImpl implements SysRoleDeptService{

    @Resource
    private SysRoleDeptMapper sysRoleDeptMapper;

    @Override
    public int insert(SysRoleDept record) {
        return sysRoleDeptMapper.insert(record);
    }

    @Override
    public int batchInsert(List<SysRoleDept> list) {
        return sysRoleDeptMapper.batchInsert(list);
    }

}
