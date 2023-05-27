package com.pufaschool.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pufaschool.server.dao.PuFaLogDao;
import com.pufaschool.conn.domain.PuFaLog;
import com.pufaschool.server.service.PuFaLogService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PuFaLogServiceImpl extends ServiceImpl<PuFaLogDao, PuFaLog> implements PuFaLogService {

    /**
     * 管理员日志记录
     *
     * @param log
     */
    @Override
    public void addAdminLog(PuFaLog log) {

        baseMapper.adminLog(log);

    }


    /**
     * 用户日志记录
     *
     * @param log
     */
    @Override
    public void addUserLog(PuFaLog log) {

        baseMapper.userLog(log);
    }


    /**
     * 查询所有用户的日志
     *
     * @return
     */
    @Override
    public List<PuFaLog> getUserLogAll() {

        List<PuFaLog> userLogAll = baseMapper.findUserLogAll();

        return userLogAll;
    }

    /**
     * 查询所有管理员的日志
     *
     * @return
     */
    @Override
    public List<PuFaLog> getAdminLogAll() {

        List<PuFaLog> adminLogAll = baseMapper.findAdminLogAll();

        return adminLogAll;
    }

    /**
     * 按时间查询管理员日志
     * @param date
     * @return
     */
    @Override
    public List<PuFaLog> getAdminLogByTime(Date date) {

        List<PuFaLog> adminLogByTime = baseMapper.findAdminLogByTime(date);

        return adminLogByTime;
    }

    /**
     * 按时间查询用户的日志
     * @param date
     * @return
     */
    @Override
    public List<PuFaLog> getUserLogByTime(Date date) {

        List<PuFaLog> userLogByTime = baseMapper.findUserLogByTime(date);

        return userLogByTime;
    }

    /**
     * 按用户id查询用户的个人日志
     * @param id
     * @return
     */
    @Override
    public List<PuFaLog> getUserLogByUserId(Long id) {

        List<PuFaLog> userLogByUserId = baseMapper.findUserLogByUserId(id);

        return userLogByUserId;
    }

    /**
     * 按管理员id查询管理员的个人日志
     * @param id
     * @return
     */
    @Override
    public List<PuFaLog> getAdminLogByAdminId(Long id) {

        List<PuFaLog> adminLogByAdminId = baseMapper.findAdminLogByAdminId(id);

        return adminLogByAdminId;
    }

    /**
     * 按类型和时间查询管理员日志（时间可以为null）
     * @param logType
     * @return
     */
    @Override
    public List<PuFaLog> getAdminLogByLogType(Integer logType,String createTime) {

        List<PuFaLog> adminLogType = baseMapper.findAdminLogType(logType, createTime);

        return adminLogType;
    }

    /**
     * 按类型和时间查询用户日志(时间可以为null)
     * @param logType
     * @param createTime
     * @return
     */
    @Override
    public List<PuFaLog> getUserLogByLogType(Integer logType,String createTime) {

        List<PuFaLog> userLogByLogType = baseMapper.findUserLogByLogType(logType, createTime);

        return userLogByLogType;
    }
}
