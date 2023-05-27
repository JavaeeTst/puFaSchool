package com.pufaschool.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pufaschool.conn.domain.PuFaActive;
import com.pufaschool.conn.domain.queryDomain.SysActiveAttributeVo;

import java.util.List;

/**
 * 普法活动业务层
 */
public interface PuFaActiveService extends IService<PuFaActive> {

    //添加活动
    boolean addActivity(PuFaActive active);

    //删除活动
    boolean deleteActivityById(Long id);

    //修改活动
    boolean updateActivityById(PuFaActive active);

    //按活动id查询活动
    PuFaActive getActivityById(Long id);

    //按活动实体属性查询活动
    List<PuFaActive> getActivityByActivityAttribute(SysActiveAttributeVo vo);
}
