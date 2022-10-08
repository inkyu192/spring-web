package com.toy.shop.service;

import com.toy.shop.controller.dto.BookSaveRequestDto;
import com.toy.shop.controller.dto.BookSaveResponseDto;
import com.toy.shop.domain.Book;
import com.toy.shop.repository.BookQueryRepository;
import com.toy.shop.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookQueryRepository bookQueryRepository;

    public BookSaveResponseDto save(BookSaveRequestDto requestDto) {
        Book book = Book.createBook(requestDto);

        bookRepository.save(book);

        return new BookSaveResponseDto(book);
    }
}
