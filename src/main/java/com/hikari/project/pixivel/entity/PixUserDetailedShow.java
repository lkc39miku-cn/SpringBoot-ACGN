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
 * PixUserDetailedShow
 *
 * @author lkc39miku_cn
 */
@ApiModel(value = "spring_cloud.pix_user_detailed_show")
@Data
@Accessors(chain = true)
@AutoGenerateId(name = "id")
@JsonIgnoreProperties(value = "handler")
public class PixUserDetailedShow implements Serializable {
    /**
     * 主键 雪花
     */
    @IdAnnotation
    @ApiModelProperty(value = "主键 雪花")
    private String id;

    /**
     * 0 公开 1 关注可见 2 隐藏
     */
    @ApiModelProperty(value = "0 公开 1 关注可见 2 隐藏")
    private Integer sexShow;

    /**
     * 0 公开 1 关注可见 2 隐藏
     */
    @ApiModelProperty(value = "0 公开 1 关注可见 2 隐藏")
    private Integer countryShow;

    /**
     * 0 公开 1 关注可见 2 隐藏
     */
    @ApiModelProperty(value = "0 公开 1 关注可见 2 隐藏")
    private Integer birthdayYearShow;

    /**
     * 0 公开 1 关注可见 2 隐藏
     */
    @ApiModelProperty(value = "0 公开 1 关注可见 2 隐藏")
    private Integer birthdayOtherShow;

    /**
     * 0 公开 1 关注可见 2 隐藏
     */
    @ApiModelProperty(value = "0 公开 1 关注可见 2 隐藏")
    private Integer professionIdShow;

    /**
     * 0 公开 1 不公开
     */
    @ApiModelProperty(value = "0 公开 1 不公开")
    private Integer collectShow;

    @Serial
    private static final long serialVersionUID = 1L;
}