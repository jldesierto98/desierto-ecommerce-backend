package com.desierto.ecommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Value("${allowed.origins}")
    private String[] allowedOrigins;

    @Value("${spring.data.rest.base-path}")
    private String basePath;

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        System.out.println("BASE PATH : " + basePath + "/**");
        System.out.println("ALLOWED ORIGINS : " + allowedOrigins[0]);
        // Set up CORS mapping.
        registry.addMapping( "/**")
                .allowedOrigins(allowedOrigins);
    }


//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")  // Set to your specific origins in production
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
//    }
}
