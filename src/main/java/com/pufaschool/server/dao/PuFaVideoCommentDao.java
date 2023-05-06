package com.pufaschool.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pufaschool.conn.domain.PuFaVideoComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 普法视频评论dao层
 */
public interface PuFaVideoCommentDao extends BaseMapper<PuFaVideoComment> {

    //按视频id查询评论
    List<PuFaVideoComment> findCommentAll(@Param("vid") Long vid);




}
