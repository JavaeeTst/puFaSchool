package com.pufaschool.conn.result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 自定义响应状态码
 */
@Api(tags = "自定义状态码")
public class Status {


    //成功
    public static final Integer SUCCESS = 200;
    //用户名已经被冻结
    public static final Integer USER_FROZEN = 303;
    //失败
    public static final Integer ERROR = 201;
    //用户名错误不存在
    public static final Integer USER_ERR = 202;
    //密码错误
    public static final Integer PASSWORD_ERR = 203;
    //用户名已存在
    public static final Integer USER_REPEAT = 204;
    //角色重复
    public static final Integer ROLE_REPETITION = 205;
    //方法参数无效(数据校验)
    public static final Integer METHOD_PARAMETER_ERR = 208;
    //验证码异常
    public static final Integer YZM_ERR = 209;
    //邮箱未找到
    public static final Integer EMAIL_NOT_FOUND = 210;
    //权限不足
    public static final Integer NO_AUTHORITY = 403;
    //参数异常
    public static final Integer PARAMETER_ERR = 400;
    //没有请求
    public static final Integer REQUEST_ERR = 404;
    //服务器异常
    public static final Integer SERVER_ERR = 500;
    //请求方法不支持
    public static final Integer NO_REQUEST_METHOD = 405;
    //token异常
    public static final Integer TOKEN_ERR = 409;
    //文件格式异常
    public static final Integer FILE_FORMAT_ERR = 501;
    //文件超出最大异常
    public static final Integer FILE_OVERFLOW_ERR = 502;
    //token为null异常
    public static final Integer TOKEN_NULL = 410;
    //非管理员异常
    public static final Integer NON_ADMIN = 411;

}
