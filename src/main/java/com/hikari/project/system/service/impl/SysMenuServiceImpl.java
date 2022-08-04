package com.hikari.project.system.service.impl;

import com.hikari.commons.key.NumberKey;
import com.hikari.commons.key.SysMenuKey;
import com.hikari.project.system.entity.SysRole;
import com.hikari.project.system.mapper.SysRoleMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

import com.hikari.project.system.mapper.SysMenuMapper;
import com.hikari.project.system.entity.SysMenu;
import com.hikari.project.system.service.SysMenuService;

/**
 * SysMenuServiceImpl
 * @author lkc39miku_cn
 */  
@Service
public class SysMenuServiceImpl implements SysMenuService{

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return sysMenuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysMenu record) {
        record.setCreateTime(LocalDateTime.now());
        return sysMenuMapper.insert(record);
    }

    @Override
    public SysMenu selectByPrimaryKey(String id) {
        return sysMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysMenu record) {
        record.setUpdateTime(LocalDateTime.now());
        return sysMenuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateBatchSelective(List<SysMenu> list) {
        return sysMenuMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<SysMenu> list) {
        return sysMenuMapper.batchInsert(list);
    }

    @Override
    public List<SysMenu> selectList(SysMenu sysMenu) {
        return sysMenuMapper.selectList(sysMenu);
    }

    @Override
    public boolean checkMenuName(String name) {
        return selectList(new SysMenu().setName(name)).size() > NumberKey.ZERO;
    }

    @Override
    public boolean checkChildMenu(String id) {
        return selectList(new SysMenu().setParentId(id)).size() > NumberKey.ZERO;
    }

    @Override
    public boolean checkRoleMenu(String id) {
        return sysMenuMapper.checkRoleMenu(id) > NumberKey.ZERO;
    }

    @Override
    public boolean checkMenuName(String name, String id) {
        return selectNotList(new SysMenu().setName(name).setId(id)).size() > NumberKey.ZERO;
    }

    @Override
    public List<SysMenu> selectNotList(SysMenu sysMenu) {
        return sysMenuMapper.selectNotList(sysMenu);
    }

    @Override
    public List<SysMenu> tree(List<SysMenu> setStatus) {
        List<SysMenu> tree = new ArrayList<>();
        setStatus.forEach(v -> {
            if (v.getParentId().equals("0")) {
                tree.add(v);
            }
        });

        return convertTree(tree, setStatus.stream().filter(v -> !v.getParentId().equals("0")).toList());
    }

    /**
     * 将菜单转换为树形结构
     * @param tree 菜单树
     * @param selectList 菜单列表
     * @return 菜单树
     */
    private List<SysMenu> convertTree(List<SysMenu> tree, List<SysMenu> selectList) {
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
    public List<SysMenu> roleTree(String roleId) {
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(roleId);
        List<String> list = sysMenuMapper.roleTree(sysRole.getId(), sysRole.isMenuCheckStrictly());
        List<SysMenu> sysMenuList = selectList(new SysMenu().setVisible(SysMenuKey.VISIBLE_SHOW).setStatus(SysMenuKey.ONLINE));

        List<SysMenu> tree = new ArrayList<>();
        sysMenuList.forEach(v -> {
            if (v.getParentId().equals("0")) {
                tree.add(v);
            }
        });
        List<SysMenu> convertTree = convertTree(tree, sysMenuList.stream().filter(v -> !v.getParentId().equals("0")).toList());

        return checkStatus(convertTree, list);
    }

    private List<SysMenu> checkStatus(List<SysMenu> tree, List<String> list) {
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

    @Override
    public Set<String> selectMenuPermsByStaffId(String id) {
        List<String> menuList = sysMenuMapper.selectMenuPermsByStaffId(id);
        Set<String> set = new HashSet<>(10);
        menuList.stream().filter(Objects::nonNull).forEach(v -> set.addAll(Arrays.asList(v.trim().split(","))));
        return set;
    }
}
