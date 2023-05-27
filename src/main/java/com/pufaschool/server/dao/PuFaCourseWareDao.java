package com.pufaschool.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pufaschool.conn.domain.PuFaCourseWare;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * 普法课件dao层
 */
public interface PuFaCourseWareDao extends BaseMapper<PuFaCourseWare> {

    //批量下载课件
    List<String> downloadCourseWareByIdList(@Param("ids") Long[] ids);

    //下载课件
    String downloadCourseWareById(@Param("id") Long id);

    //根据课件类型查询课件
    List<PuFaCourseWare> findCourseWareByCourseWareType(@Param("courseWareTypeId") Long courseWareTypeId);

    //根据id查询被删除的课件
    PuFaCourseWare findCourseWareByDeleteId(@Param("deleteId")Long deleteId);

    //查询所有被删除的课件
    List<PuFaCourseWare> findCourseWareDeleteList();

    //清空被删除的课件
    boolean deleteCourseWareByIdList(Long[] ids);

    //查询课件数量（被删除或者未删除）
    Integer findCourseWareNum(@Param("isDelete") Integer isDelete);



}