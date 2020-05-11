package org.flow.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 权限配置
 *
 * @author hdj
 * @since 2019/1/22 10:12
 **/
@Configuration
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((req, res, e) -> res.sendError(HttpStatus.UNAUTHORIZED.value(), e == null ? "Unauthorized" : e.getMessage()))
                // 此处处理的是AbstractSecurityInterceptor.beforeInvocation抛出的异常   AbstractSecurityInterceptor.afterInvocation抛出的异常由全局异常处理器处理
                .accessDeniedHandler((req, res, e) -> {
                    e.printStackTrace();
                    res.sendError(HttpStatus.FORBIDDEN.value(), e.getMessage());
                });
        http.addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
