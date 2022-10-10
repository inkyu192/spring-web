package com.toy.shop.service;

import com.toy.shop.controller.dto.CategoryResponseDto;
import com.toy.shop.controller.dto.CategorySaveRequestDto;
import com.toy.shop.domain.Category;
import com.toy.shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryResponseDto save(CategorySaveRequestDto requestDto) {
        Category category = Category.createCategory(requestDto);

        categoryRepository.save(category);

        return new CategoryResponseDto(category);
    }
}
