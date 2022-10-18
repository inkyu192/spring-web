package com.toy.shop.service;

import com.toy.shop.controller.dto.BookResponseDto;
import com.toy.shop.controller.dto.BookSaveRequestDto;
import com.toy.shop.controller.dto.BookUpdateRequestDto;

import java.util.List;

public interface BookService {

    BookResponseDto save(BookSaveRequestDto requestDto);

    BookResponseDto findById(Long id);

    List<BookResponseDto> findAll(Long categoryId, String searchWord);

    BookResponseDto update(Long id, BookUpdateRequestDto requestDto);

    void delete(Long id);
}
