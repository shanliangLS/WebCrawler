package com.get.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //不需要登录就可以查看的,
//        String[] excludes = new String[]{"/login", "/static/**", "/"};
//        String[] tests = new String[]{};
//        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns(excludes).excludePathPatterns(tests);
//        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/cache/**").addResourceLocations("file:" + Global.downloadPath);
    }

}
