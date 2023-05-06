package com.pufaschool.client.controller;


import com.pufaschool.conn.domain.PuFaCourseWareType;
import com.pufaschool.conn.result.Result;
import com.pufaschool.server.service.PuFaCourseWareTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "学习课件类型(前端)")
@RestController
@RequestMapping("/system/puFaSchool/client/courseWareType")
public class ClientCourseWareTypeController {

    @Autowired
    private PuFaCourseWareTypeService wareTypeService;


    @GetMapping("/getCourseWareTypeAll")
    @ApiOperation("获取所有学习课件类型")
    public Result getCourseWareTypeAll(){

        List<PuFaCourseWareType> courseWareTypeAll = wareTypeService.getCourseWareTypeAll();

        return Result.success(courseWareTypeAll);
    }
}
