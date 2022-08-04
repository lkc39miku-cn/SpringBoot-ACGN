package com.hikari.project.system.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;

import com.hikari.project.system.mapper.SysRoleMapper;
import com.hikari.project.system.entity.SysRole;
import com.hikari.project.system.service.SysRoleService;

/**
 * SysRoleServiceImpl
 *
 * @author lkc39miku_cn
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public int insert(SysRole record) {
        return sysRoleMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKeySelective(SysRole record) {
        return sysRoleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<SysRole> list) {
        return sysRoleMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<SysRole> list) {
        return sysRoleMapper.batchInsert(list);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return sysRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SysRole selectByPrimaryKey(String id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public Set<String> selectRolePermissionByStaffId(String id) {
        List<SysRole> roleList = sysRoleMapper.selectRolePermissionByStaffId(id);
        Set<String> set = new HashSet<>(10);
        roleList.stream().filter(Objects::nonNull).forEach(v -> set.addAll(Arrays.asList(v.getRoleKey().trim().split(","))));
        return set;
    }
}

