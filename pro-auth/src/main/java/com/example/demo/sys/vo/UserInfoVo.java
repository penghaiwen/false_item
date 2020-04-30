package com.example.demo.sys.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserInfoVo {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("账号")
    private String userName;

    @ApiModelProperty("权限")
    private List<Long> roles;
}
