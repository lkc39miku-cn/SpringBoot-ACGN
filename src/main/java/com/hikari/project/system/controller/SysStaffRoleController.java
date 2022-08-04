package com.hikari.project.system.controller;

import com.hikari.project.system.service.impl.SysStaffRoleServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (spring_cloud.sys_staff_role)表控制层
 *
 * @author lkc39miku_cn
 */
@RestController
@RequestMapping("/spring_cloud.sys_staff_role")
public class SysStaffRoleController {
    /**
     * 服务对象
     */
    @Resource
    private SysStaffRoleServiceImpl sysStaffRoleServiceImpl;


}
