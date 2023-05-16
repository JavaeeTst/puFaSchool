package com.pufaschool.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pufaschool.conn.domain.PuFaUserApplyAdmin;
import com.pufaschool.conn.domain.vo.SysUserApplyAdminVo;

import java.util.List;

/**
 * 用户申请管理员业务层
 */
public interface PuFaUserApplyAdminService extends IService<PuFaUserApplyAdmin> {

    //用户申请管理员填写
    boolean requestAdmin(PuFaUserApplyAdmin applyAdmin);

    //超级管理员查看所有用户的申请
    List<PuFaUserApplyAdmin> getUserApplyAdminAll();

    //超级管理按申请id查询用户的申请
    PuFaUserApplyAdmin getUserApplyAdminByRequestId(Long id);

    //用户查询自己的管理员申请
    List<PuFaUserApplyAdmin> getUserApplyAdminByUserId(Long userId);

    //管理员同意用户的申请
    boolean modifyUserApplyAdminReviewsStatusById(Integer status,Long id,Long userId);
}
