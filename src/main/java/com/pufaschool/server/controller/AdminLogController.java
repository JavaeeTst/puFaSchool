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
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.List;

@Api(tags = "管理员日志管理(超级管理员专属)(后端)")
@RestController
@RequestMapping("/system/puFaSchool/server/adminLog")
public class AdminLogController {

    @Autowired
    private PuFaLogService logService;

    /**
     * 查询管理员的所有日志
     * @return
     */
    @ApiOperation("查询所有管理员的日志")
    @GetMapping("/getAdminLogAll")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public Result getAdminLogAll(){

        List<PuFaLog> adminLogAll = logService.getAdminLogAll();

        return Result.success(adminLogAll);
    }

    /**
     * 按属性查询管理员日志
     */
    @ApiOperation("按属性查询管理员日志")
    @GetMapping("/getAdminLogByAttribute")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public Result getAdminLogByAttribute(@ApiParam("可以全部为空") SysLogAttributeVo vo){

        List<PuFaLog> adminByAttribute = logService.getAdminByAttribute(vo);

        return Result.success(adminByAttribute);
    }

}
