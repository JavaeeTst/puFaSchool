package com.pufaschool.conn.exception;

/**
 * 验证码错误异常
 */
public class YZMException extends RuntimeException {

    public YZMException(String msg) {
        super(msg);
    }

    public YZMException() {

    }
}
