package com.hikari.system.system.entity.vo;

import com.hikari.system.system.entity.SysPost;
import com.hikari.system.system.entity.SysRole;
import com.hikari.system.system.entity.SysStaff;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * SysStaffVo
 *
 * @author lkc39miku_cn
 */
@ApiModel(value="spring_cloud.sys_staff.vo")
@Data
@Accessors(chain = true)
public class SysStaffVo {
    /**
     * 用户信息
     */
    @ApiModelProperty(value = "用户信息")
    private SysStaff sysStaff;


    private List<SysPost> postList;

    private List<SysRole> roleList;
}
