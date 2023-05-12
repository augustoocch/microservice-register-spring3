package com.intralink.user.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan({"com.intralink.user.micro.repository"})
public class ApplicationIntraLinkUserMicro {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationIntraLinkUserMicro.class, args);
    }
}