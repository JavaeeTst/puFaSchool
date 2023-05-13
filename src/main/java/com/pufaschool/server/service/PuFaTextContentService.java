package com.pufaschool.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pufaschool.conn.domain.PuFaTextContent;
import com.pufaschool.conn.domain.vo.SysTextContentVo;

import java.util.List;

/**
 * 文章内容service
 */
public interface PuFaTextContentService extends IService<PuFaTextContent> {

    //添加文章内容
    boolean addTextContent(PuFaTextContent content);

    //删除文章内容(按文章内容id)
    boolean deleteTextContentById(Long id);

    //修改文章内容(按id)
    boolean updateTextContentById(PuFaTextContent content);

    //查询文章内容名字和文章内容id(按文章id)
    List<SysTextContentVo> getTextContextByTextId(Long id);


}
