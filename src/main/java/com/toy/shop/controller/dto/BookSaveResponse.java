package com.toy.shop.controller.dto;

import com.toy.shop.domain.Book;
import lombok.Getter;

@Getter
public class BookSaveResponse {

    private Long id;

    public BookSaveResponse(Book book) {
        id = book.getId();
    }
}
