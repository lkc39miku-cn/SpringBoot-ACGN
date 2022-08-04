package com.hikari.commons.util;

import com.hikari.commons.enums.HttpServletResponse;
import com.hikari.framework.exception.service.ServiceException;
import com.hikari.project.login.entity.LoginStaff;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * SecurityUtils
 * @author lkc39miku_cn
 */
public class SecurityUtils {
    public static LoginStaff getLoginStaff() {
        try {
            return (LoginStaff) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new ServiceException(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }


    public static String getStaffId() {
        return getLoginStaff().getId();
    }

    public static String getUsername() {
        return getLoginStaff().getUsername();
    }

    public static String getDeptId() {
        return getLoginStaff().getDeptId();
    }

    public static String encryptPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    public static Boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
}
