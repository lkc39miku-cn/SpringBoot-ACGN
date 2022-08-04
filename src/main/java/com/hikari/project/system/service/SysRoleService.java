package com.hikari.project.system.service;

import java.util.List;
import java.util.Set;

import com.hikari.project.system.entity.SysRole;
import com.hikari.project.system.entity.SysStaff;
import com.hikari.project.system.entity.SysStaffRole;

/**
 * SysRoleService
 *
 * @author lkc39miku_cn
 */
public interface SysRoleService {


    int insert(SysRole record);

    int updateByPrimaryKeySelective(SysRole record);

    int updateBatchSelective(List<SysRole> list);

    int batchInsert(List<SysRole> list);

    int deleteByPrimaryKey(String id);

    SysRole selectByPrimaryKey(String id);

    Set<String> selectRolePermissionByStaffId(String id);

    List<SysRole> selectList(SysRole sysRole);

    boolean checkName(String name);

    boolean checkRoleKey(String roleKey);

    boolean checkName(String name, String id);

    boolean checkRoleKey(String roleKey, String id);

    List<SysRole> selectNotList(SysRole sysRole);

    void checkRoleAllowed(SysRole sysRole);

    int authDataScope(SysRole sysRole);

    int updateStatus(SysRole sysRole);

    List<SysStaff> selectAssigned(SysStaff sysStaff);

    int cancelAssign(SysStaffRole sysStaffRole);

    int cancelAssignBatch(String roleId, List<String> staffIdList);

    int assignBatch(String roleId, List<String> staffIdList);

    List<SysStaff> selectUnAssigned(SysStaff sysStaff);
}

