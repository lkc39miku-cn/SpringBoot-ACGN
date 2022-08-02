package com.hikari.system.pixivel.controller;

import com.hikari.commons.result.CompareExecute;
import com.hikari.commons.result.Result;
import com.hikari.system.pixivel.entity.PixUserFeatured;
import com.hikari.system.pixivel.service.impl.PixUserFeaturedServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (spring_cloud.pix_user_featured)表控制层
 *
 * @author lkc39miku_cn
 */
@Api(value = "spring_cloud.pix_user_featured")
@RestController
@RequestMapping("/spring_cloud.pix_user_featured")
public class PixUserFeaturedController {
    /**
     * 服务对象
     */
    @Resource
    private PixUserFeaturedServiceImpl pixUserFeaturedServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("select/{id}")
    @ApiOperation(value = "通过主键查询单条数据", notes = "通过主键查询单条数据")
    public Result<PixUserFeatured> selectOne(@PathVariable(value = "id") String id) {
        return Result.success(pixUserFeaturedServiceImpl.selectByPrimaryKey(id));
    }

    /**
     * 添加数据
     * @param pixUserFeatured 实例对象
     * @return 新增结果
     */
    @PostMapping("insert")
    @ApiOperation(value = "添加数据", notes = "添加数据")
    public Result<String> insert(@RequestBody PixUserFeatured pixUserFeatured) {
        return CompareExecute.compare(pixUserFeaturedServiceImpl.insert(pixUserFeatured), CompareExecute.ExecuteStatus.INSERT);
    }

    /**
     * 删除数据
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "删除数据", notes = "删除数据")
    public Result<String> delete(@PathVariable(value = "id") String id) {
        return CompareExecute.compare(pixUserFeaturedServiceImpl.deleteByPrimaryKey(id), CompareExecute.ExecuteStatus.DELETE);
    }
}
