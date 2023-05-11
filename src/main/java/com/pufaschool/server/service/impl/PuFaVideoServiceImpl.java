package com.pufaschool.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pufaschool.conn.exception.FileFormatException;
import com.pufaschool.server.dao.PuFaVideoDao;
import com.pufaschool.conn.domain.PuFaVideo;
import com.pufaschool.conn.domain.PuFaVideoComment;
import com.pufaschool.server.service.PuFaVideoCommentService;
import com.pufaschool.server.service.PuFaVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class PuFaVideoServiceImpl extends ServiceImpl<PuFaVideoDao, PuFaVideo> implements PuFaVideoService {

    @Autowired
    private PuFaVideoCommentService commentService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询视频列表
     *
     * @param page
     * @return
     */
    @Override
    public IPage<PuFaVideo> getPageList(Page<PuFaVideo> page) {

        IPage<PuFaVideo> pageList = baseMapper.findPageList(page);

        return pageList;
    }

    /**
     * 普法视频添加
     *
     * @param puFaVideo
     * @return
     */
    @Override
    public boolean addPuFaVideo(PuFaVideo puFaVideo) {

        //先按视频封面路径查询
        PuFaVideo videoByVideoCover = baseMapper.findVideoByVideoCover(puFaVideo.getVideCover());

        if(videoByVideoCover!=null){

            throw new FileFormatException("请不要拿同一个视频封面路径来上传");
        }

        //在按视频路径
        PuFaVideo videoByVideoPath = baseMapper.findVideoByVideoPath(puFaVideo.getVideoPath());

        if(videoByVideoPath!=null){

            throw new FileFormatException("请不要拿同一个视频路径来上传");
        }

        int result = baseMapper.insert(puFaVideo);



        return result > 0;
    }

    /**
     * 普法视频删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteVideoById(Long id) {

        boolean result = this.removeById(id);


        return result;
    }

    /**
     * 普法视频修改
     *
     * @param video
     * @return
     */
    @Override
    public boolean updateVideoById(PuFaVideo video) {

        int result = baseMapper.updateById(video);

        return result > 0;
    }

    /**
     * 按id查询视频进行数据回显
     *
     * @param id
     * @return
     */
    @Override
    public PuFaVideo getVideoById(Long id) {

        //先查询缓存有没有数据
        PuFaVideo selectById = (PuFaVideo) redisTemplate.opsForValue().get("selectById");

        //没有数据就查数据库在把数据放入缓存
        if (selectById == null) {
            //先查询所有的评论
            List<PuFaVideoComment> commentAll = commentService.getCommentAll(id);

            //按id查询视频
            PuFaVideo puFaVideo = baseMapper.selectById(id);

            //再把评论放入视频里面
            puFaVideo.setComments(commentAll);

            System.out.println(puFaVideo);

            redisTemplate.opsForValue().set("selectById", puFaVideo, 24, TimeUnit.HOURS);

            selectById = (PuFaVideo) redisTemplate.opsForValue().get("selectById");
        }


        return selectById;
    }

    /**
     * 获取所有的视频(包括删除的)
     *
     * @return
     */
    @Override
    public List<PuFaVideo> getVideoAll() {

        List<PuFaVideo> videoAll = baseMapper.findVideoAll();


        return videoAll;
    }

    /**
     * 按视频路径获取视频(用于清理垃圾视频)
     *
     * @param path
     * @return
     */
    @Override
    public PuFaVideo getVideoByVideoPath(String path) {

        PuFaVideo videoByVideoPath = baseMapper.findVideoByVideoPath(path);

        return videoByVideoPath;
    }


    /**
     * 按视频封面获取视频(用于删除垃圾视频)
     * @param videoCover
     * @return
     */
    @Override
    public PuFaVideo getVideoByVideoCover(String videoCover) {

        PuFaVideo videoByVideoCover = baseMapper.findVideoByVideoCover(videoCover);

        return videoByVideoCover;
    }

    /**
     * 视频浏览量+1
     * @param id
     */
    @Override
    public void videoPageViews(Long id) {
        baseMapper.videoPageView(id);
    }
}
