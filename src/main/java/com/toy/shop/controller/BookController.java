package com.toy.shop.controller;

import com.toy.shop.domain.Book;
import com.toy.shop.controller.dto.BookSaveRequest;
import com.toy.shop.controller.dto.BookSaveResponse;
import com.toy.shop.controller.dto.BookUpdateRequest;
import com.toy.shop.controller.dto.ApiResultResponse;
import com.toy.shop.exception.UserException;
import com.toy.shop.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ApiResultResponse books() {
        log.info("상품 목록 조회 API 호출");

        throw new UserException("사용자 에러");
    }

    @PostMapping
    public ApiResultResponse addBook(@RequestBody @Valid BookSaveRequest request) {
        log.info("상품 등록 API 호출");

        Book book = bookService.save(new Book(request.getName(), request.getPrice()));

        return new ApiResultResponse<>(new BookSaveResponse(book));
    }

    @GetMapping("/{id}")
    public ApiResultResponse book(@PathVariable String id) {
        log.info("상품 상세 조회 API 호출");

        HashMap<String, String> map = new HashMap<>();
        map.put("key", "value");

        return new ApiResultResponse("200", "정상응답", map);
    }

    @PatchMapping("{id}")
    public Object patchBook(@PathVariable String id, @RequestBody @Valid BookUpdateRequest dto) {
        log.info("상품 수정 API 호출");

        return dto;
    }

    @DeleteMapping("{id}")
    public Object deleteBook(@PathVariable String id) {
        return new Object();
    }
}
