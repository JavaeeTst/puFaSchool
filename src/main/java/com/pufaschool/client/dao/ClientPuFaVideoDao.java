package com.pufaschool.client.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pufaschool.conn.domain.PuFaVideo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户获取视频dao层
 */
public interface ClientPuFaVideoDao extends BaseMapper<PuFaVideo> {

    //用户模糊查询
    List<PuFaVideo> findVideoByKey(@Param("key") String key);



}
