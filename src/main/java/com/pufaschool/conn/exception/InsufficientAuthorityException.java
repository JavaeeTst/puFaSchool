package com.pufaschool.conn.exception;

/**
 * 权限不足异常
 */
public class InsufficientAuthorityException extends RuntimeException {

    public InsufficientAuthorityException(String msg){
        super(msg);
    }

    public InsufficientAuthorityException(){

    }
}
