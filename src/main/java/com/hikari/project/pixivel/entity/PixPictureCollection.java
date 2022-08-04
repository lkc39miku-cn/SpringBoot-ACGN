package com.hikari.project.pixivel.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hikari.framework.annotation.AutoGenerateId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;


/**
 * PixPictureCollection
 * @author lkc39miku_cn
 */  
@ApiModel(value="spring_cloud.pix_picture_collection")
@Data
@Accessors(chain = true)
@AutoGenerateId(name = "id")
@JsonIgnoreProperties(value = "handler")
public class PixPictureCollection implements Serializable {
    /**
    * 主键 雪花
    */
    @ApiModelProperty(value="主键 雪花")
    private String id;

    /**
    * 标题
    */
    @ApiModelProperty(value="标题")
    private String title;

    /**
    * 图片说明
    */
    @ApiModelProperty(value="图片说明")
    private String info;

    /**
    * 外键 标签
    */
    @ApiModelProperty(value="外键 标签")
    private String tagId;

    /**
    * 获赞数量
    */
    @ApiModelProperty(value="获赞数量")
    private Integer praise;

    /**
    * 收藏数量
    */
    @ApiModelProperty(value="收藏数量")
    private Integer collect;

    /**
    * 浏览次数
    */
    @ApiModelProperty(value="浏览次数")
    private Integer browse;

    /**
    * 状态 0 显示 1 不显示
    */
    @ApiModelProperty(value="状态 0 显示 1 不显示")
    private Integer status;

    /**
    * 类型 0 正常作品 1 R-18 2 R-18G
    */
    @ApiModelProperty(value="类型 0 正常作品 1 R-18 2 R-18G")
    private Integer pictureType;

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
     * pixivel用户
     */
    @ApiModelProperty(value = "pixivel用户")
    private PixUser pixUser;

    /**
     * 图片集合
     */
    @ApiModelProperty(value = "图片集合")
    private List<PixPicture> pixPictureList;

    /**
     * 标签集合
     */
    @ApiModelProperty(value = "标签集合")
    private List<PixTag> tagList;

    /**
     * 图片文件信息
     */
    @ApiModelProperty(value = "图片文件信息")
    private List<MultipartFile> multipartFileList;

    /**
     * 图片类型
     */
    @ApiModelProperty(value = "图片类型")
    private Integer type;

    /**
     * 制图工具
     */
    @ApiModelProperty(value = "制图工具")
    private String mappingId;

    @Serial
    private static final long serialVersionUID = 1L;
}