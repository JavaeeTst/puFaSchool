package com.pufaschool.client.controller;


import com.pufaschool.conn.domain.PuFaCourseWare;
import com.pufaschool.conn.result.Result;
import com.pufaschool.server.service.PuFaCourseWareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/system/puFaSchool/client/courseWare")
@Api(tags = "普法课件管理(前端)")
public class ClientCourseWareController {

    @Autowired
    private PuFaCourseWareService courseWareService;


    /**
     * 批量下载课件
     *
     * @param ids
     * @return
     */
    @GetMapping("/getCourseWareByIds")
    @ApiOperation("批量下载课件")
    public Result getCourseWareByIds(@RequestParam("ids") Long[] ids,HttpServletRequest request ) {

        List<String> courseWareByCourseWareIds = courseWareService.getCourseWareByCourseWareIds(ids,request);

        return Result.success(courseWareByCourseWareIds);

    }

    /**
     * 单独课件下载
     */
    @GetMapping("/getCourseWareById/{id}")
    @ApiOperation("课件下载")
    public Result getCourseWareById(@PathVariable Long id,HttpServletRequest request) {

        String courseWareById = courseWareService.getCourseWareUrlById(id,request);

        return Result.success(courseWareById);
    }
}
