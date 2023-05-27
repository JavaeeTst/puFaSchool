package com.pufaschool.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pufaschool.conn.domain.PuFaLog;

import java.util.Date;
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

    //按时间查询管理员日志
    List<PuFaLog> getAdminLogByTime(Date date);

    //按时间查询用户日志
    List<PuFaLog> getUserLogByTime(Date date);

    //按id查询用户的个人日志
    List<PuFaLog> getUserLogByUserId(Long id);

    //按id查询管理员的个人日志
    List<PuFaLog> getAdminLogByAdminId(Long id);

    //按类型查询管理员日志
    List<PuFaLog> getAdminLogByLogType(Integer logType,String createTime);

    //按类型查询用户日志
    List<PuFaLog> getUserLogByLogType(Integer logType,String createTime);


}
