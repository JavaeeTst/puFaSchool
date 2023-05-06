package com.pufaschool.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pufaschool.conn.domain.PuFaCarousel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 普法轮播图dao层
 */
public interface PuFaCarouselDao extends BaseMapper<PuFaCarousel> {

    //查询所有的轮播图
    IPage<PuFaCarousel> findPageList(Page<PuFaCarousel> page);

    //按状态查询轮播图
    IPage<PuFaCarousel> findCarouselByStatus(Page<PuFaCarousel> page, @Param("status") Integer status);

    //按创建时间查询
    List<PuFaCarousel> findCarouselByCreateTime(@Param("date") String date);

    //查询所有的轮播图包括删除的
    List<PuFaCarousel> findCarouselAll();

    //按图片路径查找(用于删除垃圾图片)
    PuFaCarousel findCarouselByPicturePath(@Param("path") String path);


}
