package com.toy.shop.repository;

import com.toy.shop.domain.Category;

import java.util.List;

public interface CategoryQueryRepository {

    List<Category> findAll(String searchWord);
}
