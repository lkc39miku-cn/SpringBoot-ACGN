package com.hikari.system.login.service;

import com.hikari.system.login.entity.LoginStaff;

import javax.servlet.http.HttpServletRequest;

/**
 * TokenService
 *
 * @author lkc39miku_cn
 */
public interface TokenService {
    String createToken(LoginStaff loginStaff);

    void deleteLoginStaff(String token);

    void setLoginStaff(LoginStaff loginStaff);

    void verifyToken(LoginStaff loginStaff);

    LoginStaff getLoginStaff(HttpServletRequest request);
}
