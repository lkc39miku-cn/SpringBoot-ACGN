package com.hikari.framework.config;

import com.hikari.framework.security.filter.JwtAuthenticationToken;
import com.hikari.framework.security.handler.AccessDeniedHandlerImpl;
import com.hikari.framework.security.handler.AuthenticationEntryPointImpl;
import com.hikari.framework.security.handler.LogoutSuccessHandlerImpl;
import com.hikari.project.login.service.impl.StaffDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Resource
    private StaffDetailsServiceImpl staffDetailsServiceImpl;
    @Resource
    private CorsFilter corsFilter;
    @Resource
    private AuthenticationEntryPointImpl authenticationEntryPointImpl;
    @Resource
    private AccessDeniedHandlerImpl accessDeniedHandlerImpl;
    @Resource
    private LogoutSuccessHandlerImpl logoutSuccessHandlerImpl;
    @Resource
    private JwtAuthenticationToken jwtAuthenticationToken;

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
     * security ???????????????
     * @param web security
     * @throws Exception exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // swagger??????
        web.ignoring()
                .antMatchers("/doc.html", "/webjars/**", "/img/**")
                .antMatchers("/image/**");
    }

    /**
     * security http ??????
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception if an error occurs
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // ????????????
        http.cors().and().csrf().disable();

        // ????????????
//        http.exceptionHandling()
//                // ??????????????????
//                .authenticationEntryPoint(authenticationEntryPoint)
//                // ??????????????????
//                .accessDeniedHandler(accessDeniedHandler)
//                .and()
//                // session??????
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        // ???????????????
//        http.authorizeRequests()
//                .antMatchers("/login", "/register").permitAll();
//
//        // ????????????????????????
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.GET, "").permitAll();
//
//        // swagger??????
//        http.authorizeRequests()
//                .antMatchers("").permitAll();
//
//        // ????????????????????????
//        http.authorizeRequests().anyRequest()
//                .authenticated()
//                .and()
//                // frame??????
//                .headers().frameOptions().disable();
//
//        // ????????????
//        http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
//
//        // ??????????????????
//        http.addFilterBefore(jwtAuthenticationToken, UsernamePasswordAuthenticationFilter.class);
//        http.addFilterBefore(corsFilter, JwtAuthenticationToken.class);
//        http.addFilterBefore(corsFilter, LogoutFilter.class);
    }

    /**
     * security ????????????
     * @param auth the {@link AuthenticationManagerBuilder} to use
     * @throws Exception if an error occurs
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // ???????????????????????????
        auth.userDetailsService(staffDetailsServiceImpl)
                // ??????bcrypt??????
                .passwordEncoder(bCryptPasswordEncoder());
    }
}
