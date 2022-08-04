package com.hikari.project.login.service;

import com.hikari.project.system.entity.SysStaff;

import java.util.Set;

/**
 * PermissionService
 * @author lkc39miku_cn
 */
public interface PermissionService {
    Set<String> getRolePermission(SysStaff staff);
    Set<String> getMenuPermission(SysStaff staff);
}
