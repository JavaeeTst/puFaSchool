package com.pufaschool.server.controller;

import com.pufaschool.conn.domain.PuFaIndex;
import com.pufaschool.conn.result.Result;
import com.pufaschool.server.service.PuFaIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "普法首页数据")
@RestController
@RequestMapping("/system/puFaSchool/server/puFaIndex")
public class PuFaIndexController {

    @Autowired
    private PuFaIndexService indexService;

    @ApiOperation("首页数据")
    @GetMapping("/getIndexData")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result getIndexData(){

        PuFaIndex puFaSchoolInfo = indexService.getPuFaSchoolInfo();

        return Result.success(puFaSchoolInfo);
    }
}
