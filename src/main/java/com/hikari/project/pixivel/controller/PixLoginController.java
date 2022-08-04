package com.hikari.project.pixivel.controller;

import com.hikari.commons.result.Result;
import com.hikari.project.pixivel.entity.PixUser;
import com.hikari.project.pixivel.service.impl.PixLoginServiceImpl;
import com.hikari.project.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * PixLoginController
 *
 * @author lkc39miku_cn
 */
@Api(value = "spring_cloud.pix_login")
@RestController
@RequestMapping("/spring_cloud.pix_login")
public class PixLoginController {

    @Resource
    private PixLoginServiceImpl pixLoginServiceImpl;

    /**
     * 登录
     * @param user 用户信息
     * @return 结果
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "登录")
    public Result<String> login(User user) {
        return Result.success(pixLoginServiceImpl.login(user));
    }

    /**
     * 登出
     */
    @PostMapping("/logout")
    @ApiOperation(value = "登出", notes = "登出")
    public Result<String> logout(HttpServletRequest request) {
        return Result.success(pixLoginServiceImpl.logout(request));
    }
}
