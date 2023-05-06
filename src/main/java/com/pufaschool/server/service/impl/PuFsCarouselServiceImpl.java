package com.pufaschool.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pufaschool.server.dao.PuFaCarouselDao;
import com.pufaschool.conn.domain.PuFaCarousel;
import com.pufaschool.server.service.PuFsCarouselService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuFsCarouselServiceImpl extends ServiceImpl<PuFaCarouselDao, PuFaCarousel> implements PuFsCarouselService {


    /**
     * 添加轮播图
     *
     * @param carousel
     * @return
     */
    @Override
    public boolean addCarousel(PuFaCarousel carousel) {

        int insert = baseMapper.insert(carousel);

        return insert > 0;
    }

    /**
     * 按状态查询轮播图
     *
     * @param status
     * @return
     */
    @Override
    public IPage<PuFaCarousel> getCarouselByStatus(Page<PuFaCarousel> page, Integer status) {

        //如果传过来的状态为null,默认为1
        if (status == null) {
            status = 1;
        }

        IPage<PuFaCarousel> puFaCarouselPage = baseMapper.findCarouselByStatus(page, status);

        return puFaCarouselPage;
    }

    /**
     * 按id删除轮播图
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Long id) {

        int i = baseMapper.deleteById(id);

        return i > 0;
    }

    /**
     * 查询轮播图列表
     *
     * @param page
     * @return
     */
    @Override
    public IPage<PuFaCarousel> getPageList(Page<PuFaCarousel> page) {

        IPage<PuFaCarousel> pageList = baseMapper.findPageList(page);

        return page;
    }

    /**
     * 修改轮播图
     *
     * @param carousel
     * @return
     */
    @Override
    public boolean updateCarouselById(PuFaCarousel carousel) {

        int i = baseMapper.updateById(carousel);


        return i > 0;
    }

    /**
     * 按轮播图的id查询(主要用于数据回显)
     *
     * @param id
     * @return
     */
    @Override
    public PuFaCarousel getCarouselById(Long id) {

        PuFaCarousel puFaCarousel = baseMapper.selectById(id);


        return puFaCarousel;
    }

    @Override
    public List<PuFaCarousel> getCarouselByCreateTime(String date) {

        List<PuFaCarousel> carouselByCreateTime = baseMapper.findCarouselByCreateTime(date);

        return carouselByCreateTime;
    }

    /**
     * 查询所有的轮播图(包括删除的)
     *
     * @return
     */
    @Override
    public List<PuFaCarousel> getCarouselAll() {

        List<PuFaCarousel> carouselAll = baseMapper.findCarouselAll();

        return carouselAll;
    }

    /**
     * 按图片路径查找(用于清理垃圾图片)
     *
     * @param path
     * @return
     */
    @Override
    public PuFaCarousel getCarouselByPicturePath(String path) {

        PuFaCarousel carouselByPicturePath = baseMapper.findCarouselByPicturePath(path);

        return carouselByPicturePath;
    }
}
