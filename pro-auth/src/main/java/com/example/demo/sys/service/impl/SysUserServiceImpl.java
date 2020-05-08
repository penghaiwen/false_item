package com.example.demo.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dto.PageDTO;
import com.example.demo.sys.entity.SysUser;
import com.example.demo.sys.mapper.SysUserMapper;
import com.example.demo.sys.service.ISysUserService;
import com.example.demo.sys.vo.UserPageVo;
import com.security.JwtUser;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl  extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Override
    public Page<UserPageVo> getUserPageVo(PageDTO dto, String userName) {
        Page<UserPageVo> page = dto.createPage();
        List<UserPageVo> list = baseMapper.getUserPageVo(page,userName);
        return page.setRecords(list);
    }

    @Override
    public JwtUser getUserByUsername(String userName) {
        SysUser user= baseMapper.getUserByUsername(userName);
        JwtUser jwtUser =new JwtUser();
        BeanUtils.copyProperties(user,jwtUser);
        return jwtUser;
    }
}
