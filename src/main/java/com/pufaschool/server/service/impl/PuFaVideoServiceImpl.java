package com.pufaschool.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pufaschool.conn.exception.FileFormatException;
import com.pufaschool.conn.exception.UpdateException;
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
        PuFaVideo videoByVideoCover = baseMapper.findVideoByVideoCover(puFaVideo.getVideoCover());

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

         //如果删除成功，缓存的数据也要更新
        Boolean puFaIndex = redisTemplate.delete("puFaIndex");
        return result && puFaIndex;
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


        //没有数据就查数据库在把数据放入缓存

            //先查询所有的评论
            List<PuFaVideoComment> commentAll = commentService.getCommentAll(id);

            //按id查询视频
            PuFaVideo puFaVideo = baseMapper.selectById(id);

            if(puFaVideo==null){

                throw new UpdateException("请上传正确的视频id");
            }
            //再把评论放入视频里面
            puFaVideo.setComments(commentAll);

            System.out.println(puFaVideo);






        return puFaVideo;
    }

    /**
     * 获取所有的视频(包括删除的)
     *
     * @return
     */
    @Override
    public List<PuFaVideo> getVideoAllIsDelete() {

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

    /**
     * 模糊查询视频
     * @param key
     * @return
     */
    @Override
    public List<PuFaVideo> getVideoByVideoAttribute(String key) {

        List<PuFaVideo> videoByVideoAttribute = baseMapper.findVideoByVideoAttribute(key);

        return videoByVideoAttribute;
    }

    /**
     * 查询所有被删除的视频
     * @return
     */
    @Override
    public List<PuFaVideo> getDeleteVideoList() {

        List<PuFaVideo> deleteVideoList = baseMapper.findDeleteVideoList();

        return deleteVideoList;
    }

    @Override
    public PuFaVideo getVideoByDeleteId(Long deleteId) {

        PuFaVideo videoByDeleteId = baseMapper.findVideoByDeleteId(deleteId);

        return videoByDeleteId;
    }

    /**
     * 清空被删除的视频
     * @param ids
     * @return
     */
    @Override
    public boolean removeVideoByIdList(Long[] ids) {

        boolean result = baseMapper.deleteByVideoIdList(ids);

        return result;
    }

    /**
     * 查询所有未删除的视频
     * @return
     */
    @Override
    public List<PuFaVideo> getVideoAllNotDelete() {

        LambdaQueryWrapper<PuFaVideo> wrapper=new LambdaQueryWrapper<>();

        wrapper.orderByDesc(PuFaVideo::getPageViews);

        List<PuFaVideo> list = this.list(wrapper);

        return list;
    }
}
