package com.toy.shop.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
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
