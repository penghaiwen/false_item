package com.example.demo.sys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dto.PageDTO;
import com.example.demo.sys.dto.UserSaveDto;
import com.example.demo.sys.entity.SysUser;
import com.example.demo.sys.mapper.SysUserMapper;
import com.example.demo.sys.service.ISysUserService;
import com.example.demo.sys.vo.UserInfoVo;
import com.example.demo.sys.vo.UserPageVo;
import com.exception.RestException;
import com.security.JwtUser;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl  extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {


    @Override
    public JwtUser getUserByUsername(String userName) {
        SysUser user= baseMapper.getUserByUsername(userName);
        if (user.getStatus()!=1){
            throw new RestException("该账号异常，请联系管理员");
        }
        JwtUser jwtUser =new JwtUser();
        BeanUtils.copyProperties(user,jwtUser);
        jwtUser.setUsername(user.getAccount());
        return jwtUser;
    }

    @Override
    public Page<UserPageVo> getUserPageVo(PageDTO dto, String userName,String account) {
        Page<UserPageVo> page = dto.createPage();
        List<UserPageVo> list = baseMapper.getUserPageVo(page,userName,account);
        return page.setRecords(list);
    }

    @Override
    public boolean saveUser(UserSaveDto dto) {
        SysUser sysUser= baseMapper.getUserByUsername(dto.getAccount());
        if (sysUser!=null && sysUser.getStatus()!=1){
            throw new RestException("该账号异已存在");
        }
        SysUser user = new SysUser();
        BeanUtils.copyProperties(dto,user);
        if(dto.getId()==null){
            user.setPassword(new BCryptPasswordEncoder().encode("123456"));
        }
        boolean b = this.saveOrUpdate(user);
        // 删除该用户拥有的所有角色
        baseMapper.deleteAllUserRoleByUserId(user.getId());
        if(null!=dto.getRoleIds() && !dto.getRoleIds().isEmpty()){
            // 为该用户新增拥有的角色
            baseMapper.saveBatchUserRole(user.getId(),dto.getRoleIds());
        }
        return b;
    }

    @Override
    public UserInfoVo getUserInfoByUserId(Long id) {
        return baseMapper.getUserInfoByUserId(id);
    }
}
