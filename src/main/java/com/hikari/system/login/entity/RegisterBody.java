package com.hikari.system.login.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * RegisterBody
 *
 * @author lkc39miku_cn
 */
@Data
@Accessors(chain = true)
@Api(value = "注册请求实体")
public class RegisterBody implements Serializable {

    @Serial
    private static final long serialVersionUID = 1088434430518975478L;
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
