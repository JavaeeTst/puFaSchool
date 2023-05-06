package com.pufaschool.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pufaschool.conn.domain.PuFaLog;

import java.util.List;

/**
 * 日志业务层
 */
public interface PuFaLogService extends IService<PuFaLog> {

    //管理员日志记录
    void addAdminLog(PuFaLog log);

    //用户日志记录
    void addUserLog(PuFaLog log);

    //查询所有用户的日志记录
    List<PuFaLog> getUserLogAll();

    //查询所有的管理员日志记录
    List<PuFaLog> getAdminLogAll();


}
