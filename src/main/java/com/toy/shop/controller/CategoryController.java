package com.toy.shop.controller;

import com.toy.shop.common.ResultDto;
import com.toy.shop.dto.CategoryDto;
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
    public Object saveCategory(@RequestBody @Valid CategoryDto.SaveRequest requestDto) {
        CategoryDto.Response responseDto = categoryService.save(requestDto);

        return new ResultDto<>(responseDto);
    }

    @GetMapping
    public Object categories() {
        List<CategoryDto.Response> list = categoryService.findAll();

        return new ResultDto<>(list);
    }

    @GetMapping("/{id}")
    public Object category(@PathVariable Long id) {
        CategoryDto.Response responseDto = categoryService.findById(id);

        return new ResultDto<>(responseDto);
    }

    @PatchMapping("{id}")
    public Object updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryDto.UpdateRequest requestDto) {
        CategoryDto.Response responseDto = categoryService.update(id, requestDto);

        return new ResultDto<>(responseDto);
    }

    @DeleteMapping("{id}")
    public Object deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);

        return new ResultDto<>();
    }
}
