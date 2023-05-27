package com.pufaschool.server.service.impl;

import com.pufaschool.conn.domain.PuFaIndex;
import com.pufaschool.conn.domain.PuFaUser;
import com.pufaschool.conn.domain.vo.SysUserInfoVo;
import com.pufaschool.server.dao.PuFaCourseWareDao;
import com.pufaschool.server.dao.PuFaLearnTypeDao;
import com.pufaschool.server.dao.PuFaUserDao;
import com.pufaschool.server.dao.PuFaVideoDao;
import com.pufaschool.server.service.PuFaIndexService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 普法首页业务层
 */
@Service
public class PuFaIndexServiceImpl implements PuFaIndexService {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private PuFaVideoDao videoDao;

    @Resource
    private PuFaCourseWareDao courseWareDao;

    @Resource
    private PuFaUserDao puFaUserDao;

    @Resource
    private PuFaLearnTypeDao learnTypeDao;

    @Override
    public PuFaIndex getPuFaSchoolInfo() {

        //先查询redis有没有数据
        PuFaIndex puFaIndex = (PuFaIndex) redisTemplate.opsForValue().get("puFaIndex");

        //如果有的话直接返回
        if (puFaIndex != null) {

            return puFaIndex;
        }
        //如果没有,查询视频数量
        Integer videoNum = videoDao.findVideoNum(0);

        //查询用户数量
        Long userNum = puFaUserDao.findUserNum(0);

        //查询管理员数量
        Integer adminNum = puFaUserDao.findAdminNum();

        //查询所有用户的积分
        Double totalIntegrationAllUser = puFaUserDao.findTotalIntegrationAllUser();

        //查询课件数量
        Integer courseWareNum = courseWareDao.findCourseWareNum(0);

        //查询课件类型数量
        Integer learnTypeNum = learnTypeDao.findLearnTypeNum(0);

        //查询积分大于300的用户
        List<SysUserInfoVo> userByIntegrate = puFaUserDao.findUserByIntegrate();

        //放入实体类里面
        puFaIndex = new PuFaIndex(userNum, adminNum, videoNum, learnTypeNum, courseWareNum, totalIntegrationAllUser,userByIntegrate);

        //再把实体类放入缓存里面
        redisTemplate.opsForValue().set("puFaIndex",puFaIndex);



        return puFaIndex;
    }
}
