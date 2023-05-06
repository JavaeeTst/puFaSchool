package com.pufaschool.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pufaschool.conn.domain.PuFaCourseWareType;

import java.util.List;

/**
 * 课件类型service接口
 */
public interface PuFaCourseWareTypeService extends IService<PuFaCourseWareType> {

    //上传学习课件类型
    boolean addCourseWareType(PuFaCourseWareType wareType);

    //删除学习课件类型（按id）
    boolean deleteCourseWareType(Long courseWareTypeId);

    //修改学习课件类型(按id)
    boolean updateCourseWareType(PuFaCourseWareType wareType);

    //查询学习课件类型(全部)
    List<PuFaCourseWareType> getCourseWareTypeAll();
}
