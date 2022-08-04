package com.hikari.project.pixivel.entity;

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
 * PixUserPrimary
 * @author lkc39miku_cn
 */  
@ApiModel(value="spring_cloud.pix_user_primary")
@Data
@Accessors(chain = true)
@AutoGenerateId(name = "id")
@JsonIgnoreProperties(value = "handler")
public class PixUserPrimary implements Serializable {
    /**
    * 主键 雪花
    */
    @IdAnnotation
    @ApiModelProperty(value="主键 雪花")
    private String id;

    /**
    * 0 非会员 1 会员
    */
    @ApiModelProperty(value="0 非会员 1 会员")
    private Integer primary;

    /**
    * 外键 pixivel用户
    */
    @ApiModelProperty(value="外键 pixivel用户")
    private String pixUserId;

    /**
    * 开通时间
    */
    @ApiModelProperty(value="开通时间")
    private LocalDateTime startTime;

    /**
    * 结束时间
    */
    @ApiModelProperty(value="结束时间")
    private LocalDateTime endTime;

    /**
     * pixivel用户
     */
    @ApiModelProperty(value = "pixivel用户")
    private PixUser pixUser;

    @Serial
    private static final long serialVersionUID = 1L;
}