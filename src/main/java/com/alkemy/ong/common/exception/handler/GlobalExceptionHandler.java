package com.alkemy.ong.common.exception.handler;

import com.alkemy.ong.common.exception.ConflictException;
import com.alkemy.ong.common.exception.NotFoundException;
import com.alkemy.ong.common.exception.error.ErrorCode;
import com.alkemy.ong.common.exception.error.ErrorDetails;
import com.alkemy.ong.common.exception.error.ErrorLocation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public final class GlobalExceptionHandler extends AbstractExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ErrorDetails> handleNotFound(NotFoundException ex) {

        ErrorDetails error = ErrorDetails.builder()
                .code(ErrorCode.RESOURCE_NOT_FOUND)
                .detail("The resource with id %s is not found".formatted(ex.getResourceId()))
                .location(ErrorLocation.PATH)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
    @ExceptionHandler(ConflictException.class)
    private ResponseEntity<ErrorDetails> handleConflict(ConflictException ex) {

        ErrorDetails error = ErrorDetails.builder()
                .code(ErrorCode.RESOURCE_ALREADY_EXISTS)
                .detail("%s".formatted(ex.getMsg()))
                .location(ErrorLocation.PATH)
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
