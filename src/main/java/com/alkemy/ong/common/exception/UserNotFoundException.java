package com.alkemy.ong.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


public class UserNotFoundException extends  RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
