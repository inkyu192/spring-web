package com.toy.shop.controller.dto;

import com.toy.shop.domain.Book;
import lombok.Getter;

@Getter
public class BookSaveResponseDto {

    private Long id;

    public BookSaveResponseDto(Book book) {
        id = book.getId();
    }
}
