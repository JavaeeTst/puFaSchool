package com.pufaschool.conn.utils;

/**
 * 日志工具类
 */
public class LogUtil {

    //查询日志
    public static final Integer QUERY_LOG = 1;

    //添加日志
    public static final Integer ADD_LOG = 2;

    //修改日志
    public static final Integer UPDATE_TYPE = 3;

    //删除日志
    public static final Integer DELETE_LOG = 4;

    //登录日志
    public static final Integer LOGIN_LOG=5;

    //正常状态
    public static final Integer CONN_IS_CODE=0;

    //非正常状态(已删除冻结)
    public static final Integer CONN_NO_CODE=1;

    //同意状态
    public static final Integer AGREE=2;
}
