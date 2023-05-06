package com.pufaschool.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pufaschool.conn.domain.PuFaText;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 普法文章dao
 */
public interface PuFaTextDao extends BaseMapper<PuFaText> {


    List<PuFaText> findTextByAttribute(@Param("key") String key);
}
