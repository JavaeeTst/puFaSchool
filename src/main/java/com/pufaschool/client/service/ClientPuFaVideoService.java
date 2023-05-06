package com.pufaschool.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pufaschool.conn.domain.PuFaVideo;

import java.util.List;

/**
 * 用户获取视频业务层接口
 */
public interface ClientPuFaVideoService extends IService<PuFaVideo> {

    //获取全部视频
    List<PuFaVideo> getVideoList();

    //模糊查询视频
    List<PuFaVideo> getVideoByKey(String key);

    //用户点击视频查询视频信息
    PuFaVideo getVideoByVideoId(Long videoId);
}
