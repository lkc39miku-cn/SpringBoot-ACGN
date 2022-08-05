package com.hikari.project.user.controller;

import com.hikari.commons.result.CompareExecute;
import com.hikari.commons.result.Result;
import com.hikari.commons.util.SecurityUtils;
import com.hikari.project.user.entity.User;
import com.hikari.project.user.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

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

    /**
     * 注册用户
     *
     */
    @PostMapping("insert")
    public Result<String> insert(@RequestBody User user) {
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        user.setCreateTime(LocalDateTime.now());
        return CompareExecute.compare(userServiceImpl.insert(user), CompareExecute.ExecuteStatus.INSERT);
    }

}
