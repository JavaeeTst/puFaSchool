package com.pufaschool.conn.exception;

/**
 * 非管理员异常
 */
public class NonAdminException extends RuntimeException {

    public NonAdminException() {

    }

    public NonAdminException(String msg) {

        super(msg);
    }
}
