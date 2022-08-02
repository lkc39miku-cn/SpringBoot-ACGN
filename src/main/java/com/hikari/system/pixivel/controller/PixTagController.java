package com.hikari.system.pixivel.controller;

import com.hikari.commons.result.CompareExecute;
import com.hikari.commons.result.Result;
import com.hikari.system.pixivel.entity.PixTag;
import com.hikari.system.pixivel.service.impl.PixTagServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (spring_cloud.pix_tag)表控制层
 *
 * @author lkc39miku_cn
 */
@Api(value = "spring_cloud.pix_tag")
@RestController
@RequestMapping("/spring_cloud.pix_tag")
public class PixTagController {
    /**
     * 服务对象
     */
    @Resource
    private PixTagServiceImpl pixTagServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("select/{id}")
    @ApiOperation(value = "通过主键查询单条数据", notes = "通过主键查询单条数据")
    public Result<PixTag> selectOne(@PathVariable(value = "id") String id) {
        return Result.success(pixTagServiceImpl.selectByPrimaryKey(id));
    }

    /**
     * 新增数据
     *
     * @param pixTag 实例对象
     * @return 实例对象
     */
    @PostMapping("insert")
    @ApiOperation(value = "新增数据", notes = "新增数据")
    public Result<String> insert(@RequestBody PixTag pixTag) {
        return CompareExecute.compare(pixTagServiceImpl.insert(pixTag), CompareExecute.ExecuteStatus.INSERT);
    }

    /**
     * 修改数据
     *
     * @param pixTag 实例对象
     * @return 实例对象
     */
    @PutMapping("update")
    @ApiOperation(value = "修改数据", notes = "修改数据")
    public Result<String> update(@RequestBody PixTag pixTag) {
        return CompareExecute.compare(pixTagServiceImpl.updateByPrimaryKeySelective(pixTag), CompareExecute.ExecuteStatus.UPDATE);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "通过主键删除数据", notes = "通过主键删除数据")
    public Result<String> deleteById(@PathVariable(value = "id") String id) {
        return CompareExecute.compare(pixTagServiceImpl.deleteByPrimaryKey(id), CompareExecute.ExecuteStatus.DELETE);
    }

}
