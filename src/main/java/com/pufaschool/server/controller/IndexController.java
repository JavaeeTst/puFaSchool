package com.pufaschool.server.controller;

import com.pufaschool.conn.domain.PuFaIndex;
import com.pufaschool.conn.domain.PuFaUser;
import com.pufaschool.conn.result.Result;
import com.pufaschool.conn.utils.JWTUtils;
import com.pufaschool.server.service.PuFaIndexService;
import com.pufaschool.server.service.PuFaUserService;
import com.pufaschool.server.service.impl.LoginServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "登录接口(后端)")
@RestController
@RequestMapping("/system/puFaSchool/server")
public class IndexController {

    @Autowired
    private LoginServiceImpl loginService;

    @Autowired
    private PuFaUserService userService;





    /**
     * 后台管理员登录
     *
     * @param puFaUser
     * @return
     */
    @PostMapping("/loginServer")
    @ApiOperation("管理员登录(后端)")
    public Result loginServer(@RequestBody PuFaUser puFaUser) {


        return loginService.login(puFaUser);
    }
    /**
     * 用户登录
     */

    @PostMapping("/loginClient")
    @ApiOperation("用户登录(前端)")
    public Result loginClient(@RequestParam("username")String username, @RequestParam("password")String password ){

        //创建map集合
        Map<String,Object> map=new HashMap<>();

        PuFaUser login = userService.login(username, password);

        //生成token
        String token = JWTUtils.generatorToken(login);


        return Result.success(token);



    }




}
