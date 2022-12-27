package com.toy.shop.exception;

import com.toy.shop.common.ResultCode;
import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {

    private final ResultCode resultCode;

    public CommonException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
