package com.pufaschool.conn.exception;

/**
 * 邮箱不存在异常
 */
public class EmailNotFoundException extends RuntimeException {

    public EmailNotFoundException(String msg) {
        super(msg);
    }

    public EmailNotFoundException() {

    }
}
