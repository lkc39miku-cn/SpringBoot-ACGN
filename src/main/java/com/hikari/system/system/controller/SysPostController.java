package com.hikari.system.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hikari.commons.entity.Page;
import com.hikari.commons.result.CompareExecute;
import com.hikari.commons.result.Result;
import com.hikari.system.system.entity.SysPost;
import com.hikari.system.system.service.impl.SysPostServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (spring_cloud.sys_post)表控制层
 *
 * @author lkc39miku_cn
 */
@RestController
@RequestMapping("/spring_cloud.sys_post")
public class SysPostController {
    /**
     * 服务对象
     */
    @Resource
    private SysPostServiceImpl sysPostServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("select/{id}")
    @ApiOperation(value = "通过主键查询单条数据", notes = "通过主键查询单条数据")
    @PreAuthorize("@permissionCheck.hasPermissions('system:post:query')")
    public Result<SysPost> selectOne(@PathVariable(value = "id") String id) {
        return Result.success(sysPostServiceImpl.selectByPrimaryKey(id));
    }

    /**
     * 查询所有数据
     * @param sysPost 实体对象
     * @return 所有数据
     */
    @GetMapping("select")
    @ApiOperation(value = "查询所有数据", notes = "查询所有数据")
    @PreAuthorize("@permissionCheck.hasPermissions('system:post:list')")
    public Result<List<SysPost>> selectList(SysPost sysPost) {
        PageInfo<SysPost> pageInfo = PageHelper.startPage(Page.page(), Page.pageSize()).doSelectPageInfo(() -> sysPostServiceImpl.selectList(sysPost));
        return Result.success(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 添加数据
     * @param sysPost 实体对象
     * @return 添加结果
     */
    @PostMapping("insert")
    @ApiOperation(value = "添加数据", notes = "添加数据")
    @PreAuthorize("@permissionCheck.hasPermissions('system:post:add')")
    public Result<String> insert(SysPost sysPost) {
        if (sysPostServiceImpl.checkPostName(sysPost.getName())) {
            return Result.error("岗位名称已存在");
        }
        if (sysPostServiceImpl.checkPostCode(sysPost.getCode())) {
            return Result.error("岗位编码已存在");
        }
        return CompareExecute.compare(sysPostServiceImpl.insert(sysPost), CompareExecute.ExecuteStatus.INSERT);
    }

    /**
     * 删除数据
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "删除数据", notes = "删除数据")
    @PreAuthorize("@permissionCheck.hasPermissions('system:post:delete')")
    public Result<String> delete(@PathVariable(value = "id") String id) {
        if (sysPostServiceImpl.checkStaffPost(id)) {
            return Result.error("该岗位已被使用，不能删除");
        }
        return CompareExecute.compare(sysPostServiceImpl.deleteByPrimaryKey(id), CompareExecute.ExecuteStatus.DELETE);
    }

    /**
     * 更新数据
     * @param sysPost 实体对象
     * @return 更新结果
     */
    @PutMapping("update")
    @ApiOperation(value = "更新数据", notes = "更新数据")
    @PreAuthorize("@permissionCheck.hasPermissions('system:post:update')")
    public Result<String> update(SysPost sysPost) {
        if (sysPostServiceImpl.checkPostName(sysPost.getName(), sysPost.getId())) {
            return Result.error("岗位名称已存在");
        }
        if (sysPostServiceImpl.checkPostCode(sysPost.getCode(), sysPost.getId())) {
            return Result.error("岗位编码已存在");
        }
        return CompareExecute.compare(sysPostServiceImpl.updateByPrimaryKeySelective(sysPost), CompareExecute.ExecuteStatus.UPDATE);
    }

    /**
     * 选择框
     * @return 所有数据
     */
    @GetMapping("select-box")
    @ApiOperation(value = "选择框", notes = "选择框")
    @PreAuthorize("@permissionCheck.hasPermissions('system:post:list')")
    public Result<List<SysPost>> selectBox() {
        return Result.success(sysPostServiceImpl.selectList(new SysPost()));
    }
}
