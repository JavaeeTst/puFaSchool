package com.pufaschool.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pufaschool.client.dao.ClientPuFaVideoDao;
import com.pufaschool.client.service.ClientPuFaVideoService;
import com.pufaschool.conn.domain.PuFaVideo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户视频业务层
 */
@Service
public class ClientPuFaVideoServiceImpl extends ServiceImpl<ClientPuFaVideoDao, PuFaVideo> implements ClientPuFaVideoService {


    /**
     * 用户获取所有的视频
     * @return
     */
    @Override
    public List<PuFaVideo> getVideoList() {

        List<PuFaVideo> puFaVideos = baseMapper.selectList(null);


        return puFaVideos;
    }

    /**
     *用户模糊查询视频
     * @param key
     * @return
     */
    @Override
    public List<PuFaVideo> getVideoByKey(String key) {

        List<PuFaVideo> videoByKey = baseMapper.findVideoByKey(key);


        return videoByKey;
    }

    /**
     * 用户点击视频按id查询视频
     * @param videoId
     * @return
     */
    @Override
    public PuFaVideo getVideoByVideoId(Long videoId) {

        PuFaVideo byId = this.getById(videoId);

        return byId;
    }
}
