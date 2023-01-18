package com.toy.shop.service;

import com.toy.shop.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto.Response saveCategory(CategoryDto.SaveRequest requestDto);

    List<CategoryDto.Response> categories();

    CategoryDto.Response category(Long id);

    CategoryDto.Response updateCategory(Long id, CategoryDto.UpdateRequest requestDto);

    void deleteCategory(Long id);
}
