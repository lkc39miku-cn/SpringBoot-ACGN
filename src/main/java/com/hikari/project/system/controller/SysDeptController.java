package com.hikari.project.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hikari.commons.entity.Page;
import com.hikari.commons.key.SysDeptKey;
import com.hikari.commons.result.CompareExecute;
import com.hikari.commons.result.Result;
import com.hikari.commons.util.SecurityUtils;
import com.hikari.project.system.entity.SysDept;
import com.hikari.project.system.service.impl.SysDeptServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (spring_cloud.sys_dept)表控制层
 *
 * @author lkc39miku_cn
 */
@RestController
@RequestMapping("/spring_cloud.sys_dept")
public class SysDeptController {
    /**
     * 服务对象
     */
    @Resource
    private SysDeptServiceImpl sysDeptServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("select/{id}")
    @ApiOperation(value = "通过主键查询单条数据", notes = "通过主键查询单条数据")
    @PreAuthorize("@permissionCheck.hasPermissions('system:dept:query')")
    public Result<SysDept> selectOne(@PathVariable(value = "id") String id) {
        return Result.success(sysDeptServiceImpl.selectByPrimaryKey(id));
    }

    /**
     * 查询所有数据
     * @param sysDept 实体对象
     * @return 所有数据
     */
    @Deprecated
    @GetMapping("select")
    @ApiOperation(value = "查询所有数据", notes = "查询所有数据")
    @PreAuthorize("@permissionCheck.hasPermissions('system:dept:list')")
    public Result<List<SysDept>> selectList(SysDept sysDept) {
        PageInfo<SysDept> pageInfo = PageHelper.startPage(Page.page(), Page.pageSize()).doSelectPageInfo(() -> sysDeptServiceImpl.selectList(sysDept));
        return Result.success(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 新增数据
     *
     * @param sysDept 实体对象
     * @return 新增结果
     */
    @PostMapping("insert")
    @ApiOperation(value = "新增数据", notes = "新增数据")
    @PreAuthorize("@permissionCheck.hasPermissions('system:dept:add')")
    public Result<String> insert(@RequestBody SysDept sysDept) {
        if (sysDeptServiceImpl.checkDeptNameUnique(sysDept.getName())) {
            return Result.error("新增部门'" + sysDept.getName() + "'失败，部门名称已存在");
        }
        sysDept.setCreateStaffId(SecurityUtils.getStaffId());
        return CompareExecute.compare(sysDeptServiceImpl.insert(sysDept), CompareExecute.ExecuteStatus.INSERT);
    }

    /**
     * 修改数据
     *
     * @param sysDept 实体对象
     * @return 修改结果
     */
    @PutMapping("update")
    @ApiOperation(value = "修改数据", notes = "修改数据")
    @PreAuthorize("@permissionCheck.hasPermissions('system:dept:edit')")
    public Result<String> update(@RequestBody SysDept sysDept) {
        if (sysDeptServiceImpl.checkDeptNameUnique(sysDept.getName(), sysDept.getId())) {
            return Result.error("修改部门'" + sysDept.getName() + "'失败，部门名称已存在");
        }
        if (sysDept.getParentId().equals(sysDept.getId())) {
            return Result.error("修改部门'" + sysDept.getName() + "'失败，上级部门不能为自己");
        }
        if (SysDeptKey.STOP.equals(sysDept.getStatus()) && sysDeptServiceImpl.selectOnlineChildrenByParentId(sysDept.getId())) {
            return Result.error("修改部门'" + sysDept.getName() + "'失败，存在子部门在线状态");
        }
        sysDept.setUpdateStaffId(SecurityUtils.getStaffId());
        return CompareExecute.compare(sysDeptServiceImpl.updateByPrimaryKeySelective(sysDept), CompareExecute.ExecuteStatus.UPDATE);
    }

    /**
     * 删除数据
     *
     * @param id 实体对象
     * @return 删除结果
     */
    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "删除数据", notes = "删除数据")
    @PreAuthorize("@permissionCheck.hasPermissions('system:dept:delete')")
    public Result<String> delete(@PathVariable(value = "id") String id) {
        if (sysDeptServiceImpl.hasChildByParentId(id)) {
            return Result.error("删除部门失败，当前部门存在下级部门");
        }
        if (sysDeptServiceImpl.checkExistStaffByDeptId(id)) {
            return Result.error("删除部门失败，当前部门存在员工");
        }
        return CompareExecute.compare(sysDeptServiceImpl.deleteByPrimaryKey(id), CompareExecute.ExecuteStatus.DELETE);
    }

    /**
     * 排除指定节点
     * @param id id
     * @return 排除结果
     */
    @GetMapping("select/exclude/{id}")
    @ApiOperation(value = "排除指定节点", notes = "排除指定节点")
    @PreAuthorize("@permissionCheck.hasPermissions('system:dept:list')")
    public Result<List<SysDept>> selectExclude(@PathVariable(value = "id") String id) {
        List<SysDept> sysDeptList = sysDeptServiceImpl.selectList(new SysDept());
        sysDeptList.removeIf(sysDept -> sysDept.getId().equals(id)
        || ArrayUtils.contains(StringUtils.split(sysDept.getAncestors(), ","), id));
        return Result.success(sysDeptList);
    }

    /**
     * 树形结构
     * @return 树形结构
     */
    @GetMapping("tree")
    @ApiOperation(value = "树形结构", notes = "树形结构")
    @PreAuthorize("@permissionCheck.hasPermissions('system:dept:list')")
    public Result<List<SysDept>> tree() {
        return Result.success(sysDeptServiceImpl.tree(sysDeptServiceImpl.selectList(new SysDept().setStatus(SysDeptKey.ONLINE).setDeleteStatus(SysDeptKey.DELETE_NOT))));
    }

    /**
     * 加载对应角色部门列表树
     * @param roleId 角色id
     * @return 树形结构
     */
    @GetMapping("role/tree/{roleId}")
    @ApiOperation(value = "加载对应角色部门列表树", notes = "加载对应角色部门列表树")
    @PreAuthorize("@permissionCheck.hasPermissions('system:dept:list')")
    public Result<List<SysDept>> roleTree(@PathVariable(value = "roleId") String roleId) {
        return Result.success(sysDeptServiceImpl.roleTree(roleId));
    }
}
