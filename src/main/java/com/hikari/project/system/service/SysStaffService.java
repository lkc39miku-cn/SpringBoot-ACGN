package com.hikari.project.system.service;

import java.util.List;
import com.hikari.project.system.entity.SysStaff;
import com.hikari.project.system.entity.vo.SysStaffVo;

/**
 * SysStaffService
 * @author lkc39miku_cn
 */  
public interface SysStaffService{


    int deleteByPrimaryKey(String id);

    int insert(SysStaff record);

    SysStaffVo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysStaff record);

    int updateBatchSelective(List<SysStaff> list);

    int batchInsert(List<SysStaff> list);

    List<SysStaff> selectList(SysStaff sysStaff);

    boolean checkStaffUsername(String username);

    boolean checkStaffPhone(String phone);

    boolean checkStaffEmail(String email);

    boolean checkStaffUsername(String username, String id);

    boolean checkStaffPhone(String phone, String id);

    boolean checkStaffEmail(String email, String id);

    List<SysStaff> selectNotList(SysStaff sysStaff);

    SysStaffVo getRole(String id);

    int updateRole(String id, List<String> roleIds);

    boolean registerStaff(SysStaff staff);

    void checkStaffAllowed(SysStaff sysStaff);

    SysStaff selectStaffByUsername(String username);
}
