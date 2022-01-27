package com.zlt.shop.config;

import com.alibaba.fastjson.JSON;
import com.zlt.shop.service.MyUserDetailsService;
import com.zlt.shop.service.impl.UserServiceImpl;
import com.zlt.shop.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.PrintWriter;


/**
 * SpringSecurity的配置类
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //自定义的用户处理service
    @Autowired
    MyUserDetailsService service;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //暂时只设定index为需要验证的url
        http.authorizeRequests()
                .antMatchers("/user/index").authenticated()
                .anyRequest().permitAll()



                //设置登录路径
                .and().formLogin().loginProcessingUrl("/login")
                //登录失败
                .failureHandler((request, response, authentication) -> {
                    PrintWriter out = response.getWriter();

                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(401);
                    String content = JSON.toJSONString(new ResultVo("401", "密码错误"));
                    out.write(content);
                    out.close();
                    throw new BadCredentialsException("密码错误");
                })


                //登录成功
                .successHandler((request, response, authentication) -> {
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json;charset=utf-8");
                    String content = JSON.toJSONString(new ResultVo("200", "登录成功"));
                    out.write(content);
                    out.close();
                })


                .and().csrf().disable();

        http.exceptionHandling().accessDeniedHandler((request, response, authException) -> {
            PrintWriter out = response.getWriter();

            String content = JSON.toJSONString(new ResultVo("401", "您还没有登录哦"));
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(401);
            out.write(content);
            out.close();
        });
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service)
                //测试用
                .passwordEncoder(passwordEncoder());
    }
}
