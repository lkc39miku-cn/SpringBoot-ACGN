package com.hikari.project.system.controller;

import com.hikari.project.system.entity.SysRole;
import com.hikari.project.system.service.impl.SysRoleServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (spring_cloud.sys_role)表控制层
 *
 * @author lkc39miku_cn
 */
@RestController
@RequestMapping("/spring_cloud.sys_role")
public class SysRoleController {
    /**
     * 服务对象
     */
    @Resource
    private SysRoleServiceImpl sysRoleServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("select/{id}")
    public SysRole selectOne(@PathVariable(value = "id") String id) {
        return sysRoleServiceImpl.selectByPrimaryKey(id);
    }

}
