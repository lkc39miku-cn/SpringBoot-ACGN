package com.hikari.project.login.entity;

import com.hikari.project.system.entity.SysStaff;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * LoginStaff
 *
 * @author lkc39miku_cn
 */
@Data
@Accessors(chain = true)
public class LoginStaff implements UserDetails {
    @Serial
    private static final long serialVersionUID = -5057616455640043583L;

    @ApiModelProperty(value = "用户")
    private String id;
    @ApiModelProperty(value = "部门")
    private String deptId;
    @ApiModelProperty(value = "token")
    private String token;
    @ApiModelProperty(value = "登录时间")
    private LocalDateTime loginTime;
    @ApiModelProperty(value = "过期时间")
    private LocalDateTime expireTime;
    @ApiModelProperty(value = "登录ip")
    private String ip;
    @ApiModelProperty(value = "登录地址")
    private String loginLocation;
    @ApiModelProperty(value = "浏览器")
    private String browser;
    @ApiModelProperty(value = "操作系统")
    private String os;
    private Set<String> permissions;

    @ApiModelProperty(value = "员工信息")
    private SysStaff sysStaff;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return sysStaff.getPassword();
    }

    @Override
    public String getUsername() {
        return sysStaff.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
