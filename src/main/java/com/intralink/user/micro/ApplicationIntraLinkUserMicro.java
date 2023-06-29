package com.intralink.user.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableJpaRepositories(basePackages ="com.intralink.user.micro.repository")
@ComponentScan(basePackages={"com.intralink.user.micro.config", "com.intralink.user.micro.controller",
        "com.intralink.user.micro.model","com.intralink.user.micro.repository", "com.intralink.user.micro.security",
        "com.intralink.user.micro.security.control", "com.intralink.user.micro.service" })
public class ApplicationIntraLinkUserMicro {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationIntraLinkUserMicro.class, args);
    }
}