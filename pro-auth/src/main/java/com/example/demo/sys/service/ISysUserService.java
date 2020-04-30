package com.example.demo.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.sys.entity.SysUser;
import com.security.JwtUser;


public interface ISysUserService extends IService<SysUser> {
    Page<SysUser> getPageUser();
    
    
    /**
     * @Author 老默
     * @Description 
     * @Date 2020/4/29 10:59
     * @param: userName
     * @return com.security.JwtUser
     **/
    JwtUser getUserByUsername(String userName);
}
