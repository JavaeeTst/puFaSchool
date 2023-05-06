package com.pufaschool.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pufaschool.conn.domain.PuFaCarousel;

import java.util.List;

/**
 * 普法轮播图业务层
 */
public interface PuFsCarouselService extends IService<PuFaCarousel> {

    //添加轮播图
    boolean addCarousel(PuFaCarousel carousel);

    //按状态查询轮播图
    IPage<PuFaCarousel> getCarouselByStatus(Page<PuFaCarousel> page, Integer status);

    //删除轮播图
    boolean deleteById(Long id);

    //查询轮播图列表
    IPage<PuFaCarousel> getPageList(Page<PuFaCarousel> page);

    //修改轮播图
    boolean updateCarouselById(PuFaCarousel carousel);

    //按id查询轮播图
    PuFaCarousel getCarouselById(Long id);

    //按创建时间查找
    List<PuFaCarousel> getCarouselByCreateTime(String date);

    //查询所有的轮播图(包括删除的)
    List<PuFaCarousel> getCarouselAll();

    //按图片路径查找(删除垃圾图片用的)
    PuFaCarousel getCarouselByPicturePath(String path);
}
