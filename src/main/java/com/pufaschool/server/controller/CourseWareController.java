package com.pufaschool.server.controller;


import com.pufaschool.conn.domain.PuFaCourseWare;
import com.pufaschool.conn.result.Result;
import com.pufaschool.server.service.PuFaCourseWareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/system/puFaSchool/server/courseWare")
@Api(tags = "普法课件管理(后端)")
public class CourseWareController {

    @Autowired
    private PuFaCourseWareService courseWareService;


    /**
     * 管理员及超级管理员查询所有课件
     *
     * @return
     */
    @GetMapping("/getCourseWareList")
    @ApiOperation("查询所有课件")
    public Result getCourseWareList() {

        List<PuFaCourseWare> courseWareAll = courseWareService.findCourseWareAll();

        return Result.success(courseWareAll);
    }

    /**
     * 管理员上传课件
     */
    @PostMapping("/addCourseWare")
    @PreAuthorize("hasAnyRole('ADMIN',SUPERA_DMIN)")
    @ApiOperation("上传课件")
    public Result addCourseWare(@RequestBody PuFaCourseWare puFaCourseWare) {

        boolean result = courseWareService.addCourseWare(puFaCourseWare);

        return Result.success(result ? "上传成功" : "上传失败");
    }

    /**
     * 管理员删除课件
     */
    @DeleteMapping("/deleteById/{id}")
    @ApiOperation("删除课件")
    @PreAuthorize("hasAnyRole('ADMIN',SUPER_ADMIN)")
    public Result deleteById(@PathVariable Long id) {

        boolean result = courseWareService.deleteCourseWare(id);

        return Result.success(result ? "删除成功" : "删除失败");
    }

    /**
     * 按上传时间查询课件
     */
    @GetMapping("/getCourseWareByUploadTime")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @ApiOperation("按上传时间查询课程")
    public Result getCourseWareByUploadTime(@RequestParam("uploadTime") Date date) {

        List<PuFaCourseWare> courseWreByUploadTime = courseWareService.findCourseWreByUploadTime(date);

        return Result.success(courseWreByUploadTime);
    }
}
