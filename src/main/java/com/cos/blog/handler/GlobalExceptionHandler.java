package com.cos.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(CommonAppException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<?> handleCommonAppException(CommonAppException e) {
        return new ResponseDto<>(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDto<?> handleAnyException(Exception e) {
        log.info("handleAnyException======================>{}", e.getMessage());
        return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
