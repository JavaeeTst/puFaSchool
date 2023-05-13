package com.pufaschool.client.controller;

import com.pufaschool.client.service.ClientUserService;
import com.pufaschool.conn.domain.PuFaUser;
import com.pufaschool.conn.domain.vo.EmailVo;
import com.pufaschool.conn.domain.vo.SysUserUpdatePasswordVo;
import com.pufaschool.conn.result.Result;
import com.pufaschool.conn.result.Status;
import com.pufaschool.conn.utils.CodeUtil;
import com.pufaschool.conn.utils.JWTUtils;
import com.pufaschool.server.service.PuFaUserService;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "普法用户接口(前端)")
@RequestMapping("system/puFaSchool/client/user")
@RestController
public class ClientUserController {

    /**
     * 用户端用户业务层注入(实际设计出问题了)
     */
    @Autowired
    private ClientUserService userService;

    /**
     * 服务端用户业务层注入
     */
    @Autowired
    private PuFaUserService serverUserService;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 用户修改邮箱
     */
    @ApiOperation("用户修改邮箱")
    @PostMapping("/updateUserEmailByUserId")
    public Result updateUserEmailByUserId(@RequestBody EmailVo vo){

        boolean result = serverUserService.updateUserEmailByUserId(vo);


        return result?Result.success("修改成功"):Result.error(Status.ERROR,"修改失败");
    }

    /**
     * 用户注册接口
     */
    @ApiOperation("用户注册接口")
    @PostMapping("/addUser")
    public Result addUser(@Validated @RequestBody PuFaUser puFaUser, @RequestParam("code") String code) {

        boolean result = userService.addUser(puFaUser, code);

        return result ? Result.success("注册成功") : Result.error(Status.USER_REPEAT, "注册失败");
    }

    /**
     * 用户修改密码
     */
    @ApiOperation("用户修改密码")
    @PostMapping("/updatePassword")
    public Result updatePassword(@RequestBody SysUserUpdatePasswordVo vo) {

        boolean result = userService.updatePassword(vo);

        return result ? Result.success("修改成功") : Result.error(Status.ERROR, "修改失败");

    }

    /**
     * 发送邮箱验证码
     */
    @ApiOperation("QQ邮箱验证")
    @GetMapping("/email/{email}")
    public Result sendEmail(@PathVariable String email) {

        //先查询缓存是否有验证码
        String code = (String) redisTemplate.opsForValue().get(email);
        if (code != null) {
            return Result.success("验证码已经发送，请不要重复，请等待该验证码过期");
        }
        //没有就生成一个
        code = CodeUtil.code(6);

        userService.sendEmail(email, code);

        return Result.success("发送完成");
    }

    /**
     * 用户找回密码
     */
    @ApiOperation("用户找回密码")
    @PostMapping("/passwordRetrieval")
    public Result passwordRetrieval(@RequestParam("email") String email, @RequestParam("code") String code, @RequestBody SysUserUpdatePasswordVo vo) {

        boolean result = userService.passwordRetrieval(email, code, vo);

        return Result.success(result ? "修改成功" : "修改失败");


    }

    /**
     * 用户修改个人信息
     */
    @ApiOperation("用户修改个人信息")
    @PostMapping("/updateByUserId")
    public Result updateById(@Validated @RequestBody PuFaUser puFaUser) {

        boolean result = userService.updateByUserId(puFaUser);

        return result ? Result.success("修改成功") : Result.error(Status.ERROR, "修改失败");

    }
    /**
     * 用户按id查询自己的个人信息
     */
    @ApiOperation("用户按id查询自己的信息")
    @GetMapping("/getUserByUserId")
    public Result getUserByUserId(HttpServletRequest request){

        PuFaUser userByUserId = serverUserService.getUserByUserId(request);

        return Result.success(userByUserId);

    }
    /**
     * 用户默认头像
     */
    @ApiOperation("用户默认头像")
    @GetMapping("/userAvatars")
    public Result userAvatars(String role){

        List<String> userAndAdminDefaultAvatar = serverUserService.getUserAndAdminDefaultAvatar(role);

        return Result.success(userAndAdminDefaultAvatar);
    }
}
