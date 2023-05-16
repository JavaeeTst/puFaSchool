package com.pufaschool.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pufaschool.conn.domain.PuFaTextContent;
import com.pufaschool.conn.domain.vo.SysTextContentVo;
import com.pufaschool.conn.exception.AddException;
import com.pufaschool.server.dao.PuFaTextContentDao;
import com.pufaschool.server.service.PuFaTextContentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章内容业务实现层
 */
@Service
public class PuFaTextContentServiceImpl extends ServiceImpl<PuFaTextContentDao, PuFaTextContent> implements PuFaTextContentService {

    /**
     * 添加文章内容
     * @param content
     * @return
     */
    @Override
    public boolean addTextContent(PuFaTextContent content) {
        //文章内容名称不能为null
        if (content.getContentName()==null) {

            throw new AddException("请输入文章内容名字");
        }
        boolean save = this.save(content);

        return save;
    }

    /**
     * 删除文章内容
     * @param id
     * @return
     */
    @Override
    public boolean deleteTextContentById(Long id) {

        boolean result = this.removeById(id);

        return result;
    }

    /**
     * 修改文章内容
     * @param content
     * @return
     */
    @Override
    public boolean updateTextContentById(PuFaTextContent content) {

        boolean result = this.updateById(content);


        return result;
    }

    /**
     * 查询文章内容(只查询id和名字)
     * @param id
     * @return
     */
    @Override
    public List<SysTextContentVo> getTextContextByTextId(Long id) {

        List<SysTextContentVo> textContentByTextId = baseMapper.findTextContentByTextId(id);

        return textContentByTextId;
    }

    /**
     * 按文章内容id查询文章内容
     * @param textContentId
     * @return
     */
    @Override
    public PuFaTextContent getTextContentByTextContentId(Long textContentId) {

        PuFaTextContent textContentByTextContentId = baseMapper.findTextContentByTextContentId(textContentId);


        return textContentByTextContentId;
    }
}
