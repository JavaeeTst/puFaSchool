package com.pufaschool.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pufaschool.conn.domain.PuFaCourseWare;

import javax.servlet.http.HttpServletRequest;
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
    List<PuFaCourseWare> getCourseWareAll();

    //按课件类型查询课件
    List<PuFaCourseWare> getCourseWareByType(Long type);

    //按上传时间查询课件
    List<PuFaCourseWare> getCourseWreByUploadTime(String uploadTime);

    //按课件路径查询课件,用于删除垃圾课件(不对外暴露)
    PuFaCourseWare  getCourseWareByCourseWarePath(String courseWarePath);

    //批量下载课件
    List<String> getCourseWareByCourseWareIds(Long[] ids, HttpServletRequest request);

    //单个课件查询
    PuFaCourseWare getCourseWareById(Long id);

    //单个课件下载
    String getCourseWareUrlById(Long id,HttpServletRequest request);

    //按id查询被删除的课件
    PuFaCourseWare getCourseWareByDeleteId(Long deleteId);

    //查询所有被删除的课件
    List<PuFaCourseWare> getCourseWareDeleteList();

    //清理被删除的课件
    boolean removeCourseWareByIdList(Long[] ids);
}
