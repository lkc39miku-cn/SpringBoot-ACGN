package com.hikari.system.system.service.impl;

import com.hikari.commons.key.NumberKey;
import com.hikari.commons.key.SysDeptKey;
import com.hikari.system.system.entity.SysRole;
import com.hikari.system.system.mapper.SysRoleMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.hikari.system.system.entity.SysDept;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.hikari.system.system.mapper.SysDeptMapper;
import com.hikari.system.system.service.SysDeptService;

/**
 * SysDeptServiceImpl
 * @author lkc39miku_cn
 */  
@Service
public class SysDeptServiceImpl implements SysDeptService{

    @Resource
    private SysDeptMapper sysDeptMapper;

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return sysDeptMapper.updateByPrimaryKeySelective(new SysDept()
                .setId(id)
                .setDeleteStatus(SysDeptKey.DELETE_YES)
                .setUpdateTime(LocalDateTime.now()));
    }

    @Override
    public int insert(SysDept record) {
        record.setCreateTime(LocalDateTime.now());
        return sysDeptMapper.insert(record);
    }

    @Override
    public SysDept selectByPrimaryKey(String id) {
        return sysDeptMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysDept record) {
        record .setUpdateTime(LocalDateTime.now());
        return sysDeptMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<SysDept> list) {
        return sysDeptMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<SysDept> list) {
        return sysDeptMapper.batchInsert(list);
    }

    @Override
    public List<SysDept> selectList(SysDept sysDept) {
        return sysDeptMapper.selectList(sysDept);
    }

    @Override
    public boolean checkDeptNameUnique(String name) {
        return selectList(new SysDept().setName(name)).size() > NumberKey.ZERO;
    }

    @Override
    public boolean checkDeptNameUnique(String name, String id) {
        return selectNotList(new SysDept().setName(name).setId(id)).size() > NumberKey.ZERO;
    }

    @Override
    public List<SysDept> selectNotList(SysDept sysDept) {
        return sysDeptMapper.selectNotList(sysDept);
    }

    @Override
    public boolean hasChildByParentId(String id) {
        return selectList(new SysDept().setParentId(id)).size() > NumberKey.ZERO;
    }

    @Override
    public boolean checkExistStaffByDeptId(String id) {
        return sysDeptMapper.checkExistStaffByDeptId(id) > NumberKey.ZERO;
    }

    @Override
    public boolean selectOnlineChildrenByParentId(String id) {
        return selectList(new SysDept().setParentId(id).setStatus(SysDeptKey.ONLINE)).size() > NumberKey.ZERO;
    }

    @Override
    public List<SysDept> tree(List<SysDept> selectList) {
        List<SysDept> tree = new ArrayList<>();
        selectList.forEach(v -> {
            if (v.getParentId().equals("0")) {
                tree.add(v);
            }
        });

        return convertTree(tree, selectList.stream().filter(v -> !v.getParentId().equals("0")).toList());
    }

    /**
     * 转换为树形结构
     * @param tree 当前树
     * @param selectList 所有节点
     * @return 树形结构
     */
    private List<SysDept> convertTree(List<SysDept> tree, List<SysDept> selectList) {
        selectList.forEach(v -> {
            tree.forEach(t -> {
                if (t.getId().equals(v.getParentId())) {
                    if (t.getChildren() == null) {
                        t.setChildren(new ArrayList<>());
                    }
                    t.getChildren().add(v);
                    selectList.remove(v);
                }
            });
        });
        if (selectList.size() > 0) {
            convertTree(tree, selectList);
        }
        return tree;
    }

    @Override
    public List<SysDept> roleTree(String roleId) {
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(roleId);
        List<String> list = sysDeptMapper.roleTree(sysRole.getId(), sysRole.isDeptCheckStrictly());
        List<SysDept> sysDeptList = selectList(new SysDept().setStatus(SysDeptKey.ONLINE).setDeleteStatus(SysDeptKey.DELETE_NOT));

        List<SysDept> tree = new ArrayList<>();
        sysDeptList.forEach(v -> {
            if (v.getParentId().equals("0")) {
                tree.add(v);
            }
        });
        List<SysDept> convertTree = convertTree(tree, sysDeptList.stream().filter(v -> !v.getParentId().equals("0")).toList());

        return checkStatus(convertTree, list);
    }

    private List<SysDept> checkStatus(List<SysDept> tree, List<String> list) {
        tree.forEach(v -> {
            if (list.contains(v.getId())) {
                v.setChecked(true);
                list.remove(v.getId());
            }
            if (v.getChildren() != null) {
                checkStatus(v.getChildren(), list);
            }
        });
        return tree;
    }
}
