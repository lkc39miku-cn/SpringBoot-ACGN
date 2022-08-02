package com.hikari.system.pixivel.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hikari.framework.annotation.AutoGenerateId;
import com.hikari.system.user.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * PixUser
 * @author lkc39miku_cn
 */  
@ApiModel(value="spring_cloud.pix_user")
@Data
@Accessors(chain = true)
@AutoGenerateId(name = "id")
@JsonIgnoreProperties(value = "handler")
public class PixUser implements Serializable {
    /**
    * 主键 雪花
    */
    @ApiModelProperty(value="主键 雪花")
    private String id;

    /**
    * 昵称
    */
    @ApiModelProperty(value="昵称")
    private String nickname;

    /**
    * 头像
    */
    @ApiModelProperty(value="头像")
    private String avatar;

    /**
    * 背景
    */
    @ApiModelProperty(value="背景")
    private String background;

    /**
    * 自我介绍
    */
    @ApiModelProperty(value="自我介绍")
    private String description;


    /**
    * 个人网站
    */
    @ApiModelProperty(value="个人网站")
    private String website;

    /**
    * 外键 用户
    */
    @ApiModelProperty(value="外键 用户")
    private String userId;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private LocalDateTime createTime;

    /**
     * 用户
     */
    @ApiModelProperty(value="用户")
    private User user;

    @Serial
    private static final long serialVersionUID = 1L;
}