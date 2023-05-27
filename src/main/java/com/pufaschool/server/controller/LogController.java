package com.pufaschool.server.controller;

import com.pufaschool.conn.domain.PuFaLog;
import com.pufaschool.conn.result.Result;
import com.pufaschool.server.service.PuFaLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(tags = "日志管理(超级管理员专属)(后端)")
@RestController
@RequestMapping("/system/puFaSchool/server/log")
public class LogController {

    @Autowired
    private PuFaLogService logService;

    /**
     * 按类型和时间查询用户的日志(时间可以为null)
     */
    @ApiOperation("按时间和类型查询用户员日志(时间可以为null)")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @GetMapping("/getUserLogByLogTypeOrCreateTime/{logType}/{createTime}")
    public Result getUserLogByLogTypeOrCreateTime(@PathVariable Integer logType,String createTime){

        List<PuFaLog> userLogByLogType = logService.getUserLogByLogType(logType, createTime);

        return Result.success(userLogByLogType);
    }
    /**
     * 按类型和时间查询管理员的日志（时间可以为null）
     */
    @ApiOperation("按时间和类型查询管理员日志(时间可以为null)")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @GetMapping("/getAdminLogByLogTypeOrCreateTime/{logType}/{createTime}")
    public Result getAdminLogByLogTypeOrCreateTime(@PathVariable Integer logType,@PathVariable String createTime){

        List<PuFaLog> adminLogByLogType = logService.getAdminLogByLogType(logType, createTime);

        return Result.success(adminLogByLogType);
    }

    /**
     * 查询所有的用户的日志
     *
     * @return
     */
    @ApiOperation("查询所有用户的日志")
    @GetMapping("/getUserLogAll")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
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
    /**
     * 按时间查询用户日志
     */
    @ApiOperation("按时间查询用户日志")
    @GetMapping("/getUserLogByTime")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public Result getUserLogByTime(@RequestParam("date")Date date){

        List<PuFaLog> userLogByTime = logService.getUserLogByTime(date);

        return Result.success(userLogByTime);
    }

    /**
     * 按时间查询管理员日志
     */
    @ApiOperation("按时间查询管理员日志")
    @GetMapping("/getAdminLogByTime")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public Result getAdminLogByTime(@RequestParam("date") Date date){

        List<PuFaLog> adminLogByTime = logService.getAdminLogByTime(date);

        return Result.success(adminLogByTime);
    }
    /**
     *查询管理员个人日志
     */
    @ApiOperation("按管理员id查询管理员个人日志")
    @GetMapping("/getAdminLogByAdminId/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public Result getAdminLogByAdminId(@PathVariable Long id){

        List<PuFaLog> adminLogByAdminId = logService.getAdminLogByAdminId(id);

        return Result.success(adminLogByAdminId);
    }

    /**
     * 查询用户的个人日志
     */
    @ApiOperation("按id查询用户的个人日志")
    @GetMapping("/getUserLogByUserId/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public Result getUserLogByUserId(@PathVariable Long id){

        List<PuFaLog> userLogByUserId = logService.getUserLogByUserId(id);

        return Result.success(userLogByUserId);
    }
}
