package com.pufaschool.conn.exception;

/**
 * 文件格式错误异常
 */
public class FileFormatException extends RuntimeException {

    public FileFormatException(String msg) {
        super(msg);
    }

    public FileFormatException() {
    }
}
