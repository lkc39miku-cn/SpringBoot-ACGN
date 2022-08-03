package com.hikari.system.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hikari.commons.entity.Page;
import com.hikari.commons.result.CompareExecute;
import com.hikari.commons.result.Result;
import com.hikari.system.system.entity.SysStaff;
import com.hikari.system.system.entity.vo.SysStaffVo;
import com.hikari.system.system.service.impl.SysStaffServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * (spring_cloud.sys_staff)表控制层
 *
 * @author lkc39miku_cn
 */
@RestController
@RequestMapping("/spring_cloud.sys_staff")
public class SysStaffController {
    /**
     * 服务对象
     */
    @Resource
    private SysStaffServiceImpl sysStaffServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("select/{id}")
    @ApiOperation(value = "通过主键查询单条数据", notes = "通过主键查询单条数据")
    @PreAuthorize("@permissionCheck.hasPermissions('system:staff:query')")
    public Result<SysStaffVo> selectOne(@PathVariable(value = "id") String id) {
        return Result.success(sysStaffServiceImpl.selectByPrimaryKey(id));
    }

    /**
     * 查询数据
     * @param sysStaff 实体对象
     * @return 查询数据
     */
    @GetMapping("select")
    @ApiOperation(value = "查询数据", notes = "查询数据")
    @PreAuthorize("@permissionCheck.hasPermissions('system:staff:list')")
    public Result<List<SysStaff>> selectList(SysStaff sysStaff) {
        PageInfo<SysStaff> pageIngo = PageHelper.startPage(Page.page(), Page.pageSize()).doSelectPageInfo(() -> sysStaffServiceImpl.selectList(sysStaff));
        return Result.success(pageIngo.getList(), pageIngo.getTotal());
    }

    /**
     * 添加数据
     * @param sysStaff 实体对象
     * @return 添加结果
     */
    @PostMapping("insert")
    @ApiOperation(value = "添加数据", notes = "添加数据")
    @PreAuthorize("@permissionCheck.hasPermissions('system:staff:add')")
    public Result<String> insert(SysStaff sysStaff) {
        if (sysStaffServiceImpl.checkStaffUsername(sysStaff.getUsername())) {
            return Result.error("用户名已存在");
        }
        if (sysStaffServiceImpl.checkStaffPhone(sysStaff.getPhone())) {
            return Result.error("手机号已存在");
        }
        if (sysStaffServiceImpl.checkStaffEmail(sysStaff.getEmail())) {
            return Result.error("邮箱已存在");
        }
        return CompareExecute.compare(sysStaffServiceImpl.insert(sysStaff), CompareExecute.ExecuteStatus.INSERT);
    }

    /**
     * 修改数据
     * @param sysStaff 实体对象
     * @return 修改结果
     */
    @PutMapping("update")
    @ApiOperation(value = "修改数据", notes = "修改数据")
    @PreAuthorize("@permissionCheck.hasPermissions('system:staff:edit')")
    public Result<String> update(SysStaff sysStaff) {
        if (sysStaffServiceImpl.checkStaffUsername(sysStaff.getUsername(), sysStaff.getId())) {
            return Result.error("用户名已存在");
        }
        if (sysStaffServiceImpl.checkStaffPhone(sysStaff.getPhone(), sysStaff.getId())) {
            return Result.error("手机号已存在");
        }
        if (sysStaffServiceImpl.checkStaffEmail(sysStaff.getEmail(), sysStaff.getId())) {
            return Result.error("邮箱已存在");
        }
        return CompareExecute.compare(sysStaffServiceImpl.updateByPrimaryKeySelective(sysStaff), CompareExecute.ExecuteStatus.UPDATE);
    }

    /**
     * 删除数据
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "删除数据", notes = "删除数据")
    @PreAuthorize("@permissionCheck.hasPermissions('system:staff:delete')")
    public Result<String> delete(@PathVariable(value = "id") String id) {
        return CompareExecute.compare(sysStaffServiceImpl.deleteByPrimaryKey(id), CompareExecute.ExecuteStatus.DELETE);
    }

    /**
     * 修改密码
     * @param sysStaff 实体对象
     * @return 修改结果
     */
    @PutMapping("update/password")
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @PreAuthorize("@permissionCheck.hasPermissions('system:staff:edit')")
    public Result<String> updatePassword(SysStaff sysStaff) {
        return CompareExecute.compare(sysStaffServiceImpl.updateByPrimaryKeySelective(new SysStaff()
                .setId(sysStaff.getId())
                .setPassword(sysStaff.getPassword())
                .setUpdateTime(LocalDateTime.now())), CompareExecute.ExecuteStatus.UPDATE);
    }

    /**
     * 状态修改
     * @param sysStaff 实体对象
     * @return 修改结果
     */
    @PutMapping("update/status")
    @ApiOperation(value = "状态修改", notes = "状态修改")
    @PreAuthorize("@permissionCheck.hasPermissions('system:staff:edit')")
    public Result<String> updateStatus(SysStaff sysStaff) {
        return CompareExecute.compare(sysStaffServiceImpl.updateByPrimaryKeySelective(new SysStaff()
                .setId(sysStaff.getId())
                .setStatus(sysStaff.getStatus())
                .setUpdateTime(LocalDateTime.now())), CompareExecute.ExecuteStatus.UPDATE);
    }

    /**
     * 获取已授权角色
     * @param id 员工
     * @return 获取已授权角色
     */
    @GetMapping("get/role/{id}")
    @ApiOperation(value = "获取已授权角色", notes = "获取已授权角色")
    @PreAuthorize("@permissionCheck.hasPermissions('system:staff:list')")
    public Result<SysStaffVo> getRole(@PathVariable(value = "id") String id) {
        return Result.success(sysStaffServiceImpl.getRole(id));
    }

    /**
     * 授权角色
     * @param id 员工
     * @param roleIds 角色
     * @return 授权角色
     */
    @PutMapping("update/role/{id}")
    @ApiOperation(value = "授权角色", notes = "授权角色")
    @PreAuthorize("@permissionCheck.hasPermissions('system:staff:edit')")
    public Result<String> updateRole(@PathVariable(value = "id") String id, @RequestBody List<String> roleIds) {
        return CompareExecute.compare(sysStaffServiceImpl.updateRole(id, roleIds), CompareExecute.ExecuteStatus.UPDATE);
    }
}
