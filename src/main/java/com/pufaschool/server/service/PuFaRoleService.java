package com.pufaschool.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pufaschool.conn.domain.PuFaRole;
import com.pufaschool.conn.domain.vo.SysUserRoleVo;

import java.util.List;

/**
 * 普法角色业务类
 */
public interface PuFaRoleService extends IService<PuFaRole> {


    //按用户名称或者用户id(任意一个)查询用户的角色
    List<PuFaRole> getRoleByUsernameOrUserId(String username, Long userId);

    //给用户分配管理员角色
    boolean assignRole(Long roleId, Long userId);

    //取消用户管理员角色
    boolean updateByUserIdAndRoleId(SysUserRoleVo vo);
}
