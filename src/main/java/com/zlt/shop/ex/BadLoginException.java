package com.zlt.shop.ex;

/**
 * 用户登录异常
 */
public class BadLoginException extends RuntimeException{
    public BadLoginException(String message) {
        super(message);
    }
}
