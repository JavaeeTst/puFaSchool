package com.pufaschool.server.controller;

import com.pufaschool.conn.domain.PuFaRole;
import com.pufaschool.conn.domain.vo.SysUserRoleVo;
import com.pufaschool.conn.result.Result;
import com.pufaschool.conn.result.Status;
import com.pufaschool.server.service.PuFaRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/system/puFaSchool/server/role")
@Api(tags = "普法角色接口(后端)")
public class RoleController {

    @Autowired
    private PuFaRoleService puFaRoleService;


    /**
     * 查询所有角色信息
     */
    @ApiOperation("查询所有角色信息")
    @GetMapping("/getRoleList")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public Result getRoleList(){

        List<PuFaRole> roleList = puFaRoleService.getRoleList();

        return Result.success(roleList);
    }
    /**
     * 给用户分配角色(超级管理员)
     *
     * @param roleId
     * @param userId
     * @return
     */
    @ApiOperation("给用户分配角色")
    @PostMapping("/assignRole")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public Result assignRole(@RequestParam("roleId") Long roleId, @RequestParam("userId") Long userId) {

        boolean result = puFaRoleService.assignRole(roleId, userId);

        return result ? Result.success("操作成功") : Result.error(Status.ERROR, "操作失败");

    }

    /**
     * 按username查询用户的角色信息
     */
    @GetMapping("/getRoleByUsernameOrUserId")
    @ApiOperation("查询用户的角色")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result getRoleByUsername(@RequestParam("username") String username) {

        List<PuFaRole> byUsername = puFaRoleService.getRoleByUsernameOrUserId(username, null);

        return Result.success(byUsername);
    }

    /**
     * 启用/解放用户角色接口
     */
    @ApiOperation("冻结/解放用户角色接口")
    @PostMapping("/updateByUserIdAndRoleId")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    public Result updateByUserIdAndRoleId(@RequestBody SysUserRoleVo vo, HttpServletRequest request) {

        if (vo.getIsCancel() == null) {
            vo.setIsCancel(1);
        }
        boolean result = puFaRoleService.updateByUserIdAndRoleId(vo,request);

        return result ? Result.success(vo.getIsCancel() == 0 ? "启用成功" : "禁用成功") : Result.error(Status.ERROR, vo.getIsCancel() == 0 ? "启用失败" : "禁用失败");
    }


}
