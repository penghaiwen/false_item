package com.example.demo.sys.controller;

import com.example.demo.sys.vo.UserInfoVo;
import com.exception.RestBean;
import com.security.JwtUser;
import com.utils.SubjectUtil;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/")
@RestController
@Api(tags = "登录")
@AllArgsConstructor
public class LoginController {

//    @Resource
//    private InMemoryTokenStore tokenStore;

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




    /**
     * 退出并删除token
     *
     * @param authHeader Authorization
     */
    @DeleteMapping("oauth/user/logout")
    public RestBean logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
        if (StringUtils.isBlank(authHeader)) {
            return RestBean.error("退出失败，token 为空");
        }
        String tokenValue = authHeader.replace(OAuth2AccessToken.BEARER_TYPE.toLowerCase(), StringUtils.EMPTY).trim();

          //OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(tokenValue);




        return RestBean.ok("退出成功");
    }




}
