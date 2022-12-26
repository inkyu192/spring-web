package com.toy.shop.service;

import com.toy.shop.domain.Category;
import com.toy.shop.exception.CommonException;
import com.toy.shop.repository.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.toy.shop.common.ResultCode.CATEGORY_NOT_FOUND;
import static com.toy.shop.dto.CategoryDto.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryJpaRepository categoryRepository;
//    private final CategorySpringJpaRepository categoryRepository;

    @Override
    @Transactional
    public Response save(SaveRequest requestDto) {
        Category category = Category.createCategory(requestDto);

        categoryRepository.save(category);

        return new Response(category);
    }

    @Override
    public List<Response> findAll(String searchWord) {
        List<Category> categories = categoryRepository.findAll(searchWord);

        return categories.stream()
                .map(Response::new)
                .collect(Collectors.toList());
    }

    @Override
    public Response findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CommonException(CATEGORY_NOT_FOUND));

        return new Response(category);
    }

    @Override
    @Transactional
    public Response update(Long id, UpdateRequest requestDto) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CommonException(CATEGORY_NOT_FOUND));

        category.updateCategory(requestDto);

        return new Response(category);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CommonException(CATEGORY_NOT_FOUND));

        categoryRepository.delete(category);
    }
}
