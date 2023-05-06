package com.pufaschool.client.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pufaschool.conn.domain.PuFaUser;
import com.pufaschool.conn.domain.vo.SysUserUpdatePasswordVo;
import org.apache.ibatis.annotations.Param;

/**
 * 客户端用户dao层
 */
public interface ClientUserDao extends BaseMapper<PuFaUser> {


    //用户修改密码
    boolean modifyPassword(SysUserUpdatePasswordVo sysUserUpdatePasswordVo);

    //用户找回密码
    boolean passwordRetrieval(@Param("email") String email, String code, @Param("vo") SysUserUpdatePasswordVo vo);


}
