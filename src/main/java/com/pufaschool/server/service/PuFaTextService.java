package com.pufaschool.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pufaschool.conn.domain.PuFaText;

import java.util.List;

/**
 * 普法文章service接口
 */
public interface PuFaTextService extends IService<PuFaText> {

    //添加文章
    boolean addText(PuFaText text);

    //删除文章
    boolean deleteByTextId(Long id);

    //修改文章
    boolean updateTextByTextId(PuFaText text);

    //查询文章单个查询(传文章id)
    PuFaText getTextByTextId(Long id);

    //查询所有文章
    List<PuFaText> getTextAll();

    //模糊查询文章
    List<PuFaText> getTextByAttribute(String key);
}
