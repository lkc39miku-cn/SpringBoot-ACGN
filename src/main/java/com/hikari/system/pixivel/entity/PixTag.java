package com.hikari.system.pixivel.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hikari.framework.annotation.AutoGenerateId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * PixTag
 * @author lkc39miku_cn
 */  
@ApiModel(value="spring_cloud.pix_tag")
@Data
@Accessors(chain = true)
@AutoGenerateId(name = "id")
@JsonIgnoreProperties(value = "handler")
public class PixTag implements Serializable {
    /**
    * 主键 雪花
    */
    @ApiModelProperty(value="主键 雪花")
    private String id;

    /**
    * 名称
    */
    @ApiModelProperty(value="名称")
    private String name;

    /**
    * 别名
    */
    @ApiModelProperty(value="别名")
    private String otherName;

    /**
    * 标志图
    */
    @ApiModelProperty(value="标志图")
    private String image;

    @Serial
    private static final long serialVersionUID = 1L;
}