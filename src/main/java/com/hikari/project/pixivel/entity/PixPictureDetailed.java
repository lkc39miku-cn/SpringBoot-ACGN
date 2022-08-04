package com.hikari.project.pixivel.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hikari.framework.annotation.AutoGenerateId;
import com.hikari.framework.annotation.IdAnnotation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * PixPictureDetailed
 *
 * @author lkc39miku_cn
 */
@ApiModel(value = "spring_cloud.pix_picture_detailed")
@Data
@Accessors(chain = true)
@AutoGenerateId(name = "id")
@JsonIgnoreProperties(value = "handler")
public class PixPictureDetailed implements Serializable {
    /**
     * 主键 雪花
     */
    @IdAnnotation
    @ApiModelProperty(value = "主键 雪花")
    private String id;

    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    private String name;

    /**
     * 路径
     */
    @ApiModelProperty(value = "路径")
    private String path;

    /**
     * 文件前缀名
     */
    @ApiModelProperty(value = "文件前缀名")
    private String prefix;

    /**
     * 文件后缀名
     */
    @ApiModelProperty(value = "文件后缀名")
    private String suffix;

    /**
     * 高度px
     */
    @ApiModelProperty(value = "高度px")
    private Integer height;

    /**
     * 宽度px
     */
    @ApiModelProperty(value = "宽度px")
    private Integer width;

    /**
     * 图片类型 0 插画 1 漫画 2 动图
     */
    @ApiModelProperty(value = "图片类型 0 插画 1 漫画 2 动图")
    private Integer type;

    /**
     * 纵横比 0 横图 1 纵图 2 正方形图
     */
    @ApiModelProperty(value = "纵横比 0 横图 1 纵图 2 正方形图")
    private Integer graphics;

    /**
     * 外键 制图工具
     */
    @ApiModelProperty(value = "外键 制图工具")
    private String mappingId;

    /**
     * 制图工具
     */
    @ApiModelProperty(value = "制图工具")
    private PixMapping pixMapping;

    @Serial
    private static final long serialVersionUID = 1L;
}