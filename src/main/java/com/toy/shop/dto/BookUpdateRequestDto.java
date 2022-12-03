package com.toy.shop.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
