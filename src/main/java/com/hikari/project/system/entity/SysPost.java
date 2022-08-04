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
 * SysPost
 * @author lkc39miku_cn
 */  
@ApiModel(value="spring_cloud.sys_post")
@Data
@Accessors(chain = true)
@AutoGenerateId(name = "id")
@JsonIgnoreProperties(value = "handler")
public class SysPost implements Serializable {
    /**
    * 主键 雪花
    */
    @IdAnnotation
    @ApiModelProperty(value="主键 雪花")
    private String id;

    /**
    * 岗位编码
    */
    @ApiModelProperty(value="岗位编码")
    private String code;

    /**
    * 岗位名称
    */
    @ApiModelProperty(value="岗位名称")
    private String name;

    /**
    * 显示顺序
    */
    @ApiModelProperty(value="显示顺序")
    private Integer sort;

    /**
    * 状态 0正常 1停用
    */
    @ApiModelProperty(value="状态 0正常 1停用")
    private Integer status;

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
     * 创建者
     */
    @ApiModelProperty(value="创建者")
    private SysStaff createStaff;

    /**
     * 更新者
     */
    @ApiModelProperty(value="更新者")
    private SysStaff updateStaff;

    @Serial
    private static final long serialVersionUID = 1L;
}