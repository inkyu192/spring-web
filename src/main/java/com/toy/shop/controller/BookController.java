package com.toy.shop.controller;

import com.toy.shop.common.ResultDto;
import com.toy.shop.controller.dto.BookResponseDto;
import com.toy.shop.controller.dto.BookSaveRequestDto;
import com.toy.shop.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResultDto books(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String searchWord) {
        List<BookResponseDto> list = bookService.findAll(categoryId, searchWord);

        return new ResultDto(list);
    }

    @PostMapping
    public ResultDto addBook(@RequestBody @Valid BookSaveRequestDto requestDto) {
        BookResponseDto responseDto = bookService.save(requestDto);

        return new ResultDto(responseDto);
    }

    @GetMapping("/{id}")
    public ResultDto book(@PathVariable Long id) {
        BookResponseDto responseDto = bookService.findById(id);

        return new ResultDto(responseDto);
    }

//    @PatchMapping("{id}")
//    public Object patchBook(@PathVariable String id, @RequestBody @Valid BookUpdateRequestDto dto) {
//        log.info("상품 수정 API 호출");
//
//        return dto;
//    }

//    @DeleteMapping("{id}")
//    public Object deleteBook(@PathVariable String id) {
//        return new Object();
//    }
}
