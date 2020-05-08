package com.example.demo.sys.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.sys.entity.SysUser;
import com.example.demo.sys.vo.UserPageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface  SysUserMapper extends BaseMapper<SysUser> {

    /**
     * @Author 老默
     * @Description
     * @Date 2020/4/29 10:41
     * @param: userName
     * @return
     **/
    SysUser getUserByUsername(@Param("userName") String userName);


    /**
     *
     * @param page
     * @param userName
     * @return
     */
    List<UserPageVo> getUserPageVo(Page<UserPageVo> page,@Param("userName") String userName);
}
