package com.hikari.project.pixivel.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hikari.framework.annotation.AutoGenerateId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.Accessors;


/**
 * PixUserFeatured
 * @author lkc39miku_cn
 */  
@ApiModel(value="spring_cloud.pix_user_featured")
@Data
@Accessors(chain = true)
@AutoGenerateId(name = "id")
@JsonIgnoreProperties(value = "handler")
public class PixUserFeatured implements Serializable {
    /**
    * 主键 雪花
    */
    @ApiModelProperty(value="主键 雪花")
    private String id;

    /**
    * 外键 pixivel用户
    */
    @ApiModelProperty(value="外键 pixivel用户")
    private String pixUserId;

    /**
    * 外键 图片合集
    */
    @ApiModelProperty(value="外键 图片合集")
    private String pictureCollectionId;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private LocalDateTime createTime;

    @Serial
    private static final long serialVersionUID = 1L;
}