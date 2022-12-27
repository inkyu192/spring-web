package com.toy.shop.service;

import com.toy.shop.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto.Response save(CategoryDto.SaveRequest requestDto);

    List<CategoryDto.Response> findAll(String searchWord);

    CategoryDto.Response findById(Long id);

    CategoryDto.Response update(Long id, CategoryDto.UpdateRequest requestDto);

    void delete(Long id);
}
