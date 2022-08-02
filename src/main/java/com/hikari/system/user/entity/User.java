package com.hikari.system.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * User
 * @author lkc39miku_cn
 */  
@ApiModel(value="spring_cloud.`user`")
@Data
public class User implements Serializable {
    /**
    * 主键 雪花
    */
    @ApiModelProperty(value="主键 雪花")
    private String id;

    /**
    * 用户名
    */
    @ApiModelProperty(value="用户名")
    private String username;

    /**
    * 密码
    */
    @ApiModelProperty(value="密码")
    private String password;

    /**
    * 邮箱
    */
    @ApiModelProperty(value="邮箱")
    private String email;

    /**
    * 手机号
    */
    @ApiModelProperty(value="手机号")
    private String phone;

    /**
    * 状态 0 正常 1 禁用
    */
    @ApiModelProperty(value="状态 0 正常 1 禁用")
    private Integer status;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    @Serial
    private static final long serialVersionUID = 1L;
}