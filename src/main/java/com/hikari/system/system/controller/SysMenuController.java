package com.hikari.system.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hikari.commons.entity.Page;
import com.hikari.commons.key.SysMenuKey;
import com.hikari.commons.result.CompareExecute;
import com.hikari.commons.result.Result;
import com.hikari.commons.util.IpUtils;
import com.hikari.system.system.entity.SysMenu;
import com.hikari.system.system.service.impl.SysMenuServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (spring_cloud.sys_menu)表控制层
 *
 * @author lkc39miku_cn
 */
@RestController
@RequestMapping("/spring_cloud.sys_menu")
public class SysMenuController {
    /**
     * 服务对象
     */
    @Resource
    private SysMenuServiceImpl sysMenuServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("select/{id}")
    @ApiOperation(value = "通过主键查询单条数据", notes = "通过主键查询单条数据")
    @PreAuthorize("@permissionCheck.hasPermissions('system:menu:query')")
    public Result<SysMenu> selectOne(@PathVariable(value = "id") String id) {
        return Result.success(sysMenuServiceImpl.selectByPrimaryKey(id));
    }

    /**
     * 查询所有数据
     * @param sysMenu 实体对象
     * @return 所有数据
     */
    @Deprecated
    @GetMapping("select")
    @ApiOperation(value = "查询所有数据", notes = "查询所有数据")
    @PreAuthorize("@permissionCheck.hasPermissions('system:menu:list')")
    public Result<List<SysMenu>> selectList(SysMenu sysMenu) {
        PageInfo<SysMenu> pageInfo = PageHelper.startPage(Page.page(), Page.pageSize()).doSelectPageInfo(() -> sysMenuServiceImpl.selectList(sysMenu));
        return Result.success(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 添加一条数据
     * @param sysMenu 实体对象
     * @return 结果
     */
    @PostMapping("insert")
    @ApiOperation(value = "添加一条数据", notes = "添加一条数据")
    @PreAuthorize("@permissionCheck.hasPermissions('system:menu:add')")
    public Result<String> insert(@RequestBody SysMenu sysMenu) {
        if (sysMenuServiceImpl.checkMenuName(sysMenu.getName())) {
            return Result.error("菜单名称已存在");
        }
        if (SysMenuKey.IS_FRAME.equals(sysMenu.getIsFrame()) && !IpUtils.isHttp(sysMenu.getPath())) {
            return Result.error("非http协议的链接必须是http或https开头");
        }
        return CompareExecute.compare(sysMenuServiceImpl.insert(sysMenu), CompareExecute.ExecuteStatus.INSERT);
    }

    /**
     * 删除一条数据
     * @param id 主键
     * @return 结果
     */
    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "删除一条数据", notes = "删除一条数据")
    @PreAuthorize("@permissionCheck.hasPermissions('system:menu:delete')")
    public Result<String> delete(@PathVariable(value = "id") String id) {
        if (sysMenuServiceImpl.checkChildMenu(id)) {
            return Result.error("该菜单下存在子菜单，不能删除");
        }
        if (sysMenuServiceImpl.checkRoleMenu(id)) {
            return Result.error("该菜单下存在角色，不能删除");
        }
        return CompareExecute.compare(sysMenuServiceImpl.deleteByPrimaryKey(id), CompareExecute.ExecuteStatus.DELETE);
    }

    /**
     * 修改数据
     * @param sysMenu 实体对象
     * @return 结果
     */
    @PutMapping("update")
    @ApiOperation(value = "修改数据", notes = "修改数据")
    @PreAuthorize("@permissionCheck.hasPermissions('system:menu:update')")
    public Result<String> update(@RequestBody SysMenu sysMenu) {
        if (sysMenuServiceImpl.checkMenuName(sysMenu.getName(), sysMenu.getId())) {
            return Result.error("菜单名称已存在");
        }
        if (SysMenuKey.IS_FRAME.equals(sysMenu.getIsFrame()) && !IpUtils.isHttp(sysMenu.getPath())) {
            return Result.error("非http协议的链接必须是http或https开头");
        }
        if (sysMenu.getParentId().equals(sysMenu.getId())) {
            return Result.error("上级菜单不能为自身");
        }
        return CompareExecute.compare(sysMenuServiceImpl.updateByPrimaryKeySelective(sysMenu), CompareExecute.ExecuteStatus.UPDATE);
    }

    /**
     * 树形菜单
     * @return 结果
     */
    @GetMapping("tree")
    @ApiOperation(value = "树形菜单", notes = "树形菜单")
    @PreAuthorize("@permissionCheck.hasPermissions('system:menu:list')")
    public Result<List<SysMenu>> tree() {
        return Result.success(sysMenuServiceImpl.tree(sysMenuServiceImpl.selectList(new SysMenu().setVisible(SysMenuKey.VISIBLE_SHOW)
                .setStatus(SysMenuKey.ONLINE))));
    }

    /**
     * 加载对应角色菜单列表树
     * @param roleId 角色id
     * @return 结果
     */
    @GetMapping("roleTree/{roleId}")
    @ApiOperation(value = "加载对应角色菜单列表树", notes = "加载对应角色菜单列表树")
    @PreAuthorize("@permissionCheck.hasPermissions('system:menu:list')")
    public Result<List<SysMenu>> roleTree(@PathVariable(value = "roleId") String roleId) {
        return Result.success(sysMenuServiceImpl.roleTree(roleId));
    }
}
