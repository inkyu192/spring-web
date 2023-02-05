package com.toy.shop.repository;

import com.toy.shop.domain.Category;
import com.toy.shop.dto.CategoryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class CategoryJpaRepositoryTest {

    @Autowired
    CategoryJpaRepository categoryJpaRepository;

    @Test
    public void basicCRUD() {
        CategoryDto.SaveRequest requestA = new CategoryDto.SaveRequest();
        requestA.setName("TeamA");
        Category categoryA = Category.createCategory(requestA);

        CategoryDto.SaveRequest requestB = new CategoryDto.SaveRequest();
        requestB.setName("TeamB");
        Category categoryB = Category.createCategory(requestB);

        categoryJpaRepository.save(categoryA);
        categoryJpaRepository.save(categoryB);

        //단건 조회 검증
        Category findCategoryA = categoryJpaRepository.findById(categoryA.getId()).orElse(null);
        Category findCategoryB = categoryJpaRepository.findById(categoryB.getId()).orElse(null);

        assertThat(findCategoryA).isEqualTo(categoryA);
        assertThat(findCategoryB).isEqualTo(categoryB);

        //리스트 조회 검증
        List<Category> all = categoryJpaRepository.findAll();

        assertThat(all.size()).isEqualTo(2);

        //카운트 검증
        long count = categoryJpaRepository.count();

        assertThat(count).isEqualTo(2);

        //삭제 검증
        categoryJpaRepository.delete(categoryA);
        categoryJpaRepository.delete(categoryB);

        long deleteCount = categoryJpaRepository.count();

        assertThat(deleteCount).isEqualTo(0);
    }
}