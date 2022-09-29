package com.toy.shop.exception;

import com.toy.shop.controller.dto.ApiResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserException.class)
    public ApiResultResponse UserExceptionHandler(UserException e) {
        log.error("[UserExceptionHandler]", e);
        return new ApiResultResponse("400", "사용자 에러", null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResultResponse MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("[MethodArgumentNotValidExceptionHandler]", e);
        return new ApiResultResponse("400", "필수 파라미터 오류", null);
    }

    @ExceptionHandler(Exception.class)
    public ApiResultResponse ExceptionHandler(Exception e) {
        log.error("[ExceptionHandler]", e);
        return new ApiResultResponse("500", "시스템 오류가 발생하였습니다.", null);
    }
}
