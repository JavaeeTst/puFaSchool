package com.pufaschool.client.controller;

import com.pufaschool.conn.domain.PuFaUserApplyAdmin;
import com.pufaschool.conn.result.Result;
import com.pufaschool.conn.result.Status;
import com.pufaschool.server.service.PuFaUserApplyAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/system/puFaSchool/client/userApplyAdmin")
@Api(tags = "用户申请管理员(前端)")
public class ClientUserApplyAdminController {

    @Autowired
    private PuFaUserApplyAdminService applyAdminService;

    /**
     * 用户申请管理员填写
     * @param applyAdmin
     * @return
     */
    @PostMapping("/addUserRequest")
    @ApiOperation("用户申请管理员填写")
    public Result addUserRequest(@RequestBody PuFaUserApplyAdmin applyAdmin){

        boolean result = applyAdminService.requestAdmin(applyAdmin);

        return result?Result.success("提交成功"):Result.error(Status.ADD_ERR,"提交失败");
    }

    /**
     * 用户查询自己的申请
     */
    @GetMapping("/getUserApplyAdminByUserId/{userId}")
    @ApiOperation("用户查询自己的申请")
    public Result getUserApplyAdminByUserId(@PathVariable Long userId){

        List<PuFaUserApplyAdmin> userApplyAdminByUserId = applyAdminService.getUserApplyAdminByUserId(userId);

        return Result.success(userApplyAdminByUserId);
    }


}
