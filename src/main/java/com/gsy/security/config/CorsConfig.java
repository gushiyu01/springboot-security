package com.gsy.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping(
                "/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                //.exposedHeaders("Access-Control-Expose-Headers: Content-Disposition") // 允许axios 能够看到的header
                //.exposedHeaders("Content-Disposition")
                .maxAge(3600);
    }
}
