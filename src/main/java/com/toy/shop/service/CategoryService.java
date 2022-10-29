package com.toy.shop.service;

import com.toy.shop.controller.dto.CategoryResponseDto;
import com.toy.shop.controller.dto.CategorySaveRequestDto;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto save(CategorySaveRequestDto requestDto);

    CategoryResponseDto findById(Long id);

    List<CategoryResponseDto> findAll();
}
