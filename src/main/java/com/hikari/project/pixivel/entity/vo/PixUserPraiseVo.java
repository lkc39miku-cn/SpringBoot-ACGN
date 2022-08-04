package com.hikari.project.pixivel.entity.vo;

import com.hikari.project.pixivel.entity.PixPictureCollection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * PixUserPraiseVo
 *
 * @author lkc39miku_cn
 */
@ApiModel(value="spring_cloud.pix_user_praise.vo")
@Data
@Accessors(chain = true)
public class PixUserPraiseVo implements Serializable {
    @Serial
    private static final long serialVersionUID = -2369770332507652854L;

    /**
     * 封装用户点赞图片信息
     * List<PixUserCollect> 图片合集
     */
    @ApiModelProperty(value = "封装用户点赞图片信息")
    private List<PixPictureCollection> list;
}
