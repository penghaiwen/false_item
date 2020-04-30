package com.example.domo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@SpringBootApplication(scanBasePackages = {"com.**"})
public class AppRun {

    public static void main(String[] args) {
         SpringApplication.run(AppRun.class, args);
    }

}
