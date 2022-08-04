package com.hikari.project.user.controller;

import com.hikari.commons.result.Result;
import com.hikari.project.user.entity.User;
import com.hikari.project.user.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (spring_cloud.`user`)表控制层
 *
 * @author lkc39miku_cn
 */
@RestController
@RequestMapping("/spring_cloud.`user`")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserServiceImpl userServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("select/{id}")
    public Result<User> selectOne(@PathVariable(value = "id") String id) {
        return Result.success(userServiceImpl.selectByPrimaryKey(id));
    }

}
