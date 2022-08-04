package com.hikari.project.pixivel.controller;

import com.hikari.project.pixivel.entity.PixPicture;
import com.hikari.project.pixivel.service.impl.PixPictureServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (spring_cloud.pix_picture)表控制层
 *
 * @author lkc39miku_cn
 */
@Api(value = "spring_cloud.pix_picture")
@RestController
@RequestMapping("/spring_cloud.pix_picture")
public class PixPictureController {
    /**
     * 服务对象
     */
    @Resource
    private PixPictureServiceImpl pixPictureServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("select/{id}")
    @ApiOperation(value = "通过主键查询单条数据", notes = "通过主键查询单条数据")
    public PixPicture selectOne(@PathVariable(value = "id") String id) {
        return pixPictureServiceImpl.selectByPrimaryKey(id);
    }



}
