package com.sls.web;

import cn.gjing.result.ErrorResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author sls
 **/
public class UserNotFountException extends RuntimeException {
    public UserNotFountException (String message) {
        super(message);
    }
}
@RestControllerAdvice
class DemoExceptionHandler {
    @ExceptionHandler(UserNotFountException.class)
    public ResponseEntity userNot(UserNotFountException e) {
        return ResponseEntity.badRequest().body(ErrorResult.error(e.getMessage()));
    }
}
