package com.pufaschool.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pufaschool.conn.domain.PuFaText;
import com.pufaschool.conn.domain.vo.SysTextContentVo;
import com.pufaschool.conn.exception.DeleteException;
import com.pufaschool.server.dao.PuFaTextDao;
import com.pufaschool.server.service.PuFaTextContentService;
import com.pufaschool.server.service.PuFaTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 普法文章业务实现层
 */
@Service
public class PuFaTextServiceImpl extends ServiceImpl<PuFaTextDao, PuFaText> implements PuFaTextService {


    /**
     * 普法内容业务对象
     */
    @Autowired
    private PuFaTextContentService contentService;
    /**
     * 添加文章
     * @param text
     * @return
     */
    @Override
    public boolean addText(PuFaText text) {

        boolean result = this.save(text);

        return result;
    }

    /**
     * 按文章id删除文章
     * @param id
     * @return
     */
    @Override
    public boolean deleteByTextId(Long id) {

        //先查询该文章是否有子文章
        List<SysTextContentVo> textContextByTextId = contentService.getTextContextByTextId(id);

        //如果该文章有子文章则无法删除
        if (textContextByTextId.size()>0) {

            throw new DeleteException("该文章还有子文章存在,请先删除子文章");
        }

       //程序走到这里没有问题，代表可以删除
        boolean result = this.removeById(id);

        return result;
    }

    /**
     * 修改文章信息按id
     * @param text
     * @return
     */
    @Override
    public boolean updateTextByTextId(PuFaText text) {


        boolean result = this.updateById(text);

        return result;
    }

    /**
     * 按文章id查询文章信息
     * @param id
     * @return
     */
    @Override
    public PuFaText getTextByTextId(Long id) {

        PuFaText byId = this.getById(id);

        //拿到文章id查询文章的信息
        List<SysTextContentVo> textContextByTextId = contentService.getTextContextByTextId(byId.getId());

        //把信息放入文章里面
        byId.setTextContent(textContextByTextId);

        return byId;
    }

    /**
     * 查询所有文章
     * @return
     */
    @Override
    public List<PuFaText> getTextAll() {

        List<PuFaText> list = this.list();

        return list;
    }

    /**
     * 模糊查询文章
     * @param key
     * @return
     */
    @Override
    public List<PuFaText> getTextByAttribute(String key) {

        List<PuFaText> textByAttribute = baseMapper.findTextByAttribute(key);

        return textByAttribute;
    }

    /**
     * 文章浏览量加1
     * @param id
     */
    @Override
    public void textPageViews(Long id) {

        baseMapper.textPageView(id);
    }
}
