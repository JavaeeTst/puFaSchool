package com.pufaschool.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.pufaschool.conn.domain.PuFaUser;
import com.pufaschool.conn.domain.vo.SysUserAttributeVo;
import com.pufaschool.conn.domain.vo.SysUserUpdatePasswordVo;
import com.pufaschool.conn.result.Result;
import com.pufaschool.conn.result.Status;
import com.pufaschool.server.service.PuFaUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * user控制层
 */
@Api(tags = "普法用户接口(后端)")
@RestController
@RequestMapping("/system/puFaSchool/server/user")
public class UserController {

    //注入用户service对象
    @Autowired
    private PuFaUserService puFaUserService;

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 按用户属性查询用户
     */
    @ApiOperation("按用户属性查询用户")
    @PostMapping("/getUserByUserAttribute")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result getUserByUserAttribute(@RequestBody SysUserAttributeVo vo){

        List<PuFaUser> userByUserAttribute = puFaUserService.getUserByUserAttribute(vo);

        return Result.success(userByUserAttribute);
    }
    /**
     * 查询被删除的用户
     */
    @GetMapping("/getDeleteUser")
    @ApiOperation("查询被删除的用户")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result getDeleteUser(){

        List<PuFaUser> deleteUser = puFaUserService.getDeleteUser();

        return Result.success(deleteUser);
    }
    /**
     * 按id修改用户接口
     */
    @ApiOperation("用户修改个人信息")
    @PostMapping("/updateByUserId")
    public Result updateById(@Validated @RequestBody PuFaUser puFaUser) {

        boolean result = puFaUserService.updateById(puFaUser);

        return result ? Result.success("修改成功") : Result.error(Status.ERROR, "修改失败");

    }

    /**
     * 按id删除用户(管理员专用)
     */
    @ApiOperation("按id删除用户")
    @DeleteMapping("/deleteById/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result deleteById(@PathVariable("id") Long id) {

        boolean result = puFaUserService.deleteByUserId(id);

        return result ? Result.success("删除成功") : Result.error(Status.ERROR, "删除失败");
    }

    /**
     * 按状态查询用户
     */
    @ApiOperation("按状态查询用户")
    @GetMapping("/getUserByStatus}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result getUserByStatus(@RequestParam("status") Integer status) {

        List<PuFaUser> userByStatus = puFaUserService.getUserByStatus(status);

        return Result.success(userByStatus);
    }

    /**
     * 分页查询用户
     */
    @ApiOperation("分页查询用户")
    @GetMapping("/getPageList/{indexPage}/{lastPage}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result getPageList(@PathVariable("indexPage") Integer indexPage,
                              @PathVariable("lastPage") Integer lastPage) {
        long begin = System.currentTimeMillis();

        Page<PuFaUser> page = new Page<>(indexPage, lastPage);

        IPage<PuFaUser> pageList = puFaUserService.getPageList(page);

        long end = System.currentTimeMillis();

        System.out.println("程序执行" + (end - begin) + "毫秒");

        return Result.success(pageList);
    }

    /**
     * 用户模糊查询接口
     */
    @ApiOperation("模糊查询用户")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @GetMapping("/getByUserFiled")
    public Result getByUserFiled(SysUserUpdatePasswordVo vo) {

        long begin = System.currentTimeMillis();

        List<PuFaUser> byUserFiled = puFaUserService.getByUserFiled(vo);

        long end = System.currentTimeMillis();

        System.out.println("程序执行" + (end - begin) + "毫秒");

        return Result.success(byUserFiled);

    }
//    /**
//     * 发送邮箱验证码
//     */
//    @ApiOperation("QQ邮箱验证")
//    @GetMapping("/email/{email}")
//    public Result sendEmail(@PathVariable String email){
//
//        //先查询缓存是否有验证码
//        String code=(String) redisTemplate.opsForValue().get(email);
//        if(code!=null){
//            return Result.success("发送完成");
//        }
//        //没有就生成一个
//        code=CodeUtil.code(6);
//
//        puFaUserService.sendEmail(email,code);
//
//        return Result.success("发送完成");
//    }
//    /**
//     * 用户找回密码
//     */
//    @ApiOperation("用户找回密码")
//    @PostMapping("/passwordRetrieval")
//    public Result passwordRetrieval(@RequestParam("email") String email,@RequestParam("code") String code,@RequestBody SysUserUpdatePasswordVo vo){
//
//        boolean result = puFaUserService.passwordRetrieval(email, code, vo);
//
//        return Result.success(result?"修改成功":"修改失败");
//
//
//    }


    /**
     * 按邮箱查找用户
     */
    @ApiOperation("按邮箱查找用户")
    @GetMapping("/getUserByEmail")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result getUserByEmail(@RequestParam("email") String email) throws JsonProcessingException {

        long begin = System.currentTimeMillis();

        Object userByEmail = puFaUserService.getUserByEmail(email);

        long end = System.currentTimeMillis();

        System.out.println("程序执行" + (end - begin) + "毫秒");


        return Result.success(userByEmail);
    }

    /**
     * 按用户名查询
     */
    @ApiOperation("按用户名查询")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @GetMapping("/getUserByUsername")
    public Result getUserByUsername(@RequestParam("username") String username) {

        LambdaQueryWrapper<PuFaUser> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(PuFaUser::getUsername, username);

        PuFaUser getUserByUsername = puFaUserService.getOne(wrapper);

        return Result.success(getUserByUsername);

    }

    /**
     * 按创建时间查询用户
     */
    @ApiOperation("按创建时间查询用户")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @GetMapping("/getUserByCreateTime")
    public Result getUserByCreateTime(@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam("date") Date date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String format = dateFormat.format(date);


        List<PuFaUser> userByCreateTime = puFaUserService.getUserByCreateTime(format);

        return Result.success(userByCreateTime);
    }

    /**
     * 查询所有的管理员(超级管理员)
     */
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @GetMapping("/getAdminAll")
    @ApiOperation("查询所有的管理员(超级管理员)")
    public Result getAdminAll() {

        List<PuFaUser> adminAll = puFaUserService.getAdminAll();

        return Result.success(adminAll);
    }

    /**
     * 冻结(启用)用户
     */
    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @PostMapping("/updateUserStatus/{status}/{userId}")
    @ApiOperation("启用(禁用)用户")
    public Result updateUserStatus(@PathVariable Integer status, @PathVariable Long userId, HttpServletRequest request) {

        boolean result = puFaUserService.updateUserStatus(status, userId,request);

        return Result.success(result ? status == 1 ? "冻结成功" : "冻结失败" : status == 0 ? "启用成功" : "启用失败");
    }

    /**
     * 管理员默认头像
     */
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @GetMapping("/adminAvatars")
    @ApiOperation("管理员默认头像")
    public Result adminAvatar(String role) {

        role = "ADMIN";

        List<String> userAndAdminDefaultAvatar = puFaUserService.getUserAndAdminDefaultAvatar(role);

        return Result.success(userAndAdminDefaultAvatar);
    }

}
