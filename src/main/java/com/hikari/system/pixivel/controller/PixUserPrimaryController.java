package com.hikari.system.pixivel.controller;

import com.hikari.system.pixivel.entity.PixUserPrimary;
import com.hikari.system.pixivel.service.impl.PixUserPrimaryServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (spring_cloud.pix_user_primary)表控制层
 *
 * @author lkc39miku_cn
 */
@Api(value = "spring_cloud.pix_user_primary")
@RestController
@RequestMapping("/spring_cloud.pix_user_primary")
public class PixUserPrimaryController {
    /**
     * 服务对象
     */
    @Resource
    private PixUserPrimaryServiceImpl pixUserPrimaryServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("select/{id}")
    @ApiOperation(value = "通过主键查询单条数据", notes = "通过主键查询单条数据")
    public PixUserPrimary selectOne(@PathVariable(value = "id") String id) {
        return pixUserPrimaryServiceImpl.selectByPrimaryKey(id);
    }

}
