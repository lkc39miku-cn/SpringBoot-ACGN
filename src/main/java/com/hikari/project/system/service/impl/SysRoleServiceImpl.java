package com.hikari.project.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hikari.commons.entity.Page;
import com.hikari.commons.key.NumberKey;
import com.hikari.commons.key.SysRoleKey;
import com.hikari.commons.util.SecurityUtils;
import com.hikari.framework.exception.service.ServiceException;
import com.hikari.project.system.entity.SysStaff;
import com.hikari.project.system.entity.SysStaffRole;
import com.hikari.project.system.mapper.SysRoleDeptMapper;
import com.hikari.project.system.mapper.SysStaffMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
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

    @Resource
    private SysRoleDeptMapper sysRoleDeptMapper;

    @Resource
    private SysStaffMapper sysStaffMapper;

    @Override
    public int insert(SysRole record) {
        record.setCreateStaffId(SecurityUtils.getStaffId());
        record.setCreateTime(LocalDateTime.now());
        return sysRoleMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKeySelective(SysRole record) {
        record.setUpdateTime(LocalDateTime.now());
        record.setUpdateStaffId(SecurityUtils.getStaffId());
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

        return sysRoleMapper.updateByPrimaryKeySelective(new SysRole()
                .setId(id)
                .setDeleteStatus(SysRoleKey.DELETE_YES)
                .setUpdateTime(LocalDateTime.now())
                .setUpdateStaffId(SecurityUtils.getStaffId()));
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

    @Override
    public List<SysRole> selectList(SysRole sysRole) {
        return sysRoleMapper.selectList(sysRole);
    }

    @Override
    public boolean checkName(String name) {
        return selectList(new SysRole().setName(name)).size() > NumberKey.ZERO;
    }

    @Override
    public boolean checkRoleKey(String roleKey) {
        return selectList(new SysRole().setRoleKey(roleKey)).size() > NumberKey.ZERO;
    }

    @Override
    public boolean checkName(String name, String id) {
        return selectNotList(new SysRole().setName(name).setId(id)).size() > NumberKey.ZERO;
    }

    @Override
    public boolean checkRoleKey(String roleKey, String id) {
        return selectNotList(new SysRole().setRoleKey(roleKey).setId(id)).size() > NumberKey.ZERO;
    }

    @Override
    public List<SysRole> selectNotList(SysRole sysRole) {
        return sysRoleMapper.selectNotList(sysRole);
    }

    @Override
    public void checkRoleAllowed(SysRole sysRole) {
        if (StringUtils.isNotEmpty(sysRole.getId()) && sysRole.isAdmin()) {
            throw new ServiceException("", "不允许操作超级管理员角色");
        }
    }

    @Override
    public int authDataScope(SysRole sysRole) {
        sysRoleDeptMapper.deleteByRoleId(sysRole.getId());
        return sysRoleDeptMapper.batchInsertByRoleId(sysRole.getSysDeptIdList(), sysRole.getId());
    }

    @Override
    public int updateStatus(SysRole sysRole) {
        return updateByPrimaryKeySelective(new SysRole()
                .setId(sysRole.getId())
                .setUpdateStaffId(SecurityUtils.getStaffId())
                .setUpdateTime(LocalDateTime.now())
                .setStatus(sysRole.getStatus()));
    }

    @Override
    public List<SysStaff> selectAssigned(SysStaff sysStaff) {
        return sysStaffMapper.selectAssigned(sysStaff);
    }

    @Override
    public int cancelAssign(SysStaffRole sysStaffRole) {
        return sysRoleMapper.deleteBySelect(sysStaffRole);
    }

    @Override
    public int cancelAssignBatch(String roleId, List<String> staffIdList) {
        return sysRoleMapper.deleteBySelectBatch(roleId, staffIdList);
    }

    @Override
    public int assignBatch(String roleId, List<String> staffIdList) {
        return sysStaffMapper.insertAuthStaffAll(roleId, staffIdList);
    }

    @Override
    public List<SysStaff> selectUnAssigned(SysStaff sysStaff) {
        return sysStaffMapper.selectUnAssigned(sysStaff);
    }
}

