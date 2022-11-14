package com.toy.shop.service;

import com.toy.shop.domain.Book;
import com.toy.shop.domain.Category;
import com.toy.shop.dto.BookSaveRequestDto;
import com.toy.shop.dto.CategorySaveRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Transactional
@SpringBootTest
class BookServiceImplTest {

    @Autowired
    BookService bookService;

    @PersistenceContext
    EntityManager em;

    @Test
    void save() {
        CategorySaveRequestDto categorySaveRequestDto = new CategorySaveRequestDto();
        categorySaveRequestDto.setName("category1");

        Category saveCategory = Category.createCategory(categorySaveRequestDto);
        em.persist(saveCategory);

        em.flush();
        em.clear();

        BookSaveRequestDto bookSaveRequestDto = new BookSaveRequestDto();
        bookSaveRequestDto.setName("이것이 자바다");
        bookSaveRequestDto.setDescription("자바 기본 도서");
        bookSaveRequestDto.setPublisher("한빛미디어");
        bookSaveRequestDto.setAuthor("신용권");
        bookSaveRequestDto.setPrice(10000);
        bookSaveRequestDto.setQuantity(1000);
        bookSaveRequestDto.setCategoryId(saveCategory.getId());

        Long saveId = bookService.save(bookSaveRequestDto).getId();

        Book findBook = em.find(Book.class, saveId);

        assertThat(saveId).isEqualTo(findBook.getId());
    }
}