package com.pufaschool.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pufaschool.conn.domain.PuFaIntegration;
import com.pufaschool.conn.domain.PuFaLearnType;
import com.pufaschool.conn.utils.JWTUtils;
import com.pufaschool.server.dao.PuFaIntegrateDao;
import com.pufaschool.server.service.PuFaIntegrateService;
import com.pufaschool.server.service.PuFaLearnTypeService;
import com.sun.org.apache.bcel.internal.generic.PushInstruction;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * 积分业务层
 */
@Service
@Transactional
public class PuFaIntegrateServiceImpl extends ServiceImpl<PuFaIntegrateDao, PuFaIntegration> implements PuFaIntegrateService {

    /**
     * 学习类型service注入
     */
    @Autowired
    private PuFaLearnTypeService learnTypeService;

    /**
     * 用户完成学习任务,添加积分
     *
     * @param integration
     * @param
     * @return
     */
    @Override
    public boolean addIntegrateByUser(PuFaIntegration integration, HttpServletRequest request) {

        //取出token
        String token = request.getHeader("token");

        //解析
        Claims claims = JWTUtils.checkToken(token);

        //取出用户ID
            Long userId = Long.valueOf((Integer) claims.get("userId"));

        //先查询有没有获得过该积分,能查询到就代表获得过,不能重复获取
        PuFaIntegration puFaIntegration = baseMapper.findIntegrationByUserIdAndScoreIdAndScoreType(userId, integration.getScoreId(), integration.getScoreTypeId());

        //不为空代表获得过,就不能再次获得
        if (puFaIntegration != null) {

            return false;

        }

        int insert = baseMapper.insert(integration);

        //再次更新用户的积分
        boolean result = baseMapper.modifyUserIntegration(integration.getGet_integrate(), userId);


        return insert > 0 && result;
    }
}
