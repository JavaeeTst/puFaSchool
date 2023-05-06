package com.pufaschool.conn.exception;

/**
 * 用户未找到异常
 */
public class UsernameNotFoundExceptions extends RuntimeException {

    public UsernameNotFoundExceptions(String msg) {
        super(msg);
    }

    public UsernameNotFoundExceptions() {

    }
}
