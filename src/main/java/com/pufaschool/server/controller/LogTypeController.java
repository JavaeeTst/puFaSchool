package com.pufaschool.server.controller;

import com.pufaschool.conn.domain.LogType;
import com.pufaschool.conn.result.Result;
import com.pufaschool.server.service.LogTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/system/puFaSchool/server/logType")
@Api(tags = "获取日志类型")
@RestController
public class LogTypeController {



    @Autowired
    private LogTypeService logTypeService;


    @GetMapping("/getAllLogType")
    @ApiOperation("获取日志类型")
    public Result getAllLogType(){

        List<LogType> allLogType = logTypeService.getAllLogType();

        return Result.success(allLogType);

    }

}
