package com.hikari.project.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * SysStaffRole
 * @author lkc39miku_cn
 */  
@ApiModel(value="spring_cloud.sys_staff_role")
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(value = "handler")
public class SysStaffRole implements Serializable {
    /**
    * 外键 员工
    */
    @ApiModelProperty(value="外键 员工")
    private String staffId;

    /**
    * 外键 角色
    */
    @ApiModelProperty(value="外键 角色")
    private String roleId;

    @Serial
    private static final long serialVersionUID = 1L;
}