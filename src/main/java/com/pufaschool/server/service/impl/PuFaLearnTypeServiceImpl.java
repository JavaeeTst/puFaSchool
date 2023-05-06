package com.pufaschool.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pufaschool.conn.domain.PuFaLearnType;
import com.pufaschool.server.dao.PuFaLearnTypeDao;
import com.pufaschool.server.service.PuFaLearnTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学习类型业务层
 */
@Service
public class PuFaLearnTypeServiceImpl extends ServiceImpl<PuFaLearnTypeDao, PuFaLearnType> implements PuFaLearnTypeService {

    /**
     * 添加学习类型
     * @param learnType
     * @return
     */
    @Override
    public boolean addLearnType(PuFaLearnType learnType) {

        int result = baseMapper.insert(learnType);

        return result>0;
    }

    /**
     * 修改学习类型
     * @param learnType
     * @return
     */
    @Override
    public boolean updateLearnByLearnId(PuFaLearnType learnType) {

        int result = baseMapper.updateById(learnType);

        return result>0;
    }

    /**
     * 删除学习类型
     * @param id
     * @return
     */
    @Override
    public boolean deleteLearnTypeByLearnId(Long id) {

        int result = baseMapper.deleteById(id);

        return result>0;
    }

    /**
     * 查询学习类型
     * @return
     */
    @Override
    public List<PuFaLearnType> getLearnTypeAll() {

        List<PuFaLearnType> puFaLearnTypes = baseMapper.selectList(null);

        return puFaLearnTypes;
    }

    /**
     * 按上传人查找学习类型
     * @param founder
     * @return
     */
    @Override
    public List<PuFaLearnType> getLearnTypeByFounder(String founder) {

        LambdaQueryWrapper<PuFaLearnType> wrapper=new LambdaQueryWrapper<>();

        wrapper.eq(PuFaLearnType::getFounder,founder);

        List<PuFaLearnType> puFaLearnTypes = baseMapper.selectList(wrapper);

        return puFaLearnTypes;
    }

    /**
     * 按id查询学习类型
     * @param id
     * @return
     */
    @Override
    public PuFaLearnType getLearnTypeByLearnTypeId(Long id) {

        PuFaLearnType puFaLearnType = baseMapper.selectById(id);


        return puFaLearnType;
    }
}
