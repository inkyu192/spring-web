package com.toy.shop.exception;

import com.toy.shop.common.ResultCode;
import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private ResultCode resultCode;

    public NotFoundException() {
        super();
    }

    public NotFoundException(ResultCode resultCode) {
        super();
        this.resultCode = resultCode;
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    protected NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
