package com.universityW3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
//@ComponentScan({"com.universityW3.service"})
@EnableSwagger2
public class ApplicationW3Application {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationW3Application.class, args);
    }
}