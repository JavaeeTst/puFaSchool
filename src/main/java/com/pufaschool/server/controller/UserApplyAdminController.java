package com.pufaschool.server.controller;

import com.pufaschool.conn.domain.PuFaUserApplyAdmin;
import com.pufaschool.conn.result.Result;
import com.pufaschool.conn.result.Status;
import com.pufaschool.server.service.PuFaUserApplyAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户申请管理员控制层
 */
@Api(tags = "用户申请管理员(后端)")
@RestController
@RequestMapping("/system/puFaSchool/server/userApplyAdmin")
public class UserApplyAdminController {

    @Autowired
    private PuFaUserApplyAdminService applyAdminService;

    /**
     * 超级管理员查询用户的申请
     */
    @ApiOperation("超级管理员查询用户的申请")
    @GetMapping("/getUserApplyAdminAll")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public Result getUserRequest(){

        List<PuFaUserApplyAdmin> userApplyAdminAll = applyAdminService.getUserApplyAdminAll();

        return Result.success(userApplyAdminAll);
    }
    /**
     * 超级管理员按id查询用户申请管理员
     */
    @ApiOperation("超级管理员按申请id查询用户申请管理员")
    @GetMapping("/getUserApplyAdminById/{id}")
    public Result getUserApplyAdminById(@PathVariable Long id){

        PuFaUserApplyAdmin userApplyAdminByRequestId = applyAdminService.getUserApplyAdminByRequestId(id);

        return Result.success(userApplyAdminByRequestId);

    }
    /**
     * 超级管理员(同意拒绝)用户申请管理员
     *
     */
    @ApiOperation("超级管理员(同意拒绝用户申请管理员)")
    @PutMapping("/updateUserApplyAdminReviewerStatus")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public Result updateUserApplyAdminReviewerStatus(@RequestParam("id")Long id,@RequestParam("userId")Long userId,@RequestParam("status")Integer status){

        boolean result = applyAdminService.modifyUserApplyAdminReviewsStatusById(status, id, userId);

        return result?Result.success(status==1?"拒绝成功":"同意"):Result.error(Status.ERROR,status==1?"拒绝失败":"拒绝成功");
    }
}
