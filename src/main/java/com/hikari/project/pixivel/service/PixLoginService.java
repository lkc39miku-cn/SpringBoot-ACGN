package com.hikari.project.pixivel.service;

import com.hikari.project.pixivel.entity.PixUser;
import com.hikari.project.user.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * PixLoginService
 *
 * @author lkc39miku_cn
 */
public interface PixLoginService {
    String login(User user);

    String logout(HttpServletRequest request);
}
