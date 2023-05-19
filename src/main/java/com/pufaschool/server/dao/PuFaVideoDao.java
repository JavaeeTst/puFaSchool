package com.pufaschool.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pufaschool.conn.domain.PuFaVideo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 普法视频dao层
 */
public interface PuFaVideoDao extends BaseMapper<PuFaVideo> {

    //查询视频列表
    IPage<PuFaVideo> findPageList(Page<PuFaVideo> vo);

    //获取所有的视频
    List<PuFaVideo> findVideoAll();

    //按视频路径获取视频
    PuFaVideo findVideoByVideoPath(@Param("path") String path);

    //按视频封面查找视频信息(用于清理垃圾视频)
    PuFaVideo findVideoByVideoCover(@Param("videoCover")String videoCover);

    //视频浏览量增加(每点击一次发送一次请求+1)
    void videoPageView(@Param("id") Long id);

    //视频模糊查询
    List<PuFaVideo> findVideoByVideoAttribute(@Param("key") String key);

    //查询被删除的视频
    List<PuFaVideo> findDeleteVideoList();

    //按id查询被删除的视频
    PuFaVideo findVideoByDeleteId(@Param("deleteId")Long deleteId);

    //强制删除视频
    boolean deleteByVideoIdList(@Param("ids") Long[] ids);


}
