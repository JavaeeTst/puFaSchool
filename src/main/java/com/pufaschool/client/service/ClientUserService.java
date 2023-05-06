package com.pufaschool.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pufaschool.conn.domain.PuFaUser;
import com.pufaschool.conn.domain.vo.SysUserUpdatePasswordVo;
import org.apache.ibatis.annotations.Param;

public interface ClientUserService extends IService<PuFaUser> {

    //用户修改密码
    boolean updatePassword(SysUserUpdatePasswordVo sysUserUpdatePasswordVo);

    //验证码验证
    void sendEmail(String email, String code);

    //用户注册方法
    boolean addUser(PuFaUser puFaUser, String code);

    //用户找回密码
    boolean passwordRetrieval(String email, String code, @Param("vo") SysUserUpdatePasswordVo vo);

    //修改用户方法
    boolean updateByUserId(PuFaUser puFaUser);



}
