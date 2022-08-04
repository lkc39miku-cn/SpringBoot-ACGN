package com.hikari.project.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * SysStaffPost
 * @author lkc39miku_cn
 */  
@ApiModel(value="spring_cloud.sys_staff_post")
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(value = "handler")
public class SysStaffPost implements Serializable {
    /**
    * 外键 员工
    */
    @ApiModelProperty(value="外键 员工")
    private String staffId;

    /**
    * 外键 岗位
    */
    @ApiModelProperty(value="外键 岗位")
    private String postId;

    @Serial
    private static final long serialVersionUID = 1L;
}