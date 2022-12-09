package com.toy.shop.repository;

import com.toy.shop.domain.Category;

import java.util.List;

public interface CategoryCustomRepository {

    List<Category> findAll(String searchWord);
}
