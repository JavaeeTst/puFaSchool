package com.pufaschool.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pufaschool.conn.domain.PuFaVideoComment;

import java.util.List;

/**
 * 普法视频评论service层
 */
public interface PuFaVideoCommentService extends IService<PuFaVideoComment> {

    //按视频id查询所有评论
    List<PuFaVideoComment> getCommentAll(Long vid);

    //用户评论
    boolean addUserComment(PuFaVideoComment comment);

    //删除评论
    boolean deleteVideoCommentByUserIdAndCommentId(Long userId,Long videoCommentId);
}
