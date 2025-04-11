package com.blws.side.config.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    private final CorsProperties corsProperties;

    public CorsConfig(CorsProperties corsProperties) {
        this.corsProperties = corsProperties;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(corsProperties.getAllowedOrigins().split(","))
                .allowedMethods(corsProperties.getAllowedMethods().split(","))
                .allowCredentials(corsProperties.isAllowCredentials());

        // Swagger 관련 엔드포인트 추가
        registry.addMapping("/v3/api-docs/**")
                .allowedOrigins(corsProperties.getAllowedOrigins().split(","))
                .allowedMethods("GET");

        registry.addMapping("/swagger-ui/**")
                .allowedOrigins(corsProperties.getAllowedOrigins().split(","))
                .allowedMethods("GET");

        registry.addMapping("/swagger-ui.html")
                .allowedOrigins(corsProperties.getAllowedOrigins().split(","))
                .allowedMethods("GET");
    }
}
