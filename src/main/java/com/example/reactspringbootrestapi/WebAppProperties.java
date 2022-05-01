package com.example.reactspringbootrestapi;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@EnableConfigurationProperties(value = {WebAppProperties.class})
@ConfigurationProperties(prefix = "dev")
@PropertySource("application.properties")
public class WebAppProperties {
    private String db_driver_class;
    private String db_url;
    private String db_user;
    private String db_pwd;
}
