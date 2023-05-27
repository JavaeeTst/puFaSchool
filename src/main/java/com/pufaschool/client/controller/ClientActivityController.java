package com.pufaschool.client.controller;

import com.pufaschool.conn.domain.PuFaActive;
import com.pufaschool.conn.domain.queryDomain.SysActiveAttributeVo;
import com.pufaschool.conn.result.Result;
import com.pufaschool.server.service.PuFaActiveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "普法活动控制层(前端)")
@RestController
@RequestMapping("/system/puFaSchool/client/activity")
public class ClientActivityController {

    @Autowired
    private PuFaActiveService activeService;

    @ApiOperation("按id查询活动")
    @GetMapping("/getActivityById/{id}")
    public Result getActivityById(@PathVariable Long id){

        PuFaActive activityById = activeService.getActivityById(id);

        return Result.success(activityById);
    }

    @ApiOperation("按普法查询属性查询")
    @GetMapping("/getActivityByAttribute")
    public Result getActivityByAttribute(SysActiveAttributeVo vo){

        List<PuFaActive> activityByActivityAttribute = activeService.getActivityByActivityAttribute(vo);

        return Result.success(activityByActivityAttribute);
    }
}
