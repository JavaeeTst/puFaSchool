package com.pufaschool.conn.exception;

/**
 * 用户错误异常
 */
public class UserErrorException extends RuntimeException {

    public UserErrorException() {

    }

    public UserErrorException(String msg) {
        super(msg);
    }
}
