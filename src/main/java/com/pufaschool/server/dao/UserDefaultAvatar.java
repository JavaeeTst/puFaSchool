package com.pufaschool.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pufaschool.conn.domain.SysUserDefaultAvatar;

import java.util.List;

/**
 * 用户默认头像dao层
 */
public interface UserDefaultAvatar  extends BaseMapper<SysUserDefaultAvatar> {

    //查询用户的默认头像
    List<SysUserDefaultAvatar> getUserDefaultAvatar();

    //查询管理员默认头像
    List<SysUserDefaultAvatar> getAdminDefaultAvatar();
}
