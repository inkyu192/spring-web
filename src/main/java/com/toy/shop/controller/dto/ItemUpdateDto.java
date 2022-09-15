package com.toy.shop.controller.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
public class ItemUpdateDto {

    private String name;

    @Min(100)
    private Integer price;

    @Max(value = 9999)
    private Integer quantity;
}
