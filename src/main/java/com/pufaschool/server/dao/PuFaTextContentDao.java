package com.pufaschool.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pufaschool.conn.domain.PuFaTextContent;
import com.pufaschool.conn.domain.vo.SysTextContentVo;

import java.util.List;

/**
 * 文章内容dao层
 */
public interface PuFaTextContentDao extends BaseMapper<PuFaTextContent> {

      //获取文章内容(按文章id)
      List<SysTextContentVo> findTextContentByTextId(Long textId);
}
