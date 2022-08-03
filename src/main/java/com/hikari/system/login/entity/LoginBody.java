package com.hikari.system.login.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * LoginBody
 *
 * @author lkc39miku_cn
 */
@Data
@Accessors(chain = true)
@Api(value = "登录请求实体")
public class LoginBody implements Serializable {
    @Serial
    private static final long serialVersionUID = -7623400592077405528L;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码")
    private String code;
    @ApiModelProperty(value = "验证请求id")
    private String uuid;
}
