package com.toy.shop.controller.dto;

import com.toy.shop.domain.Category;
import lombok.Getter;

@Getter
public class CategoryResponseDto {

    private Long id;
    private String name;

    public CategoryResponseDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
