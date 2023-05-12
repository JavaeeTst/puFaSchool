package com.pufaschool.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pufaschool.conn.domain.PuFaUser;
import com.pufaschool.conn.domain.vo.EmailVo;
import com.pufaschool.conn.domain.vo.SysUserUpdatePasswordVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户业务层
 */
public interface PuFaUserService extends IService<PuFaUser> {

//    //用户注册方法
//    boolean addUser(PuFaUser puFaUser,String code);


    //删除用户方法
    boolean deleteByUserId(Long id);

//
//    //用户修改密码
//    boolean updatePassword(SysUserUpdatePasswordVo sysUserVo);

    //按状态查询用户
    List<PuFaUser> getUserByStatus(Integer status);

    //分页查询用户
    IPage<PuFaUser> getPageList(Page<PuFaUser> page);

    //用户模糊查询
    List<PuFaUser> getByUserFiled(SysUserUpdatePasswordVo vo);

//    //验证码验证
//    void sendEmail(String email,String code);

//    //用户找回密码
//    boolean passwordRetrieval(String email,String code,@Param("vo")SysUserUpdatePasswordVo vo);

    //按邮箱查询
    Object getUserByEmail(String email);

    //按时间查询用户
    List<PuFaUser> getUserByCreateTime(String date);

    //按id查询用户
    PuFaUser getUserById(Long id);

    //按id查询用户(用户自己)
    PuFaUser getUserByUserId(HttpServletRequest request);

    //获取所有用户
    List<PuFaUser> getUserAll();

    //获取所有管理员
    List<PuFaUser> getAdminAll();


    //按头路径获取用户(清理图片专用)
    PuFaUser getUserByAvatar(String avatar);

    //冻结(启用)用户
    boolean updateUserStatus(Integer status,Long userId);

    //用户登录
    PuFaUser login(String username,String password);

    //用户(管理员)默认头像
    List<String> getUserAndAdminDefaultAvatar(String role);

    //按用户名查询用户
    PuFaUser getUserByUsername(String username);

    //查询被删除的用户
    List<PuFaUser> getDeleteUser();

    //用户邮箱修改
    boolean updateUserEmailByUserId(EmailVo vo);

}
