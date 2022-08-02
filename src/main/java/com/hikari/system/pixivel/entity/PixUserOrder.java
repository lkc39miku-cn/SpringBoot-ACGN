package com.hikari.system.pixivel.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hikari.framework.annotation.AutoGenerateId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * PixUserOrder
 * @author lkc39miku_cn
 */  
@ApiModel(value="spring_cloud.pix_user_order")
@Data
@Accessors(chain = true)
@AutoGenerateId(name = "id")
@JsonIgnoreProperties(value = "handler")
public class PixUserOrder implements Serializable {
    /**
    * 主键 雪花
    */
    @ApiModelProperty(value="主键 雪花")
    private String id;

    /**
    * 订单编号
    */
    @ApiModelProperty(value="订单编号")
    private String orderNumber;

    /**
    * 价格
    */
    @ApiModelProperty(value="价格")
    private BigDecimal price;

    /**
    * 购买说明
    */
    @ApiModelProperty(value="购买说明")
    private String context;

    /**
    * 0 订单进行中 1 订单完成 2 订单取消 3 订单超时
    */
    @ApiModelProperty(value="0 订单进行中 1 订单完成 2 订单取消 3 订单超时")
    private Integer status;

    /**
    * 外键 pixivel用户
    */
    @ApiModelProperty(value="外键 pixivel用户")
    private String pixUserId;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private LocalDateTime createTime;

    /**
    * 支付 / 取消时间
    */
    @ApiModelProperty(value="支付 / 取消时间")
    private LocalDateTime updateTime;

    /**
     * pixivel用户
     */
    @ApiModelProperty(value = "pixivel用户")
    private PixUser pixUser;

    @Serial
    private static final long serialVersionUID = 1L;
}