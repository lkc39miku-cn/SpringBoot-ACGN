package com.hikari.system.pixivel.entity.vo;

import com.hikari.system.pixivel.entity.PixPictureCollection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * PixUserCollectVo
 *
 * @author lkc39miku_cn
 */
@ApiModel(value="spring_cloud.pix_user_collect.vo")
@Data
@Accessors(chain = true)
public class PixUserCollectVo implements Serializable {
    @Serial
    private static final long serialVersionUID = -8355386554186203483L;
    /**
     * 封装用户收藏图片信息
     * String 收藏标签
     * List<PixPictureCollection> 图片合集
     */
    @ApiModelProperty(value = "封装用户收藏图片信息")
    private List<PixPictureCollection> list;
}
