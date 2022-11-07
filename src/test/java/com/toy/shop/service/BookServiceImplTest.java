package com.toy.shop.service;

import com.toy.shop.dto.BookSaveRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Test
    void test() {
        BookSaveRequestDto requestDto = new BookSaveRequestDto();
        requestDto.setName("이것이 자바다");
        requestDto.setDescription("자바 기본책");
        requestDto.setPublisher("한빛미디어");
        requestDto.setAuthor("신용권");
        requestDto.setPrice(9000);
        requestDto.setQuantity(1000);
        requestDto.setCategoryId(1L);


    }
}