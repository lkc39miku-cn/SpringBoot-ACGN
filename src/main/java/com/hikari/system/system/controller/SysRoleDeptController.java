package com.hikari.system.system.controller;

import com.hikari.system.system.entity.SysRoleDept;
import com.hikari.system.system.service.impl.SysRoleDeptServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (spring_cloud.sys_role_dept)表控制层
 *
 * @author lkc39miku_cn
 */
@RestController
@RequestMapping("/spring_cloud.sys_role_dept")
public class SysRoleDeptController {
    /**
     * 服务对象
     */
    @Resource
    private SysRoleDeptServiceImpl sysRoleDeptServiceImpl;
}
