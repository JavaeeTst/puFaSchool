package com.pufaschool.conn.exception;

/**
 * 用户名已存在异常
 */
public class UsernameRepeatException extends RuntimeException {

    public UsernameRepeatException(String msg) {
        super(msg);
    }

    public UsernameRepeatException() {

    }
}
