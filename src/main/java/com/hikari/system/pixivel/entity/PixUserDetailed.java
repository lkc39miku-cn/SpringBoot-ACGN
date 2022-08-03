package com.hikari.system.pixivel.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hikari.framework.annotation.AutoGenerateId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * PixUserDetailed
 *
 * @author lkc39miku_cn
 */
@ApiModel(value = "spring_cloud.pix_user_detailed")
@Data
@Accessors(chain = true)
@AutoGenerateId(name = "id")
@JsonIgnoreProperties(value = "handler")
public class PixUserDetailed implements Serializable {
    /**
     * 主键 雪花
     */
    @ApiModelProperty(value = "主键 雪花")
    private String id;

    /**
     * 性别 0 男 1 女 2 其他
     */
    @ApiModelProperty(value = "性别 0 男 1 女 2 其他")
    private Integer sex;

    /**
     * 外键 国家
     */
    @ApiModelProperty(value = "外键 国家")
    private String countryId;

    /**
     * 出生年份
     */
    @ApiModelProperty(value = "出生年份")
    private LocalDateTime birthdayYear;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    private LocalDateTime birthdayOther;

    /**
     * 外键 职业
     */
    @ApiModelProperty(value = "外键 职业")
    private String professionId;

    /**
     * 外键 pixivel用户详情
     */
    @ApiModelProperty(value = "外键 pixivel用户详情")
    private String userDetailedShowId;

    /**
     * pixivel用户详情
     */
    @ApiModelProperty(value = "pixivel用户详情")
    private PixUserDetailedShow pixUserDetailedShow;

    @Serial
    private static final long serialVersionUID = 1L;
}