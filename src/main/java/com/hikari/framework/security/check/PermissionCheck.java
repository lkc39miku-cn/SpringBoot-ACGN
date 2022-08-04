package com.hikari.framework.security.check;

import com.hikari.commons.key.PermissionKey;
import com.hikari.commons.util.SecurityUtils;
import com.hikari.project.login.entity.LoginStaff;
import com.hikari.project.system.entity.SysRole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Objects;
import java.util.Set;

/**
 * PermissionCheck
 * custom permission check annotation
 * @author lkc39miku_cn
 */
@Component
public class PermissionCheck {
    /**
     * has permission release else prevent
     * @param permission permission
     * @return boolean
     */
    public Boolean hasPermissions(String permission) {
        if (StringUtils.isEmpty(permission)) {
            return false;
        }
        LoginStaff staff = SecurityUtils.getLoginStaff();

        if (Objects.isNull(staff) || CollectionUtils.isEmpty(staff.getPermissions())) {
            return false;
        }

        return hasPermissions(staff.getPermissions(), permission);
    }

    /**
     * has permission prevent else release
     * @param permission permission
     * @return boolean
     */
    public Boolean lacksPermission(String permission) {
        return !hasPermissions(permission);
    }

    public Boolean hasAnyPermission(String permissions) {
        if (StringUtils.isEmpty(permissions)) {
            return false;
        }

        LoginStaff staff = SecurityUtils.getLoginStaff();
        if (Objects.isNull(staff) || CollectionUtils.isEmpty(staff.getPermissions())) {
            return false;
        }

        Set<String> authorities = staff.getPermissions();
        for (String permission : permissions.split(PermissionKey.PERMISSION_DELIMITER)) {
            if (StringUtils.isNotEmpty(permission) && Boolean.TRUE.equals(hasPermissions(authorities, permission))) {
                return true;
            }
        }
        return false;
    }

    /**
     * has role release else prevent
     * @param role role
     * @return boolean
     */
    public Boolean hasRole(String role) {
        if (StringUtils.isEmpty(role)) {
            return false;
        }

        LoginStaff staff = SecurityUtils.getLoginStaff();
        if (Objects.isNull(staff) || CollectionUtils.isEmpty(staff.getSysStaff().getSysRoleList())) {
            return false;
        }

        for (SysRole i : staff.getSysStaff().getSysRoleList()) {
            String roleKey = i.getRoleKey();
            if (PermissionKey.SUPER_ADMIN.equals(roleKey) || roleKey.equals(StringUtils.trim(role))) {
                return true;
            }
        }
        return false;
    }

    /**
     * has role prevent else release
     * @param role role
     * @return boolean
     */
    public Boolean lacksRole(String role) {
        return !hasRole(role);
    }

    /**
     * has any role release else prevent
     * @param roles roles
     * @return boolean
     */
    public Boolean hasAnyRoles(String roles) {
        if (StringUtils.isEmpty(roles)) {
            return false;
        }

        LoginStaff staff = SecurityUtils.getLoginStaff();
        if (Objects.isNull(staff) || CollectionUtils.isEmpty(staff.getSysStaff().getSysRoleList())) {
            return false;
        }

        for (String role : roles.split(PermissionKey.ROLE_DELIMITER)) {
            if (Boolean.TRUE.equals(hasRole(role))) {
                return true;
            }
        }
        return false;
    }

    private Boolean hasPermissions(Set<String> permissions, String permission) {
        return permissions.contains(PermissionKey.ALL_PERMISSION) || permissions.contains(StringUtils.trim(permission));
    }

}
