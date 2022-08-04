package com.hikari.project.system.service;

import com.hikari.project.system.entity.SysDept;

import java.util.List;
    
/**
 * SysDeptService
 * @author lkc39miku_cn
 */  
public interface SysDeptService{

    /**
     * deleteByPrimaryKey
     * @param id 主键
     * @return int
     */
    int deleteByPrimaryKey(String id);

    /**
     * insert
     * @param record 实体
     * @return int
     */
    int insert(SysDept record);

    /**
     * selectByPrimaryKey
     * @param id 主键
     * @return SysDept
     */
    SysDept selectByPrimaryKey(String id);

    /**
     * updateByPrimaryKeySelective
     * @param record 实体
     * @return int
     */
    int updateByPrimaryKeySelective(SysDept record);

    /**
     * updateBatchSelective
     * @param list 实体集合
     * @return int
     */
    int updateBatchSelective(List<SysDept> list);

    /**
     * batchInsert
     * @param list 实体集合
     * @return int
     */
    int batchInsert(List<SysDept> list);

    /**
     * 查询数据列表
     * @param sysDept 实体
     * @return List<SysDept>
     */
    List<SysDept> selectList(SysDept sysDept);

    /**
     * 检查部门名称唯一性
     * @param name 名称
     * @return boolean
     */
    boolean checkDeptNameUnique(String name);

    /**
     * 检查部门名称唯一性
     * @param name 名称
     * @param id 主键
     * @return boolean
     */
    boolean checkDeptNameUnique(String name, String id);

    /**
     * 查询不包含数据列表
     * @param sysDept 实体
     * @return List<SysDept>
     */
    List<SysDept> selectNotList(SysDept sysDept);

    /**
     * 检查是否有子部门
     * @param id 主键
     * @return boolean
     */
    boolean hasChildByParentId(String id);

    /**
     * 检查是否有员工
     * @param id 主键
     * @return boolean
     */
    boolean checkExistStaffByDeptId(String id);

    /**
     * 查询在线子部门
     * @param id 主键
     * @return List<SysDept>
     */
    boolean selectOnlineChildrenByParentId(String id);

    List<SysDept> tree(List<SysDept> selectList);

    List<SysDept> roleTree(String roleId);
}
