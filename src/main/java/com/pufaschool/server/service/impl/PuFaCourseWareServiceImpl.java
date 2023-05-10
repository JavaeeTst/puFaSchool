package com.pufaschool.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pufaschool.conn.BaseEntity;
import com.pufaschool.conn.domain.PuFaCourseWare;
import com.pufaschool.conn.exception.UsernameFreezeException;
import com.pufaschool.server.dao.PuFaCourseWareDao;
import com.pufaschool.server.service.PuFaCourseWareService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class PuFaCourseWareServiceImpl extends ServiceImpl<PuFaCourseWareDao, PuFaCourseWare> implements PuFaCourseWareService {


    /**
     * 批量下载课件
     * @param ids
     * @return
     */
    @Override
    public List<String> getCourseWareByCourseWareIds(Long[] ids,HttpServletRequest request) {

        //如果token没有代表没有登录拦截
        if(request.getHeader("token")==null){

            throw new UsernameFreezeException("请先登录");

        }

        List<String> courseWareByIdList = baseMapper.downloadCourseWareByIdList(ids);

        return courseWareByIdList;
    }

    /**
     * 单独下载课件
     * @param id
     * @return
     */
    @Override
    public PuFaCourseWare getCourseWareById(Long id) {

        PuFaCourseWare byId = this.getById(id);


        return byId;
    }

    /**
     * 单个课件下载
     * @param id
     * @return
     */
    @Override
    public String getCourseWareUrlById(Long id, HttpServletRequest request) {

        //如果token没有代表没有登录拦截
        if(request.getHeader("token")==null){

            throw new UsernameFreezeException("请先登录");

        }

        String courseWareById = baseMapper.downloadCourseWareById(id);

        return courseWareById;
    }

    /**
     * 上传课件
     *
     * @param puFaCourseWare
     * @return
     */
    @Override
    public boolean addCourseWare(PuFaCourseWare puFaCourseWare) {

        int insert = baseMapper.insert(puFaCourseWare);


        return insert > 0;
    }

    /**
     * 删除课件
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteCourseWare(Long id) {

        int i = baseMapper.deleteById(id);

        return i > 0;
    }

    /**
     * 查询所有课件
     *
     * @return
     */
    @Override
    public List<PuFaCourseWare> findCourseWareAll() {

        List<PuFaCourseWare> puFaCourseWares = baseMapper.selectList(null);


        return puFaCourseWares;
    }

    /**
     * 按课件类型查询课件
     *
     * @param type
     * @return
     */
    @Override
    public List<PuFaCourseWare> findCourseWareByType(String type) {

        LambdaQueryWrapper<PuFaCourseWare> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(PuFaCourseWare::getCourserwareType, type);

        List<PuFaCourseWare> puFaCourseWares = baseMapper.selectList(wrapper);


        return puFaCourseWares;
    }

    /**
     * 按上传时间查询课件
     *
     * @param uploadTime
     * @return
     */
    @Override
    public List<PuFaCourseWare> findCourseWreByUploadTime(Date uploadTime) {

        LambdaQueryWrapper<PuFaCourseWare> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(BaseEntity::getCreateTime, uploadTime);

        List<PuFaCourseWare> puFaCourseWares = baseMapper.selectList(wrapper);

        return puFaCourseWares;
    }

    /**
     * 按照课件路径查询课件,用于删除课件
     *
     * @param courseWarePath
     * @return
     */
    @Override
    public PuFaCourseWare findCourseWareByCourseWarePath(String courseWarePath) {

        LambdaQueryWrapper<PuFaCourseWare> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(PuFaCourseWare::getCoursewareUrl,courseWarePath);

        PuFaCourseWare puFaCourseWare = baseMapper.selectOne(wrapper);

        return puFaCourseWare;
    }


}
