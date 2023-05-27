package com.pufaschool.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pufaschool.conn.domain.PuFaActive;
import com.pufaschool.conn.domain.queryDomain.SysActiveAttributeVo;
import com.pufaschool.server.dao.PuFaActivityDao;
import com.pufaschool.server.service.PuFaActiveService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 普法活动业务实现层
 */
@Service
public class PuFaActiveServiceImpl extends ServiceImpl<PuFaActivityDao, PuFaActive> implements PuFaActiveService {

    /**
     * 添加活动
     * @param active
     * @return
     */
    @Override
    public boolean addActivity(PuFaActive active) {

        boolean result = this.save(active);

        return result;
    }

    /**
     * 删除活动(按id)
     * @param id
     * @return
     */
    @Override
    public boolean deleteActivityById(Long id) {

        boolean result = this.removeById(id);

        return result;
    }

    /**
     * 修改活动
     * @param active
     * @return
     */
    @Override
    public boolean updateActivityById(PuFaActive active) {

        boolean result = this.updateById(active);

        return result;
    }

    /**
     * 按id获取活动信息
     * @param id
     * @return
     */
    @Override
    public PuFaActive getActivityById(Long id) {

        PuFaActive puFaActive = this.getById(id);

        return puFaActive;
    }

    /**
     * 按活动属性实体获取活动信息
     * @param vo
     * @return
     */
    @Override
    public List<PuFaActive> getActivityByActivityAttribute(SysActiveAttributeVo vo) {

        List<PuFaActive> activeByStatus = baseMapper.findActiveByStatus(vo);

        return activeByStatus;
    }
}
