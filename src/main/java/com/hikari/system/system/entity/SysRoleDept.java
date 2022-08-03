package com.hikari.system.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hikari.framework.annotation.AutoGenerateId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * SysRoleDept
 * @author lkc39miku_cn
 */  
@ApiModel(value="spring_cloud.sys_role_dept")
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(value = "handler")
public class SysRoleDept implements Serializable {
    /**
    * 外键 角色
    */
    @ApiModelProperty(value="外键 角色")
    private String roleId;

    /**
    * 外键 部门
    */
    @ApiModelProperty(value="外键 部门")
    private String deptId;

    @Serial
    private static final long serialVersionUID = 1L;
}