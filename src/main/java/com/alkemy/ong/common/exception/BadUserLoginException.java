package com.alkemy.ong.common.exception;

public class BadUserLoginException extends RuntimeException{

    public BadUserLoginException(String message) {
        super(message);
    }
}
