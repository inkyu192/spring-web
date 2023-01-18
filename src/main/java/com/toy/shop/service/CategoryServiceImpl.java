package com.toy.shop.service;

import com.toy.shop.common.ResultCode;
import com.toy.shop.domain.Category;
import com.toy.shop.dto.CategoryDto;
import com.toy.shop.exception.CommonException;
import com.toy.shop.repository.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryJpaRepository categoryRepository;
//    private final CategorySpringJpaRepository categoryRepository;

    @Override
    @Transactional
    public CategoryDto.Response saveCategory(CategoryDto.SaveRequest requestDto) {
        Category category = Category.createCategory(requestDto);

        categoryRepository.save(category);

        return new CategoryDto.Response(category);
    }

    @Override
    public List<CategoryDto.Response> categories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(CategoryDto.Response::new)
                .toList();
    }

    @Override
    public CategoryDto.Response category(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CommonException(ResultCode.CATEGORY_NOT_FOUND));

        return new CategoryDto.Response(category);
    }

    @Override
    @Transactional
    public CategoryDto.Response updateCategory(Long id, CategoryDto.UpdateRequest requestDto) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CommonException(ResultCode.CATEGORY_NOT_FOUND));

        category.updateCategory(requestDto);

        return new CategoryDto.Response(category);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CommonException(ResultCode.CATEGORY_NOT_FOUND));

        categoryRepository.delete(category);
    }
}
