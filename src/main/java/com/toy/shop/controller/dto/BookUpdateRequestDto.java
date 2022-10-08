package com.toy.shop.controller.dto;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
public class BookUpdateRequestDto {

    private String name;

    @Min(100)
    private Integer price;

    @Max(value = 9999)
    private Integer quantity;
}
