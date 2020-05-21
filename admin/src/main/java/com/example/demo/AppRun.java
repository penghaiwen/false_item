package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@SpringBootApplication(scanBasePackages = {"com.**"})
@EnableScheduling
public class AppRun {

    public static void main(String[] args) {
         SpringApplication.run(AppRun.class, args);
    }

}
