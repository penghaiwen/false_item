package com.example.demo.sys.controller;

import com.example.demo.sys.vo.UserInfoVo;
import com.exception.RestBean;
import com.security.JwtUser;
import com.utils.SubjectUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/")
@RestController
@Api(tags = "登录")
public class LoginController {
    @PostMapping("login")
    public String login(){
     return "登录中";
    }


    @GetMapping("get/info")
    public RestBean getInfo(){
        UserInfoVo vo =new UserInfoVo();
        vo.setId(SubjectUtil.getId());
        vo.setUserName(SubjectUtil.getName());
        List<Long> roles= new ArrayList<>();
        roles.add(1L);
        roles.add(2L);
        vo.setRoles(roles);
        return RestBean.ok(vo);
    }

}
