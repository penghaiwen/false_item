package com.example.demo.sys.controller;

import com.security.JwtUser;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
@Api(tags = "登录")
public class LoginController {
    @PostMapping("login")
    public String login(){
     return "登录中";
    }


    @GetMapping("get/info")
    public JwtUser getInfo(){
        JwtUser a =new JwtUser();
        return a;
    }

}
