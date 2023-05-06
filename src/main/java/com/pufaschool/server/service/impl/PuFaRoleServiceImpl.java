package com.pufaschool.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pufaschool.server.dao.PuFaRoleDao;
import com.pufaschool.conn.domain.PuFaRole;
import com.pufaschool.conn.domain.vo.SysUserRoleVo;
import com.pufaschool.conn.exception.RoleRepetitionException;
import com.pufaschool.server.service.PuFaRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuFaRoleServiceImpl extends ServiceImpl<PuFaRoleDao, PuFaRole> implements PuFaRoleService {

    /**
     * 按用户名获取角色信息
     *
     * @param username
     * @return
     */
    @Override
    public List<PuFaRole> getRoleByUsernameOrUserId(String username, Long userId) {

        return baseMapper.findByUsernameOrUserId(username, userId);
    }

    /**
     * 给用户分配角色
     *
     * @param roleId
     * @param userId
     * @return
     */
    @Override
    public boolean assignRole(Long roleId, Long userId) {

        //分配角色前先查询用户有啥角色
        List<PuFaRole> byUsernameOrUserId = baseMapper.findByUsernameOrUserId(null, userId);

        //在按角色id查询角色
        PuFaRole getRoleByRoleId = baseMapper.selectById(roleId);


        for (PuFaRole puFaRole : byUsernameOrUserId) {

            if (getRoleByRoleId.equals(puFaRole)) {

                throw new RoleRepetitionException("该用户已经有该角色，请不要重复");
            }
        }

        boolean result = baseMapper.assignRole(roleId, userId);

        return result;
    }

    /**
     * 更新用户角色(取消和解封)
     *
     * @param vo
     * @return
     */
    @Override
    public boolean updateByUserIdAndRoleId(SysUserRoleVo vo) {

        boolean result = false;

        if (vo != null) {

            result = baseMapper.modifyByUserIdAndRoleId(vo);

        }

        return result;
    }
}
