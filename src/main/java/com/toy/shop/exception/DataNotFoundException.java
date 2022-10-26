package com.toy.shop.exception;

import com.toy.shop.common.ResultCode;
import lombok.Getter;

@Getter
public class DataNotFoundException extends RuntimeException {

    private ResultCode resultCode;

    public DataNotFoundException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
