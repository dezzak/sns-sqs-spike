package com.sainsburys.psr.spike;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@SuppressWarnings("HideUtilityClassConstructor")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
