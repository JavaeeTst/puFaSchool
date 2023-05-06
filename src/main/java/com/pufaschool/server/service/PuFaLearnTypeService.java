package com.pufaschool.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pufaschool.conn.domain.PuFaLearnType;

import java.util.List;

/**
 * 学习类型service层
 */
public interface PuFaLearnTypeService extends IService<PuFaLearnType> {

    //添加学习类型
    boolean addLearnType(PuFaLearnType learnType);

    //修改学习类型
    boolean updateLearnByLearnId(PuFaLearnType learnType);

    //删除学习类型
    boolean deleteLearnTypeByLearnId(Long id);

    //查询所有学习类型
    List<PuFaLearnType> getLearnTypeAll();

    //按创建人查询学习类型
    List<PuFaLearnType> getLearnTypeByFounder(String founder);

    //按id查询
    PuFaLearnType getLearnTypeByLearnTypeId(Long id);
}
