package com.hikari.project.system.mapper;

import com.hikari.project.system.entity.SysDept;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * SysDeptMapper
 * @author lkc39miku_cn
 */  
@Mapper
public interface SysDeptMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysDept record);

    SysDept selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateBatchSelective(List<SysDept> list);

    int batchInsert(@Param("list") List<SysDept> list);

    List<SysDept> selectList(SysDept sysDept);

    List<SysDept> selectNotList(SysDept sysDept);

    int checkExistStaffByDeptId(String id);

    List<String> roleTree(@Param(value = "id") String id, @Param(value = "deptCheckStrictly") boolean deptCheckStrictly);
}