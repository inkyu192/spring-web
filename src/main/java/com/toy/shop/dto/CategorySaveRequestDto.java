package com.toy.shop.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CategorySaveRequestDto {

    @NotEmpty
    private String name;
}
