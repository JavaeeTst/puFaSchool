package com.pufaschool.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pufaschool.conn.domain.PuFaCarousel;
import com.pufaschool.conn.domain.PuFaUser;

import java.util.List;

public interface ClientCarouselService extends IService<PuFaCarousel> {

    //用户获取界面轮播图
    List<PuFaCarousel> getCarouselList();


}
