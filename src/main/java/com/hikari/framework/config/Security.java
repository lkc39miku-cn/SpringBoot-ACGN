package com.hikari.framework.config;

import com.hikari.framework.security.handler.AccessDeniedHandlerImpl;
import com.hikari.framework.security.handler.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;

/**
 * Security
 * spring security 弃用 WebSecurityConfigurerAdapter，改用 WebSecurityConfigurer。
 * @Configuration
 * @EnableWebSecurity
 * @EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
 * @author lkc39miku_cn
 */
@Deprecated
public class Security {

//    @Resource
//    private UserDetailsService userDetailsService;
//    @Resource
//    private JwtAuthenticationToken jwtAuthenticationToken;
//    @Resource
//    private AuthenticationEntryPointImpl authenticationEntryPointImpl;
//    @Resource
//    private AccessDeniedHandlerImpl accessDeniedHandlerImpl;
//    @Resource
//    private LogoutSuccessHandler logoutSuccessHandler;
//    @Resource
//    private CorsFilter corsFilter;

//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http.csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint(authenticationEntryPoint)
//                .accessDeniedHandler(accessDeniedHandler)
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/login", "/register").permitAll()
//                .antMatchers("/upload", "/upload/batch").permitAll()
//                .antMatchers("/captcha/image").permitAll()
//                .and()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .headers().frameOptions().disable()
//                .and()
//                .logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler)
//                .and()
//                .addFilterBefore(jwtAuthenticationToken, UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(corsFilter, JwtAuthenticationToken.class)
//                .addFilterBefore(corsFilter, LogoutFilter.class)
//                .userDetailsService(userDetailsService)
//                .build();
//    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return web -> web.ignoring()
//                .antMatchers("/video/**");
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder () {
//        return new BCryptPasswordEncoder();
//    }
}
