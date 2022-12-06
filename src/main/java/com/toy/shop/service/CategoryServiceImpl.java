package com.toy.shop.service;

import com.toy.shop.domain.Category;
import com.toy.shop.dto.CategoryResponseDto;
import com.toy.shop.dto.CategorySaveRequestDto;
import com.toy.shop.dto.CategoryUpdateRequestDto;
import com.toy.shop.exception.DataNotFoundException;
import com.toy.shop.repository.CategoryQueryRepository;
import com.toy.shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.toy.shop.common.ResultCode.CATEGORY_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryQueryRepository categoryQueryRepository;

    @Override

    public CategoryResponseDto save(CategorySaveRequestDto requestDto) {
        Category category = Category.createCategory(requestDto);

        categoryRepository.save(category);

        return new CategoryResponseDto(category);
    }

    @Override
    public List<CategoryResponseDto> findAll(String searchWord) {
        List<Category> categories = categoryQueryRepository.findAll(searchWord);

        return categories.stream()
                .map(CategoryResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDto findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new DataNotFoundException(CATEGORY_NOT_FOUND));

        return new CategoryResponseDto(category);
    }

    @Override
    public CategoryResponseDto update(Long id, CategoryUpdateRequestDto requestDto) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new DataNotFoundException(CATEGORY_NOT_FOUND));

        category.updateCategory(requestDto);

        return new CategoryResponseDto(category);
    }

    @Override
    public void delete(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new DataNotFoundException(CATEGORY_NOT_FOUND));

        categoryRepository.delete(category);
    }
}
