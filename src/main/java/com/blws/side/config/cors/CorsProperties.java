package com.blws.side.config.cors;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.cors")
public class CorsProperties {
    private String allowedOrigins;
    private String allowedMethods;
    private boolean allowCredentials;
}
