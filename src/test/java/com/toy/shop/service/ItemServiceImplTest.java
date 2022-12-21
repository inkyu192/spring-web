package com.toy.shop.service;

import com.toy.shop.domain.Item;
import com.toy.shop.domain.Category;
import com.toy.shop.dto.ItemResponseDto;
import com.toy.shop.dto.ItemSaveRequestDto;
import com.toy.shop.dto.ItemUpdateRequestDto;
import com.toy.shop.dto.CategorySaveRequestDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Transactional
@SpringBootTest
class ItemServiceImplTest {

    @Autowired
    ItemService itemService;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    void save() {
        CategorySaveRequestDto categorySaveRequestDto = new CategorySaveRequestDto();
        categorySaveRequestDto.setName("category1");

        Category saveCategory = Category.createCategory(categorySaveRequestDto);
        entityManager.persist(saveCategory);

        ItemSaveRequestDto itemSaveRequestDto = new ItemSaveRequestDto();
        itemSaveRequestDto.setName("이것이 자바다");
        itemSaveRequestDto.setDescription("자바 기본 도서");
        itemSaveRequestDto.setPrice(10000);
        itemSaveRequestDto.setQuantity(1000);
        itemSaveRequestDto.setCategoryId(saveCategory.getId());

        Long saveId = itemService.save(itemSaveRequestDto).getId();

        entityManager.flush();
        entityManager.clear();

        Item findItem = entityManager.find(Item.class, saveId);

        assertThat(saveId).isEqualTo(findItem.getId());
    }

    @Test
    void findAll() {
        CategorySaveRequestDto categorySaveRequestDto = new CategorySaveRequestDto();
        categorySaveRequestDto.setName("category1");

        Category saveCategory = Category.createCategory(categorySaveRequestDto);

        entityManager.persist(saveCategory);

        ItemSaveRequestDto itemSaveRequestDto1 = new ItemSaveRequestDto();
        itemSaveRequestDto1.setName("이것이 자바다");
        itemSaveRequestDto1.setDescription("자바 기본 도서");
        itemSaveRequestDto1.setPrice(10000);
        itemSaveRequestDto1.setQuantity(1000);
        itemSaveRequestDto1.setCategoryId(saveCategory.getId());

        Item saveItem1 = Item.createItem(itemSaveRequestDto1, saveCategory);

        entityManager.persist(saveItem1);

        ItemSaveRequestDto itemSaveRequestDto2 = new ItemSaveRequestDto();
        itemSaveRequestDto2.setName("이것이 자바다");
        itemSaveRequestDto2.setDescription("자바 기본 도서");
        itemSaveRequestDto2.setPrice(10000);
        itemSaveRequestDto2.setQuantity(1000);
        itemSaveRequestDto2.setCategoryId(saveCategory.getId());

        Item saveItem2 = Item.createItem(itemSaveRequestDto2, saveCategory);

        entityManager.persist(saveItem2);

        entityManager.flush();
        entityManager.clear();

        List<ItemResponseDto> list = itemService.findAll(null, null);

        List<Long> collect = list.stream()
                .map(ItemResponseDto::getId)
                .toList();

        assertThat(collect).containsOnly(saveItem1.getId(), saveItem2.getId());
    }

    @Test
    void findById() {
        CategorySaveRequestDto categorySaveRequestDto = new CategorySaveRequestDto();
        categorySaveRequestDto.setName("category1");

        Category saveCategory = Category.createCategory(categorySaveRequestDto);

        entityManager.persist(saveCategory);

        ItemSaveRequestDto itemSaveRequestDto = new ItemSaveRequestDto();
        itemSaveRequestDto.setName("이것이 자바다");
        itemSaveRequestDto.setDescription("자바 기본 도서");
        itemSaveRequestDto.setPrice(10000);
        itemSaveRequestDto.setQuantity(1000);
        itemSaveRequestDto.setCategoryId(saveCategory.getId());

        Item saveItem = Item.createItem(itemSaveRequestDto, saveCategory);

        entityManager.persist(saveItem);

        entityManager.flush();
        entityManager.clear();

        ItemResponseDto itemResponseDto = itemService.findById(saveItem.getId());

        Assertions.assertThat(saveItem.getId()).isEqualTo(itemResponseDto.getId());
    }

    @Test
    void update() {
        CategorySaveRequestDto categorySaveRequestDto = new CategorySaveRequestDto();
        categorySaveRequestDto.setName("category1");

        Category saveCategory = Category.createCategory(categorySaveRequestDto);

        entityManager.persist(saveCategory);

        ItemSaveRequestDto itemSaveRequestDto = new ItemSaveRequestDto();
        itemSaveRequestDto.setName("이것이 자바다");
        itemSaveRequestDto.setDescription("자바 기본 도서");
        itemSaveRequestDto.setPrice(10000);
        itemSaveRequestDto.setQuantity(1000);
        itemSaveRequestDto.setCategoryId(saveCategory.getId());

        Item saveItem = Item.createItem(itemSaveRequestDto, saveCategory);

        entityManager.persist(saveItem);

        entityManager.flush();
        entityManager.clear();

        ItemUpdateRequestDto itemUpdateRequestDto = new ItemUpdateRequestDto();
        itemUpdateRequestDto.setName("이것이 자바다 개정판");

        ItemResponseDto responseDto = itemService.update(saveItem.getId(), itemUpdateRequestDto);

        assertThat(itemUpdateRequestDto.getName()).isEqualTo(responseDto.getName());
        assertThat(itemSaveRequestDto.getDescription()).isEqualTo(responseDto.getDescription());
    }

    @Test
    void delete() {
        CategorySaveRequestDto categorySaveRequestDto = new CategorySaveRequestDto();
        categorySaveRequestDto.setName("category1");

        Category saveCategory = Category.createCategory(categorySaveRequestDto);

        entityManager.persist(saveCategory);

        ItemSaveRequestDto itemSaveRequestDto = new ItemSaveRequestDto();
        itemSaveRequestDto.setName("이것이 자바다");
        itemSaveRequestDto.setDescription("자바 기본 도서");
        itemSaveRequestDto.setPrice(10000);
        itemSaveRequestDto.setQuantity(1000);
        itemSaveRequestDto.setCategoryId(saveCategory.getId());

        Item saveItem = Item.createItem(itemSaveRequestDto, saveCategory);

        entityManager.persist(saveItem);

        entityManager.flush();
        entityManager.clear();

        itemService.delete(saveItem.getId());

        Item item = entityManager.find(Item.class, saveItem.getId());

        assertThat(item).isNull();
    }
}