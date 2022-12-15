package com.toy.shop.repository;

import com.toy.shop.domain.Book;

import java.util.List;

public interface BookQueryRepository {

    List<Book> findAll(Long categoryId, String searchWord);
}
