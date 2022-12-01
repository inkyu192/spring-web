package com.toy.shop.service;

import com.toy.shop.domain.Category;
import com.toy.shop.dto.CategoryResponseDto;
import com.toy.shop.dto.CategorySaveRequestDto;
import com.toy.shop.dto.CategoryUpdateRequestDto;
import lombok.extern.slf4j.Slf4j;
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

        em.flush();
        em.clear();

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

        assertThat(saveCategory.getId()).isEqualTo(responseDto.getId());
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

        List<CategoryResponseDto> list = categoryService.findAll(null);

        List<Long> collect = list.stream()
                .map(CategoryResponseDto::getId)
                .toList();

        assertThat(collect).containsOnly(saveCategory1.getId(), saveCategory2.getId());
    }

    @Test
    void update() {
        CategorySaveRequestDto saveRequestDto = new CategorySaveRequestDto();
        saveRequestDto.setName("category1");

        Category saveCategory = Category.createCategory(saveRequestDto);
        em.persist(saveCategory);

        em.flush();
        em.clear();

        CategoryUpdateRequestDto updateRequestDto = new CategoryUpdateRequestDto();
        updateRequestDto.setName("category2");

        categoryService.update(saveCategory.getId(), updateRequestDto);

        Category category = em.find(Category.class, saveCategory.getId());

        assertThat(updateRequestDto.getName()).isEqualTo(category.getName());
    }

    @Test
    void delete() {
        CategorySaveRequestDto saveRequestDto = new CategorySaveRequestDto();
        saveRequestDto.setName("category1");

        Category saveCategory = Category.createCategory(saveRequestDto);
        em.persist(saveCategory);

        em.flush();
        em.clear();

        categoryService.delete(saveCategory.getId());

        Category category = em.find(Category.class, saveCategory.getId());

        assertThat(category).isNull();
    }
}