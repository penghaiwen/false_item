package com.example.demo.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dto.PageDTO;
import com.example.demo.sys.entity.SysUser;
import com.example.demo.sys.vo.UserPageVo;
import com.security.JwtUser;


public interface ISysUserService extends IService<SysUser> {


    /**
     * @Author 老默
     * @Description
     * @param dto
     * @param userName
     * @return
     */
    Page<UserPageVo> getUserPageVo(PageDTO dto,String userName);
    
    
    /**
     * @Author 老默
     * @Description 
     * @Date 2020/4/29 10:59
     * @param: userName
     * @return com.security.JwtUser
     **/
    JwtUser getUserByUsername(String userName);
}
