package com.pufaschool.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pufaschool.server.dao.PuFaLogDao;
import com.pufaschool.conn.domain.PuFaLog;
import com.pufaschool.server.service.PuFaLogService;
import org.springframework.stereotype.Service;

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
}
