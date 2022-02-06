package com.zlt.shop.ex;

/**
 * 注册时异常
 */
public class BadRegisterException extends RuntimeException{
    public BadRegisterException(String message) {
        super(message);
    }
}
