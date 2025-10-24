package com.sudo.raillobatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class RailloBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(RailloBatchApplication.class, args);
    }

}
