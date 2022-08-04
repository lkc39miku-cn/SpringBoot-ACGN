package com.hikari.project.login.service.impl;

import com.hikari.commons.key.PermissionKey;
import com.hikari.project.login.service.PermissionService;
import com.hikari.project.system.entity.SysStaff;
import com.hikari.project.system.service.impl.SysMenuServiceImpl;
import com.hikari.project.system.service.impl.SysRoleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * PermissionServiceImpl
 * @author lkc39miku_cn
 */
@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private SysRoleServiceImpl sysRoleServiceImpl;

    @Resource
    private SysMenuServiceImpl sysMenuServiceImpl;

    @Override
    public Set<String> getRolePermission(SysStaff staff) {
        Set<String> roleList = new HashSet<>(10);
        if (Boolean.TRUE.equals(staff.isAdmin())) {
            roleList.add(PermissionKey.SUPER_ADMIN);
        } else {
            roleList.addAll(sysRoleServiceImpl.selectRolePermissionByStaffId(staff.getId()));
        }
        return roleList;
    }

    @Override
    public Set<String> getMenuPermission(SysStaff staff) {
        Set<String> menuList = new HashSet<>(10);
        if (Boolean.TRUE.equals(staff.isAdmin())) {
            menuList.add(PermissionKey.ALL_PERMISSION);
        } else {
            menuList.addAll(sysMenuServiceImpl.selectMenuPermsByStaffId(staff.getId()));
        }
        log.info("用户权限：{}", menuList);
        return menuList;
    }
}
