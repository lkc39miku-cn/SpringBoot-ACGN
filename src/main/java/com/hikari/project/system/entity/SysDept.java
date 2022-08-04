package com.hikari.project.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hikari.framework.annotation.AutoGenerateId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;


/**
 * SysDept
 * @author lkc39miku_cn
 */  
@ApiModel(value="spring_cloud.sys_dept")
@Data
@Accessors(chain = true)
@AutoGenerateId(name = "id")
@JsonIgnoreProperties(value = "handler")
public class SysDept implements Serializable {
    /**
    * 主键 雪花
    */
    @ApiModelProperty(value="主键 雪花")
    private String id;

    /**
    * 父部门
    */
    @ApiModelProperty(value="父部门")
    private String parentId;

    /**
    * 祖级列表
    */
    @ApiModelProperty(value="祖级列表")
    private String ancestors;

    /**
    * 部门名称
    */
    @ApiModelProperty(value="部门名称")
    private String name;

    /**
    * 显示顺序
    */
    @ApiModelProperty(value="显示顺序")
    private Integer deptOrder;

    /**
    * 外键 负责人
    */
    @ApiModelProperty(value="外键 负责人")
    private String leaderId;

    /**
    * 联系电话
    */
    @ApiModelProperty(value="联系电话")
    private String phone;

    /**
    * 邮箱
    */
    @ApiModelProperty(value="邮箱")
    private String email;

    /**
    * 部门状态 0正常 1停用
    */
    @ApiModelProperty(value="部门状态 0正常 1停用")
    private Integer status;

    /**
    * 删除标志 0代表存在 1代表删除
    */
    @ApiModelProperty(value="删除标志 0代表存在 1代表删除")
    private Integer deleteStatus;

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
     * 创建人
     */
    @ApiModelProperty(value="创建人")
    private SysStaff createStaff;

    /**
     * 修改人
     */
    @ApiModelProperty(value="修改人")
    private SysStaff updateStaff;

    /**
     * 领导人
     */
    @ApiModelProperty(value = "领导人")
    private SysStaff leaderStaff;

    /**
     * 子部门
     */
    @ApiModelProperty(value = "子部门")
    private List<SysDept> children;

    /**
     * 选中状态
     */
    @ApiModelProperty(value = "选中状态")
    private boolean checked;

    @Serial
    private static final long serialVersionUID = 1L;
}