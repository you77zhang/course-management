package com.hbut.config;

import com.hbut.sys.service.impl.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : zhangyf
 * @project: com.hbut.config
 * @date: 2023/6/26 11:38
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    FileServiceImpl fileService;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将匹配上/files/**虚拟路径的url映射到文件上传到服务器的目录，获取静态资源
        registry.addResourceHandler("/" + fileService.pathPattern + "/**")
                .addResourceLocations("file:" + fileService.filePath);
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}

