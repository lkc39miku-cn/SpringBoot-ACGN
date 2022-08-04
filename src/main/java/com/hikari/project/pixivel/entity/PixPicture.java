package com.hikari.project.pixivel.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hikari.framework.annotation.AutoGenerateId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * PixPicture
 *
 * @author lkc39miku_cn
 */
@ApiModel(value = "spring_cloud.pix_picture")
@Data
@Accessors(chain = true)
@AutoGenerateId(name = "id")
@JsonIgnoreProperties(value = "handler")
public class PixPicture implements Serializable {
    /**
     * 主键 雪花
     */
    @ApiModelProperty(value = "主键 雪花")
    private String id;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**
     * 外键 图片集合
     */
    @ApiModelProperty(value = "外键 图片集合")
    private String pictureCollectionId;

    /**
     * 外键 图片详情
     */
    @ApiModelProperty(value = "外键 图片详情")
    private String pictureDetailedId;

    /**
     * 图片详情
     */
    @ApiModelProperty(value = "图片详情")
    private PixPictureDetailed pixPictureDetailed;

    @Serial
    private static final long serialVersionUID = 1L;
}