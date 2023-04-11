package com.zys.store.config;

import com.github.pagehelper.PageHelper;
import com.zys.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Properties;

@Configuration
public class LoginInterceptorConfigure implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //construct a new interceptor
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        ArrayList<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/user/register");
        patterns.add("/user/login");
        patterns.add("/user/**");
        patterns.add("/districts/**");
        patterns.add("/products/**");
        patterns.add("/kaptcha/**");



        registry.addInterceptor(loginInterceptor)//add login interceptor
                .addPathPatterns("/**") //all the path
                .excludePathPatterns(patterns);// exclude path

    }

    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("dialect", "mysql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }

}
