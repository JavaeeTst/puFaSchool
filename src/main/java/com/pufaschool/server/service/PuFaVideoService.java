package com.pufaschool.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pufaschool.conn.domain.PuFaVideo;

import java.util.List;

/**
 * 普法视频业务层
 */
public interface PuFaVideoService extends IService<PuFaVideo> {

    //查询用户分页列表
    IPage<PuFaVideo> getPageList(Page<PuFaVideo> page);

    //视频上传
    boolean addPuFaVideo(PuFaVideo puFaVideo);

    //删除视频
    boolean deleteVideoById(Long id);

    //修改视频
    boolean updateVideoById(PuFaVideo video);

    //按id查询视频进行数据回显
    PuFaVideo getVideoById(Long id);

    //获取所有的视频(包括删除的)
    List<PuFaVideo> getVideoAll();

    //按视频路径获取视频(用于清理视频垃圾视频)
    PuFaVideo getVideoByVideoPath(String path);

    //按视频封面查找视频(用于清理垃圾视频)
    PuFaVideo getVideoByVideoCover(String videoCover);

    //视频浏览量+1每次点击视频发送请求
    void videoPageViews(Long id);
}
