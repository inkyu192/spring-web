package com.toy.shop.exception;

import com.toy.shop.common.ResultCode;
import lombok.Getter;

@Getter
public class DataNotFoundException extends RuntimeException {

    private ResultCode resultCode;

    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataNotFoundException(Throwable cause) {
        super(cause);
    }

    protected DataNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
