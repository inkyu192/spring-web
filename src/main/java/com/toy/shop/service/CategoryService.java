package com.toy.shop.service;

import com.toy.shop.controller.dto.CategoryResponseDto;
import com.toy.shop.controller.dto.CategorySaveRequestDto;
import com.toy.shop.controller.dto.CategoryUpdateRequestDto;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto save(CategorySaveRequestDto requestDto);

    CategoryResponseDto findById(Long id);

    List<CategoryResponseDto> findAll();

    CategoryResponseDto update(Long id, CategoryUpdateRequestDto requestDto);
}
