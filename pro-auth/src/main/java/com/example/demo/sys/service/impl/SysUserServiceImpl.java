package com.example.demo.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.sys.entity.SysUser;
import com.example.demo.sys.mapper.SysUserMapper;
import com.example.demo.sys.service.ISysUserService;
import com.security.JwtUser;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl  extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Override
    public Page<SysUser> getPageUser() {
        Page<SysUser> page=new Page<>(1,10);
        List<SysUser> list = baseMapper.selectList(new QueryWrapper<>());
        page.setRecords(list);
        return page;
    }

    @Override
    public JwtUser getUserByUsername(String userName) {
        SysUser user= baseMapper.getUserByUsername(userName);
        JwtUser jwtUser =new JwtUser();
        BeanUtils.copyProperties(user,jwtUser);
        return jwtUser;
    }
}
