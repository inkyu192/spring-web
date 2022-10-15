package com.toy.shop.service;

import com.toy.shop.controller.dto.CategoryResponseDto;
import com.toy.shop.controller.dto.CategorySaveRequestDto;

public interface CategoryService {

    CategoryResponseDto save(CategorySaveRequestDto requestDto);

    CategoryResponseDto findById(Long id);
}
