package com.pufaschool.conn.exception;

/**
 * 用户已被冻结异常
 */
public class UsernameFreezeException extends RuntimeException {

    public UsernameFreezeException() {
    }

    public UsernameFreezeException(String msg) {
        super(msg);
    }
}
