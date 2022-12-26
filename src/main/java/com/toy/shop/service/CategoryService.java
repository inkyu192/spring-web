package com.toy.shop.service;

import java.util.List;

import static com.toy.shop.dto.CategoryDto.*;

public interface CategoryService {

    Response save(SaveRequest requestDto);

    List<Response> findAll(String searchWord);

    Response findById(Long id);

    Response update(Long id, UpdateRequest requestDto);

    void delete(Long id);
}
