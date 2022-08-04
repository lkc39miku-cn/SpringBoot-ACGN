package com.hikari.project.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hikari.commons.entity.Page;
import com.hikari.commons.result.CompareExecute;
import com.hikari.commons.result.Result;
import com.hikari.commons.util.SecurityUtils;
import com.hikari.project.system.entity.SysConfig;
import com.hikari.project.system.service.impl.SysConfigServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (spring_cloud.sys_config)表控制层
 *
 * @author lkc39miku_cn
 */
@RestController
@RequestMapping("/spring_cloud.sys_config")
public class SysConfigController {
    /**
     * 服务对象
     */
    @Resource
    private SysConfigServiceImpl sysConfigServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("select/{id}")
    @PreAuthorize("@permissionCheck.hasPermissions('system:config:query')")
    @ApiOperation(value = "通过主键查询单条数据", notes = "通过主键查询单条数据")
    public Result<SysConfig> selectOne(@PathVariable(value = "id") String id) {
        return Result.success(sysConfigServiceImpl.selectByPrimaryKey(id));
    }

    /**
     * 查询所有数据
     * @param sysConfig 实体对象
     * @return 所有数据
     */
    @GetMapping("select")
    @PreAuthorize("@permissionCheck.hasPermissions('system:config:list')")
    @ApiOperation(value = "查询所有数据", notes = "查询所有数据")
    public Result<List<SysConfig>> selectList(SysConfig sysConfig) {
        PageInfo<SysConfig> pageInfo = PageHelper.startPage(Page.page(), Page.pageSize()).doSelectPageInfo(() -> sysConfigServiceImpl.selectList(sysConfig));
        return Result.success(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 新增数据
     *
     * @param sysConfig 实体对象
     * @return 新增结果
     */
    @PostMapping("insert")
    @PreAuthorize("@permissionCheck.hasPermissions('system:config:add')")
    @ApiOperation(value = "新增数据", notes = "新增数据")
    public Result<String> insert(@RequestBody SysConfig sysConfig) {
        if (sysConfigServiceImpl.checkConfigName(sysConfig.getName())) {
            return Result.error("配置项名称已存在");
        }
        if (sysConfigServiceImpl.checkConfigKey(sysConfig.getConfigKey())) {
            return Result.error("配置项已存在");
        }
        sysConfig.setCreateStaffId(SecurityUtils.getStaffId());
        return CompareExecute.compare(sysConfigServiceImpl.insert(sysConfig), CompareExecute.ExecuteStatus.INSERT);
    }

    /**
     * 删除数据
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("delete/{id}")
    @PreAuthorize("@permissionCheck.hasPermissions('system:config:delete')")
    @ApiOperation(value = "删除数据", notes = "删除数据")
    public Result<String> delete(@PathVariable(value = "id") String id) {
        return CompareExecute.compare(sysConfigServiceImpl.deleteByPrimaryKey(id), CompareExecute.ExecuteStatus.DELETE);
    }

    /**
     * 批量删除数据
     * @param ids 主键集合
     * @return 删除结果
     */
    @DeleteMapping("delete")
    @PreAuthorize("@permissionCheck.hasPermissions('system:config:delete')")
    @ApiOperation(value = "批量删除数据", notes = "批量删除数据")
    public Result<String> delete(@RequestBody List<String> ids) {
        return CompareExecute.compare(sysConfigServiceImpl.deleteBatch(ids), ids.size(), CompareExecute.ExecuteStatus.DELETE);
    }

    /**
     * 修改数据
     *
     * @param sysConfig 实体对象
     * @return 修改结果
     */
    @PutMapping("update")
    @PreAuthorize("@permissionCheck.hasPermissions('system:config:update')")
    @ApiOperation(value = "修改数据", notes = "修改数据")
    public Result<String> update(@RequestBody SysConfig sysConfig) {
        if (sysConfigServiceImpl.checkConfigName(sysConfig.getName(), sysConfig.getId())) {
            return Result.error("配置项名称已存在");
        }
        if (sysConfigServiceImpl.checkConfigKey(sysConfig.getConfigKey(), sysConfig.getId())) {
            return Result.error("配置项已存在");
        }
        sysConfig.setUpdateStaffId(SecurityUtils.getStaffId());
        return CompareExecute.compare(sysConfigServiceImpl.updateByPrimaryKeySelective(sysConfig), CompareExecute.ExecuteStatus.UPDATE);
    }
}
