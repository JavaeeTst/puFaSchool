package com.pufaschool.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pufaschool.conn.domain.PuFaLog;
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

    //查询用户个人的日志记录
    List<PuFaLog> findUserLogByUserId(@Param("id") Long id);

    //查询管理员个人的日志记录
    List<PuFaLog> findAdminLogByAdminId(@Param("id") Long id);

    //按日期查询管理员日志
    List<PuFaLog> findAdminLogByTime(@Param("date")Date date);

    //按日期查询用户的日志
    List<PuFaLog> findUserLogByTime(@Param("date") Date date);
}
