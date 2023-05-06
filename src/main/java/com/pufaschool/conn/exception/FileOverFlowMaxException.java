package com.pufaschool.conn.exception;

/**
 * 文件超出最大异常
 */
public class FileOverFlowMaxException extends RuntimeException {

    public FileOverFlowMaxException(String msg) {
        super(msg);
    }

    public FileOverFlowMaxException() {

    }
}
