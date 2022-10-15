package com.toy.shop.service;

import com.toy.shop.controller.dto.BookResponseDto;
import com.toy.shop.controller.dto.BookSaveRequestDto;

import java.util.List;

public interface BookService {

    BookResponseDto save(BookSaveRequestDto requestDto);

    BookResponseDto findById(Long id);

    List<BookResponseDto> findAll(Long categoryId, String searchWord);
}
