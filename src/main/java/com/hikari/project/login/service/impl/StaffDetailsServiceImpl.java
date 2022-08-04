package com.hikari.project.login.service.impl;

import com.hikari.commons.key.SysStaffKey;
import com.hikari.framework.exception.service.ServiceException;
import com.hikari.project.login.entity.LoginStaff;
import com.hikari.project.system.entity.SysStaff;
import com.hikari.project.system.service.impl.SysStaffServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * StaffDetailsService
 *
 * @author lkc39miku_cn
 */
@Slf4j
@Service
public class StaffDetailsServiceImpl implements UserDetailsService {
    @Resource
    private SysStaffServiceImpl sysStaffServiceImpl;

    @Resource
    private PermissionServiceImpl permissionServiceImpl;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("check:{}", username);

        SysStaff staff = sysStaffServiceImpl.selectList(new SysStaff()
                .setUsername(username)).get(0);

        if (Objects.isNull(staff)) {
            log.info("登录用户：{} 不存在.", username);
            throw new ServiceException("", "登录用户：" + username + " 不存在");
        }

        if (SysStaffKey.DELETE_YES.equals(staff.getDeleteStatus())) {
            log.info("登录用户：{} 已被删除.", username);
            throw new ServiceException("", "对不起，您的账号：" + username + " 已被删除");
        }

        if (SysStaffKey.STOP.equals(staff.getStatus())) {
            log.info("登录用户：{} 已被停用.", username);
            throw new ServiceException("", "对不起，您的账号：" + username + " 已停用");
        }

        return createLoginStaff(staff);
    }

    private UserDetails createLoginStaff(SysStaff staff) {
        return new LoginStaff()
                .setId(staff.getId())
                .setDeptId(staff.getDeptId())
                .setSysStaff(staff)
                .setPermissions(permissionServiceImpl.getMenuPermission(staff));
    }
}
