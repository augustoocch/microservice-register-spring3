package com.intralink.user.micro;

import com.intralink.user.micro.config.SecurityConfig;
import com.intralink.user.micro.repository.UserRepository;
import com.intralink.user.micro.security.JwtTokenFilter;
import com.intralink.user.micro.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
//@ComponentScan(basePackageClasses = {SecurityConfig.class, UserService.class, JwtTokenFilter.class})
//{"com.intralink.user.micro.config", "com.intralink.user.micro.repository"})
@ComponentScan(basePackages={"com.intralink.user.micro.config", "com.intralink.user.micro.controller", "com.intralink.user.micro.model","com.intralink.user.micro.repository", "com.intralink.user.micro.security","com.intralink.user.micro.security.control", "com.intralink.user.micro.service" })
//@ComponentScan({"com.intralink.user.micro.repository"})
public class ApplicationIntraLinkUserMicro {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationIntraLinkUserMicro.class, args);
    }
}