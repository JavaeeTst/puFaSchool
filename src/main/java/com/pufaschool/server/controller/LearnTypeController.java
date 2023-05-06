package com.pufaschool.server.controller;

import com.pufaschool.conn.domain.PuFaLearnType;
import com.pufaschool.conn.result.Result;
import com.pufaschool.server.service.PuFaLearnTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/puFaSchool/server/learnType")
@Api(tags = "学习类型接口(后端)")
public class LearnTypeController {

    @Autowired
    private PuFaLearnTypeService learnTypeService;

    /**
     * 查询所有学习类型
     * @return
     */
    @GetMapping("/getLearnTypeAll")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @ApiOperation("查询所有学习类型")
    public Result getLearnTypeAll(){

        List<PuFaLearnType> learnTypeAll = learnTypeService.getLearnTypeAll();

        return Result.success(learnTypeAll);

    }
    /**
     * 按id修改学习类型
     */
    @PostMapping("/updateLearnTypeByLearnId")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @ApiOperation("按id修改学习类型")
    public Result updateLearnTypeByLearnId(@RequestBody PuFaLearnType learnType){

        boolean result = learnTypeService.updateLearnByLearnId(learnType);

        return Result.success(result?"修改成功":"修改失败");

    }
    /**
     * 按id删除学习类型
     */
    @PostMapping("/deleteLearnTypeByLearnId")
    @ApiOperation("按id删除学习类型")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result deleteLearnTypeLearnId(@RequestParam("id")Long id){

        boolean result = learnTypeService.deleteLearnTypeByLearnId(id);

        return Result.success(result?"删除成功":"删除失败");

    }
    /**
     * 按id查询学习类型
     */
    @GetMapping("/getLearnTypeByLearnId/{id}")
    @ApiOperation("按id删除学习类型")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result getLearnTypeByLearnId(@PathVariable Long id){

       PuFaLearnType result = learnTypeService.getLearnTypeByLearnTypeId(id);

        return Result.success(result);

    }
    /**
     * 按创建人查询学习类型
     */
    @GetMapping("/getLearnTypeByFounder")
    @ApiOperation("按创建人查询学习类型")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result getLearnTypeByFounder(@RequestParam("founder")String founder){

        List<PuFaLearnType> learnTypeByFounder = learnTypeService.getLearnTypeByFounder(founder);

        return Result.success(learnTypeByFounder);
    }


}
