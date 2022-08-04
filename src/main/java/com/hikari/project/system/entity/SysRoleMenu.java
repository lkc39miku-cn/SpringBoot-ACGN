package com.hikari.project.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * SysRoleMenu
 * @author lkc39miku_cn
 */  
@ApiModel(value="spring_cloud.sys_role_menu")
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(value = "handler")
public class SysRoleMenu implements Serializable {
    /**
    * 外键 角色
    */
    @ApiModelProperty(value="外键 角色")
    private String roleId;

    /**
    * 外键 菜单
    */
    @ApiModelProperty(value="外键 菜单")
    private String menuId;

    @Serial
    private static final long serialVersionUID = 1L;
}