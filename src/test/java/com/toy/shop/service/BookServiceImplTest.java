package com.toy.shop.service;

import com.toy.shop.domain.Book;
import com.toy.shop.domain.Category;
import com.toy.shop.dto.BookResponseDto;
import com.toy.shop.dto.BookSaveRequestDto;
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

    @Test
    void findById() {
        CategorySaveRequestDto categorySaveRequestDto = new CategorySaveRequestDto();
        categorySaveRequestDto.setName("category1");

        Category saveCategory = Category.createCategory(categorySaveRequestDto);

        em.persist(saveCategory);

        BookSaveRequestDto bookSaveRequestDto = new BookSaveRequestDto();
        bookSaveRequestDto.setName("이것이 자바다");
        bookSaveRequestDto.setDescription("자바 기본 도서");
        bookSaveRequestDto.setPublisher("한빛미디어");
        bookSaveRequestDto.setAuthor("신용권");
        bookSaveRequestDto.setPrice(10000);
        bookSaveRequestDto.setQuantity(1000);
        bookSaveRequestDto.setCategoryId(saveCategory.getId());

        Book saveBook = Book.createBook(bookSaveRequestDto, saveCategory);

        em.persist(saveBook);

        em.flush();
        em.clear();

        BookResponseDto bookResponseDto = bookService.findById(saveBook.getId());

        Assertions.assertThat(saveBook.getId()).isEqualTo(bookResponseDto.getId());
    }

    @Test
    void findAll() {
        CategorySaveRequestDto categorySaveRequestDto = new CategorySaveRequestDto();
        categorySaveRequestDto.setName("category1");

        Category saveCategory = Category.createCategory(categorySaveRequestDto);

        em.persist(saveCategory);

        BookSaveRequestDto bookSaveRequestDto1 = new BookSaveRequestDto();
        bookSaveRequestDto1.setName("이것이 자바다");
        bookSaveRequestDto1.setDescription("자바 기본 도서");
        bookSaveRequestDto1.setPublisher("한빛미디어");
        bookSaveRequestDto1.setAuthor("신용권");
        bookSaveRequestDto1.setPrice(10000);
        bookSaveRequestDto1.setQuantity(1000);
        bookSaveRequestDto1.setCategoryId(saveCategory.getId());

        Book saveBook1 = Book.createBook(bookSaveRequestDto1, saveCategory);

        em.persist(saveBook1);

        BookSaveRequestDto bookSaveRequestDto2 = new BookSaveRequestDto();
        bookSaveRequestDto2.setName("이것이 자바다");
        bookSaveRequestDto2.setDescription("자바 기본 도서");
        bookSaveRequestDto2.setPublisher("한빛미디어");
        bookSaveRequestDto2.setAuthor("신용권");
        bookSaveRequestDto2.setPrice(10000);
        bookSaveRequestDto2.setQuantity(1000);
        bookSaveRequestDto2.setCategoryId(saveCategory.getId());

        Book saveBook2 = Book.createBook(bookSaveRequestDto2, saveCategory);

        em.persist(saveBook2);

        em.flush();
        em.clear();

        List<BookResponseDto> list = bookService.findAll(null, null);

        List<Long> collect = list.stream()
                .map(BookResponseDto::getId)
                .toList();

        assertThat(collect).containsOnly(saveBook1.getId(), saveBook2.getId());
    }
}