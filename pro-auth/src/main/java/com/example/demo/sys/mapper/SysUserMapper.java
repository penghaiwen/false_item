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
    List<UserPageVo> getUserPageVo(Page<UserPageVo> page,@Param("userName") String userName,@Param("account") String account);

    /**
     *功能描述 删除用户所有拥有的角色
     * @author 老默
     * @date 2020/5/9
     * @time 9:23
     * @param userId
     * @return void
     */
    void deleteAllUserRoleByUserId(@Param("userId")Long userId);


    /**
     *功能描述 保存用户拥有的角色
     * @author 老默
     * @date 2020/5/9
     * @time 9:38
     * @param userId
     * @param roleIds
     * @return void
     */
    void saveBatchUserRole(@Param("userId")Long userId,@Param("list")List<Long> roleIds);
}
