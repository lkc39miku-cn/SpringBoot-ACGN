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
 * SysStaff
 * @author lkc39miku_cn
 */  
@ApiModel(value="spring_cloud.sys_staff")
@Data
@Accessors(chain = true)
@AutoGenerateId(name = "id")
@JsonIgnoreProperties(value = "handler")
public class SysStaff implements Serializable {
    /**
    * 主键雪花
    */
    @ApiModelProperty(value="主键雪花")
    private String id;

    /**
    * 外键 部门
    */
    @ApiModelProperty(value="外键 部门")
    private String deptId;

    /**
    * 员工账号
    */
    @ApiModelProperty(value="员工账号")
    private String username;

    /**
    * 员工昵称
    */
    @ApiModelProperty(value="员工昵称")
    private String nickname;

    /**
    * 员工邮箱
    */
    @ApiModelProperty(value="员工邮箱")
    private String email;

    /**
    * 手机号码
    */
    @ApiModelProperty(value="手机号码")
    private String phone;

    /**
    * 员工性别 0 男 1 女 2 未知
    */
    @ApiModelProperty(value="员工性别 0 男 1 女 2 未知")
    private Integer sex;

    /**
    * 头像地址
    */
    @ApiModelProperty(value="头像地址")
    private String avatar;

    /**
    * 密码
    */
    @ApiModelProperty(value="密码")
    private String password;

    /**
    * 帐号状态 0正常 1停用
    */
    @ApiModelProperty(value="帐号状态 0正常 1停用")
    private Integer status;

    /**
    * 删除标志 0代表存在 1代表删除
    */
    @ApiModelProperty(value="删除标志 0代表存在 1代表删除")
    private Integer deleteStatus;

    /**
    * 最后登录IP
    */
    @ApiModelProperty(value="最后登录IP")
    private String endLoginIp;

    /**
    * 最后登录时间
    */
    @ApiModelProperty(value="最后登录时间")
    private LocalDateTime endLoginTime;

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
     * 更新人
     */
    @ApiModelProperty(value="更新人")
    private SysStaff updateStaff;

    /**
     * 所在部门
     */
    @ApiModelProperty(value = "所在部门")
    private SysDept sysDept;

    @Serial
    private static final long serialVersionUID = 1L;
}