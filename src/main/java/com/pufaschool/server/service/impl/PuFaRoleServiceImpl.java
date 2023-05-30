package com.pufaschool.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pufaschool.conn.exception.InsufficientAuthorityException;
import com.pufaschool.conn.utils.JWTUtils;
import com.pufaschool.conn.utils.LogUtil;
import com.pufaschool.conn.utils.RoleUtil;
import com.pufaschool.server.dao.PuFaRoleDao;
import com.pufaschool.conn.domain.PuFaRole;
import com.pufaschool.conn.domain.vo.SysUserRoleVo;
import com.pufaschool.conn.exception.RoleRepetitionException;
import com.pufaschool.server.dao.PuFaUserDao;
import com.pufaschool.server.service.PuFaRoleService;
import com.pufaschool.server.service.PuFaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class PuFaRoleServiceImpl extends ServiceImpl<PuFaRoleDao, PuFaRole> implements PuFaRoleService {


    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * user业务层对象
     */
    @Resource
    private PuFaUserDao puFaUserDao;

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

        //如果分配的是超级管理员抛异常(因为超级管理员只能有一个)
        PuFaRole roleByRoleCode = baseMapper.findRoleByRoleCode(String.valueOf(roleId));

        if("SUPER_ADMIN".equals(roleByRoleCode.getRoleCode())){

            throw new RoleRepetitionException("超级管理员只能有一个");
        }

        //如果已经有该角色信息不能重复添加
        for (PuFaRole puFaRole : byUsernameOrUserId) {

            if (getRoleByRoleId.equals(puFaRole)) {

                throw new RoleRepetitionException("该用户已经有该角色，请不要重复");
            }
        }

        boolean result = baseMapper.assignRole(roleId, userId);

        return result;
    }

    /**
     * 取消用户角色
     * @param vo
     * @return
     */
    @Override
    @Transactional
    public boolean updateByUserIdAndRoleId(SysUserRoleVo vo, HttpServletRequest request) {

        //取出用户id
        Long userId = (Long) JWTUtils.checkToken(request.getHeader("token")).get("userId");

        List<PuFaRole> roleCode = baseMapper.findByUsernameOrUserId(null, userId);

        //如果不是超级管理员则没有权限取消用户管理员身份(其实也没有啥意义,因为接口已经拦截了,防君子不防小人)
        if(!roleCode.contains(baseMapper.findRoleByRoleCode(RoleUtil.SUPER_ADMIN_CODE))){

            throw new InsufficientAuthorityException("抱歉,您没有权限取消该用户管理员的权限");
        }
        //之后取消用户管理员权限
        boolean deleteResult = baseMapper.modifyByUserIdAndRoleId(vo);


        //同时也把用户的最高角色名改成用户
        boolean updateResult = puFaUserDao.modifyUserHighestRole(RoleUtil.USER, vo.getRoleId());

        return deleteResult && updateResult;
    }

    /**
     * 查询所有角色
     * @return
     */
    @Override
    public List<PuFaRole> getRoleList() {

        List<PuFaRole> roleList = baseMapper.findRoleList();

        return roleList;
    }
}
