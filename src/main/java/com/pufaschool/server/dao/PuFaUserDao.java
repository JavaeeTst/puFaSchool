package com.pufaschool.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pufaschool.conn.domain.PuFaUser;
import com.pufaschool.conn.domain.vo.EmailVo;
import com.pufaschool.conn.domain.vo.SysUserAttributeVo;
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


}
