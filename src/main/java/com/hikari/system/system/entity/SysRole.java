package com.hikari.system.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hikari.framework.annotation.AutoGenerateId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * SysRole
 *
 * @author lkc39miku_cn
 */
@ApiModel(value = "spring_cloud.sys_role")
@Data
@Accessors(chain = true)
@AutoGenerateId(name = "id")
@JsonIgnoreProperties(value = "handler")
public class SysRole implements Serializable {
    /**
     * 主键 雪花
     */
    @ApiModelProperty(value = "主键 雪花")
    private String id;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String name;

    /**
     * 角色权限字符串
     */
    @ApiModelProperty(value = "角色权限字符串")
    private String roleKey;

    /**
     * 显示顺序
     */
    @ApiModelProperty(value = "显示顺序")
    private Integer sort;

    /**
     * 菜单树选择项是否关联显示
     */
    @ApiModelProperty(value = "菜单树选择项是否关联显示")
    private boolean menuCheckStrictly;

    /**
     * 部门树选择项是否关联显示
     */
    @ApiModelProperty(value = "部门树选择项是否关联显示")
    private boolean deptCheckStrictly;

    /**
     * 角色状态 0正常 1停用
     */
    @ApiModelProperty(value = "角色状态 0正常 1停用")
    private Integer status;

    /**
     * 删除标志 0代表存在 1代表删除
     */
    @ApiModelProperty(value = "删除标志 0代表存在 1代表删除")
    private Integer deleteStatus;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    private String createStaffId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    @ApiModelProperty(value = "更新者")
    private String updateStaffId;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    private SysStaff createStaff;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private SysStaff updateStaff;

    @Serial
    private static final long serialVersionUID = 1L;
}