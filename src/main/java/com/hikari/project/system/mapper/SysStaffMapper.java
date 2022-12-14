package com.hikari.project.system.mapper;

import com.hikari.project.system.entity.SysStaff;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * SysStaffMapper
 * @author lkc39miku_cn
 */  
@Mapper
public interface SysStaffMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysStaff record);

    SysStaff selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysStaff record);

    int updateBatchSelective(List<SysStaff> list);

    int batchInsert(@Param("list") List<SysStaff> list);

    List<SysStaff> selectList(SysStaff sysStaff);

    List<SysStaff> selectNotList(SysStaff sysStaff);

    List<SysStaff> selectAssigned(SysStaff sysStaff);

    List<SysStaff> selectUnAssigned(SysStaff sysStaff);

    int insertAuthStaffAll(@Param(value = "roleId") String roleId, @Param(value = "staffIdList") List<String> staffIdList);
}