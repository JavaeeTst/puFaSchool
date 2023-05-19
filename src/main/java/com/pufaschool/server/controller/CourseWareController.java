package com.pufaschool.server.controller;


import com.pufaschool.conn.domain.PuFaCourseWare;
import com.pufaschool.conn.result.Result;
import com.pufaschool.conn.result.Status;
import com.pufaschool.server.service.PuFaCourseWareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
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
     * 清理被删除的课件
     */
    @DeleteMapping("/clearCourseWareByIdList")
    @ApiOperation("清理被删除的课件")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result clearCourseWareByIdList(@Param("ids") Long[] ids){

        boolean result = courseWareService.removeCourseWareByIdList(ids);

        return result?Result.success("删除成功"):Result.error(Status.DELETE_ERR,"删除失败");
    }

    /**
     * 查询所有被删除的课件
     */

    @GetMapping("/getCourseWareDeleteList")
    @ApiOperation("查询所有被删除的课件")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result getCourseWareDeleteList(){

        List<PuFaCourseWare> courseWareDeleteList = courseWareService.getCourseWareDeleteList();

        return Result.success(courseWareDeleteList);
    }
    /**
     * 按id查询被删除的课件
     */
    @GetMapping("/getCourseWareByDeleteId/{deleteId}")
    @ApiOperation("按id查询被删除的课件")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result getCourseWareByDeleteId(@PathVariable Long deleteId){

        PuFaCourseWare courseWareByDeleteId = courseWareService.getCourseWareByDeleteId(deleteId);

        return Result.success(courseWareByDeleteId);
    }
    /**
     * 管理员及超级管理员查询所有课件
     *
     * @return
     */
    @GetMapping("/getCourseWareList")
    @ApiOperation("查询所有课件")
    public Result getCourseWareList() {

        List<PuFaCourseWare> courseWareAll = courseWareService.getCourseWareAll();

        return Result.success(courseWareAll);
    }

    /**
     * 管理员上传课件
     */
    @PostMapping("/addCourseWare")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
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
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
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
    public Result getCourseWareByUploadTime(@RequestParam("uploadTime") String date) {

        List<PuFaCourseWare> courseWreByUploadTime = courseWareService.getCourseWreByUploadTime(date);

        return Result.success(courseWreByUploadTime);
    }
}
