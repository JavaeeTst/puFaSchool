package com.pufaschool.server.controller;

import com.pufaschool.conn.domain.PuFaIntegration;
import com.pufaschool.conn.result.Result;
import com.pufaschool.server.service.PuFaIntegrateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 积分控制层
 */
@Api(tags = "积分控制层")
@RestController
@RequestMapping("/system/puFaSchool/server/integration")
public class IntegrationController {

    @Autowired
    private PuFaIntegrateService integrateService;

    /**
     * 添加积分
     */
    @PostMapping("/addIntegration")
    @ApiOperation("获得积分")
    public Result addIntegration(@RequestBody PuFaIntegration integration, HttpServletRequest request){

        boolean result = integrateService.addIntegrateByUser(integration, request);

        return Result.success(result?"恭喜你获得"+integration.getGet_integrate()+"积分":null);
    }
}
