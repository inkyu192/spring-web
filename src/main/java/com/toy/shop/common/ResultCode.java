package com.toy.shop.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResultCode {

    SUCCESS("200", "정상"),
    NOT_VALID("400", "필수 파라미터 오류"),
    ERROR("500", "시스템 오류가 발생하였습니다."),

    CATEGORY_NOT_FOUND("C0001", "카테고리가 존재하지 않습니다."),
    BOOK_NOT_FOUND("B0001", "책이 존재하지 않습니다.");

    private final String code;
    private final String message;
}
