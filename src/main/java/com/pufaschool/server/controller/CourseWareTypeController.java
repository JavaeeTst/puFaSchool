package com.pufaschool.server.controller;


import com.pufaschool.conn.domain.PuFaCourseWareType;
import com.pufaschool.conn.result.Result;
import com.pufaschool.server.service.PuFaCourseWareService;
import com.pufaschool.server.service.PuFaCourseWareTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/system/puFaSchool/server/courseWareType")
@RestController
@Api(tags = "学习课件类型(后端)")
public class CourseWareTypeController {

    @Autowired
    private PuFaCourseWareTypeService wareService;

    /**
     * 添加学习课件类型
     */
    @PostMapping("/addCourseWareType")
    @ApiOperation("添加学习课件类型")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result addCourseWareType(@RequestBody PuFaCourseWareType puFaCourseWareType) {

        boolean result = wareService.addCourseWareType(puFaCourseWareType);

        return Result.success(result ? "添加成功" : "添加失败");
    }

    /**
     * 删除学习课件类型
     */
    @DeleteMapping("/deleteCourseTypeById/{id}")
    @ApiOperation("删除学习课件类型")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result deleteCourseWareTypeById(@PathVariable Long id) {

        boolean result = wareService.deleteCourseWareType(id);

        return Result.success(result ? "删除成功" : "删除失败");
    }

    @PutMapping("/updateCourseWareType")
    @ApiOperation("修改学习课件类型")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result updateCourseWareType(@RequestBody PuFaCourseWareType wareType) {

        boolean result = wareService.updateCourseWareType(wareType);

        return Result.success(result ? "修改成功" : "修改失败");
    }
}
