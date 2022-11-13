package com.toy.shop.service;

import com.toy.shop.domain.Category;
import com.toy.shop.dto.CategoryResponseDto;
import com.toy.shop.dto.CategorySaveRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Transactional
@SpringBootTest
class CategoryServiceImplTest {

    @Autowired
    CategoryService categoryService;

    @PersistenceContext
    EntityManager em;

    @Test
    void save() {
        CategorySaveRequestDto requestDto = new CategorySaveRequestDto();
        requestDto.setName("category1");

        Long saveId = categoryService.save(requestDto).getId();

        Category findCategory = em.find(Category.class, saveId);

        assertThat(saveId).isEqualTo(findCategory.getId());
    }

    @Test
    void findById() {
        CategorySaveRequestDto requestDto = new CategorySaveRequestDto();
        requestDto.setName("category1");

        Category saveCategory = Category.createCategory(requestDto);
        em.persist(saveCategory);

        em.flush();
        em.clear();

        CategoryResponseDto responseDto = categoryService.findById(saveCategory.getId());

        Assertions.assertThat(saveCategory.getId()).isEqualTo(responseDto.getId());
    }

    @Test
    void findAll() {
        CategorySaveRequestDto requestDto1 = new CategorySaveRequestDto();
        requestDto1.setName("category1");

        Category saveCategory1 = Category.createCategory(requestDto1);
        em.persist(saveCategory1);

        CategorySaveRequestDto requestDto2 = new CategorySaveRequestDto();
        requestDto2.setName("category2");

        Category saveCategory2 = Category.createCategory(requestDto2);
        em.persist(saveCategory2);

        em.flush();
        em.clear();

        List<CategoryResponseDto> list = categoryService.findAll();

        List<Long> collect = list.stream()
                .map(CategoryResponseDto::getId)
                .toList();

        Assertions.assertThat(collect).containsOnly(saveCategory1.getId(), saveCategory2.getId());
    }
}