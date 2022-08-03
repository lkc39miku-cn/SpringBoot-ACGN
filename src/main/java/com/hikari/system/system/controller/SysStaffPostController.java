package com.hikari.system.system.controller;

import com.hikari.system.system.entity.SysStaffPost;
import com.hikari.system.system.service.impl.SysStaffPostServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (spring_cloud.sys_staff_post)表控制层
 *
 * @author lkc39miku_cn
 */
@RestController
@RequestMapping("/spring_cloud.sys_staff_post")
public class SysStaffPostController {
    /**
     * 服务对象
     */
    @Resource
    private SysStaffPostServiceImpl sysStaffPostServiceImpl;


}
