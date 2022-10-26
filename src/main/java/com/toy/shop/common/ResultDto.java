package com.toy.shop.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import static com.toy.shop.common.ResultCode.SUCCESS;

@Getter
public class ResultDto<T> {

    private String resultCode;
    private String resultMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ResultDto(T data) {
        this.resultCode = SUCCESS.getCode();
        this.resultMessage = SUCCESS.getMessage();
        this.data = data;
    }

    public ResultDto(String resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }
}
