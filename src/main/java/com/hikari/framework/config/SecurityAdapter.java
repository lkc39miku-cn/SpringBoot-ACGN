package com.hikari.framework.config;

import com.hikari.framework.security.handler.AccessDeniedHandlerImpl;
import com.hikari.framework.security.handler.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;

/**
 * Security
 * @EnableWebSecurity
 * @EnableGlobalMethodSecurity (prePostEnabled = true, proxyTargetClass = true)
 * @author lkc39miku_cn
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
@Configuration
public class SecurityAdapter extends WebSecurityConfigurerAdapter {
/*    @Resource
    private UserDetailsService userDetailsService;*/
    @Resource
    private CorsFilter corsFilter;
    @Resource
    private AuthenticationEntryPointImpl authenticationEntryPointImpl;
    @Resource
    private AccessDeniedHandlerImpl accessDeniedHandlerImpl;
//    @Resource
//    private LogoutSuccessHandler logoutSuccessHandler;
//    @Resource
//    private JwtAuthenticationToken jwtAuthenticationToken;

    /**
     * @return bcryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder () {
        return new BCryptPasswordEncoder();
    }

    /**
     * @return authenticationManager
     * @throws Exception exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * security 放行的路径
     * @param web security
     * @throws Exception exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // swagger路径
        web.ignoring()
                .antMatchers("/doc.html", "/webjars/**", "/img/**")
                .antMatchers("/image/**");
    }

    /**
     * security http 配置
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception if an error occurs
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 跨域配置
        http.csrf().disable();

        // 认证配置
//        http.exceptionHandling()
//                // 认证失败处理
//                .authenticationEntryPoint(authenticationEntryPoint)
//                // 权限拒绝处理
//                .accessDeniedHandler(accessDeniedHandler)
//                .and()
//                // session配置
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        // 放行的路径
//        http.authorizeRequests()
//                .antMatchers("/login", "/register").permitAll();
//
//        // 特定方法放行路径
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.GET, "").permitAll();
//
//        // swagger路径
//        http.authorizeRequests()
//                .antMatchers("").permitAll();
//
//        // 其他方法需要认证
//        http.authorizeRequests().anyRequest()
//                .authenticated()
//                .and()
//                // frame配置
//                .headers().frameOptions().disable();
//
//        // 登出配置
//        http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
//
//        // 认证前过滤器
//        http.addFilterBefore(jwtAuthenticationToken, UsernamePasswordAuthenticationFilter.class);
//        http.addFilterBefore(corsFilter, JwtAuthenticationToken.class);
//        http.addFilterBefore(corsFilter, LogoutFilter.class);
    }

    /**
     * security 认证配置
     * @param auth the {@link AuthenticationManagerBuilder} to use
     * @throws Exception if an error occurs
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // 使用自定义认证服务
//        auth.userDetailsService(userDetailsService)
//                // 使用bcrypt加密
//                .passwordEncoder(bCryptPasswordEncoder());
    }
}
