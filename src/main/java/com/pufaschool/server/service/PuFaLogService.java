package com.pufaschool.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pufaschool.conn.domain.PuFaLog;
import com.pufaschool.conn.domain.queryDomain.SysLogAttributeVo;

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

    //按属性查询管理员日志(传管理员id,日志创建时间,日志类型)
    List<PuFaLog> getUserLogByAttribute(SysLogAttributeVo vo);

    //按属性查询用户的日志(传用户id,日志创建时间,日志类型)
    List<PuFaLog> getAdminByAttribute(SysLogAttributeVo vo);


}
