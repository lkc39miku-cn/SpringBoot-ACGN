package com.hikari.project.system.service;

import java.util.List;
import java.util.Set;

import com.hikari.project.system.entity.SysRole;

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
}
