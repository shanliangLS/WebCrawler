package com.get.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
        // 缓存位置
        final String cachePath = Global.outPath + Global.cachePath;
        // 头像位置
        final String profilePicturesPath = Global.outPath + Global.profilePicturesPath;
        // 背景图片位置
        final String backgroundPicturesPath = Global.outPath + Global.backgroundPicturesPath;

        registry.addResourceHandler("/cache/**").addResourceLocations("file:" + cachePath);
        registry.addResourceHandler("/profilePictures/**").addResourceLocations("file:" + profilePicturesPath);
        registry.addResourceHandler("/backgroundPictures/**").addResourceLocations("file:" + backgroundPicturesPath);
    }

}
