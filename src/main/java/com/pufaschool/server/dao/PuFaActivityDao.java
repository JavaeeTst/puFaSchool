package com.pufaschool.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pufaschool.conn.domain.PuFaActive;
import com.pufaschool.conn.domain.queryDomain.SysActiveAttributeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 普法活动dao层
 */
public interface PuFaActivityDao extends BaseMapper<PuFaActive> {

    //按属性查询活动
    List<PuFaActive> findActiveByStatus(@Param("vo") SysActiveAttributeVo activeAttributeVo);
}
