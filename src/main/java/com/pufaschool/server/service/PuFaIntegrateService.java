package com.pufaschool.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pufaschool.conn.domain.PuFaIntegration;

import javax.servlet.http.HttpServletRequest;

/**
 * 积分表service层
 */
public interface PuFaIntegrateService extends IService<PuFaIntegration> {

    //给用户添加积分
    boolean addIntegrateByUser(PuFaIntegration puFaIntegration, HttpServletRequest request);



}
