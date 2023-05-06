package com.pufaschool.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pufaschool.conn.domain.LogType;
import com.pufaschool.server.dao.LogTypeDao;
import com.pufaschool.server.service.LogTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogTypeServiceImpl extends ServiceImpl<LogTypeDao, LogType> implements LogTypeService {


    /**
     * 获取所有的日志类型信息
     * @return
     */
    @Override
    public List<LogType> getAllLogType() {

        List<LogType> logTypes = baseMapper.selectList(null);

        return logTypes;
    }
}
