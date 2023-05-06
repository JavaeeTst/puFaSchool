package com.pufaschool.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pufaschool.conn.domain.PuFaIntegration;

/**
 * 积分表dao层
 */
public interface PuFaIntegrateDao extends BaseMapper<PuFaIntegration> {

    //按用户id和学习类型id以及学习类型查询积分表
    PuFaIntegration findIntegrationByUserIdAndScoreIdAndScoreType(Long userId,Long scoreId,Long scoreType);

    //更新用户的积分
    boolean modifyUserIntegration(Double integration,Long userId);
}
