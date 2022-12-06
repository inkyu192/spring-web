package com.toy.shop.controller;

import com.toy.shop.common.ResultDto;
import com.toy.shop.dto.CategoryResponseDto;
import com.toy.shop.dto.CategorySaveRequestDto;
import com.toy.shop.dto.CategoryUpdateRequestDto;
import com.toy.shop.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public Object saveCategory(@RequestBody @Valid CategorySaveRequestDto requestDto) {
        CategoryResponseDto responseDto = categoryService.save(requestDto);

        return new ResultDto<>(responseDto);
    }

    @GetMapping
    public Object categories(@RequestParam(required = false) String searchWord) {
        List<CategoryResponseDto> list = categoryService.findAll(searchWord);

        return new ResultDto<>(list);
    }

    @GetMapping("/{id}")
    public Object category(@PathVariable Long id) {
        CategoryResponseDto responseDto = categoryService.findById(id);

        return new ResultDto<>(responseDto);
    }

    @PatchMapping("{id}")
    public Object updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryUpdateRequestDto requestDto) {
        CategoryResponseDto responseDto = categoryService.update(id, requestDto);

        return new ResultDto<>(responseDto);
    }

    @DeleteMapping("{id}")
    public Object deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);

        return new ResultDto<>();
    }
}
