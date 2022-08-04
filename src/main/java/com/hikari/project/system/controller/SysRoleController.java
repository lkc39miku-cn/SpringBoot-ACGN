package com.hikari.project.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hikari.commons.entity.Page;
import com.hikari.commons.key.SysRoleKey;
import com.hikari.commons.result.CompareExecute;
import com.hikari.commons.result.Result;
import com.hikari.commons.result.ServiceExecute;
import com.hikari.commons.util.SecurityUtils;
import com.hikari.project.login.entity.LoginStaff;
import com.hikari.project.login.service.impl.PermissionServiceImpl;
import com.hikari.project.login.service.impl.TokenServiceImpl;
import com.hikari.project.system.entity.SysRole;
import com.hikari.project.system.entity.SysStaff;
import com.hikari.project.system.entity.SysStaffRole;
import com.hikari.project.system.service.impl.SysRoleServiceImpl;
import com.hikari.project.system.service.impl.SysStaffServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * (spring_cloud.sys_role)表控制层
 *
 * @author lkc39miku_cn
 */
@RestController
@RequestMapping("/spring_cloud.sys_role")
public class SysRoleController {
    /**
     * 服务对象
     */
    @Resource
    private SysRoleServiceImpl sysRoleServiceImpl;

    @Resource
    private PermissionServiceImpl permissionServiceImpl;

    @Resource
    private SysStaffServiceImpl sysStaffServiceImpl;

    @Resource
    private TokenServiceImpl tokenServiceImpl;
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("select/{id}")
    @ApiOperation(value = "通过主键查询单条数据", notes = "通过主键查询单条数据")
    @PreAuthorize("@permissionCheck.hasPermissions('system:role:query')")
    public Result<SysRole> selectOne(@PathVariable(value = "id") String id) {
        return Result.success(sysRoleServiceImpl.selectByPrimaryKey(id));
    }

