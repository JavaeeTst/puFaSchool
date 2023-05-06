package com.pufaschool.conn.exception;

/**
 * 密码错误异常
 */
public class PasswordErrorException extends RuntimeException {

    public PasswordErrorException(String msg) {
        super(msg);
    }

    public PasswordErrorException() {

    }
}
