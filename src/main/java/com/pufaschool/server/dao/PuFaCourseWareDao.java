package com.pufaschool.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pufaschool.conn.domain.PuFaCourseWare;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 普法课件dao层
 */
public interface PuFaCourseWareDao extends BaseMapper<PuFaCourseWare> {

    //批量下载课件
    List<String> downloadCourseWareByIdList(@Param("ids") Long[] ids);

    //下载课件
    String downloadCourseWareById(@Param("id") Long id);


}
