package com.toy.shop.controller.dto;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class BookSaveRequestDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotEmpty
    private String publisher;

    @NotEmpty
    private String author;

    @NotNull
    @Min(100)
    private Integer price;

    @NotNull
    @Max(value = 9999)
    private Integer quantity;

    @NotNull
    private Long categoryId;
}
