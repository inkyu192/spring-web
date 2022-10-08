package com.toy.shop.controller.dto;

import com.toy.shop.domain.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class BookResponseDto {

    private Long id;
    private String name;
    private int price;

    public BookResponseDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.price = book.getPrice();
    }
}
