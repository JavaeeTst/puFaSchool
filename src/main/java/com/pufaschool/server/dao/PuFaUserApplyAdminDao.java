package com.pufaschool.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pufaschool.conn.domain.PuFaUserApplyAdmin;
import com.pufaschool.conn.domain.vo.SysUserApplyAdminVo;
import org.apache.ibatis.annotations.Param;

/**
 * 用户申请管理员dao层
 */
public interface PuFaUserApplyAdminDao extends BaseMapper<PuFaUserApplyAdmin> {

    //用户申请管理员填写
    boolean insertUserApplyAdmin(@Param("vo") SysUserApplyAdminVo vo);

    //管理员同意用户的申请
    boolean modifyUserApplyAdminReviewsStatusById(@Param("status")Integer status,@Param("id")Long id);
}
