package com.example.basespringbootproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class BaseSpringBootProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseSpringBootProjectApplication.class, args);
    }

}
