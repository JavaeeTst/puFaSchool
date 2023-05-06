package com.pufaschool.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pufaschool.conn.domain.PuFaCourseWare;

import java.util.Date;
import java.util.List;

/**
 * 课件业务层接口
 */
public interface PuFaCourseWareService extends IService<PuFaCourseWare> {


    //上传课件
    boolean addCourseWare(PuFaCourseWare puFaCourseWare);

    //删除课件
    boolean deleteCourseWare(Long id);

    //查询所有课件
    List<PuFaCourseWare> findCourseWareAll();

    //按课件类型查询课件
    List<PuFaCourseWare> findCourseWareByType(String type);

    //按上传时间查询课件
    List<PuFaCourseWare> findCourseWreByUploadTime(Date uploadTime);

    //按课件路径查询课件,用于删除垃圾课件
    PuFaCourseWare  findCourseWareByCourseWarePath(String courseWarePath);

    //批量下载课件
    List<String> getCourseWareByCourseWareIds(Long[] ids);

    //单个课件下载
    PuFaCourseWare getCourseWareById(Long id);
}
