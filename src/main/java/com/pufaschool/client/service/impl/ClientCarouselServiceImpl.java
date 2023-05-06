package com.pufaschool.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pufaschool.client.dao.ClientCarouselDao;
import com.pufaschool.client.service.ClientCarouselService;
import com.pufaschool.conn.domain.PuFaCarousel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientCarouselServiceImpl extends ServiceImpl<ClientCarouselDao, PuFaCarousel> implements ClientCarouselService {

    /**
     * 用户查看所有轮播图
     *
     * @return
     */
    @Override
    public List<PuFaCarousel> getCarouselList() {

        List<PuFaCarousel> puFaCarousels = baseMapper.selectList(null);


        return puFaCarousels;
    }
}
