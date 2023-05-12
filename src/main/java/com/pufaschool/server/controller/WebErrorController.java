package com.pufaschool.server.controller;

import com.pufaschool.conn.result.Result;
import com.pufaschool.conn.result.Status;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebErrorController {
    //响应404状态码接口
    @RequestMapping("/error/404")
    public Result error404() {

        return Result.error(Status.REQUEST_ERR, "抱歉没有找到当前请求");
    }

    @RequestMapping("/error/400")
    //响应400的状态码
    public Result error400() {

        return Result.error(Status.PARAMETER_ERR, "请上传正确的参数");
    }

    @RequestMapping("/error/405")
    public Result error405() {

        return Result.error(Status.NO_REQUEST_METHOD, "当前请求不支持该方式");
    }

    @RequestMapping("/error/500")
    public Result error500() {

        return Result.error(Status.SERVER_ERR, "你干嘛,哎呦(服务器异常，请联系管理员)");
    }

    @RequestMapping("/error/403")
    public Result error403() {
        return Result.error(Status.NO_AUTHORITY, "您没有权限访问(可能没有登录，如果登录还这样，那就是没有权限或者密码错误)");
    }

}
