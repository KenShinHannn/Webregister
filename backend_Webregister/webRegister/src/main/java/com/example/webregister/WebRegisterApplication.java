package com.example.webregister;

import com.example.webregister.properties.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({JwtProperties.class})
public class WebRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebRegisterApplication.class, args);
    }

}
