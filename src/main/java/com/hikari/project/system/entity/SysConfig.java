package com.hikari.project.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hikari.framework.annotation.AutoGenerateId;
import com.hikari.framework.annotation.IdAnnotation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.Accessors;


/**
 * SysConfig
 * @author lkc39miku_cn
 */  
@ApiModel(value="spring_cloud.sys_config")
@Data
@Accessors(chain = true)
@AutoGenerateId(name = "id")
@JsonIgnoreProperties(value = "handler")
public class SysConfig implements Serializable {
    /**
    * 主键 雪花
    */
    @IdAnnotation
    @ApiModelProperty(value="主键 雪花")
    private String id;

    /**
    * 参数名称
    */
    @ApiModelProperty(value="参数名称")
    private String name;

    /**
    * 参数键名
    */
    @ApiModelProperty(value="参数键名")
    private String configKey;

    /**
    * 参数键值
    */
    @ApiModelProperty(value="参数键值")
    private String configValue;

    /**
    * 系统内置 Y是 N否
    */
    @ApiModelProperty(value="系统内置 Y是 N否")
    private String type;

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

    @Serial
    private static final long serialVersionUID = 1L;
}