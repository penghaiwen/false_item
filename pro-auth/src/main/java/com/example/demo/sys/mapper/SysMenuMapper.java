package com.example.demo.sys.mapper;

import com.example.demo.sys.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.sys.vo.MenuTreeVo;
import com.example.demo.sys.vo.RouterVO;

import java.util.List;

/**
 * <p>
 * 菜单管理 Mapper 接口
 * </p>
 *
 * @author 车资道科技
 * @since 2020-05-08
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     *功能描述
     * @author 老默
     * @date 2020/5/8
     * @time 9:53
     * @param
     * @return java.util.List<com.example.demo.sys.vo.MenuTreeVo>
     */
    List<MenuTreeVo> getAllMenu();


    /**
     *功能描述
     * @author 老默
     * @date 2020/5/8
     * @time 10:43
     * @param
     * @return java.util.List<com.example.demo.sys.vo.RouterVO>
     */
    List<RouterVO> getRouterTree();
}
