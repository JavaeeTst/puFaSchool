package com.pufaschool.server.controller;

import com.pufaschool.conn.domain.PuFaActive;
import com.pufaschool.conn.result.Result;
import com.pufaschool.conn.result.Status;
import com.pufaschool.server.service.PuFaActiveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "普法活动控制层(后台)")
@RequestMapping("/system/puFaSchool/server/activity")
public class ActivityController {

    @Autowired
    private PuFaActiveService activeService;

    /**
     * 上传活动
     */
    @ApiOperation("添加活动")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @PostMapping("/addActivity")
    public Result addActivity(@RequestBody PuFaActive active){

        boolean result = activeService.addActivity(active);

        return result?Result.success("上传成功"):Result.error(Status.ADD_ERR,"上传失败");
    }
    /**
     * 修改活动
     */
    @ApiOperation("修改活动")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @PutMapping("/updateActivityById")
    public Result updateById(@RequestBody PuFaActive active){

        boolean result = activeService.updateActivityById(active);

        return result?Result.success("修改成功"):Result.error(Status.UPDATE_ERR,"修改失败");
    }
    /**
     * 删除活动
     */
    @ApiOperation("删除活动")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @DeleteMapping("/deleteActivityById/{id}")
    public Result deleteActivityById(@PathVariable Long id){

        boolean result = activeService.deleteActivityById(id);

        return result?Result.success("删除成功"):Result.error(Status.DELETE_ERR,"删除失败");
    }

}
