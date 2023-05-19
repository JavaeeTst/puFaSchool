package com.pufaschool.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pufaschool.conn.domain.PuFaRole;
import com.pufaschool.conn.domain.vo.SysUserRoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 普法角色dao层
 */
public interface PuFaRoleDao extends BaseMapper<PuFaRole> {

    //按名称或者用户id查询用户的角色(内部使用不对外暴露)
    List<PuFaRole> findByUsernameOrUserId(@Param("username") String username, @Param("userId") Long userId);

    //给用户分配角色
    boolean assignRole(@Param("roleId") Long roleId, @Param("userId") Long userId);

    //取消用户角色
    boolean modifyByUserIdAndRoleId(@Param("vo") SysUserRoleVo vo);

    //使用角色编码查询角色(项目内部私有不对外暴露)
    PuFaRole findRoleByRoleCode(@Param("roleCode") String roleCode);

    //查询所有角色
    List<PuFaRole> findRoleList();

}

