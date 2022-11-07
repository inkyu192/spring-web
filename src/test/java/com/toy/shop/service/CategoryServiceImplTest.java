package com.toy.shop.service;

import com.toy.shop.domain.Category;
import com.toy.shop.dto.CategorySaveRequestDto;
import com.toy.shop.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void save() {
        CategorySaveRequestDto requestDto = new CategorySaveRequestDto();
        requestDto.setName("category1");

        Long saveId = categoryService.save(requestDto).getId();

        Category findCategory = categoryRepository.findById(saveId).get();

        assertThat(saveId).isEqualTo(findCategory.getId());
    }
}