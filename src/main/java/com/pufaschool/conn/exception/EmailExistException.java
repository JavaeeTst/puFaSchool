package com.pufaschool.conn.exception;

/**
 * 邮箱已存在异常
 */
public class EmailExistException extends RuntimeException {

    public EmailExistException(String msg){

        super(msg);
    }
    public EmailExistException(){

    }
}
