package com.toy.shop.controller;

import com.toy.shop.common.ResultDto;
import com.toy.shop.dto.BookResponseDto;
import com.toy.shop.dto.BookSaveRequestDto;
import com.toy.shop.dto.BookUpdateRequestDto;
import com.toy.shop.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

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

    @GetMapping
    public ResultDto books(@RequestParam(required = false) Long categoryId, @RequestParam(required = false) String searchWord) {
        List<BookResponseDto> list = bookService.findAll(categoryId, searchWord);

        return new ResultDto(list);
    }

    @PatchMapping("{id}")
    public ResultDto patchBook(@PathVariable Long id, @RequestBody @Valid BookUpdateRequestDto requestDto) {
        BookResponseDto responseDto = bookService.update(id, requestDto);

        return new ResultDto(responseDto);
    }

    @DeleteMapping("{id}")
    public ResultDto deleteBook(@PathVariable Long id) {
        bookService.delete(id);

        return new ResultDto(null);
    }
}
