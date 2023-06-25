package com.hbut.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;

@Configuration
public class MyCorsConfig implements WebMvcConfigurer {
    //跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//项目中的所有接口都支持跨域
                .allowedOriginPatterns("*")//所有地址都可以访问,也可以篇日志具体地址
                .allowCredentials(true)
                .allowedMethods("*")        //"GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS" 
                .maxAge(3600);
    }
    // @Bean
    // public CorsFilter corsFilter(){
    //     CorsConfiguration configuration = new CorsConfiguration();
    //     configuration.addAllowedOrigin("http://localhost:8888");
    //     configuration.setAllowCredentials(true);
    //     configuration.setAllowedMethods(Collections.singletonList("*"));
    //     configuration.setAllowedHeaders(Collections.singletonList("*"));
    //
    //     UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
    //     urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",configuration);
    //
    //     CorsFilter corsFilter = new CorsFilter(urlBasedCorsConfigurationSource);
    //     return corsFilter;
    //
    // }
}
