package com.pufaschool.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pufaschool.conn.domain.PuFaUser;
import com.pufaschool.conn.domain.vo.EmailVo;
import com.pufaschool.conn.domain.queryDomain.SysUserAttributeVo;
import com.pufaschool.conn.domain.vo.SysUserInfoVo;
import com.pufaschool.conn.domain.vo.SysUserUpdatePasswordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 普法用户dao层
 */
public interface PuFaUserDao extends BaseMapper<PuFaUser> {

    //用户修改密码
    boolean modifyPassword(SysUserUpdatePasswordVo sysUserUpdatePasswordVo);

    //用户查询(禁用，启用)
    List<PuFaUser> findUserByStatus(Integer status);

    //分页查询用户
    IPage<PuFaUser> findPageList(Page<PuFaUser> page);

    //模糊查询用户
    List<PuFaUser> findByUserFiled(@Param("vo") SysUserUpdatePasswordVo vo);

    //用户找回密码
    boolean passwordRetrieval(@Param("email") String email, String code, @Param("vo") SysUserUpdatePasswordVo vo);

    //按创建时间查找用户
    List<PuFaUser> findUserByCreateTime(@Param("date") String date);

    //获取全部用户
    List<PuFaUser> findUserAll();

    //图片查询用户(清理图片专用)
    PuFaUser findUserByAvatar(@Param("avatar") String avatar);

    //超级管理员查询所有的管理员
    List<PuFaUser> findAdminAll();

    //冻结(启用)用户
    boolean modifyUserStatus(@Param("status") Integer status,@Param("userId") Long userId);

    //用户登录
    PuFaUser findUserByUsernameAndPassword(@Param("username")String username,@Param("password")String password);

    //查询被删除的用户
    List<PuFaUser> findDeleteUser();

    //用户邮箱修改
    boolean modifyUserEmailByUserId(@Param("vo") EmailVo vo);

    //按用户信息查询用户(创建时间，状态，用户名等)
    List<PuFaUser> findUserByUserAttribute(@Param("vo")SysUserAttributeVo vo);

    //修改用户最高角色名称(不对外暴露接口,用于用户变成管理员之后就执行这条sql)
    boolean modifyUserHighestRole(@Param("highestRole") String highestRole,@Param("id") Long id);

    //查询超级管理员信息(只允许在项目中私有,不对外暴露)
    PuFaUser findUserByUserRole(@Param("role")String role);

    //记录所有用户的积分
    Double findTotalIntegrationAllUser();

    //查询管理员数量
    Integer findAdminNum();

    //查询用户量
    Long findUserNum(@Param("isDelete") Integer isDelete);

    //查询用户积分和用户名(积分大于300的不对外暴露接口只用于业务)
    List<SysUserInfoVo> findUserByIntegrate();



}
