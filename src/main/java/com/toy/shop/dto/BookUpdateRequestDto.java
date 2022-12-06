package com.toy.shop.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookUpdateRequestDto {

    private String name;

    private String description;

    private String publisher;

    private String author;

    @Min(100)
    private Integer price;

    @Max(value = 9999)
    private Integer quantity;

    private Long categoryId;
}
