package com.toy.shop.controller.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class CategorySaveRequestDto {

    @NotEmpty
    private String name;
}
