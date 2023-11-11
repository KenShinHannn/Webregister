package com.example.webregister.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secretKey;
    private Integer tokenInterval;
    private Integer jwtRefreshExpirationMs;
    private String abc;
}
