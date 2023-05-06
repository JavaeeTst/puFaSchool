package com.pufaschool.conn.exception;

/**
 * 角色重复异常
 */
public class RoleRepetitionException extends RuntimeException {

    public RoleRepetitionException(String msg) {
        super(msg);
    }

    public RoleRepetitionException() {

    }
}
