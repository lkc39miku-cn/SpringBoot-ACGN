package com.hikari.system.system.controller;

import com.hikari.system.system.entity.SysRoleMenu;
import com.hikari.system.system.service.impl.SysRoleMenuServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (spring_cloud.sys_role_menu)表控制层
 *
 * @author lkc39miku_cn
 */
@RestController
@RequestMapping("/spring_cloud.sys_role_menu")
public class SysRoleMenuController {
    /**
     * 服务对象
     */
    @Resource
    private SysRoleMenuServiceImpl sysRoleMenuServiceImpl;


}
