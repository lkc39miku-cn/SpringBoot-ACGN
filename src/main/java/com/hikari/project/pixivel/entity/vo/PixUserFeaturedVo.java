package com.hikari.project.pixivel.entity.vo;

import com.hikari.project.pixivel.entity.PixUserCollect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * PixUserFeaturedVo
 *
 * @author lkc39miku_cn
 */
@ApiModel(value="spring_cloud.pix_user_featured.vo")
@Data
@Accessors(chain = true)
public class PixUserFeaturedVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 6091912915072935332L;

    /**
     * 封装用户评选图片信息
     * List<PixUserCollect> 图片合集
     */
    @ApiModelProperty(value = "封装用户评选图片信息")
    private List<PixUserCollect> list;
}
