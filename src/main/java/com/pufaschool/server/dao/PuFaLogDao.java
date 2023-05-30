package com.pufaschool.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pufaschool.conn.domain.PuFaLog;
import com.pufaschool.conn.domain.queryDomain.SysLogAttributeVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PuFaLogDao extends BaseMapper<PuFaLog> {

    //管理员日志记录
    void adminLog(PuFaLog user);

    //用户日志记录
    void userLog(PuFaLog user);

    //查询所有管理员的日志记录
    List<PuFaLog> findAdminLogAll();

    //查询所有用户的日志记录
    List<PuFaLog> findUserLogAll();

    //按属性查询用户日志（用户id,创建时间,日志类型）
    List<PuFaLog> findUserLogByAttribute(@Param("vo")SysLogAttributeVo vo);

    //按属性查询管理员日志(管理员id,创建时间，日志类型)
    List<PuFaLog> findAdminLogByAttribute(@Param("vo")SysLogAttributeVo vo);


}
