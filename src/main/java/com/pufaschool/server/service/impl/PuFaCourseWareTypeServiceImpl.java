package com.pufaschool.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pufaschool.conn.domain.PuFaCourseWareType;
import com.pufaschool.server.dao.PuFaCourseWareTypeDao;
import com.pufaschool.server.service.PuFaCourseWareTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuFaCourseWareTypeServiceImpl extends ServiceImpl<PuFaCourseWareTypeDao, PuFaCourseWareType> implements PuFaCourseWareTypeService {

    /**
     * 添加学习课件类型
     * @param wareType
     * @return
     */
    @Override
    public boolean addCourseWareType(PuFaCourseWareType wareType) {

        boolean result = this.save(wareType);

        return result;
    }

    /**
     * 删除学习课件类型(按id)
     * @param courseWareTypeId
     * @return
     */
    @Override
    public boolean deleteCourseWareType(Long courseWareTypeId) {

        boolean result = this.deleteCourseWareType(courseWareTypeId);

        return result;
    }

    /**
     * 修改学习课件类型
     * @param wareType
     * @return
     */
    @Override
    public boolean updateCourseWareType(PuFaCourseWareType wareType) {

        boolean result = this.updateById(wareType);

        return result;
    }

    /**
     * 查询所有的学习课件类型
     * @return
     */
    @Override
    public List<PuFaCourseWareType> getCourseWareTypeAll() {

        List<PuFaCourseWareType> list = this.list();

        return list;
    }
}
