package com.pufaschool.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pufaschool.conn.domain.PuFaCourseWare;
import com.pufaschool.conn.domain.PuFaCourseWareType;
import com.pufaschool.conn.exception.DeleteException;
import com.pufaschool.server.dao.PuFaCourseWareTypeDao;
import com.pufaschool.server.service.PuFaCourseWareService;
import com.pufaschool.server.service.PuFaCourseWareTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PuFaCourseWareTypeServiceImpl extends ServiceImpl<PuFaCourseWareTypeDao, PuFaCourseWareType> implements PuFaCourseWareTypeService {


    @Resource
    private PuFaCourseWareService wareService;

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

        //删除之前先看看该类型有没有绑定的课件数据
        List<PuFaCourseWare> courseWareByType = wareService.getCourseWareByType(courseWareTypeId);

        //如果课件类型绑定其他课件抛异常
        if(courseWareByType.size()>0){

            throw new DeleteException("该课件类型已经绑定其他课件，无法删除");
        }

        //程序走到这里没有问题
        boolean result = this.removeById(courseWareTypeId);

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
