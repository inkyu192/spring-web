package com.toy.shop.service;

import com.toy.shop.dto.CategoryResponseDto;
import com.toy.shop.dto.CategorySaveRequestDto;
import com.toy.shop.dto.CategoryUpdateRequestDto;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto save(CategorySaveRequestDto requestDto);

    List<CategoryResponseDto> findAll(String searchWord);

    CategoryResponseDto findById(Long id);

    CategoryResponseDto update(Long id, CategoryUpdateRequestDto requestDto);

    void delete(Long id);
}
