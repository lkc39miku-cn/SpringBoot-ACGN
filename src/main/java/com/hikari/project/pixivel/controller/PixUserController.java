package com.hikari.project.pixivel.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hikari.commons.entity.Page;
import com.hikari.commons.result.CompareExecute;
import com.hikari.commons.result.Result;
import com.hikari.project.pixivel.entity.PixUser;
import com.hikari.project.pixivel.service.impl.PixUserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (spring_cloud.pix_user)表控制层
 * @author lkc39miku_cn
 */
@Api(value = "spring_cloud.pix_user")
@RestController
@RequestMapping("/spring_cloud.pix_user")
public class PixUserController {
    /**
     * 服务对象
     */
    @Resource
    private PixUserServiceImpl pixUserServiceImpl;

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("select/{id}")
    @ApiOperation(value = "通过主键查询单条数据", notes = "通过主键查询单条数据")
    public Result<PixUser> selectOne(@PathVariable(value = "id") String id) {
        return Result.success(pixUserServiceImpl.selectByPrimaryKey(id));
    }

    /**
     * 查询所有数据
     * @param pixUser 实体对象
     * @return 所有数据
     */
    @GetMapping("select")
    @ApiOperation(value = "查询所有数据", notes = "查询所有数据")
    public Result<List<PixUser>> selectList(PixUser pixUser) {
        PageInfo<PixUser> pageInfo = PageHelper.startPage(Page.page(), Page.pageSize()).doSelectPageInfo(() -> pixUserServiceImpl.selectAll(pixUser));
        return Result.success(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 新增数据
     * @param pixUser 实体对象
     * @return 新增结果
     */
    @PostMapping("insert")
    @ApiOperation(value = "新增数据", notes = "新增数据")
    public Result<String> insert(@RequestBody PixUser pixUser) {
        return CompareExecute.compare(pixUserServiceImpl.insert(pixUser), CompareExecute.ExecuteStatus.INSERT);
    }

    /**
     * 修改数据
     * @param pixUser 实体对象
     * @return 修改结果
     */
    @PutMapping("update")
    @ApiOperation(value = "修改数据", notes = "修改数据")
    public Result<String> update(@RequestBody PixUser pixUser) {
        return CompareExecute.compare(pixUserServiceImpl.updateByPrimaryKeySelective(pixUser), CompareExecute.ExecuteStatus.UPDATE);
    }

    /**
     * 删除数据
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "删除数据", notes = "删除数据")
    public Result<String> delete(@PathVariable(value = "id") String id) {
        return CompareExecute.compare(pixUserServiceImpl.deleteByPrimaryKey(id), CompareExecute.ExecuteStatus.DELETE);
    }

    /**
     * 最近获取赞最多的20名作者
     * @return 最近获取赞最多的20名作者
     */
    @GetMapping("select/praise")
    @ApiOperation(value = "最近获取赞最多的20名作者", notes = "最近获取赞最多的20名作者")
    public Result<List<PixUser>> selectPraise() {
        return Result.success(pixUserServiceImpl.selectPraise());
    }
}
