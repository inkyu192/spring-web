package com.toy.shop.dto;

import com.toy.shop.domain.Category;
import lombok.Getter;

@Getter
public class CategoryResponseDto {

    private Long id;
    private String name;

    private String description;

    public CategoryResponseDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
    }
}
