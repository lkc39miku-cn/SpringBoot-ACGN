package com.hikari.project.monitor.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.Accessors;


/**
 * SysLoginOs
 *
 * @author lkc39miku_cn
 */
@ApiModel(value = "spring_cloud.sys_login_os")
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(value = "handler")
public class SysLoginOs implements Serializable {
    /**
     * 访问员工
     */
    @ApiModelProperty(value = "访问员工")
    private String id;

    /**
     * 用户账号
     */
    @ApiModelProperty(value = "用户账号")
    private String username;

    /**
     * 登录IP地址
     */
    @ApiModelProperty(value = "登录IP地址")
    private String ip;

    /**
     * 登录地点
     */
    @ApiModelProperty(value = "登录地点")
    private String loginLocation;

    /**
     * 浏览器类型
     */
    @ApiModelProperty(value = "浏览器类型")
    private String browser;

    /**
     * 操作系统
     */
    @ApiModelProperty(value = "操作系统")
    private String os;

    /**
     * 登录状态 0成功 1失败
     */
    @ApiModelProperty(value = "登录状态 0成功 1失败")
    private Integer status;

    /**
     * 提示消息
     */
    @ApiModelProperty(value = "提示消息")
    private String msg;

    /**
     * 访问时间
     */
    @ApiModelProperty(value = "访问时间")
    private LocalDateTime loginTime;

    @Serial
    private static final long serialVersionUID = 1L;
}