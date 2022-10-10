package com.toy.shop.service;

import com.toy.shop.controller.dto.BookResponseDto;
import com.toy.shop.controller.dto.BookSaveRequestDto;
import com.toy.shop.domain.Book;
import com.toy.shop.exception.DataNotFoundException;
import com.toy.shop.repository.BookQueryRepository;
import com.toy.shop.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.toy.shop.common.ResultCode.*;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookQueryRepository bookQueryRepository;

    public List<BookResponseDto> findAll(Long categoryId, String searchWord) {
        List<Book> findBooks = bookQueryRepository.findAll(categoryId, searchWord);

        List<BookResponseDto> collect = findBooks.stream()
                .map(BookResponseDto::new)
                .collect(Collectors.toList());

        return collect;
    }

    public BookResponseDto save(BookSaveRequestDto requestDto) {
        Book book = Book.createBook(requestDto);

        bookRepository.save(book);

        return new BookResponseDto(book);
    }

    public BookResponseDto findById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new DataNotFoundException(BOOK_NOT_FOUND));

        return new BookResponseDto(book);
    }
}
