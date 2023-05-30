package com.pufaschool.server.controller;

import com.pufaschool.conn.domain.PuFaLog;
import com.pufaschool.conn.domain.queryDomain.SysLogAttributeVo;
import com.pufaschool.conn.result.Result;
import com.pufaschool.server.service.PuFaLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "用户日志记录(超级管理员专属)(后端)")
@RestController
@RequestMapping("/system/puFaSchool/server/userLog")
public class UserLogController {


    @Autowired
    private PuFaLogService logService;

    @ApiOperation("查询所有用户的日志")
    @GetMapping("/getUserLogAll")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public Result getUserLogAll(){

        List<PuFaLog> userLogAll = logService.getUserLogAll();

        return Result.success(userLogAll);
    }

    /**
     * 按属性查询用户的日志
     */
    @ApiOperation("按属性查询用户的日志")
    @GetMapping("/getUserLogByAttribute")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public Result getUserLogByAttribute(@ApiParam("日志属性实体类")SysLogAttributeVo vo){

        List<PuFaLog> userLogByAttribute = logService.getUserLogByAttribute(vo);

        return Result.success(userLogByAttribute);
    }
}
