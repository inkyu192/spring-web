package com.toy.shop.service;

import com.toy.shop.entity.Book;
import com.toy.shop.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

//    private final ItemQueryRepository itemQueryRepository;

    public Book save(Book book) {
        return bookRepository.save(book);
    }
}
