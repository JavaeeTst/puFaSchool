package com.pufaschool.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pufaschool.conn.BaseEntity;
import com.pufaschool.server.dao.PuFaVideoCommentDao;
import com.pufaschool.conn.domain.PuFaUser;
import com.pufaschool.conn.domain.PuFaVideoComment;
import com.pufaschool.server.service.PuFaUserService;
import com.pufaschool.server.service.PuFaVideoCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 普法视频评论业务实现类
 */
@Service
public class PuFaVideoCommentServiceImpl extends ServiceImpl<PuFaVideoCommentDao, PuFaVideoComment> implements PuFaVideoCommentService {

    @Autowired
    private PuFaUserService puFaUserService;

    /**
     * 查询所有用户额评论
     *
     * @param vid
     * @return
     */
    @Override
    public List<PuFaVideoComment> getCommentAll(Long vid) {

        //先按视频id查询所有的视频评论
        List<PuFaVideoComment> commentAll = baseMapper.findCommentAll(vid);


//        //在遍历所有的评论
//        for (PuFaVideoComment puFaVideoComment : commentAll) {
//
//            //取出id来进行查询用户
//            PuFaUser userById = puFaUserService.getUserById(puFaVideoComment.getUserId());
//
//            //查询到额用户id存入对象里面
//            puFaVideoComment.setUser(userById);
//
//        }

        return commentAll;
    }


    /**
     * 用户评论
     *
     * @param comment
     * @return
     */
    @Override
    public boolean addUserComment(PuFaVideoComment comment) {

        boolean result = this.save(comment);

        return result;
    }

    /**
     * 用户删除评论(按视频评论id和用户id)
     * @param userId
     * @param videoCommentId
     * @return
     */
    @Override
    public boolean deleteVideoCommentByUserIdAndCommentId(Long userId, Long videoCommentId) {

        LambdaQueryWrapper<PuFaVideoComment> wrapper=new LambdaQueryWrapper<>();

        wrapper.eq(PuFaVideoComment::getUserId,userId);

        wrapper.eq(BaseEntity::getId,videoCommentId);

        int result = baseMapper.delete(wrapper);


        return result>0;
    }
}
