package com.pufaschool.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pufaschool.conn.domain.LogType;

import java.util.List;

public interface LogTypeService extends IService<LogType> {

    List<LogType> getAllLogType();
}
