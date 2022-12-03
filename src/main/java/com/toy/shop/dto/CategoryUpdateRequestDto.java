package com.toy.shop.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class CategoryUpdateRequestDto {

    private String name;
}
