package com.hikari.project.login.controller;

import com.hikari.commons.result.Result;
import com.hikari.commons.util.SecurityUtils;
import com.hikari.project.login.entity.LoginBody;
import com.hikari.project.login.service.impl.LoginServiceImpl;
import com.hikari.project.system.entity.SysMenu;
import com.hikari.project.system.service.impl.SysMenuServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * LoginController
 *
 * @author lkc39miku_cn
 */
@Slf4j
@RestController
public class LoginController {

    @Resource
    private LoginServiceImpl loginServiceImpl;

    @Resource
    private SysMenuServiceImpl sysMenuServiceImpl;

    /**
     * 登录
     * @param loginBody 登录信息
     * @return token
     */
    @PostMapping(value = "/login")
    public Result<String> login(@RequestBody LoginBody loginBody) {
        log.info("login staff: {}", loginBody.getUsername() + "/" + loginBody.getPassword());
        String token = loginServiceImpl.login(loginBody);
        log.info("success token: {}", token);
        return Result.success(token);
    }

    /**
     * 初始化路由
     * @return 路由列表
     */
    @GetMapping(value = "/get/routers")
    public Result<List<SysMenu>> getRouters() {
        String id = SecurityUtils.getStaffId();
        List<SysMenu> menuList = sysMenuServiceImpl.selectMenuTreeByStaffId(id);
        return Result.success(menuList);
    }
}
