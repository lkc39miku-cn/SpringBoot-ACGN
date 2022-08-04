package com.hikari.project.system.mapper;

import com.hikari.project.system.entity.SysRole;
import java.util.List;

import com.hikari.project.system.entity.SysStaffRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * SysRoleMapper
 *
 * @author lkc39miku_cn
 */
@Mapper
public interface SysRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysRole record);

    SysRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateBatchSelective(List<SysRole> list);

    int batchInsert(@Param("list") List<SysRole> list);

    List<SysRole> selectListByStaffId(String id);

    List<SysRole> selectRolePermissionByStaffId(String id);

    List<SysRole> selectList(SysRole sysRole);

    List<SysRole> selectNotList(SysRole sysRole);

    int deleteBySelect(SysStaffRole sysStaffRole);

    int deleteBySelectBatch(@Param(value = "roleId") String roleId, @Param(value = "staffIdList") List<String> staffIdList);
}