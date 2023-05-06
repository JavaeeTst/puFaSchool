package com.pufaschool.server.controller;

import com.pufaschool.conn.domain.PuFaLog;
import com.pufaschool.conn.result.Result;
import com.pufaschool.server.service.PuFaLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "日志管理(超级管理员专属)(后端)")
@RestController
@RequestMapping("/system/puFaSchool/server/log")
public class LogController {

    @Autowired
    private PuFaLogService logService;


    /**
     * 查询所有的用户的日志
     *
     * @return
     */
    @ApiOperation("查询所有用户的日志")
    @GetMapping("/getUserLogAll")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public Result getUserLogAll() {

        List<PuFaLog> userLogAll = logService.getUserLogAll();

        return Result.success(userLogAll);
    }

    /**
     * 查询所有管理员的日志
     *
     * @return
     */
    @ApiOperation("查询所有管理员的日志")
    @GetMapping("/getAdminLogAll")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public Result getAdminAll() {

        List<PuFaLog> adminLogAll = logService.getAdminLogAll();

        return Result.success(adminLogAll);
    }
}
