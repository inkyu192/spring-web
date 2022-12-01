package com.toy.shop.service;

import com.toy.shop.dto.BookResponseDto;
import com.toy.shop.dto.BookSaveRequestDto;
import com.toy.shop.dto.BookUpdateRequestDto;

import java.util.List;

public interface BookService {

    BookResponseDto save(BookSaveRequestDto requestDto);

    List<BookResponseDto> findAll(Long categoryId, String searchWord);

    BookResponseDto findById(Long id);

    BookResponseDto update(Long id, BookUpdateRequestDto requestDto);

    void delete(Long id);
}
