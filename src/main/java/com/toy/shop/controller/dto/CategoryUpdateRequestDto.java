package com.toy.shop.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class CategoryUpdateRequestDto {

    @NotEmpty
    private String name;
}
