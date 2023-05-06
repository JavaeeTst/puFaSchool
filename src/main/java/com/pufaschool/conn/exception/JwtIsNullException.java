package com.pufaschool.conn.exception;

/**
 * jwt为空异常
 */
public class JwtIsNullException extends RuntimeException {

    public JwtIsNullException(String s) {
        super(s);
    }

    public JwtIsNullException() {

    }
}
