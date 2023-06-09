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
    List<PuFaVideo> getVideoAllIsDelete();

    //按视频路径获取视频(用于清理视频垃圾视频)
    PuFaVideo getVideoByVideoPath(String path);

    //按视频封面查找视频(用于清理垃圾视频)
    PuFaVideo getVideoByVideoCover(String videoCover);

    //视频浏览量+1每次点击视频发送请求
    void videoPageViews(Long id);

    //模糊查询视频
    List<PuFaVideo> getVideoByVideoAttribute(String key);

    //查询被删除的视频
    List<PuFaVideo> getDeleteVideoList();

    //按id查询被删除的视频
    PuFaVideo getVideoByDeleteId(Long deleteId);

    //强制删除视频(清空被删除的视频)
    boolean removeVideoByIdList(Long[] ids);

    //查询所有视频(未删除的)
    List<PuFaVideo> getVideoAllNotDelete();

}
