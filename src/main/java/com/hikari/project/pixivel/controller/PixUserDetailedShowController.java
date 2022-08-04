package com.hikari.project.pixivel.controller;

import com.hikari.commons.result.Result;
import com.hikari.project.pixivel.entity.PixUserDetailedShow;
import com.hikari.project.pixivel.service.impl.PixUserDetailedShowServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (spring_cloud.pix_user_detailed_show)表控制层
 *
 * @author lkc39miku_cn
 */
@Api(value = "spring_cloud.pix_user_detailed_show")
@RestController
@RequestMapping("/spring_cloud.pix_user_detailed_show")
public class PixUserDetailedShowController {
    /**
     * 服务对象
     */
    @Resource
    private PixUserDetailedShowServiceImpl pixUserDetailedShowServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("select/{id}")
    @ApiOperation(value = "通过主键查询单条数据", notes = "通过主键查询单条数据")
    public PixUserDetailedShow selectOne(@PathVariable(value = "id") String id) {
        return pixUserDetailedShowServiceImpl.selectByPrimaryKey(id);
    }

    /**
     * 根据作者id查询作者详情展示状态
     */
    @GetMapping("select/author/{authorId}")
    @ApiOperation(value = "根据作者id查询作者详情", notes = "根据作者id查询作者详情")
    public Result<PixUserDetailedShow> selectByAuthorId(@PathVariable(value = "authorId") String authorId) {
        return Result.success(pixUserDetailedShowServiceImpl.selectByAuthorId(authorId));
    }

}