    /**
     * 查询所有数据
     * @param sysRole 实体对象
     * @return 所有数据
     */
    @GetMapping("select")
    @ApiOperation(value = "查询所有数据", notes = "查询所有数据")
    @PreAuthorize("@permissionCheck.hasPermissions('system:role:list')")
    public Result<List<SysRole>> selectList(SysRole sysRole) {
        PageInfo<SysRole> pageInfo = PageHelper.startPage(Page.page(), Page.pageSize()).doSelectPageInfo(() -> sysRoleServiceImpl.selectList(sysRole));
        return Result.success(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 新增数据
     * @param sysRole 实体对象
     * @return 新增结果
     */
    @PostMapping("insert")
    @ApiOperation(value = "新增数据", notes = "新增数据")
    @PreAuthorize("@permissionCheck.hasPermissions('system:role:add')")
    public Result<String> insert(@RequestBody SysRole sysRole) {
        if (sysRoleServiceImpl.checkName(sysRole.getName())) {
            return Result.error("角色名称已存在");
        }
        if (sysRoleServiceImpl.checkRoleKey(sysRole.getRoleKey())) {
            return Result.error("角色标识已存在");
        }
        return CompareExecute.compare(sysRoleServiceImpl.insert(sysRole), CompareExecute.ExecuteStatus.INSERT);
    }

    /**
     * 修改数据
     * @param sysRole 实体对象
     * @return 修改结果
     */
    @PutMapping("update")
    @ApiOperation(value = "修改数据", notes = "修改数据")
    @PreAuthorize("@permissionCheck.hasPermissions('system:role:edit')")
    public Result<String> update(@RequestBody SysRole sysRole) {
        sysRoleServiceImpl.checkRoleAllowed(sysRole);
        if (sysRoleServiceImpl.checkName(sysRole.getName(), sysRole.getId())) {
            return Result.error("角色名称已存在");
        }
        if (sysRoleServiceImpl.checkRoleKey(sysRole.getRoleKey(), sysRole.getId())) {
            return Result.error("角色标识已存在");
        }

        ServiceExecute.compare(sysRoleServiceImpl.updateByPrimaryKeySelective(sysRole), ServiceExecute.ExecuteStatus.UPDATE);
        LoginStaff staff = SecurityUtils.getLoginStaff();
        if (Objects.nonNull(staff.getSysStaff()) && !staff.getSysStaff().isAdmin()) {
            staff.setPermissions(permissionServiceImpl.getMenuPermission(staff.getSysStaff()));
            staff.setSysStaff(sysStaffServiceImpl.selectStaffByUsername(staff.getSysStaff().getUsername()));
            tokenServiceImpl.setLoginStaff(staff);
        }
        return Result.success();
    }

    /**
     * 删除数据
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "删除数据", notes = "删除数据")
    @PreAuthorize("@permissionCheck.hasPermissions('system:role:delete')")
    public Result<String> delete(@PathVariable(value = "id") String id) {
        sysRoleServiceImpl.checkRoleAllowed(new SysRole().setId(id));
        return CompareExecute.compare(sysRoleServiceImpl.deleteByPrimaryKey(id), CompareExecute.ExecuteStatus.DELETE);
    }

    /**
     * 修改保存数据权限
     * @param sysRole 实体对象
     * @return 修改结果
     */
    @PutMapping("update/permission")
    @ApiOperation(value = "修改保存数据权限", notes = "修改保存数据权限")
    @PreAuthorize("@permissionCheck.hasPermissions('system:role:edit')")
    public Result<String> updatePermission(@RequestBody SysRole sysRole) {
        sysRoleServiceImpl.checkRoleAllowed(sysRole);
        return CompareExecute.compare(sysRoleServiceImpl.authDataScope(sysRole), sysRole.getSysDeptIdList().size(), CompareExecute.ExecuteStatus.UPDATE);
    }

    /**
     * 状态修改
     * @param sysRole 实体对象
     * @return 修改结果
     */
    @PutMapping("update/status")
    @ApiOperation(value = "状态修改", notes = "状态修改")
    @PreAuthorize("@permissionCheck.hasPermissions('system:role:edit')")
    public Result<String> updateStatus(@RequestBody SysRole sysRole) {
        sysRoleServiceImpl.checkRoleAllowed(sysRole);
        return CompareExecute.compare(sysRoleServiceImpl.updateStatus(sysRole), CompareExecute.ExecuteStatus.UPDATE);
    }

    /**
     * 获取角色选择框列表
     * @return 角色选择框列表
     */
    @GetMapping("select/select")
    @ApiOperation(value = "获取角色选择框列表", notes = "获取角色选择框列表")
    @PreAuthorize("@permissionCheck.hasPermissions('system:role:list')")
    public Result<List<SysRole>> selectSelect() {
        return Result.success(sysRoleServiceImpl.selectList(new SysRole()
                .setDeleteStatus(SysRoleKey.DELETE_NOT)
                .setStatus(SysRoleKey.ONLINE)));
    }

    /**
     * 已分配用户角色列表
     * @param sysStaff 实体对象
     * @return 已分配用户角色列表
     */
    @GetMapping("select/assigned")
    @ApiOperation(value = "已分配用户角色列表", notes = "已分配用户角色列表")
    @PreAuthorize("@permissionCheck.hasPermissions('system:role:list')")
    public Result<List<SysStaff>> selectAssigned(@RequestBody SysStaff sysStaff) {
        PageInfo<SysStaff> pageInfo = PageHelper.startPage(Page.page(), Page.pageSize()).doSelectPageInfo(() -> sysRoleServiceImpl.selectAssigned(sysStaff));
        return Result.success(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 未分配用户角色列表
     * @param sysStaff 实体对象
     * @return 已分配用户角色列表
     */
    @GetMapping("select/unassigned")
    @ApiOperation(value = "已分配用户角色列表", notes = "已分配用户角色列表")
    @PreAuthorize("@permissionCheck.hasPermissions('system:role:list')")
    public Result<List<SysStaff>> selectUnAssigned(@RequestBody SysStaff sysStaff) {
        PageInfo<SysStaff> pageInfo = PageHelper.startPage(Page.page(), Page.pageSize()).doSelectPageInfo(() -> sysRoleServiceImpl.selectUnAssigned(sysStaff));
        return Result.success(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 取消授权用户
     * @param sysStaffRole 实体对象
     * @return 取消授权用户
     */
    @PutMapping("cancel/assign")
    @ApiOperation(value = "取消授权用户", notes = "取消授权用户")
    @PreAuthorize("@permissionCheck.hasPermissions('system:role:edit')")
    public Result<String> cancelAssign(@RequestBody SysStaffRole sysStaffRole) {
        return CompareExecute.compare(sysRoleServiceImpl.cancelAssign(sysStaffRole), CompareExecute.ExecuteStatus.UPDATE);
    }

    /**
     * 批量取消授权用户
     * @param roleId id
     * @param staffIdList idList
     * @return 批量取消授权用户
     */
    @PutMapping("cancel/assign/batch")
    @ApiOperation(value = "批量取消授权用户", notes = "批量取消授权用户")
    @PreAuthorize("@permissionCheck.hasPermissions('system:role:edit')")
    public Result<String> cancelAssignBatch(@RequestParam(value = "roleId") String roleId, @RequestBody List<String> staffIdList) {
        return CompareExecute.compare(sysRoleServiceImpl.cancelAssignBatch(roleId, staffIdList), staffIdList.size(), CompareExecute.ExecuteStatus.UPDATE);
    }

    /**
     * 批量选择用户授权
     * @param roleId id
     * @param staffIdList idList
     * @return 批量选择用户授权
     */
    @PutMapping("assign/batch")
    @ApiOperation(value = "批量选择用户授权", notes = "批量选择用户授权")
    @PreAuthorize("@permissionCheck.hasPermissions('system:role:edit')")
    public Result<String> assignBatch(@RequestParam(value = "roleId") String roleId, @RequestBody List<String> staffIdList) {
        return CompareExecute.compare(sysRoleServiceImpl.assignBatch(roleId, staffIdList), staffIdList.size(), CompareExecute.ExecuteStatus.UPDATE);
    }
}
