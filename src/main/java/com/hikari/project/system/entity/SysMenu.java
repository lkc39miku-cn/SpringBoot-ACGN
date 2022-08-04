package com.hikari.project.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hikari.framework.annotation.AutoGenerateId;
import com.hikari.framework.annotation.IdAnnotation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;


/**
 * SysMenu
 * @author lkc39miku_cn
 */  
@ApiModel(value="spring_cloud.sys_menu")
@Data
@Accessors(chain = true)
@AutoGenerateId(name = "id")
@JsonIgnoreProperties(value = "handler")
public class SysMenu implements Serializable {
    /**
    * 主键 雪花
    */
    @IdAnnotation
    @ApiModelProperty(value="主键 雪花")
    private String id;

    /**
    * 菜单名称
    */
    @ApiModelProperty(value="菜单名称")
    private String name;

    /**
    * 父菜单ID
    */
    @ApiModelProperty(value="父菜单ID")
    private String parentId;

    /**
    * 显示顺序
    */
    @ApiModelProperty(value="显示顺序")
    private Integer menuOrder;

    /**
    * 路由地址
    */
    @ApiModelProperty(value="路由地址")
    private String path;

    /**
    * 组件路径
    */
    @ApiModelProperty(value="组件路径")
    private String component;

    /**
    * 路由参数
    */
    @ApiModelProperty(value="路由参数")
    private String query;

    /**
    * 是否为外链 0是 1否
    */
    @ApiModelProperty(value="是否为外链 0是 1否")
    private Integer isFrame;

    /**
    * 是否缓存 0缓存 1不缓存
    */
    @ApiModelProperty(value="是否缓存 0缓存 1不缓存")
    private Integer isCache;

    /**
    * 菜单类型 M目录 C菜单 F按钮
    */
    @ApiModelProperty(value="菜单类型 M目录 C菜单 F按钮")
    private String type;

    /**
    * 菜单状态 0显示 1隐藏
    */
    @ApiModelProperty(value="菜单状态 0显示 1隐藏")
    private Integer visible;

    /**
    * 菜单状态 0正常 1停用
    */
    @ApiModelProperty(value="菜单状态 0正常 1停用")
    private Integer status;

    /**
    * 权限标识
    */
    @ApiModelProperty(value="权限标识")
    private String perms;

    /**
    * 菜单图标
    */
    @ApiModelProperty(value="菜单图标")
    private String icon;

    /**
    * 创建者
    */
    @ApiModelProperty(value="创建者")
    private String createStaffId;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private LocalDateTime createTime;

    /**
    * 更新者
    */
    @ApiModelProperty(value="更新者")
    private String updateStaffId;

    /**
    * 更新时间
    */
    @ApiModelProperty(value="更新时间")
    private LocalDateTime updateTime;

    /**
    * 备注
    */
    @ApiModelProperty(value="备注")
    private String remark;

    /**
     * 添加人
     */
    @ApiModelProperty(value = "添加人")
    private SysStaff createStaff;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private SysStaff updateStaff;

    /**
     * 子菜单
     */
    @ApiModelProperty(value = "子菜单")
    private List<SysMenu> children;

    /**
     * 是否选中
     */
    @ApiModelProperty(value = "是否选中")
    private boolean checked;

    @Serial
    private static final long serialVersionUID = 1L;
}